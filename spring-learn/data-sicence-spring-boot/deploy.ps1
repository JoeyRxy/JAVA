ssh root@120.53.7.211 "cd /home/rxy/"
ssh root@120.53.7.211 "cd rm -f data-*.jar"
mvn clean package -DskipTests
scp target/data-sicence-spring-boot-0.0.1-SNAPSHOT.jar root@120.53.7.211:/home/rxy/
ssh root@120.53.7.211 "cd /home/rxy/"
ssh root@120.53.7.211 "nohup /usr/local/java13/bin/java -jar data-sicence-spring-boot-0.0.1-SNAPSHOT.jar &"