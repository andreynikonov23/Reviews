let index = window.location.href.lastIndexOf("/") + 1;
const reviewId = window.location.href.substring(index);
const comments = document.querySelector('.comments');

async function sendComment(){
    let comment = document.getElementById("commentField").value;
    let answer = document.getElementById("idAnswer").value;
    let date = new Date();

    //user info
    let userLink = document.querySelector('.account-link').href;
    let avatarIcon = document.querySelector('.avatar-icon').src;
    let nickname = document.querySelector('.authority-user-nick').innerHTML

    let response = await fetch("/send-comment?review=" + reviewId, {
        method: "POST",
        body: JSON.stringify({
           answer: answer,
           comment: comment
        }),
        headers: {
            'content-type': 'application/json'
        }
    });
    let div = document.createElement("div");
    div.classList.add("comment-item", "mb-2");
    //idComment
    let doc =
        "<a href=\"" + userLink + "\">\n" +
        "   <div class=\"icon\">\n" +
        "        <img src=\"" + avatarIcon + "\" width=\"100px\" height=\"100px\">\n" +
        "   </div>\n" +
        "</a>\n" +
        "<div class=\"comment-block\">\n" +
        "     <div class=\"author-date\">\n" +
        "          <div class=\"com-user\"><a href=\"" + nickname + "\">" + nickname + "</a></div>\n" +
        "          <div class=\"author date\">" + date + "</div>\n" +
/* Id Comment */    "<input type=\"hidden\" id=\"idComment\">\n" +
        "     </div>\n" +
        "     <div >\n" +
        "          <span th:if=\"${comment.getAnswer() != null}\">\n" +
        "                <a th:href=\"@{#} + ${comment.getAnswer().getId()}\" th:text=\"${comment.getAnswer().getUser().getNick()}\"></a>,\n" +
        "          </span>\n" +
        "          <span class=\"comment\" th:text=\"${comment.getComment()}\"></span>\n" +
        "          <div class=\"answer-block\">\n" +
        "               <span class=\"answer-txt\">Ответить</span>\n" +
        "          </div>\n" +
        "      </div>\n" +
        "   </div>\n";
}
function test(comment){
    console.log(comment);
}