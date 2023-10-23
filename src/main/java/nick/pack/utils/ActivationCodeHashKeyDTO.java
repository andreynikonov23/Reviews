package nick.pack.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationCodeHashKeyDTO {
    private String username;
    private String code;

    public String getHashKey(){
        return username + "-" + code;
    }
}
