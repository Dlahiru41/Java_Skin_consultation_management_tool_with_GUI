import java.io.Serializable;

public class Doctor extends Person {
    private String licenseNumber;
    private String specialisation;
    private int patientcount=0;

    public void setPatientcount() {
        patientcount+=1;
    }

    public int getPatientcount() {
        return patientcount;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

//    @Override
//    public String toString() {
//        return "Doctor{" +
//                "licenseNumber='" + licenseNumber + '\'' +
//                ", specialisation='" + specialisation + '\'' +
//                '}';
//    }
}
