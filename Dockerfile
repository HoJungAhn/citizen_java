FROM openjdk:17-alpine

ADD target/citizen-example.jar app.jar
RUN wget https://github.com/microsoft/ApplicationInsights-Java/releases/download/3.2.6/applicationinsights-agent-3.2.6.jar
ENV APPLICATIONINSIGHTS_CONNECTION_STRING="InstrumentationKey=a63792dd-e97f-4c70-ab4f-66bd4e66d4f7;IngestionEndpoint=https://koreacentral-0.in.applicationinsights.azure.com/"

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Duser.timezone=Asia/Seoul /app.jar