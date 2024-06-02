Simple API to interact with database: 

methods and endpoints:

-       GET http://{public IP of AWS database}:8080/users | return the total amount of users in database
-       DELETE http://{public IP of AWS database}:8080/users/{id} | delete user from database
-       GET http://{public IP of AWS database}:8080/users/{user_id}/messages | return the total amount user's messages
-       GET http://{public IP of AWS database}:8080/chat/{chat_id} | return the chat of specific id
-       POST http://{public IP of AWS database}:8080/chat/ | saves the chat into database

To connect your database simply change credentials in `src/main/resources/application.properties`