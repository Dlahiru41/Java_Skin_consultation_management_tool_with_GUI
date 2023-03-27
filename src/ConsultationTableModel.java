
import javax.crypto.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class ConsultationTableModel extends JFrame {
    Consultation consul;//----creates a consultation object
    Doctor conDoctor;//----creates a docotr object
    //==== initialising variables that are used to creat objects in the later part of the code=========
    String patientName;
    String paientSurname;
    String patienMobileNumebr;
    String patienDateofBirth;
    String consultationDate;
    String consultationNotes;
    String doctorName;
    String patientID;
//===================================================================================
    //=====GUI COMPONENTS===========
    JMenuBar mb = new JMenuBar(); // main method for menu bar
    JMenu fileMenu = new JMenu("File");
    JMenu edit = new JMenu("edit");
    JMenu helpMenu = new JMenu("Help"); // top three items in the menu bar
    JMenuItem loadItem = new JMenuItem("Load");
    JMenuItem saveItem = new JMenuItem("Save");
    JMenuItem con=new JMenuItem("cList");
    JMenuItem exitItem = new JMenuItem("Exit"); // three items in the menu bar

    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField tf1, tf2, tf5, tf8;
    JTextArea tf6, ta9;
    JButton btn1, btn2, btn3;
    JComboBox month, year, date, month1, date1, year1;
    //==================================================
    public JComboBox comboBox;
    public static String condoc;

    //=====USE FOR SELECT DATE COMBOBOXES===========
    private String dates[]
            = {"1", "2", "3", "4", "5",
            "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15",
            "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25",
            "26", "27", "28", "29", "30",
            "31"};
    private String months[]
            = {"Jan", "feb", "Mar", "Apr",
            "May", "Jun", "July", "Aug",
            "Sup", "Oct", "Nov", "Dec"};
    private String years[]
            = {"1995", "1996", "1997", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022"};
    private String years1[] = {
            "2023"
    };
    //====================================================================

    public void disposeFrame() {
        // Dispose of the JFrame
        dispose();
    }
//====CONSTRUCTOR===========
    ConsultationTableModel() {
        setVisible(true);
        setSize(716, 780);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Consultation registration form");
//-----------------labels-----------------
        l1 = new JLabel("Consultation form");
        l1.setForeground(Color.blue);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2 = new JLabel("Patient Name:");
        l3 = new JLabel("Patient Surname");
        l4 = new JLabel("Patient Mobile");
        l5 = new JLabel("DOB");
        l6 = new JLabel("Consultation Date");
        l7 = new JLabel("Consultation notes");
        l8 = new JLabel("Consultation Doctor");
        l9 = new JLabel("Patient ID");
//--------------------------------------------------------
        //-----text fields---------
        tf1 = new JTextField();
        tf2 = new JTextField();

        tf5 = new JTextField();
        tf6 = new JTextArea();//-----text area (for patient notes)---
        tf8 = new JTextField();
        ta9 = new JTextArea();//--text area(for display the input summary)---
//------------------------------------------------------
        //---buttons-------
        btn1 = new JButton("Submit");
        btn2 = new JButton("Clear");
        btn3 = new JButton("Upload image");
        myListner btn = new myListner();
        btn1.addActionListener(btn);
        btn2.addActionListener(btn);
        btn3.addActionListener(btn);
        //-----=====-------

        //=============================================================
        l1.setBounds(171, 62, 400, 30);
        l1.setFont(new Font("Arial", Font.PLAIN, 20));

        l2.setBounds(13, 152, 193, 25);
        l2.setFont(new Font("Arial", Font.PLAIN, 20));

        l3.setBounds(13, 196, 193, 25);
        l3.setFont(new Font("Arial", Font.PLAIN, 20));

        l4.setBounds(13, 240, 193, 25);
        l4.setFont(new Font("Arial", Font.PLAIN, 20));

        l5.setBounds(13, 284, 193, 25);
        l5.setFont(new Font("Arial", Font.PLAIN, 20));

        l6.setBounds(13, 323, 193, 25);
        l6.setFont(new Font("Arial", Font.PLAIN, 20));

        l7.setBounds(13, 367, 193, 25);
        l7.setFont(new Font("Arial", Font.PLAIN, 20));

        l8.setBounds(13, 525, 193, 25);//*****
        l8.setFont(new Font("Arial", Font.PLAIN, 20));

        l9.setBounds(13, 571, 193, 25);
        l9.setFont(new Font("Arial", Font.PLAIN, 20));

        tf1.setBounds(222, 152, 220, 25);
        tf2.setBounds(222, 196, 220, 25);

        tf5.setBounds(222, 241, 220, 25);
        tf6.setBounds(222, 366, 220, 141);
        tf6.setLineWrap(true);

        tf8.setBounds(222, 571, 220, 25);
        ta9.setBounds(479, 152, 191, 242);
        ta9.setLineWrap(true);

        btn1.setBounds(37, 614, 100, 25);
        btn2.setBounds(222, 614, 100, 25);
        btn3.setBounds(479,664,100,25);

        date = new JComboBox(dates);
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSize(50, 20);
        date.setLocation(222, 282);
        add(date);

        month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSize(60, 20);
        month.setLocation(290, 282);
        add(month);

        year = new JComboBox(years);
        year.setFont(new Font("Arial", Font.PLAIN, 15));
        year.setSize(60, 20);
        year.setLocation(370, 282);
        add(year);
//-------------------------------------------------------------------------------
        date1 = new JComboBox(dates);
        date1.setFont(new Font("Arial", Font.PLAIN, 15));
        date1.setSize(50, 20);
        date1.setLocation(222, 325);
        add(date1);

        month1 = new JComboBox(months);
        month1.setFont(new Font("Arial", Font.PLAIN, 15));
        month1.setSize(60, 20);
        month1.setLocation(290, 325);
        add(month1);

        year1 = new JComboBox(years1);
        year1.setFont(new Font("Arial", Font.PLAIN, 15));
        year1.setSize(60, 20);
        year1.setLocation(370, 325);
        add(year1);
//----------------------------------------------------------------------------
//====================================================================================

        // Create a DefaultComboBoxModel
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();

        // Iterate through the list of Person objects and add their names to the model
        for (Doctor doc : WestminsterSkinConsultationManager.doctors) {
            model.addElement(doc.getName());
        }

        // Create a JComboBox and set the model as its model
        JComboBox<String> comboBox = new JComboBox<>(model);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
        comboBox.setSize(60, 20);
        comboBox.setLocation(222, 525);
        add(comboBox);
//----adding all the GUI components to the frome--------
        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(l4);
        add(l5);
        add(l6);
        add(tf5);
        add(l7);
        add(tf6);
        add(l8);
        add(l9);
        add(tf8);
        add(ta9);
        add(btn1);
        add(btn2);
        add(btn3);
//----------------------------------------------------------------
        loadItem.addActionListener(btn);
        saveItem.addActionListener(btn);
        exitItem.addActionListener(btn);
        con.addActionListener(btn);
        //======GENERATING KEY BOARD SHORT CUTS=========

        fileMenu.setMnemonic(KeyEvent.VK_F); // for main menu items u need to use alt+key for this to work
        edit.setMnemonic(KeyEvent.VK_E);
        helpMenu.setMnemonic(KeyEvent.VK_H);

        loadItem.setMnemonic(KeyEvent.VK_L); // this sets keyboard key "l" as a shortcut key
        saveItem.setMnemonic(KeyEvent.VK_S);
        exitItem.setMnemonic(KeyEvent.VK_E);
//============================================================
        fileMenu.add(loadItem);
        fileMenu.add(saveItem);
        fileMenu.add(con);
        fileMenu.add(exitItem); //file menu is given these three items as sub items

        mb.add(fileMenu);
        mb.add(edit);
        mb.add(helpMenu); // added the three main items onto the menu bar

        this.setJMenuBar(mb); // setting the menu bar visible
        this.setVisible(true);

        setVisible(true);
        condoc = (String) comboBox.getSelectedItem();


    }
    //-------PRIVATE CLASS TO IMPLEMENT ACTIONLISTENER-----------

    private class myListner implements ActionListener {
        //CREATING ERROR MESSAGES TO USE IN NECCESSARY OCCASIONS------
        public void error() {
            JOptionPane.showMessageDialog(null, "Some of the fields are not filled!",
                    "Westminster Skin Consultation", JOptionPane.ERROR_MESSAGE);
        }

        public void errorMessageDocNotAvailable() {
            JOptionPane.showMessageDialog(null, "Doctor is not available random doctor will be assigned",
                    "Westminster Skin Consultation", JOptionPane.ERROR_MESSAGE);
        }
//------------------------------------------------------------------------------------------------
        @Override
        public void actionPerformed(ActionEvent e) {
            int count=0;
            //--------checks whether every text fields are filled by the user----------
            if (btn1 == e.getSource()) {
                if (tf1.getText().trim().isEmpty()) {
                    error();
                    count=1;
                } else if (tf2.getText().trim().isEmpty()) {
                    error();
                    count=1;
                } else if (tf5.getText().trim().isEmpty()) {
                    error();
                    count=1;
                } else if (tf6.getText().trim().isEmpty()) {
                    error();
                    count=1;
                } else if (tf8.getText().trim().isEmpty()) {
                    error();
                    count=1;
                }
                //--------------------------------------------------
                //--------display the user inputs to the user as summary in a text area----------
                else if(count==0){
                String data1="Patient Name: "+tf1.getText()+"\n";
                String data2="Patient Surname: "+tf2.getText()+"\n";
                String data3="Patient Mobile: "+tf5.getText()+"\n";
                String data4="Patient ID: "+tf8.getText()+"\n";
                ta9.setText(data1+data2+data3+data4);
                ta9.setEditable(false);
                //--------------------------------------------------------------------------------
                //======SUBMIT BUTTON===========

                    patientName = tf1.getText();
                    paientSurname = tf2.getText();
                    patienDateofBirth = years[year.getSelectedIndex()] + "-" + months[month.getSelectedIndex()] + "-" + dates[date.getSelectedIndex()];
                    consultationDate = years1[year1.getSelectedIndex()] + "-" + months[month1.getSelectedIndex()] + "-" + dates[date1.getSelectedIndex()];
//----------------------------------------------------------------------------------------------
                    //-----encrypt the patient using the AES algorithm------------
                    consultationNotes = tf6.getText();
                    KeyGenerator keyGenerator = null;
                    try {
                        keyGenerator = KeyGenerator.getInstance("AES");
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                    keyGenerator.init(128); // key size in bits
                    SecretKey secretKey = keyGenerator.generateKey();
                    Cipher cipher = null;
                    try {
                        cipher = Cipher.getInstance("AES");
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoSuchPaddingException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
                    } catch (InvalidKeyException ex) {
                        throw new RuntimeException(ex);
                    }
                    byte[] encryptedNote;
                    try {
                        encryptedNote = cipher.doFinal(consultationNotes.getBytes());
                    } catch (IllegalBlockSizeException ex) {
                        throw new RuntimeException(ex);
                    } catch (BadPaddingException ex) {
                        throw new RuntimeException(ex);
                    }

//------------------------------------------------------------------------------------------------

                    //intialising the conDoctor according to the user input doctr in the comboBox----
                    for (Doctor d : WestminsterSkinConsultationManager.doctors) {
                        if (condoc.equals(d.getName())) {
                            System.out.println(condoc);
                            conDoctor = d;
//                          d.setPatientcount();
//                          System.out.println(d.getPatientcount());

                        }
                    }
                    patienMobileNumebr = tf5.getText();
                    patientID = tf8.getText();

                    //--------------------------------------------------------
                    if (conDoctor.getPatientcount() >= 4) {
                        errorMessageDocNotAvailable();
                        conDoctor = randomSelectdoctor(consultationDate);

                    }
                    Consultation consul = new Consultation(consultationDate, encryptedNote);
                    consul.setPatient(patientName, paientSurname, patienDateofBirth, patienMobileNumebr, patientID);
                    consul.setDoctor(conDoctor);
                    WestminsterSkinConsultationManager.consultations.add(consul);
                    System.out.println(WestminsterSkinConsultationManager.consultations);
                    System.out.println(consul);
                    System.out.println(WestminsterSkinConsultationManager.consultations);


                }
            }
            //===========================================================================
            //clear button---------------------------
            else if (btn2 == e.getSource()) {
                String def = "";
                tf1.setText(def);
                tf2.setText(def);
                tf5.setText(def);
                tf6.setText(def);
                tf8.setText(def);
                ta9.setText(def);

                date.setSelectedIndex(0);
                month.setSelectedIndex(0);
                year.setSelectedIndex(0);

                date1.setSelectedIndex(0);
                month1.setSelectedIndex(0);
                year1.setSelectedIndex(0);
            }
            //========IMAGE UPLOAD BUTTON================
            else if (btn3 == e.getSource()) {
                EncyrptedImages enimg=new EncyrptedImages();//--creates an object to store the encrypted image byte code and patient ID

                //-------creating a file chooser to upload an image----------
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int result = fileChooser.showOpenDialog(null);
                //------encrypts the uploaded image----------
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    KeyGenerator keyGenerator = null;
                    try {
                        keyGenerator = KeyGenerator.getInstance("AES");
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                    keyGenerator.init(128); // key size in bits
                    SecretKey secretKey = keyGenerator.generateKey();
                    byte[] imageData = new byte[0];
                    try {
                        imageData = Files.readAllBytes(selectedFile.toPath());
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    Cipher cipher = null;
                    try {
                        cipher = Cipher.getInstance("AES");
                    } catch (NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    } catch (NoSuchPaddingException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        cipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
                    } catch (InvalidKeyException ex) {
                        throw new RuntimeException(ex);
                    }
                    try {
                        byte[] encryptedImageData = cipher.doFinal(imageData);
                        enimg.setEncryptedImageData(encryptedImageData);
                        enimg.setPatienID(patientID = tf8.getText());
                        WestminsterSkinConsultationManager.EncyrptedImagesList.add(enimg);

                    } catch (IllegalBlockSizeException ex) {
                        throw new RuntimeException(ex);
                    } catch (BadPaddingException ex) {
                        throw new RuntimeException(ex);
                    }
                    catch(NullPointerException ex){
                        System.out.println("");
                    }
                }
            }
                //=======load the launch page ====================
                else if (e.getSource() == exitItem) {
//              ConsultationTableModel.AccessibleJFrame.;
//              ConsultationTableModel.dispose();
                    disposeFrame();
                    LaunchPage page = new LaunchPage();
                }
                //========CONSULTATIO LIST==============
            else if (e.getSource() == con) {
//              ConsultationTableModel.AccessibleJFrame.;
//              ConsultationTableModel.dispose();
                disposeFrame();
                ConsultationList conList=new ConsultationList(WestminsterSkinConsultationManager.consultations);
                JTable table = new JTable(conList);

                // add to the container and continue the code
                JScrollPane scrollPane = new JScrollPane(table);
                table.setGridColor(Color.black);
                JFrame frame= new JFrame("Consultation table");
//---------------after user close the window the program launches the laaunch page window again---------
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                                            @Override
                                            public void windowClosing(java.awt.event.WindowEvent e) {
                                                // Create a new JFrame and make it visible
                                                LaunchPage ln=new LaunchPage();
                                            }
                                        });

                frame.add(scrollPane);
                frame.setSize(450,450);
                frame.setVisible(true);


            }

                //----------------------------------------------------------------------------------

            //---------save the data into the "data.dat" file----------
                else if (e.getSource() == saveItem) {
                    try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
                        output.writeObject(WestminsterSkinConsultationManager.doctors);
                        output.writeObject(WestminsterSkinConsultationManager.consultations);
                        System.out.println("Data saved successfully");
                    } catch (IOException eii) {
                        System.out.println("Error saving data: " + eii.getMessage());
                    }

                }
                //----------------------------------------------------------------------------------
            //---------loads the data from the "data.dat" file----------
                else if (e.getSource() == loadItem) {
                    try (ObjectInputStream input = new ObjectInputStream(new FileInputStream("data.dat"))) {
                        WestminsterSkinConsultationManager.doctors = (ArrayList<Doctor>) input.readObject();
                        WestminsterSkinConsultationManager.consultations = (ArrayList<Consultation>) input.readObject();
                        System.out.println("Data loaded successfully");
                    } catch (IOException | ClassNotFoundException ej) {
                        System.out.println("Error loading data: " + ej.getMessage());
                    }
                }



        }
//-----------method that used to select a random doctor if the doctor is no available---------
        public Doctor randomSelectdoctor(String date) {
            Doctor dc = new Doctor();
            int randno;
            Random ran = new Random();
            for (Consultation c : WestminsterSkinConsultationManager.consultations) {
                randno = ran.nextInt(WestminsterSkinConsultationManager.doctors.size());
                dc = WestminsterSkinConsultationManager.doctors.get(randno);
                //if(c.getDataAndTime()==date) {
                if (c.getDoctor().getLicenseNumber() != dc.getLicenseNumber()) {
                    if (dc.getPatientcount() < 4) {
                        break;
                    }
                }
                //}
            }
            return dc;
        }
    }
}