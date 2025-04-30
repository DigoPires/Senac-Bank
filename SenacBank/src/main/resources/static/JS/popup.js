function openPopup_Extrato() {
    document.getElementById('extrato-popup').style.display = 'block';
}

function closePopup_Extrato() {
    document.getElementById('extrato-popup').style.display = 'none';
}

function openPopup_Comprovante() {
    document.getElementById('comprovante-popup').style.display = 'block';
    closePopup_Extrato();
}

function closePopup_Comprovante() {
    document.getElementById('comprovante-popup').style.display = 'none';
    openPopup_Extrato();
}