package space.stemfun.stemfun;

public enum Field {
    SCIENCE(0),
    TECHNOLOGY(1),
    ENGINEERING(2),
    MATH(3),
    EMPTY(-1);

    private final int fieldCode;

    Field(int levelCode) {
        this.fieldCode = levelCode;
    }

    public int getFieldValue() {
        return this.fieldCode;
    }
}
