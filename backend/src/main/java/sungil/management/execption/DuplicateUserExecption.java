package sungil.management.execption;

public class DuplicateUserExecption extends Exception{
    private static final long serialVersionUID = 1L;

    public DuplicateUserExecption(){
        super("DUPLICATE_USER");
    }

}
