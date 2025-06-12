# Operación Fuego de Quasar 🔥🛰️

El objetivo del proyecto es interceptar un mensaje de auxilio imperial a través de tres satélites, determinando su posición y contenido a pesar de interferencias y mensajes incompletos.

---

## 🚀 Tecnologías utilizadas

- Java 17
- Spring Boot
- Maven
- MongoDB (para nivel 3)
- Google App Engine (para deploy)

---

## 🧪 Cómo ejecutar el proyecto
### 1. Generar archivo .env con las variables de entorno
En la raíz del proyecto, crea un archivo `.env` con las siguientes variables:

```dotenv
MONGO_URI=
MONGO_DB_NAME=
  ```  
> Los valores deben ser configurados con los datos provistos en el email.
### 2. Instalar dependencias

`./mvnw install`

### 3. Ejecutar el proyecto localmente

`./mvnw spring-boot:run`

### 4. Ejecutar tests
`./mvnw test`

## 📡 Endpoints disponibles
### POST /topsecret/
#### Objetivo: enviar la información de los 3 satélites en una sola request.
#### Request Body:
```json
{
  "satellites": [
    {
      "name": "kenobi",
      "distance": 538.52,
      "message": ["este", "", "", "mensaje", ""]
    },
    {
      "name": "skywalker",
      "distance": 141.42,
      "message": ["", "es", "", "", "secreto"]
    },
    {
      "name": "sato",
      "distance": 509.90,
      "message": ["este", "", "", "", ""]
    }
  ]
}
```
#### Respuesta exitosa (200 OK):
```json
{
  "position": { "x": -100.0, "y": 75.5 },
  "message": "este es un mensaje secreto"
}
```
#### Respuesta con error (404 Not Found):
```json
{
  "error": "Not enough information to determine position or message."
}
```
### POST /topsecret_split/{satelite_name}
#### Objetivo: Registrar un satélite individualmente.
#### Request Body:
```json
{
  "distance": 100.0,
  "message": ["este", "", "", "mensaje", ""]
}
```
#### Respuesta exitosa (200 OK):
```json
{
  "name": "sato",
  "distance": 100.0,
  "message": ["este", "", "", "mensaje", ""],
  "position": {
    "x": 500.0,
    "y": 100.0
  }
}
```
### GET /topsecret_split/
#### Objetivo: Obtener la información de la transmisión enemiga.

#### Respuesta exitosa (200 OK):
> Si la información de los 3 satelites es válida
```json
{
  "position": { "x": -100.0, "y": 75.5 },
  "message": "este es un mensaje secreto"
}
```

#### Respuesta con error (404 Not Found):
> Si la información es incorrecta
```json
{
  "error": "Not enough information"
}
```

## ✨ Autor

Maxi Pomar - Desarrollador Fullstack  
Proyecto realizado para la evaluación técnica de MercadoLibre - Junio 2025