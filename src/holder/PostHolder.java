package mini.twter.holder;


import mini.twter.entity.post.Post;
import mini.twter.service.UserAuthService;

import java.util.*;

public class PostHolder {
    private static Map<String, List<Post>> postMap = new HashMap<>();

    public static List<Post> getPostsByUserLogin(String login){
        return postMap.get(login);
    }

    public static List<Post> getAllAuthorUser(){
        if(UserAuthService.getAunthUser() == null){
            System.err.println("Вы не Авторезированны для получения информации ");
            return null;
        }
        return postMap.get(UserAuthService.getAunthUser().getLogin());
    }

    public static List<Post> getAllPosts(){
        List<Post> getAllPosts = new ArrayList<>();
        for(List<Post> el : postMap.values()){
            for (Post postElem : el){
                getAllPosts.add(postElem);
            }
        }
        return getAllPosts;

    }

    public static Post add(Post post){
        if(UserAuthService.getAunthUser() == null){
            System.err.println("Вы не Авторезированны для получения информации ");
            return null;
        }
        List<Post> authorizedUserPosts = getPostsByUserLogin(UserAuthService.getAunthUser().getLogin());
        if(authorizedUserPosts == null || authorizedUserPosts.isEmpty()){
            authorizedUserPosts = new ArrayList<>();
        }
        authorizedUserPosts.add(post);
        postMap.put(UserAuthService.getAunthUser().getLogin(), authorizedUserPosts);
        return post;
    }

}
