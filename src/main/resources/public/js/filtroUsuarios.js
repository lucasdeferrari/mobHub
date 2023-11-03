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
                <td>${usuario.email}</td>
                <td>
                    <select class="rol-select">
                        <option value="MIEMBRO">Miembro</option>
                        <option value="ORGANISMO_DE_CONTROL">Organismo de Control</option>
                        <option value="ENTIDAD_PRESTADORA">Entidad Prestadora</option>
                        <option value="ADMINISTRADOR_PLATAFORMA">Administrador de Plataforma</option>
                    </select>
                </td>
                <td>
                    <select class="validado-select">
                        <option value="true">Autorizado</option>
                        <option value="false">No Autorizado</option>
                    </select>
                </td>
            `;
            fila.setAttribute('data-id', usuario.id);
            tablaUsuarios.appendChild(fila);

            const rolSelect = fila.querySelector('.rol-select');
            const validadoSelect = fila.querySelector('.validado-select');

            // Establecer los valores iniciales de los selectores
            rolSelect.value = usuario.rolUsuario;
            validadoSelect.value = usuario.validado.toString();

            // Escuchar eventos de cambio en los selectores
            rolSelect.addEventListener('change', () => {
                // Puedes almacenar el cambio en una estructura de datos
                usuario.rolUsuario = rolSelect.value;
            });
            validadoSelect.addEventListener('change', () => {
                // Puedes almacenar el cambio en una estructura de datos
                usuario.validado = validadoSelect.value === 'true';
            });
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

        const usuariosFiltrados = usuarios.filter(usuario => {
            return (
                (filtro === 'nombre' && usuario.nombre.toLowerCase().includes(busqueda)) ||
                (filtro === 'apellido' && usuario.apellido.toLowerCase().includes(busqueda)) ||
                (filtro === 'rol' && usuario.rolUsuario.toLowerCase().includes(busqueda))
            );
        });

        cargarUsuarios(usuariosFiltrados);
    });

    const agregarUsuarioButton = document.getElementById('agregarUsuario');
    agregarUsuarioButton.addEventListener('click', function() {
        const usuariosActualizados = Array.from(document.querySelectorAll('tr')).map(fila => {
            const id = parseInt(fila.getAttribute('data-id'));
            const rol = fila.querySelector('.rol-select').value;
            const validado = fila.querySelector('.validado-select').value === 'true';

            return {
                id,
                rolUsuario: rol,
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
        fetch('/validarUsuarios', {
            method: 'POST',
            body: jsonData,
            headers: {
                'Content-Type': 'application/json'
            }
        })
            .then(response => response.json())
            .then(data => {
                console.log('Se mandó bien', data);
            })
            .catch(error => {
                console.error('Error en la solicitud POST:', error);
            });
    });
});
