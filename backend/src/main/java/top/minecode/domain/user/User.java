package top.minecode.domain.user;

import java.util.Date;

/**
 * Created on 2018/5/19.
 * Description:
 *
 * @author iznauy
 */
public abstract class User {

    protected UserType userType;

    protected String email;

    protected String name;

    protected double dollars;

    protected Date joinTime;

    protected String avatar;

    public User() {
    }

    public User(UserType userType, String email, String name,
                double dollars, Date joinTime, String avatar) {
        this.userType = userType;
        this.email = email;
        this.name = name;
        this.dollars = dollars;
        this.joinTime = joinTime;
        this.avatar = avatar;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDollars() {
        return dollars;
    }

    public void setDollars(double dollars) {
        this.dollars = dollars;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}