package controllers;

import componentesExternos.geoRef.entidades.Localidad;
import componentesExternos.geoRef.entidades.Municipio;
import componentesExternos.geoRef.entidades.Provincia;
import domain.Repositorios.Usuario.RepositorioDeUsuarios;
import domain.entidades.comunidad.Comunidad;
import domain.entidades.servicios.Establecimiento;
import domain.entidades.servicios.Incidente;
import domain.entidades.servicios.Servicio;
import domain.entidades.signin.ControladorDeEstrategiaValidacion;
import domain.entidades.signin.EstrategiaValidacion;
import domain.entidades.signin.Usuario;
import domain.entidades.signin.estrategiasDeValidacion.*;
import io.javalin.http.Context;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import server.utils.ICrudViewsHandler;
import org.hibernate.Session;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class InicioDeSesionController implements ICrudViewsHandler {
    private RepositorioDeUsuarios repoUsuarios;

    public InicioDeSesionController(RepositorioDeUsuarios repoUsuarios) {this.repoUsuarios = repoUsuarios;
    }
    @Override
    public void index(Context context) {
        context.render("InicioSesion.hbs");
    }
    @Override
    public void show(Context context) {
        //OrganismoDeControl organismoDeControl = (OrganismoDeControl) this.repositorioOrganismoDeControl.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
       // model.put("organismoDeControl", organismoDeControl);
        context.render("organismoDeControl/organismoDeControl.hbs", model);
    }
    @Override
    public void create(Context context) {
    }

    @Override
    public void save(Context context) {
            Usuario user = new Usuario();
            String contrasenia = context.formParam("contrasena");
            String confPassword = context.formParam("confirmar-contrasena");
            ControladorDeEstrategiaValidacion controller = new ControladorDeEstrategiaValidacion();
            EstrategiaValidacion e1 = new ValidacionTieneMayuscula();
            controller.agregarEstrategia(e1); // TODO poner las otras validaciones y que funcionen
            asignarParametros(user, context);
            if (controller.verificarContrasenia(contrasenia)) {
                repoUsuarios.guardar(user);
                Usuario usuarioGuardado = repoUsuarios.buscarPorId(user.getId());
                Integer id = usuarioGuardado.getId();
                context.sessionAttribute("id", id);
                context.redirect("/inicio");
            } else {
                context.result("La contraseña no es segura");
            }

    }

    @Override
    public void edit(Context context) {
    }

    @Override
    public void update(Context context) {
    }

    @Override
    public void delete(Context context) {

    }
    public void iniciarSesion(Context context){

        String username = context.formParam("usuario");
        String password = context.formParam("contrasena");
        // Realizar la autenticación aquí (por ejemplo, verificar credenciales en una base de datos)
        Integer id = (Integer)context.sessionAttribute("id");
       // if(!(username.isEmpty()) && !(password.isEmpty())){
            Usuario usuario= this.repoUsuarios.buscarPorId(id);
            if (usuario.getContrasenia().equals(password)) {
                // Autenticación exitosa, establecer una sesión
                //context.result("Inicio de sesión exitoso, redireccionando a la página principal.");
                context.redirect("/incidentes");
            } else {
                // Autenticación fallida, mostrar un mensaje de error
                context.result("Inicio de sesion fallido. Por favor, verifica tus credenciales.");
            }
     //   }

    }


    public void vista(Context context){
        Map<String, Object> modelProvincia = new HashMap<>();
        Map<String, Object> modelDepartamento = new HashMap<>();
        Map<String, Object> modelLocalidad = new HashMap<>();
        //List<Provincia> provincias = repositorio.buscarTodos();
        //List<Municipio> departamento = repositorio.buscarTodos();
        //List<Localidad> localidades = repositorio.buscarTodos();

        //modelProvincia.put("provincias", provincias);
        //modelDepartamento.put("departamentos", departamento);
        //modelLocalidad.put("localidades", localidades);

        context.render("/CrearCuenta.hbs");
    }

    private void asignarParametros(Usuario usuario, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            usuario.setNombre(context.formParam("nombre"));
        }
        if(!Objects.equals(context.formParam("apellido"), "")) {
            usuario.setApellido(context.formParam("apellido"));
        }
        if(!Objects.equals(context.formParam("email"), "")) {
            usuario.setEmail(context.formParam("email"));
        }
        if(!Objects.equals(context.formParam("establecimiento"), "")) {
            usuario.setNombreUsuario(context.formParam("usuario"));
        }
        if(!Objects.equals(context.formParam("servicio"), "")) {
            usuario.setContrasenia(context.formParam("contrasena"));
        }
        if(!Objects.equals(context.formParam("provincia"), "")) {
            usuario.setContrasenia(context.formParam("contrasena"));
        }
        if(!Objects.equals(context.formParam("telefono"), "")) {
            usuario.setTelefono(context.formParam("telefono"));
        }
        if(!Objects.equals(context.formParam("provincia"), "")) {
         //   usuario.setProvincia(context.formParam("provincia"));
        }
        if(!Objects.equals(context.formParam("departamento"), "")) {
           // usuario.setDepartamento(context.formParam("departamento"));
        }
        if(!Objects.equals(context.formParam("localidad"), "")) {
            //usuario.setLocalidad(context.formParam("localidad"));
        }
    }
}
