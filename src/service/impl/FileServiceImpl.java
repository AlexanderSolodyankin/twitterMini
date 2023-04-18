package service.impl;

import entity.post.Post;
import entity.user.Organization;
import entity.user.Person;
import entity.user.User;
import entity.user.UserType;
import exaption.FileDataHolderException;
import service.FileService;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileServiceImpl implements FileService {
    @Override
    public boolean createFile(String fileName) throws IOException {
        return new File(fileName).createNewFile();
    }

    @Override
    public boolean createFolder(String folderDirectory) {
        return new File(folderDirectory).mkdirs();
    }

    @Override
    public boolean writeUserFileData(List<User> users) throws IOException {
        FileWriter writer = new FileWriter("UserData.txt");
        int fileSizeBefore = (int) new File("UserData.txt").length();
        for (User user : users){
            String userLine = String.format("{%s}{%s}{%s}{%s}{%s}{%s}{%s}{%s}\n",
                    user.getId(), user.getLogin(),user.getPassword(), user.getDataRegister(), user.getUserType(),
                    user instanceof Person? ((Person)user).getName() : ((Organization)user).getName(),
                    user instanceof Person? ((Person)user).getSerName() : ((Organization)user).getOccupation(),
                    user instanceof Person? ((Person)user).getDataBerth() : ((Organization)user).getDataFounding());
            writer.write(userLine);
        }
        writer.close();
        return fileSizeBefore != (int) new File("UserData.txt").length();
    }

    @Override
    public boolean writePostFileData(List<Post> postList) throws IOException {
        FileWriter writer = new FileWriter("PostData.txt");
        int fileSizeBefore = (int) new File("PostData.txt").length();

        for (Post el : postList){
            writer.write(String.format("{%s}{%s}{%s}{%s}{%s}{%s}\n",
                    el.getId(),el.getAuthor().getLogin(), el.getThem()
                    ,el.getText(),el.getTags(), el.getDatePosts()));
        }
        writer.close();
        return fileSizeBefore != (int) new File("PostData.txt").length();
    }

    @Override
    public List<User> getAllUsersFromFile() throws IOException {
        List<String> userListLine = new ArrayList<>();
        FileReader reader = new FileReader("UserData.txt");
        Scanner scan = new Scanner(reader);
        while (scan.hasNext()){
            userListLine.add(scan.nextLine());
        }
        reader.close();
        List<User> userList = new ArrayList<>();
        for(String el : userListLine) {
            el = el.replace("{", "");
            String[] result = el.split("}");
            int id = Integer.parseInt(result[0]);
            String login = result[1];
            String password = result[2];
            LocalDateTime registerDate = LocalDateTime.parse(result[3]);
            UserType userType = UserType.valueOf(result[4]);
            String name = result[5];
            String secondName = result[6];
            LocalDate dateBerth = LocalDate.parse(result[7]);
            User user;
            if(userType.equals(UserType.PERSON)){
                user = new Person(id,login,password,registerDate,userType,name,secondName,dateBerth);
            }else user = new Organization(id,login,password,registerDate,userType,name,secondName,dateBerth);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<User> getAllUsersFromFile(String fileName) throws IOException {
        if(!fileName.contains(".txt")) throw new IOException("Неверный формат файла!!!");

        List<String> userListLine = new ArrayList<>();
        FileReader reader = new FileReader(fileName);
        Scanner scan = new Scanner(reader);

        while (scan.hasNext()){
            userListLine.add(scan.nextLine());
        }
        reader.close();

        List<User> userList = new ArrayList<>();
        for(String el : userListLine) {
            el = el.replace("{", "");
            String[] result = el.split("}");
            int id = Integer.parseInt(result[0]);
            String login = result[1];
            String password = result[2];
            LocalDateTime registerDate = LocalDateTime.parse(result[3]);
            UserType userType = UserType.valueOf(result[4]);
            String name = result[5];
            String secondName = result[6];
            LocalDate dateBerth = LocalDate.parse(result[7]);
            User user;
            if(userType.equals(UserType.PERSON)){
                user = new Person(id,login,password,registerDate,userType,name,secondName,dateBerth);
            }else user = new Organization(id,login,password,registerDate,userType,name,secondName,dateBerth);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public List<Post> getAllPostsFromFile(List<User> userList) throws IOException, FileDataHolderException {
        List<String> postListLine = new ArrayList<>();
        FileReader postReader = new FileReader("PostData.txt");
        Scanner scanner = new Scanner(postReader);
        while (scanner.hasNext()){
            postListLine.add(scanner.nextLine());
        }
        postReader.close();

        List<Post> readPostsList = new ArrayList<>();
        for(String el : postListLine){
            el = el.replace("{","");
            String[] postParams = el.split("}");
            int id = Integer.parseInt(postParams[0]);
            User user = userList.stream().filter(x -> x.getLogin().equals(postParams[1])).findFirst().orElse(null);
            if(user == null){
                throw new FileDataHolderException("Пользователь не существует");
            }
            String them = postParams[2];
            String text = postParams[3];
            List<String> tags = Arrays.asList(postParams[4].split(","));
            LocalDateTime datePostRegister = LocalDateTime.parse(postParams[5]);

            Post<User> post = new Post<>(id,user,them,text,tags,datePostRegister);
            readPostsList.add(post);
        }
        return readPostsList;
    }

    @Override
    public List<Post> getAllPostsFromFile(String fileName, List<User> userList) throws IOException, FileDataHolderException {
        if(!fileName.contains(".txt")) throw new IOException("Неверный формат файла!!!");

        List<String> postListLine = new ArrayList<>();
        FileReader postReader = new FileReader(fileName);
        Scanner scanner = new Scanner(postReader);
        while (scanner.hasNext()){
            postListLine.add(scanner.nextLine());
        }
        postReader.close();

        List<Post> readPostsList = new ArrayList<>();
        for(String el : postListLine){
            el = el.replace("{","");
            String[] postParams = el.split("}");
            int id = Integer.parseInt(postParams[0]);
            User user = userList.stream().filter(x -> x.getLogin().equals(postParams[1])).findFirst().orElse(null);
            if(user == null){
                throw new FileDataHolderException("Пользователь не существует");
            }
            String them = postParams[2];
            String text = postParams[3];
            List<String> tags = Arrays.asList(postParams[4].split(","));
            LocalDateTime datePostRegister = LocalDateTime.parse(postParams[5]);

            Post<User> post = new Post<>(id,user,them,text,tags,datePostRegister);
            readPostsList.add(post);
        }
        return readPostsList;
    }

    @Override
    public boolean isFileEmpty(File file) {
        return file.length() == 0;
    }
}
