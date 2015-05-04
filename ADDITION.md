Sean Scott Addition
===================
####Estimation

It should not be difficult to implement this feature and will probably take less than one hour. I estimate five files will need to be updated: Model, View, Forward, Wrap, and Window. Model will be used to set a new property to hold a window wrapping boolean. A change listener will be put in View to track the value of the boolean. The Forward command will be changed to reflect different ways to move on the display. Wrap and Window will be new commands that set the boolean wrap property.

####Review

It took about thirty minutes to complete the feature. I did not get it right on the first try, since Forward could directly read the property value, so a change listener was not necessary in View; I figured this out within the first few minutes, so it was not a major hindrance. I didn't realize until later that I had to update the properties file in the resources package as well to allow commands to be recognized. Only five files were ultimately updated, with the same reasoning as described in the Estimation section.

####Analysis

The extensibility of the project was almost as good as I remembered, as only very small changes were needed to each of the relevant files. It is possible that this could have been made better if movement was handled in the View so Forward would not need access to the Model and could explicitly handle all display concerns. A completely unfamiliar user would probably be able to figure out how to make the changes I made by examining commands and the Model class, but it may be more difficult for a new user to figure out the changes to the properties file. 
