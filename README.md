# Restaurant-api
- Java 21 (spring framework)
- MYSQL

## Endpoints

### Restaurants
#### Get All Restaurants
GET "/restaurants" 

#### Find Restaurant By Id
GET "/restaurants/{id}"

#### Find Restaurants By City
GET "/restaurants/findbycity/?city=name"

#### Find Restaurants By Type
GET "/restaurants/findbytype/?type=name"

#### Find Restaurants By City and Type
GET "/restaurants/findbycityandtype/?city=name&type=name"

#### Add restaurant
POST "/restaurants"

#### Modify restaurant
PATCH "/restaurants/{id}"

#### Delete restaurant
DELETE "/restaurants/{id}"

### Menu
#### Get Menu By Restaurant Id
GET "/menu/{restaurantId}"

#### Add Menu Item
POST "/menu/?restaurantId=id"

#### Delete Menu Item
DELETE "/menu/?restaurantId=id?ItemId=id"

#### Modify Menu Item
PATCH "/menu/?restaurantId=id?ItemId=id"