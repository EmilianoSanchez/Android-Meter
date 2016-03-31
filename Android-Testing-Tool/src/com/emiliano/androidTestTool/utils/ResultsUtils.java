package com.emiliano.androidTestTool.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.emiliano.androidTestTool.core.Results;
import com.opencsv.CSVWriter;

public class ResultsUtils {

	public static void saveToCSV(Results results, File file) throws IOException {
		List<Map<String, Object>> desnormalizedResults = getDesnormalizedResults(results);
		CSVWriter writer = null;
		writer = new CSVWriter(new FileWriter(file), ';');
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
		writer.flush();
		writer.close();
	}

	public static List<Map<String, Object>> getDesnormalizedResults(Results results) {

		List<Map<String, Object>> dresults = new LinkedList<Map<String, Object>>();
		Map<String, Object> globalMeasures = results.getGlobalMeasures();
		
		List<Map<String, Object>> inputMeasures=results.getInputMeasures();
		for (int i=0;i< inputMeasures.size();i++) {
			List<Map<String, Object>> componentMeasures=results.getComponentMeasures();
			for (int c=0;c< componentMeasures.size();c++) {
				Map<String, Object> operationMeasures=results.getOperationMeasures(i, c);
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
