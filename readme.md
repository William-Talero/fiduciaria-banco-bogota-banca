# Bancario Microservice

Este es un microservicio para la gestión de cuentas bancarias, desarrollado utilizando Java 11, Spring Boot, y Spring Data JPA con una base de datos PostgreSQL. El microservicio permite la creación de cuentas, realización de consignaciones, retiros, y consulta de saldos.

## Tecnologías Utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven

## Endpoints
- **POST /accounts**: Crear una nueva cuenta bancaria.
- **POST /accounts/{id}/deposit**: Realizar una consignación en una cuenta bancaria.
- **POST /accounts/{id}/withdraw**: Realizar un retiro de una cuenta bancaria.
- **GET /accounts/{id}/balance**: Consultar el saldo de una cuenta bancaria.

## Entidades
### CuentaBancaria
- **id** (Long): Identificador único de la cuenta bancaria.
- **titular** (String): Nombre del titular de la cuenta bancaria.
- **saldo** (BigDecimal): Saldo actual de la cuenta bancaria.
- **fechaCreacion** (LocalDateTime): Fecha de creación de la cuenta bancaria.

### Transacción
- **id** (Long): Identificador único de la transacción.
- **cuentaBancaria** (CuentaBancaria): Referencia a la cuenta bancaria asociada.
- **tipo** (enum: DEPOSITO, RETIRO): Tipo de transacción.
- **monto** (BigDecimal): Monto de la transacción.
- **fecha** (LocalDateTime): Fecha de la transacción.

## Validaciones
- El saldo inicial debe ser 0 al crear una cuenta.
- Los montos de las transacciones deben ser positivos.
- No se puede realizar un retiro si el saldo es insuficiente.
- El titular debe tener entre 1 y 100 caracteres.
- Si se presenta una de estas condiciones, el Response debe informarlo y se debe registrar un log con la validación realizada.

## Estructura del Proyecto
```plaintext
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── fiduciaria_banco_bogota/
│   │           └── banca/
│   │               ├── BancarioApplication.java
│   │               ├── controller/
│   │               │   └── CuentaBancariaController.java
│   │               ├── model/
│   │               │   ├── CuentaBancaria.java
│   │               │   └── Transaccion.java
│   │               ├── repository/
│   │               │   ├── CuentaBancariaRepository.java
│   │               │   └── TransaccionRepository.java
│   │               ├── service/
│   │               │   └── CuentaBancariaService.java
│   │               └── dto/
│   │                   ├── CuentaBancariaDTO.java
│   │                   └── TransaccionDTO.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/
        └── com/
            └── fiduciaria_banco_bogota/
                └── banca/
                    └── controller/
                        └── CuentaBancariaControllerTest.java
```

## Configuración
### application.properties
El archivo `application.properties` incluye configuraciones para la aplicación, como la URL de la base de datos, las credenciales, y otros parámetros importantes. Asegúrate de configurar correctamente estos valores antes de ejecutar la aplicación.

```properties
spring.application.name=banca
spring.jpa.database=POSTGRESQL
spring.datasource.platform=postgres
spring.datasource.url=jdbc:postgresql://<HOST>/<DATABASE>
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
#spring.jpa.hibernate.ddl-auto=create-drop
spring-jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
jwt.secret=<YOUR_SECRET_KEY>
```

## Instrucciones para Ejecutar el Proyecto
1. Clona el repositorio a tu máquina local:
    ```sh
    git clone https://github.com/william-talero/fiduciaria-banco-bogota-banca.git
    ```

2. Navega al directorio del proyecto:
    ```sh
    cd bancario
    ```

3. Configura tu base de datos PostgreSQL y actualiza el archivo `application.properties` con tus credenciales y la URL de tu base de datos.

4. Construye el proyecto usando Maven:
    ```sh
    mvn clean install
    ```

5. Ejecuta la aplicación:
    ```sh
    mvn spring-boot:run
    ```

## Pruebas
El proyecto incluye pruebas unitarias básicas. Puedes ejecutar las pruebas utilizando Maven:
```sh
mvn test
```

## Documentación
La documentación de la API está disponible a través de Swagger. Una vez que la aplicación esté en ejecución, puedes acceder a Swagger en la siguiente URL:
```
http://localhost:8080/swagger-ui.html
```

## Contribuciones
Las contribuciones son bienvenidas. Por favor, abre un issue o envía un pull request para cualquier mejora o corrección.

## Autor
William Andres Talero Cifuentes

## Licencia
Este proyecto está bajo la Licencia MIT.