package calendarios;

import java.time.LocalDateTime;
import java.util.List;

import static calendarios.Pending.pending;

public class Calendario {

  public void agendar(Evento evento) {
    pending();
  }

  public boolean estaAgendado(Evento evento) {
    return pending();
  }

  public List<Evento> eventosEntreFechas(LocalDateTime initio, LocalDateTime fin) {
    return pending();
  }

  public List<Evento> eventosSolapadosCon(Evento evento) {
    return pending();
  }
}
