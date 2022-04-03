package go_rest_api_v12.go_rest_api_v12.models;

public class CommentModel {

    private int id;
    private int post_id;
    private String name;
    private String email;
    private String body;

    public CommentModel() {
    }

    public CommentModel(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getId() {
        return id;
    }

    public int getPost_id() {
        return post_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "id=" + id +
                ", post_id=" + post_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

}
