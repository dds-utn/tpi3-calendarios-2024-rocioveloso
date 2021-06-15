package calendarios;

import calendarios.servicios.GugleMapas;
import calendarios.servicios.PositionService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static calendarios.Pending.*;

public class Usuario {

  // TODO agregar estado y comportamiento necesario

  public void agregarCalendario(Calendario calendario) {
    pending();
  }

  public List<Evento> eventosEntreFechas(LocalDateTime inicio, LocalDateTime fin) {
    return pending();
  }

  public boolean llegaATiempoAlProximoEvento()  {
    return pending();
  }

  public boolean tieneCalendario(Calendario calendario) {
    return pending();
  }
}
