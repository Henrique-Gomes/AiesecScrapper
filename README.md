Java 17 required

Executing the application:
```
cd target
java -jar AiesecScrapper.jar > csv.csv
```

If you want to specify the filters, you can provide an URL by parameter as follows:
```
cd target
java -jar AiesecScrapper.jar https://aiesec.org/search?earliest_start_date=2023-11-04&programmes=8 > csv.csv
```

It is already compiled, but in case you need to compile it again, run the following in the project root folder:

Windows:
```
mvnw package
```

Linux / MacOS (requires Maven):
```
mvn package
```
