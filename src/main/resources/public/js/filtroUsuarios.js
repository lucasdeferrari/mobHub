document.addEventListener('DOMContentLoaded', function() {
    const usuarios = []; // Aquí debes llenar la lista de usuarios

    function cargarUsuarios(usuariosMostrados) {
        const tablaUsuarios = document.getElementById('tabla-usuarios');
        tablaUsuarios.innerHTML = '';
        usuariosMostrados.forEach(usuario => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>
                    <select class="rol-select">
                    <option value="MIEMBRO" ${usuario.rolUsuario === 'MIEMBRO' ? 'selected' : ''}>Miembro</option>
                    <option value="ORGANISMO_DE_CONTROL" ${usuario.rolUsuario === 'ORGANISMO_DE_CONTROL' ? 'selected' : ''}>Organismo de Control</option>
                    <option value="ENTIDAD_PRESTADORA" ${usuario.rolUsuario === 'ENTIDAD_PRESTADORA' ? 'selected' : ''}>Entidad Prestadora</option>
                    <option value="ADMINISTRADOR_PLATAFORMA" ${usuario.rolUsuario === 'ADMINISTRADOR_PLATAFORMA' ? 'selected' : ''}>Administrador de Plataforma</option>

                    </select>
                </td>
                <td>
                    <select class="validado-select">
                        <option value="true" ${usuario.validado ? 'selected' : ''}>Autorizado</option>
                        <option value="false" ${!usuario.validado ? 'selected' : ''}>No Autorizado</option>
                    </select>
                </td>
            `;
            fila.setAttribute('data-id', usuario.id);
            tablaUsuarios.appendChild(fila);
        });
    }

    cargarUsuarios(usuarios);

    // Filtrar usuarios cuando se cambie la opción en el select y se presione el botón "Buscar"
    const filtroSelect = document.getElementById('filtro');
    const busquedaInput = document.getElementById('busqueda');
    const buscarButton = document.getElementById('buscar');

    buscarButton.addEventListener('click', function() {
      const filtro = filtroSelect.value;
          const busqueda = busquedaInput.value.toLowerCase();
          const validadoSelect = document.getElementById('validado');
          const esValidado = validadoSelect.value === 'true';

          const usuariosFiltrados = usuarios.filter(usuario => {
              return (
                  (filtro === 'nombre' && usuario.nombre.toLowerCase().includes(busqueda)) ||
                  (filtro === 'apellido' && usuario.apellido.toLowerCase().includes(busqueda)) ||
                  (filtro === 'rol' && usuario.rolUsuario.toLowerCase().includes(busqueda)) ||
                  (filtro === 'validado' && (esValidado || usuario.validado === esValidado))
              );
          });

          cargarUsuarios(usuariosFiltrados);
      });

    const agregarUsuarioButton = document.getElementById('agregarUsuario');
    agregarUsuarioButton.addEventListener('click', function() {
        const usuariosActualizados = Array.from(document.querySelectorAll('.rol-select, .validado-select'))
            .map(select => {
                const fila = select.parentElement.parentElement;
                const id = parseInt(fila.getAttribute('data-id'));
                const rol = fila.querySelector('.rol-select').value;
                const validado = fila.querySelector('.validado-select').value === 'true';

                return {
                    id,
                    rol,
                    validado,
                };
            });

        // Filtrar solo los usuarios que han tenido cambios
        const usuariosModificados = usuariosActualizados.filter(usuario => {
            // Aquí puedes comparar el estado actual con los valores originales para determinar cambios
            const usuarioOriginal = usuarios.find(u => u.id === usuario.id);
            return usuarioOriginal && (usuarioOriginal.rolUsuario !== usuario.rolUsuario || usuarioOriginal.validado !== usuario.validado);
        });

        // Crear el JSON de actualización con los usuarios modificados
        const jsonData = JSON.stringify({ usuariosActualizados: usuariosModificados });
        fetch('/actualizarUsuarios', {
            method: 'POST',
            body: jsonData,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log('Se mando bien', data);
        })
        .catch(error => {
            console.error('Error en la solicitud POST:', error);
        });
    });
});
