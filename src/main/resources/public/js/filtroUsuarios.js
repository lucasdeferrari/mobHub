document.addEventListener("DOMContentLoaded", function () {
    const filtroSelect = document.getElementById("filtro");
    const busquedaInput = document.getElementById("busqueda");
    const buscarBtn = document.getElementById("buscar");
    const rolSelects = document.querySelectorAll(".rol-select");
    const validadoSelects = document.querySelectorAll(".validado-select");
    const agregarUsuarioBtn = document.getElementById("agregarUsuario");

    buscarBtn.addEventListener("click", function () {
        const filtro = filtroSelect.value;
        const busqueda = busquedaInput.value.toLowerCase();
        const usuarios = document.querySelectorAll("#tabla-usuarios tr");

        usuarios.forEach(function (usuario) {
            const td = usuario.children[filtro];
            if (td) {
                const texto = td.textContent.toLowerCase();
                usuario.style.display = texto.includes(busqueda) ? "" : "none";
            }
        });
    });

    agregarUsuarioBtn.addEventListener("click", function () {
        const usuarios = document.querySelectorAll("#tabla-usuarios tr");
        const data = [];

            usuarios.forEach(function (usuario) {
                const id = usuario.getAttribute("data-id");
                const rolSelect = usuario.querySelector(".rol-select");
                const validadoSelect = usuario.querySelector(".validado-select");

                const nuevoRol = rolSelect.value;
                const nuevoValidado = validadoSelect.value;

                // Caso 1: Si uno es "" se manda solo el otro
                if (nuevoRol !== "" && nuevoValidado === "") {
                    data.push({
                        id: id,
                        rol: nuevoRol
                    });

                    usuario.dataset.rol = nuevoRol;
                }

                // Caso 2: Si el otro es "" se manda el otro
                else if (nuevoRol === "" && nuevoValidado !== "") {
                    data.push({
                        id: id,
                        validado: nuevoValidado
                    });

                    usuario.dataset.validado = nuevoValidado;
                }

                // Caso 3: Si los dos son "" no se manda ninguno
                // (No hacer nada en este caso)

                // Caso 4: Si los dos son distintos de "" se mandan los dos
                else if (nuevoRol !== "" && nuevoValidado !== "") {
                    data.push({
                        id: id,
                        rol: nuevoRol,
                        validado: nuevoValidado
                    });

                    usuario.dataset.rol = nuevoRol;
                    usuario.dataset.validado = nuevoValidado;
                }
            });

            // Enviar la lista de objetos al servidor solo si hay datos para enviar
            if (data.length > 0) {
                const url = "/validarUsuarios"; // Cambia esto con tu ruta correcta

                fetch(url, {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json"
                    },
                    body: JSON.stringify(data)
                })
                .then(response => response.json())
                .then(data => console.log("Respuesta del servidor:", data))
                .catch(error => console.error("Error al enviar la solicitud:", error));
            }
        });
    // Configurar opciones seleccionadas para rol y validado al cargar la página
rolSelects.forEach(function (rolSelect) {
    const rolActual = rolSelect.dataset.rol;
    seleccionarOpcion(rolSelect, rolActual);
});

validadoSelects.forEach(function (validadoSelect) {
    const validadoActual = validadoSelect.dataset.validado;
    seleccionarOpcion(validadoSelect, validadoActual);
});

    function seleccionarOpcion(select, valor) {
        Array.from(select.options).forEach(function (option) {
            option.selected = false; // Desmarcar todas las opciones
        });

        // Seleccionar la opción solo si se proporciona un valor no vacío
        if (valor !== "") {
            const optionToSelect = select.querySelector(`option[value="${valor}"]`);
            if (optionToSelect) {
                optionToSelect.selected = true;
            }
        }
    }

    // Manejar cambio en el elemento <select> con ID "filtro"
    const filtroSelectUsuarios = document.getElementById('filtro');
    const busquedaInputUsuarios = document.getElementById('busqueda');

    filtroSelectUsuarios.addEventListener('change', function() {
        filtrarUsuarios();
    });

    busquedaInputUsuarios.addEventListener('input', function() {
        filtrarUsuarios();
    });

    function filtrarUsuarios() {
        const filtroValor = filtroSelectUsuarios.value;
        const busquedaValor = busquedaInputUsuarios.value.toLowerCase();

        document.querySelectorAll('#tabla-usuarios tr').forEach(function(row) {
            const celda = row.querySelector(`td:nth-child(${getIndiceColumna(filtroValor)})`);
            if (filtroValor === '' || celda.textContent.toLowerCase().includes(busquedaValor)) {
                row.style.display = 'table-row';
            } else {
                row.style.display = 'none';
            }
        });
    }

    function getIndiceColumna(filtro) {
        switch (filtro) {
            case 'nombre':
                return 1;
            case 'apellido':
                return 2;
            case 'email':
                return 3;
            case 'rol':
                return 4;
            case 'validado':
                return 5;
            default:
                return 0;
        }
    }
});
