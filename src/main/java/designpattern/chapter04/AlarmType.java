package designpattern.chapter04;

public enum AlarmType {
    FAULT("fault"),
    RESUME("resume");

    private String message;

    AlarmType(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
