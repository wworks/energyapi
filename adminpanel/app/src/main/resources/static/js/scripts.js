$(document).ready(function() {
    // Haal energiemeters op bij het laden van de pagina
    loadMeters();

    // Zoek naar energiemeters
    $('#searchForm').on('submit', function(e) {
        e.preventDefault();
        const query = $('#searchQuery').val();
        searchMeters(query);
    });

    // Voeg een nieuwe energiemeter toe
    $('#addMeterForm').on('submit', function(e) {
        e.preventDefault();
        const location = $('#location').val();
        const status = $('#status').is(':checked'); // Checkbox, true als aangevinkt, false anders
        addMeter(location, status);
    });

    // Functie om energiemeters op te halen
    function loadMeters() {
        $.ajax({
            url: '/api/energy-meters',
            type: 'GET',
            success: function(response) {
                populateTable(response);
            },
            error: function(error) {
                alert('Failed to fetch energy meters.');
            }
        });
    }

    // Functie om te zoeken naar energiemeters
    function searchMeters(query) {
        $.ajax({
            url: '/api/energy-meters/search',
            type: 'GET',
            data: { query: query },
            success: function(response) {
                populateTable(response);
            },
            error: function(error) {
                alert('Failed to search energy meters.');
            }
        });
    }

    // Functie om een nieuwe energiemeter toe te voegen
    function addMeter(location, status) {
        $.ajax({
            url: '/api/energy-meters',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                location: location,
                status: status
            }),
            success: function(response) {
                alert('Energy meter added successfully.');
                $('#addMeterForm')[0].reset();
                loadMeters();
            },
            error: function(error) {
                alert('Failed to add energy meter.');
            }
        });
    }

    // Functie om de tabel te vullen met energiemeters
    function populateTable(meters) {
        const tableBody = $('#meterTableBody');
        tableBody.empty();
        meters.forEach(function(meter) {
            const statusText = meter.status ? 'On' : 'Off';
            const row = `<tr>
                <td>${meter.id}</td>
                <td>${meter.location}</td>
                <td>${statusText}</td>
                <td>
                  <a href="/meter-details.html?id=${meter.id}" class="btn btn-info btn-sm">Details</a>
                    |
                    <button class="btn btn-danger btn-sm delete-btn" data-id="${meter.id}">Delete</button>
                </td>
            </tr>`;
            tableBody.append(row);
        });

        // Voeg verwijderfunctionaliteit toe aan de delete-knoppen
        $('.delete-btn').on('click', function() {
            const id = $(this).data('id');
            deleteMeter(id);
        });
    }

    // Functie om een energiemeter te verwijderen
    function deleteMeter(id) {
        $.ajax({
            url: `/api/energy-meters/${id}`,
            type: 'DELETE',
            success: function(response) {
                alert('Energy meter deleted successfully.');
                loadMeters();
            },
            error: function(error) {
                alert('Failed to delete energy meter.');
            }
        });
    }
});
