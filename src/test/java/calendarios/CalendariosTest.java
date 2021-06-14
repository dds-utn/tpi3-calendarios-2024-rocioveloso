package calendarios;

import calendarios.evento.Evento;
import calendarios.evento.EventoUnico;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// TODO: se recomienda
// partir esta clase entre varias para hacerla más fácil de mantener
class CalendariosTest {

  Ubicacion utnMedrano = new Ubicacion(-34.5984145,-58.4222096);


  // 1. Permitir que une usuarie tenga muchos calendarios

  @Test
  void uneUsuarieTieneMuchosCalendarios() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");
    Calendario calendario = crearCalendarioVacio();

    usuario.agregarCalendario(calendario);

    assertTrue(usuario.tieneCalendario(calendario));
  }

  // 2. Permitir que en cada calendario se agenden múltiples eventos
  // 3. Permitir que los eventos registren nombre, fecha y hora de inicio y fin, ubicación, invitades (otros usuaries)

  @Test
  void unEventoPuedeTenerMultiplesInvitades() {
    // TODO completar
    fail("Pendiente");
  }


  @Test
  void unCalendarioPermiteAgendarUnEvento() {
    Calendario calendario = new Calendario();

    Evento seguimientoDeTP = crearReunionCortaEnMedrano("Seguimiento de TP", LocalDateTime.of(2021, 10, 1, 15, 30), 30);
    calendario.agendar(seguimientoDeTP);

    assertTrue(calendario.estaAgendado(seguimientoDeTP));
  }

  @Test
  void unCalendarioPermiteAgendarDosEvento() {
    Calendario calendario = new Calendario();
    LocalDateTime inicio = LocalDateTime.of(2021, 10, 1, 15, 30);

    Evento seguimientoDeTPA = crearReunionCortaEnMedrano("Seguimiento de TPA", inicio, 30);
    Evento practicaParcial = crearReunionCortaEnMedrano("Practica para el primer parcial", inicio.plusMinutes(60), 90);

    calendario.agendar(seguimientoDeTPA);
    calendario.agendar(practicaParcial);

    assertTrue(calendario.estaAgendado(seguimientoDeTPA));
    assertTrue(calendario.estaAgendado(practicaParcial));
  }


  // 4. Permitir listar los próximos eventos entre dos fechas

  @Test
  void sePuedeListarUnEventoEntreDosFechasParaUnCalendario() {
    // Nota: Esto es opcional pero puede ayudar a resolver el siguiente item.
    // Borrar este test si no se utiliza

    Calendario calendario = new Calendario();
    Evento tpRedes = crearReunionCortaEnMedrano("TP de Redes", LocalDateTime.of(2020, 4, 3, 16, 0), 60);

    calendario.agendar(tpRedes);

    List<Evento> eventos = calendario.eventosEntreFechas(
        LocalDate.of(2020, 4, 1).atStartOfDay(),
        LocalDate.of(2020, 4, 4).atStartOfDay());

    assertEquals(eventos, Arrays.asList(tpRedes));
  }

  @Test
  void sePuedeListarUnEventoEntreDosFechasParaUneUsuarie() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");
    Calendario calendario = new Calendario();
    usuario.agregarCalendario(calendario);

    Evento tpRedes = crearReunionCortaEnMedrano("TP de Redes", LocalDateTime.of(2020, 4, 3, 16, 0), 60);

    calendario.agendar(tpRedes);

    List<Evento> eventos = usuario.eventosEntreFechas(
        LocalDate.of(2020, 4, 1).atStartOfDay(),
        LocalDate.of(2020, 4, 4).atStartOfDay());

    assertEquals(eventos, Arrays.asList(tpRedes));
  }

  @Test
  void noSeListaUnEventoSiNoEstaEntreLasFechasIndicadasParaUneUsuarie() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");
    Calendario calendario = new Calendario();
    usuario.agregarCalendario(calendario);

    Evento tpRedes = crearReunionCortaEnMedrano("TP de Redes", LocalDateTime.of(2020, 4, 3, 16, 0), 60);

    calendario.agendar(tpRedes);

    List<Evento> eventos = usuario.eventosEntreFechas(
        LocalDate.of(2020, 5, 8).atStartOfDay(),
        LocalDate.of(2020, 5, 16).atStartOfDay());

    assertTrue(eventos.isEmpty());
  }


  @Test
  void sePuedenListarMultiplesEventoEntreDosFechasParaUneUsuarieConCoincidenciaParcial() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");
    Calendario calendario = new Calendario();
    usuario.agregarCalendario(calendario);

    Evento tpRedes = crearReunionCortaEnMedrano("TP de Redes", LocalDateTime.of(2020, 4, 3, 16, 0), 60);
    Evento tpDeGestion = crearReunionCortaEnMedrano("TP de Gestión", LocalDateTime.of(2020, 4, 5, 18, 30), 60);
    Evento tpDeDds = crearReunionCortaEnMedrano("TP de DDS", LocalDateTime.of(2020, 4, 12, 16, 0), 60);

    calendario.agendar(tpRedes);
    calendario.agendar(tpDeGestion);
    calendario.agendar(tpDeDds);

    List<Evento> eventos = usuario.eventosEntreFechas(
        LocalDate.of(2020, 4, 1).atStartOfDay(),
        LocalDate.of(2020, 4, 6).atStartOfDay());

    assertEquals(eventos, Arrays.asList(tpRedes, tpDeGestion));
  }


  @Test
  void sePuedenListarMultiplesEventoEntreDosFechasParaUneUsuarieConCoincidenciaTotal() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");
    Calendario calendario = new Calendario();
    usuario.agregarCalendario(calendario);

    Evento tpRedes = crearReunionCortaEnMedrano("TP de Redes", LocalDateTime.of(2020, 4, 3, 16, 0), 60);
    Evento tpDeGestion = crearReunionCortaEnMedrano("TP de Gestión", LocalDateTime.of(2020, 4, 5, 18, 30), 60);
    Evento tpDeDds = crearReunionCortaEnMedrano("TP de DDS", LocalDateTime.of(2020, 4, 12, 16, 0), 60);

    calendario.agendar(tpRedes);
    calendario.agendar(tpDeGestion);
    calendario.agendar(tpDeDds);

    List<Evento> eventos = usuario.eventosEntreFechas(
        LocalDate.of(2020, 4, 1).atStartOfDay(),
        LocalDateTime.of(2020, 4, 12, 21, 0));

    assertEquals(eventos, Arrays.asList(tpRedes, tpDeGestion, tpDeDds));
  }

  @Test
  void sePuedenListarEventosDeMultiplesCalendarios() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");

    Calendario calendarioFacultad = new Calendario();
    usuario.agregarCalendario(calendarioFacultad);

    Calendario calendarioLaboral = new Calendario();
    usuario.agregarCalendario(calendarioLaboral);

    // TODO completar
    // Agregar eventos a los dos calendarios y asegurarse de que eventosEntreFechas retorne a todos ellos
    fail("Pendiente");
  }


  // 5. Permitir saber cuánto falta para un cierto calendarios.evento (por ejemplo, 15 horas)

  @Test
  void unEventoSabeCuantoFalta() {
    LocalDateTime inicio = LocalDateTime.now().plusDays(60);
    Evento parcialDds = crearReunionCortaEnMedrano("Parcial DDS", inicio, 60);

    assertTrue(parcialDds.cuantoFalta().compareTo(Duration.of(60, ChronoUnit.DAYS)) <= 0);
    assertTrue(parcialDds.cuantoFalta().compareTo(Duration.of(59, ChronoUnit.DAYS)) >= 0);
  }

  // 7. Permitir agendar eventos con repeticiones, con una frecuencia diaria, semanal, mensual o anual

  @Test
  void sePuedenAgendarYListarEventosRecurrrentes() {
    Usuario usuario = crearUsuario("rene@gugle.com.ar");

    // TODO completar
    // Agregar uno evento recurrente que se repita los martes a las 19 y dure 45 minutos,
    // y por tanto deberá aparecer dos veces entre el lunes 14 a las 9 y el lunes 28 a las 21

    List<Evento> eventos = usuario.eventosEntreFechas(
        LocalDateTime.of(2020, 9, 14, 9, 0),
        LocalDateTime.of(2020, 9, 28, 21, 0));

    assertEquals(eventos.size(), 2);
  }

  @Test
  void unEventoRecurrenteSabeCuantoFaltaParaSuProximaRepeticion() {
    // TODO completar
    // crear un evento recurrente que se repita, a partir de hoy, cada 15 días, y arranque una hora antes de la hora actual

    assertTrue(parcialDds.cuantoFalta().compareTo(Duration.of(15, ChronoUnit.DAYS)) <= 0);
    assertTrue(parcialDds.cuantoFalta().compareTo(Duration.of(14, ChronoUnit.DAYS)) >= 0);
  }


  // 6. Permitir saber si un calendarios.evento está solapado, y en tal caso, con qué otros eventos
  // 9. Permitir asignarle a un calendarios.evento varios recordatorios, que se enviarán cuando falte un cierto tiempo

  @Test
  void llegaATiempo() {
  }

  @Test
  void proximoEvento() {
  }

  // 8. Permitir saber si le usuarie llega al calendarios.evento más próximo a tiempo, tomando en cuenta la ubicación actual de le usuarie y destino.


  @Test
  void llegaATiempoAlProximoEvento() {
  }

  /**
   * @return une usuarie con el mail dado
   */
  Usuario crearUsuario(String email) {
    // TODO completar
    return new Usuario(email);
  }
  /*
   * @return Un calendario sin ningún evento agendado aún
   */

  Calendario crearCalendarioVacio() {
    // TODO completar
    return new Calendario();
  }

  /**
   * @return un evento sin invtades que no se repite, que tenga el nombre, fecha de inicio y fin, ubicación dados
   */
  Evento crearEventoSolitarioSimple(String nombre, LocalDateTime inicio, LocalDateTime fin, Ubicacion ubicacion) {
    // TODO completar
    return new EventoUnico(nombre, inicio, fin, ubicacion, Collections.emptyList());
  }


  Evento crearReunionCortaEnMedrano(String nombre, LocalDateTime inicio, int duracionEnMinutos) {
    return crearEventoSolitarioSimple("Seguimiento de TPA", inicio, inicio.plusMinutes(duracionEnMinutos), utnMedrano);
  }
}