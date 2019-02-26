package id.kiosku.utils.exceptions;

public class InvalidMacCryptoException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Invalid Mac";
    }
}
