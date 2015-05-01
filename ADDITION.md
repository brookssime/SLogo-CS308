CS 308: SLogo Addition Analysis
===================

###Estimation
I estimated that it would take me 2 hours to complete the new feature. I predicted that I would have to make 2 new files and update 2 existing ones. The new files would be for the new commands. One of the existing files would be the existing forward command, and the other would be the model.

###Review
It took me about an hour to implement the new feature. I did not get it completely right on the first try. At first, I forgot to pass the correct class variables to the CommandNode class to set up the parameter checking. I also made a small mistake with the algorithm for the advanceTurtleFence method of the Forward class at first, but this bug was easy to find. Additionally, I forgot to update the resource bundle for the english commands at first.

I changed and added the same files that I thought I would. However, I also added a new exception, which is not really necessary (more of an error check for the programmer), and had to change the command name resource bundle so the program would recognize the new commands.

###Analysis
Although I happened to remember a lot about how I designed my SLogo project, I still made some mistakes while implementing the simple changes. I think this could have been avoided by having better documentation, because I basically did not document anything in SLogo. 

My overall program design was about as bad as I remembered it. I did not use interfaces in the design, so every command has access to the Model, which is way more than each command should have access to. I wrote about this in my SLogo analysis, however. This is probably the main issue with the project at the moment.

If I were not familiar with this code at all, it would have taken me a very long time to add the new feature. There is no documentation to explain how to add new commands, which is essential to figuring it out. Maybe I could have guessed by copying another command, and just modifying the guts of it, but understanding where commands fit in to the greater picture would have been quite the task. 
