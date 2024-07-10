package calendarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Calendario {
  public List<Evento> eventos = new ArrayList<Evento>();


  public void agendar(Evento evento) {

    eventos.add(evento);

  }

  public boolean estaAgendado(Evento evento) {

    return eventos.contains(evento);
  }

  public List<Evento> eventosEntreFechas(LocalDateTime initio, LocalDateTime fin) {

    List<Evento> eventosSolicitados = new ArrayList<Evento>();

    eventos.forEach(e -> eventosSolicitados.addAll(e.repeticionesEntreFechas(initio, fin)));
    return eventosSolicitados;

  }

  public List<Evento> eventosSolapadosCon(Evento evento) {

    return eventos.stream().filter(event -> event.estaSolapadoCon(evento)).toList();

  }

  public Evento proximoEvento() {

    return eventos.stream().min(Comparator.comparing(Evento::cuantoFalta)).orElse(null);

  }

}
