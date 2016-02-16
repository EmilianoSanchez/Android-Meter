package com.emiliano.androidTestTool.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.emiliano.androidTestTool.R;
import com.emiliano.androidTestTool.core.Executor;
import com.emiliano.androidTestTool.core.ExecutorListener;
import com.emiliano.androidTestTool.core.Results;
import com.emiliano.androidTestTool.core.TestPlan;
import com.opencsv.CSVWriter;

public abstract class TestToolActivity<Input, Output> extends Activity implements ExecutorListener {

	ProgressDialog progressDialog;
	ListView listView;
	Results results;

	protected abstract TestPlan<Input, Output> getPlan();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_tool_activity);

		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Test plan progress");

		listView = (ListView) this.findViewById(R.id.listView);
	}

	public void executePlan(View view) {
		Executor<Input, Output> executor = new Executor<Input, Output>(this, this);
		TestPlan<Input, Output> plan = getPlan();
		executor.execute(plan);
	}

	public void saveResults(View view) {
		Log.i("BlackBox", "Saving data...");
		if (results != null) {
//			List<Map<String, Object>> operationResults = this.results.getOperationResults();
			List<Map<String, Object>> desnormalizedResults = this.results.getDesnormalizedResults();
			if (desnormalizedResults != null && desnormalizedResults.size() > 0) {
				
				try {
					File file = getFilePath();
//					saveFileCSVWriter(file,desnormalizedResults);		
					saveFile(file,desnormalizedResults);
					
					AlertDialog dialog = new AlertDialog.Builder(this)
							.setMessage("Results were saved in " + file.getAbsolutePath()).setTitle("Results saved")
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							}).create();
					dialog.show();
				} catch (IOException e) {
					AlertDialog dialog = new AlertDialog.Builder(this).setMessage(e.getMessage())
							.setTitle("Error saving results")
							.setPositiveButton("OK", new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog, int which) {
									dialog.dismiss();
								}
							}).create();
					dialog.show();
					// Log.e("BlackBox", e.getMessage());
				}
			}
		}
//		this.findViewById(R.id.saveResults).setEnabled(false);
	}

	private void saveFile(File file, List<Map<String, Object>> desnormalizedResults) throws IOException {
		FileOutputStream writer = null;
		writer = new FileOutputStream(file);
		Map<String, Object> operationResult = desnormalizedResults.get(0);
		
		writer.write("OperationID".getBytes());
		for (String header : operationResult.keySet()) {
			writer.write( ";".getBytes());
			writer.write( header.getBytes());
		}
		writer.write("\n".getBytes());

		for (int i = 0; i < desnormalizedResults.size(); i++) {
			Map<String, Object> oper = desnormalizedResults.get(i);
			writer.write(Integer.toString(i).getBytes());
			for (Object object : oper.values()) {
				writer.write( ";".getBytes());
				writer.write(object.toString().getBytes());
			}
			writer.write("\n".getBytes());
		}
		writer.flush();
		writer.close();
	}
	
	private void saveFileCSVWriter(File file, List<Map<String, Object>> desnormalizedResults) throws IOException {
		CSVWriter writer = null;
		writer = new CSVWriter(new FileWriter(file),';');

		Map<String, Object> operationResult = desnormalizedResults.get(0);
		String[] headers = new String[operationResult.size()];
		headers[0] = "OperationID";
		int pos = 1;
		for (String header : operationResult.keySet()) {
			headers[pos] = header;
			pos++;
		}
		writer.writeNext(headers);

		for (int i = 0; i < desnormalizedResults.size(); i++) {
			Map<String, Object> oper = desnormalizedResults.get(i);
			String[] head = new String[headers.length];
			head[0] = Integer.toString(i);
			int po = 1;
			for (Object object : oper.values()) {
				head[po] = object.toString();
				po++;
			}
			writer.writeNext(head);
		}
		writer.flush();
		writer.close();
	}

	private File getFilePath() throws IOException {
		// File externalDir = this.getExternalFilesDir(null);
		String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		if (externalDir == null)
			throw new IOException("No external storage available");
		File file;
		int number = 0;
		do {
			number++;
			// file=new
			// File(externalDir.getAbsolutePath()+"/results"+number+".csv");
			file = new File(externalDir + "/results" + number + ".csv");
			Log.i("BlackBox", file.getAbsolutePath());
		} while (file.exists());
		return file;
	}

	@Override
	public void onTestPlanStarted() {
		progressDialog.setMessage("Starting test plan");
		progressDialog.show();
	}

	@Override
	public void onProgressUpdate(String progress) {
		progressDialog.setMessage(progress);
	}

	@Override
	public void onTestPlanFinished(Results allResults[]) {
		this.results = allResults[0];
		progressDialog.dismiss();
		listView.setAdapter(new ResultsAdapter(results.getDesnormalizedResults()));
		Button saveResults = (Button) this.findViewById(R.id.saveResults);
		saveResults.setEnabled(true);
	}

	private static class ResultsAdapter extends BaseAdapter {

		private List<Map<String, Object>> operationResults;

		public ResultsAdapter(List<Map<String, Object>> operationResults) {
			this.operationResults = operationResults;
		}

		@Override
		public int getCount() {
			return operationResults.size() + 1;
		}

		@Override
		public Object getItem(int position) {
			if (position == 0) {
				if (this.operationResults.size() > 0)
					return this.operationResults.get(0).keySet();
				else
					return null;
			}
			return this.operationResults.get(position - 1);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == 0) {
				LinearLayout view = new LinearLayout(parent.getContext());
				view.setOrientation(LinearLayout.HORIZONTAL);
				if (this.operationResults.get(0) != null) {
					Set<String> values = this.operationResults.get(0).keySet();
					if (values != null)
						for (String object : values) {
							TextView text = new TextView(parent.getContext());
							text.setText(object);
							view.addView(text);

							View line = new View(parent.getContext());
							line.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT));
							line.setBackgroundColor(Color.rgb(51, 51, 51));
							view.addView(line);
						}
				}
				return view;
			} else {
				LinearLayout view = new LinearLayout(parent.getContext());
				view.setOrientation(LinearLayout.HORIZONTAL);
				Map<String, Object> values = this.operationResults.get(position - 1);
				if (values != null)
					for (Object object : values.values()) {
						TextView text = new TextView(parent.getContext());
						text.setText(object.toString());
						view.addView(text);

						View line = new View(parent.getContext());
						line.setLayoutParams(new LayoutParams(1, LayoutParams.MATCH_PARENT));
						line.setBackgroundColor(Color.rgb(51, 51, 51));
						view.addView(line);
					}
				return view;
			}
		}

	}
}
