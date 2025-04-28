FROM tomcat:9.0.73-jdk8-corretto
RUN rm -rf /usr/local/tomcat/webapps/*
RUN mkdir -p /opt/tuniu/logs/tomcat/app/tomcat_fbs_npc
COPY target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/demo.war
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone