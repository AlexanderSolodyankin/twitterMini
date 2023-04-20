package holder;

import entity.post.Post;
import entity.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PostHolder {
    private static PostHolder postHolderInstance;

    public static PostHolder getPostHolderInstance(){
        if (postHolderInstance == null){
            postHolderInstance = new PostHolder();
        }
        return postHolderInstance;
    }
    private List<Post> postAllHolder ;
    private List<Post> postAuthUserHolder ;

    private PostHolder() {
        postAllHolder = new ArrayList<>();
        postAuthUserHolder = new ArrayList<>();
    }

    public Post save(Post post){
        post.setId(postAllHolder.size());
        postAllHolder.add(post);
        return post;
    }

    public List<Post> getAllPost(){
        return postAllHolder;
    }

    public List<Post> getPostByUser(User user){
        return  postAllHolder.stream().filter(
                x -> x.getAuthor().equals(user)).collect(Collectors.toList()
        );
    }
    public List<Post> getAllPostUserAuth(User user){
        postAuthUserHolder = getPostByUser(user);
        return postAuthUserHolder;
    }
    public void setPostUserAuth(User user){
        postAuthUserHolder = getAllPostUserAuth(user);
    }

    public List<Post> getPostAllHolder() {
        return postAllHolder;
    }

    public void setPostAllHolder(List<Post> postAllHolder) {
        this.postAllHolder = postAllHolder;
    }

    public List<Post> getPostAuthUserHolder() {
        return postAuthUserHolder;
    }

    public void setPostAuthUserHolder(List<Post> postAuthUserHolder) {
        this.postAuthUserHolder = postAuthUserHolder;
    }
}
