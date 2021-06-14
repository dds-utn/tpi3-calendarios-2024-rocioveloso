# java-base-project


Este es el código base para el ejercicio de [Calendarios](https://docs.google.com/document/d/1Pm4gIDMORKmK3SXxTlanO_k-EzwD8H_79U7YZ4S1xbc/edit). Está diseñado para:

* Java 8. :warning: Si bien el proyecto no lo limita explícitamente, el comando `mvn verify` no funcionará con versiones mas modernas de Java.
* JUnit 5. :warning: La versión 5 de JUnit es la más nueva del framework y presenta algunas diferencias respecto a la versión "clásica" (JUnit 4). Para mayores detalles, ver:
  *  [Apunte de herramientas](https://docs.google.com/document/d/1VYBey56M0UU6C0689hAClAvF9ILE6E7nKIuOqrRJnWQ/edit#heading=h.dnwhvummp994)
  *  [Entrada de Blog (en inglés)](https://www.baeldung.com/junit-5-migration)
  *  [Entrada de Blog (en español)](https://www.paradigmadigital.com/dev/nos-espera-junit-5/)
* Maven 3.3 o superior

# El enunciado

[Acá](https://docs.google.com/document/d/1Pm4gIDMORKmK3SXxTlanO_k-EzwD8H_79U7YZ4S1xbc/edit) vas a encontrar el enunciado original de este ejercicio. Leelo atentamente y tratá de resolver en orden los requerimientos que plantea:

 1. Permitir que une usuarie tenga muchos calendarios
 2. Permitir que en cada calendario se agenden múltiples eventos
 3. Permitir que los eventos registren nombre, fecha y hora de inicio y fin, ubicación, invitades (otros usuaries)
 4. Permitir listar los próximos eventos entre dos fechas (para une usuarie)
 5. Permitir saber cuánto falta para un cierto calendarios.evento (por ejemplo, 15 horas)
 6. Permitir saber si un calendarios.evento está solapado, y en tal caso, con qué otros eventos
 7. Permitir agendar eventos con repeticiones, con una frecuencia diaria, semanal, mensual o anual
 8. Permitir saber si le usuarie llega al calendarios.evento más próximo a tiempo, tomando en cuenta la ubicación actual de le usuarie y destino.
 9. Permitir asignarle a un calendarios.evento varios recordatorios, que se enviarán cuando falte un cierto tiempo


Mientras vas implementando el código que resuelve el problema, codificá casos de prueba que validen la solución. Ya te dejamos algunos para que completes y te sirvan como punto de partida, pero no son suficientes y deberás agregar los tuyos.


# Ejecutar tests

```
mvn test
```

# Validar el proyecto de forma exahustiva

```
mvn clean verify
```

Este comando hará lo siguiente:

 1. Ejecutará los tests
 2. Validará las convenciones de formato mediante checkstyle
 3. Detectará la presencia de (ciertos) code smells
 4. Validará la cobertura del proyecto

# Entrega del proyecto

Para entregar el proyecto, crear un tag llamado `entrega-final`. Es importante que antes de realizarlo se corra la validación
explicada en el punto anterior. Se recomienda hacerlo de la siguiente forma:

```
mvn clean verify && git tag entrega-final && git push origin HEAD --tags
```

# Configuración del IDE (IntelliJ)

 1. Tabular con dos espacios: ![Screenshot_2021-04-09_18-23-26](https://user-images.githubusercontent.com/677436/114242543-73e1fe00-9961-11eb-9a61-7e34be9fb8de.png)
 2. Instalar y configurar Checkstyle:
    1. Instalar el plugin https://plugins.jetbrains.com/plugin/1065-checkstyle-idea:
    2. Configurarlo activando los Checks de Google y la versión de Checkstyle `== 8.35`: ![Screenshot_2021-04-09_18-16-13](https://user-images.githubusercontent.com/677436/114242548-75132b00-9961-11eb-972e-28e6e1412979.png)
 3. Usar fin de linea unix
    1. En **Settings/Preferences**, ir a a **Editor | Code Style**.
    2. En la lista **Line separator**, seleccionar `Unix and OS X (\n)`.
 ![Screenshot 2021-04-10 03-49-00](https://user-images.githubusercontent.com/11875266/114260872-c6490c00-99ad-11eb-838f-022acc1903f4.png)
