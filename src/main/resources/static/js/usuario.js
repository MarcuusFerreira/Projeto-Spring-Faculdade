function validateForm() {
    var cpfRegex = /\d{3}\.\d{3}\.\d{3}-\d{2}/;
    var telefoneRegex = /\(\d{2}\) \d{4,5}-\d{4}/;

    var nome = document.getElementById('nome').value;
    var dataNascimento = document.getElementById('dataNascimento').value;
    var email = document.getElementById('email').value;
    var cpf = document.getElementById('cpf').value;
    var telefone = document.getElementById('telefone').value;
    var senha = document.getElementById('senha').value;

    var errorMessage = document.getElementById('error-message');
    errorMessage.innerHTML = '';

    if (!cpfRegex.test(cpf)) {
      errorMessage.innerHTML += 'CPF inválido<br>';
    }

    if (!telefoneRegex.test(telefone)) {
      errorMessage.innerHTML += 'Telefone inválido<br>';
    }

    // Adicione mais validações conforme necessário

    if (errorMessage.innerHTML === '') {
      alert('Formulário válido!'); // Aqui você pode enviar o formulário ou realizar outra ação desejada
    }
  }