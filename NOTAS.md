# Decisiones tomadas

Detalla aquí las decisiones y asumciones que has ido tomando durante el ejercicio

He cambiado la versión de Java a la 11 que es compatible con la versión de SpringBoot 2.2.6 que permite utilizar repositorios jpa sin tener hacer explícitas las consultas (basta con nombrar correctamente los métodos)
He añadido mapstruct para simplificar los mapeos entre las capas.

He elegido una arquitectura hexagonal para la implementación del proyecto, consta de 3 capas:

1. **Infraestructura: Aquí incluiré todas la librerias/técnologias externas de terceros, lombok, mappers con MapStructs, controladores rest, la persistencia utilizando jpa, la configuración de las beans (de ámbito Singleton) también se hará en esta capa
Esto permite cambiar cualquier de estas sin afectar a la lógica de negocio.

2. **Aplicación: Aquí incluiré los casos de uso (puertos primarios) que utilizan los objetos de negocio para implementar la lógica, estos pueden necesitar llamar a la capa de infraestructura a través de puertos secundarios.

3. **Dominio: Objetos java simples (pojos) que incluirán las validaciones y las interfaces necesarias para modelizar el negocio.

NOTA: la arquitectura se ha simplificado un poco y no es correcta del todo, los objetos de dominio no deberían estar expuestos
fuera de la capa de aplicación; ocurre que sería necesario definir las entradas y salidas de los puertos primarios como adpatadores (PrimaryPortRequest y PrimaryPortResponse por ejemplo)
Estos serían parecidos a las clases de dominio y no permitirían que se pudiera implementar lógica del dominio en la capa de infraestructura, osea
en lugar de:

    public interface PrimaryPort<I,O> {
        O execute(I input);
    }

(I y O van ser clases de dominio)

Debería ser:

    public interface PrimaryPort<I extends PrimaryPortRequest, O extends PrimaryPortResponse> {
        O execute(I input);
    }

(I y O serían clases adaptadores)

Para SecondaryPort habría que hacer lo mismo (SecondaryPortRequest y SecondaryPortResponse)

NOTA: No voy a implementar tests de los mappers; la entidad de dominio, persistencia y dto de momento tienen los mismos parámetros y tipos.
He usado enteros para los identificadores, para la prueba servirán.

Pruebas end-to-end (manuales):

curl -X POST http://localhost:8080/reserva/ -H "Content-Type: application/json" -d "{ \"idPasajero\": 1, \"idSala\": 3, \"fechaInicio\": \"2125-11-03T14:00:00\", \"estado\": \"XXX\" }"

fecha pocha
curl -X POST http://localhost:8080/reserva/ -H "Content-Type: application/json" -d "{ \"idPasajero\": 1, \"idSala\": 3, \"fechaInicio\": \"2025-11-03T14:00:01\" }"

curl -X PUT http://localhost:8080/reserva/pasajero/1 -H "Content-Type: application/json"

curl -X GET http://localhost:8080/sala/1 -H "Content-Type: application/json"

Se añaden validaciones de negocio (faltan aún), campos no nulos para fechas. Se ha empleado el patrón de diseño Método Plantilla (Template Method)
en ValidacionCampoGenerica.java.

    public final T validate(final T field) {
        if (isNoValid(field)) {
            throw buildException(name);
        }
        return field;
    }

Las validaciones hijas solo se tienen que preocupar de construir las excepciones de dominio que correspondan así como de definir como evalúan si el campo no es válido.
No se ahorra mucho código pero permite forzar a que todas tengan el mismo comportamiento, esto es, validan el atributo, si es correcto lo devuelven, si no lanzan excepción.

Se añade máquina de estados para gestionar los estados de la reserva, se ha utilizado una máquina similar (simplificada) a la que tenemos en pagos para controlar el estado del pago y el contexto.
La clase concreta MaquinaEstadosReserva solo tiene que definir en su construcción el estado inicial y las transiciones (pares origen, destino) permitidas. Para dar más contexto en caso de error
también se envía en los mensajes de error el nombre de la máquina (aunque solo es una), utilizando de nuevo el Método-Plantilla.

Se analiza lógica de negocio y se observa que no hay ningún evento para modificar el estado de una reserva, es decir siempre está en estado 'PENDIENTE' salvo por la cancelación 
a través del endpoint correspondiente, añado un proceso de escucha que salte cada hora para que consolide las reservas de manera que si siguen pendientes aún siendo en una fecha de inicio
anterior a la actual pasen a 'CANCELADAS' (por simplificar y reutilizar el caso de uso de cancelación pero lo correcto tal vez sería 'FINALIZADA')

He incluido el id de la reserva en el dominio para que no cree una nueva cada vez que se actualiza (puede ser nulo durante la creación pero no hace falta validar esto al no incluirse en el dto de entrada).
El 'Listener' salta cada 5 minutos con @Scheduled(cron = "0 */5 * * * *") pero lo correcto sería cada hora con
    
    @Scheduled(cron = "0 0 * * * *"))

NOTA FINAL:
Mejoras:
Solo se han hecho test unitarios (y uno de integración)
El estado EN_CURSO no se está usando y tampoco es procesado por el listener;
La clase de dominio ReservaDomain aunque es inmutable requiere conocer la posición de los id's que se pasan al constructor (aunque el IDE ayuda... son muchos parámetros),
se podría usar el patrón de diseño builder para evitarlo.
Lo que comenté al principio: la capa de infraestructura conoce clases del dominio (de hecho las validaciones de dominio están saltando en los mappers de MapStruct al instanciarlos)

NOTAS EVALUACIÓN
 
Debería capturarse las excepciones en la capa de infraestructura con un ExceptionHandler como en https://github.com/FabianSR/TestJava2020
La maquina de estado no se está utilizando demasiado y añade complejidad

