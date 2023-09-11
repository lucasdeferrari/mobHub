package domain.servicios;

import domain.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table
public class Servicio extends EntidadPersistente {
  @Enumerated(EnumType.STRING)
  @Column(name = "nombre")
  private TipoDeServicio nombre;
  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private Estado estado;
  @Column
  private String descripcion;

  public Servicio(TipoDeServicio nombre, Estado estado, String descripcion) {
    this.nombre = nombre;
    this.estado = estado;
    this.descripcion = descripcion;
  }

  public boolean estaDenegado() {
    return estado == Estado.DENEGADO;
  }

  public void denegar() {
    setEstado(Estado.DENEGADO);
  }

  public void disponible() {setEstado(Estado.DISPONIBLE);}


}
