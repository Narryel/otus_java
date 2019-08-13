package ru.otus.homework2;

public class BasicDto {
    private String name;
    private int number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BasicDto() {
    }

    @Override
    public String toString() {
        return "BasicDto{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }

    public BasicDto(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
