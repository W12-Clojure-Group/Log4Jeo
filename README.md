# log4jeo

Geolocation of log entries

## Prerequisites

You will need [Leiningen][1] 1.7.0 or above installed.

[1]: https://github.com/technomancy/leiningen

## Get dependancies

To retrieve dependancies, run:

	lein deps


## Running tests

To run the tests, run:

	lein test

## Running

To start a web server for the application, run:

    lein ring server
    
To start a headless web server, run:

    lein ring server-headless

## License

Copyright © 2014 FIXME

##TODO

* Create JSON based on Apache logs which include timestamp and IP address
* POST JSON and extract values adding them into a map
* GET enpoint that returns contents of the map