package org.example;

public class Post {
    private int id;
    private int user_id;
    private String title;
    private String body;

    public int getID() { return id; }
    public void setID(int value) { this.id = value; }


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Post post = (Post) obj;

        return getUser_id() == post.getUser_id() &&
                getTitle().equals(post.getTitle()) &&
                getBody().equals(post.getBody());
    }

    @Override
    public String toString() {
        return "Post {" +
                "id = " + id + "'," +
                "userId = " + user_id + "'," +
                "title = '" + title + "'," +
                "body = '" + body + '\'' +
                '}';
    }
}
