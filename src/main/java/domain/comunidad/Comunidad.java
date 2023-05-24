package domain.comunidad;

import domain.servicios.Establecimiento;

import java.util.List;

public class Comunidad {
  private String nombre;
  private String descripcion;
  private List<Miembro> miembros;
  private List<Miembro> administradores;



  public void agregarMiembro(Miembro miembro) {
    miembros.add(miembro);
  }

  public void eliminarMiembro(Miembro miembro) {
    miembros.remove(miembro);
  }

  public Boolean esAdmin(Miembro miembro) {
    return administradores.contains(miembro);
  }

  public void agregarServicio(Servicio servicio, Establecimiento establecimiento) {
    establecimiento.agregarServicio(servicio);
  }

}
