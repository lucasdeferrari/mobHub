package domain.comunidad;


import java.util.List;

public class Miembro {
  private String nombre;
  private String apellido;
  private String correoElectronico;
  private List<Comunidad> comunidadesPertenecientes;

  public void unirseAComunidad(Comunidad comunidad) {
    comunidad.agregarMiembro(this);
  }

  public void irseDeComunidad(Comunidad comunidad) {
    comunidad.eliminarMiembro(this);
  }

  public Boolean esAdminEn(Comunidad comunidad) {
    return comunidad.esAdmin(this);
  }
}
