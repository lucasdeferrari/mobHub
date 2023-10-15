document.addEventListener("DOMContentLoaded", function () {
    const filtroSelect = document.getElementById("filtro");
    const busquedaInput = document.getElementById("busqueda");
    const buscarButton = document.getElementById("buscar");
    const table = document.querySelector("table");

    // Ejemplo de datos de incidentes
    const incidentes = [
        { nombre: "Incidente 1", servicio: "Servicio A", establecimiento: "Establecimiento 1", comunidad: "Comunidad X" },
        { nombre: "Incidente 2", servicio: "Servicio B", establecimiento: "Establecimiento 2", comunidad: "Comunidad Y" },
        { nombre: "Incidente 3", servicio: "Servicio A", establecimiento: "Establecimiento 3", comunidad: "Comunidad X" },
        { nombre: "Incidente 4", servicio: "Servicio C", establecimiento: "Establecimiento 4", comunidad: "Comunidad Z" },
    ];

    // Función para mostrar incidentes en la tabla
    function mostrarIncidentes(incidentes) {
        // Crear la fila de encabezado
        const tableHead = document.createElement("thead");
        const headerRow = document.createElement("tr");
        headerRow.innerHTML = `<th>Nombre</th>
                             <th>Servicio</th>
                             <th>Establecimiento</th>
                             <th>Comunidad</th>
                             <th>Cerrar Incidente</th>`; // Agregar la nueva columna
        tableHead.appendChild(headerRow);

        // Crear el cuerpo de la tabla
        const tableBody = document.createElement("tbody");
        incidentes.forEach((incidente) => {
            const row = document.createElement("tr");
            row.innerHTML = `<td>${incidente.nombre}</td>
                             <td>${incidente.servicio}</td>
                             <td>${incidente.establecimiento}</td>
                             <td>${incidente.comunidad}</td>
                             <td><input type="checkbox"></td>`; // Agregar la casilla de verificación
            tableBody.appendChild(row);
        });

        // Limpiar contenido anterior de la tabla
        table.innerHTML = "";

        // Agregar encabezado y cuerpo a la tabla
        table.appendChild(tableHead);
        table.appendChild(tableBody);
    }

    // Función para filtrar incidentes en función de la búsqueda y el filtro
    function filtrarIncidentes() {
        const filtro = filtroSelect.value.toLowerCase();
        const busqueda = busquedaInput.value.toLowerCase();
        const incidentesFiltrados = incidentes.filter((incidente) => {
            return (
                incidente[filtro].toLowerCase().includes(busqueda) ||
                busqueda === ""
            );
        });
        mostrarIncidentes(incidentesFiltrados);
    }

    // Evento de clic en el botón de búsqueda
    buscarButton.addEventListener("click", function (e) {
        e.preventDefault();
        filtrarIncidentes();
    });

    // Mostrar todos los incidentes al cargar la página
    mostrarIncidentes(incidentes);
});
