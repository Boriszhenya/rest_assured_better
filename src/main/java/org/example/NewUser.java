package org.example;

public class NewUser {
    private int id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public int getID() {
        return id;
    }

    public void setID(int value) {
        this.id = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String value) {
        this.email = value;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String value) {
        this.gender = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String value) {
        this.status = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NewUser newUser)) return false;
        return
                getName().equals(newUser.getName()) &&
                getEmail().equals(newUser.getEmail())&&
                getGender().equals(newUser.getGender())&&
                getStatus().equals(newUser.getStatus());
    }
}
