# Library application
REST-API приложение на Java для управления библиотекой
## Описание
Приложение получает запросы от пользователя (GET, POST, PUT, DELETE), данные передаются в формате JSON с помощью протокола HTTP.
## Поддерживаемые операции
### Book
* добавить книгу (POST/books)
* получить список всех книг (GET/books)
* получить книгу по ID (GET/books/{id})
* обновить информацию о книге (PUT/books/{id})
* удалить книгу (DELETE/books)
### Author
* добавить автора (POST/authors)
* получить список авторов(пагинация) (GET/authors?page=0&size=10)
* получить автора по ID (GET/authors/{id})
## Требования
* Spring Boot
* in-memory DB(H2)
* формат выходных данных JSON
* читаемый код, разбитый по слоям (controller,service,model,repository)
## Клонирование и запуск
```
git clone https://github.com/BogachVS/LibApp.git
```
Откройте проект в IntelliJ IDEA и запустите командой `Shift + F10`  
После запуска, для тестирования запросов перейдите по ссылке [SwaggerUI](http://localhost:8080/swagger-ui.html)  
Для просмотра данных в БД перейдите по ссылке [H2](http://localhost:8080/h2-console)  
Имя пользователя для подключения по умолчанию `sa` пароль отсутствует
