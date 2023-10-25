document.addEventListener('DOMContentLoaded', function() {

    function cargarUsuarios(usuariosMostrados) {
        const tablaUsuarios = document.getElementById('tabla-usuarios');
        tablaUsuarios.innerHTML = ''; // Limpiamos la tabla antes de cargar los usuarios
        usuariosMostrados.forEach(usuario => {
            const fila = document.createElement('tr');
            fila.innerHTML = `
                <td>${usuario.nombre}</td>
                <td>${usuario.apellido}</td>
                <td>
                    <select>
                        <option value="Usuario" ${usuario.rol === 'Usuario' ? 'selected' : ''}>Usuario</option>
                        <option value="Administrador" ${usuario.rol === 'Administrador' ? 'selected' : ''}>Administrador</option>
                    </select>
                 </td>
                <td>${usuario.validado ? 'Sí' : '<input type="checkbox" class="validado" data-id="' + usuario.id + '">'}</td>
            `;
            tablaUsuarios.appendChild(fila);
        });
    }

    cargarUsuarios(usuarios);

    // Filtrar usuarios cuando se cambie la opción en el select y se presione el botón "Buscar"
    const filtroSelect = document.getElementById('filtro');
    const busquedaInput = document.getElementById('busqueda');
    const buscarButton = document.getElementById('buscar');
    const validadoCheckbox = document.getElementById('validado');

    buscarButton.addEventListener('click', function() {
        const filtro = filtroSelect.value;
        const busqueda = busquedaInput.value.toLowerCase();
        const esValidado = validadoCheckbox.checked;

        const usuariosFiltrados = usuarios.filter(usuario => {
            return (
                (filtro === 'nombre' && usuario.nombre.toLowerCase().includes(busqueda)) ||
                (filtro === 'apellido' && usuario.apellido.toLowerCase().includes(busqueda)) ||
                (filtro === 'rol' && usuario.rol.toLowerCase().includes(busqueda)) ||
                (filtro === 'validado' && usuario.validado === esValidado)
            );
        });

        // Actualiza la tabla con los usuarios filtrados
        cargarUsuarios(usuariosFiltrados);
    });

    const agregarUsuarioButton = document.getElementById('agregarUsuario');
    agregarUsuarioButton.addEventListener('click', function() {
        const usuariosValidados = Array.from(document.querySelectorAll('.validado:checked'))
            .map(checkbox => parseInt(checkbox.getAttribute('data-id')));

        // Realiza una solicitud POST con el JSON que contiene los ID de los usuarios validados
        const jsonData = JSON.stringify({ usuariosValidados });
        fetch('/validarUsuarios', {
            method: 'POST',
            body: jsonData,
            headers: {
                'Content-Type': 'application/json'
            }
        })
        .then(response => response.json())
        .then(data => {
            console.log('Respuesta del servidor:', data);
            // Puedes manejar la respuesta del servidor aquí
        })
        .catch(error => {
            console.error('Error en la solicitud POST:', error);
        });
    });
});
