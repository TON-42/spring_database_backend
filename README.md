Simple API to interact with database: 

methods and endpoints:
    

-       GET http://ec2-13-60-87-183.eu-north-1.compute.amazonaws.com:8080/users | return the total amount of users in database
-       DELETE http://ec2-13-60-87-183.eu-north-1.compute.amazonaws.com:8080/users/{id} | delete user from database
-       GET http://ec2-13-60-87-183.eu-north-1.compute.amazonaws.com:8080users/{user_id}/messages | return the total amount user's messages
-       GET http://ec2-13-60-87-183.eu-north-1.compute.amazonaws.com:8080chat/{chat_id} | return the chat of specific id
-       POST http://ec2-13-60-87-183.eu-north-1.compute.amazonaws.com:8080chat/ | saves the chat into database

