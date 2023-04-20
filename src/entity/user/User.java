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

    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public User setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public LocalDateTime getDataRegister() {
        return dataRegister;
    }

    public User setDataRegister(LocalDateTime dataRegister) {
        this.dataRegister = dataRegister;
        return this;
    }

    public UserType getUserType() {
        return userType;
    }

    public User setUserType(UserType userType) {
        this.userType = userType;
        return this;
    }
}
