package calendarios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static calendarios.Pending.*;
public class Evento {

  // TODO implementar estado, comportamiento y/o polimorfismo según sea neceario

  private LocalDateTime getInicio() {
    return pending();
  }
  public Duration cuantoFalta() {
    // TODO: Ejemplo de cómo se puede obtener una duración
    return Duration.ofHours(LocalDateTime.now().until(getInicio(), ChronoUnit.HOURS));
  }

  public boolean estaSolapadoCon(Evento otro) {
    return pending();
  }
}
