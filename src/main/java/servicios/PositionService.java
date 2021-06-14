package calendarios.servicios;

import calendarios.usuario.Ubicacion;
import calendarios.usuario.Usuario;

/**
 * Obtiene la {@code Ubicacion} actual del {@code Usuario}.
 */
public interface PositionService {
    Ubicacion ubicacionActual(Usuario usuario);
}
