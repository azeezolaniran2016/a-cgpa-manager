/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author AZYSS
 */
class Course extends JPanel{
    
    //declaration of class fields
    static final Dimension COURSE_DIMENSION = new Dimension(320,20);
    static final int FIVE_POINT_GRADE_SYSTEM = 5 ; // 5 point grade system.
    static final int FOUR_POINT_GRADE_SYSTEM = 4;
    private String courseTitle ;  // title of this course
    //private String courseCode ;  // code of this course
    //private double courseUnit ;  // unit for this course
    //private double courseScore ;  // score gotten in this course
    //private double courseCreditPoint;  // credit point for this course
    private boolean courseIsCarried ;  // boolean value for the course carried or not
    private boolean courseIsOffered ; // 
    private String courseGrade ;
    final static Color COURSE_CARRIED_COLOR = new Color(255,221,221);
    
    private JPanel coursePanel ;
    private JLabel courseCodeLabel ;
    private JTextField unitTextField ;
    private JTextField scoreTextField ;
    protected JComboBox gradeComboBox ;  // intentionally made protected so it can be manipulated by the sub-classes
    private JCheckBox courseOfferedCheckBox ;
    private JButton deleteCourseButton ;
    
    
    /**
     * Constructor for an instance of this Course class
     * @param code code for this course
     * @param title title of this course
     */
    Course (String code, String title){
        super();
        
        this.initCourse();
        
        this.courseCodeLabel.setText(code); 
        this.courseCodeLabel.setToolTipText(title);
        this.courseTitle = title ;
        
        
    }
    
    /**
     * 
     * @param code courseCode
     * @param title course title
     * @param grade course grade
     * @param unit course unit
     * @param courseScore course score
     * @param registered  course registered
     */
    public Course(String code, String title, String grade, int unit, int courseScore, boolean registered ){
        super();
        
        this.initCourse();
        
        this.courseCodeLabel.setText(code);
        this.courseTitle = title;
        //this.courseGrade = grade ;
        //this.courseUnit = unit;
        this.courseIsOffered = registered ;
        this.courseCodeLabel.setToolTipText(title);
        
        
        
        
        this.setCourseUnit(unit);
        this.setCourseGrade(grade);
        this.setCourseUnit(unit);
        this.setCourseScore(courseScore);
        this.setCourseRegistered(registered);
        
    }
    
    /**
     * Method to draw rows and columns for this course components
     */
   /* @Override
    public void paintComponent(Graphics g){
        //super.paint(g);
        //g.setColor(Color.blue);
        for (int i = 1; i <= 4; i++){
            g.drawLine((this.getWidth()/5)*i, 0, (this.getWidth()/5)*i, this.getHeight());
        }
        //for (int i = 1; i <= 20; i++){
            //g.drawLine(0, 0, this.getWidth(), 0);
            //g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
        //}
    }*/
    private void initCourse(){
        
        //courseCodeLabel stuff
        this.courseCodeLabel = new JLabel();
        this.courseCodeLabel.setHorizontalAlignment(JLabel.CENTER);
        this.courseCodeLabel.setOpaque(true);
        //this.courseCodeLabel.setText(this.courseCode);
        //this.courseCodeLabel.setToolTipText("Double click to edit this course. \n Course Title : " + this.courseTitle);
        this.courseCodeLabel.addMouseListener(new MouseAdapter(){  // adapter to enable user change course code and title

            @Override
            public void mouseClicked(MouseEvent e) {
                if(Course.this.courseCodeLabel.isFocusable()){
                    if(e.getClickCount() == 2)
                        CourseCreator.editCourseCodeAndLabel(Course.this);
                    
                }
            }
        });
        
        //unitJTextField stuff
        this.unitTextField = new JTextField(3) ;
        this.unitTextField.setText("Unit ?");
        this.unitTextField.setToolTipText("Enter Course Unit");
        this.unitTextField.setOpaque(false) ;
        this.unitTextField.setHorizontalAlignment(JTextField.CENTER) ;
        this.unitTextField.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {
                String temp = Course.this.unitTextField.getText();
                Course.this.unitTextField.setDocument(new JTextFieldLimit(2));
                Course.this.unitTextField.setText(temp);
                //Course.this.unitTextField.setText(Double.toString(courseUnit));
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Course.this.unitTextField.getText().equalsIgnoreCase("")){
                    Course.this.unitTextField.setDocument(new JTextFieldLimit(7));
                    Course.this.unitTextField.setText("Unit ?");
                }
             }
        });
        
        //scoreTextFieldStuff
        this.scoreTextField = new JTextField("% ?") ;
        this.scoreTextField.setToolTipText("Enter Score %");
        this.scoreTextField.setOpaque(false) ;
        //this.scoreTextField.setDocument(new JTextFieldLimit(2)) ;
        this.scoreTextField.setHorizontalAlignment(JTextField.CENTER) ;
        this.scoreTextField.addFocusListener(new FocusListener(){

            @Override
            public void focusGained(FocusEvent e) {
                String temp = Course.this.scoreTextField.getText();
                Course.this.scoreTextField.setDocument(new JTextFieldLimit(2));
                Course.this.scoreTextField.setText(temp);
            }

            @Override
            public void focusLost(FocusEvent e) {
                if(Course.this.scoreTextField.getText().equalsIgnoreCase("")){
                    Course.this.scoreTextField.setDocument(new JTextFieldLimit(8));
                    Course.this.scoreTextField.setText("% ?");
                }else{
                    try{
                        Course.this.setGradeFromScore(Double.parseDouble(Course.this.scoreTextField.getText()));
                    }catch(NumberFormatException exp){
                        //do nothing
                    }    
                }
            }
        });
        
        
        //gradeComboBoxField stuff
        doGradeComboBoxInit();
        
        
        //delete courseButton stuff
        this.deleteCourseButton = new JButton(PNGGetter.getDeleteIcon());
        this.deleteCourseButton.setToolTipText("Double click to\n delete this course ?");
        this.deleteCourseButton.setForeground(Color.RED);
        //this.deleteCourseButton.setFont(new Font(Font.MONOSPACED,8,12));
        this.deleteCourseButton.setPreferredSize(new Dimension(20,15));
        this.deleteCourseButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        //courseOfferedCheckBox stuff
        this.courseOfferedCheckBox = new JCheckBox() ;
        this.courseOfferedCheckBox.setHorizontalAlignment(JCheckBox.LEFT);
        this.courseOfferedCheckBox.setToolTipText("Offer Course ?");
        this.courseOfferedCheckBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.courseOfferedCheckBox.setHorizontalAlignment(JCheckBox.TRAILING);
        this.courseOfferedCheckBox.setOpaque(false) ;
        this.courseOfferedCheckBox.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Course.this.inputVerifyer())
                Course.this.courseOfferedAction();
            }
        });
        
        //checkBoxPanel stuff
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new BorderLayout());
        checkBoxPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        checkBoxPanel.add(this.courseOfferedCheckBox);
        
        //Panel to hold action buttons/check box
        JPanel actionButtonsPanel = new JPanel();
        actionButtonsPanel.setLayout(new BorderLayout());
        actionButtonsPanel.setBorder(BorderFactory.createLoweredBevelBorder());
        actionButtonsPanel.add(checkBoxPanel, BorderLayout.WEST);
        actionButtonsPanel.add(this.deleteCourseButton, BorderLayout.EAST);

        // JPanel to hold the course label, unit and score
        coursePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g){
        //super.paint(g);
        //g.setColor(Color.blue);
                for (int i = 1; i <= 4; i++){
                    //g.setFont(new Font(Font.MONOSPACED,Font.BOLD,3));
                    g.drawLine((this.getWidth()/4)*i, 0, (this.getWidth()/4)*i, this.getHeight());
                }
        //for (int i = 1; i <= 20; i++){
            //g.drawLine(0, 0, this.getWidth(), 0);
            //g.drawLine(0, this.getHeight()-1, this.getWidth(), this.getHeight()-1);
        //}
            }
        };
        
        
        
        this.coursePanel.setLayout(new GridLayout(1,4,10,0));
        this.coursePanel.setOpaque(false);
        this.coursePanel.add(this.courseCodeLabel);
        this.coursePanel.add(this.gradeComboBox);
        this.coursePanel.add(this.unitTextField);
        this.coursePanel.add(this.scoreTextField);
        
        
        //this Panel stuff
        this.setLayout(new BorderLayout()) ;
        this.setOpaque(false) ;
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)) ;
        
        //this.add(this.courseCodeLabel) ;
        //this.add(this.gradeComboBox) ;
        //this.add(this.unitTextField) ;
        //this.add(this.scoreTextField) ;
        this.setPreferredSize(Course.COURSE_DIMENSION);
        this.add(coursePanel, BorderLayout.CENTER);
        this.add(actionButtonsPanel, BorderLayout.EAST) ;
        
    }  // End of initCourse()
    
    //String getCourseGradeForDB{
        
    //}
    /**
     * method to get this course code
     * @return course code
     */
    String getCourseGrade(){
        if(gradeComboBox.getSelectedIndex() == 0){
            return "N/A" ;
        }else{
            return this.gradeComboBox.getSelectedItem().toString();
            }
    }
    
    JLabel getCourseCodeLabel(){
        return this.courseCodeLabel ;
    }
    
    /**
     * Method to get the delete course button in this course
     * @return deleteButton
     */
    JButton getDelecteCourseButton(){
        return this.deleteCourseButton ;
    }
    
    /**
     * Method to do the grade combo box initialisation
     * should be over-ridden by child classes that wish to use a different implementation for grades
     */
     void doGradeComboBoxInit(){
        String[] gradeCombo = {"Grade ?", "   A", "   B", "   C", "   D", "   E", "   F", "   P"} ;
            this.gradeComboBox = new JComboBox(gradeCombo);
            this.gradeComboBox.setToolTipText("Grade");
            this.gradeComboBox.setOpaque(false) ;
            this.gradeComboBox.setMaximumRowCount(4); // maximum number of rows visible at once
            this.gradeComboBox.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                
            // change the grade color to red if its an F
            if(Course.this.gradeComboBox.getSelectedIndex() == 6)
                Course.this.courseCodeLabel.setForeground(Color.red);
            else
                Course.this.courseCodeLabel.setForeground(Color.black);
                 }
        });
            this.gradeComboBox.setRenderer(new DefaultListCellRenderer(){
            @Override
            public void paint(Graphics g){
                setForeground(Color.black);
                super.paint(g);
            }
            });
            
    }
    
    /**
     * Method to get this course code as set in the constructor
     * @return course code 
     */
    String getCourseCode(){
        return this.courseCodeLabel.getText() ;
    }
    
    /**
     * Method to get this course title as set in the constructor
     * @return course title
     */
    String getCourseTitle(){
        return this.courseTitle ;
    }
    
    /**
     * method to set this course unit
     */
    /*void setCourseUnit(){
       //if(this.unitTextField.getText() == null)
        //    JOptionPane.showMessageDialog(this.unitTextField, "Wrong Input", "Error", JOptionPane.ERROR_MESSAGE);
        //else
        try{
            if(this.gradeComboBox.getSelectedIndex() == 7)
               //this.courseUnit = 0;
            else{
                //this.courseUnit = Double.parseDouble(this.unitTextField.getText()) ;
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(this.unitTextField, "Enter a valid unit (0 - 99).", this.getCourseCode(), JOptionPane.ERROR_MESSAGE);
            this.enableCourse();
            //this.courseOfferedCheckBox.setSelected(false);
            //this.gradeComboBox.setEnabled(true);
            //this.unitTextField.setEnabled(true);
            return ;
        }
    }
    */
    
    /**
     * Method to get the unit text field in this course object
     * @return JTextField
     */
    JTextField getUnitTextField(){
        return this.unitTextField ;
    }
    
    /**
     * method to get this course unit
     * @return course unit
     */
    double getCourseUnit(){
        if(this.gradeComboBox.getSelectedIndex() == 7){  // grade p
            return 0 ;
        }
        try{
            return Double.parseDouble(this.unitTextField.getText()) ;
        }catch (NumberFormatException nfe){
            return -1 ;
        }
    }
    
    /**
     * method to setCourseCreditPoint
     */
    /*
    void setCourseCreditPoint(){
        this.courseCreditPoint = this.evaluateGradeTCP() ;
        
    }
    */
    
    /**
     * method to get this course credit point
     * @return course credit point
     */
    double getCourseCreditPoint(){
        //this.setCourseCreditPoint();
        return this.evaluateGradeTCP() ;
    }
    
    /**
     * Method to determine the equivalent grade of the scores
     */
    protected void setGradeFromScore(double grade){
        if(this.gradeComboBox.getSelectedIndex() == 7){
            //do nothing
        }else{
            if(grade < 40)
                this.gradeComboBox.setSelectedIndex(6);
            else if(grade >= 40 && grade < 45)
                this.gradeComboBox.setSelectedIndex(5);
            else if(grade >= 45 && grade < 50)
                this.gradeComboBox.setSelectedIndex(4);
            else if(grade >= 50 && grade < 60)
                this.gradeComboBox.setSelectedIndex(3);
            else if(grade >= 60 && grade < 70)
                this.gradeComboBox.setSelectedIndex(2);
            else if(grade >= 70 && grade < 100)
                this.gradeComboBox.setSelectedIndex(1);
            else{
            // do nothing
            }
        }
        
    }
    
    /**
     *  method to evaluate the 
     * double representation of different grades
     */
     double evaluateGradeTCP(){
        double evaluant = 0;
        switch(this.gradeComboBox.getSelectedIndex()){
            case 7:
                evaluant = 0 ;
                this.courseIsCarried = false ;
                break ;
            case 1:
                evaluant = 5 * getCourseUnit() ;
                this.courseIsCarried = false ;
                break ;
            case 2:
                evaluant = 4 * getCourseUnit() ;
                this.courseIsCarried = false ;
                break ;
            case 3:
                evaluant = 3 * getCourseUnit() ;
                this.courseIsCarried = false ;
                break ;
            case 4:
                evaluant = 2 * getCourseUnit() ;
                this.courseIsCarried = false ;
                break ;
            case 5:
                evaluant = 1 * getCourseUnit() ;
                this.courseIsCarried = false ;
                break ;
            case 6:
                evaluant = 0 * getCourseUnit() ;
                this.courseIsCarried = true ;
                break ;
            case 0:
                JOptionPane.showMessageDialog(gradeComboBox, "Select a grade.", this.courseCodeLabel.getText(), JOptionPane.ERROR_MESSAGE);
                this.enableCourse();
                break ;
        }
        return evaluant ;
    } 
     
     /**
     * method to set course carried or not
     */
    void setCourseCarried(boolean carried){
        this.courseIsCarried = carried ;
    }
    
    /**
     * method to check if this course if carried
     * @return return true if this course is carried and false if it is not
     */
    boolean isCourseCarried(){
        if(this.gradeComboBox.getSelectedIndex() == 6)
            return true;
        else
            return false ;
    }
    
    /**
     * method to check if this course is offered
     * @return true if course is offered and false if it is not
     */
    boolean isCourseOffered(){
        return this.courseIsOffered ;
    }
    
    /**
     * Method to re-enable the components of this course
     */
    void enableCourse(){
        this.unitTextField.setFocusable(true);
        this.scoreTextField.setFocusable(true);
        this.gradeComboBox.setEnabled(true);
        //this.unitTextField.setEnabled(true);
        //this.scoreTextField.setEnabled(true);
        this.courseOfferedCheckBox.setSelected(false);
    }
    
    /**
     * Method to reset this course to its initial values
     */
    void resetCourse(){
        this.enableCourse();
        this.gradeComboBox.setSelectedIndex(0);
        this.unitTextField.setDocument(new JTextFieldLimit(7));
        this.unitTextField.setText("Unit ?");
        this.scoreTextField.setDocument(new JTextFieldLimit(3));
        this.scoreTextField.setText("% ?");
        this.courseIsOffered = false ;
    }
    
    /**
     * private method to set the course score from the score text field
     */
    /*private void setCourseScore(){
        try{
            this.courseScore = Double.parseDouble(this.scoreTextField.getText());
        }catch(NumberFormatException e){
            this.scoreTextField.setDocument(new JTextFieldLimit(8));
            this.scoreTextField.setText("% ?");
        }
    }
    */
    
    
    
    /**
     * method to get this course score
     * @return course score
     * setCourseScore() must to assure that the right score is returned
     */
    double getCourseScore(){
        try{
            return Double.parseDouble(this.scoreTextField.getText()) ;
        }catch (NumberFormatException nfe){
                return -1 ;
        }
    }
    
    /**
     * Method to set the text in the textUnitTextField
     * @param txt text to show
     */
    protected void setCourseUnit(int unit){
        if(unit < 0){
            this.unitTextField.setText("Unit ?");
        }else{
            this.unitTextField.setText(Integer.toString(unit));
        }
    }
    
    protected void setCourseScore(int score){
        if(score < 0){
            this.scoreTextField.setText("% ?");
        }else{
            this.scoreTextField.setText(Integer.toString(score));
        }
    }
    
    protected void setCourseGrade(String grade){
        if(grade.equalsIgnoreCase("   A")){
            this.gradeComboBox.setSelectedIndex(1);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   B")){
            this.gradeComboBox.setSelectedIndex(2);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   C")){
            this.gradeComboBox.setSelectedIndex(3);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   D")){
            this.gradeComboBox.setSelectedIndex(4);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("   E")){
            this.gradeComboBox.setSelectedIndex(5);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else if(grade.equalsIgnoreCase("F")){
            this.gradeComboBox.setSelectedIndex(6);
            this.gradeComboBox.setForeground(Color.red);
        }
        else if(grade.equalsIgnoreCase("   P")){
            this.gradeComboBox.setSelectedIndex(7);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
        else{
            this.gradeComboBox.setSelectedIndex(0);
            this.gradeComboBox.setForeground(Color.BLACK);
        }
    }
    
    private boolean inputVerifyer(){
        //try{
        if(this.getCourseUnit() < 0){
         //   this.getCourseUnit();
        //}catch (Exception e){
            JOptionPane.showMessageDialog(this.unitTextField, "Invalid Unit", this.courseCodeLabel.getText(), JOptionPane.ERROR_MESSAGE);
            this.enableCourse();
            return false ;
        }
        //        return false ;
        //}
        if(this.gradeComboBox.getSelectedIndex() == 0){
            JOptionPane.showMessageDialog(gradeComboBox, "Select a grade.", this.courseCodeLabel.getText(), JOptionPane.ERROR_MESSAGE);    
            this.enableCourse();
            return false ;
        } else
            return true ;
    }
    private void courseOfferedAction(){
        if(Course.this.courseOfferedCheckBox.isSelected()){
            //Course.this.setCourseUnit() ;
            //Course.this.setCourseCreditPoint() ;
            Course.this.courseIsOffered = true ;
            this.gradeComboBox.setEnabled(false);
            this.unitTextField.setFocusable(false);
            this.scoreTextField.setFocusable(false);
            this.courseCodeLabel.setFocusable(false);
            //Course.this.gradeComboBox.setEnabled(false);
            //Course.this.unitTextField.setEnabled(false);
            //Course.this.scoreTextField.setEnabled(false);
            try{
                Course.this.setGradeFromScore(Double.parseDouble(Course.this.scoreTextField.getText()));
            }catch (NumberFormatException exp){
                 //do nothin
            }
             //Course.this.setCourseScore();
                    
             if(!Course.this.courseOfferedCheckBox.isSelected()){
                    Course.this.enableCourse();
             }
                    
        }else{
            Course.this.enableCourse();
            this.courseCodeLabel.setFocusable(true);
            Course.this.courseIsOffered = false ;
        }
    }
    protected void setCourseRegistered(boolean reg){
        this.courseOfferedCheckBox.setSelected(reg);   
        this.courseOfferedAction();
    }
    
    int getCourseOffered(){
        if(this.courseOfferedCheckBox.isSelected())
            return 1;
        else 
            return 0 ;
    }
    
    /*
 
    public static void main(String[] args){
         try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch( ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){
        }
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Course("EEG201", "elect intro", "f", 3, 50, true), BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
                
    }
    */
}
