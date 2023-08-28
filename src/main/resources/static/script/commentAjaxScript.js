console.log("CommentScript start")
console.log(window.location.href);
const reviewIdent = window.location.href.substring(window.location.href.lastIndexOf("/") + 1);
console.log(reviewIdent);

async function sendComment(){
    let noComment = document.querySelector('.no-comments');
    if (noComment){
        noComment.classList.remove('no-comments');
        let commentBlock = document.querySelector('.no-comment-text');
        commentBlock.textContent = "";
        commentBlock.classList.add("comments");
        commentBlock.classList.remove("no-comment-text");
    }
    const comments = document.querySelector('.comments');
    let comment = document.getElementById("commentField").value;
    let answer = document.getElementById("idAnswer").value;
    let answerNickname = document.getElementById("nickname-answer").value;
    let date = new Date();

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

    const data = await response.json();
    console.log(data.id);
    //user info
    let userLink = document.querySelector('.account-link').href;
    let avatarIcon = document.querySelector('.avatar-icon').src;
    let nickname = document.querySelector('.authority-user-nick').innerHTML


    //idComment
    let commentHtml;
    if (answer === ""){
        commentHtml =
            "<div id='" + data.id + "' class='comment-item mb-2'>" +
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
            "     </div>\n" +
            "     <div >\n"  +
            "          <span class=\"comment\">" + comment +"</span>\n" +
            "          <div class=\"answer-block\">\n" +
            "          <input type='hidden' class='nickname-user' value='" + nickname + "'>" +
            "          <input type=\"hidden\" value='" + data.id + "' id='idComment'>\n" +
            "          <span class=\"answer-txt\" onclick='sendAnswer(this)'>Ответить</span>\n" +
            "          </div>\n" +
            "      </div>\n" +
            "   </div>\n" +
            "<div/>";
    } else {
        commentHtml =
            "<div id='" + data.id + "' class='comment-item mb-2'>" +
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
            "<a href='#" + answer + "'>" + answerNickname + ", </a>" +
            "          <span class=\"comment\">" + comment +"</span>\n" +
            "          <div class=\"answer-block\">\n" +
            "          <input type=\"hidden\" class='nickname-user' value='" + nickname + "'>" +
            "          <input type='hidden' id='idComment' value='" + data.id + "'>" +
            "          <span class=\"answer-txt\" onclick='sendAnswer(this)'>Ответить</span>\n" +
            "          </div>\n" +
            "      </div>\n" +
            "   </div>\n" +
            "<div/>";
    }

        comments.insertAdjacentHTML("beforeend", commentHtml);
        document.getElementById("commentField").value = "";
        cencelAnswer();
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
    document.querySelector(".nickname-block").style.display='block';
    const nickname = document.querySelector(".nickname");
    let commentField = document.getElementById("commentField");
    commentField.focus();
    document.getElementById("idAnswer").value = spanElem.previousSibling.previousSibling.value;
    document.getElementById("nickname-answer").value = spanElem.previousSibling.previousSibling.previousSibling.previousSibling.value;
    nickname.textContent=spanElem.previousSibling.previousSibling.previousSibling.previousSibling.value;
}
function cencelAnswer(){
    document.querySelector(".nickname-block").style.display='none';
    document.getElementById("idAnswer").value="";
}