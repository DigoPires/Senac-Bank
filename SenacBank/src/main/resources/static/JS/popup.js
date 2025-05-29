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

function closeIfClickedOutside_Extrato(event) {
    const popupContent = document.querySelector('#extrato-popup .popup-content');
    if (!popupContent.contains(event.target)) {
        closePopup_Extrato();
    }
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

function closeIfClickedOutside_Comprovante(event) {
    const popupContent = document.querySelector('#comprovante-popup .popup-content-comprovante');
    if (!popupContent.contains(event.target)) {
        closePopup_Comprovante();
    }
}




function abrirPopupUsuarios() {
    document.getElementById('popup-usuarios').style.display = 'block';
}

function fecharPopupUsuarios() {
    document.getElementById('popup-usuarios').style.display = 'none';
}

function fecharSeClicarFora(event) {
    const popupContent = document.getElementById('popup-usuarios-content');
    if (!popupContent.contains(event.target)) {
        fecharPopupUsuarios();
    }
}




// ✅ Fechamento com tecla ESC para todos os popups

document.addEventListener('keydown', function(event) {
    if (event.key === "Escape") {
        // Fechar Extrato se visível
        const extrato = document.getElementById('extrato-popup');
        if (extrato && extrato.style.display === 'block') {
            closePopup_Extrato();
        }

        // Fechar Comprovante se visível
        const comprovante = document.getElementById('comprovante-popup');
        if (comprovante && comprovante.style.display === 'block') {
            closePopup_Comprovante();
        }

        // Fechar Usuários se visível
        const usuarios = document.getElementById('popup-usuarios');
        if (usuarios && usuarios.style.display === 'block') {
            fecharPopupUsuarios();
        }
    }
});
