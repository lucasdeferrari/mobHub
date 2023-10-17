package domain.entidades.servicios;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import domain.Persistencia.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table
public class Servicio extends EntidadPersistente {

  @Enumerated(EnumType.STRING)
  @Column(name = "tipoServicio")
  private TipoDeServicio tipoServicio;

  @Column
  @JsonProperty("nombre")
  private String nombre;

  @Enumerated(EnumType.STRING)
  @Column(name = "estado")
  private Estado estado;

  @Column
  private String descripcion;

  public Servicio(TipoDeServicio tipoServicio, Estado estado, String descripcion) {
    this.tipoServicio = tipoServicio;
    this.estado = estado;
    this.descripcion = descripcion;
  }

  public Servicio() {

  }

  public boolean estaDenegado() {
    return estado == Estado.DENEGADO;
  }

  public void denegar() {
    setEstado(Estado.DENEGADO);
  }

  public void disponible() {setEstado(Estado.DISPONIBLE);}


}
