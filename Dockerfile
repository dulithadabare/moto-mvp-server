FROM openjdk:8-jdk-alpine
ENV HIBERNATE_RESOURCE_DB_URL="jdbc:mariadb://localhost:3306/autobox_demo_db"
ENV HIBERNATE_RESOURCE_DB_USERNAME="demoroot"
ENV HIBERNATE_RESOURCE_DB_PASSWORD="demoroot"
ENV HIBERNATE_RESOURCE_DB_CONNECTOR_DRIVER_CLASS="org.mariadb.jdbc.Driver"
ENV HIBERNATE_RESOURCE_DB_DIALECT="org.hibernate.dialect.MariaDB103Dialect"
COPY api/target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]