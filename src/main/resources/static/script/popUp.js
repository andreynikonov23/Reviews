//Открыть всплывающее окно
function openDeleteReviewPopUp(spanElem){
    var id = spanElem.previousSibling.previousSibling.value;
    let idReviewInput = document.getElementById("id");
    idReviewInput.value = id;
    document.querySelector(".delete-popup").style.display="flex";
}
//Закрыть всплывающее окно
function closeReviewPopUp(){
    document.querySelector(".delete-popup").style.display="none";
}