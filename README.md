[![Build Status](https://travis-ci.org/Lucivarushka/RestApiSecurity.svg?branch=master)](https://travis-ci.org/Lucivarushka/RestApiSecurity)


Необходимо реализовать REST API, которое взаимодействует с БД и позволяет выполнять CRUD операции со следующими сущносятми:
User (id, username, password, created, updated, lastPasswordChangeDate, Status status, String phoneNumber)
Skill (id, name)
Developer (id, firstName, lastName, specialty, Set<Skill> skills, Account account).
Account(id, accountData)
enum Status {... ACTIVE, APPROVAL_REQUIRED, DELETED, BANNED ...} 

Требования:

Все CRUD операции для каждой из сущностей

Придерживаться подхода MVC

Для сборки проекта использовать Maven
В качестве основного фреймворка необходимо использовать Spring (IoC, Security, Data, etc.)

Для взаимодействия с БД - Spring Data

Инициализация БД должна быть реализована с помощью liquibase

Взаимодействие с пользователем необходимо реализовать с помощью Postman (https://www.getpostman.com/)

Репозиторий должен иметь бейдж сборки travis(https://travis-ci.com/)

В приложении должно быть 3 роли пользователя:
ROLE_ADMIN (имеет полный доступ ко всем сущностям)

ROLE_MODERATOR (имеет доступ на чтение для всех сущностей и на запись для сущностей Developer + Account)

ROLE_USER (имеет доступ на чтение для сущностей Developer, Account, Skill)

Рабочее приложение должно быть развёрнуто на https://heroku.com/

Регистрацию необходимо подтверждать по номеру телефона. Для этих целей использовать https://www.twilio.com/

Сервисный слой и контроллеры должны быть покрыты unit-тестами



Технологии: Java, MySQL, Spring (MVC, Web, Data, Security, Boot), Lombok, Maven, Liquibase.

Результатом выполнения приложения должно быть приложение, развернутое на heroku.

