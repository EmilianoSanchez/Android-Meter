package com.emiliano.androidTestTool.utils;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.emiliano.androidTestTool.R;
import com.emiliano.androidTestTool.core.Executor;
import com.emiliano.androidTestTool.core.Results;
import com.emiliano.androidTestTool.core.TestPlan;
import com.emiliano.androidTestTool.core.Executor.ExecutorListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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

public abstract class TestToolActivity<Input, Output> extends Activity implements ExecutorListener {

	// protected ListView listView;
	protected Results<Input, Output>[] results;
	protected ProgressDialog progressDialog;

	protected Executor<Input, Output> executor;
	protected TestPlan<Input, Output>[] plans;

	protected abstract TestPlan<Input, Output>[] getTestPlans();

	protected Executor<Input, Output> getExecutor(ExecutorListener listener) {
		return new Executor<Input, Output>(listener);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test_tool_activity);
		// listView = (ListView) this.findViewById(R.id.listView);
	}

	public void executePlan(View view) {
		executor = getExecutor(this);
		plans = getTestPlans();
		executor.execute(plans);
	}

	public void saveResultsTogether(View view) {
		Log.i("BlackBox", "Saving data...");
		if (results != null) {
			try {
				File file = getFilePath("RESULTADOS-"+this.getClass().getSimpleName()+".csv");
				ResultsUtils.saveToCSV_DesnormalizedData(file, results);

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
			}
		}
	}

	public void saveResults(View view) {
		Log.i("BlackBox", "Saving data...");
		if (results != null) {
			try {
				File file = null;
				for (int r = 0; r < results.length; r++) {
						file = getFilePath(plans[r].getName() + ".csv");
						ResultsUtils.saveToCSV_DesnormalizedData(file, results[r]);
				}
					
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
			}
		}
	}

	public void saveResultsMatrix(View view) {
		Log.i("BlackBox", "Saving data...");
		if (results != null) {
			try {
				File file=null;
				for (int r = 0; r < results.length; r++) {
					 file = getFilePath(plans[r].getName() + ".csv");
					ResultsUtils.saveToCSV_InputComponentMatrix(results[r], file,
							plans[r].getInputMetrics().get(0).getName(),
							plans[r].getComponentMetrics().get(0).getName(),
							plans[r].getOperationMetrics().get(0).getName());
				}
				AlertDialog dialog = new AlertDialog.Builder(this).setMessage("Results were saved in " + file.getAbsolutePath())
						.setTitle("Results saved").setPositiveButton("OK", new DialogInterface.OnClickListener() {

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
			}
		}
	}

	protected File getFilePath(String fileName) throws IOException {
		String externalDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		if (externalDir == null)
			throw new IOException("No external storage available");
		return new File(externalDir + "/" + fileName);
	}

	@Override
	public void onPreExecute() {
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("Test plan progress");
		progressDialog.setMessage("Starting test plan");
		progressDialog.show();
	}

	@Override
	public void onProgressUpdate(String... progress) {
		progressDialog.setMessage(progress[0]);
	}

	@Override
	public void onPostExecute(Results[] allResults) {
		this.results = allResults;
		progressDialog.dismiss();
		progressDialog = null;
		// listView.setAdapter(new
		// ResultsAdapter(ResultsUtils.getDesnormalizedResults(results)));
		Button saveResults = (Button) this.findViewById(R.id.saveResults);
		saveResults.setEnabled(true);
		Button saveResultsMatrix = (Button) this.findViewById(R.id.saveResultsMatrix);
		saveResultsMatrix.setEnabled(true);
		Button saveResultsTogether = (Button) this.findViewById(R.id.saveResultsTogether);
		saveResultsTogether.setEnabled(true);
	}

	public static class ResultsAdapter extends BaseAdapter {

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
