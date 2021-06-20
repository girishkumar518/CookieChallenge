# Building the image
FROM java:8
WORKDIR /
EXPOSE 8080
COPY target/CookieChallenge-1.0-jar-with-dependencies.jar /CookieChallenge.jar

CMD ["java", "-jar" , "/CookieChallenge.jar", "--file $file" , "--date $date"]