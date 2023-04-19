package entity.user;

import java.time.LocalDate;

public class Organization extends User {
    private String name;
    private String occupation;

    private LocalDate dateFound;
    public Organization() {
        setUserType(UserType.ORGANIZATION);
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

    public LocalDate getDateFound() {
        return dateFound;
    }

    public void setDateFound(LocalDate dateFound) {
        this.dateFound = dateFound;
    }
    @Override
    public String toString() {
        return String.format("      [%s] \n" +
                        "            ID: %s \n" +
                        "            Полное имя: %s %s \n" +
                        "            Дата основания: %s \n" +
                        "            Логин: %s",
                this.getUserType().name(), this.getId(), this.getName(), this.getOccupation(), this.getDateFound(),
                this.getLogin()
        );
    }
}
