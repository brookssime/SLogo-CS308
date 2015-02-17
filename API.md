#SLOGO API

Parser

- parse(String input)

Evaluator

- evaluate(String command, List<Object> params)
- numberOfParameters(String command)

Command

- double process(Movable turtle, List<Object> params)

Movable

- get and set methods for: 
-- double x, y, heading
-- boolean penDown, showing
-- Node node

View

- preference getters/setters
	pen color, language, font size, etc.

Model

- addTurtles(int num, int[][] coords)
- clearTurtles()
- runCommand(String input)

###Exceptions

- ArgNumberException (Parser) - Parser needs to know how many arguments to expect from a Command, so this catches too little or too few arguments
- InvalidCommandException (Parser) - This will need to be caught in the parser, since it needs to know the proper number of arguments to expect from the command
- ArgTypeException (Evaluator) - This will need to be caught in the evaluator, since it will try to pass invalid arguments to the Command’s function method
- InvalidArgException (Command) - This catches arguments that are proper data types, but outside of the domain of the method. These are caught in the Command, which contains checks for its domain.
- DivByZeroException (Command) - Subclass of InvalidArgException. Declared explicitly due to the frequency of the error.

###Design Justification

>Model will have no access to View. Instead, View will be an Observer that watches Movables. View will also call parsing methods in the model class and print results. Exceptions will be thrown up the call chain from their sources to View to be displayed to the user in the command window.

>Future designers will find it easy to make subclasses of Movable, which is a state container for the cursor/pen that is watched by the View. Any additional or divergent functionality that a programmer wants to ascribe to Movables can be done with extension. 

>Commands are also easily added by extending Command, which makes its core functionality abstract to ensure consistency. Another programmer needs only to alter the Function method to change behavior and specify the number of arguments required.