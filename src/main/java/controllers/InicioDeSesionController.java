package controllers;

import domain.Repositorios.Usuario.RepositorioDeUsuarios;
import domain.entidades.signin.ControladorDeEstrategiaValidacion;
import domain.entidades.signin.Usuario;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.Map;

public class InicioDeSesionController implements ICrudViewsHandler {
    private RepositorioDeUsuarios repoUsuarios;

    public InicioDeSesionController(RepositorioDeUsuarios repoUsuarios) {
        this.repoUsuarios = repoUsuarios;
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

        user.setNombre(context.formParam("nombre"));
        String contrasenia = context.formParam("contrasena");
        user.setApellido(context.formParam("apellido"));
        user.setEmail(context.formParam("email"));
        user.setNombreUsuario(context.formParam("usuario"));
        String confPassword = context.formParam("confirmar-contrasena");

        ControladorDeEstrategiaValidacion controller = new ControladorDeEstrategiaValidacion();

        if (controller.verificarContrasenia(contrasenia)) {
            if (contrasenia.equals(confPassword)) {
                repoUsuarios.guardar(user);
            } else {
                context.result("La contraseña y la confirmación no coinciden");
            }
        } else {
            context.result("La contraseña no es válida");
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
        Usuario usuario= this.repoUsuarios.buscarPorNombre(username);

        if (usuario.getContrasenia().equals(password)) {
            // Autenticación exitosa, establecer una sesión
            context.sessionAttribute("username", username);
            context.result("Inicio de sesión exitoso, redireccionando a la página principal.");
            context.redirect("/inicio");
        } else {
            // Autenticación fallida, mostrar un mensaje de error
            context.result("Inicio de sesión fallido. Por favor, verifica tus credenciales.");
        }
    }

    public void vista(Context context){
        context.render("/CrearCuenta.hbs");
    }
}
