# Meetup Micro-services

The project explores micro-service oriented application design with use of CQRS principle:

![CQRS](docs/img/cqrs.png "CQRS")

It consists of two services:

- users-api - provides REST API to clients and implements the Query part of CQRS.
It uses Elasticsearch as projection repository updated by events generated by users-handler.
- users-handler - implements the Command part of CQRS and uses h2 database for persistence.

The services communicate via JMS bus provided by Apache ActiveMQ.

More detailed design documentation is available [here](docs/README.md).

## Prerequisities

Install Docker Machine and Compose: http://docs.docker.com/machine/install-machine/

## Build and Run

> git clone https://github.com/benzohub/meetup.git

> cd meetup

> ./gradlew buildDocker

> ./runall

If everything went well, you should see a lot of log messages in the console without errors among them ;)

Visit the following urls:

`http://192.168.99.100:8080/users` - list of registered users

`http://192.168.99.100:8161/admin/queues.jsp` - ActiveMQ Admin page listing queues

`192.168.99.100` is the default IP of the docker VM. It can be retrieved by calling:

> docker-machine ip default

### Create new user

curl -XPOST 'http://192.168.99.100:8080/users' -H 'Content-Type: application/json' -d '{
    "userName" : "eupestov",
    "userFullName" : "Eugene Pestov"
}
'

curl -XPOST 'http://192.168.99.100:8080/users' -H 'Content-Type: application/json' -d '{
    "userName" : "bonny",
    "userFullName" : "Bonny Nord"
}
'

### Create new users connection

curl -XPOST 'http://192.168.99.100:8080/users/eupestov/connections' -H 'Content-Type: application/json' -d '{
    "userName" : "bonny"
}
'

The API Specification is available in docs/api in RAML format.

## UI

After the services started successfully you can run the simplistic UI as described in ui/README.md

## Scalability

In order to make this application truly scalable and resilient it requires an addition of few infrastructure
elements e.g.:

- API Gateway to proxy and load-balance instances of api modules. It will also inject security context.
  (a possible candidate: Netflix Zuul)
- API Registry to provide the API Gateway with information about available services and their location
  (Netflix Eureka)
- Replace the built-in Elasticsearch instance in the api module with a cluster

## Future developments

- At the moment the service implements only sunny day scenarios and have no error handling in place.
- The messaging infrastructure needs to be improved to support guaranteed delivery.
- handler services should probably implement Event Sourcing patters with actual Event Store implementation
  rather that a RDBMS.

## Testing

There is one test example in the api module which demonstrates possibility for contract (RAML-based) and
integration testing.

The following parts require better unit test coverage:

- core: ActionDispatcherImpl, ActionJmsListenerEndpoint, SimpleJsonMessageConverter
- handler: command handlers, custom repository methods
- api: controllers, services

The test coverage should be calculated during every CI build and uploaded into Sonar Cube.
