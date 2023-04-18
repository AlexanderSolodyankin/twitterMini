package entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Person extends User{
    private String name;
    private String serName;
    private LocalDate dataBerth;

    public Person() {
        this.setUserType(UserType.PERSON);
    }

    public Person(Integer id, String login, String password,
                  LocalDateTime dataRegister, UserType userType,
                  String name, String serName, LocalDate dataBerth) {
        super(id, login, password, dataRegister, userType);
        this.name = name;
        this.serName = serName;
        this.dataBerth = dataBerth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public LocalDate getDataBerth() {
        return dataBerth;
    }

    public void setDataBerth(LocalDate dataBerth) {
        this.dataBerth = dataBerth;
    }

    @Override
    public String toString() {
        return String.format("\n[%s] " +
                        "ID: %s \n" +
                        "Полное имя: %s %s \n" +
                        "Дата Рождения: %s \n" +
                        "Логин: %s ",
                getUserType() , getId(), getName(),
                getSerName(), getDataBerth(), getLogin());
    }
}
