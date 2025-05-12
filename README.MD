# Box Problem Application

## Requirements

- Java 21

## Building the application

```shell
./mvnw clean verify
```

## Running the application

```shell
./mvnw spring-boot:run
```

## Examples

### 6 st artikel 7, 2 st artikel 4, 4 st artikel 1 => kartong nr 2

```shell

curl -X POST \
  http://localhost:8080/api/boxes/suitable \
  -H "Content-Type: application/json" \
  -d '[
    {"quantity": 6, "id": 7},
    {"quantity": 2, "id": 4},
    {"quantity": 4, "id": 1}
  ]'
```

### 12 st artikel 7, 100 st artikel 1 => "Upphämtning krävs"

```shell
curl -X POST \
  http://localhost:8080/api/boxes/suitable \
  -H "Content-Type: application/json" \
  -d '[
    {"quantity": 12, "id": 7},
    {"quantity": 100, "id": 1}
  ]'
```

