package space.stemfun.stemfun.Classrooms;

import java.util.ArrayList;

import space.stemfun.stemfun.UnderLevel;
import space.stemfun.stemfun.User;

public class ClassroomData {

   public ClassroomData (String name){
        levels = new ArrayList<>();
        students = new ArrayList<>();
        this.name = name;
    }

    private ArrayList<UnderLevel> levels;
    private String name;
    private ArrayList<User> students;
    private ArrayList<Note> notes;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<User> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<User> students) {
        this.students = students;
    }

    public void addStudent (User user){
        students.add(user);

    }

    public ArrayList<UnderLevel> getLevels() {
        return levels;
    }

    public void addLevel (UnderLevel level){

        levels.add(level);
    }

    public void setLevels(ArrayList<UnderLevel> levels) {
        this.levels = levels;
    }


    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void addNote (Note note){
        notes.add(note);
    }
}
