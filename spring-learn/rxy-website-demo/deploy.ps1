ssh root@120.53.7.211 "cd /home/rxy/"
ssh root@120.53.7.211 "cd rm -f data-*.jar"
mvn clean package -DskipTests
scp target/rxy-website-demo-0.0.1.jar root@120.53.7.211:/home/rxy/
ssh root@120.53.7.211 "cd /home/rxy/"
ssh root@120.53.7.211 "nohup /usr/local/java13/bin/java -jar rxy-website-demo-0.0.1.jar -spring.profiles.active=prod &"