package com.company;

public class Removers {
    String part;
    boolean was_glued;

    public Removers(String part) {
        this.part = part;
        this.was_glued = false;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public boolean is_true() {
        return was_glued;
    }

    public void set_True() {
        this.was_glued = true;
    }

    @Override
    public String toString() {
        return "part= '" + part + '\'' +
                ", was_glued= " + was_glued +
                '}';
    }
}
