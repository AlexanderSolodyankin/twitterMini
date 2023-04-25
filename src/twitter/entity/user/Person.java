package twitter.entity.user;

import java.time.LocalDate;

public class Person extends User{
    private String name;
    private String serName;
    private LocalDate dataBerth;

    public Person() {
        this.setUserType(UserType.PERSON);
    }

    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getSerName() {
        return serName;
    }

    public Person setSerName(String serName) {
        this.serName = serName;
        return this;
    }

    public LocalDate getDataBerth() {
        return dataBerth;
    }

    public Person setDataBerth(LocalDate dataBerth) {
        this.dataBerth = dataBerth;
        return this;
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
