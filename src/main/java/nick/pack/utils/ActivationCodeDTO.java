package nick.pack.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCodeDTO {
    private String login;
    private String code;

    //Unique key for unconfirmed users
    public String getUniqueKey(){
        return login + "-" + code;
    }
}
