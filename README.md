# log4jeo

Geolocation of log entries

# Overview

We aim to consume Apache HTTPD access_log entries and display their distribution on a map (global or UK).
We also wish to take subsets of the data (e.g. 3 weeks ago until 1 week ago) and display this subset distribution.

# Suggested Technologies (subject to change)
Clojure 
Compojure
Incanter
Datomic


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

To kick off auto-compilation of clojurescript code, in a separate terminal or screen, run:
    lein cljsbuild auto
    
## License

Copyright © 2014 FIXME

This product includes GeoLite2 data created by MaxMind, available from [http://www.maxmind.com][1]

[1]: http://www.maxmind.com

##TODO

- [x] Merge timestamp and ip address functions- AL
- [ ] Convert timestamp and ip to JSON - AL
- [ ] Convert timestamp to epoch time - AL
- [ ] Extract all long and lats from Apache log and convert them to JSON - AL
- [ ] GET endpoint JSON with relevant data from Apache logs - AL
- [ ] Integrate database (Maxmind CSV) with GeoIP location - RL
- [ ] Provide a lookup function for IP address to location - RL
- [ ] Visualise map with :longitude(s), :latitude(s), (:city?) - NL  (contour)
- [ ] Render statistics : Create some starting graphs that consume the data - UT
- [x] Add instructions for lein cljs build - UT 
- [x] Decide on which rendering graphs - UT - at this stage incanter with memoization most straightforward
- [ ] Instructions on Datatomic install - RL
- [ ] Get data from MaxMind urls and unzip as a periodic background process
- [ ] 
