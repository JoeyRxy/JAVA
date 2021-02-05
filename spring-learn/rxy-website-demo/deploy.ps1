echo '---- Start -------'
ssh root@120.53.7.211 "cd /home/rxy;rm -f rxy-website*jar"
mv C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/pom.xml C:/Users/Rxy/Documents/tmpsss/
mv C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/.vscode/pom.xml C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/
mvn clean package -DskipTests
mv C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/pom.xml C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/.vscode/
mv C:/Users/Rxy/Documents/tmpsss/pom.xml C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/
scp C:/Users/Rxy/Documents/MYCODE/JAVA/spring-learn/rxy-website-demo/target/rxy-website*.jar root@120.53.7.211:/home/rxy/
ssh root@120.53.7.211 "cd /home/rxy"
ssh root@120.53.7.211 "nohup /usr/local/java13/bin/java -jar rxy-website*.jar --spring.profiles.active=prod &"
echo '----- Done ------'