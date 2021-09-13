[![Build Status](https://travis-ci.com/dmitriyermoshin19/job4j_managingPassports.svg?branch=main)](https://travis-ci.com/dmitriyermoshin19/job4j_managingPassports)
[![codecov](https://codecov.io/gh/dmitriyermoshin19/job4j_managingPassports/branch/main/graph/badge.svg)](https://codecov.io/gh/dmitriyermoshin19/job4j_managingPassports)
# job4j_managingPassports

### Описание проекта
Данный проект показывает ***виды взаимодействия микросервисов***. ***Синхронное взаимодействие и ассинхронное***. Синхронное осуществляется посредством ***Rest API***. Асинхронное - посредством ***Kafka*** как брокера сообщений. Сервис  PassportApiService в модуле managingPassports полностью копирует методы сервиса PassportServiceImpl через url-запрос. Асинхронное взаимодействие представляется меджу двумя модулями managingPassports и mail_service. На контроллер модуля managingPassports поступает запрос из браузера для проверки паспортов, подлежащих замене в ближайшие 3 месяца, и затем поступает ответ с сообщением о наличии или отстутствии в базе данных таких паспортов. Затем, если паспорта найдены, то сведения о них посылаются через брокер сообщений в сервис модуля mail_service, который извещает владельцев о предстоящей замене. Проверка происходит с помощью переодического запуска.

### Использованные технологии
* Kafka
* Java 14
* Spring Boot 2
* Spring Data JPA
* PostgreSQL
* Liquibase
* Maven
* Travis C.I.
* Checkstyle
* Jacoco

### Работа программы
#### Запуск микросервисов
1. Запускаем Kafka и Zookeeper


   ![GitHub Logo](images/kafka.png)


2. Запускаем микросервисы


   ![GitHub Logo](images/microserveses.png)


3. Сервис посылает сообщения через Kafka


   ![GitHub Logo](images/send.png)


4. Сервис получает сообщения от Kafka


   ![GitHub Logo](images/listener.png)