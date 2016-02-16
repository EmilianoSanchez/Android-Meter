package com.emiliano.evalfd.testPlan;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import com.emiliano.evalfd.FacePosition;
import com.emiliano.evalfd.InputFaceDetection;

import android.util.Log;

public class FDDB_DataSetUtils {
	public static List<InputFaceDetection> getInput(String fddbFilePath) {
		List<InputFaceDetection> result = new LinkedList();
		File file = new File(fddbFilePath);
		String folder = file.getParent();
		if (file.exists()) {
			FileInputStream is;
			try {
				is = new FileInputStream(file);

				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = reader.readLine();
				while (line != null) {
					InputFaceDetection input = new InputFaceDetection(new File(folder + "/" + line + ".jpg"), 0);
					result.add(input);
					line = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("File " + fddbFilePath + " do not exist");
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		} else
			throw new IllegalArgumentException("File " + fddbFilePath + " do not exist");
		return result;
	};

	public static List<InputFaceDetection> getInputFromEllipseList(String fddbFilePath) {
		List<InputFaceDetection> result = new LinkedList();
		File file = new File(fddbFilePath);
		String folder = file.getParent();
		if (file.exists()) {
			FileInputStream is;
			try {
				is = new FileInputStream(file);

				BufferedReader reader = new BufferedReader(new InputStreamReader(is));
				String line = reader.readLine();
				String auxLine;
				while (line != null) {
					auxLine = reader.readLine();
					int realFaces = Integer.parseInt(auxLine);
					FacePosition[] facePositions=new FacePosition[realFaces];
					for (int i = 0; i < realFaces; i++) {
						auxLine = reader.readLine();
						String[] splited = auxLine.split(" ");
						double major_axis_radius = Double.parseDouble(splited[0]);
						double minor_axis_radius = Double.parseDouble(splited[1]);
						double angle = Double.parseDouble(splited[2]);
						double center_x = Double.parseDouble(splited[3]);
						double center_y = Double.parseDouble(splited[4]);
						facePositions[i]=new FacePosition(center_x-minor_axis_radius,center_y-major_axis_radius,minor_axis_radius*2.0,major_axis_radius*2.0);
					}
					
					InputFaceDetection input = new InputFaceDetection(new File(folder + "/" + line + ".jpg"),facePositions);
					result.add(input);
					line = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("File " + fddbFilePath + " do not exist");
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
		} else
			throw new IllegalArgumentException("File " + fddbFilePath + " do not exist");
		return result;
	};
}
