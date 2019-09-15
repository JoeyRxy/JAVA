1. generate a quickstart
   in cmd prompt instead of powershell!
   ```cmd
    cd <path-you-want-to-generate>
    mvn archetype:generate -DarchetypeArtifactId="maven-archetype-quickstart" -DarchetypeGroupId="org.apache.maven.archetypes" -DarchetypeCatalog=local
   ```
   which `-DarchetypeCatalog=local`'s prerequisite  is a `archetype-catalog.xml` file in local repository.
   the file can download from [archtype-catalog.xml](https://github.com/JoeyRxy/code/blob/master/Java/archetype-catalog.xml)

2. add external jar to local repository
   get the jar.
   in cmd instead of powershell
   ```cmd
    cd <path-to-jar>
    mvn install:install-file -Dfile=<path-to-file> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=<packaging-e.g.:jar>
   ```
   after install to repository,add the dependency to `pom.xml`'s `dependencies`
   ```xml
   <dependency>
      <groupId>groupId</groupId>
      <artifactId>artifactId</artifactId>
      <version>version</version>
    </dependency>
   ```

3. download sources and javadocs

  in the java-project:
   ```cmd
   mvn dependency:sources
   mvn dependency:resolve -Dclassifier=javadoc
   ```

   if these files are not uploaded by the author to the repository,then there will promot:
   