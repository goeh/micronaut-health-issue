# Test project to reproduce Micronaut issue

1. ./gradlew docker
2. docker-compose up

Wait 30 seconds for system to stabilize.

Check Eureka status http://localhost:8761/

Services HELLO and NOTIFICATIONS should be UP.

Check the health endpoint of service hello.

curl http://localhost:8082/health

All services reports UP

Make the hello service report status DOWN

curl -X POST http://localhost:8082/DOWN

Check health status again

curl http://localhost:8082/health

The hello service reports DOWN.

Check Eureka status http://localhost:8761/

The HELLO service is DOWN

Bring the hello service UP again.

curl -X POST http://localhost:8082/UP

Check health status again.

curl http://localhost:8082/health

All services are UP again

But Eureka still reports HELLO as DOWN

http://localhost:8761/

I waited 5 minutes but HELLO never was reported UP by Eureka but all health checks reports UP.

It looks like Micronaut send .../status?value=DOWN to Eureka when the service is down. But when it's back up again Micronaut does not send .../status?value=UP


**DOWN**
```
hello_1          | 10:33:12.288 [nioEventLoopGroup-1-7] DEBUG i.m.http.client.DefaultHttpClient - Sending HTTP Request: PUT /eureka/apps/hello/hello%3A8082/status?value=DOWN
hello_1          | 10:33:12.289 [nioEventLoopGroup-1-7] DEBUG i.m.http.client.DefaultHttpClient - Chosen Server: eureka(8761)
eureka_1         | 2018-10-06 10:33:12.291  INFO 1 --- [nio-8761-exec-7] c.n.eureka.resources.InstanceResource    : Status updated: HELLO - hello:8082 - DOWN
eureka_1         | 2018-10-06 10:33:12.805  INFO 1 --- [nio-8761-exec-6] c.n.eureka.resources.InstanceResource    : Status updated: HELLO - hello:8082 - DOWN
```

**UP**
```
hello_1          | 10:37:12.061 [nioEventLoopGroup-1-7] DEBUG i.m.http.client.DefaultHttpClient - Sending HTTP Request: PUT /eureka/apps/hello/hello%3A8082
```
