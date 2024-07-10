package calendarios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class Usuario {
  private String mail;
  public List<Calendario> calendarios = new ArrayList<>();
  public Servicios servicios;


  public Usuario(String mail, Servicios servicios) {
    this.mail = mail;
    this.servicios = servicios;
  }

  public void agregarCalendario(Calendario calendario) {

    calendarios.add(calendario);
  }

  public String getMail() {

    return mail;
  }


  public List<Evento> eventosEntreFechas(LocalDateTime inicio, LocalDateTime fin) {

    List<Evento> eventosDifCalendarios = new ArrayList<Evento>();

    for (Calendario calendario : calendarios) {
      eventosDifCalendarios.addAll(calendario.eventosEntreFechas(inicio, fin));
    }

    return eventosDifCalendarios;
  }

  public boolean llegaAtiempoAlProximoEvento()  {

    Evento proximoEvento = calendarios.stream().map(Calendario::proximoEvento)
        .min(Comparator.comparing(Evento::cuantoFalta)).orElse(null);
    if (proximoEvento == null) {
      return true;
    }

    Ubicacion usuario = servicios.positionService().ubicacionActual(this.mail);
    Ubicacion even = proximoEvento.getUbicacion();

    Duration distancia = servicios.gugleMapas().tiempoEstimadoHasta(usuario, even);

    return distancia.compareTo(proximoEvento.cuantoFalta()) <= 0;
  }

  public boolean tieneCalendario(Calendario calendario) {

    return calendarios.contains(calendario);
  }

}
