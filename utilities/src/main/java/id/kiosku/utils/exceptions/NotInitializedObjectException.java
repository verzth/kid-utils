package id.kiosku.utils.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * Created by Dodi on 01/08/2018.
 */

public class NotInitializedObjectException extends RuntimeException {
    private Throwable throwable;
    private String message="Object not Initialized before, please initialize it in your application class.";
    public NotInitializedObjectException(){
        super();
        System.err.println(message);
    }
    public NotInitializedObjectException(String message){
        super(message);
        this.message = message;
    }

    public NotInitializedObjectException(Throwable throwable){
        super();
        System.err.println(message);

        this.throwable = throwable;
    }
    public NotInitializedObjectException(String message,Throwable throwable){
        super(message);
        this.message = message;
        this.throwable = throwable;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Throwable getCause() {
        return throwable;
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
        if(throwable!=null){
            throwable.printStackTrace();
            System.err.println(message);
        }
    }

    @Override
    public void printStackTrace(PrintStream err) {
        super.printStackTrace(err);
        if(throwable!=null){
            throwable.printStackTrace(err);
            System.err.println(message);
        }
    }

    @Override
    public void printStackTrace(PrintWriter err) {
        super.printStackTrace(err);
        if(throwable!=null){
            throwable.printStackTrace(err);
            System.err.println(message);
        }
    }
}
