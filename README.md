# NutriDiet

## CalorieTracker and Recommender


NutriDiet is a *CalorieTracking* app which will act as a diary for the food
that you eat and the fluids you consume. In addition to this functionality, 
it is also a *recommender* in the sense that it will tell you what to work on 
if you ask it based on the sigmoid function which essentially tells you how close you are to your goal. This function quantifies priority by standardising weight, 
calories, millilitres of fluid consumed etc into one form.

The target audience for this application is the general public which is health conscious as well as people who are trying to make a difference 
but don't know where they might be going wrong or have lost their path to a physically fit lifestyle. The *CalorieTracker* and *Recommender* 
ensure that you stay fit as you are able to track your eating habits, gym routine in general, your lifestyle.

I chose to do this project because often me and my gym partner weren't able to see results. We would work out regularly, however, 
didn't realise when we would be consuming unnecessary calories. Logging them into an application
where they will stay stored really helps you track your progress. That was my inspiration for this project.
 
## User Stories

- As a user, I want to be able to add the food and calories I consumed
- As a user, I want to be able to add the fluids I consumed and its quantity
- As a user, I want to be able to view the list of what I consumed (food and drinks)
- As a user, I want to get prioritised recommendations on what I should do
- As a user, I want to be able to get my BMI. 
- As a user, I want to be able to save my data into a JSON file
- As a user, I want to be able to read the data that has been saved in a JSON file and should be able to choose the file.

##Phase 4: Task 2 
I have chosen the following option 
- Test and design a class that is robust. You must have at least one method that throws a checked 
exception. You must have one test for the case where
 the exception is expected and another where the exception is not expected.
 
 My exception class is InvalidInputException as to ensure robustness,
- I had to ensure that user enters the appropriate data type so as 
to prevent NumberFormatException which is the unchecked exception.
- I had to ensure that the user also enters semantically relevant data which
 I checked with if conditions such as taking the value of gym rigour, height and weight
 
The classes that are robust are listed below and can be found in the model package:
- Food
- Fluids
- Goals
- Attributes
- Activities

##Phase 4 : Task 3
I had no coupling issues as such however, while creating a popup error message in MainController, I realised
that the new pop up window wasn't the responsibility of maincontroller, hence, created a separate class for it called PopUpController.
Unfortunately, this is combined with Task 2 as I wasn't aware that separate commits were required

##Instructions to grader 
- Sometimes buttons require double click, but not always
- Each button is named and its function is pretty self explanatory based on the button name such as view button views, add button adds etc
- The images (visual component) can be found when you click on any one of the add buttons (food,fluid,activity). These images were found online and are not mine.
 They were just used for the purpose of this project
 