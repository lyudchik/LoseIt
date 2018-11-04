# System design

#### [Glossary](Glossary.md)

1. [Use Case Diagram](#1)<br>
1.1. [Actors](#1.1)<br>
1.2. [Use Cases](#1.2)<br>
1.2.1. [Set calories restriction](#1.2.1)<br>
1.2.2. [Pick date](#1.2.2)<br>
1.2.3. [Search nutrients information](#1.2.3)<br>
1.2.4. [Add meal information](#1.2.4)<br>
1.2.5. [Create meal information](#1.2.5)<br>
1.2.6. [Delete meal information](#1.2.6)<br>
2. [Activity Diagram](#2)<br>
3. [Sequence Diagram](#3)<br>
4. [State Diagram](#4)<br>
5. [Class Diagram](#5)<br>
6. [Component Diagram](#6)<br>
7. [Deployment Diagram](#7)<br>

## 1\. Use case diagram <a name = "1"></a>
Use case diagram is represented [here](Use%20Case/Use%20Case.png)
### 1.1\. Actors <a name = "1.1"> </a>

| Actor | Description|
|:--|:--|
| User | Person who uses application

### 1.2\. Use cases <a name = "1.2"> </a>
#### 1.2.1\. Set calories restriction <a name = "1.2.1"> </a>
**Description:** Use case "Set calories restriction" allows user to set daily calorie goal<br>
**Flow of events:**

**Main thread:**
1. User clicks "Settings" button;
2. User chooses "Input area";
3. User sets numeric value;
4. Application is waiting for user input. If user clicks "OK" button, alternative thread A1 will start execution. If user clicks "OK" button, alternative thread A2 will start execution.
5. Close "Input dialog" and return to main page.
6. Use case ends.

**Alternative thread A1:**
1. Save user's calorie restriction data;
2. Return to paragraph 5 of main thread.

**Alternative thread A2:**
1. Delete user's input data;
2. Return to paragraph 5 of main thread. 
#### 1.2.2\. Pick date <a name = "1.2.2"> </a>
**Description:** Use case "Pick date" allows user to choose day from the calendar and works with information about nutrients.<br>
**Flow of events:**

**Main thread:**
1. User clicks "Date label" at the main page;
2. Application opens calendar;
3. User picks date. If user clicks "OK" button, alternative thread A3 will start execution. If user clicks "Back" button, alternative thread A4 will start execution.
4. Close calendar and return to main page;
5. Use case ends.

**Alternative thread A3:**
1. Save user's date choice and provide nutrients information;
2. Return to paragraph 4 of main thread.

**Alternative thread A4:**
1. Delete user's date choice and set old date;
2. Return to paragraph 4 of main thread. 

#### 1.2.3\. Search nutrients information <a name = "1.2.3"> </a>
**Description:** Use case "Search nutrients information" allows user to search meal from nutrionix database or local database and works with information about nutrients.<br>
**Flow of events:**

**Main thread:**
1. User clicks "Add food" label at the main page;
2. Application opens search page;
3. User clicks search label and types meal name;
4. If user clicks "Enter" button, alternative thread A5 will start execution. If user clicks "Back" button, then go to paragraph 5.
5. Return to main page;
6. Use case ends.

**Alternative thread A5:**
1. Search data uses Nutritionix API via Internet;
2.  If there's no connection or connection timed out, alternative thread A6 will start execution;
3. Return to paragraph 6 of main thread.

**Alternative thread A6:**
1. Show alert dialog "No Internet connection";
2. Search data in local database;
3. Show matches at search page;
4. Return to paragraph 5 of main thread.
#### 1.2.4\.Add meal information <a name = "1.2.4"> </a>
**Description:** Use case "Add meal information" allows user to add meal and edit data about nutrients.<br>
**Flow of events:**

**Main thread:**
1. If user clicks "Meal" label in any food intake area at the main page, alternative thread A7 will start execution. If user clicks "Add food" label and after that clicks "Search" label then alternative thread A8 will start execution;
2. Add data to food intake;
3. Return to main page;
4. Use case ends.

**Alternative thread A7:**
1. User edit meal information;
2. If user doesn't input data to any required input area and clicks "Apply" button, alternative thread A9 will start execution. If user clicks "Back" button, alternative thread A10 will start execution.
3. Return to paragraph 2 of main thread.

**Alternative thread A8:**
1. Application search data using "Search food information"
2. Return to paragraph 2 of main thread.

**Alternative thread A9:**
1. Application shows alert dialog;
2. Return to paragraph 1 of alternative thread A7;
 
**Alternative thread A10:**
1. Clear all user input data;
2. Return to paragraph 3 of main thread. 
#### 1.2.5\. Create meal information <a name = "1.2.5"> </a>
**Description:** Use case "Create meal information" allows user to create meal information manually.<br>
**Flow of events:**

**Main thread:**
1. User clicks "Add food" label at the main page;
2. Application opens search page;
3. User clicks "Create Food" button, then alternative thread A11 will start execution;
4. Return to main page;
5. Use case ends.

**Alternative thread A11:**
1. User create meal information manually;
2. If user doesn't input data to any required input area and clicks "Apply" button, alternative thread A12 will start execution. If user clicks "Back" button, alternative thread A13 will start execution.
3. Return to paragraph 4 of main thread.

**Alternative thread A12:**
1. Application shows alert dialog;
2. Return to paragraph 1 of alternative thread A11.

**Alternative thread A13:**
1. Clear all user input data;
2. Return to paragraph 4 of main thread. 
#### 1.2.6\. Delete meal information <a name = "1.2.6"> </a>
**Description:** Use case "Delete meal information" allows user to delete meal information.<br>
**Precondition:** At least one dish at a given food intake exists.<br>
**Flow of events:**

**Main thread:**
1. User clicks "Meal" label in any food intake area at the main page;
2. Application opens "Edit food" page;
3. If user clicks "Delete Food" button, then alternative thread A14 will start execution.  If user clicks "Back" button, then go to paragraph 4;
4. Return to main page;
5. Use case ends.

**Alternative thread A14:**
1. Delete meal information;
2. Return to paragraph 4 of main thread.

##  2\. Activity Diagram <a name = "2"></a>
Activity Diagram is represented [here](Activity%20diagram/Activity.md)

## 3\. Sequence diagram <a name = "3"></a>
Sequence diagram is represented [here](Sequence%20diagram/Sequence.md)