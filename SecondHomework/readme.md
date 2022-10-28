Проект собирается циклами clean install;

Для передачи аргументов main в командной строке нужно перейти в каталог target, модуля Service, проекта SecondHomework
\SecondHomework\Service\target

Примеры запросов для задания 4:
java -cp homework2-jar-with-dependencies.jar task4.MainExpenses 21.10.2022 yandex.taxi 7.8
java -cp homework2-jar-with-dependencies.jar task4.MainExpenses 22.10.2022 cofix 3.5

Примеры запросов для задания 5:
java -cp homework2-jar-with-dependencies.jar task5.MainPrecompileExpenses 20.10.2022 JET 0.88
java -cp homework2-jar-with-dependencies.jar task5.MainPrecompileExpenses 18.10.2022 VARKA 4.5

Задание 6 
Запросы сформированы в текстовом файле task6. Протестировать можно через Connection.

Задание 7
Требуемые объекты созданы. Протестировать методы можно через класс MainTest (в том числе из-под командной строки):
java -cp homework2-jar-with-dependencies.jar task7.MainTest
