package service;

import entity.post.Post;
import entity.user.User;
import entity.user.UserType;
import exaption.PostException;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post createPost(User user) throws IOException, PostException;
    List<Post> myPosts(User user);
    List<Post> allPosts();
    List<Post> postByTag(String tag);
    List<Post> postByLogin(String login);
    List<Post> postByUserType(UserType userType);


}
