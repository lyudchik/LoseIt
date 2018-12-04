# Test plan
 ## Content
1 [Introduction](#1)
2 [Test Items](#2)
3 [Risk Issues](#3)
4 [Features to be tested](#4)
5 [Test Approach](#5)
6 [Results](#6)
7 [Conclusion](#7)
  
# <a name = "1"> 1. Introduction </a>
This document represents the Test Plan developed for the mobile application LoseIt.
The main aim of testing – verification of the functionality and suitability of the application.
 # <a name = "2">2. Test Items </a>
The application LoseIt is created for simplify everyday life of persons who keep track of nutrition: store nutrition data in mobile phone. It uses Nutritionix database for searching most recent meal information via the Internet and local database for giving all functionality without any network connections. 
 #### Quality attributes (ISO 25010):
<br>1. Functional Suitability</br>
 - Functional completeness;
- Functional correctness;
- Functional appropriateness.

 <br>2. Usability </br>
 - Operability. 
- User error protection. 
- User interface aesthetics.
- Accessibility.</br>
 # <a name = "3">3. Risk Issues </a></br>
- Storage of mobile phone is almost full (not enough space to store application data);
- Unsupported version of Android system.
 # <a name = "4">4. Features to be tested </a>
 During testing process planned to check correctness of implementation followed main functionality:
 - <b>Pick date </b></br>
This use case should be tested on:
	1. Pick past date and show user info
	2. Pick future date and show user info
- <b>Set calorie restriction </b></br>
This use case should be tested on:
	1. Set positive amout of calories
	2. Set negative amout of calories
- <b>Create meal information </b></br>
	This use case should be tested on:
	1. Create meal information manually
	2. Create meal information using search
- <b>Add meal information </b></br>
	This use case should be tested on:
	1. Add new meal to all food intakes
	2. Add new meal with empty description
- <b>Edit meal information </b></br>
	This use case should be tested on:
	1. Edit meal information
	2. Save meal with empty meal description
-	<b>Delete meal information </b></br>
	This use case should be tested on:
	1. Accept deleting option at the Alert dialog
	2. Decline deleting option at the Alert dialog
-	<b>Search meal information </b></br>
	This use case should be tested on:
	1. Search for information via the Internet
	2. Search for information in the local database

 It is also important to check correctness of non- functional requirements:
 
 - Usability:
	All functional elements of the user interface have names describing the action that the element does.
- External interfaces:
	Application screens should be convenient for use by users with poor eyesight. Font size >12pt, functional elements should be contrast to the screen background.
	
 # <a name = "5">5. Test Approach </a>
This application will be tested manually.
 # <a name = "6">6. Pass / Fail Criteria </a>
Results of testing are represented in [this document](Test%20results.md)
# <a name = "7">7. Conclusion </a>