**För att köra i docker**
1. mvn package

2. cd docker

3. docker network create driftstorningnetwork

4. docker run -d -p 27017:27017 --name mongocontainer --network=driftstorningnetwork mongo

5. docker build -t driftstorning-admin:latest .

6. docker run -p 8080:8080 --name driftstorning-admin-container --network=driftstorningnetwork driftstorning-admin


**För att köra i IDE, t.ex. Intellij**
1. docker run -d -p 27017:27017 --name mongocontainer mongo

2. Main class: MdhDriftstorningAdminApplication

    Env: -Dspring.profiles.active=non-docker,init-mongo-data


