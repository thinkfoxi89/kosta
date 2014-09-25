package ex2;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * TestCode.java
 *
 * Created on 2009. 8. 11, ?��?? 12:15:25
 */


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc1
 */
public class TestCode extends javax.swing.JFrame {

    private Socket sk;
    private PrintWriter pw;
    private String nickName;
    private int ptx;
    private int pty;
    private CardLayout card;
    private String crv;
    private Color cr;
    /** Creates new form TestCode */
    public TestCode() {
        initComponents();
       
    }

    private void exe() {
        try {
            nickName = namev.getText();
            sk = new Socket("192.168.7.3",7777); // ip ?? port �? ??�? ????
            pw = new PrintWriter(sk.getOutputStream(), true); // �??? ?��?�림
           
        } catch (Exception ex) {
            System.out.println("???��?�????? ?��?��?? ???��?�?");
        }
        Thread t = new Thread(new Runnable() {
           

            public void run() {

                BufferedReader br = null;
                try {
                    br = new BufferedReader(new InputStreamReader(sk.getInputStream()));
                    
                    while (true) {
                        String str = br.readLine();
                        System.out.println("str :" + str);
                       StringTokenizer stn = new StringTokenizer(str, "/");
                        String com = stn.nextToken();
                        System.out.println("com:::"+com);
                       
                        if (com.equals("draw")) { 
                            crv = stn.nextToken(); 
                            System.out.println("Color"+crv);
                            ptx = Integer.parseInt(stn.nextToken()); 
                            pty = Integer.parseInt(stn.nextToken());
                            if(crv.equalsIgnoreCase("RED")){
                                cr = Color.RED;
                            }else{
                                cr = Color.BLUE;
                            }
                        }
                        can.repaint();
                    }
                    
                } catch (IOException ex) {
                }
            }
        });
                t.start();


        can.addMouseMotionListener(new MouseMotionAdapter() {
           

            @Override
            public void mouseDragged(MouseEvent e) {
             
                int x = e.getX();
                int y = e.getY();
              
                pw.println("draw" + "/" + crv + "/" + x + "/" + y);
            }
        });
       
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        namev = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        can = new java.awt.Canvas(){
            @Override
            public void paint(Graphics g) {
                if ((ptx != 0) || (pty !=0)) {
                    g.setColor(cr);
                    g.fillOval(ptx, pty, 10, 10);
                }
            }

            @Override
            public void update(Graphics g) {
                paint(g);
            }

        };
        userName = new javax.swing.JLabel();
        redBtn = new javax.swing.JButton();
        blueBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 153));

        jScrollPane1.setViewportView(namev);

        jButton1.setText("����");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("����", 1, 18)); // NOI18N
        jLabel1.setText("[��Ʈ��ũ �׸��� �Է¿���]");

        jLabel2.setText("�г����Է�");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1)))))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jLabel1)
                .addGap(226, 226, 226)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, "card2");

        can.setBackground(new java.awt.Color(255, 255, 255));

        userName.setText("jLabel1");

        redBtn.setText("RED");
        redBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                redBtnActionPerformed(evt);
            }
        });

        blueBtn.setText("BLUE");
        blueBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blueBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(can, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(redBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(blueBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(can, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName)
                    .addComponent(redBtn)
                    .addComponent(blueBtn))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        card = (CardLayout) jPanel1.getLayout();
        card.show(jPanel1, "card3");
        userName.setText(namev.getText());
         exe();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void redBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_redBtnActionPerformed
        // TODO add your handling code here:
        crv = "RED";
    }//GEN-LAST:event_redBtnActionPerformed

    private void blueBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blueBtnActionPerformed
        // TODO add your handling code here:
        crv = "BLUE";
        
    }//GEN-LAST:event_blueBtnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new TestCode().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton blueBtn;
    private java.awt.Canvas can;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane namev;
    private javax.swing.JButton redBtn;
    private javax.swing.JLabel userName;
    // End of variables declaration//GEN-END:variables
}
