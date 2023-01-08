package error;

public class ErrorResponse implements java.io.Serializable  {
    private String message;
    private int error = -1;

    public ErrorResponse( int error, String message )
    {
        this.error = error;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public boolean _isSuccess()
    {
        return this.error == ErrorConstant.SUCCESS;
    }
}
