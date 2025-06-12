# Arquitectura del Proyecto - Operación Fuego de Quasar 🚀

Este proyecto resuelve el desafío técnico de MercadoLibre "Operación Fuego de Quasar", decodificando un mensaje secreto
y determinando la posición de una nave a partir de datos incompletos de tres satélites.

---

## 🧱 Componentes Principales

### 1. Capa de Controlador

- **TopSecretController**  
  Expone los endpoints `/topsecret` y `/topsecret_split`.
    - Recibe datos de los satélites (distancia y mensaje).
    - Valida la información.
    - Llama a los servicios y devuelve respuestas HTTP estándar.

### 2. Capa de Servicio

- **TransmissionService**  
  Contiene la lógica principal:
    - Cálculo de posición mediante trilateración.
    - Reconstrucción del mensaje a partir de fragmentos incompletos.

- **SatelliteService**  
  Maneja el acceso a datos satelitales (lectura y escritura en MongoDB para el split del nivel 3).

### 3. Modelos

- `SatelliteEntity`: representa un satélite persistido.
- `SatelliteStatus`: datos entrantes por satélite individual.
- `TopSecretRequest`: contenedor para los 3 satélites (modo batch).
- `Transmission`: respuesta que contiene posición y mensaje decodificado.

### 4. Persistencia

- Se utiliza **MongoDB** para almacenar los datos de satélites recibidos en `/topsecret_split/{satellite_name}`.
- Los datos se acumulan hasta que puedan ser procesados para obtener una respuesta completa.

---

## ⚙️ Tecnologías

- Java 17
- Spring Boot
- Maven
- MongoDB
- Google App Engine (entorno de despliegue)

---

## 📦 Organización del Código

```text
com.quasar
├── controller     # Exposición de API REST
├── service        # Lógica de negocio (triangulación, mensajes)
├── model          # Entidades y DTOs
├── repository     # Acceso a MongoDB
└── config         # Configuración del entorno y beans
```

---

## 🧪 Testing

- Se testean las funcionalidades principales:
    - Algoritmo de triangulación
    - Reconstrucción de mensajes
    - Casos de error y excepciones

---

## 🌐 Deployment

El proyecto se despliega en **Google App Engine**