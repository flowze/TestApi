# Тестовое задание


Настройте подключение к БД:
```sh
spring.datasource.url=jdbc:postgresql://localhost:5432/NameDb
spring.datasource.username=Username
spring.datasource.password=Password
```


### Примеры запросов
Get http://localhost:8080/api/reports/history/1
```sh
{
    "date": "2024-03-15",
    "items": [
      {
        "id": 1,
        "food": {
          "id": 1,
          "name": "Овсянка",
          "caloriesPerPortion": 151,
          "proteins": 5,
          "fats": 2.5,
          "carbohydrates": 27
        },
        "portionCount": 3
      }
    ]
  }
```

Get http://localhost:8080/api/reports/daily/1?date=2024-03-15
```sh
{
    "date": "2024-03-15",
  "totalCalories": 453,
  "meals": [
    {
      "date": "2024-03-15",
      "items": [
        {
          "id": 1,
          "food": {
            "id": 1,
            "name": "Овсянка",
            "caloriesPerPortion": 151,
            "proteins": 5,
            "fats": 2.5,
            "carbohydrates": 27
          },
          "portionCount": 3
        }
      ]
    }
  }
```

Get http://localhost:8080/api/reports/history/1
```sh
{
    "date": "2024-03-15",
    "items": [
      {
        "id": 1,
        "food": {
          "id": 1,
          "name": "Овсянка",
          "caloriesPerPortion": 151,
          "proteins": 5,
          "fats": 2.5,
          "carbohydrates": 27
        },
        "portionCount": 3
      }
    ]
  }
```

