/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author AZYSS
 */
public class UserDataForm extends JDialog {
    private JTextField lastNameTextField ;  //1
    private JTextField firstNameTextField ;  //2
    private JTextField userNameTextField ;  // 3 Matric number
    private JPasswordField passwordTextField ;  //4
    private JPasswordField confirmPWTextField ; // 5
    private JTextField schoolNameTextField ;    //6
    private JTextField departmentNameTextField ;    //7
    private JTextField facultyNameTextField ;   //8
    private JComboBox totalSessionComboBox ;    //9
    private JComboBox gradeSystemComboBox ; //10
    
    static final int FULL_DATA_FORM = 1 ;
    static final int CHANGE_PASSWORD_FORM = 2 ;
    static final int CHANGE_DATA_FORM = 3 ;    // doesnt include the grade system, total session, and password fields for editing
    
    private String password ;
    private String firstName ;
    private String lastName ;
    private String userName ;
    private String schoolName ;
    private String facultyName ;
    private String departmentName ;
    private int totalSession ;
    private int gradeSystem ;
    
    private JButton okButton ;
    private JButton cancelButton ;
    
    private JLabel topLabel ;
    
    /**
     * Static method to display an editable form 
     * containing the user data
     * @param db database to load user data from
     * @param relativePositionComponent component to set relative location to 
     
    static void viewUserDataForm(final DB_Manipulator db, JComponent relativePositionComponent){
        final UserDataForm udf = new UserDataForm(UserDataForm.CHANGE_DATA_FORM, null);
        udf.getUserNameTextField().setText(db.getUserName()); //1
        udf.getFacultyNameTextField().setText(db.getFacultyName()); //2
        udf.getFirstNameTextField().setText(db.getLastName());  //3
        udf.getLastNameTextField().setText(db.getFirstName());  //4
        udf.getPasswordTextField().setText(db.getUserPassword());   //5
        udf.getConfirmPasswordField().setText(db.getUserPassword());  //6
        udf.getSchoolNameTextField().setText(db.getSchoolName());   //7
        udf.getDepartmentNameTextField().setText(db.getDepartmentName());   //8
        udf.getGradeSystemComboBox().setSelectedIndex((db.getGradeSystem() == 4)?1:2);  //Tenary Operator :)
        
        //set selected gradeSystemStyle
        //the db.getTotal sessions returns the total number of sessions 'including' the spill over session
        if(db.getTotalSessions() == 8)
            udf.totalSessionComboBox.setSelectedIndex(4);
        else if(db.getTotalSessions() == 7)
            udf.getTotalSessionComboBox().setSelectedIndex(3);
        else if(db.getTotalSessions() == 6)
            udf.getTotalSessionComboBox().setSelectedIndex(2);
        else if(db.getTotalSessions() == 5)
            udf.getTotalSessionComboBox().setSelectedIndex(1);
        udf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        udf.setVisible(true);
        udf.getOkButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if (udf.verifyInputs() ){
                    //save data
                    db.setDepartmentName(udf.getDepartmentName());
                    db.setFacultyName(udf.getFacultyName());
                    db.setSchoolName(udf.getSchoolName());
                    db.setUserName(udf.getUserName());
                    db.setLastName(udf.getLastName());
                    db.setFirstName(udf.getFirstName());
                    db.setUserPassword(udf.getPassword());
                    db.setTotalSessions(udf.getTotalSessions());
                    db.setGradeSystem(udf.getGradeSystem());
                    udf.dispose();
                    
                }
            }
        });
        
        udf.getCancelButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                udf.dispose();
            }
        });
        
        
    }
    */
    
    static void loadDBFullData(final DB_Manipulator db){
        final UserDataForm udf = new UserDataForm(UserDataForm.FULL_DATA_FORM, null);
        udf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        udf.setVisible(true);
        udf.setIconImage(new ImageIcon(UserDataForm.class.getClass().getResource("/multimedia/png/frameIcon.png")).getImage());
        //udf.getTitleLablel().setIcon(PNGGetter.getUserDataFormIcon());
        udf.getCancelButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) { 
                int i = JOptionPane.showConfirmDialog(udf, "Are you sure you want to Cancel this process ?"
                        , "End Registration", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
                if(i == JOptionPane.OK_OPTION)
                    System.exit(0);
            }
        });
        udf.getOkButton().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean cont = udf.verifyInputs();
                if(cont){
                    // necessary filling of data base here
                    db.setDepartmentName(udf.getDepartmentName());
                    db.setFacultyName(udf.getFacultyName());
                    db.setSchoolName(udf.getSchoolName());
                    db.setUserName(udf.getUserName());
                    db.setLastName(udf.getLastName());
                    db.setFirstName(udf.getFirstName());
                    db.setUserPassword(udf.getPassword());
                    db.setTotalSessions(udf.getTotalSessions());
                    db.setGradeSystem(udf.getGradeSystem());
                    udf.dispose();
                    //DB_Manipulator.this.createSemesterTables(DB_Manipulator.this.getTotalSemesters());
                    //DB_Manipulator.this.updateUseCount(); // update the count
                }
            }
        });
    }
    
    /**
     * 
     * @param formType type of form to create 
     */
    UserDataForm(int formType, final DB_Manipulator db){
        super();
        
        
        if (formType == UserDataForm.FULL_DATA_FORM){ 
            //surname stuff
            JLabel surnameLabel = new JLabel("Last Name  ");
            surnameLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.lastNameTextField = new JTextField(25);
            this.lastNameTextField.setDocument(new JTextFieldLimit(DB_Manipulator.FIRST_NAME_LENGTH));
        
            //other name stuff
            JLabel otherNameLabel = new JLabel("First Name  ");
            otherNameLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.firstNameTextField = new JTextField(25);
            this.firstNameTextField.setDocument(new JTextFieldLimit(DB_Manipulator.FIRST_NAME_LENGTH));
        
            // user name stuff
            JLabel userNameLabel = new JLabel("Matric No.  ");
            userNameLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.userNameTextField = new JTextField(25);
            this.userNameTextField.setDocument(new JTextFieldLimit(DB_Manipulator.USER_NAME_LENGTH));
        
            //userPassword stuff
            JLabel userPasswordLabel = new JLabel("Password  ");
            userPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.passwordTextField = new JPasswordField(25);
            this.passwordTextField.setDocument(new JTextFieldLimit(DB_Manipulator.USER_PASSWORD_LENGTH));
        
            //confirm user password stuff
            JLabel confirmUserPasswordLabel = new JLabel("  Confirm Password  ");
            confirmUserPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.confirmPWTextField = new JPasswordField(25);
            this.confirmPWTextField.setDocument(new JTextFieldLimit(DB_Manipulator.USER_PASSWORD_LENGTH));
        
            //school name stuff
            JLabel schoolNameLabel = new JLabel("School  ");
            schoolNameLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.schoolNameTextField = new JTextField(25);
            this.schoolNameTextField.setDocument(new JTextFieldLimit(DB_Manipulator.SCHOOL_NAME_LENGTH));
            //JScrollPane schoolScrollPane = new JScrollPane(this.schoolNameTextField);
        
            //Faculty name stuff
            JLabel facultyNameLabel = new JLabel("Faculty  ");
            facultyNameLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.facultyNameTextField = new JTextField(25);
            this.facultyNameTextField.setDocument(new JTextFieldLimit(DB_Manipulator.FACULTY_NAME_LENGTH));
            //JScrollPane facultyScrollPane = new JScrollPane(this.facultyNameTextField);
        
            //Department name stuff
            JLabel departmentNameLabel = new JLabel("Department  ");
            departmentNameLabel.setHorizontalAlignment(JLabel.RIGHT);
            this.departmentNameTextField = new JTextField(25);
            this.departmentNameTextField.setDocument(new JTextFieldLimit(DB_Manipulator.DEPARTMENT_NAME_LENGTH));
            //JScrollPane departmentScrollPane = new JScrollPane(this.departmentNameTextField);
        
            //total session combobox stuff
            String[] sessionsAvailable = new String[]{" ?? Years", " 4 Years  ", " 5 Years ", " 6 Years  ", " 7 Years  "} ;
            this.totalSessionComboBox = new JComboBox(sessionsAvailable);
            JLabel sesAvaLabel = new JLabel("Regular Course Duration ");
            sesAvaLabel.setHorizontalAlignment(JLabel.RIGHT); 
        
            //total gradePoint system combobox stuff
            String[] gpSysAvailable = new String[]{"G.P System ??", "  4  ", "  5  "} ;
            this.gradeSystemComboBox = new JComboBox(gpSysAvailable);
            JLabel gpSystemLabel = new JLabel("Grade Point System ");
            gpSystemLabel.setHorizontalAlignment(JLabel.RIGHT);
        
            //textField array
            JComponent[][] centerPanelComponents = new JComponent[][]{
                {otherNameLabel, this.firstNameTextField},
                {surnameLabel, this.lastNameTextField},
                {userNameLabel, this.userNameTextField},
                {userPasswordLabel, this.passwordTextField},
                {confirmUserPasswordLabel, this.confirmPWTextField},
                {departmentNameLabel, this.departmentNameTextField},
                {facultyNameLabel, this.facultyNameTextField},
                {schoolNameLabel, this.schoolNameTextField},
                {gpSystemLabel, this.gradeSystemComboBox},
                {sesAvaLabel, this.totalSessionComboBox},
            };              
        
            // JPanel array to hold fields
            JPanel[] fieldsPanel = new JPanel[10];
        
            //Center panel
            JPanel centerPanel = new JPanel();
            centerPanel.setLayout(new GridLayout(10,1,2,2));
            for(int count = 0; count < fieldsPanel.length; count++){
                fieldsPanel[count] = new JPanel();
                fieldsPanel[count].setLayout(new BorderLayout());
                if(count < 8){
                    fieldsPanel[count].add(centerPanelComponents[count][0], BorderLayout.CENTER);
                    fieldsPanel[count].add(centerPanelComponents[count][1], BorderLayout.EAST);
                }else{
                    fieldsPanel[count].setLayout(new FlowLayout());
                    fieldsPanel[count].add(centerPanelComponents[count][0]);
                    fieldsPanel[count].add(centerPanelComponents[count][1]);
                }
                centerPanel.add(fieldsPanel[count]);
                centerPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
            }
        
            //top label stuff
            this.topLabel = new JLabel("");
            topLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
            //view passwords radio button stuff
            final JRadioButton viewPWButton = new JRadioButton("View Passwords");
            viewPWButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(viewPWButton.isSelected()){
                        UserDataForm.this.confirmPWTextField.setEchoChar((char)0);
                        UserDataForm.this.passwordTextField.setEchoChar((char)0);
                    }else
                    {
                        UserDataForm.this.confirmPWTextField.setEchoChar('●');
                        UserDataForm.this.passwordTextField.setEchoChar('●');
                    }
                }
            });
        
        
            //button panel stuff
            JPanel buttonPanel = new JPanel();
            this.okButton = new JButton("Proceed");
            this.okButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                  
                    if(UserDataForm.this.verifyInputs()){
                        // necessary filling of data base here
                        db.setDepartmentName(UserDataForm.this.getDepartmentName());
                        db.setFacultyName(UserDataForm.this.getFacultyName());
                        db.setSchoolName(UserDataForm.this.getSchoolName());
                        db.setUserName(UserDataForm.this.getUserName());
                        db.setLastName(UserDataForm.this.getLastName());
                        db.setFirstName(UserDataForm.this.getFirstName());
                        db.setUserPassword(UserDataForm.this.getPassword());
                        db.setTotalSessions(UserDataForm.this.getTotalSessions());
                        db.setGradeSystem(UserDataForm.this.getGradeSystem());
                        db.updateUseCount();
                        UserDataForm.this.dispose();
                        //UserDataForm.this.setVisible(false);
                        db.createSemesterTables(db.getTotalSemesters());
                        //System.out.println("got to line 87");
                        //DB_Manipulator.this.createSemesterTables(DB_Manipulator.this.getTotalSemesters());
                        //DB_Manipulator.this.updateUseCount(); // update the count
                        //createDepartment(dbM);
                        }
                }
            });
            
        
            this.cancelButton = new JButton("Cancel");
            this.cancelButton.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) { 
                    int i = JOptionPane.showConfirmDialog(UserDataForm.this, "Are you sure you want to Cancel this process ?"
                        , "End Registration", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
                    if(i == JOptionPane.OK_OPTION)
                        System.exit(0);
                }
            });
            
        
            buttonPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            buttonPanel.add(okButton);
            buttonPanel.add(cancelButton);
            buttonPanel.add(viewPWButton);
            
            this.setLayout(new BorderLayout());
            this.add(centerPanel, BorderLayout.CENTER);
            this.add(this.topLabel, BorderLayout.NORTH);
            this.add(buttonPanel, BorderLayout.SOUTH);
            this.topLabel.setIcon(PNGGetter.getUserDataFormIcon());
            this.setModal(true);
            this.setIconImage(PNGGetter.getFrameIcon());
            this.pack();
            this.setLocation(DriverClass.setPositionToCenter(this.getWidth(), this.getHeight()));
            this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowAdapter(){

                @Override
                public void windowClosing(WindowEvent e) {
                    int i = JOptionPane.showConfirmDialog(UserDataForm.this, "Are you sure you want to Cancel this process ?"
                        , "End Registration", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null);
                    if(i == JOptionPane.OK_OPTION)
                        System.exit(0);
                }
            });
            //this.setVisible(true);
            //System.out.println("width = " + this.getWidth() + "and \n Height = " + this.getHeight());
        //this.setVisible(true);
        }    
        
        //Second if
        if(formType == UserDataForm.CHANGE_DATA_FORM){
            //surname stuff
        JLabel surnameLabel = new JLabel("Last Name  ");
        surnameLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.lastNameTextField = new JTextField(25);
        this.lastNameTextField.setDocument(new JTextFieldLimit(30));
        
        //other name stuff
        JLabel otherNameLabel = new JLabel("First Name  ");
        otherNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.firstNameTextField = new JTextField(25);
        this.firstNameTextField.setDocument(new JTextFieldLimit(30));
        
        // user name stuff
        JLabel userNameLabel = new JLabel("Matric No.  ");
        userNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.userNameTextField = new JTextField(25);
        this.userNameTextField.setDocument(new JTextFieldLimit(30));
        
        //userPassword stuff
        JLabel userPasswordLabel = new JLabel("Password  ");
        userPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.passwordTextField = new JPasswordField(25);
        //this.passwordTextField.setFocusable(false);
        this.passwordTextField.setDocument(new JTextFieldLimit(30));
        
        //confirm user password stuff
        JLabel confirmUserPasswordLabel = new JLabel("  Confirm Password  ");
        confirmUserPasswordLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.confirmPWTextField = new JPasswordField(25);
        //this.confirmPWTextField.setFocusable(false);
        this.confirmPWTextField.setDocument(new JTextFieldLimit(30));
        
        //school name stuff
        JLabel schoolNameLabel = new JLabel("School  ");
        schoolNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.schoolNameTextField = new JTextField(25);
        this.schoolNameTextField.setDocument(new JTextFieldLimit(100));
        JScrollPane schoolScrollPane = new JScrollPane(this.schoolNameTextField);
        
        //Faculty name stuff
        JLabel facultyNameLabel = new JLabel("Faculty  ");
        facultyNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.facultyNameTextField = new JTextField(25);
        this.facultyNameTextField.setDocument(new JTextFieldLimit(100));
        JScrollPane facultyScrollPane = new JScrollPane(this.facultyNameTextField);
        
        //Department name stuff
        JLabel departmentNameLabel = new JLabel("Department  ");
        departmentNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        this.departmentNameTextField = new JTextField(25);
        this.departmentNameTextField.setDocument(new JTextFieldLimit(100));
        JScrollPane departmentScrollPane = new JScrollPane(this.departmentNameTextField);
        
        //total session combobox stuff
        String[] sessionsAvailable = new String[]{" ?? Years", " 4 Years  ", " 5 Years ", " 6 Years  ", " 7 Years  "} ;
        this.totalSessionComboBox = new JComboBox(sessionsAvailable);
        this.totalSessionComboBox.setEnabled(false);
        JLabel sesAvaLabel = new JLabel("Regular Course Duration ");
        sesAvaLabel.setHorizontalAlignment(JLabel.RIGHT); 
        
        //total gradePoint system combobox stuff
        String[] gpSysAvailable = new String[]{"G.P System ??", "  4  ", "  5  "} ;
        this.gradeSystemComboBox = new JComboBox(gpSysAvailable);
        this.gradeSystemComboBox.setEnabled(false);
        JLabel gpSystemLabel = new JLabel("Grade Point System ");
        gpSystemLabel.setHorizontalAlignment(JLabel.RIGHT);
        
         //textField array
         JComponent[][] centerPanelComponents = new JComponent[][]{
             {otherNameLabel, this.firstNameTextField},
             {surnameLabel, this.lastNameTextField},
             {userNameLabel, this.userNameTextField},
             {userPasswordLabel, this.passwordTextField},
             {confirmUserPasswordLabel, this.confirmPWTextField},
             {departmentNameLabel, departmentScrollPane},
             {facultyNameLabel, facultyScrollPane},
             {schoolNameLabel,schoolScrollPane},
             {gpSystemLabel, this.gradeSystemComboBox},
             {sesAvaLabel, this.totalSessionComboBox},
         };              
        
        // JPanel array to hold fields
        JPanel[] fieldsPanel = new JPanel[10];
        
        //Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(10,1,2,2));
        for(int count = 0; count < fieldsPanel.length; count++){
            fieldsPanel[count] = new JPanel();
            fieldsPanel[count].setLayout(new BorderLayout());
            if(count < 8){
                fieldsPanel[count].add(centerPanelComponents[count][0], BorderLayout.CENTER);
                fieldsPanel[count].add(centerPanelComponents[count][1], BorderLayout.EAST);
            }else{
                fieldsPanel[count].setLayout(new FlowLayout());
                fieldsPanel[count].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                fieldsPanel[count].add(centerPanelComponents[count][0]);
                fieldsPanel[count].add(centerPanelComponents[count][1]);
            }
            centerPanel.add(fieldsPanel[count]);
            centerPanel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        }
        
        //top label stuff
        this.topLabel = new JLabel("");
        topLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        
        //view passwords radio button stuff
        final JRadioButton viewPWButton = new JRadioButton("View Passwords");
        viewPWButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                if(viewPWButton.isSelected()){
                    UserDataForm.this.confirmPWTextField.setEchoChar((char)0);
                    UserDataForm.this.passwordTextField.setEchoChar((char)0);
                }else
                {
                    UserDataForm.this.confirmPWTextField.setEchoChar('●');
                    UserDataForm.this.passwordTextField.setEchoChar('●');
                }
            }
        });
        
        
        //button panel stuff
        JPanel buttonPanel = new JPanel();
        this.okButton = new JButton("Proceed");
        this.okButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                boolean cont = UserDataForm.this.verifyInputs();
                if (cont){
                    //carry On
                    db.setDepartmentName(UserDataForm.this.getDepartmentName());
                    db.setFacultyName(UserDataForm.this.getFacultyName());
                    db.setSchoolName(UserDataForm.this.getSchoolName());
                    db.setUserName(UserDataForm.this.getUserName());
                    db.setLastName(UserDataForm.this.getLastName());
                    db.setFirstName(UserDataForm.this.getFirstName());
                    db.setUserPassword(UserDataForm.this.getPassword());
                    db.setTotalSessions(UserDataForm.this.getTotalSessions());
                    db.setGradeSystem(UserDataForm.this.getGradeSystem());
                    UserDataForm.this.dispose();
                }
            }
        });
        
        
        this.cancelButton = new JButton("Cancel");
        this.cancelButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) { 
                UserDataForm.this.dispose();
            }
        });
        
        
        buttonPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(viewPWButton);
        
        // init fields from data base 
        this.getUserNameTextField().setText(db.getUserName()); //1
        this.getFacultyNameTextField().setText(db.getFacultyName()); //2
        this.getFirstNameTextField().setText(db.getLastName());  //3
        this.getLastNameTextField().setText(db.getFirstName());  //4
        this.getPasswordTextField().setText(db.getUserPassword());   //5
        this.getConfirmPasswordField().setText(db.getUserPassword());  //6
        this.getSchoolNameTextField().setText(db.getSchoolName());   //7
        this.getDepartmentNameTextField().setText(db.getDepartmentName());   //8
        this.getGradeSystemComboBox().setSelectedIndex((db.getGradeSystem() == 4)?1:2);  //Tenary Operator :)
        
        //set selected gradeSystemStyle
        //the db.getTotal sessions returns the total number of sessions 'including' the spill over session
        if(db.getTotalSessions() == 8)
            this.totalSessionComboBox.setSelectedIndex(4);
        else if(db.getTotalSessions() == 7)
            this.getTotalSessionComboBox().setSelectedIndex(3);
        else if(db.getTotalSessions() == 6)
            this.getTotalSessionComboBox().setSelectedIndex(2);
        else if(db.getTotalSessions() == 5)
            this.getTotalSessionComboBox().setSelectedIndex(1);
        
        //
        this.setLayout(new BorderLayout());
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(this.topLabel, BorderLayout.NORTH);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.getTitleLablel().setIcon(PNGGetter.getUserDataFormIcon());
        this.setIconImage(PNGGetter.getFrameIcon());
        this.setModal(true);
        this.pack();
        this.setLocation(DriverClass.setPositionToCenter(this.getWidth(), this.getHeight()));
        //this.setVisible(true);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //this.setVisible(true);
        }
        
    } // end constructor
    
    
    /**
     * Method to get the okButton
     * @return JButton
     */
    JButton getOkButton(){
        return this.okButton ;
    }
    
    /**
     * Method to get the cancel button
     * @return JButton
     */
    JButton getCancelButton(){
        return this.cancelButton ;
    }
    
    /**
     * Method to get the gradeSystemComboBox
     * @return JComboBox
     */
    JComboBox getGradeSystemComboBox(){
        return this.gradeSystemComboBox ;
    }
    
    /**
     * Method to get the total sessions combo box
     * @return  JComboBox
     */
    JComboBox getTotalSessionComboBox(){
        return this.totalSessionComboBox ;
    }
    
    /**
     * Method to get the title (top) label
     * @return JLabel
     */
    JLabel getTitleLablel(){
        return this.topLabel ;
    }
    
    /**
     * Method to set the password
     * @param pw password
     */
    private void setPassword(String pw){
        this.password = pw ;
    }
    
    /**
     * Method to get the password
     * @return password from the pass word text field
     */
    String getPassword(){
        return this.password ;
    }
    
    /**
     * Method to get the firstNameTextField
     * @return JTextField - firstName
     */
    JTextField getFirstNameTextField(){
        return this.firstNameTextField ;
    }
     /**
     * Method to set the first name
     * @param nm firstName
     */
    private void setFirstName(String nm){
        this.firstName = nm ;
    }
    
    /**
     * Method to get the first name
     * @return password 
     */
    String getFirstName(){
        return this.firstName ;
    }
    
    /**
     * Method to get the LastNameTextField
     * @return JTextField
     */
    JTextField getLastNameTextField(){
        return this.lastNameTextField ;
    }
    /**
     * Method to set the last name
     * @param nm lastName
     */
    private void setLastName(String nm){
        this.lastName = nm ;
    }
    
    /**
     * Method to get the last name
     * @return password 
     */
    String getLastName(){
        return this.lastName ;
    }
    
    /**
     * Method to get the UserNameTextField
     * @return JTextField
     */
    JTextField getUserNameTextField(){
        return this.userNameTextField ;
    }
    
    /**
     * Method to set the user name (matric no.)
     * @param nm userName
     */
    private void setUserName(String nm){
        this.userName = nm ;
    }
    
    /**
     * Method to get the user name
     * @return userName
     */
    String getUserName(){
        return this.userName ;
    }
    
    /**
     * Method to get the SchoolNameTextField
     * @return JTextField
     */
    JTextField getSchoolNameTextField(){
        return this.schoolNameTextField;
    }
    
    /**
     * Method to set the school name
     * @param nm schoolName
     */
    private void setSchoolName(String nm){
        this.schoolName = nm ;
    }
    
    /**
     * Method to get the school name
     * @return schoolName
     */
    String getSchoolName(){
        return this.schoolName ;
    }
    
    /**
     * Method to get the facultyNameTextField
     * @return JTextField
     */
    JTextField getFacultyNameTextField(){
        return this.facultyNameTextField ;
    }
    /**
     * Method to set the faculty name
     * @param nm facultyName
     */
    private void setFacultyName(String nm){
        this.facultyName = nm ;
    }
    
    /**
     * Method to get the faculty name
     * @return facultyName
     */
    String getFacultyName(){
        return this.facultyName ;
    }
    
    /**
     * Method to get departmentNameTextField
     * @return JTextField
     */
    JTextField getDepartmentNameTextField(){
        return this.departmentNameTextField ;
    }
    /**
     * Method to set the department name
     * @param nm departmentName
     */
    private void setDepartmentName(String nm){
        this.departmentName = nm ;
    }
    
    /**
     * Method to get the department name
     * @return departmentName
     */
    String getDepartmentName(){
        return this.departmentName ;
    }
    
    /**
     * Method to set the grade system by virtue of the gradeComboBox selection
     */
    private void setGradeSystem() {
        if (this.gradeSystemComboBox.getSelectedIndex() == 1)
            this.gradeSystem = 4 ;
        if (this.gradeSystemComboBox.getSelectedIndex() == 2)
            this.gradeSystem = 5 ;
    }
    
    
    
    /**
     * Method to get the grade system
     * @return grade system
     */
    int getGradeSystem(){
        return this.gradeSystem ;
    }
    
      /**
     * Method to set the grade system by virtue of the gradeComboBox selection
     */
    private void setTotalSession() {
        if (this.totalSessionComboBox.getSelectedIndex() == 1)
            this.totalSession = 4 ;
        if (this.totalSessionComboBox.getSelectedIndex() == 2)
            this.totalSession = 5 ;
        if (this.totalSessionComboBox.getSelectedIndex() == 3)
            this.totalSession = 6 ;
        if (this.totalSessionComboBox.getSelectedIndex() == 4)
            this.totalSession = 7 ;
    }
    
    /*
     * Method to get the total sessions JComboBox
     * @return JComboBox
     
    JComboBox getTotalSessionsCombox(){
        return this.totalSessionComboBox ;
    }
*/    
    
    /**
     * Method to get the total sessions
     * @return grade system
     */
    int getTotalSessions(){
        return this.totalSession ;
    }
    
    /**
     * Method to verify the inputs
     * @return 
     */
     boolean verifyInputs(){
        if(this.firstNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "First name cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.lastNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Last name cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.userNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Matric number cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.departmentNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Department cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.schoolNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "School cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if(this.facultyNameTextField.getText().isEmpty()){
            JOptionPane.showMessageDialog(this, "Faculty cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if(this.passwordTextField.getPassword().length < 6){
            JOptionPane.showMessageDialog(this, "Invalid password length\n "
                    + "password should be greater than 5 characters", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return false ;
        }else
            if(UserDataForm.passwordConfirmer(this.passwordTextField, this.confirmPWTextField) == false){
                JOptionPane.showMessageDialog(this, "Passwords donot match", "Input Error", JOptionPane.ERROR_MESSAGE);
                return false ;
            }
        if(this.gradeSystemComboBox.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Select Grade Point System", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false ;
        }
        if(this.totalSessionComboBox.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(this, "Select Duration", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        //set values accordingly
        this.setFirstName(this.firstNameTextField.getText());
        this.setLastName(this.lastNameTextField.getText());
        this.setDepartmentName(this.departmentNameTextField.getText());
        this.setFacultyName(this.facultyNameTextField.getText());
        this.setUserName(this.userNameTextField.getText());
        this.setSchoolName(this.schoolNameTextField.getText());
        this.setPassword(new String(this.passwordTextField.getPassword()));
        this.setTotalSession();
        //this.totalSession = this.getTotalSessions() ;
        this.setGradeSystem();
        //this.gradeSystem = this.getGradeSystem() ;
                
        return true ;
    }
    
     /**
      * method to get the password field in this dialog
      * @return  JPasswordField
      */
    JPasswordField getPasswordTextField(){
        return this.passwordTextField ;
    }
    
    /**
     * Method to get the confirm password field in this dialog
     * @return JPasswordField
     */
    JPasswordField getConfirmPasswordField(){
        return this.confirmPWTextField ;
    }
    
    /**
     * Method that accepts two password fields and checks if they match
     * @param one JPasswordField
     * @param two JPasswordField
     * @return true if both passwordFields contain same data and false if otherwise
     */
    public static boolean passwordConfirmer(JPasswordField one, JPasswordField two){
        boolean same = false;
        char[] pwOne = one.getPassword();
        char[] pwTwo = two.getPassword();
        
        if(new String(pwOne).equals(new String(pwTwo)))
            same = true ;
        else
            same = false ;
        
        return same ;
    }
    
    
   /* public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch( ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
        }
        JFrame frame = new JFrame("test UserDataForm");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final UserDataForm udf = new UserDataForm(UserDataForm.CHANGE_DATA_FORM);
        udf.setVisible(true);
        JButton act = new JButton("action button");
        act.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("User Name = " + udf.getUserName());
            }
        });
        frame.add(act, BorderLayout.SOUTH);
        frame.pack();
        frame.setSize(290, 360);
        frame.setVisible(true);
    }
    */
}
