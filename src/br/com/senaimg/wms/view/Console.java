/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 *
 * @author ÁlefeLucas
 */
public class Console extends javax.swing.JFrame {

    private static Console console;

    public static Console create() {
        if (console == null) {
            console = new Console();
        }
        return console;
    }

    public static Console getConsole() {
        return console;
    }
    private final StringBuilder sb = new StringBuilder();

    /**
     * Creates new form Console
     */
    private Console() {
        initComponents();
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        //System.setErr(jTextAreaConsole.getP);

        PrintStream oldErr = System.err;
        PrintStream oldOut = System.out;
        PrintStream ps = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                if (b == '\r') {
                    return;
                }

                if (b == '\n') {
                    final String text = sb.toString();
                    SwingUtilities.invokeLater(() -> {
                        //jTextPane.appen(text);
                        StyledDocument sDoc = jTextPane.getStyledDocument();
                        Style s = jTextPane.addStyle("Err", null);
                        StyleConstants.setForeground(s, Color.red);

                        try {
                            sDoc.insertString(sDoc.getLength(), text + "\n", s);
                        } catch (BadLocationException ex) {

                        }
                    });
                    sb.setLength(0);
                }
                sb.append((char) b);
            }
        });
        PrintStream psOut = new PrintStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                if (b == '\r') {
                    return;
                }

                if (b == '\n') {
                    final String text = sb.toString();
                    SwingUtilities.invokeLater(() -> {
                        //jTextPane.appen(text);
                        StyledDocument sDoc = jTextPane.getStyledDocument();
                        Style s = jTextPane.addStyle("Out", null);
                        StyleConstants.setForeground(s, Color.white);

                        try {
                            sDoc.insertString(sDoc.getLength(), text, s);
                        } catch (BadLocationException ex) {

                        }
                    });
                    sb.setLength(0);
                }
                sb.append((char) b);
            }
        });
        System.setOut(psOut);
        System.setErr(ps);
      
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
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane = new javax.swing.JTextPane();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 300));

        jTextPane.setEditable(false);
        jTextPane.setBackground(new java.awt.Color(0, 0, 0));
        jTextPane.setFont(new java.awt.Font("Consolas", 0, 11)); // NOI18N
        jTextPane.setForeground(new java.awt.Color(255, 255, 255));
        jTextPane.setToolTipText("");
        jScrollPane3.setViewportView(jTextPane);

        getContentPane().add(jScrollPane3, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane;
    // End of variables declaration//GEN-END:variables

}
