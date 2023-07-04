function openDeleteReviewPopUp(spanElem){
    var id = spanElem.previousSibling.previousSibling.value;
    let hiddenInput = document.getElementById("id");
    hiddenInput.value = id;
    document.querySelector(".delete-popup").style.display="flex";
}
function closeReviewPopUp(){
    document.querySelector(".delete-popup").style.display="none";
}