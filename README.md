# Неделя 5 домашнее задание по Reflection API

---
___Содержание:___
* Используемый стек.
* Деплой
* Описание.
* Примеры запросов.
___     

# Используемый стек и библиотеки.
1. IntelliJ IDEA Community Edition (для проверки итоговых ветвлений)
2. GitHub
3. AssertJ
4. GSON, Jackson
5. Mapstruct
6. Snakeyaml
7. PostgreSQL
8. Группа сокурсников в Telegram
___

# Деплой
- Перед запуском необходимо создать базу данных и в ней запустить скрипт для создания таблиц и заполнения базовыми значениями. Находится в директории main.resources (table_for_db.sql).
- Настройки (url, логин и пароль к базе данных) указать в файле properties.yml той же директории.
- Дополнительные настройки - для кэша. Выбирается тип кэширования и размер (вместимость объектов) кэша.

# Описание.
- Запросы делаются в классе Main. В этом классе созданы запросы для проверки работоспособности методов.
- Для вызовов методов сервиса сделана прослойка в виде Контроллера, в котором также производится преобразование объектов из JSON / XML в объекты DTO. Использовался GSON и Jackson.
- Методы сервиса работают со слоем dao через прокси. Прокси реализован согласно п.6 ТЗ. Делался на основе reflect.Proxy.
- В сервисе происходит маппинг объектов dto с объектом entity.
- В сервисе производится валидация объектов dto с фронта(UI) с возможным выбросом исключений в случае неудачи.
- Прокси объект дополнительно работает с кэшем, который имеет 2 реализации LRU или LFU. Выбор можно установить в properties.yml
- Dao слой работает с БД на PostgreSQL.

# Примеры запросов:
### Все примеры наглядно можно увидеть в консоли - нужно раскомментировать соответствующие блоки в Main классе.


> **findFacultyById(String uuidString)**
> - принимает строку, представляющую собой UUID объекта для запроса в БД.
> - возвращает строку JSON. Пример ответа: 
> ~~~
> {
> "id": "773dcbc0-d2fa-45b4-acf8-485b682adedd",
> "name": "Geography",
> "teacher": "Ivanov Pert Sidorovich",
> "email": "geography@gmail.com",
> "freePlaces": 13,
> "pricePerDay": 6.72
> }
> ~~~

> **findAllFaculties()**
> - возвращает строку JSON со всеми объектами из БД. Пример ответа:
> ~~~
> [
> {
> "id": "773dcbc0-d2fa-45b4-acf8-485b682adedd",
> "name": "Geography",
> "teacher": "Ivanov Pert Sidorovich",
> "email": "geography@gmail.com",
> "freePlaces": 13,
> "pricePerDay": 6.72
> },
> {
> "id": "8d8cfc84-e77c-4722-b4d6-8e9fdc17c721",
> "name": "Mathematics",
> "teacher": "Kobrina Daria Nikolaevna",
> "email": "mathematics@somewhere.by",
> "freePlaces": 6,
> "pricePerDay": 10.15
> }
> ]
> ~~~

> **saveFaculty(String facultyDTOJSON)**
> - принимает строку, представляющую собой JSON, конвертирующийся в DTO объект.
> - возвращает строку об успешном сохранении. Пример ответа:
> ~~~
> "The faculty Economic has been saved in the database with UUID: 936c8388-f6f4-4b4a-b6a5-7ae050de77fb"
> ~~~

> **updateFaculty(String uuidString, String facultyDTOJSON)**
> - принимает строку c id объекта для изменения и JSON с полями для изменения.
> - возвращает строку об успешном изменении. Пример ответа:
> ~~~
> "The faculty with UUID: 773dcbc0-d2fa-45b4-acf8-485b682adedd has been updated in the database."
> ~~~

> **deleteFacultyByUUID(String uuidString)**
> - принимает строку c id объекта для удаления.
> - возвращает строку об успешном удалении. Пример ответа:
> ~~~
> "The faculty with UUID: 8d8cfc84-e77c-4722-b4d6-8e9fdc17c721 has been deleted."
> ~~~

> **saveFacultyFromXML(String facultyDTOXml)**
> - принимает строку в вида xml для последующего сохранения объекта.
> - возвращает строку об успешном сохранении. Пример ответа:
> ~~~
> <String>The faculty Pagonini Day has been saved in the database with UUID: cf42af25-17dc-4779-a26d-f39f041c46a8</String>
> ~~~

###### CПАСИБО ЗА ВНИМАНИЕ !!!
