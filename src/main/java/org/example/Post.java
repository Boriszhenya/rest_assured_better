package org.example;

public class Post {
    private int id;
    private int userId;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Post post = (Post) obj;

        return getUserId() == post.getUserId() &&
                getTitle().equals(post.getTitle()) &&
                getBody().equals(post.getBody());
    }

    @Override
    public String toString() {
        return "Post {" +
                "id = " + id + "'," +
                "userId = " + userId + "'," +
                "title = '" + title + "'," +
                "body = '" + body + '\'' +
                '}';
    }
}
