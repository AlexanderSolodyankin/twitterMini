package entity.post;

import entity.user.User;

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

    public Post(Integer id, T author, String them, String text, List<String> tags, LocalDateTime datePosts) {
        this.id = id;
        this.author = author;
        this.them = them;
        this.text = text;
        this.tags = tags;
        this.datePosts = datePosts;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public T getAuthor() {
        return author;
    }

    public void setAuthor(T author) {
        this.author = author;
    }

    public String getThem() {
        return them;
    }

    public void setThem(String them) {
        this.them = them;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public LocalDateTime getDatePosts() {
        return datePosts;
    }

    public void setDatePosts(LocalDateTime datePosts) {
        this.datePosts = datePosts;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", author=" + author +
                ", them='" + them + '\'' +
                ", text='" + text + '\'' +
                ", tags=" + tags +
                ", datePosts=" + datePosts +
                '}'+ '\'' ;
    }
}
