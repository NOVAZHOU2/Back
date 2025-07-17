package jesper.summer.exception;

public class BaiduApiException extends Exception {
    private int errorCode;

    public BaiduApiException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return "百度API错误 [" + errorCode + "]: " + super.getMessage();
    }
}