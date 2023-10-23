
const reviewIdent = window.location.href.substring(window.location.href.lastIndexOf("/") + 1);
console.log(reviewIdent);

//sending comment in server
async function sendComment(){
    let noComments = document.querySelector('.no-comments');
    if (noComments){
        noComments.classList.remove('no-comments');
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

    let userLink = document.querySelector('.account-link').href;
    let avatarIcon = document.querySelector('.avatar-icon').src;
    let nickname = document.querySelector('.authority-user-nick').innerHTML

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

//format in valid date
function dateFormatter(dateString){
    let date = new Date(dateString);
    let result = date.getFullYear() + "-";
    if (date.getMonth() < 10){
        result = result + "0" + date.getMonth() + "-";
    } else
        result = result + date.getMonth() + "-"
    if (date.getDate() < 10){
        result = result + "0" + date.getDate() + " ";
    } else
        result = result + date.getDate() + " ";
    if (date.getHours() < 10){
        result = result + "0" + date.getHours() + ":";
    } else
        result = result + date.getHours() + ":";
    if (date.getMinutes() < 10){
        result = result + "0" + date.getMinutes();
    } else
        result = result + date.getMinutes();
    return result;
}

//int comment with answer
function sendAnswer(spanElem){
    document.querySelector(".nickname-block").style.display='block';
    const nickname = document.querySelector(".nickname");
    let commentField = document.getElementById("commentField");
    commentField.focus();
    document.getElementById("idAnswer").value = spanElem.previousSibling.previousSibling.value;
    document.getElementById("nickname-answer").value = spanElem.previousSibling.previousSibling.previousSibling.previousSibling.value;
    nickname.textContent=spanElem.previousSibling.previousSibling.previousSibling.previousSibling.value;
}

//clear answer field
function cencelAnswer(){
    document.querySelector(".nickname-block").style.display='none';
    document.getElementById("idAnswer").value="";
}