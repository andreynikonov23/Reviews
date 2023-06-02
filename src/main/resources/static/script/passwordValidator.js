function passwordValidator(){
    let password = document.getElementById("password");
    let repeatPassword = document.getElementById("password-repeat");
    let button = document.querySelector(".block-btn");

    if(password.value === repeatPassword.value){
        button.disabled = false;
    } else{
        button.disabled = true;
    }
}