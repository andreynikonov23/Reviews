let image = document.querySelector('.selected-avatar');

function load(input){
    let file = input.files[0];
    let reader = new FileReader();
    reader.readAsDataURL(file);

    reader.onload = function () {
        console.log(reader.result);
        image.src = reader.result;
    }
}

function searchImage(){
    let url = document.getElementById('poster').value;
    image.src = url;
}