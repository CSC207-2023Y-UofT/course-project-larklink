# LarkLink
## Introduction
LarkLink is a desktop chat app that allows you to host a room or join existing rooms to chat with other users.
Once inside the room, you can send and receive normal messages or if you'd like to annoy your friends you can send a _lark_!

## Overview
### Main
Running LarkLink is as simple as navigating to our `Main` class under `src/main/java` and running the `main` method.
It allows you to initialize and start LarkLink. To chat, run two instances, with one hosting the room and the other one joining that room.
### Signup and Login
First you'll be prompted to either create an account or sign in to an existing one.<br>
<img src="images/signup_or_login.png" width="500"/>

Clicking the "Log in" button triggers the following 
prompt

<img src="images/login.png" width="500"/>

where the user can enter in existing credentials. Clicking the 
"Sign up" button displays the following.

<img src="images/signup.png" width="500"/>

Here, you can create an account by entering username and password and click `Sign up` button. 
LarkLink allows alphanumeric characters for username and password. Username must be at least 3 characters long and
must not overlap with existing usernames. Password must be at least 8 characters long. 
If you try to set username and password shorter than each minimum length, you will see an error message.
If you enter valid username and password, you're all set! You'll be allowed to either Join or Host a chat room.

### Join or Host a Room
<img src="images/join_or_host.png" width="500"/>

Here you'll see a list of rooms to join and you'll also be allowed to host a room. If you want to host a room, you need to enter room name with alphanumeric characters and click `Host` button. Room name must be at least 5 characters long and if you try to set the room name shorter than the minimum length, you will see an error message. In addition, you are allowed to host only one room. 

### Send Messages (and _lark_)
<img src="images/room.png" width="500"/>

Here you can send a message by typing a message in the blank space and pressing `Send Message`. 
If you try to send an empty message, you will get an error message. To send a lark just type and send `\lark` and everyone in the room will hear our lark sound !

### Leave a Room
If you click `Leave Room` on chat room screen, you can leave the room, and you'll be prompted to host or join screen again.

## Clean Architecture
Separation of Concerns: 
The interactor is part of the application core, and it is responsible for handling the business logic related to corresponding use case. 
It communicates with the outer layers (controller, presenter and database_and_drivers) through interfaces (InputBoundary, OutputBoundary, and DBGateway), 
ensuring a clear separation of concerns between different layers of the application.
The Interactor does not have any direct dependencies on specific frameworks or libraries. 
Its dependencies are abstracted through interfaces, and the actual implementations are provided externally (injected) during runtime. 
This design ensures that the core business logic remains agnostic of the technologies used in the outer layers.
In addition, inputs from users and data from DB are encapsulated as input models and DB models to decouple the layers. 

## SOLID & Design Patterns
**Single Responsibility Principle (SRP)**: All classes and interfaces are responsible to a single responsibility, 
which is to handle the interaction between layers for feature related operations. 

**Open/Closed Principle (OCP)**: The Interactor class seems to be open for extension, 
as it is designed to interact with abstractions (InputBoundary and OutputBoundary) rather than concrete implementations. 
This allows for potential extensions or modifications to the behavior of the interactor without modifying its existing code.

**Interface Segregation Principle (ISP)**: Since we aimed to break down the classes into small pieces according to SRP, all interfaces are 
specific to each class which each interface is implemented. We also implemented a different DBGateway for each use cases requirements so that to obey ISP. 

**Liskov Substitution Principle (LSP)**: All subclasses that inherit superclass `View` are overriding the abstract method `prepareGUI`
and all classes that implement interfaces implemented all methods in corresponding interfaces appropriately. Therefore, all these classes
can be used interchangeably with `View` or each corresponding interface without unexpected error.

**Dependency Inversion Principle (DIP)**: By using interfaces and data transfer object (which is called models here), dependencies between 
layers could be inverted and therefore, high level classes do not depend on lower level classes. 
For example, `UserSignupInteractor` receives data from users through `UserModel` object,
and `UserSignupInputBoundary` interacts with database_and_drivers through `UserDBGateway`. 
Hence, `UserSignupInteractor` does not depend on outer layers such as controller and database_and_drivers.
This inversion of dependencies allows for easier interacting between layers without affecting core business logic in high level.

**Extra Design Patterns:** We used the `viewable` interface to remove each presenter's dependency on different views.
We also use a singleton style design pattern for the `Room` and `User` class instead of passing information down and up through the views.
This works because only one user can be logged in at once and each user can only be in one room at a time.
We also use dependency injection for virtually everything - notably by injecting a `DBAccess` into every `interactor`.

## Test Coverage
We aimed for near perfect coverage across the board and manage to achieve 95% class coverage and 83% line coverage. Many of the 
view tests are commented out because it was not possible to make them headless. Any tests that intentionally generate an error 
(e.g. invalid password) will cause the creation of a pop-up which violates the headless requirement. We could not find a way to circumvent this because these pop-ups are integral to the functions being tested.

## Packaging
Originally we had packaged this by use case, but now we've packaged by level and included sub packages for use case because 
we found there was some code that many use cases used (like `RoomDBModel`).
