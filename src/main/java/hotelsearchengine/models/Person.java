package hotelsearchengine.models;

public class Person {
    private String name;
    private String password;
    private int Id;

    public Person(String name, String password, int Id) {
        this.name = name;
        this.password = password;
        this.Id = Id;
    }
    public int getId() {
        return Id;
    }
}