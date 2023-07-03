const rating = document.querySelector('.rating-block');

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
                ratingValue.innerHTML = i + 1;
                setRatingActiveWidth();

            });
        }
    }
}