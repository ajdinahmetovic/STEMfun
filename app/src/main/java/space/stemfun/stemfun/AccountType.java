package space.stemfun.stemfun;

public enum AccountType {
    STUDENT(0),
    PROFESSOR(1);

    private int id;

    AccountType (int id) {
        this.id = id;
    }
    public int getValue (){
        return id;
    }

}
