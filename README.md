## Prueba Técnica: Microservicio de Reservas "VIP10"

### Objetivo

Tienes que implementar el corazón de nuestro nuevo servicio de salas "VIP10". Tu objetivo es desarrollar un microservicio en **Java y Spring Boot** que gestione el sistema de reservas para estas exclusivas salas de aeropuerto.

La característica fundamental de estas salas es su aforo: **nunca pueden superar las 10 personas de forma simultánea**. Además, solo se permite reservar 1 hora, y esta debe coincidir con una hora en punto (ej: 18h).

### Funcionalidad Requerida

El microservicio deberá exponer una **API REST** que ofrezca estrictamente las siguientes tres operaciones:

1.  **Consultar Disponibilidad:** Verificar si hay plazas disponibles para una sala.
2.  **Crear Reserva:** Registrar una reserva para un pasajero en una sala para un día y hora determinado.
3.  **Cancelar Reserva:** Marcar una reserva existente como cancelada (no debe eliminarse de la base de datos).

### Reglas de Negocio Clave

La lógica de tu servicio debe cumplir con las siguientes reglas de negocio:

* **Aforo Máximo (10 personas):** El sistema debe impedir cualquier reserva que provoque que se supere el límite de 10 personas en la sala durante cualquier hora solicitada.
* **Bloques de una hora, en punto:** Recuerda que, por simplicidad, las salas solo se pueden reservar 1 hora, y estas coinciden con las horas en punto.
* **Disponibilidad Total:** El servicio debe estar operativo y permitir reservas las 24 horas del día, todos los días del año.
* **Reservas por Pasajero:** Un mismo pasajero no puede tener más de dos reservas activas (pendientes de disfrutar) en el sistema.

### Simplificaciones

Para que puedas centrarte en la lógica de negocio de las reservas, y que esta sea lo más simple posible, ten en cuenta estas consideraciones:

* **Entidades Externas:** No necesitas gestionar las entidades de Salas ni Pasajeros (creación, listado, etc.). Asume que cualquier identificador para Sala o Pasajero que reciba tu API es válido y existe en otros microservicios.
* **Base de Datos:** Utiliza la base de datos en memoria (`H2`) que ya encontrarás preconfigurada en el proyecto base.

### Requerimientos Adicionales

* **Libertad de Diseño**: Tienes total libertad para definir la arquitectura y el modelo de datos que consideres más apropiado. Lo importante será tener una separación clara de las capas y sus responsabilidades, y que el código sea de calidad. Puedes usar la versión de Java y Spring que prefieras, así como las utilerías que consideres.
* **Libertad de Versiones**: Puedes cambiar la versión de Java y de Spring del `pom.xml`, así como incluir las librerías que consideres necesarias.
* **Documentación**: Añade al archivo NOTAS.md las decisiones de diseño que vayas tomando, así como las asunciones realizadas, y cualquier otra cosa que consideres de interés. Ante la duda, toma la iniciativa, actúa de forma proactiva y documéntalo en el fichero de `NOTAS.md`.
* **Historial de Commits**: Es importante que se desarrolle iterativamente, haciendo commits pequeños e incrementales, aunque no compilen, que muestren cómo te has aproximado a la solución.
* **Testing**: Se valorará muy positivamente la implementación de los tests necesarios.
* **(Opcional / Extra) Definición de Tarea para Despliegue en AWS:** Si estás familiarizado, y de manera opcional, crea la Task Definition para Amazon ECS. No es necesario que sea funcional, sino que demuestre tu comprensión de los conceptos.

## Pasos para Empezar

Para comenzar a trabajar, sigue estas instrucciones. Primero, **acepta la invitación** que has debido recibir por correo electrónico para tener acceso de escritura en este repositorio.

**1. Clona el Repositorio**

Una vez aceptada la invitación, clona el repositorio en tu máquina local.

```bash
git clone https://github.com/URL-DEL-REPOSITORIO/nombre-del-repositorio.git
```

**2. Entra en el Directorio del Proyecto**

Navega hasta la carpeta que acabas de crear.

```bash
cd nombre-del-repositorio
```

**3. Crea tu Rama de Trabajo**

Es **muy importante** que no trabajes directamente sobre la rama `main`. Crea una nueva rama para desarrollar tu solución de manera iterativa.

```bash
git checkout -b nombre-rama
```

Ya puedes abrir el proyecto en tu IDE favorito y empezar a trabajar en tu rama. Recuerda **hacer `commits` pequeños y frecuentes** para que podamos ver tu progreso.

## Entrega

Ve _pusheando_ el progreso a medida que vayas desarrollando. No pasa nada si son soluciones parciales que no compilen; nos gustará ver cómo te fuiste aproximando a la solución. 

Un vez termines, o si te quedas sin tiempo, deja puesto un pull request de tu rama contra la rama `main` con lo que lleves.

¡Mucha suerte!
