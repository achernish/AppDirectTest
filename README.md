# AppDirectTest

Demo application that integrates with AppDirect. By default it uses hsqldb database for "persistence".

## Getting started

NOTE: The application requires that Java 8 is installed.

To build the application:

    $ mvn clean package

To start the application:

    $ java $JAVA_OPTS -jar target/dependency/webapp-runner.jar --port $PORT target/*.war

Application can then be deployed to Heroku (see Procfile).

## Configure the application

The default settings for OAuth can be found in the file `resources/application.properties`.

The only need to change the OAuth consumer key and secret.

```
## OAuth
# See https://www.appdirect.com/cms/editApp/<app-id>#edit-integration for these values
appdirect.oauth.consumerKey=appdirecttest5-23466
appdirect.oauth.consumerSecret=ZrQONecxmShH8FwN