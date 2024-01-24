Это приложение предназначено для работы с данными по ценным бумагам и истории торгов.
Приложение предоставляет функциональность: импорт информации о ценных бумагах и истории торгов через api, предоставляемое
московской биржей, формирование сводной таблицы по ценным бумагам и истории торгов, и удаление данных о ценных  бумагах и
истории торгов.
Приложение сделано с использованием play 2 framework , СУБД PostgreSQl для хранения данных, Slick для взаимодействия бэкенда с базой
данных.

Для запуска приложения нужно сделать следующие шаги:
1. Установить JDK 11 и выше.
2. Установить Scala 2.13.8.
3. Установить Intellij IDEA.
4. Склонировать репозиторий из GitHub.
5. Установить PostgreSql и создать базу данных с дефолтными именем и паролем, и создать базу данных moscow_exchange.
6. Открыть файл \conf\db\changelog\db.changelog-1.0.sql и запустить там 3 скрипта создания таблиц.
7. Запуск приложения через панель Run в Intellij IDEA, либо через sbt shell.
8. После запуска приложение будет доступно на дефолтном порту http://localhost:9000/.

Особенности : Функция выведения сводной таблицы доступна после перезапуска приложения, так как данные она берет из базы данных.
