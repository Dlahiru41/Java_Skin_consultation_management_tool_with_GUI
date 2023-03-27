import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;

public class WestminsterSkinConsultationManager implements  SkinConsultationManager{
    //==========STATIC LISTS===============
    public static ArrayList<Doctor> doctors;
    public static  ArrayList<Consultation> consultations;
    public static ArrayList<EncyrptedImages> EncyrptedImagesList;//---used to store the byte codes of the encrypted images with patiend ids---
    //=========================================================================================
    private  Scanner scanner;
    public WestminsterSkinConsultationManager(){
        this.doctors=new ArrayList<Doctor>();
        this.consultations=new ArrayList<Consultation>();
        this.scanner=new Scanner(System.in);
        console();
    }
//==========CONSOLE===================
    public void   console(){
        while (true) {
            System.out.println("Management action");
            System.out.println("1. Add a new doctor");
            System.out.println("2.Delete a doctor from the doctors' list");
            System.out.println("3. Print the list of the doctors");
            System.out.println("4. Save data");
            System.out.println("5. Load Data");
            System.out.println("6. Load GUI");
            System.out.println("7. Exit");
int command;
    while(true) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("enter your command");
            command = sc.nextInt();
            break;
        }
        catch (InputMismatchException e){
            System.out.println("invalid Input");
        }
    }
            if (command == 1) {
                Doctor d = new Doctor();
                addDoctor(d);
            }
            else if (command==2) {
                for (Doctor doctor : doctors) {
                    if (doctor == null) {
                        System.out.println("no dco");
                    } else {
                            removeDoctor();

                    }
                }

            }
            else if(command==3){
                printDoctors();
            }
            else if(command==4){
                saveData();
            }
            else if (command==5){
                loadData();
            }
            else if (command==6){
                LaunchPage pg=new LaunchPage();
            }
            else if (command==7){
                break;
            }
            else{
                System.out.println("Please enter a valid response");
            }

        }
    }
    //============================================================================
//--------------------------adding a doctor to the doctors list--------
    @Override
    public void addDoctor(Doctor d){
        if (this.doctors.size() >= 10) {
            System.out.println("The centre cannot allocate more than 10 doctors. Please remove a doctor first.");
            return;
        }

        Scanner sc = new Scanner(System.in);
       System.out.println("enter the name of the doctor");
       String nameOfTheDoctor= sc.next();
       d.setName(nameOfTheDoctor);
       //--------------------------------------------
       System.out.println("Enter the surname of the doctor");
       String surname=sc.next();
       d.setSurname(surname);
      //----------------------------------------------
        while(true) {
            System.out.println("Enter the Date of birth");
            String date = sc.next();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");//-----take the input in particular manner even though it's string
            Date date2=null;
            try {
                //Parsing the String
                date2 = dateFormat.parse(date);
                break;
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            d.setDateOfBirth(date);
        }

        //---------------------------------------------

        System.out.println("enter the mobile number of the doctor");
        String mobileNo=sc.next();
        d.setMobileNumber(mobileNo);
        //----------------------------------------------
        System.out.println("enter the license Number of the doctor");
        String licenseNo=sc.next();
        d.setLicenseNumber(licenseNo);
        //----------------------------------------------
        System.out.println("enter the specialisation of the doctor");
        String specialisation=sc.next();
        d.setSpecialisation(specialisation);
        //-----------------------adds the doctor
        doctors.add(d);


    }
    //---------------------------------------------------------------------------------------
    //remove a doctor using the license number------------
    @Override
    public void removeDoctor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the license number of the doctor you want to delete");
        String deletelicense = sc.next();
        Doctor doc = null;

//            for (Doctor d : doctors) {
//                if (d.getLicenseNumber().equals(deletelicense)) {
//                    doc=d;
//                    break;
//                }
//
//            //}
//    }
        Iterator<Doctor> iterator = doctors.iterator();
        while (iterator.hasNext()) {
            Doctor d = iterator.next();
            if (d.getLicenseNumber().equals(deletelicense)) {
                doc = d;
                iterator.remove();
                break;
            }
        }

        if (doc==null){//-------if no doctor is found----------
            System.out.println("No doctors were found");
        }
        else{
            //System.out.println("The doctor with license "+ doc.getLicenseNumber()+" was removed");
            doctors.remove(doc);

        }

}
//-------------------------------------------------------------------------------------------------

//--------------------------------------------------------------------------------
//---prints sorted doctors list----------------------------
    public void printDoctors() {
        List<Doctor> sortedDoctors = new ArrayList<>(doctors);
        sortedDoctors.sort(Comparator.comparing(Doctor::getSurname));

        System.out.println("--- Doctors ---");
        for (Doctor doctor : sortedDoctors) {
            System.out.println("Name: " + doctor.getName());
            System.out.println("Surname: " + doctor.getSurname());
            System.out.println("Date of birth: " + doctor.getDateOfBirth());
            System.out.println("Mobile number: " + doctor.getMobileNumber());
            System.out.println("Medical licence number: " + doctor.getLicenseNumber());
            System.out.println("Specialisation: " + doctor.getSpecialisation());
            System.out.println();
        }
    }
    //-----------------------------------------------------------------------------
    //save the data to a the file "data.dat"--------------(writes only the doctors list in here)
    public void saveData() {
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            output.writeObject(doctors);
//            output.writeObject(consultations);
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    //------------------------------------------------------------------------------

    //---load the data from the file "data.dat"--------------(loads only the doctors list in here)
    public void loadData() {
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data.dat"))) {
            doctors = (ArrayList<Doctor>) input.readObject();
//            consultations = (ArrayList<Consultation>) input.readObject();
            System.out.println("Data loaded successfully");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
//-----------------------------------------------------------------------------

}
