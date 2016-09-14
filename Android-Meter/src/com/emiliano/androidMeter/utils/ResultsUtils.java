package com.emiliano.androidMeter.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.emiliano.androidMeter.core.Results;
import com.opencsv.CSVWriter;

import android.util.Log;

public class ResultsUtils {

	public static void saveToSQLite(Results results, File file) throws IOException {
//		TO DO
	}

	public static void saveToFirebase(Results results, URL url) throws IOException {
//		TO DO
	}
	
	public static void saveToCSV_InputComponentMatrix(Results results, File file,String inputMetric,String componentMetric,String operationMetric) throws IOException {

		CSVWriter writer = null;
		writer = new CSVWriter(new FileWriter(file), ',');
		
		results.getComponentMeasures();

		String[] headers = new String[results.getComponentMeasures().size() + 1];
		headers[0] = inputMetric+'|'+componentMetric+':'+operationMetric;
		for (int i=0;i<results.getComponentMeasures().size();i++) {
			headers[i+1] = results.getComponentMeasures(i).get(componentMetric).toString();
		}
		writer.writeNext(headers);
		
		for (int i = 0; i < results.getInputMeasures().size(); i++) {
			String[] tupla = new String[results.getComponentMeasures().size() + 1];
			tupla[0] = results.getInputMeasures(i).get(inputMetric).toString();
			for (int j=0;j<results.getComponentMeasures().size();j++) {
				tupla[j+1]=results.getOperationMeasures(i, j).get(operationMetric).toString();
			}
			writer.writeNext(tupla);
		}
		writer.flush();
		writer.close();
	}
	
	public static void saveToCSV_DesnormalizedData(File file,Results... results) throws IOException {
		CSVWriter writer = null;
		writer = new CSVWriter(new FileWriter(file), ',');
		for(int r=0;r<results.length;r++){
			List<Map<String, Object>> desnormalizedResults = getDesnormalizedResults(results[r]);
			
			if (desnormalizedResults.size() > 0) {
				Map<String, Object> operationResult = desnormalizedResults.get(0);
				String[] headers = new String[operationResult.size() + 1];
				headers[0] = "OperationID";
				int pos = 1;
				for (String header : operationResult.keySet()) {
					headers[pos] = header;
					pos++;
				}
				writer.writeNext(headers);
	
				for (int i = 0; i < desnormalizedResults.size(); i++) {
					Map<String, Object> oper = desnormalizedResults.get(i);
					String[] tupla = new String[headers.length];
					tupla[0] = Integer.toString(i);
					int po = 1;
					for (Object object : oper.values()) {
						tupla[po] = object.toString();
						po++;
					}
					writer.writeNext(tupla);
				}
			}
		}
		writer.flush();
		writer.close();
	}

	public static List<Map<String, Object>> getDesnormalizedResults(Results results) {

		List<Map<String, Object>> dresults = new LinkedList<Map<String, Object>>();
		Map<String, Object> globalMeasures = results.getGlobalMeasures();
		List<Map<String, Object>> inputMeasures = results.getInputMeasures();
		List<Map<String, Object>> componentMeasures = results.getComponentMeasures();

		for (int i = 0; i < inputMeasures.size(); i++) {
			for (int c = 0; c < componentMeasures.size(); c++) {
				Map<String, Object> operationMeasures = results.getOperationMeasures(i, c);
				Map<String, Object> result = new TreeMap<String, Object>();
				result.putAll(globalMeasures);
				result.putAll(inputMeasures.get(i));
				result.putAll(componentMeasures.get(c));
				result.putAll(operationMeasures);
				dresults.add(result);
			}
		}
		return dresults;
	}
}
