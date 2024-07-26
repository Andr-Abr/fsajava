document.addEventListener('DOMContentLoaded', function() {
    const searchForm = document.getElementById('searchForm');

    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            e.preventDefault();
            buscarVehiculos();
        });
    }

    function buscarVehiculos(page = 0) {
        const formData = new FormData(searchForm);
        formData.append('page', page);

        const url = '/vehiculos/resultados?' + new URLSearchParams(formData);
        window.location.href = url;
    }

    document.getElementById('marca').addEventListener('change', function() {
        const marca = this.value;
        fetch(`/vehiculos/modelos?marca=${marca}`)
            .then(response => response.json())
            .then(data => {
                const modeloSelect = document.getElementById('modelo');
                modeloSelect.innerHTML = '<option value="">Todos los modelos</option>';
                data.forEach(modelo => {
                    modeloSelect.innerHTML += `<option value="${modelo}">${modelo}</option>`;
                });
            });
        
        fetch(`/vehiculos/anios?marca=${marca}`)
            .then(response => response.json())
            .then(data => {
                const anioSelect = document.getElementById('anio');
                anioSelect.innerHTML = '<option value="">Todos los años</option>';
                data.forEach(anio => {
                    anioSelect.innerHTML += `<option value="${anio}">${anio}</option>`;
                });
            });
    });
});