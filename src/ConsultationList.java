import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

//----class that used to print the consultation list---------
public class ConsultationList extends AbstractTableModel {

    private String[] columnNames = {"Patient Name", "Patient ID", "Doctor","Doc LicenseNO","Date"};
    private ArrayList<Consultation> myList;

    public ConsultationList(ArrayList<Consultation> concList) {
        myList = concList;

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
            temp = myList.get(row).getPatient().getName();
        } else if (col == 1) {
            temp = myList.get(row).getPatient().getPatientID();
        } else if (col == 2) {
            temp = myList.get(row).getDoctor().getName();
        } else if (col==3) {
            temp=myList.get(row).getDoctor().getLicenseNumber();

        } else if (col==4) {
            temp=myList.get(row).getDataAndTime();
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


