# Lab 2 Webshop

En applikation som först laddar in objekt i en arraylist som sparas till JSON-fil.

Efter JSON-filen är genererad för första gången så läser programmet direkt från JSON-filen istället för att skapa en ny
JSON nästa gång programmet körs.

Användaren får upp valmöjligheter i en meny.

#### I menyn kan användaren:

* Lägga till en produkt till varukorgen.
* Ta bort en produkt från varukorgen.
* Lista alla produkter användaren har lagt till i varukorgen.
* Fråga efter information om en specifik produkt.
* Lista alla produkter som finns tillgängliga i den så kallade katalogen (JSON-filen).
* Avsluta programmet.

### Struktur:

* WebshopService.java styr den övergripande logiken i appen.
* RepositoryService.java sköter hur programmet läser och sparar till och från JSON-fil.
* Product är en abstrakt klass som har 3 subklasser. Dessa är Furniture, Lamps och Plants.  
  Dessa tillsammans går under Model-paketet och deras ansvar är att skapa objekt.
* I paketet ui så har ui:n separerats från logiken. Det medför att flera olika ui kan möjligen implementeras.  
  I nuläget finns bara scanner ui implementerat.
* Main.java ansvarar för att köra igång applikationen.

```
lab2-webshop/
└── src/
    └── main/
        ├── java/
        │   └── se/
        │       └── iths/
        │           └── jimmy/
        │               ├── Main.java
        │               ├── model/
        │               │   ├── Product.java
        │               │   ├── Furniture.java
        │               │   ├── Lamps.java
        │               │   └── Plants.java
        │               ├── service/
        │               │   └── Webshop.java
        │               └── ui/
        │                   ├── Ui.java
        │                   └── UiScanner.java
        └── resources/
            ├── pom.xml
            └── products.json
```
