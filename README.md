# Java Chat Utility
* This program implements a chat utility system using java sockets and threads. 
* It creates a server in the ServerMain class and allows a client to connect to it. 
* The client is a separate class, ClientMain. ClientMain connects to the server through a specified IP (locahost), and then the user can communicate to the server from the client and vise versa. 
* If the client exits, the server keeps searching for another client.
* This program only allows 1 client connection. 
## Screenshots
### Start up the chat server
![Alt text](screenshots/sc1.png?raw=true "sc1")
### Start up the chat client 
![Alt text](screenshots/sc2.png?raw=true "sc2")
### Connect to the appropriate server IP through client
![Alt text](screenshots/sc3.png?raw=true "sc3")
### Server logs the client connection
![Alt text](screenshots/sc4.png?raw=true "sc4")
### Messages can now be sent back and forth
![Alt text](screenshots/sc5.png?raw=true "sc5")
![Alt text](screenshots/sc6.png?raw=true "sc6")
### Client can ctrl+c to exit client when they are finished chatting
![Alt text](screenshots/sc7.png?raw=true "sc7")
### Server logs when a client exits, must press enter to continue searching for another connection
![Alt text](screenshots/sc8.png?raw=true "sc8")
### When server is done searching for connections, can press ctrl+c to exit
![Alt text](screenshots/sc9.png?raw=true "sc9")