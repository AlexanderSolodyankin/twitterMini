package twitter.service;

import twitter.entity.post.Post;
import twitter.entity.user.User;
import twitter.entity.user.UserType;
import twitter.exaption.DatePostException;
import twitter.exaption.PostException;

import java.io.IOException;
import java.util.List;

public interface PostService {
    Post createPost(User user) throws IOException, PostException;
    List<Post> myPosts(User user);
    List<Post> allPosts();
    List<Post> postByTag(String tag) throws DatePostException;
    List<Post> postByLogin(String login) throws DatePostException;
    List<Post> postByUserType(UserType userType);


}
