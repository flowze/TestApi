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

Таблицы
```sh
CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INTEGER NOT NULL CHECK (age > 0),
    weight DOUBLE PRECISION NOT NULL CHECK (weight > 0),
    height DOUBLE PRECISION NOT NULL CHECK (height > 0),
    gender VARCHAR(10) NOT NULL CHECK (gender IN ('MALE', 'FEMALE')),
    goal VARCHAR(20) NOT NULL CHECK (goal IN ('WEIGHT_LOSS', 'MAINTENANCE', 'WEIGHT_GAIN')),
    daily_calorie_norm DOUBLE PRECISION
)

CREATE TABLE foods (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    calories_per_portion INTEGER NOT NULL CHECK (calories_per_portion > 0),
    proteins DOUBLE PRECISION NOT NULL,
    fats DOUBLE PRECISION NOT NULL,
    carbohydrates DOUBLE PRECISION NOT NULL
)
CREATE TABLE meals (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE
)

CREATE TABLE meal_items (
    id BIGSERIAL PRIMARY KEY,
    meal_id BIGINT NOT NULL REFERENCES meals(id) ON DELETE CASCADE,
    food_id BIGINT NOT NULL REFERENCES foods(id) ON DELETE CASCADE,
    portion_count INTEGER NOT NULL CHECK (portion_count >= 1)
)
```
