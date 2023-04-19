package service;


import entity.post.Post;
import entity.user.UserType;
import holder.PostHolder;
import util.CommandKey;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PostService {

    public Post addPosts(Post post){




       return PostHolder.add(post); //////////////////////////////////////////////////////////////////////


    }

    public List<Post> getMyPosts(){
        return PostHolder.getAllAuthorUser();
    }
    public List<Post> getAllPosts(){
        return PostHolder.getAllPosts();
    }
    public List<Post> getAllPostsByTag(){
        System.out.println("Введите тэг для поиска публикации");
        CommandKey.nextLine();
        List<Post> listByTag = new ArrayList<>();
        for (Post el : getAllPosts()){
            for(String tagEl : el.getTag()){
                if(tagEl.equals(CommandKey.getLine())){
                    listByTag.add(el);
                    break;
                }
            }
        }
        if(listByTag.isEmpty()){
            return null;
        }
        return listByTag;
    }

    public List<Post> getAllPostsByUserType(){
        System.out.println("Введите тип польхователя  для поиска публикации");
        CommandKey.nextLine();
        UserType userType ;

        while (true){
            if(CommandKey.getLine().equalsIgnoreCase("PERSON")){
                userType = UserType.PERSON;
                break;
            }else if(CommandKey.getLine().equalsIgnoreCase("ORGANIZATION")){
                userType = UserType.PERSON;
                break;
            }else {
                System.err.println("Таких типов пользователя нет повторите попытку ");
                CommandKey.nextLine();
            }
        }
        List<Post> listByUserType = new ArrayList<>();
        for (Post el : getAllPosts()){
           if(el.getAuthor().getUserType() == userType){
               listByUserType.add(el);
           }
        }
        if(listByUserType.isEmpty()){
            return null;
        }
        return listByUserType;
    }

    public List<Post> getAllPostsByLogin(){
        System.out.print("Введите логин чьи публикации вы хотите просмотреть: ");
        CommandKey.nextLine();
        List<Post> listByLogin = PostHolder.getPostsByUserLogin(CommandKey.getLine());
        if(listByLogin.isEmpty()){
            return null;
        }
        return listByLogin;
    }
    public List<String> newTags (String lineTags){
        List<String> tags = Arrays.asList(lineTags.split(","));
        return tags;
    }

}
