import java.util.Arrays;
import java.util.Date;

public class Consultation {
    //-------DATA FIELDS----------
    private String dataAndTime;
    private int cost;
    private byte[] notes;
    Doctor doctor;
    Patient patient;
//-------------------------------------
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doc) {
        this.doctor=doc;
        this.doctor.setPatientcount();

    }
//-----!!SET PATIENT METHOD----------
    public void setPatient(String name,String surname,String dob,String mob,String id) {
        this.patient=new Patient();
        patient.setName(name);
        patient.setSurname(surname);
        patient.setDateOfBirth(dob);
        patient.setMobileNumber(mob);
        patient.setPatientID(id);
    }
//---------------------------------------------------------------------
    //-----GWTTERS AND SETTERS---------
    public Patient getPatient() {
        return patient;
    }

    public String getDataAndTime() {
        return dataAndTime;
    }

    public void setDataAndTime(String dataAndTime) {
        this.dataAndTime = dataAndTime;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public byte[] getNotes() {
        return notes;
    }

    public void setNotes(byte [] notes) {
        this.notes = notes;
    }
//---------------------------------------------------------------
  //======CONSTRUCTOR==============
    Consultation( String d, byte[] notes){
        this.dataAndTime=d;
        this.notes=notes;
        cost=25;// cost 25 dollars per two hours
  }
//==============================================================
    @Override
    //====TOSTRING FOR A TESTING PURPOSE==========
    public String toString() {
        return "Consultation{" +
                "dataAndTime='" + dataAndTime + '\'' +
                ", cost=" + cost +
                ", notes=" + notes +
                ", doctor=" + doctor +
                ", patient=" + patient +
                '}';
    }
    //===========================================================
}
