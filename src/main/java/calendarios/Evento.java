package calendarios;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Evento {

  private final String nombre;
  private  LocalDateTime inicio;
  private final LocalDateTime fin;
  public Ubicacion ubi;
  public final List<Usuario> invitados;

  private final TipoRepeticion tipo;
  private final Integer cadaCuanto;

  public List<Recordatorios> recordatorios = new ArrayList<Recordatorios>();

  public Evento(String nombre, LocalDateTime inicio, LocalDateTime fin,
                Ubicacion ubi, TipoRepeticion tipo, Integer cadaCuanto,
                List<Usuario> invitados, List<Recordatorios> recordatorios) {
    this.nombre = nombre;
    this.inicio = inicio;
    this.fin = fin;
    this.ubi = ubi;
    this.tipo = tipo;
    this.cadaCuanto = cadaCuanto;
    this.invitados = invitados;
    this.recordatorios = recordatorios;
  }

  public LocalDateTime getInicio() {

    if (this.getTipo() == TipoRepeticion.UNICA) {
      return inicio;
    }

    while (inicio.isBefore(LocalDateTime.now())) {
      inicio = this.repeticiones(inicio);
    }
    return inicio;

  }

  public LocalDateTime getFin() {
    return fin;
  }

  public List<Usuario> getInvitados() {
    return invitados;
  }

  public void agregarInvitados(Usuario invitado) {
    invitados.add(invitado);
  }

  public Ubicacion getUbicacion() {
    return ubi;
  }

  public List<Recordatorios> getRecordatorios() {
    return recordatorios;
  }

  public void agregarRecordatorio(Recordatorios recordatorio) {
    recordatorios.add(recordatorio);
  }

  public TipoRepeticion getTipo() {
    return tipo;
  }

  public String getNombre() {
    return nombre;
  }

  public Duration cuantoFalta() {
    // Este es un ejemplo de cómo se puede obtener una duración
    // Modificar en caso de que sea necesario
    return Duration.ofMinutes(LocalDateTime.now().until(getInicio(), ChronoUnit.MINUTES));
  }

  public boolean estaSolapadoCon(Evento otro) {

    if (otro.getInicio().isEqual(this.getInicio())) {
      return true;
    }
    if (otro.getInicio().isAfter(this.getInicio()) && otro.getInicio().isBefore(this.getFin())) {
      return true;
    }
    if (otro.getFin().isAfter(this.getInicio()) && otro.getFin().isBefore(this.getFin())) {
      return true;
    }
    return repeticionesEntreFechas(otro.getInicio(), otro.getFin()).size() > 0;
  }

  private LocalDateTime repeticiones(LocalDateTime fecha) {
    switch (tipo) {
      case UNICA -> {
        return fecha; }
      case DIARIA -> {
        return fecha.plusDays(cadaCuanto); }
      case SEMANAL -> {
        return fecha.plusWeeks(cadaCuanto); }
      case MENSUAL -> {
        return fecha.plusMonths(cadaCuanto); }
      case ANUAL -> {
        return fecha.plusYears(cadaCuanto); }
      default -> throw new IllegalArgumentException("No Existe Tipo");
    }
  }


  public List<Evento> repeticionesEntreFechas(LocalDateTime iniciot, LocalDateTime fint) {
    LocalDateTime fecha = this.inicio;
    List<Evento> eventosConRepes = new ArrayList<Evento>();

    while (fecha.isBefore(fint)) {
      if (fecha.isAfter(iniciot)) {
        if (fecha.equals(this.inicio)) {
          eventosConRepes.add(this);
        } else {
          eventosConRepes.add(crearRepe(fecha));
        }
      }

      if (this.getTipo() == TipoRepeticion.UNICA) {
        return eventosConRepes;
      }

      fecha = this.repeticiones(fecha);

    }
    return eventosConRepes;
  }

  private Evento crearRepe(LocalDateTime iniciot) {
    return new Evento(this.nombre, iniciot,
        iniciot.plus(Duration.between(this.inicio, this.fin)), this.ubi,
        this.tipo, this.cadaCuanto, this.invitados, this.recordatorios);
  }


}
