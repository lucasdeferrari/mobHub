package controllers;

import domain.Repositorios.EntidadPrestadora.RepositorioEntidadPrestadora;
import domain.entidades.servicios.EntidadPrestadora;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import io.javalin.http.UploadedFile;
import server.utils.ICrudViewsHandler;
import domain.entidades.LectorCSV.ImportadorDeEntidadesPrestadoras;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class EntidadesPrestadorasController implements ICrudViewsHandler {
    private RepositorioEntidadPrestadora repositorioEntidadPrestadora;

    public EntidadesPrestadorasController(RepositorioEntidadPrestadora repositorio) {
        this.repositorioEntidadPrestadora = repositorio;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<EntidadPrestadora> entidadPrestadoras = this.repositorioEntidadPrestadora.buscarTodos();
        model.put("entidadPrestadora", entidadPrestadoras);
        context.render("portalCargaDeDatos.hbs", model);
    }

    @Override
    public void show(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("entidadPrestadora", entidadPrestadora);
        context.render("portalCargaDeDatos.hbs", model);
    }

    @Override
    public void create(Context context) {
        EntidadPrestadora entidadPrestadora = null;
        Map<String, Object> model = new HashMap<>();
        model.put("entidadPrestadora", entidadPrestadora);
        context.render("entidadPrestadora.hbs", model);
    }

    @Override
    public void save(Context context) {
        UploadedFile archivo = context.uploadedFile("formData");

        if (archivo != null) {
            try {
                InputStream inputStream = archivo.content();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder contenidoCSV = new StringBuilder();
                String linea;

                while ((linea = reader.readLine()) != null) {
                    contenidoCSV.append(linea).append("\n");
                }

                ImportadorDeEntidadesPrestadoras importador = new ImportadorDeEntidadesPrestadoras();

                // Llama a tu importador para procesar y guardar los datos del archivo CSV
           //     boolean importacionExitosa = importador.importarEntidadesPrestadoras(contenidoCSV.toString());

          //      if (importacionExitosa) {
                    context.result("Archivo CSV procesado y guardado con éxito.");
            //    } else {
                    context.result("Error al procesar y guardar el archivo CSV.");
              //  }
            } catch (IOException e) {
                // Maneja cualquier error de lectura del archivo
                context.result("Error al procesar el archivo.");
            }
        } else {
            // Manejar el caso en el que no se ha subido un archivo o se ha proporcionado un nombre de campo incorrecto
            context.result("No se ha seleccionado ningún archivo CSV.");
        }
    }







    /*public void save(Context context) {
        EntidadPrestadora entidadPrestadora = new EntidadPrestadora();
        this.asignarParametros(entidadPrestadora, context);
        this.repositorioEntidadPrestadora.guardar(entidadPrestadora);
        context.status(HttpStatus.CREATED);
        context.redirect("/entidadPrestadora");
    }
*/
    @Override
    public void edit(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("entidadPrestadora", entidadPrestadora);
        context.render("entidadPrestadora/entidadPrestadora.hbs", model);

    }

    @Override
    public void update(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(entidadPrestadora, context);
        this.repositorioEntidadPrestadora.actualizar(entidadPrestadora);
        context.redirect("/entidadPrestadora");
    }

    @Override
    public void delete(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.buscarPorId(Long.parseLong(context.pathParam("id")));
        this.repositorioEntidadPrestadora.eliminar(Long.parseLong(context.pathParam("id")));
        context.redirect("/entidadPrestadora");
    }

    private void asignarParametros(EntidadPrestadora entidadPrestadora, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
           // entidadPrestadora.setNombre(context.formParam("nombre")); //TODO
        }
    }
}

