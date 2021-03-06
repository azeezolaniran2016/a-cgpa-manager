/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cgpa;

import java.awt.GridLayout;

/**
 *
 * @author AZYSS
 */
public class CGPAResultPanel extends javax.swing.JPanel {

    /**
     * Creates new form CGPAResultPanel
     */
    public CGPAResultPanel() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        sessionsResultPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        cgpaLabel = new javax.swing.JLabel();
        cgpaField = new javax.swing.JTextField();
        cgpaTranslation = new javax.swing.JLabel();
        cTNULabel = new javax.swing.JLabel();
        cTCPLabel1 = new javax.swing.JLabel();
        cTNUTextField = new javax.swing.JTextField();
        cTCPextField = new javax.swing.JTextField();

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        sessionsResultPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        sessionsResultPanel.setMaximumSize(new java.awt.Dimension(100, 2000));
        sessionsResultPanel.setMinimumSize(new java.awt.Dimension(100, 2000));
        sessionsResultPanel.setName(""); // NOI18N
        sessionsResultPanel.setPreferredSize(new java.awt.Dimension(100, 2000));

        javax.swing.GroupLayout sessionsResultPanelLayout = new javax.swing.GroupLayout(sessionsResultPanel);
        sessionsResultPanel.setLayout(sessionsResultPanelLayout);
        sessionsResultPanelLayout.setHorizontalGroup(
            sessionsResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        sessionsResultPanelLayout.setVerticalGroup(
            sessionsResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1996, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(sessionsResultPanel);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 255)));

        cgpaLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        cgpaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cgpaLabel.setText("C.G.P.A = ");

        cgpaField.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        cgpaField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cgpaField.setFocusable(false);

        cgpaTranslation.setFont(new java.awt.Font("Levenim MT", 0, 12)); // NOI18N
        cgpaTranslation.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cTNULabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cTNULabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cTNULabel.setText("CTNU =");
        cTNULabel.setToolTipText("");

        cTCPLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cTCPLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cTCPLabel1.setText("CTCP =");
        cTCPLabel1.setToolTipText("");

        cTNUTextField.setColumns(3);
        cTNUTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cTNUTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cTNUTextField.setFocusable(false);

        cTCPextField.setColumns(3);
        cTCPextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cTCPextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        cTCPextField.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cTCPLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cTNULabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cgpaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(cTCPextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                    .addComponent(cgpaField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cTNUTextField))
                .addGap(33, 33, 33)
                .addComponent(cgpaTranslation, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cTNULabel)
                    .addComponent(cTNUTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cTCPLabel1)
                    .addComponent(cTCPextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cgpaTranslation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cgpaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cgpaField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cTCPLabel1;
    private javax.swing.JTextField cTCPextField;
    private javax.swing.JLabel cTNULabel;
    private javax.swing.JTextField cTNUTextField;
    private javax.swing.JTextField cgpaField;
    private javax.swing.JLabel cgpaLabel;
    private javax.swing.JLabel cgpaTranslation;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel sessionsResultPanel;
    // End of variables declaration//GEN-END:variables

        CGPAResultPanel(Session[] sessions, int gradeType){
            this.initComponents();
            double tcp = 0 ;
            double tup = 0 ;
            SessionResultsPanel[] srp = new SessionResultsPanel[sessions.length];
            for(int i = 0; i < srp.length; ++i){
                String tt = "" ;
                if(i == (sessions.length - 1))
                    srp[i] = Department.createSessionResultTable(sessions[i], Integer.toString(i + 1) + " (Spill-Over) ");
                else if(i == (sessions.length - 2))
                    srp[i] = Department.createSessionResultTable(sessions[i], Integer.toString(i + 1) + " (Finals )");
                else
                    srp[i] = Department.createSessionResultTable(sessions[i], Integer.toString(i + 1));
                tcp += sessions[i].getSessionCreditPoint();
                tup += sessions[i].getSessionUnit() ;
            }
            
            this.sessionsResultPanel.setLayout(new GridLayout(sessions.length, 1));
            for(int j = 0; j < sessions.length; ++j){
                this.sessionsResultPanel.add(srp[j]);
            }
            
            double cgpa  ;
            cgpa = tcp / tup ;
            this.cTCPextField.setText(Double.toString(tcp));
            this.cTNUTextField.setText(Double.toString(tup));
            this.cgpaField.setText(Double.toString(SemesterResultPanel.roundNumber(cgpa, 1))) ;
            this.createGradeTranslation(cgpa, gradeType);
            
        }
        private void createGradeTranslation(double gp, int type){
            if (type == 5){
                if(gp >= 4.5)
                    this.cgpaTranslation.setText("First Class");
                else if(gp >= 3.5 && gp < 4.5)
                    this.cgpaTranslation.setText("Second Class Honours (Upper Division)");
                else if(gp >= 2.5 && gp <3.5)
                    this.cgpaTranslation.setText("Second Class Honours (Lower Division)");
                else if(gp >= 1.5 && gp < 2.5)
                    this.cgpaTranslation.setText("Third Class Honours");
                else if(gp >= 1.0 && gp < 1.5)
                    this.cgpaTranslation.setText("Pass");
                else
                    this.cgpaTranslation.setText("Advice To Withdraw");
            }
        }

}
