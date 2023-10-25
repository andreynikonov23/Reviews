package nick.pack.utils;



public class ActivationCodeDTO {
    private String login;
    private String code;

    public ActivationCodeDTO(String login, String code) {
        this.login = login;
        this.code = code;
    }
    public ActivationCodeDTO(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    //Unique key for unconfirmed users
    public String getUniqueKey(){
        return login + "-" + code;
    }
}
