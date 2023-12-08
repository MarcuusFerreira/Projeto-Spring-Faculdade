const URL_LOGIN = 'http://localhost:8080/condominium/login'

const createElement = (nameElement, classElement) => {
  let element = document.createElement(nameElement)
  for(let classHtml of classElement) {
    element.classList.add(classHtml)
  }
  return element
}

const createAlertContainer = (text) => {
  const overlay = createElement('div', ['overlay']);
  document.body.appendChild(overlay);
  const alertContainer = createElement('div', ['alert-container'])
  const alertMessage = createElement('p', ['alert-message'])
  alertMessage.textContent = text

  const closeButton = createElement('button', ['close-button']);
  closeButton.textContent = 'Fechar';
  closeButton.addEventListener('click', () => {
    document.body.removeChild(alertContainer);
    document.body.removeChild(overlay);
  })

  alertContainer.appendChild(alertMessage)
  alertContainer.appendChild(closeButton)
  document.body.appendChild(alertContainer)
}

const validateLogin = async (username, password) => {
  if (!username.trim() || !password.trim()) {
    createAlertContainer("Usuário ou senha invalidos!")
    return null
  }

  const data = {username: username, password: password}

  try {
    const response = await fetch(URL_LOGIN, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })

    console.log(response)

    if (!response.ok) {
      createAlertContainer('Erro ao realizar o login')
    }
  } catch (error) {
    console.log('ouve um erro ao enviar os dados para o servidor')
    console.log(error)
  }
} 

const login = event => {
  const usuario = document.getElementById('username').value
  const senha = document.getElementById('password').value

  if (event && event.key === 'Enter') {
    if (validateLogin(usuario, senha)) {
      window.location = '..\\html\\home.html'
    } else {
      alert('Login falhou. Verifique seu usuário e senha.')
    }
  } else if (validateLogin(usuario, senha)) {
    window.location = '..\\html\\home.html'
  } else {
    alert('Login falhou. Verifique seu usuário e senha.')
  }
}
