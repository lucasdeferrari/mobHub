package domain.servicios;

import domain.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table
public class Servicio extends EntidadPersistente {
  @OneToOne
  private TipoDeServicio nombre;
  @OneToOne
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
