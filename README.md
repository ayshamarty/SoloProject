# Personal yoga application

In fulfilment of the solo project assignment due Monday week 7 at QA consulting.

## Index
[Brief](#brief)
   * [Solution](#solution)
   
[Architecture](#architecture)
   * [Entity Relationship Diagrams](#erd)
   * [Multi Tier Architechture Diagram](#mla)
	
[Testing](#testing)
   * [Report](#report)

     
[Deployment](#depl)
   * [Technologies Used](#tech)
     
[Front End Design](#FE)

[Improvements for the Future](#improve)

[Authors](#auth)

[Acknowledgements](#ack)

<a name="brief"></a>
## The Brief

To create an OOP-based application with utilisation of supporting tools, methodologies and technologies that encapsulate all core modules covered during training. The application must manipulate two tables with full CRUD functionality.

<a name="solution"></a>
### Solution

I decided to create a personal yoga application that would allow the user to create poses and routines, as well as add and remove poses from each routine.

The many to many relationship between poses and routines is working, where poses can be added and removed from routines.

<a name="architecture"></a>
## Architecture
<a name="erd"></a>
### Entity Relationship Diagrams
#### Initial plan
![Initial ERD](/Documentation/ERD_Initial_Plan.jpg)

The initial plan for the ERD consisted of a lot more tables and entities than were produced in the final application. The tables are coloured based on how I had hoped to prioritise the features of the application, and therefore the order in which I would tackle them. Given the time constraints, I only managed to deliver two tables plus a join, as depicted below

#### Delivered solution
![Final ERD](/Documentation/ERD_Final.jpg)

As shown in this ERD, I ended up changing the focus of the initial tables. After building the first entity (poses), I concluded it made more sense to continue making a routine table first, encompassing a many-to-many relationship between the two. My reasoning was that it would make for a more interesting user experience to create routines from the poses, rather than arbitrarily add health benefits to each pose.

<a name="mla"></a>
### Multi Tier Architecture Diagram
Please click on the diagram for a high resolution version:
![MTA](/Documentation/MTA.jpg)

This is a very high-level architecture diagram to demonstrate the architecture of the application. It does not include getters and setters, test classes, or constants classes. 


<a name="testing"></a>
## Testing

JUnit, Mockito and Selenium tests have been used for automated testing, and SonarLint/SonarQube for static reporting and refactoring.

<a name="report"></a>
### Report

[Link to Final Surefire Report](/Documentation/Surefire_Report.pdf)

Test coverage for the backend is at 84%, there are as of yet no working Selenium tests but hope to get these running soon.
The SonarQube static report shows 9 code smells remaining, 0 bugs, 0 duplications and 0 vulnerabilities.

<a name="depl"></a>
## Deployment

The build, test and deployment process was automated using Jenkins, with a webhook to GitHub which was triggered with every push event

This application can be deployed both locally and externally through a virtual machine. The constants.js file has commented out options to switch from an external to local IP address.

![Deployment Pipeline](/Documentation/CI_pipeline.jpg)
<a name="tech"></a>
### Technologies Used

* H2 Database Engine - Database
* Java - Logic
* Wildfly - Deployment
* Jenkins - CI Server
* Maven - Dependency Management
* Jacoco, EclEmma, Surefire - Test Reporting
* SonarQube - Static Testing
* [Git](https://github.com/ayshamarty/SoloProject.git) - VCS
* [Trello](https://trello.com/qasoloproject) - Project Tracking
* GCP - Live Environment

<a name="FE"></a>
## Front End Design
### Wireframes
Poses
![Poses Wireframe](/Documentation/Poses_Wireframe.png)
Routines
![Routines Wireframe](/Documentation/Routines_Wireframe.png)
### Final Appearance

<a name="improve"></a>
## Improvements for the Future

Currently, IDs are required to update poses and routines, and to add or remove poses from routines. In my next sprint, I would like to create buttons in the front end which allow this functionality without the need for IDs, which would allow the object IDs to be hidden from the user.

In later sprints, I would also like create a health-benefit entity which would have a many to many relationship with poses, so that users can create routines based on their focus for their practice. After this, I would add the capability to create different user accounts. At this point, I would remove the functionality for the user to add and remove poses themselves in the front end. These would instead be hard coded into the database, which the user could manipulate only for adding and removing them from their own routines.

<a name="auth"></a>
## Authors

Aysha Marty

<a name="ack"></a>
## Acknowledgements

* QA consulting and our fantastic instructors
* The rest of our wonderful cohort on the programme

