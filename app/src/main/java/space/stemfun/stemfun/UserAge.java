package space.stemfun.stemfun;

public enum UserAge {
    junior("junior"),
    senior("senior");

    private final String value;

    UserAge(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
