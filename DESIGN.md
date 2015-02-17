#Slogo Design Document
##Introduction
*This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should be approximately 200-300 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).*

An integrated development environment (IDE) is a programming environment, typically consisting of a code editor, a compiler, and a graphical user interface (GUI) builder. This program is designed to create a user-friendly IDE that allows a user to control a “turtle”, which is a drawing object, as it drags a pen across the screen. In this program, the read-evaluate-print loop is designed to maintain the idea of open architecture, in that there will be room to add to its complexity as the program grows (e.g. stackable commands, added commands, etc.). The primary design goal of the project is to have commands programmed in the backend with the Model having no access to View. Instead, View will be an Observer that watches Movables. View will also call parsing methods in the model class and print results so that the user can interact with the commands displayed in the View.

##Overview
*This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific components you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing specifically on each class's behavior, not its state. It should also include a picture of how the components are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should be approximately 700-1000 words long and discuss specific classes, methods, and data structures, but not individual lines of code.*

###Front End
The front end aspect of this project is the View in the Model/View dynamic-- it contains classes that operate what the user sees and give the user the ability to customize certain aspects of the display. The different components of the View include classes that create a display area where the turtle and movement of the turtle can be displayed, three text panes (one of which allows the user to input commands, one of which displays previous commands before the scene is reset, and one that displays full designs, called “My Designs”, that allows the user to display a design that has already been programmed in), and a vertical button bar with buttons to choose a language, set color of pen and background, and select an image for the turtle. Each of these classes will have methods that will help create the functionality of each aspect in the View class itself. The purpose of dividing up the classes is so that the tasks that the View needs to handle will be split up between multiple classes and not clutter the View class. There has to be proper interaction between the Model and View, so that commands that the user inputs can be interpreted (and checked for errors) so that the movement will be displayed on the screen that the user sees. This is handled by subclassing Observer in the View and Observable in Movable. The View watches variables representing the pen status, icon status, coordinates, and orientation to reflect the changes in the interface.

###Back End
The Parser class takes single-line string inputs from Model and ultimately View, pulls out the commands one-by-one, and sends them to the Evaluator (with their parameters) in the correct order. This class is on its own because the details of the parsing are not needed by any other piece of the program. It is also convenient to call the Evaluator directly as the input is parsed, which is why the parser will also ensure that commands are completed in the correct order. 

The Parser matches a command in string form to the appropriate Command object and inserts the command as a node in the Evaluator, which runs the process method for the object. The Parser gets the number of arguments needed from the Command and parses expecting that number. In this sense, the Parser acts as a factory for Evaluator objects, constructing a root evaluation node and assigning it children as the parsing continues. When the command is fully parsed, the evaluate command is called on the root node, which recursively combines children based on the function of the contained Evaluator. The leaves of the tree are variable objects, which store values and define orderings for argument insertion. Each command has its own class, which means once a Command is written, it should be closed to further modification. If a new command is needed, a new subclass of Command should be written. 

When Commands are performed with respect to the turtle, the Turtle object must be updated. Turtle is made as a subclass of movable to keep the program extendable to the creation of other actors other than a turtle. The Movable class contains only basic get and set methods because all of the logic related to commands is kept in the Command hierarchy. This keeps the Movable object independent of all commands and vice versa.

##User Interface
*This section describes how the user will interact with your program (keep it simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.*

The user interface will be divided into 4 spaces - the main viewing window, the read-evaluate-print-loop (REPL), a command history, and stored command window.

![alt text](http://i.imgur.com/8sqp4Qi.jpg)

The main display window will be the largest screen. It will be used to display the turtle and all art that is draw with the turtle. The REPL will be right below, and will essentially be a text box. In it, the user will be able to input commands and run them with the Run button. Each set of commands that the user runs will be saved in command history, which will be scrollable, allowing the user to peruse old commands throughout the session. The stored command window will be used to save complex commands that the user creates in the REPL. In order to do this, the REPL will allow the user to create stackable commands in order to create complex pictures with the turtle.

Additionally, there will be six buttons in the UI. The run button, which runs commands from the REPL, “Browse Commands,” which creates a pop-up that lists all possible commands,  “Set Language,” which allows you to change the language of the integrated development environment from a drop down menu of languages, “Set Colors”, which will open up a pop-up that creates two more options to change the pen color and the color of the display window, and “Pick Turtle Image,” which will allow the user to upload an image to use as a “turtle.” Lastly, there will be a button to save the complex commands designed in the REPL which will be called “Save to My Designs.”

Initially, there will be only one error message designed to handle commands in the read-print-evaluate loop that are not registered in “Browse Commands.” It will print out a string message in the REPL and clear it of any text.

##Design Details 
*This section describes each component introduced in the Overview in detail (as well as any other sub-components that may be needed but are not significant to include in a high-level description of the program). It should describe how each component handles specific features given in the assignment specification, what resources it might use, how it collaborates with other components, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Finally, justify the decision to create each component with respect to the design's key goals, principles, and abstractions. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.*

###Back End
The Parser is an integral part of this project. In the read, evaluate, print model, the parser is the first step: read. The Parser is essentially a factory for evaluation trees, which it stores in a list of root Evaluators, which have zero or more child Evaluators and Variables. Given that parsing will have a lot of details which no other parts of the program need to know about, it is intuitive to place it in its own class. The Parser class will only have one public method, called parse, which will take the user’s input as a parameter. The parse method will then pull all commands from the string and send them to the Evaluator in the correct order and with the correct parameters. The parser will handle nested commands with the use of a tree or a stack. This functionality may be extracted into another class in the future depending on the details of the implementation.

The main purpose of the Evaluator is to collapse the tree of Commands and parameters stored as Evaluators and Variables to produce a single return. Evaluator has an evaluate method which recursively calls evaluates on its children until it reaches Evaluators with only Variable children. The Evaluator handles argument type exceptions.

The Command class is abstract to allow for the most extendability to new commands. A new command can be written by writing a new Command subclass and adding it to the Evaluator’s logic. Each command has a process method which takes a List of parameters and possibly a Movable. All commands return a double, which makes them all behave the same to outsiders. Commands handle invalid argument exceptions, which are arguments that are invalid for the performed operations.

Keeping the Movable class generic leaves it open to extension through subclasses. As of now it just keeps track of a turtle’s state, but methods could be added in a subclass in the future to expand the responsibilities and features of actors. The variables of the turtle are modified by commands chosen by the user and are observed by the Viewer.

###Front End
The View class acts as a manager for disjoint UI components. It holds a List of Observable Movables. The View updates the Display when changes occur within the list.

The Display class will be used to render the turtle and the pen. In it, there will be methods to change usage of the pen (show, size, etc.) and methods to initiate the turtle for the user to control.

The REPL class will be a textbox that takes in what the user types and sends it to the Parser.

The Command History class will take all the commands that are successfully evaluated and copy them to the sidebar. It will have methods to store text and be navigable so that the user can reuse commands in the REPL.

My Designs will hold clickable links to files saved from the REPL, and from there recreate the commands in the Display window. This will likely be the most complicated frontend class and will need methods to copy what is displayed in the REPL, save that text to a file that can also be read by the Parser, and create a visual representation of that file so that it can be chosen from the My Design sidebar.

Other important classes include a system messages class so that error messages can be created, a button maker class for the various buttons in the UI, and a colorchooser class so that different colors can be picked for the pen and background.

##API Example code
It should be clear from this code which objects are responsible for completing each part of the task, but you do not have to implement the called functions.
Show actual "sequence of code" that implements the following use case: 
The user types 'fd 50' in the command window, and sees the turtle move in the display window leaving a trail.
Note, clearly show the flow of calls to public methods needed to complete this example, indicating which class contains each method called. It is not necessary to understand exactly how parsing works in order to complete this example, just what the result of parsing the command will be.


When the user enters the command, the String “fd 50” is captured by event handlers. The string is passed to the Model class, which passes it to Parser. The Parser converts it to a tree with an Evaluator for the command Forward and a Variable storing 50. The evaluator rood calls Forward(50), which alters parameters of the active Turtle. These parameter changes are observed by the View class, which displays the movement.

myView.parseCommand()
myModel.parseCommand()
myParser.parse()
root.evaluate()
list.add(50)
cmd.function(turtle, list)

##Design Considerations 
This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that the group discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

We are still not completely sure how the Observer-Observable pattern works and will have to wait until implementation to be sure that it will work as intended. We are fairly sure that we’ve designed it correctly, but there may be unforeseen difficulties in our implementation.

##Team Responsibilities
This section describes the program components each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

Sean and Tom will be handling the back end, while Cosette and Brooks will handle the front end. On the back end, Tom will write the parser and the Movable structure, and Sean will handle observability and the evaluator. Tom and Sean will split up the command classes as necessary. 