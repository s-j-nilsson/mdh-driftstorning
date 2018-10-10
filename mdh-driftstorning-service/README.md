**För att köra i docker**
1. docker network create driftstorningnetwork

2. docker run -d -p 27017:27017 --name mongocontainer --network=driftstorningnetwork mongo

3. docker build -t driftstorning-service:latest .

4. docker run -p 8080:8080 --name driftstorning-service-container --network=driftstorningnetwork driftstorning-service


**För att köra i IDE, t.ex. Intellij**
1. docker run -d -p 27017:27017 --name mongocontainer mongo

2. Main class: MdhDriftstorningServiceApplication

    Env: -Dspring.profiles.active=non-docker,init-mongo-data


