package Domain;

import java.util.List;

public class Comunidad {
  private String nombre;
  private String descripcion;
  private List<Miembro> miembros;
  private List<Miembro> administradores;
  private CategoriaServicio categoria;


  public void agregarMiembro(Miembro miembro) {
    miembros.add(miembro);
  }

  public void eliminarMiembro(Miembro miembro) {
    miembros.remove(miembro);
  }

  public Boolean esAdmin(Miembro miembro) {
    return administradores.contains(miembro);
  }

  public void agregarCategoriaServicio(String nombre) {
  categoria = new CategoriaServicio(nombre);
  //TODO
  }

}
