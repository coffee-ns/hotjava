# Hot Java

Introduction – Hot Java is an application that lets users submit photos and descriptions of their car to be funneled into a voting form. Users will be able to vote on cars and builds which will raise and lower their ranks among their peers. Users will be able to search for and view the top cars in specified categories such as make and model.

Submission will not be required to vote, but registration will be a necessary step to avoid duplicate votes/entries.

## Storyboard

![image](![image]https://user-images.githubusercontent.com/77906864/151719829-0c550a46-f6d4-4365-be51-e44a7eaa95b6.png)



## Requirements

1. As a user, I want to be able to submit my car for polling by filling out a simple form and submitting a photograph.

### Example

Given: A user needs to submit their 2001 Nissan Silvia S15 vehicle to be entered into the polling system 

When: The user inputs all of their vehicle's information and photo into the form 

Then: The user will recieve confirmation of their submission and voting may commence 


### Example

Given: A user wants to submit their 1989 Nissan Skyline GTR vehicle to be entered into the polling system 

When: The user inputs all of their vehicle's information and photo into the form 

Then: The user will recieve confirmation of their submission and voting may commence 

 
 ### Example

Given: A user wants to submit their 2005 Subaru WRX STI vehicle to be entered into the polling system without a photograph 

When: The user inputs all of their vehicle's information and photo into the form minus the photograph

Then: The user will recieve notification that their submission is incomplete and they must populate all required fields


2. As a user, I want to be able either upvote, or downvote vehicle I see on the polling portion of the application, causing the page to refresh and present additional options.

### Example

Given: A user downvotes a vehicle

When: The user clicks the down arrow icon under the vehicle profile

Then: The customer will then reviece a message regarding their vote and the tally above the vehicle will update

 
 ### Example

Given: A user upvotes a vehicle

When: The user clicks the up arrow icon under the vehicle profile

Then: The customer will then reviece a message regarding their vote and the tally above the vehicle will update

 
 ### Example

Given: A user skips a vehicle

When: The user clicks the skip button under the vehicle profile

Then: The page will then refresh presenting a new vehicle for polling

## JSON Schema

>{ 
>	“type” : “object”, 
>		“properties” : { 
>			“VehicleSubmissionID” : { 
>				“type” : “integer” 
>			}, 
>			“vehicleOwnerName” : {
>				“type” : “string”
>			}, 
>			“vehicleDescription” : {
>				“type” : “string”
>			}, 
>			“vehicleYear” : {
>				“type” : “string” 
>			}, 
>			“vehicleMake” : {
>				“type” : “string”
>			}, 
>			“vehicleModel” : {
>				“type” : “string”
>			}, 
>			“vehicleScore” : {
>				“type” : “integer”
>			}
>		}
>	} 

## Class Diagram

![image](ADD CLASS DIAGRAM)

## Class Descriptions

### com.hotjava.ui

Controller - Controller for the UI

VehicleLayout - Format for the Vehicle lookup and submission pages

LookupVehicle - Collects vehicle name to find objects with the given name.

CreateVehicleSubmission - Handles information given from the user to create a vehicle submission & entry.


### com.hotjava.dto

vehicle - Basic Object class to store information for vehicles.


### com.hotjava.service

IVehicleService, VehicleService, VehicleServiceStub - handles sending information from the UI to the database


### com.hotjava.dao

IVehicleDAO, VehicleDAO, VehicleDAOStub - handles data retrieval and storage for the application

## Team Members & Roles

**UI Specialist**: Mason Mickelini / Brandon Zachary

**Business Logic and Persistence Specialist**: Michael Mcquade

**Product Owner/Scrum Master/DevOps/GitHub Administrator**: Nathan Coffee

## Milestones

[Milestone 1](https://github.com/coffee-ns/hotjava/milestone/1)

[Milestone 2](https://github.com/coffee-ns/hotjava/milestone/2)

[Milestone 3](https://github.com/coffee-ns/hotjava/milestone/3)

## Standup

[We meet weekly at 6:00PM on Sundays](https://teams.microsoft.com/l/meetup-join/19%3ameeting_ZGM4NjIwYTYtYmQzYi00OWUzLWIxYmUtODFhZGMwZjMyYzNj%40thread.v2/0?context=%7b%22Tid%22%3a%22f5222e6c-5fc6-48eb-8f03-73db18203b63%22%2c%22Oid%22%3a%22f763efca-3726-4129-8023-2d5dda742031%22%7d)
