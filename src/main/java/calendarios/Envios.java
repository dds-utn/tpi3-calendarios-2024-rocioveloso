package calendarios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Envios {
  public List<Usuario> usuarios = new ArrayList<Usuario>();
  public Servicios servicios;

  public Envios(Servicios servicios) {
    this.servicios = servicios;
  }

  public void enviarReacordatorios() {
    usuarios.forEach(user -> {
      user.calendarios.forEach(calendar -> {
        calendar.eventos.forEach(event -> {
          event.getRecordatorios().forEach(record -> {
            if (esTiempoDeRecordar(event, record)) {
              this.sendMail(user, event, record);
            }
          });
        });
      });
    });
  }

  private boolean esTiempoDeRecordar(Evento event, Recordatorios record) {
    return event.getInicio().isBefore(LocalDateTime.now().plus(record.getTiempo()));
  }

  public void sendMail(Usuario user, Evento event, Recordatorios record) {
    servicios.sheMailLib().enviarMailA(user.getMail(), event.getNombre(),
        "Evento " + event.getNombre() + " serà dentro de "
            + record.getTiempo().toMinutes() + " minutos");
  }

  public void agregarUsuario(Usuario usuario) {
    usuarios.add(usuario);
  }
}
