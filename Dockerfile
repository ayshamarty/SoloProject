FROM maven as build
WORKDIR /build
COPY . .
RUN mvn -f Java/ clean package
FROM jboss/wildfly
COPY --from=build /build/Java/target/Yoga.war /opt/jboss/wildfly/standalone/deployments/


#longer version only using one image. Make sure you publish port 8888 rather than 8080
#FROM ubuntu
#COPY . .
#RUN apt-get update
#RUN apt-get install maven wget unzip -y

#RUN mvn -f Java/ clean package

#RUN wget "https://download.jboss.org/wildfly/10.1.0.Final/wildfly-10.1.0.Final.zip"
#RUN unzip wildfly-10.1.0.Final.zip
#RUN rm wildfly-10.1.0.Final/standalone/configuration/standalone.xml

#COPY standalone.xml wildfly-10.1.0.Final/standalone/configuration/
#EXPOSE 8888

#RUN cp /Java/target/Yoga.war /wildfly-10.1.0.Final/standalone/deployments/
#CMD ["sh", "wildfly-10.1.0.Final/bin/standalone.sh"]

