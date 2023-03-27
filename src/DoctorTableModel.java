import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

//---class that used to create the doctors list in a table------
public class DoctorTableModel extends AbstractTableModel {

    private String[] columnNames = {"Name", "LicenseNo", "Specialisation"};
    private ArrayList<Doctor> myList;

    public DoctorTableModel(ArrayList<Doctor> dcList) {
        List<Doctor> sortedDoctors = new ArrayList<>(dcList);
        sortedDoctors.sort(Comparator.comparing(Doctor::getSurname));
        myList = dcList;

    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return myList.size();
    }

    public Object getValueAt(int row, int col) {
        Object temp = null;
        if (col == 0) {
            temp = myList.get(row).getName();
        } else if (col == 1) {
            temp = myList.get(row).getLicenseNumber();
        } else if (col == 2) {
            temp = myList.get(row).getSpecialisation();
        }
        return temp;

    }

    // needed to show column names in JTable
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Class getColumnClass(int col) {
        if (col == 2) {
            return String.class;
        } else {
            return String.class;
        }
    }


}


