**För att köra i docker**
1. docker network create driftavbrottnetwork

2. docker run -d -p 27017:27017 --name mongocontainer --network=driftavbrottnetwork mongo

3. docker build -t driftavbrott-gui:latest .

4. docker run -p 8080:8080 --name driftavbrott-gui-container --network=driftavbrottnetwork driftavbrott-gui


**För att köra i IDE, t.ex. Intellij**
1. docker run -d -p 27017:27017 --name mongocontainer mongo

2. Main class: MdhDriftavbrottGuiApplication

    Env: -Dspring.profiles.active=non-docker,init-mongo-data


