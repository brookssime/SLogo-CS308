CS 308: SLogo Addition
===================

###Estimation:

* How long: I think it will take about 30 minutes to complete the new feature for front end of SLogo

* I think I will need to only update the file that displays the turtles (Display.java), because that is where turtles are added, 
and I can hopefully add a setOnAction method to open up the image chooser already created.


###Review

* It took about 40 minutes to complete the new feature.

* No, I did not get it completely right on the first try. The first try nothing happened, then the second time all the images of 
turtles changed if I tried to change one of them, then the third time I completed the feature with each image being customizable on click.
I spent the last 10 minutes fixing a bug that would change all of the images of turtles by clicking the Set Image button.

* I updated 3 files-- Display.java, ImageChooser.java, and View.java. Display is what I anticipated earlier, with setting lambdas for 
onClick actions. In ImageChooser, I had to create a few methods to save a specific image to set in display and add a boolean parameter to
tell whether the user wanted to change all of the turtle images (by pressing the button) or to only set 1 (by pressing the image). I only updated
the method call of the imagechooser in View.java.


###Analysis

* The design isn't especially pretty looking back, but it did have the capability to just add a few lines to very easily change images, mainly 
because of the use of a map with all of the turtle images connected to their respective turtles, and an individual method that adds an imageview 
when an turtle is added in the backend (connected to a listener).

* It could be improved by having a separate TurtleImage class that had a simple setNewImage method, but overall I think this design was simple to
implement the new feature. Documentation wise, I think I could have used more descriptive and consistent names for variables and methods to make it more
clear; I tried to do so with the new methods I wrote.

* It may have been more difficult if you were super unfamiliar with the code only to know that the addTurtle() method was in the display class.
Once that is cleared up, I think it is very straightforward.