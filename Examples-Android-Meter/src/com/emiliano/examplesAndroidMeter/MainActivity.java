package com.emiliano.examplesAndroidMeter;

import com.emiliano.examplesAndroidMeter.dummySample.ActivityDummysample;
import com.emiliano.examplesAndroidMeter.knapsackProblem.ActivityKnapsackProblem;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.ActivityMatrix;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {
	class ItemVo {
		public String filename;
		public Class<?> cls;
		public String label;

		public ItemVo(String $label, Class<?> $class, String $filename) {
			label = $label;
			cls = $class;
			filename = $filename;
		}
	}

	private ItemVo[] _items = {
			new ItemVo("Dummy example", ActivityDummysample.class, "ActivityDummysample.java"),
			new ItemVo("Matrix multiplication example", ActivityMatrix.class, "ActivityMatrix.java"),
			new ItemVo("Knapsack problem example", ActivityKnapsackProblem.class, "ActivityKnapsackProblem.java")
			};
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		String manufacturer = Build.MANUFACTURER;
		String model = Build.MODEL;
		Toast.makeText(this, manufacturer + " " + model, 3).show();

		String[] strings = new String[_items.length];
		for (int i = 0; i < _items.length; i++) {
			strings[i] = _items[i].label;
		}

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));

		registerForContextMenu(getListView());

	}

	@Override
	public void onListItemClick(ListView parent, View v, int position, long id) {
		this.startActivity(new Intent(this, _items[position].cls));
	}
}