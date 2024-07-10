package calendarios;

import java.time.Duration;

public class Recordatorios {
  Duration tiempoArecordar;

  public Recordatorios(Duration tiempoArecordar) {
    this.tiempoArecordar = tiempoArecordar;
  }

  public Duration getTiempo() {
    return tiempoArecordar;
  }

}