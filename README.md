# LarkLink
## Introduction
LarkLink is a chat system that allows you to host a room or join existing rooms to chat with other users.
Once inside the room, you can send and receive normal messages or if you'd like to annoy your friends you can send a _lark_!
## Overview
### Main
Running LarkLink is as simple as navigating to our `Main` class under `src/main/java` and running the `main` method.
It allows you to initialize and start LarkLink. To chat, run two instances, with one hosting the room and the other one joining that room.
### Signup and Login
First you'll be prompted to either create an account or sign in to an existing one.<br>
![](images/Screenshots/main.png)<br>
Here, you can create an account or login by entering username and password and click **Submit** button. 
LarkLink allows alphanumeric characters for username and password. Username must be at least 3 characters long and
must not overlap with existing usernames. Password must be at least 8 characters long. 
If you try to set username and password shorter than each minimum length, you will see an error message.
If you enter valid username and password, you're all set! You'll be allowed to either Join or Host a chat room.
### Host a Room
![](images/Screenshots/host_or_join.png)<br>
To host a room, you need to enter room name with alphanumeric characters and click **Host** button. 
Room name must be at least 5 characters long and if you try to set the room name shorter than the minimum length, you will see an error message. 
In addition, you are allowed to host only one room. 
### Join a Room
![](images/Screenshots/join.png)<br>
When you click **Join** button on host or join screen, you'll be prompted to enter the name of room that you try to join. 
If you enter the room name which does not exist, an error message will be shown.
### Send Messages (and _lark_)
![](images/Screenshots/room.png)<br>
Here you can send a message and get messages from other users. If you try to send an empty message, you will get an error message.
(The implementation of sending lark is still in progress.)

### Leave a Room
If you click **Leave Room** on chat room screen, you can leave the room and you'll be prompted to host or join screen again.
## Clean Architecture
Separation of Concerns: 
The interactor is part of the application core, and it is responsible for handling the business logic related to corresponding use case. 
It communicates with the outer layers (controller, presenter and database) through interfaces (InputBoundary, OutputBoundary, and DBGateway), 
ensuring a clear separation of concerns between different layers of the application.
The Interactor does not have any direct dependencies on specific frameworks or libraries. 
Its dependencies are abstracted through interfaces, and the actual implementations are provided externally (injected) during runtime. 
This design ensures that the core business logic remains agnostic of the technologies used in the outer layers.
In addition, inputs from users and data from DB are encapsulated as input models and DB models to decouple the layers. 
## Solid Principle
Single Responsibility Principle (SRP): All classes and interfaces appears to have a single responsibility, 
which is to handle the interaction between layers for feature related operations. 

Open/Closed Principle (OCP): The Interactor class seems to be open for extension, 
as it is designed to interact with abstractions (InputBoundary and OutputBoundary) rather than concrete implementations. 
This allows for potential extensions or modifications to the behavior of the interactor without modifying its existing code.

Liskov Substitution Principle (LSP): Since the Interactor implements the InputBoundary interface, 
it can be used interchangeably with other implementations of that interface. 
Any class that expects a InputBoundary can work correctly with the Interactor.

Dependency Inversion Principle (DIP): The Interactor depends on abstractions (MessageInputBoundary and MessageOutputBoundary) 
rather than concrete implementations. This inversion of dependencies allows for easier swapping of different implementations 
without affecting the Message Interactor class.

## Design Patterns
TODO - Mention View Builder Pattern and ??? 

## Test Coverage
We intended to test the controller, interactor and presenter for each feature and test for the database (room db and user db).

## Java Doc
TODO

## Packaging
Entity: `src/main/java/entities`
Use case: `src/main/java/use_case`
Input and data model: `src/main/java/models`
Gateway and DBAccess: `src/main/java/database`
View: `src/main/java/ui`

