FROM ubuntu
COPY . .
RUN apt-get update
RUN apt-get install maven -y
RUN mvn -f Java/ clean
RUN mvn -f Java/ package
RUN apt-get install wget -y
RUN apt-get install unzip -y
RUN wget "https://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip"
RUN unzip wildfly-10.1.0.Final.zip
RUN rm wildfly-10.1.0.Final/standalone/configuration/standalone.xml
COPY standalone.xml wildfly-10.1.0.Final/standalone/configuration/
EXPOSE 8888
RUN cp /Java/target/Yoga.war /wildfly-10.1.0.Final/standalone/deployments/
CMD ["sh", "wildfly-10.1.0.Final/bin/standalone.sh"]
