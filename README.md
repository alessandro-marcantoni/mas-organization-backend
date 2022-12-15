# MAS Organization Backend

This repository contains the backend for the [mas-web-ide-organization](https://github.com/alessandro-marcantoni/mas-web-based-ide-organisation) frontend, providing CRUD operations on the stored organization specifications.

---

## Running the backend

The environment variable `MONGO_CONNECTION_STRING` for the connection to a MongoDB instance must be provided.

### Gradle

```
./gradlew run
```

### Docker
```
docker run -p <port>:3000 --env-file <path/to/dotenv> -it --rm ghcr.io/alessandro-marcantoni/mas-organization-backend:<latest-version>
```
