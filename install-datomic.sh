#!/bin/bash

version="0.9.4752"

function has_users_dot_lein_profile_been_updated() { datomic_install_location=${1}
	datomic_count=`cat ~/.lein/profiles.clj | grep -c ":datomic"`
	install_location_count=`cat ~/.lein/profiles.clj | grep -c "${datomic_install_location}"`
	if [ ${datomic_count} -eq 1 ] && [ ${install_location_count} -eq 1 ]; then
		echo "y"
	else
		echo "n"
	fi
}

working_dir=`pwd`

mkdir -p libs

curl -Lk https://my.datomic.com/downloads/free/${version} -o libs/datomic-free-${version}.zip
cd libs
unzip datomic-free-${version}.zip
cd ..

echo "what directory would you like datomic to be installed in (datomic will automatically be appended to your chosen path)?"
read install_dir
datomic_dir=${install_dir}/datomic
mkdir -p ${datomic_dir}
if [ ! -e ${datomic_dir} ]; then
	echo "I'll have to sudo mkdir that directoy..."
	sudo mkdir -p ${datomic_dir}
	if [ ! -e ${datomic_dir} ]; then
		echo "failed to make that directory, sorry!"
		return -2
	fi
	sudo mv libs/datomic-free-${version} ${datomic_dir}/datomic-free-${version}
	cd ${datomic_dir}
	sudo ln -s ${datomic_dir}/datomic-free-${version} current
else
	mv libs/datomic-free-${version} ${datomic_dir}/datomic-free-${version}
	cd ${datomic_dir}
	ln -s ${datomic_dir}/datomic-free-${version} current
fi

lein_profiles_set_up=`has_users_dot_lein_profile_been_updated /usr/share/datomic/current`

while [ "${lein_profiles_set_up}" == "n" ]; do
	echo "in the file: ~/.lein/profiles.clj"
	echo "add this line:"
	echo "    :datomic { :install-location \"${datomic_dir}\" }"
	echo "inside the :user {} section"
	lein_profiles_set_up=`has_users_dot_lein_profile_been_updated /usr/share/datomic/current`
done

#jar="~/.m2/repository/com/datomic/datomic-free/${version}/datomic-free-${version}.jar"
#pom="~/.m2/repository/com/datomic/datomic-free/${version}/datomic-free-${version}.pom"
#mvn install:install-file -DgroupId=com.datomic -DartifactId=datomic-free -Dfile=${jar} -DpomFile=${pom}

echo "To start Datomic, run:"
echo "  lein datomic start &"
echo "from the project root directory"
echo "then:"
echo "  lein datomic initialize"
echo ""
echo "Datomic console"
echo "==============="
echo "You may want to get the Datomic console from https://my.datomic.com/downloads/console - requires setting up a login"
echo "More info: http://blog.datomic.com/2013/10/datomic-console.html?updated-min=2013-01-01T00:00:00-08:00&updated-max=2014-01-01T00:00:00-08:00&max-results=11"
echo "in the repl:"
echo "  lein repl"
echo ""
echo "Using the following should connect you to a clean database:"
echo ""
echo "user=> (use '[datomic.api :only [q db] :as d])"
echo "user=> (def uri \"datomic:free://localhost:4334/log4jeo\")"
echo "user=> (d/delete-database uri)"
echo "user=> (d/create-database uri)"
echo "user=> (def conn (d/connect uri))"
