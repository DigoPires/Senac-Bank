const tipoContribuicao = document.getElementById('tipoContribuicao');

tipoContribuicao.addEventListener('change', function() {
    if (this.value === "Adicionar" || this.value === "Retirar") {
        this.style.fontWeight = 'bold';
    } else {
        this.style.fontWeight = 'normal';
    }
});
