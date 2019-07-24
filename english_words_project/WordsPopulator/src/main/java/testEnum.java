public enum testEnum {
    HIGH  (3),  //calls constructor with value 3
    MEDIUM(2),  //calls constructor with value 2
    LOW   (1); //calls constructor with value 1
     // semicolon needed when fields / methods follow


    private final int data;

    private testEnum(int levelCode) {
        this.data = levelCode;
    }
}

