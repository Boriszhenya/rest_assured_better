package org.example;

public class ColorEntity {
    private String id;
    private String name;
    private SmallData data;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColorEntity post))
            return false;
        return
                getName().equals(post.getName()) &&
                        getData().equals(post.getData());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SmallData getData() {
        return data;
    }

    public void setData(SmallData data) {
        this.data = data;
    }
}
