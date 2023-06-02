function openDeletePopUp(spanElem){
    var id = spanElem.previousSibling.previousSibling.value;
    let hiddenInput = document.getElementById("id");
    hiddenInput.value = id;
    document.querySelector(".delete-popup").style.display="flex";
}
function closePopUp(){
    document.querySelector(".delete-popup").style.display="none";
}