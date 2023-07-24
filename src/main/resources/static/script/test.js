console.log("RatingScript start");

const rating = document.querySelector('.rating-block');
let user = getUser();
let review = getReview();
console.log(user.id);
initRating(rating);

async function getUser(){
    const userId = document.querySelector("#userId").value;
    let response = await fetch("/get-user-json", {
        method: "GET",
        headers: {
            "content-type": "application/json"
        }
    });
    const result = await response.json();
    return result;
}
async function getReview(){
    let index = window.location.href.lastIndexOf("/") + 1;
    const reviewId = window.location.href.substring(index);
    let response = await fetch("/get-review-json?id=" + reviewId,{
        method: "GET",
        headers: {
            "content-type": "application/json"
        }
    });
    const result = await response.json();
    return result;
}

function initRating(rating){
    let ratingActive = rating.querySelector('.rating-active');
    let ratingValue = rating.querySelector('.rating-value');

    setRatingActiveWidth();

    setRating(rating);

    function setRatingActiveWidth(index = ratingValue.innerHTML){
        const ratingActiveWidth = index / 0.10;
        ratingActive.style.width = ratingActiveWidth + '%';
    }

    function setRating(rating){
        const ratingItems = rating.querySelectorAll('.rating-item');
        for(let i=0; i<ratingItems.length; i++){
            const ratingItem = ratingItems[i];

            ratingItem.addEventListener("mouseenter", function(e){
                setRatingActiveWidth(ratingItem.value);
            });

            ratingItem.addEventListener("mouseleave", function(e){
                setRatingActiveWidth();
            });

            ratingItem.addEventListener("click", function(e){
                console.log("value - " + ratingValue.innerHTML);
                ratingValue.innerHTML = i + 1;

                setRatingActiveWidth();
                setRatingValue(ratingValue.innerHTML, rating);
            });
        }
    }

    async function setRatingValue(value, rating){
        if (!rating.classList.contains('rating-sending')){
            rating.classList.add('rating-sending');

            //Отправка данных на форму
            let response = await fetch("/set-rating", {
                method: "POST",

                body: JSON.stringify({
                    rating: value,
                    user: {
                        id : user.id,
                        login: user.login,
                        password: user.password,
                        nick : user.nick,
                        email : user.email,
                        photo: user.photo,
                        role: user.role,
                        status: user.status,
                    },
                    review: {
                        id : review.id,
                        name: review.name,
                        trailerUrl: review.trailerUrl,
                        poster: review.poster,
                        filmName: review.filmName,
                        year: review.year,
                        director: review.director,
                        cast: review.cast,
                        text: review.text,
                        user: review.user,
                        country: review.country
                    }
                }),
                headers: {
                    'content-type': 'application/json'
                }
            });
            console.log(response.json().then());
            if (response.ok){
                const result = await response.json();

                //Получаем новый рейтинг
                const newRating = result.newRating;
                //Ввод нового среднего результата
                ratingValue.innerHTML = newRating;
                //Обновление активных звезд
                setRatingActiveWidth();

                rating.classList.remove("rating-sending");
            } else {
                alert("Ошибка");
            }
        }

    }
}