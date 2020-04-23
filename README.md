# Java Chat Utility
* This program implements a chat utility system using java sockets and threads. 
* It creates a server in the ServerMain class and allows a client to connect to it. 
* The client is a separate class, ClientMain. ClientMain connects to the server through a specified IP (locahost), and then the user can communicate to the server from the client and vise versa. 
* If the client exits, the server keeps searching for another client.
* This program only allows 1 client connection. 