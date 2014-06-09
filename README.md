gatling-seed
============

Simple Stress Simulation with JSON login and session data



The project attempts to submit login details using x/www-form-urlencoded POST request. A JSON response is expected, from which
an access token value is stored in the gatling user session for later usage. The next request accesses the stored token and
passes it as an authorization header.


The scenarios are run by the Gatling runner class com.excilys.ebi.gatling.app.Gatling.


Build: gradle

To run
======
```
gradle clean run
```

The gatling HTML report is saved at /build/reports/gatling

