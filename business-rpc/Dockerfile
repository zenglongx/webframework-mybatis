FROM openjdk:8-jre

RUN mkdir /app

ADD target/webframework-business-rpc-2.0.3.RELEASE.jar /app
ADD entrypoint.sh /app

WORKDIR /app

CMD sh entrypoint.sh
