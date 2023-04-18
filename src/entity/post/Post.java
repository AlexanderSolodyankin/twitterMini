package entity.post;

import entity.user.Organization;
import entity.user.Person;
import entity.user.User;
import entity.user.UserType;

import java.time.LocalDateTime;
import java.util.List;

public class Post<T extends User> {
    private Integer id;
    private T author;
    private String them;
    private String text;
    private List<String> tags;
    private LocalDateTime datePosts;

    public Post() {
    }


    public Integer getId() {
        return id;
    }

    public Post<T> setId(Integer id) {
        this.id = id;
        return this;
    }

    public T getAuthor() {
        return author;
    }

    public Post<T> setAuthor(T author) {
        this.author = author;
        return this;
    }

    public String getThem() {
        return them;
    }

    public Post<T> setThem(String them) {
        this.them = them;
        return this;
    }

    public String getText() {
        return text;
    }

    public Post<T> setText(String text) {
        this.text = text;
        return this;
    }

    public List<String> getTags() {
        return tags;
    }

    public Post<T> setTags(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public LocalDateTime getDatePosts() {
        return datePosts;
    }

    public Post<T> setDatePosts(LocalDateTime datePosts) {
        this.datePosts = datePosts;
        return this;
    }

    @Override
    public String toString() {
        return String.format("Публикация: {\n"+
                "Автор: [ %s ] %s ; \n" +
                "Создано: %s ; \n"+
                "Тема: %s ; \n" +
                "Текст: %s ; \n" +
                "Тэги: %s ; \n}",
                author.getUserType(),
                author.getUserType().equals(UserType.PERSON) ?( ((Person)author).getName() + " " + ((Person)author).getSerName()) :
                        ((Organization)author).getName(),
                getDatePosts(), getThem(), getText(), getTags());
    }
}
