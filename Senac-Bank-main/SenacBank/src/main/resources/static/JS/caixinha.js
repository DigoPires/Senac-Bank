let saldoCaixinha = 0;
const rendimento = 0.50; // Exemplo: R$0,50 a cada 5 segundos
const display = document.getElementById("valor-caixinha");

function atualizarDisplay() {
    display.textContent = saldoCaixinha.toFixed(2).replace('.', ',');
}  

function adicionarNaCaixinha() {
    const valorInput = document.getElementById("valorDepositoCaixinha");
    const valor = parseFloat(valorInput.value);

    if (!isNaN(valor) && valor > 0) {
        saldoCaixinha += valor;
        atualizarDisplay();
        valorInput.value = '';
    } else {
        alert("Insira um valor válido.");
    }
}

setInterval(() => {
    saldoCaixinha += rendimento;
    atualizarDisplay();
}, 5000); // A cada 5 segundos