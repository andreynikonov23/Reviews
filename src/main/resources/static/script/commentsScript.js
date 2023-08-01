console.log("CommentScript start")
console.log(window.location.href);
const reviewIdent = window.location.href.substring(window.location.href.lastIndexOf("/") + 1);
console.log(reviewIdent);
const comments = document.querySelector('.comments');

async function sendComment(){
    let comment = document.getElementById("commentField").value;
    let answer = document.getElementById("idAnswer").value;
    let date = new Date();

    //user info
    let userLink = document.querySelector('.account-link').href;
    let avatarIcon = document.querySelector('.avatar-icon').src;
    let nickname = document.querySelector('.authority-user-nick').innerHTML

    let response = await fetch("/send-comment?review=" + reviewIdent, {
        method: "POST",
        body: JSON.stringify({
           answer: answer,
           comment: comment,
           date: dateFormatter(date)
        }),
        headers: {
            'content-type': 'application/json'
        }
    });
    let div = document.createElement("div");
    div.classList.add("comment-item", "mb-2");
    //idComment
    let commentHtml =
        "<div class='comment-item mb-2'>" +
        "<a href=\"" + userLink + "\">\n" +
        "   <div class=\"icon\">\n" +
        "        <img src=\"" + avatarIcon + "\" width=\"100px\" height=\"100px\">\n" +
        "   </div>\n" +
        "</a>\n" +
        "<div class=\"comment-block\">\n" +
        "     <div class=\"author-date\">\n" +
        "          <div class=\"com-user\"><a href=\"" + userLink + "\">" + nickname + "</a></div>\n" +
        "          <div class=\"author date\">\n" +
                        dateFormatter(date) +
        "          </div>\n" +
/* Id Comment */    "<input type=\"hidden\" id=\"idComment\">\n" +
        "     </div>\n" +
        "     <div >\n"  +
        "          <span class=\"comment\">" + comment +"</span>\n" +
        "          <div class=\"answer-block\">\n" +
        "               <span class=\"answer-txt\">Ответить</span>\n" +
        "          </div>\n" +
        "      </div>\n" +
        "   </div>\n" +
        "<div/>";
        comments.insertAdjacentHTML("beforeend", commentHtml);
        document.getElementById("commentField").value = "";
}
function dateFormatter(date){
    let a = new Date(date);
    let result = a.getFullYear() + "-";
    if (a.getMonth() < 10){
        result = result + "0" + a.getMonth() + "-";
    } else
        result = result + a.getMonth() + "-"
    if (a.getDate() < 10){
        result = result + "0" + a.getDate() + " ";
    } else
        result = result + a.getDate() + " ";
    if (a.getHours() < 10){
        result = result + "0" + a.getHours() + ":";
    } else
        result = result + a.getHours() + ":";
    if (a.getMinutes() < 10){
        result = result + "0" + a.getMinutes();
    } else
        result = result + a.getMinutes();
    return result;
}

function sendAnswer(spanElem){
    let commentField = document.getElementById("commentField");
    commentField.focus();
    document.getElementById("idAnswer").value = spanElem.previousSibling.previousSibling.value;
}