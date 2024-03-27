# AIESEC Scrapper

Java 17, Chrome and compatible [ChromeDriver](https://chromedriver.chromium.org/downloads) required.

Executing the application:
```
cd target
java -jar AiesecScrapper.jar
		[-u|--url https://aiesec.org/search?<query string>]
		[-rf|--resume-from https://aiesec.org/opportunity/<category>/<id>]
		[-o|--output output_filename.csv]
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

## TODO:
* parallelism
	* Load opportunities while loading list
	* Load multiple opportunities at once
	* Load multiple properties of the same opportunity at once
* Headless