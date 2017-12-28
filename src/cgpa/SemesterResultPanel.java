/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

/**
 *
 * @author AZYSS
 */
public class SemesterResultPanel extends javax.swing.JPanel {

    /**
     * Creates new form SemesterGPCodesPanel
     */
    public SemesterResultPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gpaCodingLabel = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        gpaLabel = new javax.swing.JLabel();
        gpaField = new javax.swing.JTextField();
        coField = new javax.swing.JTextField();
        totalCoursesOfferedLabel = new javax.swing.JLabel();
        coLabel = new javax.swing.JLabel();
        tnuLabel = new javax.swing.JLabel();
        tcpLabel = new javax.swing.JLabel();
        tnuField = new javax.swing.JTextField();
        tcpField = new javax.swing.JTextField();
        mcLabel1 = new javax.swing.JLabel();
        mcField = new javax.swing.JTextField();
        totalCoursesField = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        setMaximumSize(new java.awt.Dimension(284, 115));
        setMinimumSize(new java.awt.Dimension(284, 115));
        setPreferredSize(new java.awt.Dimension(284, 115));
        setLayout(null);

        gpaCodingLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/multimedia/png/gradingPointCodes.png"))); // NOI18N
        gpaCodingLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));
        add(gpaCodingLabel);
        gpaCodingLabel.setBounds(120, 10, 180, 130);

        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 255), 1, true));
        jPanel1.setOpaque(false);
        jPanel1.setLayout(null);

        gpaLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        gpaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gpaLabel.setText("GPA =");
        gpaLabel.setFocusable(false);
        jPanel1.add(gpaLabel);
        gpaLabel.setBounds(0, 110, 43, 13);

        gpaField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        gpaField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        gpaField.setToolTipText("");
        gpaField.setBorder(null);
        gpaField.setFocusable(false);
        gpaField.setOpaque(false);
        jPanel1.add(gpaField);
        gpaField.setBounds(50, 110, 60, 15);

        coField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        coField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        coField.setToolTipText("");
        coField.setBorder(null);
        coField.setFocusable(false);
        coField.setOpaque(false);
        jPanel1.add(coField);
        coField.setBounds(48, 50, 60, 13);

        totalCoursesOfferedLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        totalCoursesOfferedLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalCoursesOfferedLabel.setText("TNC =");
        totalCoursesOfferedLabel.setFocusable(false);
        jPanel1.add(totalCoursesOfferedLabel);
        totalCoursesOfferedLabel.setBounds(0, 90, 43, 13);

        coLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        coLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        coLabel.setText("C.O =");
        coLabel.setFocusable(false);
        jPanel1.add(coLabel);
        coLabel.setBounds(1, 50, 43, 13);

        tnuLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tnuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tnuLabel.setText("TNU =");
        tnuLabel.setFocusable(false);
        jPanel1.add(tnuLabel);
        tnuLabel.setBounds(1, 31, 43, 13);

        tcpLabel.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tcpLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tcpLabel.setText("TCP =");
        tcpLabel.setToolTipText("Total Credit Point");
        tcpLabel.setFocusable(false);
        jPanel1.add(tcpLabel);
        tcpLabel.setBounds(1, 12, 43, 13);

        tnuField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tnuField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tnuField.setToolTipText("");
        tnuField.setBorder(null);
        tnuField.setFocusable(false);
        tnuField.setOpaque(false);
        tnuField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tnuFieldActionPerformed(evt);
            }
        });
        jPanel1.add(tnuField);
        tnuField.setBounds(48, 31, 60, 13);

        tcpField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        tcpField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        tcpField.setToolTipText("");
        tcpField.setBorder(null);
        tcpField.setFocusable(false);
        tcpField.setOpaque(false);
        jPanel1.add(tcpField);
        tcpField.setBounds(48, 12, 60, 13);

        mcLabel1.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mcLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        mcLabel1.setText("M.C =");
        mcLabel1.setFocusable(false);
        jPanel1.add(mcLabel1);
        mcLabel1.setBounds(1, 69, 43, 13);

        mcField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        mcField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        mcField.setToolTipText("");
        mcField.setBorder(null);
        mcField.setFocusable(false);
        mcField.setOpaque(false);
        mcField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcFieldActionPerformed(evt);
            }
        });
        jPanel1.add(mcField);
        mcField.setBounds(48, 69, 60, 13);

        totalCoursesField.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        totalCoursesField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        totalCoursesField.setToolTipText("");
        totalCoursesField.setBorder(null);
        totalCoursesField.setFocusable(false);
        totalCoursesField.setOpaque(false);
        totalCoursesField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCoursesFieldActionPerformed(evt);
            }
        });
        jPanel1.add(totalCoursesField);
        totalCoursesField.setBounds(50, 90, 60, 13);

        add(jPanel1);
        jPanel1.setBounds(10, 10, 90, 130);
    }// </editor-fold>//GEN-END:initComponents

    private void tnuFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tnuFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tnuFieldActionPerformed

    private void mcFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mcFieldActionPerformed

    private void totalCoursesFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalCoursesFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalCoursesFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField coField;
    private javax.swing.JLabel coLabel;
    private javax.swing.JLabel gpaCodingLabel;
    private javax.swing.JTextField gpaField;
    private javax.swing.JLabel gpaLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField mcField;
    private javax.swing.JLabel mcLabel1;
    private javax.swing.JTextField tcpField;
    private javax.swing.JLabel tcpLabel;
    private javax.swing.JTextField tnuField;
    private javax.swing.JLabel tnuLabel;
    private javax.swing.JTextField totalCoursesField;
    private javax.swing.JLabel totalCoursesOfferedLabel;
    // End of variables declaration//GEN-END:variables

    
    void setTotalCourses(int totCourses){
        this.totalCoursesField.setText(Integer.toString(totCourses));
    }
    void setGPA(double gpa){
        this.gpaField.setText(Double.toString(roundNumber(gpa, 1)));
    }
    
    static double roundNumber(double number, double dcp){
        double tempVal = Math.round(number * Math.pow(10, (dcp + 1)));
        return tempVal / Math.pow(10, (dcp + 1));
    }
    
    void setCO(int co){
        this.coField.setText(Integer.toString(co));
    }
    
    void setMC(int mc){
        this.mcField.setText(Integer.toString(mc));
    }
    
    void setTCP(double tcp){
        this.tcpField.setText(Integer.toString((int)tcp));
    }
    
    void setTNU(double units){
        this.tnuField.setText(Integer.toString((int)units));
    }

}