FROM hirokimatsumoto/alpine-openjdk-11:latest as jlink-image

RUN jlink \
     --module-path /opt/java/jmods \
     --compress=2 \
     --add-modules jdk.management.agent,java.base,java.logging,java.compiler,java.xml,jdk.unsupported,java.sql,java.naming,java.desktop,java.management,java.security.jgss,java.instrument \
     --no-header-files \
     --no-man-pages \
     --output /opt/jdk-11-mini-runtime

FROM alpine:3.8
VOLUME /tmp

ENV JAVA_HOME=/opt/jdk-11-mini-runtime
ENV PATH="$PATH:$JAVA_HOME/bin"
ARG BASE_DIRECTORY

ENTRYPOINT [ "sh", "-c", "java -Dserver.port=8080 -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -jar /app.jar " ]

HEALTHCHECK --interval=20s --timeout=3s --retries=20 \
	CMD curl --fail http://localhost:8080/actuator/health || exit 1

RUN apk --no-cache add curl && \
	apk --no-cache add iptables

RUN apk --no-cache add ca-certificates && \
	wget -q -O /etc/apk/keys/sgerrand.rsa.pub https://alpine-pkgs.sgerrand.com/sgerrand.rsa.pub
RUN wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-2.29-r0.apk && \
	wget https://github.com/sgerrand/alpine-pkg-glibc/releases/download/2.29-r0/glibc-bin-2.29-r0.apk
RUN apk add glibc-2.29-r0.apk glibc-bin-2.29-r0.apk

COPY --from=jlink-image /opt/jdk-11-mini-runtime /opt/jdk-11-mini-runtime
COPY ${BASE_DIRECTORY}/target/*.jar app.jar
