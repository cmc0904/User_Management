package sungil.management.execption;

public class NotFoundUserExecption extends Exception{
    private static final long serialVersionUID = 1L;

    public NotFoundUserExecption(){
        super("NOT_FOUND_USER");
    }

}
