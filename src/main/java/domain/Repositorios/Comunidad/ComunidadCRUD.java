package domain.Repositorios.Comunidad;

import domain.comunidad.Comunidad;
import io.github.flbulgarelli.jpa.extras.simple.WithSimplePersistenceUnit;


import java.util.List;

public interface ComunidadCRUD extends WithSimplePersistenceUnit {
    Comunidad guardar(Comunidad comunidad);
    Comunidad buscarPorId(Long id);
    List<Comunidad> buscarTodos();
    void actualizar(Comunidad comunidad);
    void eliminar(Long id);
}
