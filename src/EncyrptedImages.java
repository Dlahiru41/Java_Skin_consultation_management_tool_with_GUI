
public class EncyrptedImages {
    private byte[] encryptedImageData;
    private String patienID;

    public byte[] getEncryptedImageData() {
        return encryptedImageData;
    }

    public String getPatienID() {
        return patienID;
    }

    public void setPatienID(String patienID) {
        this.patienID = patienID;
    }

    public void setEncryptedImageData(byte[] encryptedImageData) {
        this.encryptedImageData = encryptedImageData;
    }
}
