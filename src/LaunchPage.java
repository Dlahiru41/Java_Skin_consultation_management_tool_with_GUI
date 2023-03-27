import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

public class LaunchPage implements ActionListener {
    JFrame frame = new JFrame();
    JButton btn = new JButton("View Doctors");
    JButton btn2 = new JButton("Add a consultation");
    LaunchPage(){

        btn.setBounds(100,160,200,40);
        btn.setFocusable(false);
        btn.addActionListener(this);
        btn2.setBounds(100,260,200,40);
        btn2.setFocusable(false);
        btn2.addActionListener(this);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(null); // can customize margins with this
        frame.setVisible(true);
        frame.add(btn);
        frame.add(btn2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //-------------opens the doctor list window-------------------
        if(e.getSource() == btn){
            frame.dispose();
//            Learning_GUI.OpenNewWindow.NewWindow window = new Learning_GUI.OpenNewWindow.NewWindow();
            DoctorTableModel tableModel = new DoctorTableModel(WestminsterSkinConsultationManager.doctors);

            JTable table = new JTable(tableModel);

            // add to the container and continue the code
            JScrollPane scrollPane = new JScrollPane(table);
            table.setGridColor(Color.black);
            JFrame frame= new JFrame("doctor table");
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
//--------------------------------------------------------------------------------------------------
            //----opnes the consultaion form------------
        }
        if(e.getSource() == btn2){
            frame.dispose();
            ConsultationTableModel con = new ConsultationTableModel();
        }
    }
}
