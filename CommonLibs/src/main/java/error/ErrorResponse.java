package error;

public class ErrorResponse<T> implements java.io.Serializable  {
    private String message;
    private int error = -1;
    private String id;
    T response;
    public ErrorResponse( int error, String message )
    {
        this.error = error;
        this.message = message;
    }
    public ErrorResponse( int error, String message, T response )
    {
        this.error = error;
        this.message = message;
        this.response = response;
    }
    public ErrorResponse( int error, String message, T response, String id )
    {
        this.error = error;
        this.message = message;
        this.response = response;
        this.id = id;
    }
    public ErrorResponse( int error, String message,  String id )
    {
        this.error = error;
        this.message = message;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
