console.log("RatingScript start");

const rating = document.querySelector('.rating-block');
let index = window.location.href.lastIndexOf("/") + 1;
const reviewId = window.location.href.substring(index);
initRating(rating);

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
        let login = "http://localhost:8080/login";
        if (!rating.classList.contains('rating-sending')){
            rating.classList.add('rating-sending');


            //Отправка данных на форму
            let response = await fetch("/set-rating?id=" + reviewId, {
                method: "POST",

                body: JSON.stringify({
                    rating: value
                }),
                headers: {
                    'content-type': 'application/json'
                }
            });
            if (response.url === login){
                alert("Вы не авторизированны");
            } else if (response.ok){
                rating.classList.remove("rating-sending");
                const result = await response.json();

                //Получаем новый рейтинг
                const newRating = result.newRating;
                console.log(result.newRating);
                //Ввод нового среднего результата
                ratingValue.innerHTML = newRating;
                //Обновление активных звезд
                setRatingActiveWidth();
            } else {
                alert("Ошибка");
            }
        }
    }
}