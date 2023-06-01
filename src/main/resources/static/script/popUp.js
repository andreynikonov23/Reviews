function openPopUp(spanElem){
    var id = spanElem.previousSibling.previousSibling.value;
    let hiddenInput = document.getElementById("id");
    console.log(spanElem.previousSibling);
    console.log("\n");
    console.log(spanElem.previousSibling.previousSibling);
    console.log("\n" + id);
    hiddenInput.value = id;
    document.querySelector(".delete-popup").style.display="flex";
}
function closePopUp(){
    document.querySelector(".delete-popup").style.display="none";
}