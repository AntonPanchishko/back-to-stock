package com.example.model;

public class User implements Subscriber {
    private String name;
    private boolean premium;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", premium=" + premium
                + ", age=" + age + '}';
    }

    @Override
    public String notifySubscriber() {
        return new String("Dear " + name
                + " the product on which you subscribed is present in your stock");
    }
}
