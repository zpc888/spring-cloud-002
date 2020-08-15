# Lab Goal
![Final Goal](./docs/low-latency-high-throughput-design.png)
# Lab Current Status
![Current Phase](./docs/spring-boot-lab-002-overall.png)
## spring cloud config
### lookup config server
* via config server url
* via auto discovery using eureka
### refresh config data from github
* actuator/refresh
* actuator/bus-refresh
API bus-refresh can be invoked from any nodes where they include actuator and bus-amqp
![Invoke from config server](./docs/screenshot-bus-refersh-from-config-server.png)
After bus-refresh, all user-consumers and user-providers' configuration values are updated
![User Consumer 2 Config](./docs/screenshot-show-consumer-2-config.png)
