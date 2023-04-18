package entity.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Organization extends User{
    private String name;
    private String occupation;
    private LocalDate dataFounding;




    public Organization() {
        this.setUserType(UserType.ORGANIZATION);
    }


    public String getName() {
        return name;
    }

    public Organization setName(String name) {
        this.name = name;
        return this;
    }

    public String getOccupation() {
        return occupation;
    }

    public Organization setOccupation(String occupation) {
        this.occupation = occupation;
        return this;
    }

    public LocalDate getDataFounding() {
        return dataFounding;
    }

    public Organization setDataFounding(LocalDate dataFounding) {
        this.dataFounding = dataFounding;
        return this;
    }

    @Override
    public String toString() {
        return String.format("\n[%s] { \n" +
                        "ID: %s \n" +
                        "Наименование : %s \n" +
                        "Дата Основания: %s \n" +
                        "Логин: %s }",
                getUserType() , getId(), getName()
                , getDataFounding(), getLogin());
    }
}
