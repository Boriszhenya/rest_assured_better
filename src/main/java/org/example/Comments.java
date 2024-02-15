package org.example;

public class Comments {
    private long id;
    private long postID;
    private String name;
    private String email;
    private String body;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public long getPostID() { return postID; }
    public void setPostID(long value) { this.postID = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getEmail() { return email; }
    public void setEmail(String value) { this.email = value; }

    public String getBody() { return body; }
    public void setBody(String value) { this.body = value; }
}
