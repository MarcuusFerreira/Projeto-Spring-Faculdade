const mostrarReserva = (ambiente) => {
    document.getElementById('ambiente-selecionado').innerText = ambiente;
    document.getElementById('reserva-container').style.display = 'block';
}

document.getElementById('reserva-form').addEventListener('submit', function(event) {
    event.preventDefault();
    alert('Reserva realizada com sucesso!');
});