# Prompts de Docker

## Prompt 1: Solución de conflicto de beans

### Prompt Utilizado:
```
que es este error: "2025-11-01T16:17:16.541-03:00  WARN 14301 --- ... Error creating bean with name 'dataSource' ... No qualifying bean of type 'org.springframework.boot.autoconfigure.jdbc.JdbcConnectionDetails' available: expected single matching bean but found 2: jdbcConnectionDetailsForEmpleados_mysql,jdbcConnectionDetailsForEmpleados_postgres" me sale al dar spring-boot mvn run
```

### Respuesta Recibida:
```
El error indica que Spring Boot detectó automáticamente dos servicios de base de datos en el archivo docker-compose.yml (mysql y postgres) y creó un bean de conexión para cada uno. Al intentar arrancar la aplicación, Spring no sabía cuál de los dos usar, generando un conflicto de ambigüedad.

Modifica el archivo `application.yml` para asignar explícitamente qué servicio de Docker debe usar cada perfil de Spring:
1. Perfil 'dev': Desactivar la integración de Docker (`enabled: false`) para usar H2.
2. Perfil 'mysql': Indicar explícitamente usar solo el servicio 'mysql'.
3. Perfil 'postgres': Indicar explícitamente usar solo el servicio 'postgres'.
```

### Modificaciones Realizadas:
- Se cambió 'enabled: true' a 'enabled: false' en el perfil 'dev'.
- El error/conflicto no me permitía continuar con la ejecución de la aplicación, por lo que fue necesario hacer este cambio.

### Explicación del Prompt:
El prompt se utilizó para entender y resolver un error específico relacionado con la configuración de beans.

### Aprendizajes Obtenidos:
- Se crean beans por cada servicio
- Se necesita especificar cuál bean usar en caso de que haya más de uno


## Prompt 2: Solución de conflicto de puertos

### Prompt Utilizado:
```
Tengo MySQL instalado localmente en mi máquina y al hacer `docker-compose up` me da este error: "Bind for 0.0.0.0:3306 failed: port is already allocated".
```

### Respuesta Recibida:
```
El error indica que Spring Boot detectó automáticamente dos servicios de base de datos en el archivo docker-compose.yml (mysql y postgres) y creó un bean de conexión para cada uno. Al intentar arrancar la aplicación, Spring no sabía cuál de los dos usar, generando un conflicto de ambigüedad.

Modifica el archivo `application.yml` para asignar explícitamente qué servicio de Docker debe usar cada perfil de Spring:
1. Perfil 'dev': Desactivar la integración de Docker (`enabled: false`) para usar H2.
2. Perfil 'mysql': Indicar explícitamente usar solo el servicio 'mysql'.
3. Perfil 'postgres': Indicar explícitamente usar solo el servicio 'postgres'.
Te recomiento cambiar el puerto mapeado en el archivo `application.yml` para evitar el conflicto con tu instalación local de MySQL. Por ejemplo, puedes cambiar el puerto mapeado de `3306:3306` a `3307:3306` en la sección del servicio MySQL en tu archivo `docker-compose.yml`.
```

### Modificaciones Realizadas:
- Se cambió 'enabled: true' a 'enabled: false' en el perfil 'dev'.
- Se asignó un puerto para cada perfil de base de datos.
- El error/conflicto no me permitía continuar con la ejecución de la aplicación, por lo que fue necesario hacer este cambio.

### Explicación del Prompt:
El prompt se utilizó para entender y resolver un error específico relacionado con la configuración de beans.

### Aprendizajes Obtenidos:
- Se crean beans por cada servicio
- Se necesita especificar cuál bean usar en caso de que haya más de uno
- No reutilizar puertos ya ocupados