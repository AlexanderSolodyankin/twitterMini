import entity.post.Post;
import entity.user.Organization;
import entity.user.Person;
import entity.user.User;
import entity.user.UserType;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Draft {
    public static void main(String[] args) throws IOException {
    LocalDate dateBerth = LocalDate.parse("1990-11-26");
        System.out.println(LocalDate.now().toEpochDay() - dateBerth.toEpochDay());


//        List<User> usersListGenerator  = new ArrayList<>();
//        Random ran = new Random();
//        for (int i = 0; i < ran.nextInt(46) + 5; i++) {
//            User user ;
//            if(ran.nextBoolean()){
//                user = new Person();
//            }else user = new Organization();
//
//            user.setId(usersListGenerator.size());
//            user.setLogin("login"+i);
//            user.setPassword("password"+i);
//            user.setDataRegister(LocalDateTime.now());
//
//            if(user.getUserType() == UserType.PERSON){
//                Person person =(Person) user;
//                person.setName("name"+i);
//                person.setSerName("serName"+i);
//                person.setDataBerth(LocalDate.now());
//                user = person;
//            }else {
//                Organization organization =(Organization) user;
//                organization.setName("name"+i);
//                organization.setOccupation("occupation"+i);
//                organization.setDataFounding(LocalDate.now());
//                user = organization;
//            }
//            usersListGenerator.add(user);
//        }
//
//        File file = new File("UserData.txt");
//
//        try {
//            file.createNewFile();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//            FileWriter writer = new FileWriter("UserData.txt");
//            for (User user : usersListGenerator){
//                String userLine = String.format("{%s}{%s}{%s}{%s}{%s}{%s}{%s}{%s}\n",
//                                user.getId(), user.getLogin(),user.getPassword(), user.getDataRegister(), user.getUserType(),
//                                user instanceof Person? ((Person)user).getName() : ((Organization)user).getName(),
//                                user instanceof Person? ((Person)user).getSerName() : ((Organization)user).getOccupation(),
//                                user instanceof Person? ((Person)user).getDataBerth() : ((Organization)user).getDataFounding());
//                writer.write(userLine);
//            }
//            writer.close();
//
//
//        List<String> userList = new ArrayList<>();
//            FileReader reader = new FileReader("UserData.txt");
//            Scanner scan = new Scanner(reader);
//            while (scan.hasNext()){
//                userList.add(scan.nextLine());
//            }
//            reader.close();
//
//        List<User> newListUsers = new ArrayList<>();
//        for(String el : userList) {
//            el = el.replace("{", "");
//            String[] result = el.split("}");
//            int id = Integer.parseInt(result[0]);
//            String login = result[1];
//            String password = result[2];
//            LocalDateTime registerDate = LocalDateTime.parse(result[3]);
//            UserType userType = UserType.valueOf(result[4]);
//            String name = result[5];
//            String secondName = result[6];
//            LocalDate dateBerth = LocalDate.parse(result[7]);
//            User user;
//            if(userType.equals(UserType.PERSON)){
//                user = new Person(id,login,password,registerDate,userType,name,secondName,dateBerth);
//            }else user = new Organization(id,login,password,registerDate,userType,name,secondName,dateBerth);
//            newListUsers.add(user);
//        }
//
////        System.out.println(newListUsers);
////        \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
//
//        List<Post> postList = new ArrayList<>();
//        for (User user : newListUsers){
//            for (int i = 0; i < ran.nextInt(101)+5; i++) {
//                Post<User> post = new Post<>();
//                post.setId(postList.size());
//                post.setDatePosts(LocalDateTime.now());
//                post.setAuthor(user);
//                post.setText("Text"+ i+" " + user.getLogin());
//                post.setThem("Theme"+ i+" " + user.getLogin());
//                List<String> tags = new ArrayList<>();
//                for (int j = 0; j < ran.nextInt(10)+1; j++) {
//                    tags.add("tags"+i);
//                }
//                post.setTags(tags);
//                postList.add(post);
//            }
//        }
//
//        File postData = new File("PostData.txt");
//        postData.createNewFile();
//
//        FileWriter postWriter = new FileWriter(postData);
//        for (Post el : postList){
//            postWriter.write(String.format("{%s}{%s}{%s}{%s}{%s}{%s}\n",
//                    el.getId(),el.getAuthor().getLogin(), el.getThem()
//                    ,el.getText(),el.getTags(), el.getDatePosts()));
//        }
//        postWriter.close();
//
//        List<String> postListLine = new ArrayList<>();
//        FileReader postReader = new FileReader(postData);
//        Scanner scanner = new Scanner(postReader);
//        while (scanner.hasNext()){
//            postListLine.add(scanner.nextLine());
//        }
//        postReader.close();
//
//
//            newListUsers.remove(5);
//        List<Post> readPostsList = new ArrayList<>();
//        for(String el : postListLine){
//            el = el.replace("{","");
//            String[] postParams = el.split("}");
//            int id = Integer.parseInt(postParams[0]);
//            User user = newListUsers.stream().filter(x -> x.getLogin().equals(postParams[1])).findFirst().orElse(null);
//            if(user == null){
//                System.out.println("\033[0;31m Пользователь не существует (Отрфактить) \033[0m");
//            }
//            String them = postParams[2];
//            String text = postParams[3];
//            List<String> tags = Arrays.asList(postParams[4].split(","));
//            LocalDateTime datePostRegister = LocalDateTime.parse(postParams[5]);
//
//            Post<User> post = new Post<>(id,user,them,text,tags,datePostRegister);
//            readPostsList.add(post);
//        }
//        System.out.println(readPostsList);

    }


}
