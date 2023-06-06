package domain.servicios.localizacion;

import domain.comunidad.Miembro;
import domain.services.geoRef.entidades.Provincia;

import java.io.IOException;

public abstract class Localizacion {

    public abstract Object obtenerLocalizacion () throws IOException ;
}
