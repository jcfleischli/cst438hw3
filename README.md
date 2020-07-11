# cst438hw3
CST438 Assignment 3 - Message Queue Programs

# Introduction

In assignments 1 and 2, you coded a Controller and Unit tests for an application to retrieve city information. In this assignment, you will extend that application allowing the user to book an “Mystery Surprise Weekend GetAway trip for two”  to that city.
Objectives in this assignment:

•	Create Spring configuration classes that create the message exchange and queue.

•	Modify the controller and service classes from homework 2 and add methods that will write a message to an exchange using the RabbitTemplate class provided by Spring.

•	Code a second microservice that processes the messages.  For now, this second service will just retrieve and print the message.


•	You also might find the “Hello World” tutorial on the RabbitMQ site helpful  https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html

# Requirements

•	Update the city web page to add a form to give user the option to select one of Luxury, Moderate or budget getaway vacation for 2 people to the city  

•	if the user fills in and submits the form, a information page will be shown 
