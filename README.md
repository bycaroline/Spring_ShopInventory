## Shop Inventory Project

This is project I created using Java, Spring Web, Spring data JPA, h2 database and ThymeLeaf. It is a shop inventory and has a simple frontend to see the items in the shop and which items are low in stock. 
All CRUD operations are set up but only a few are currently set up in the frontend. These are create a new item and to see which items are low in stock. 
There are mock tests set up for the models class and the ShopController class. 

## Models

The project has one model which is a simple class that contains the attributes of the object.In this case it is the shop item. 
Item has the following attributes:
* id
* name
* price
* quantity

## Endpoints

The endpoints are the methods that are used to interact with the database.
The endpoints are:

* "/" : home page
* "/about" : about page showing info from the api requests
* "/api/shop": get Request for all items
* "/api/shop": post request for creating new item
* "/api/shop/{id}" : get request for find item by id
* "/api/shop/{id}" : put request for update item
* "/api/shop/{id}" : delete request for delete item
* "/api/shop/search/quantity" : get request for items that are low in stock




