#Android Performance Testing and Prediction
An Android tool and method for testing and predicting performance properties of runtime components using machine learning techniques.

####Table of Contents
- [Objectives](#objectives)
- [Concepts](#concepts)
  * [Component](#component)
  * [Operation](#operation)
  * [Execution context](#execution-context)
  * [Test plan](#test-plan)
  * [Metrics](#metrics)
  * [Results](#results)
- [Workflow](#workflow)
  * [Measure](#measure)
  * [Predict](#predict)
  * [Apply](#apply)
- [To do](#to-do)


##Objectives
The performance of runtime components (algoritms, Web services, background processes, etc) depends on several factors: the **execution context** where the component is running, the **input parameters** of the requested component operation, and its **internal implementation**. However, when using third-party components, we usually do not have knowledge of its internal implementation. This components are “black-boxes” to mobile developers, for which their Application Programming Interfaces (APIs) are known but not their internal working.

To have some insight of their performance, machine learning techniques over empirically collected data can be used to build prediction models of the component’s runtime, as a function of input parameters and execution context specific features. 

This method focus on the following properties:
 - **Response time**: it is the total amount of time that a component takes to perform a task or operation, i.e. to respond to a request with a given input. A response time model over a component **C** is defined as a function **Rc(E,I)=r**, being **E** a set of measured execution context features **E=[E1,...Ee]** (like CPU cores, Network type, etc), **I** a set of measured input parameter features **I=[I1,...Ii]** (input size in bytes, etc), and **r** the estimated response time.
 - **Accuracy**: it is a measure of the quality of the component output results. It has different meanings depending on the required functionality. For instance, in classification problems, it is the statistical measure of how well a classifier, like a face detector, correctly identifies or excludes a condition. In optimization problems, it is also known as Optimality and is usually measured as a ratio between the obteined output solution and the optimal known solution. In most cases, accuracy do not depends on execution context features, in contrast to response time. Therefore, an accuracy model over a component **C** is defined as a function **Ac(I)=a**, being  **I** a set of measured input parameter features **I=[I1,...Ii]**, and **a** the estimated accuracy.

Such models are known as **empirical performance models** (EPM), and are useful in a variety of practical contexts:
 - Algoritm selection and configuration: performance models can be used to dynamically select the best from a given set
of algorithm configurations, depending on the problem instance and hardware characteristics. 
 - Service selection and composition: when several Web services implements the same functionality, performance models are a good criteria for choosing the best candidate among them. Even at runtime, service providers can be switched if context and input parameter conditions change. 
 - Task scheduling in mobile grids: given a set of tasks to be assigned among a set of devices, performance models can obtain an accurate measure of the response time of each task over each device in order to minimize the makespan. 
 - Others.

##Concepts

###Component
A component is an individual runtime software entity that provides services (a set of related functions and data) through an specific interface (API). The tool focus on three kind of components:
- **Object instances**, components that reside on the application memory space. 
- **Android Services**, background processes that reside on the same device and are accessed via [Intents](http://developer.android.com/intl/es/reference/android/content/Intent.html), the interprocess communication mechanism implemented by Android OS. 
- **Web Services**, remote components that reside outside the device and are accessed via Web communication protocols, like HTTP.

The tool use a simplyfied component representation defined by the **Component** interface. The **execute** method must implement the component behaviour as a blocking function.

Example of an Object instance component:
```java
Component<Integer[], Integer> sum = new Component<Integer[], Integer>() {

	@Override
	public String getName() {
		return "Sum";
	}

	@Override
	public Integer execute(Integer[] input) {
		int output = 0;
		for (int element : input)
			output += element;
		return output;
	}
};
```
Android and Web Services components can be easily integrated by implementing **AndroidServiceClient** and **WebServiceClient** classes respectively. They extends the Component interface.

![Components](/Documentation/Images/Components.png)

###Operation:
An operation, task or job is a unit of work performed by a component. An operation is requested by calling the execute method of the component with a given **Input** object. When the operation is done, an **Output** object is returned.

- Input: object that encapsulates the input parameters of the requested component operation. This object may include data and configuration parameters.
- Output: object that encapsulates the response of the operation.

###Execution context
The execution context is the environment where the components reside. Since environment conditions vary dynamically, operation performance also vary.

###Test plan
A test plan is an object instance of the **TestPlan** class that defines a systemathic execution of operations and measures over them. 
A test plan is composed by: 
- a set of components, 
- a set of input objects,
- and a set of **metrics** that carry out the measures.

An **Executor** object executes the test plans and stores the measured values on a **Result** object instance. The following pseudo-code describe the execution sequence of operations and measures:

```java
public class Executor {
	...
	public Result executeTestPlan(TestPlan testPlan){
		Results results=new Results();
		for(GlobalMetric m : testPlan.getGlobalMetrics())
			results.addGlobalMeasure(m.calculate(testPlan));
		for(Input i : testPlan.getInputs())
			for(InputMetric m : testPlan.getInputMetrics())
				results.addInputMeasure(m.calculate(i));
		for(Component c : testPlan.getComponents())
			for(ComponentMetric m : testPlan.getComponentMetrics())
				results.addComponentMeasure(m.calculate(c));
		for(Input i : testPlan.getInputs())
			for(Component c : testPlan.getComponents()){
				for(OperationMetric m : testPlan.getOperationMetrics())
					m.beforeOperation(i,c);
				Output o=c.execute(i);
				for(OperationMetric m : testPlan.getOperationMetrics())
					results.addOperationMeasure(m.calculate(o));
			}
		return Results;
	}
}
```
###Metrics
They are the units of code that computes a measure or feature value from some element. The tool distinguish 4 kind of metrics depending on the measured element:
- **Global metrics** compute **static context features**, i.e., execution context features that remain static during the test plan, like the device model, CPU architecture, number of CPU cores, memory size, etc.
- **Input metrics** compute **input features** that depends on the problem domain. For instance, in face detection on images, some input features are the image name, size, color contrast, file format, etc.  
- **Component metrics** compute **component features** like its name, location, etc.
- **Operation metrics** compute three kind of features:
 * **Output features**: these are characteristics of the operation result, like its size, quality, etc. Like input features, they depend on the problem domain. In face detection for instance, the output is a vector with the location of the detected features, therefore, an interesting output feature is the size of this vector, i.e., the number of detected faces.
 * **Dynamic context features**: this are characteristics of the execution environment that may vary from operation to operation, like the CPU usage, number of runnning processes, connection type, device location, etc.
 * **Performance features**: this are the performance measures of interest that vary from operation to operation, like response time, consumed battery, operation executed with error or not, etc. 

###Results
It is the object that stores the measured data and exports it into a CSV file for its latter processing. 

##Workflow
- **Measure** (or test): obtain empirical data performing a test plan.
- **Predict**: use the collected data for building and evaluating prediction models
- **Apply**: use the models in some practical context: algoritm configuration, service selection, job scheduling, etc.

###Measure
###Predict
###Apply

##To do
- More metrics
- Load simulation
- Incremental prediction models in Android
- Distributed prediction models in Android
