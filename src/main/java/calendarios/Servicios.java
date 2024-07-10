package calendarios;

import calendarios.servicios.GugleMapas;
import calendarios.servicios.PositionService;
import calendarios.servicios.ShemailLib;

public class Servicios {
  public PositionService posicion;
  public GugleMapas llegarEvento;
  public ShemailLib lib;

  public Servicios(PositionService posicion, GugleMapas llegarEvento, ShemailLib lib) {
    this.posicion = posicion;
    this.llegarEvento = llegarEvento;
    this.lib = lib;
  }

  public PositionService positionService() {
    return posicion;
  }

  public GugleMapas gugleMapas() {
    return llegarEvento;
  }

  public ShemailLib sheMailLib() {
    return lib;
  }

}
