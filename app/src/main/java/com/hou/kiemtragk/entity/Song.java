package com.hou.kiemtragk.entity;

public class Song {
    public int id;
    public String name;
    public int path;

    public Song(int id, String name, int path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPath() {
        return this.path;
    }

    public void setPath(int path) {
        this.path = path;
    }
}
