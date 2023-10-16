document.addEventListener('DOMContentLoaded', function() {
    // Manejar clic en el botón "Cerrar Seleccionados"
    document.getElementById('cerrarSeleccionados').addEventListener('click', function() {
        const selectedIncidents = [];
        document.querySelectorAll('input[type="checkbox"]:checked').forEach(function(checkbox) {
            const incidentId = checkbox.getAttribute('data-id');
            selectedIncidents.push(incidentId);
        });

        if (selectedIncidents.length > 0) {
            // Enviar una solicitud al servidor para cambiar el estado de los incidentes a "Cerrado"
            // Ejemplo con fetch:
            fetch('/cerrar-incidentes', {
                method: 'POST',
                body: JSON.stringify(selectedIncidents),
                headers: {
                    'Content-Type': 'application/json'
                }
            })
                .then(response => {
                    if (response.ok) {
                        // Actualizar el estado de las filas en la tabla a "Cerrado"
                        selectedIncidents.forEach(incidentId => {
                            const row = document.querySelector(`tr[data-id="${incidentId}"`);
                            if (row) {
                                row.querySelector('input[type="checkbox"]').setAttribute('disabled', true);
                                row.querySelector('input[type="checkbox"]').remove();
                                row.querySelector('span').textContent = 'Cerrado';
                            }
                        });
                    }
                });
        }
    });

    // Manejar cambio en el elemento <select> con ID "filtro"
    const filtroSelect = document.getElementById('filtro');
    const busquedaInput = document.getElementById('busqueda');

    filtroSelect.addEventListener('change', function() {
        filtrarIncidentes();
    });

    busquedaInput.addEventListener('input', function() {
        filtrarIncidentes();
    });

    function filtrarIncidentes() {
        const filtroValor = filtroSelect.value;
        const busquedaValor = busquedaInput.value.toLowerCase();

        document.querySelectorAll('tbody tr').forEach(function(row) {
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
            case 'servicio':
                return 2;
            case 'establecimiento':
                return 3;
            case 'comunidad':
                return 4;
            case 'abierto':
                return 5;
            case 'cerrado':
                return 5;
            default:
                return 0;
        }
    }
});






// document.addEventListener("DOMContentLoaded", function () {
//     // let incidentes = [
//     //     { id: 1, nombre: "Incidente 1", servicio: "Servicio A", establecimiento: "Establecimiento 1", comunidad: "Comunidad X", cerrado: false },
//     //     { id: 2, nombre: "Incidente 2", servicio: "Servicio B", establecimiento: "Establecimiento 2", comunidad: "Comunidad Y", cerrado: true },
//     //     { id: 3, nombre: "Incidente 3", servicio: "Servicio A", establecimiento: "Establecimiento 3", comunidad: "Comunidad X", cerrado: false },
//     //     { id: 4, nombre: "Incidente 4", servicio: "Servicio C", establecimiento: "Establecimiento 4", comunidad: "Comunidad Z", cerrado: true }
//     // ];
//
//     const filtroSelect = document.getElementById("filtro");
//     const busquedaInput = document.getElementById("busqueda");
//     const buscarButton = document.getElementById("buscar");
//     const cerrarSeleccionadosButton = document.getElementById("cerrarSeleccionados");
//
//     // Función para mostrar incidentes en la tabla
//     function mostrarIncidentes(incidentes) {
//         const tablaIncidentes = document.getElementById("tabla-incidentes");
//
//         while (tablaIncidentes.firstChild) {
//             tablaIncidentes.removeChild(tablaIncidentes.firstChild);
//         }
//
//         incidentes.forEach(({{incidente) => {
//             const row = document.createElement("tr");
//             row.innerHTML = `<td><a href="/incidentes/${incidente.id}">${incidente.nombre}</a></td>
//                              <td>${incidente.servicio}</td>
//                              <td>${incidente.establecimiento}</td>
//                              <td>${incidente.comunidad}</td>
//                              <td>${incidente.cerrado ? "Cerrado" : "<button class='cerrar-incidente' data-id='" + incidente.id + "'>Cerrar</button>"}</td>`;
//             tablaIncidentes.appendChild(row);
//         });
//     }
//
//     // Función para filtrar incidentes en función de la búsqueda y el filtro
//     function filtrarIncidentes() {
//         const filtro = filtroSelect.value.toLowerCase();
//         const busqueda = busquedaInput.value.toLowerCase();
//
//         let incidentesFiltrados = incidentes;
//
//         if (filtro === "abierto") {
//             incidentesFiltrados = incidentes.filter((incidente) => !incidente.cerrado);
//         } else if (filtro === "cerrado") {
//             incidentesFiltrados = incidentes.filter((incidente) => incidente.cerrado);
//         }
//
//         if (busqueda) {
//             incidentesFiltrados = incidentesFiltrados.filter((incidente) => {
//                 return (
//                     incidente.nombre.toLowerCase().includes(busqueda) ||
//                     incidente.servicio.toLowerCase().includes(busqueda) ||
//                     incidente.establecimiento.toLowerCase().includes(busqueda) ||
//                     incidente.comunidad.toLowerCase().includes(busqueda)
//                 );
//             });
//         }
//
//         mostrarIncidentes(incidentesFiltrados);
//     }
//
//     // Evento de clic en el botón de búsqueda
//     buscarButton.addEventListener("click", function (e) {
//         e.preventDefault();
//         filtrarIncidentes();
//     });
//
//     // Evento de clic en el botón "Cerrar Seleccionados"
//     cerrarSeleccionadosButton.addEventListener("click", function () {
//         const checkboxes = document.querySelectorAll("input[type='checkbox']:checked");
//
//         checkboxes.forEach((checkbox) => {
//             const id = checkbox.getAttribute("data-id");
//             const incidente = incidentes.find((i) => i.id.toString() === id);
//             if (incidente) {
//                 incidente.cerrado = true;
//             }
//         });
//
//         filtrarIncidentes();
//     });
//
//     // Mostrar todos los incidentes al cargar la página
//     mostrarIncidentes(incidentes);
// });
