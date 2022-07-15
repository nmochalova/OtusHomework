Команда запуска тестов:
mvn clean test -Dbrowser="chrome" -Dfilter="Developer" -Dtest=FindCourse_test

где 
-Dbrowser - имя браузера. Поддерживается: firefox, chrome, opera
-Dfilter - строка фильтра по имени курсов, например, "выбрать все курсы в имени которых есть 'QA'"

Проект содержит три теста:
FindCourseByName() - фильтрация курсов по заданному имени
getEarlyCourse()/getLatestCourse() - выбор курса, которрый стартует раньше/позже всех. Здесь игнорируются все курсы, в дате старта которых не указано конкретное число и месяц. Если несколько курсов стартуют в один день, берется любой из этих курсов. Перед кликом выбора курса на него сначала позиционируется курсор, потом перед кликом курс выделяется в красную рамку, посли клика рамка снимается(не реализовано). Далее тест проверяет, что загруженная страница соответствует выбранному курсу. Проверка происходит по тегу title страницы. В случае если title не содержит названия курса, тест проверку не проходит. Пример когда тест будет зафейлен:
1) title = null
2) название курса "Специализация сетевой инженер" , title = "Специализация Network Engineer"
3) название курса "Специализация Java-разработчик", title = "Специализация Java Developer"
В этих случаях предполагается регистрация дефекта для корректировка title страницы.

Зона развития тестов: доработка функционала поиска дат на сломе года, сейчас анализируются даты только для текущего года. 