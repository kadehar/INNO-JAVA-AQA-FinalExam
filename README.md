# Промежуточная аттестация

### Задания

Напишите UI-тесты на сайт Swag Labs

### Реквизиты

- URL: https://www.saucedemo.com/

### Создание Allure отчета после прогона тестов и его просмотр

Чтобы создать Allure-отчет после загрузки проекта, нужно выполнить следующий шаг:
`Gradle > verification > allureReport` - установит Allure и все необходимые инструменты 
(можно так же выполнить команду `allureReport` в консоли):

```shell
gradle allureReport
```

`Gradle > verification > allureServe` - Создаст отчет после прогона теста и откроет его в браузере 
(можно выполнить команду `allureServe` в консоли)

```shell
gradle allureServe
```

### Управление браузером

По-умолчанию, браузер в тестах запускается в `headless` режиме.
Чтобы браузер отображался во время тестов необходимо изменить значение `headless` с `true` на `false` в файле `web.properties`.
Там же можно изменить `pageLoadStrategy` и `timeout` для `Selenide`. Для отображения выполнения шагов `Selenide` в отчете нужно изменить значение `enableSteps` с `false` на `true`.