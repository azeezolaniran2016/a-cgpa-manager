/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Olaniran Azeez Olawale (AZYSS)
 A class to deal with all database actions as needed 
 by the application.
 */
public class DB_Manipulator {
    
    static final int MAXIMUM_TRIAL_COUNT = 1025 ;  // Remember to increase this before shipping the final version
    static final int COURSE_TITLE_LENGTH = 100 ;    //maximum length for course code
    static final int COURSE_ADDITIONAL_INFO_LENGTH = 300 ; // maximum length for course additional information
    static final int COURSE_CODE_LENGTH = 8 ;       //maximum length for course title
    static final int COURSE_GRADE_LENGTH = 30 ;
    static final int FIRST_NAME_LENGTH = 30 ;          //Maximum Surname length 
    static final int LAST_NAME_LENGTH = 30 ;       //Maximum other name length
    static final int USER_NAME_LENGTH = 30 ;        //Maximum user name length
    static final int USER_PASSWORD_LENGTH = 20 ;    //Maximum user password lenght
    static final int SCHOOL_NAME_LENGTH = 100 ;     //Maximum school name lenght
    static final int FACULTY_NAME_LENGTH = 100;     //Maximum faculty name length
    static final int DEPARTMENT_NAME_LENGTH = 100 ; //Maximum Department name length
    
    
    private Connection connection; // connection object for this class
    private static final String DB_NAME = "GP_Database" ; //name of database to be created
    private static final String USER_DATA_TABLE = "USER_DATA_TABLE";
    private DatabaseMetaData dbmd ;
    private static final String USER_NAME = "olawaleazyss";
    private static final String PASSWORD = "olaniran";
    private static final String[] SEMESTER_NAMES = new String[]{"Semester_1",
        "Semester_2",
        "Semester_3",
        "Semester_4",
        "Semester_5",
        "Semester_6",
        "Semester_7",
        "Semester_8",
        "Semester_9",
        "Semester_10",
        "Semester_11",
        "Semester_12",
        "Semester_13",
        "Semester_14",
        "Semester_15",
        "Semester_16"};
    /**
     * Constructor for this class
     */ 
    DB_Manipulator(){
        this.loadDriver();
        this.createConnection();
        
        try{
            this.connection.close();
        }catch(SQLException sqle){
            //do nothing
        } 
    }
    
    /**
     * Method to create a connection for an object of this class
     * creates a connection if the database exists 
     * or throws an exception if the database doesn't exist
     */
    private void createConnection(){
        try{
            this.connection = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
        }catch(SQLException sqlExp){
            //System.out.println("Exception thrown in ceateConnection()\n + sqlExp.getMessage());
            //Database not found create the database and the basic tables
            try{
                this.connection = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME + ";create=true", DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
                this.createUserDataTable();
            }catch(SQLException sqlExp2){
                //do nothing
            }  // End of inner try catch block
        } // end of try catch block
    } // end of create connection method
    
    /**
     * Method to load the driver
     */
    private void loadDriver(){
        try{
            //System.out.println("Loading driver");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            //System.out.println("Driver Loaded Successfully ");
        }catch(ClassNotFoundException cnfe){
            // do nothing
        }
    }
    
    /**
     * Method to get the total rows in a table
     * @param tblName name of table to count rows
     * @return integer number of rows
     */
     int getTotalRows(String tblName){
        int rowCount = 0 ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + 
                DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
                Statement st = con.createStatement()){
            ResultSet rs =  st.executeQuery("SELECT * FROM " + DB_Manipulator.DB_NAME
            + "." + tblName);
            while(rs.next()){
                ++rowCount ;
            }  
        }catch (SQLException sqle){
         //   System.out.println("Exception thrown in getTotalRows()\n"
         //   + sqle.getMessage());
        }
        return rowCount ;
    }
     
    /**
     * Method to determine true or false based on an integer value of 1 or 0
     * 1 = true
     * 0 = false
     */
     private boolean getTrueOrFalse(int data){
         if (data == 1)
             return true ;
         else if (data == 0)
             return false ;
         else 
             throw new IllegalArgumentException("invalid input value");
     }
     /**
      * Method to save courses from a semester into the respective semester table
      * @param semesterCourses array of courses to be saved
      * @param semPosition position of this semester in the db and department
      */
     void saveSemesterCourses(Course[] semesterCourses, int semPosition){
         String cTitle ;
         String cAdditionalInfo = "" ; // course additional info !!!!
         String cCode ;
         double cScore ;
         double cUnit ;
         int cOffered ;
         String cGrade ;
         //int currentCourse = semesterCourses.length ;
         //int totSem = this.getTotalSemesters();
         try(Connection con = DriverManager.getConnection("jdbc:derby:" + 
            DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement()){
            //delete previous data
             st.executeUpdate("DELETE FROM " + DB_Manipulator.DB_NAME + "." 
                + SEMESTER_NAMES[semPosition]);
             for(int k = 0; k < semesterCourses.length; k++){
                //System.out.println("Title : " +semesterCourses[k].getCourseCode());
                 cCode = semesterCourses[k].getCourseCode();
                 //System.out.println("Title : " +semesterCourses[k].getCourseTitle());
                 cTitle = semesterCourses[k].getCourseTitle();
                 //System.out.println("Unit : " + semesterCourses[k].getCourseUnit());
                 cUnit = semesterCourses[k].getCourseUnit();
                 //System.out.println("Grade : " +semesterCourses[k].getCourseGrade());
                 cGrade = semesterCourses[k].getCourseGrade();
                 cScore = semesterCourses[k].getCourseScore();
                 //System.out.println("Offered : " +semesterCourses[k].getCourseOffered());
                 cOffered = semesterCourses[k].getCourseOffered();
                 st.executeUpdate("INSERT INTO " + DB_Manipulator.DB_NAME + "."
                         + SEMESTER_NAMES[semPosition]
                         +" VALUES('" + cCode + "', '" + cAdditionalInfo + "','" + cTitle + "', " + cUnit + ", '"+cGrade+"', "+ cScore +", " + cOffered + ")") ;
                 }//end of for loop
             
         }catch(SQLException sqle){
             System.out.println("Sqle exception thrown in saveSemesterCourses()\n" +
                     sqle.getMessage());
         }catch(Exception e){  // incase it not an sqle exception
             //System.out.println("Exception e caught in saveSemesterCourses()\n"
             //+ e.toString());
         }
     }
     
    /**
     * method to getCourses in a semester
     * @param sem semester to retrieve course[] from
     */
    Course[]  getSemesterCourseArray(int sem ){
        int semester = sem - 1 ;  // subtract one since arrays starts at 0
        int courseCount = this.getTotalRows(DB_Manipulator.SEMESTER_NAMES[semester]) ;
        String courseCode ;
        String courseTitle;
        int courseUnit;
        String courseGrade;
        int courseScore;
        boolean courseRegistered ;
        //course[] to hold the courses that would be generated from the database
        Course[] semesterCourses = new Course[courseCount] ;
    
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + 
                DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
                Statement st = con.createStatement()){
            ResultSet rs = st.executeQuery("SELECT * FROM " +DB_Manipulator.DB_NAME + "." 
                + SEMESTER_NAMES[semester]);
            int i = 0 ;
            while(rs.next()){
                courseCode = rs.getString("Course_Code");
                courseTitle = rs.getString("Course_Title");
                courseUnit = rs.getInt("Course_Unit");
                courseGrade = rs.getString("Course_Grade");
                //System.out.println(courseGrade);
                courseScore = rs.getInt("Course_Score");
                courseRegistered = this.getTrueOrFalse(rs.getInt("Course_Registered"));
                if(this.getGradeSystem() == Course.FOUR_POINT_GRADE_SYSTEM){
                    semesterCourses[i] = new Course4(courseCode, courseTitle, courseGrade,courseUnit, courseScore, courseRegistered);
                }
                if(this.getGradeSystem() == Course.FIVE_POINT_GRADE_SYSTEM){
                    semesterCourses[i] = new Course(courseCode, courseTitle, courseGrade,courseUnit, courseScore, courseRegistered);
                }
            i++ ;
            }
            //return semesterCourses ;
        }catch(SQLException sqle){
        //System.out.println("Exception thrown at getSemesterCourseArray()\n"
        //+ sqle.getMessage());
        }
        return semesterCourses ;
    }
    
    /**
     * Method to create the user basic data table
     */
    private void createUserDataTable(){
        String dataString = "(Number INTEGER NOT NULL DEFAULT 1,"
                + "Grade_System INTEGER NOT NULL DEFAULT 5,"
                + " Last_Name VARCHAR(" + DB_Manipulator.FIRST_NAME_LENGTH + ") NOT NULL DEFAULT 'Surname',"
                + " First_Name VARCHAR(" + DB_Manipulator.LAST_NAME_LENGTH + ") NOT NULL DEFAULT 'Other names',"
                + " User_Name VARCHAR(" + DB_Manipulator.USER_NAME_LENGTH + ") NOT NULL DEFAULT 'UserName/Matric',"
                + " User_Password VARCHAR(" + DB_Manipulator.USER_PASSWORD_LENGTH + ") NOT NULL DEFAULT 'Password',"
                + " School_Name VARCHAR(" + DB_Manipulator.SCHOOL_NAME_LENGTH + ") NOT NULL DEFAULT 'School name',"
                + " Faculty_Name VARCHAR(" + DB_Manipulator.FACULTY_NAME_LENGTH + ") NOT NULL DEFAULT 'Faculty name',"
                + " Department_Name VARCHAR(" + DB_Manipulator.DEPARTMENT_NAME_LENGTH + ") NOT NULL DEFAULT 'Department name',"
                + " Total_Sessions INTEGER NOT NULL DEFAULT 0,"
                + " Total_Semesters INTEGER NOT NULL DEFAULT 0,"
                + " Last_Session_Viewed INTEGER NOT NULL DEFAULT 1,"
                + " Use_Count INTEGER NOT NULL DEFAULT 0,"
                + "PRIMARY KEY(Number))";
        try{
            Statement st = this.connection.createStatement();
            st.executeUpdate("CREATE TABLE " + DB_Manipulator.DB_NAME + "." + DB_Manipulator.USER_DATA_TABLE + dataString );
            st.executeUpdate("INSERT INTO " + DB_Manipulator.DB_NAME + "." + DB_Manipulator.USER_DATA_TABLE 
                    + " VALUES("
                    + " 1, 5, 'Surname', 'Other name', 'User name', 'Password', 'School', 'Faculty', 'Department',"
                    + "0, 0, 1, 0)");
            
            //System.out.println("User table successfully created");
        }catch(SQLException sqle){
            //do nothing
            //System.out.println("Exception thrown in createUserTable()\n"
            //        + sqle.getMessage());
        }
    }
    
    /**
     * Method to create the semester tables
     * @param sms number of semesters to created
     */
    void createSemesterTables(int smsNum){
        String updateString = "(Course_Code VARCHAR(" + DB_Manipulator.COURSE_CODE_LENGTH + ") NOT NULL,"
                + "Course_Additional_Info VARCHAR(" + DB_Manipulator.COURSE_ADDITIONAL_INFO_LENGTH + ") NOT NULL,"  // created at 20/sept/2014(without set and get methods yet) 
                + "Course_Title VARCHAR(" + DB_Manipulator.COURSE_TITLE_LENGTH + ") NOT NULL,"
                + "Course_Unit INTEGER NOT NULL,"
                + "Course_Grade VARCHAR(" + DB_Manipulator.COURSE_GRADE_LENGTH + ") NOT NULL,"
                + "Course_Score INTEGER NOT NULL,"
                //+ "Course_Carried INTEGER NOT NULL,"  // O for carried and 1 for uncarried
                + "Course_Registered INTEGER NOT NULL," // 0 for registered and 1 for unregistered
                + "PRIMARY KEY(Course_Code))";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            for(int i = 0; i < smsNum; ++i){
                st.executeUpdate("CREATE TABLE " + DB_Manipulator.DB_NAME + "." + DB_Manipulator.SEMESTER_NAMES[i] +
                        updateString);
            //System.out.println("semester table " + (i+1) + " successfully created");
            }
        }catch (SQLException sqle){
            //System.out.println("Erron in createSemesterTables()\n" +
            //        sqle.getMessage());
        }
    }
    
    /**
     * Method to get the userName
     * @return userName from the database
     */
    public String getUserName(){
        String userName = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT User_Name FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            userName = rs.getString("User_name");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getUserName()\n" + sqle.getMessage());
            //do nothing
        }
        return userName ;
    }
    
    /**
     * method to set the userName
     * @param uName new user name to be inputted in to the database
     */
    public void setUserName(String uName){
        String userName = uName ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET User_Name = '"
            + userName + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in setUserName()\n" + sqle.getMessage());
            //do nothing
        }
    }
    
    
    /**
     * Method to get the lastName
     * @return lastName from the database
     */
    public String getLastName(){
        String surname = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Last_Name FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            surname = rs.getString("Last_Name");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getLastName()\n" + sqle.getMessage());
            //do nothing
        }
        return surname ;
    }
    
    /**
     * method to set the lastName
     * @param sName new last name to be inputted in to the database
     */
    public void setLastName(String sName){
        String surname = sName ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Last_Name = '"
            + surname + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in setSurname()\n" + sqle.getMessage());
            //do nothing
        }
    }
    
    /**
     * Method to get the firstName
     * @return firstName from the database
     */
    public String getFirstName(){
        String otherName = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT First_Name FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            otherName = rs.getString("First_Name");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getUserName()\n" + sqle.getMessage());
            //do nothing
        }
        return otherName ;
    }
    
    /**
     * method to set the first Name
     * @param oName new first name to be inputted in to the database
     */
    public void setFirstName(String oName){
        String otherName = oName ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET First_Name = '"
            + otherName + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
        }
    }
    
     /**
     * Method to get the password
     * @return password from the database
     */
    public String getUserPassword(){
        String password = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT User_Password FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            password = rs.getString("User_Password");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getUserPassword()\n" + sqle.getMessage());
            //do nothing
        }
        return password ;
    }
    
    /**
     * method to set the userName
     * @param pWord new password to be inputted in to the database
     */
    public void setUserPassword(String pWord){
        String password = pWord ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET User_Password = '"
            + password + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
        }
    }
    
    /**
     * Method to get the school name from the database
     * @return school name
     */
    public String getSchoolName(){
        String schoolName = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT School_Name FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            schoolName = rs.getString("School_Name");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getSchoolName()\n" + sqle.getMessage());
            //do nothing
        }
        return schoolName ;
    }
    
    /**
     * Method to set the school name in this database
     * @param schlName new school name
     */
    public void setSchoolName(String schlName){
        String schoolName = schlName ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET School_Name = '"
            + schoolName + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
        }
    }
    
    /**
     * method to get the faculty name from this database
     * @return faculty name from the database
     */
    public String getFacultyName(){
        String facultyName = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Faculty_Name FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            facultyName = rs.getString("Faculty_Name");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getFacultyName()\n" + sqle.getMessage());
            //do nothing
        }
        return facultyName ;
    }
    
    /**
     * Method to get the faculty name in this database
     * @param facName new faculty name
     */
    public void setFacultyName(String facName){
        String facultyName = facName ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Faculty_Name = '"
            + facultyName + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
        }
    }
    
    /**
     * Method to get the department name as stored in this database
     * @return department name
     */
    public String getDepartmentName(){
        String deptName = "";
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Department_Name FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            deptName = rs.getString("Department_Name");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getDepartmentName()\n" + sqle.getMessage());
            //do nothing
        }
        return deptName ;
    }
    
    /**
     * set the department name in the database
     * @param dName new department name
     */
    public void setDepartmentName(String dName){
        String deptName = dName ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Department_Name = '"
            + deptName + "' WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
        }
    }
    
    /**
     * Method to get the total sessions from the database
     * @return integer of the total sessions
     */
    public int getTotalSessions(){
        int totSessions = 0;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Total_Sessions FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            totSessions = rs.getInt("Total_Sessions");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getTotalSessions()\n" + sqle.getMessage());
            //do nothing
        }
        return totSessions ;
    }
    
    /**
     * Method to set the total sessions in this semester
     * @param tSessions total sessions in this semester
     * this method automatically sets the total number of semesters 
     * note!  2 semesters makes a session
     */
    public void setTotalSessions(int tSessions){
        int totalSessions = tSessions + 1 ;  // add one to include the spillover session
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Total_Sessions = "
            + totalSessions + " WHERE Number = 1");
            
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Total_Semesters = "
            + (totalSessions * 2) + " WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
        }
    }
     
    /**
     * get the total semesters
     * @return total semesters as an integer
     */
     public int getTotalSemesters(){
        int totSemesters = 0;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Total_Semesters FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            totSemesters = rs.getInt("Total_Semesters");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getTotalSemesters()\n" + sqle.getMessage());
            //do nothing
        }
        return totSemesters ;
    }
     
     /**
      * method to set the last session viewed by the user
      * @param sViewed last session viewed
      */
     public void setLastSessionViewed(int sViewed){
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Last_Session_Viewed = "
            + sViewed + " WHERE Number = 1");   
        }catch(SQLException sqle){
            //do nothing
        }
    }
     
     /**
      * Method to get the last session viewed
      * @return last session viewed as an integer
      */
     public int getLastSessionViewed(){
        int lViewed = 1;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Last_Session_Viewed FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            lViewed = rs.getInt("Last_Session_Viewed");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getLastSessionViewed()\n" + sqle.getMessage());
            //do nothing
        }
        return lViewed ;
    }
     
     public int getUseCount(){
        int uCount = 0;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Use_Count FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            uCount = rs.getInt("Use_Count");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getUseCount()\n" + sqle.getMessage());
            //do nothing
        }
        return uCount ;
    }
     
     public void updateUseCount(){
         int useCount = this.getUseCount() + 1 ;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Use_Count = "
            + useCount + " WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
             //System.out.println("Exception thrown in updateUseCount()\n" + sqle.getMessage());
        }
    }
    /**
     * Method to get the grade style used
     * @return gradePointSystem
     */ 
    public int getGradeSystem(){
        int gSys = 0;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            ResultSet rs = st.executeQuery("SELECT Grade_System FROM " +
                    DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE);
            rs.next();
            gSys = rs.getInt("Grade_System");
        }catch(SQLException sqle){
            //System.out.println("Exception thrown in getGradeSystem()\n" + sqle.getMessage());
            //do nothing
        }
        return gSys ;
    }
    
    /**
     * Method to set the grade System
     * @param gS gradeSystem, should be either 4 or 5
     */
    public void setGradeSystem(int gS){
         int gSys = gS;
        try(Connection con = DriverManager.getConnection("jdbc:derby:" + DB_Manipulator.DB_NAME, DB_Manipulator.USER_NAME, DB_Manipulator.PASSWORD);
            Statement st = con.createStatement();){
            st.executeUpdate("UPDATE " + DB_Manipulator.DB_NAME + "." +
                    DB_Manipulator.USER_DATA_TABLE +" SET Grade_System = "
            + gSys + " WHERE Number = 1");
            
        }catch(SQLException sqle){
            //do nothing
             //System.out.println("Exception thrown in setGradeSystem()\n" + sqle.getMessage());
        }
    }
   
}
