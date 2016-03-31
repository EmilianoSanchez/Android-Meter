package com.emiliano.examplesatt.examples.matrix;

import com.emiliano.androidTestTool.core.TestPlanImpl;
import com.emiliano.androidTestTool.core.metrics.component.ComponentName;
import com.emiliano.androidTestTool.core.metrics.operation.ResponseTimeMetric;

import android.content.Context;

public class TestPlanMatrix extends TestPlanImpl<MatrixPair, double[][]> {

	public static final int INPUT_SAMPLES = 20;

	@SuppressWarnings("unchecked")
	public TestPlanMatrix(Context context) {
		super("Test plan matrix multiplication",context);
		this.addComponents(new MatrixMultiplication());
		this.addComponents(new MatrixMultiplicationMultiThread());

		for (int i = 1; i < 200; i++) {
			this.addInputs(MatrixPair.generateRandom(i, i, i));
		}

		// int sizes[]=new int[]{25,50,75,100,125,150,175,200,225,250};
		// for(int i=0;i<sizes.length;i++){
		// this.addInputs(MatrixPair.generateRandom(sizes[i], sizes[i],
		// sizes[i]));
		// }

		// for(int i=0;i<INPUT_SAMPLES;i++){
		// Random random=new Random();
		// int aRows=random.nextInt(100)+100;
		// int aColums=random.nextInt(100)+100;
		// int bColums=random.nextInt(100)+100;
		// this.addInputs(MatrixPair.generateRandom(aRows, aColums, bColums));
		// }

		// Static context metrics
		// this.addContextMetrics(new Cpu2dMark());
		// this.addContextMetrics(new Cpu3dMark());
		// this.addContextMetrics(new CpuArchitecture());
		// this.addContextMetrics(new CpuCores());
		// this.addContextMetrics(new CpuMark());
		// this.addContextMetrics(new DeviceModel());
		// this.addContextMetrics(new DiskMark());
		// this.addContextMetrics(new MemMark());
		// this.addContextMetrics(new MemSize());

		// Component metrics
		this.addComponentMetrics(new ComponentName());

		// Input metrics
		this.addInputMetrics(new ARowsMetric());
		// this.addInputMetrics(new AColumnMetric());
		// this.addInputMetrics(new BColumnMetric());

		// Operation metrics
		// this.addOperationMetrics(new MemFree(context));
		// this.addOperationMetrics(new MemUsage(context));
		this.addOperationMetrics(new ResponseTimeMetric());
	}

}
