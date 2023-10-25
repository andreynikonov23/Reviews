package nick.pack.utils;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class CommentDTO {
    private int answer;
    private String comment;
    private String date;

    public CommentDTO(int answer, String comment, String date) {
        this.answer = answer;
        this.comment = comment;
        this.date = date;
    }
    public CommentDTO(){}

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //Getter with parse date string to the LocalDateTime
    public LocalDateTime getDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(date, formatter);
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "answer=" + answer +
                ", comment='" + comment + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
