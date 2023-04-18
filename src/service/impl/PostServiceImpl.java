package service.impl;

import entity.post.Post;
import entity.user.User;
import entity.user.UserType;
import exaption.PostException;
import holder.PostHolder;
import service.FileService;
import service.PostService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PostServiceImpl implements PostService {
    private Scanner scanner;
    private PostHolder postHolder;
    private FileService fileService;

    public PostServiceImpl(PostHolder postHolder) {
        this.postHolder = postHolder;
        scanner = new Scanner(System.in);
        fileService = new FileServiceImpl();
    }

    @Override
    public Post createPost(User user) throws IOException, PostException {
        System.out.println("<<<<<<<<<<< Создание новой публикации >>>>>>>>>>");
        Post<User> newPost = new Post<>();
        System.out.print("Введите тему публикации: ");
        String themPost = scanner.nextLine();
        if(( themPost == null || themPost.isEmpty())  || themPost.length() <=5)
            throw new PostException("Тема публикации не может быть пустым или содержать менее 5-ти симвалов. Операция прервана!!!");
        newPost.setThem(themPost);
        System.out.print("Введите текст публикации: ");
        String text = scanner.nextLine();
        if(( text == null || text.isEmpty())  || themPost.length() > 255)
            throw new PostException("Текст публикации не должен быть пустым или содержать больше 255 симвалов. Операция прервана!!!");
        newPost.setText(text);
        System.out.println("Введите тэги публикации для разделения используйте ',' ");
        String tagTmp = scanner.nextLine();
        List<String> tags = Arrays.asList(tagTmp.split(","));
        boolean spaceTag = false;
        boolean lengthTags = false;
        for(String el : tags){
            if(el.contains(" ")) spaceTag = true;
            if(el.length() > 3 && el.length() < 16 ) lengthTags = true;
        }
        if(spaceTag && lengthTags)
            throw  new PostException("Тег публикации не должен содержать пробелов," +
                    " иметь менее 3 символов и иметь более 15 символов. Операция прервана!!!");
        newPost.setTags(tags);
        newPost.setDatePosts(LocalDateTime.now());
        newPost.setAuthor(user);
        postHolder.save(newPost);
        postHolder.setPostUserAuth(user);

        new Thread(){
            @Override
            public void run() {
                try {
                    fileService.writePostFileData(postHolder.getAllPost());
                } catch (IOException e) {
                    System.out.println("\033[0;31m" + e.getClass().getName() + ": " + e.getMessage());
                    System.out.println("\033[0m");
                }
            }
        }.start();
        return newPost;
    }

    @Override
    public List<Post> myPosts(User user) {
        postHolder.setPostUserAuth(user);

        return postHolder.getAllPostUserAuth(user);
    }

    @Override
    public List<Post> allPosts() {
        return postHolder.getAllPost();
    }

    @Override
    public List<Post> postByTag(String tag) {
        List<Post> postList = postHolder.getAllPost();
        List<Post> postsResult = new ArrayList<>();
        for (Post el : postList){
            List<String> tagList = el.getTags();
            for (String tagEl : tagList){
                if(tagEl.equals(tag)){
                    postsResult.add(el);
                }
            }
        }
        return postsResult;
    }

    @Override
    public List<Post> postByLogin(String login) {
        return postHolder.getAllPost().stream()
                .filter(x -> x.getAuthor().getLogin().equals(login))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> postByUserType(UserType userType) {
        return postHolder.getAllPost().stream()
                .filter(x -> x.getAuthor().getUserType().equals(userType))
                .collect(Collectors.toList());
    }
}
