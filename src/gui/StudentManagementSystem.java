package gui;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;


import database.DatabaseConnection;



public class StudentManagementSystem extends JFrame implements ActionListener {


    JTextField txtName,txtCourse,txtPhone,txtEmail,txtGender;

    JTextArea displayArea;


    JButton btnAdd,btnView,btnSearch,btnUpdate,btnDelete;



    public StudentManagementSystem(){


        setTitle("Student Management System");

        setSize(700,600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new FlowLayout());



        txtName=new JTextField(20);
        txtCourse=new JTextField(20);
        txtPhone=new JTextField(20);
        txtEmail=new JTextField(20);
        txtGender=new JTextField(20);



        add(new JLabel("Name"));
        add(txtName);

        add(new JLabel("Course"));
        add(txtCourse);

        add(new JLabel("Phone"));
        add(txtPhone);

        add(new JLabel("Email"));
        add(txtEmail);

        add(new JLabel("Gender"));
        add(txtGender);



        btnAdd=new JButton("Add");

        btnView=new JButton("View");

        btnSearch=new JButton("Search");

        btnUpdate=new JButton("Update");

        btnDelete=new JButton("Delete");



        add(btnAdd);
        add(btnView);
        add(btnSearch);
        add(btnUpdate);
        add(btnDelete);



        displayArea=new JTextArea(20,55);

        displayArea.setEditable(false);

        add(new JScrollPane(displayArea));



        btnAdd.addActionListener(this);
        btnView.addActionListener(this);
        btnSearch.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);



        setVisible(true);

    }




    public void actionPerformed(ActionEvent e){



        if(e.getSource()==btnAdd){


            try{


                Connection con=
                DatabaseConnection.getConnection();



                String sql=
                "INSERT INTO students(name,course,phone,email,gender) VALUES(?,?,?,?,?)";



                PreparedStatement pst=
                con.prepareStatement(sql);



                pst.setString(1,txtName.getText());

                pst.setString(2,txtCourse.getText());

                pst.setString(3,txtPhone.getText());

                pst.setString(4,txtEmail.getText());

                pst.setString(5,txtGender.getText());



                pst.executeUpdate();



                JOptionPane.showMessageDialog(this,
                "Student Added Successfully");

                clear();



            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                ex.getMessage());

            }

        }



        else if(e.getSource()==btnView){


            try{


                Connection con=
                DatabaseConnection.getConnection();


                Statement st=con.createStatement();


                ResultSet rs=
                st.executeQuery("SELECT * FROM students");



                displayArea.setText("");



                while(rs.next()){


                    displayArea.append(

                    "ID: "+rs.getInt("id")+
                    "\nName: "+rs.getString("name")+
                    "\nCourse: "+rs.getString("course")+
                    "\nPhone: "+rs.getString("phone")+
                    "\nEmail: "+rs.getString("email")+
                    "\nGender: "+rs.getString("gender")+
                    "\n----------------------\n"

                    );

                }



            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                ex.getMessage());

            }

        }



        else if(e.getSource()==btnSearch){


            try{


                int id=Integer.parseInt(
                JOptionPane.showInputDialog("Enter ID"));



                Connection con=
                DatabaseConnection.getConnection();



                PreparedStatement pst=
                con.prepareStatement(
                "SELECT * FROM students WHERE id=?");



                pst.setInt(1,id);



                ResultSet rs=pst.executeQuery();



                if(rs.next()){


                    displayArea.setText(

                    "Name: "+rs.getString("name")+
                    "\nCourse: "+rs.getString("course")+
                    "\nPhone: "+rs.getString("phone")+
                    "\nEmail: "+rs.getString("email")+
                    "\nGender: "+rs.getString("gender")

                    );


                }else{

                    JOptionPane.showMessageDialog(this,
                    "Student not found");

                }



            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                ex.getMessage());

            }

        }



        else if(e.getSource()==btnDelete){


            try{


                int id=Integer.parseInt(
                JOptionPane.showInputDialog("Enter ID"));



                Connection con=
                DatabaseConnection.getConnection();



                PreparedStatement pst=
                con.prepareStatement(
                "DELETE FROM students WHERE id=?");



                pst.setInt(1,id);


                pst.executeUpdate();



                JOptionPane.showMessageDialog(this,
                "Student Deleted");



            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                ex.getMessage());

            }

        }




        else if(e.getSource()==btnUpdate){


            try{


                int id=Integer.parseInt(
                JOptionPane.showInputDialog("Enter ID"));



                Connection con=
                DatabaseConnection.getConnection();



                PreparedStatement pst=
                con.prepareStatement(

                "UPDATE students SET name=?,course=?,phone=?,email=?,gender=? WHERE id=?"

                );



                pst.setString(1,txtName.getText());
                pst.setString(2,txtCourse.getText());
                pst.setString(3,txtPhone.getText());
                pst.setString(4,txtEmail.getText());
                pst.setString(5,txtGender.getText());
                pst.setInt(6,id);



                pst.executeUpdate();



                JOptionPane.showMessageDialog(this,
                "Student Updated");



            }catch(Exception ex){

                JOptionPane.showMessageDialog(this,
                ex.getMessage());

            }

        }

    }




    void clear(){

        txtName.setText("");
        txtCourse.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        txtGender.setText("");

    }




    public static void main(String[] args){

        new StudentManagementSystem();

    }

}