# Grep

Запуск

mvn package

Запускаем полученную джарку в папке target - grep-1.0-SNAPSHOT.jar

Stream - основан на стримах, наиболее эффективен для одного большого файла.

Buffered - наименее эффективный способ.(обычное считывание файла)

Parallel buffered - паралельный buffered, эффективен для множества файлов(даже для 2 лучше юзать его)

Подстрока в строке ищется стандартной джововской функцией, также имеется алгоритм кнута(с префикс функцией), но на моих тестах он не дал выгоды.