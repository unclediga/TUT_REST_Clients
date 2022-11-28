call mvn archetype:generate -DarchetypeGroupId=org.glassfish.jersey.archetypes^
 -DarchetypeArtifactId=jersey-quickstart-grizzly2^
 -DinteractiveMode=false^
 -DgroupId=ru.unclediga^
 -DartifactId=jersey-simple^
 -Dpackage=ru.unclediga.jersey^
 -DarchetypeVersion=2.35

cd jersey-simple

call mvn dependency:tree -l jersey-simple.txt

tree /f  >> jersey-simple.txt
