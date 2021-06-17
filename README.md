# Shoes project

## Application info
TODO

## Launch Application
TODO

## Test Application
TODO

## Doc
[Explanations](doc/Explanations.md#explanations)

## Work to do
- Add a new entity Stock that will represent the current stock of the whole shop, which can contain any shoe model, 
and a limited capacity of shoes globally (30 shoe boxes)

- Add a new DTO which will represent the state of the Stock (EMPTY, FULL, SOME) as well as the availability of each shoe model. 
The Shoe DTO should not be updated, since the shoe exhibition API consumers should keep working. 
Nevertheless, you can create any Entities you want, and modelize the new features as it pleases you.

- Create a new core that will contain those new entities in an embedded database (any embedded database you think fit, 
and once again, feel free to create/update the application.yml or the pom.xml or whatever file you want)

- Add a new endpoint /stock (or whatever name you want) which will:
  - GET: return the state of the stock (EMPTY, FULL or SOME)
  - PATCH: update the stock (either submit a big DTO containing all shoes and their quantities, 
  or a small DTO we will have to push multiple time containing the shoe model to add along with the quantity)
  
- Provide some JUnit/TestNG tests, in order to:
  - make sure your application works (for you)
  - provide some datasets to see how your application works (for us)
  - any additional stuff that may ease the maintenance




