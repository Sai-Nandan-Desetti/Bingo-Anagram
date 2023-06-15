# Bingo-Anagram

## Objectives:
* Create a connection oriented server socket that accepts and connects to any TCP client.
	* The server should provide at least three services to the connected client.
	* Client should be provided a menu of commands available as it is connected to the server.

## Guidelines:
* The code should be modularized appropriately based on object oriented programming concepts.
* This server should be multi-threaded (serving multiple clients simultaneously).
* Exception handling ; memory optimization ; packaging.

## Services offered: 
1. Play Bingo
2. Dictionary and Anagrams
                   
### Details:
To run the server, in the `server` folder, run (in Windows)
```
java main\Server.java                 
```						
					
To run the client, in the `client` folder, run
```
java main\Client.java <Host name> <port number>
```			 
Host name (System IPv4 address) and port number to be got from the person running the server side of the program.
	
### Example:
```
Host name:   10.0.3.160	
port number: 1712
```
				  
To shut down the server: `Ctrl+c`
