package jesper.summer.exception;

public class DeviceNotFoundException extends BusinessException {
    public DeviceNotFoundException(Long id) {
        super("Device not found with id: " + id);
    }
}