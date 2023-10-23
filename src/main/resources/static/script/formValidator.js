//Проверка паролей в поле "Пароль" и "Повторить пароль"
function passwordValidator(){
    let password = document.getElementById("password");
    let repeatPassword = document.getElementById("password-repeat");
    let button = document.querySelector(".block-btn");
    let errorBlock = document.querySelector(".hidden");

    if(password.value === repeatPassword.value && password.value !== '' && repeatPassword.value !== ''){
        button.disabled = false;
    } else{
        button.disabled = true;
    }
    if (password.value !== '' && repeatPassword.value !== ''){
        if (repeatPassword.value !== password.value){
            repeatPassword.style.marginBottom = "10px";
            errorBlock.style.display="block";
        } else {
            repeatPassword.style.marginBottom = "30px";
            errorBlock.style.display="none";
        }
    }
}

//Проверка заполнения поля Имя
function profileValidator(){
    let nick = document.getElementById('nick');
    let button = document.getElementById('edit-btn');
    let errorBlock = document.querySelector('.hidden');

    if (nick.value === ''){
        errorBlock.style.display = 'block';
        nick.style.borderColor = 'red';
        button.disabled = true;
    } else {
        errorBlock.style.display = 'none';
        nick.style.borderColor = 'black';
        button.disabled = false;

    }
}