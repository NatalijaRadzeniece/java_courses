package model.enums;

public enum CourseName {

    QA1("Manual Testing 1"),
    QA2("Automation testing 2"),
    QA3("Advanced testing 2"),
    Java("Java for Beginners : Web Development");

    private String name;

    CourseName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
