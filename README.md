# Calorie and fitness tracker

## What will the application do? 

My goal is to make a calorie tracker that provides the user with a visualization of the progress that they are making towards their goal.
By taking in and recording the user's calorie intake and physical activity, the application will calculate the calorie deficit/surplus that the user achieved for that day.
By setting a goal(desired weight) the application will be able to calculate the progress made and visualize it. 

## Who will use it? 

The target audience for this application would be people who already track their calories, but want a more streamlined way of recording their information. 
This application would require users who are able to accurately track their calories, and are willing to update their information regularly. 
Aside from that, this application would be useful for athletic people, or people who just want to lose/gain weight.

## Why is this project of interest to me? 

I have always enjoyed games that ae based off leveling up the players stats.
There are times when the player is uninterested, yet they continue to play in order to reach the next level.
With this app, I hope to recreate that motivation, but with their physical body instead of a fictional character. 
I believe that if people viewed exercise more like a game, they would be more inclined to commit to it for long enough to see real benefit


User stories:
- As a user, I want to be able to input my name, height, weight, and gender
- As a user, I want to create a new record of my physical activity and add it to the list of my physical activity on previous days
- As a user, I want to view the list of physical activity done 
- As a user, I want to create a new record of my calorie intake and add it to the list of my calorie intake on previous days
- As a user, I want to view the list of my calorie intake
- As a user, when I select the quit option from the application menu, I want to be reminded to save my to-do list to file and have the option to do so or not
- As a user, when I start the application, I want to be given the option to load my to-do list from file
- As a user, I want to view a progress bar showing me how close I am to reaching my goal(not implemented for phase 1)


*Note: Used provided json example as a template for my own code*

## Phase 4: Task 2
**A sample of events may look like the following:**

Sat Apr 06 01:26:00 PDT 2024  
Name set to Sanuda  
Sat Apr 06 01:26:00 PDT 2024  
Height set to 160 cm  
Sat Apr 06 01:26:00 PDT 2024  
Weight set to 160.0 pounds  
Sat Apr 06 01:26:09 PDT 2024  
1000 Calories added on day 1  
Sat Apr 06 01:26:10 PDT 2024  
10.0 Km added on day 1  
Sat Apr 06 01:26:19 PDT 2024  
1000 Calories added on day 2  
Sat Apr 06 01:26:20 PDT 2024  
10.0 Km added on day 2  
Sat Apr 06 01:26:21 PDT 2024  
1000 Calories added on day 2  
Sat Apr 06 01:26:22 PDT 2024  
10.0 Km added on day 2  
Sat Apr 06 01:26:25 PDT 2024  
Viewing information from day 1  
Sat Apr 06 01:26:25 PDT 2024  
Viewing information from day 1  
Sat Apr 06 01:26:26 PDT 2024  
Viewing information from day 2  
Sat Apr 06 01:26:26 PDT 2024  
Viewing information from day 2  
Sat Apr 06 01:26:28 PDT 2024  
Average calories and activity calculated from day 1 to day 1  
Sat Apr 06 01:26:29 PDT 2024  
Average calories and activity calculated from day 1 to day 2  
Sat Apr 06 01:26:29 PDT 2024  
Average calories and activity calculated from day 1 to day 2  


## Phase 4: Task 3

One way I could refactor my code is by making the User class a singleton class. 
In my GUI, I was forced to pass the user object as a parameter for every consecutive frame or panel. 
By using the singleton pattern, I could simply call a getInstance() method in User for each different class. 
Additionally, the listPop class in my GUI fails the single responsibility principle. 
If I were to refactor this code, I would ensure that each panel has a separate class instead of keeping them together.

