package top.minecode.web.user;

/**
 * Created on 2018/5/17.
 * Description:
 * @author Liao
 */
public class LoginCommand {
    private String email;
    private String password;
    private String userType;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
