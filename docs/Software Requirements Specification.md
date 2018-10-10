# Requirements Document
 ### Table of Contents
 1. [Introduction](#1) <br>
	1.1. [Purpose](#1.1) <br> 
	1.2. [Business requirements](#1.2) <br>
		1.2.1. [Initial data](#1.2.1) <br>
		1.2.2. [Business opportunities](#1.2.2) <br>
	1.3. [Analogs](#analogs) <br>
2. [User Requirements](#2) <br>
	2.1. [Software Interfaces](#2.1) <br>
  	2.2. [User Interfaces](#2.2) <br>
  	2.3. [User Characteristics](#2.3) <br>
  		2.3.1. [User Classes](#2.3.1) <br>
  		2.3.2. [Target Audience](#2.3.2)<br>
  	2.4. [Assumptions and Dependencies](#2.4) <br>
3. [System Requirements](#3.) <br>
  	3.1. [Functional Requirements](#3.1) <br>
  		3.1.1. [Basic functionality](#3.1.1) <br>
  			3.1.1.1. [Set calories restriction](#3.1.1.1)<br>
  			3.1.1.2. [Pick date](#3.1.1.2)<br>
  			3.1.1.3. [Search nutrients information](#3.1.1.3)<br>
  			3.1.1.4. [Add food information](#3.1.1.4)<br>
  			3.1.1.5. [Create food information](#3.1.1.5)<br>
  		3.1.2. [Limitations and Exceptions](#3.1.2)<br>
  	3.2. [Non-Functional Requierements](#3.2) <br>
   		3.2.1. [Software Quality Attributes](#3.2.1) <br>
    		3.2.1.1. [Usability](#3.2.1.1) <br>
    	3.2.2. [External Interfaces](#3.2.2) <br>
    	3.2.3. [Constraints](#3.2.3) <br>
 ## 1\. Introduction <a name = "1"></a>
 ### 1.1\. Purpose <a name = "1.1"></a>
This document contains functional and non-functional requirements for the mobile application "LoseIt" for Android OS 4.4 and higher. This document is intended for a team that will implement and verify the correctness of the application.
 ### 1.2\. Business requirements<a name = "1.2"></a>
 #### 1.2.1\. Initial data <a name = "1.2.1"></a>
Study after study has confirmed the benefits of keeping track of the food you eat and the activity you do. It's simple - the more consistently you track your food intake, the more likely you are to lose weight. That's why every successful weight management program suggests that you keep a food diary and/or an activity log. But recording everything you eat without the right tools can be tedious at best, or simply impossible at worst.

LoseIt Application focusing on making sure you can log your meals as quickly and easily as possible. Because the easier we make it for you, the more likely you are to stay on track, and the more likely you are to succeed in your weight loss goals.

 #### 1.2.2\. Business opportunities <a name = "1.2.2"></a>
Many people want an application that allows to store health data in their mobiles, with minimal technical literacy. Such application will allow them to spend less time searching for necessary information about their nutrition. LoseIt Application will also helps fitness instructors to spend less time for collecting information about their wards. The designed user interface should allow the application to be used by people with minimal technical knowledge.
 ### 1.3\. Analogs<a name = "1.3"></a>
Unlike various analogs,  data about frequently used nutrition will store in mobiles. <br>
 - <b>MyFitnessPal by [Under Armour](https://www.myfitnesspal.com/) </b><br>
![MyFitnessPal](analogs/MyFitnessPal.png)

 	MyFitnessPal App is your personal assistant that helps you reach your goals in losing weight and healthy lifestyle. Application provide daily control of calories, fats, carbohydrates, proteins, vitamins etc. <br>
 	-  Professional software for athletes;
	- Required Internet connection.  
  - <b>Lifesum by [Lifesum](https://lifesum.com/) </b><br>
![Lifesum](analogs/Lifesum.png)

 	With Lifesum, tracking your healthy habits (and the not so healthy ones) becomes a breeze. It will help you pick the right food, and eat the right portion sizes, to reach your personal health goals.
	- Required authorization in the system;
	- Animated interface with many tips; 
 ## 2\. User Requirements <a name = "2"></a>
 ### 2.1\. Software Interfaces <a name="2.1"></a>
Application uses [nutritionix API](https://developer.nutritionix.com/), that provides the necessary nutritional information on products. User data about frequently used nutrients is stored in a SQLite database file.
 ### 2.2\. User Interfaces <a name="2.2"></a>
Graphical user interface is represented by mockup images.
 <p align = "center">
<img width = "282" height = "573" src = "https://github.com/VladislavLiudchyk/LoseIt/blob/master/docs/mockups/Main%20Page.png?raw=true">
</p>
 <p align = "center">
Figure 1. The application's main page screen<br>
</p>
 <p align = "center">
<img width = "282" height = "573" src="https://github.com/VladislavLiudchyk/LoseIt/blob/master/docs/mockups/Set%20Calories%20Page.png?raw=true">
</p>
 <p align = "center">
Figure 2. Set calories page screen<br>
</p>
 <p align = "center">
<img width = "282" height = "573" src="https://github.com/VladislavLiudchyk/LoseIt/blob/master/docs/mockups/Pick%20Data%20Page.png?raw=true">
</p>
 <p align = "center">
Figure 3. Pick date page screen<br>
</p>
 <p align = "center">
<img width = "282" height = "573" src="https://github.com/VladislavLiudchyk/LoseIt/blob/master/docs/mockups/Search%20Food%20Page.png?raw=true">
</p>
 <p align = "center">
Figure 4. Search food page screen<br>
</p>
 <p align = "center">
<img width = "282" height = "573" src="https://github.com/VladislavLiudchyk/LoseIt/blob/master/docs/mockups/Create%20Food%20Page.png?raw=true">
</p>
 <p align = "center">
Figure 5. Create food page screen<br>
</p>
 <p align = "center">
<img width = "282" height = "573" src="https://github.com/VladislavLiudchyk/LoseIt/blob/master/docs/mockups/Result%20Food%20Page.png?raw=true">
</p>
 <p align = "center">
Figure 6. Result food page screen<br>
</p>

 ### 2.3\. User Characteristics <a name="2.3"></a>
#### 2.3.1\. User Classes <a name="2.3.1"></a>
| User class | Description |
|:---|:---| 
| Anonymous users | Users who are not logged in LoseIt App. Have access to the all functionality of the application|
 #### 2.3.2\. Target Audience <a name="2.3.2"></a>
People of all age categories who want to get in shape or lead a healthy lifestyle.
### 2.4\. Assumptions and Dependencies <a name="2.4"></a>
1. The application will not load unique data without network connection;
2. LoseIt will not work without application ID and application key, provided by nutritionix API;
3. The application will work without network connection with frequently used nutrients and all other functionality.
 ## 3\. System Requirements <a name="3"></a>
### 3.1\. Functional Requirements <a name="3.1"></a>

 #### 3.1.1\. Basic functionality <a name="3.1.1"></a>

 ##### 3.1.1.1\. Set calories restriction <a name="3.1.1.1"></a>
<b>Description: </b> The user has the ability to set amount of calories to reach goals.

Function | Requirements
|:---|:---
Set calories restriction | The application should allow the user input correct amount of calories

 ##### 3.1.1.2\. Pick date <a name="3.1.1.2"></a>
<b>Description: </b> The user has the ability to choose day and view previous results or set new goals.

Function | Requirements
|:---|:---
Pick date | The application should allow the user to input correct date in format DD/MM/YYYY

 ##### 3.1.1.3\. Search nutrients information <a name="3.1.1.3"></a>
<b>Description: </b> The user has the ability to search nutrients information about food in the application.

Function | Requirements
|:---|:---
Search nutrients information | The application should allow the user to search info and make a request to nutritionix via API

 ##### 3.1.1.4\. Add food information <a name="3.1.1.4"></a>
<b>Description: </b> The user has the ability to add food for every meal in the application.

Function | Requirements
|:---|:---
Add food information | The application should allow the user to add food for every meal in the application

 ##### 3.1.1.5\. Create food information <a name="3.1.1.5"></a>
<b>Description: </b> The user has the ability to create basic food information.

Function | Requirements
|:---|:---
Create food information | The application should allow the user to create basic food information

 #### 3.1.2\. Limitations and Exceptions <a name="3.1.2"></a>
1. Application works without network connection with frequently used nutrients;
2. Application can't send requests without app ID and app key.
 ### 3.2\. Non-Functional Requierements <a name = "3.2"></a>
#### 3.2.1\. Software Quality Attributes <a name = "3.2.1"></a>
##### 3.2.1.1 Usability <a name = "3.2.1.1"></a>
1. All elements must be in bright colors;
2. All functional elements of the user interface have names describing the action that the element does.
 #### 3.2.2\. External Interfaces <a name = "3.2.2"></a>
Application screens should be convenient for use by users with poor eyesight:
 - font size >12 pt;
- functional elements are contrast to the screen background.
 #### 3.2.3\. Constraints<a name = "3.2.3"></a>
1. Application is created for Android OS 4.4+ system;<br>
2. User data about frequently used nutrients is stored in a SQLite database file.