# Recipes Project
## Описание
Создадим программу, которая хранит все рецепты в одном месте. Многопользовательский веб-сервис созданное с помощью Java/Spring Boot/Spring Security, который позволяет хранить, извлекать, обновлять и удалять рецепты.

Выпускной проект  Java Backend Developer JetBrain Academy.

### Main Features:
* Registration/Login пользователей.
* Доступ к рецептам только у зарегистрированных пользователей.
* Проверка структуры адреса электронной почты.
* Authentication - пользователи могут редактировать и удалять только рецепты, созданные ими самостоятельно.
* Проверка отправленных рецептов, ни одно поле не может быть пустым.
* Поиск рецептов по категориям или по названию.
* Хранение рецептов в базе данных.

## Примеры
Пример 1: ___`POST /api/recipe/new` запрос без аутентификации___
```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```

___Status code: `401 (Unauthorized)`___

Пример 2: ___`POST /api/register` запрос без аутентификации___
```json
{
   "email": "Cook_Programmer@somewhere.com",
   "password": "RecipeInBinary"
}
```
___Status code: `200 (Ok)`___

___Further `POST /api/recipe/new` request with basic authentication; email (login): Cook_Programmer@somewhere.com, and password: RecipeInBinary___
```json
{
   "name": "Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
___Response:___
```json
{
   "id": 1
}
```
___Further `PUT /api/recipe/1` request with basic authentication; email (login): Cook_Programmer@somewhere.com, password: RecipeInBinary___
```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
___Status code: `204 (No Content)`___

___Further `GET /api/recipe/1` request with basic authentication; email (login): Cook_Programmer@somewhere.com, password: RecipeInBinary___

___Response:___
```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
Example 3: ___`POST /api/register` request without authentication___
```json
{
   "email": "CamelCaseRecipe@somewhere.com",
   "password": "C00k1es."
}
```
___Status code: 200 (Ok)___

___Further response for the `GET /api/recipe/1` request with basic authentication; email (login): CamelCaseRecipe@somewhere.com, password: C00k1es.___
```json
{
   "name": "Fresh Mint Tea",
   "category": "beverage",
   "date": "2020-01-02T12:11:25.034734",
   "description": "Light, aromatic and refreshing beverage, ...",
   "ingredients": ["boiled water", "honey", "fresh mint leaves"],
   "directions": ["Boil water", "Pour boiling hot water into a mug", "Add fresh mint leaves", "Mix and let the mint leaves seep for 3-5 minutes", "Add honey and mix again"]
}
```
___Further `PUT /api/recipe/1` request with basic authentication; email (login): CamelCaseRecipe@somewhere.com, password: C00k1es.___
```json
{
   "name": "Warming Ginger Tea",
   "category": "beverage",
   "description": "Ginger tea is a warming drink for cool weather, ...",
   "ingredients": ["1 inch ginger root, minced", "1/2 lemon, juiced", "1/2 teaspoon manuka honey"],
   "directions": ["Place all ingredients in a mug and fill with warm water (not too hot so you keep the beneficial honey compounds in tact)", "Steep for 5-10 minutes", "Drink and enjoy"]
}
```
___Status code: `403 (Forbidden)`___

___Further `DELETE /api/recipe/1` request with basic authentication; email (login): CamelCaseRecipe@somewhere.com, password: C00k1es.___

___Status code: `403 (Forbidden)`___
