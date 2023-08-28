package nick.pack.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int answer;
    private String comment;
    private String date;

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
