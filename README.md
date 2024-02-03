
# Introduction
Sample API proxy, based on Spring cloud gateway and Kotlin the proxy  
is configured to proxy the URLs configured in the application.yaml

# Use cases
Different filters could also be configured for different URLs, so the use cases are unlimited, as simple   
as the included logging filter, audit filter, caching filter...etc.  
The app is also configured to use ssl, so make sure to generate a key store, put it in the classpoth and update the properties `key-store-XXX` in the application.yaml

# Build and Run
Use the included gradle wrapper to build and run the application:

    ./gradlew build
    ./gradlew bootRun
