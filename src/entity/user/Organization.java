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

    public Organization(Integer id, String login, String password,
                        LocalDateTime dataRegister, UserType userType,
                        String name, String occupation, LocalDate dataFounding) {
        super(id, login, password, dataRegister, userType);
        this.name = name;
        this.occupation = occupation;
        this.dataFounding = dataFounding;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public LocalDate getDataFounding() {
        return dataFounding;
    }

    public void setDataFounding(LocalDate dataFounding) {
        this.dataFounding = dataFounding;
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
