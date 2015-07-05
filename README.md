# Challenge to connect with AppDirect API

This is a sample application showing how to integrate with AppDirect API's including:

Integration
- Single Sign ON, to authenticate in the application through AppDirect oauth provider
- Assign / unassign users
- Subscribe / unsubscribe to a free edition of the app

Web
- Login screen
- Users screen and account info, coming from AppDirect Integration
- A log screen with all xml received from AppDirect

Technical features
- Spring based WebApp
- Spring security with openid authentication
- Bootstrap based UI
- HSQL in memory Database
- Simple test with Junit


- To generate a valid war execute: mvn package

- The application is running in amazon server: http://52.26.104.124:8080/Challenge

- Source code: https://github.com/henrycm/Challenge

