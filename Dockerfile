FROM gradle:9.2.1-jdk17 AS builder
COPY --chown=gradle:gradle . /mas-organization-backend/
WORKDIR /mas-organization-backend/
RUN gradle build --no-daemon

FROM openjdk:25 AS runner
RUN mkdir /app
COPY --from=builder /mas-organization-backend/build/libs/ /mas-organization-backend/
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/mas-organization-backend/mas-organization-backend.jar"]