package holder;

import entity.user.User;

import java.util.ArrayList;
import java.util.List;

public class UserHolder {
    private  List<User> userList ;

    public UserHolder() {
        userList = new ArrayList<>();
    }

    public   User saveUser(User user){
        if(user.getId() == null) {
            user.setId(userList.size());
        }
        userList.add(user);
        return user;
    }

    public  List<User> getAllUsers(){
        return userList;
    }

    public   User getUserByLogin(String login){
        return userList.stream()
                .filter(x -> x.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
