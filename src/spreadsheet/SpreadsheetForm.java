/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spreadsheet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author 186900
 */
public class SpreadsheetForm extends javax.swing.JPanel
{
  private int rows;
  private int columns;
  private JTable table;
  private JScrollPane scrollPane;

  /** Creates new form SpreadsheetForm */
  public SpreadsheetForm()
  {
    initComponents();
    //createTable(30,4);

  }
  
  public SpreadsheetForm(int r, int c)
  {
    initComponents();
    createTable(r,c);
  }

  public void createTable(int r, int c){
    try{
      super.remove(scrollPane);
    }
    catch (NullPointerException e){
    }
      
    rows = r; //<=30
    columns = c; //<=6
    table = new JTable(rows,columns);
    //table.setBounds(313-50*c+10,60,100*c,30*r);
    table.setRowHeight(30);
    for (int i=0; i<c; i++) {
      TableColumn column = table.getColumnModel().getColumn(i);
      if (c<=6) {
        //column.setPreferredWidth(607/c);
      } 
      else {
        column.setPreferredWidth(610/6);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
      }
    }

    //table.doLayout();
    //if (r>30 || c>6){
    //}
    //else super.add(table);
    scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    
    
    super.add(scrollPane);
    if (r<30){
      if (c<=6){
        scrollPane.setBounds(20,50,610,30*r+23);
      }
      else scrollPane.setBounds(20,50,610,30*r+38);
    }
    else scrollPane.setBounds(20,50,610,30*29+23);
    ExcelAdapter a = new ExcelAdapter(table);
    super.repaint();
  }
  
  public void saveFile()
  {
    //System.out.println(table.getValueAt(3,3));
    final JFileChooser fc = new JFileChooser();
    int returnVal = fc.showSaveDialog(this);
    
    if (returnVal == JFileChooser.APPROVE_OPTION){
      File chosen = fc.getSelectedFile();
      String name = chosen.getName();
      //System.out.println(name.substring(name.length()-4));
      try{
        if (!name.substring(name.length()-4).equals(".ssf")){
          chosen = new File(""+fc.getSelectedFile()+".ssf");
          name+=".ssf";
        }
      }
      catch (StringIndexOutOfBoundsException e){
        chosen = new File(""+fc.getSelectedFile()+".ssf");
        name+=".ssf";
      }
      //System.out.println(name);
      
      
      PrintWriter out;
      try {
        out = new PrintWriter(new BufferedWriter(new FileWriter(chosen)));
        
        out.println(""+rows+","+columns);
        for (int r=0; r<rows; r++){
          for (int c=0; c<columns; c++){
            try{
              //System.out.print(table.getValueAt(r,c));
              out.print(table.getValueAt(r,c).toString()+",");
            }
            catch (NullPointerException e){
              out.print(",");
            }
          }
          out.println();
        }
        out.flush();
        out.close();
        //System.out.println(out);
      }
      catch (IOException ex) {
        System.out.print("ERROR");
        Logger.getLogger(SpreadsheetForm.class.getName()).log(Level.SEVERE, null, ex);
      } 
    }
  }
  
  
  public void openFile()
  {
    final JFileChooser fc = new JFileChooser();
    int returnVal = fc.showOpenDialog(this);
    if (returnVal == JFileChooser.APPROVE_OPTION){
      try {
        File file = fc.getSelectedFile();
        Scanner sc = new Scanner(file);
        int line = 0;
        while (sc.hasNextLine()){
          String[] text = sc.nextLine().split(",");
          
          if (line==0){
            //System.out.println(text);
            rows = Integer.parseInt(text[0]);
            columns = Integer.parseInt(text[1]);
            //System.out.println(""+rows+","+columns);
            
            try{
              super.remove(scrollPane);
            }
            catch (NullPointerException e){
            }
            
            this.createTable(rows, columns);
            line++;
          }
          else{
            //System.out.println(text.length);
            for (int i=0; i<text.length; i++){
              table.setValueAt(text[i],line-1,i); 
            }
            
           // DefaultTableModel model = new DefaultTableModel();
            //model.addRow(text);
            //table = new JTable(model);
            //System.out.println(text);
            line++;
          }
          //System.out.println(sc.next());
        }
        super.add(scrollPane);
      }
      catch (FileNotFoundException ex) {
        Logger.getLogger(SpreadsheetForm.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel1.setText("Rows:");

        jLabel2.setText("Columns:");

        jButton1.setText("Create");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Save");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Open");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Clear");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton4)))
                .addContainerGap(945, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton1ActionPerformed
  {//GEN-HEADEREND:event_jButton1ActionPerformed
    try{
      rows = parseInt(jTextField1.getText());
    }
    catch (NumberFormatException e){
    }
    try{
      columns = parseInt(jTextField2.getText());
    }
    catch (NumberFormatException e){
    }
    
    //System.out.print("Hello");
    this.createTable(rows,columns);

  }//GEN-LAST:event_jButton1ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton2ActionPerformed
  {//GEN-HEADEREND:event_jButton2ActionPerformed
    saveFile();
  }//GEN-LAST:event_jButton2ActionPerformed

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton3ActionPerformed
  {//GEN-HEADEREND:event_jButton3ActionPerformed
    openFile();
  }//GEN-LAST:event_jButton3ActionPerformed

  private void jButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jButton4ActionPerformed
  {//GEN-HEADEREND:event_jButton4ActionPerformed
    try{
      super.remove(scrollPane);
    }
    catch (NullPointerException e){
    }
    super.repaint();
  }//GEN-LAST:event_jButton4ActionPerformed


  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
