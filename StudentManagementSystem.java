import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class StudentManagementSystem extends JFrame implements ActionListener {

    JLabel lblId, lblName, lblCourse;

    JTextField txtId, txtName, txtCourse;

    JButton btnAdd;
    JButton btnView;
    JButton btnSearch;
    JButton btnUpdate;
    JButton btnDelete;

    JTextArea displayArea;

    ArrayList<Student> students = new ArrayList<>();

    public StudentManagementSystem() {

        setTitle("Student Management System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        lblId = new JLabel("Student ID:");
        txtId = new JTextField(20);

        lblName = new JLabel("Student Name:");
        txtName = new JTextField(20);

        lblCourse = new JLabel("Course:");
        txtCourse = new JTextField(20);

        btnAdd = new JButton("Add");
        btnView = new JButton("View");
        btnSearch = new JButton("Search");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");

        displayArea = new JTextArea(15,45);
        displayArea.setEditable(false);

        add(lblId);
        add(txtId);

        add(lblName);
        add(txtName);

        add(lblCourse);
        add(txtCourse);

        add(btnAdd);
        add(btnView);
        add(btnSearch);
        add(btnUpdate);
        add(btnDelete);

        add(new JScrollPane(displayArea));

        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnSearch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);

        setVisible(true);
    }
        @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btnAdd){

            String id=txtId.getText();
            String name=txtName.getText();
            String course=txtCourse.getText();

            students.add(new Student(id,name,course));

            JOptionPane.showMessageDialog(this,"Student Added Successfully");

            txtId.setText("");
            txtName.setText("");
            txtCourse.setText("");
        }

        else if(e.getSource()==btnView){

            displayArea.setText("");

            for(Student s:students){
                displayArea.append(s.toString()+"\n");
            }

        }

        else if(e.getSource()==btnSearch){

            String id=txtId.getText();

            boolean found=false;

            for(Student s:students){

                if(s.getId().equals(id)){

                    displayArea.setText(s.toString());

                    found=true;

                    break;
                }

            }

            if(!found){
                JOptionPane.showMessageDialog(this,"Student Not Found");
            }

        }

        else if(e.getSource()==btnUpdate){

            String id=txtId.getText();

            boolean found=false;

            for(Student s:students){

                if(s.getId().equals(id)){

                    s.setName(txtName.getText());
                    s.setCourse(txtCourse.getText());

                    JOptionPane.showMessageDialog(this,"Student Updated");

                    found=true;

                    break;
                }

            }

            if(!found){

                JOptionPane.showMessageDialog(this,"Student Not Found");

            }

        }

        else if(e.getSource()==btnDelete){

            String id=txtId.getText();

            boolean found=false;

            for(int i=0;i<students.size();i++){

                if(students.get(i).getId().equals(id)){

                    students.remove(i);

                    JOptionPane.showMessageDialog(this,"Student Deleted");

                    found=true;

                    break;

                }

            }

            if(!found){

                JOptionPane.showMessageDialog(this,"Student Not Found");

            }

        }

    }

    public static void main(String[] args) {

        new StudentManagementSystem();

    }

}