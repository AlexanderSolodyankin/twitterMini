package mini.twter.entity.post;

import mini.twter.entity.user.Organization;
import mini.twter.entity.user.Person;
import mini.twter.entity.user.User;
import mini.twter.entity.user.UserType;

import java.time.LocalDate;
import java.util.List;

public class Post {
    private Integer id;
    private User author;
    private String theme;
    private String postsText;
    private List<String> tag;
    private LocalDate postsDate;

    public Post() {
    }




    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPostsText() {
        return postsText;
    }

    public void setPostsText(String postsText) {
        this.postsText = postsText;
    }

    public List<String> getTag() {
        return tag;
    }

    public void setTag(List<String> tag) {
        this.tag = tag;
    }

    public LocalDate getPostsDate() {
        return postsDate;
    }

    public void setPostsDate(LocalDate postsDate) {
        this.postsDate = postsDate;
    }

    public String tagsToString(){
        String tagLine = "";
        for (String line : this.tag){
            tagLine += line;
        }
        return tagLine;
    }

    public String userNameToString(){
        if( this.author.getUserType() == UserType.PERSON){
            return ((Person) this.author).getName();
        }
         return ((Organization) this.author).getName();
    }
    public String userSerNameToString(){
        if( this.author.getUserType() == UserType.PERSON){
            return ((Person) this.author).getSerName();
        }
        return ((Organization) this.author).getOccupation();
    }

    @Override
    public String toString() {
        return String.format("Автор: [%s] %s %s \n" +
                "Созданно: %s \n" +
                "Тема: %s \n" +
                "Текст: %s \n" +
                "Тэги: %s",
                this.author.getUserType(), userNameToString(), userSerNameToString(),
                this.getPostsDate(), this.getTheme(), this.getPostsText(), tagsToString()
                );
    }
}
