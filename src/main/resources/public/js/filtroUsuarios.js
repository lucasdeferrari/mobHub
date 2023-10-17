document.addEventListener('DOMContentLoaded', function() {
    // Supongamos que tenemos un arreglo de objetos de usuarios
    const usuarios = [
        { nombre: 'John', apellido: 'Doe', rol: 'Usuario' },
        { nombre: 'Jane', apellido: 'Smith', rol: 'Administrador' },
        { nombre: 'Bob', apellido: 'Johnson', rol: 'Usuario' }
        // Puedes agregar más usuarios aquí
    ];

    // Función para cargar usuarios en la tabla
    function cargarUsuarios() {
        const tablaUsuarios = document.getElementById('tabla-usuarios');
        usuarios.forEach(usuario => {
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
            `;
            tablaUsuarios.appendChild(fila);
        });
    }

    cargarUsuarios();

    // El resto del código para filtrar, buscar y realizar acciones en la tabla
    // Puedes seguir utilizando la lógica anterior adaptada para usuarios.

    // Asegúrate de modificar la lógica del servidor para manejar usuarios en lugar de incidentes.
});
