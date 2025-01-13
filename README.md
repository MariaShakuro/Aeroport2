# Aeroport
**Инструкция**
--------------------------
**1.Склонировать репозиторий**

```git clone git@github.com:MariaShakuro/Aeroport2.git```

**2.Проверить скачан ли Maven**

Если нет,то вот [ссылка](https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.zip)

Распаковать в папку Maven и добавить путь в ,,Переменные среды,,+проверить JDK установлен ли и прописан ли также путь там

**2.1 Проверить скачан ли Docker Desktop**

[Ссылка для скачивания](https://www.docker.com/products/docker-desktop/)

 Установить и также добавить путь в ,,Переменные среды,,

**3.Переходим в IDE **

В терминале:

Сначала надо собрать jar каждого сервиса:

      ```cd auth_service_application```

     ```mvn clean install```

      Возвращаемся обратно:

      ```cd ..```

И проделываем так со всеми 

**4.Собираем проект**

```docker-compose build```

**5.Поднимаем его**

```docker-compose up -d```


**5.Тестируем через Postman**
                                                                                           
  1.Импортируем коллекцию в Postman
  
1)Откройте Postman.

2)Нажмите на кнопку Import в левом верхнем углу.

Появится окошко:
![image](https://github.com/user-attachments/assets/b9a189e8-74e7-4e62-8c4c-86549d748700)

Нажмите в середине на ***files*** и нажмите на  Aeroport.postman_collection.json(которое находится в моем проекте в папке,в которую вы скопировали мой проект)
    

**5.Закрываем программу**
```docker-compose down```

!!!Для отслеживания рекоммендую скачать:[Docker Desktop](https://www.docker.com/products/docker-desktop/)


