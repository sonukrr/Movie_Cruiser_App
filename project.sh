cd authservice
source ./env-variable.sh
mvn clean package
cd ..
cd moviecruiserserverapplication
source ./env-variable.sh
mvn clean package
cd ..