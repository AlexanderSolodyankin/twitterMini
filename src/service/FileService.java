package service;

import entity.post.Post;
import entity.user.User;
import exaption.FileDataHolderException;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface FileService {
    boolean createFile(String fileName) throws IOException;
    boolean createFolder(String folderName);
    boolean writeUserFileData(List<User> user) throws IOException;
    boolean writePostFileData(List<Post> post) throws IOException;
    List<User> getAllUsersFromFile() throws IOException;
    List<User> getAllUsersFromFile(String fileName) throws IOException;
    List<Post> getAllPostsFromFile(List<User> userList) throws IOException, FileDataHolderException;
    List<Post> getAllPostsFromFile(String fileName, List<User> userList) throws IOException, FileDataHolderException;
    boolean isFileEmpty(File file);


}
