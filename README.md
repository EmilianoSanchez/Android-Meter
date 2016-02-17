# Android Performance Testing and Prediction
An Android tool and method for testing and predicting performance properties of runtime components using machine learning techniques.

## Objectives
The performance of runtime components (algoritms, Web services, background processes, etc) depends on several factors: the **execution context** where the component is running, the **input parameters** of the requested component operation, and its **internal implementation**. However, when using third-party components, we usually do not have knowledge of its internal implementation. This components are “black-boxes” to mobile developers, for which their Application Programming Interfaces (APIs) are known but not their internal working.

To have some insight of their performance, machine learning techniques over empirically collected data can be used to build prediction models of the component’s runtime, as a function of input parameters and execution context specific features. This method focus on the following properties:
 - Response time: it is the total amount of time that a component takes to perform a task or operation, i.e. to respond to a request. A response time model over a component **C** is defined as a function **Rc(E,I)=r**, being **E** a set of measured execution context features **E=[E1,...Ee]** (like CPU cores, Network type, etc), **I** a set of measured input parameter features **I=[I1,...Ii]** (input size in bytes, etc), and **r** the estimated response time.
 - Accuracy: it is a measure of the quality of the output result. It has different meanings depending on the required functionality. For instance, in classification problems, it is the statistical measure of how well a classifier, like a face detector, correctly identifies or excludes a condition. In optimization problems, it is usually measured as a ratio between the obteined output solution and the optimal known solution. In most cases, accuracy do not depends on execution context features, in contrast to response time. Therefore, an accuracy model over a component **C** is defined as a function **Ac(I)=a**, being  **I** a set of measured input parameter features **I=[I1,...Ii]**, and **a** the estimated accuracy.

Such models are known as **empirical performance models** (EPM), and are useful in a variety of practical contexts:
 - Algoritm selection and configuration: performance models can be used to dynamically select the best from a given set
of algorithm configurations, depending on the problem instance and hardware characteristics. 
 - Service selection and composition: when several Web services implements the same functionality, performance models are a good criteria for choosing the best candidate among them. Even at runtime, service providers can be switched if context and input parameter conditions change. 
 - Task scheduling in mobile grids: given a set of tasks to be assigned among a set of devices, performance models can obtain an accurate measure of the response time of each task over each device in order to minimize the makespan. 
 - Others.

## Concepts

- Component
- Operation (or Task)
- Input
- Output
- Execution context
- Metrics: unit of code that computes a feature value from some element.
    - Execution context metrics
        - Static context metrics
        - Dynamic context metrics
    - Input metrics
    - Component metrics
    - Operation metrics
- Test plan
- Results

## Workflow
- **Measure** (or test): obtain empirical data performing component operations.
- **Predict**: use the collected data for building and evaluating prediction models
- **Apply**: use the models in some practical context.

### Measure
### Predict
### Apply
