FROM gradle:7.6.0-jdk17 AS builder
COPY --chown=gradle:gradle . /mas-organization-backend/
WORKDIR /mas-organization-backend/
RUN gradle build --no-daemon

FROM openjdk:17.0.2 AS runner
RUN mkdir /app
COPY --from=builder /mas-organization-backend/build/libs/ /mas-organization-backend/
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/mas-organization-backend/mas-organization-backend.jar"]