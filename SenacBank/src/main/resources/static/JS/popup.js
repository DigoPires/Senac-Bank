function openPopup_Extrato() {
    const extratoLista = document.querySelector('.extrato-lista');
    const mensagemVazia = document.querySelector('.extrato-vazio');

    if (!extratoLista) {
        if (mensagemVazia) mensagemVazia.style.display = 'block';
    } else {
        if (mensagemVazia) mensagemVazia.style.display = 'none';
        extratoLista.style.display = 'block';
    }

    document.getElementById('extrato-popup').style.display = 'block';
}



function closePopup_Extrato() {
    document.getElementById('extrato-popup').style.display = 'none';
}

function openPopup_Comprovante(tipo, valor, participante, dataHora, id) {
    
    document.getElementById("tipo-comprovante").textContent = tipo;
    document.getElementById("valor-comprovante").textContent = "Valor: " + "R$ " + valor;
    document.getElementById("participante-comprovante").textContent = "Nome: " + participante;
    document.getElementById("datahora-comprovante").textContent =  "Data e Hora: " + dataHora;
    document.getElementById("id-comprovante").textContent = "Id do Comprovante: " + id;
    document.getElementById("comprovante-popup").style.display = "block";
    closePopup_Extrato();
}

function closePopup_Comprovante() {
    document.getElementById('comprovante-popup').style.display = 'none';
    openPopup_Extrato();
}

function abrirPopupUsuarios() {
    document.getElementById('popup-usuarios').style.display = 'block';
}

function fecharPopupUsuarios() {
    document.getElementById('popup-usuarios').style.display = 'none';
}

