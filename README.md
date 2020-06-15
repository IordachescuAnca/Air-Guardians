# Air-Guardians
  **Application designed and developed by _The Guardians_ team including: [Flavius Stan](https://github.com/StanFlavius),  [Stefan Zaharia](https://github.com/stefzah),  [Anca Iordachescu](https://github.com/IordachescuAnca).**

## Description of application:
Android application written in Java that monitor air quality in some locations in Romania.

Air pollution is now the biggest environmental risk for out health, having various malign health effects in early human life such as such as respiratory, cardiovascular, mental, and perinatal disorders. With the population in urban areas particularly exposed to high concentrations of pollutans that even if you can't see, they can still hurt you and produce damage to your health.

This is where technology comes in!

Air-Guardians application offers information about the level of pollutions and 5 pollutans carbone monixide, nitroge dioxide, ozone, inhalable particulate matter PM2.5 and PM10 that can be found in the city the customer choose. 

It is our belief that monitoring the air quality of the places protects people's life and health
and minimize exposure to outdoor air pollution as daily information about pollutants that can
be found in the air help people decide when to stay indoors.

## Trello

We used Trello in order to manage and organise our tasks during the development of the application. This [link](https://trello.com/b/UBPFRoEN/air-guardian-development) redirects to our backlog creation.

## User stories

User stories are short, simple descriptions of a feature told from the perspective of the person who desires the new capability, usually a user or customer of the system. 

Our team has come up with 10 user stories:

1. As an environmentalist, I want to see all polluted cities in order to help reduce air pollution there.
2. As an environmentalist I want to be informed about what trees I can plant in the polluted areas.
3. As a parent, I want to know the time of the day when air pollution is lowest so that I can go outside and play outdoor activities with my children.
4. As an athlete, I want to know when the ozone value overcomes its thresholds so that training outside in such enivronment can cause me  bronchoconstriction.
5. As a traveler, I want to see the air pollution of cities I often visit so that I can be sure if I need a mask. 
6. As a customer, I want to see the air quality index of all cities so that I can decide the least polluted city to move.
7. As a customer, I want to choose some locations on map so that I can see quickly information about air quality in those regions.
8. As a customer, I want to receive air quality based health recommendations so that I can organize my day for maximum productivity.
9. As a customer I want to be notified if in any of my favourites cities the pollution is too high.
10. As a customer I want to receive health recommendations based on the level of the pollution.

## UML Use Case Diagram

![alt text](https://github.com/IordachescuAnca/Air-Guardians/blob/master/uml/uml.jpg)

## Building tool

Our project was developed in IntellIJ IDEA and the building tool used is Gradle, which is an open-source build automation tool which is used to automate building, testing, deployment etc. "Build.grade" are scripts where one can automate the tasks.

## Bug reporting

We have encountered the following bugs:

1. The undo operation performed by the user to remove cities from favourites had no effect. Solution: Fixing the sql query that deletes a city from the user's list of favourites cities. To see the fixing, click [here](https://github.com/IordachescuAnca/Air-Guardians/commit/0cc49fa528b6f8ab0f4f3473bbfb84320e38330e).

2. In the Information Activity the navigation bar was not placed on the bottom of the activity.Solution: We tried to put it in a Linear Layout outside the Linear Layout that contains the other elements but it was covered, so we decided to increase the margin of the other elements. To see the fixing, click [here](https://github.com/IordachescuAnca/Air-Guardians/commit/4f6d63f3afcce84c8308b7f8c9b79b4cf0659f90).

## Memes: We do love them :laughing:
![Meme 2](https://github.com/IordachescuAnca/Air-Guardians/blob/master/Memes/Meme-2.jpg)
![Meme 1](https://github.com/IordachescuAnca/Air-Guardians/blob/master/Memes/Meme-1.jpg)

