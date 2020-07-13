# spring-sse
This project is an example of how to use SSE (Server-Sent-Events) between server to server.
This can be used for example to communicate between two services, in an asynchrnous manner.

The project contains two sub projects:
1. spring-sse-client (8080 port by default) - which exposes a "web/test" endpoint. When accessed, it starts SSE communication with the other service and prints the output to the console. 
2. spring-sse-server (8081 port by default) - which exposes a "web/sse/test" endpoint. When accessed, it starts a thread that sends a string each 1 second.


