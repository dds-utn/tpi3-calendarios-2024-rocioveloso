package calendarios.servicios;

import calendarios.usuario.Ubicacion;

import java.time.Duration;

public interface GugleMapas {
    Duration tiempoEstimadoHasta(Ubicacion partida, Ubicacion llegada);
}
