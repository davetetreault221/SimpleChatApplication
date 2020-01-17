# Simple Chat Application 

## Description

A simple chat application which has the goal of helping to learn how sockets and threads can be used to interact with a server.


## Setup (Running on Mac Terminal)

\*\*Note\*\* : It is important to first 'Javac' the java files in the terminal to create the necessary files before running the following commands below:

Javac Command Example (in Terminal): 

```bash
javac ChatServer.java
javac Client.java
```

1) Starting the Server:

```bash
java ChatServer 8080
```
2) Starting the Client

```bash
java Client localhost 8080
```

*Both the Client and the Server must be run on seperate terminal windows\*

## Usage

In order to fully embrace the functionality of this program it is advised to open multiple terminals running the Client and seeing how multiple users can communicate with each other. 


## License
[MIT](https://choosealicense.com/licenses/mit/)
