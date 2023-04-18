package entity.user;

import java.time.LocalDateTime;

public abstract class User {
    private Integer id;
    private String login;
    private String password;
    private LocalDateTime dataRegister;
    private UserType userType;

    public User() {
    }

    public User(Integer id, String login, String password, LocalDateTime dataRegister, UserType userType) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.dataRegister = dataRegister;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDataRegister() {
        return dataRegister;
    }

    public void setDataRegister(LocalDateTime dataRegister) {
        this.dataRegister = dataRegister;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
