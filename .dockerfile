FROM tomcat:9.0.73-jdk8-corretto

COPY target/*.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
