package holder;


import entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserHolder {
   private static List<User> userList = new ArrayList<>();



    public static List<User> getAll(){
        return userList;
    }

    public static User save(User user){
        userList.add(user);
        return user;
    }
    public static User getLogin(String login){
        User user = userList.stream()
                .filter(x -> x.getLogin().equals(login))
                .findFirst()
                .orElse(null);
        return user;
    }
}
