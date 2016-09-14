package com.emiliano.examplesAndroidMeter.matrixMultiplication;

import java.util.Random;

import com.emiliano.androidMeter.core.BenchmarkPlanImpl;
import com.emiliano.androidMeter.core.metrics.component.ComponentName;
import com.emiliano.androidMeter.core.metrics.operation.ResponseTimeMetric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.components.EigenMatrixMultiplication;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.components.MultiThreadMatrixMultiplication;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.components.NativeMatrixMultiplication;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.components.OpenCVMatrixComputation;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.components.SingleThreadMatrixMultiplication;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics.AColumnMetric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics.ARowsMetric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics.BColumnMetric;
import com.emiliano.examplesAndroidMeter.matrixMultiplication.metrics.ComplexityMetric;

import android.content.Context;

public class MatrixRandomBenchmarkPlan extends BenchmarkPlanImpl<MatrixPair, float[][]> {

	public static final int INPUT_SAMPLES = 20;

	@SuppressWarnings("unchecked")
	public MatrixRandomBenchmarkPlan(Context context) {
		super("Benchmark plan matrix multiplication random", context);
		this.addComponents(new SingleThreadMatrixMultiplication());
		this.addComponents(new MultiThreadMatrixMultiplication());
//		this.addComponents(new RenderScriptMatrixMultiplication(context));
		this.addComponents(new NativeMatrixMultiplication());
		this.addComponents(new EigenMatrixMultiplication());
		this.addComponents(new OpenCVMatrixComputation());

		for (int i = 1; i < 150; i++) {
			int[] factors=descomposeFactors(i);
			this.addInputs(new MatrixPairLoader(factors[0], factors[1], factors[2]));
//			this.addInputs(new MatrixPairLoader(i, i+10, i+5));
		}

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
		this.addInputMetrics(new AColumnMetric());
		this.addInputMetrics(new BColumnMetric());
		this.addInputMetrics(new ComplexityMetric());

		// Operation metrics
		this.addOperationMetrics(new ResponseTimeMetric());
	}

	private int[] descomposeFactors(int size) {
		if(size>1){
			int complexity=size*size*size;
			int value1=random(size-size/2,size+size/2);
			int value2=random(size-size/2,size+size/2);
			int value3=(complexity/value1)/value2;
			return new int[]{value1,value2,value3};
		}else
			return new int[]{1,1,1};
	}

	Random rn=new Random();
	int random(int min,int max){
		int result = rn.nextInt(max - min + 1) + min;
		return result;
	}
}
