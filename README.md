## Тестовое задание на стажера java разработчика от VK

### Реализовано restapi для перенаправления запросов к https://jsonplaceholder.typicode.com/.
### Приложение использует Java 21, Spring Boot

#### 1. Реализованы обработчики (GET, POST, PUT, DELETE)
- api/posts/**
- api/albums/**
- api/users/**
#### 2. Реализована базовая авторизация и ролевая модель при помощи Spring Security
- ROLE_ADMIN - доступ ко всем обработчикам
- ROLE_POSTS - доступ к обработчику posts
- ROLE_ALBUMS - доступ к обработчику albums
- ROLE_USERS - доступ к обработчику users


#### 3. Реализовано введение аудита действий при помощи Spring Aspect

#### 4. Используется inmemory кэш
- Для запросов к jsonplaceholder - Mono.cache
- Для запросов к бд - Spring Cache
#### 5. Для пользователей и аудита действий используется база данных - PostgreSql и Spring Data JPA 
#### 6. Написаны юнит тесты испоользуя Junit5, Mockito, WireMock
