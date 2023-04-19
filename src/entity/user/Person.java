package mini.twter.entity.user;

import java.time.LocalDate;

public class Person extends User {
    private String name;
    private String serName;
    private LocalDate dateBerth;

    public Person() {
        setUserType(UserType.PERSON);
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

    public LocalDate getDateBerth() {
        return dateBerth;
    }

    public void setDateBerth(LocalDate dateBerth) {
        this.dateBerth = dateBerth;
    }
    public void setDateBerth(String dateBerth) {
        this.dateBerth = LocalDate.parse(dateBerth);
    }

    @Override
    public String toString() {
        return String.format("      [%s] \n" +
                "            ID: %s \n" +
                "            Полное имя: %s %s \n" +
                "            Дата рождения: %s \n" +
                "            Логин: %s",
                this.getUserType().name(), this.getId(), this.getName(), this.getSerName(), this.getDateBerth(),
                this.getLogin()
        );
    }
}
