# Test project to reproduce Micronaut issue

1. ./gradlew docker
2. docker-compose up

Wait 30 seconds for system to stabilize.

Check Eureka status http://localhost:8761/

Services HELLO and NOTIFICATIONS should be UP.

curl http://localhost:8082/health

All services reports UP

Make the hello service report status DOWN

curl -X POST http://localhost:8082/DOWN

Check health status again

curl http://localhost:8082/health

Check Eureka

Check Eureka status http://localhost:8761/

The HELLO service is DOWN

Bring the hello service UP again

curl -X POST http://localhost:8082/UP

Check health status again

curl http://localhost:8082/health

All services are UP again

But Eureka still reports HELLO as DOWN

I waited 5 minutes but HELLO never was reported UP by Eureka but all health checks reports UP.
