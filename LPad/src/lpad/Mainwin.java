
package lpad;

import java.awt.BorderLayout;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.text.DefaultEditorKit;



public class Mainwin extends JFrame {

    JTextArea body;
    JMenu file,edit,help;
    JMenuItem open,exit,newFile,editF,copy,cut,paste,delete,update,helps,print,save,lookAndFeel;
    JButton openT,newFileT,helpsT,printT,saveT;
    JToolBar toolBar;
    JMenuBar menuBar;
    String fileReaderBody;
    String dir;
    Event event;
    FileAction action;
    

    Mainwin(){

        super("LPad Edit");
        setSize(500,350);
        setDefaultCloseOperation(2);
        event=new Event();

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ex) {

            JOptionPane.showMessageDialog(null, "Joder con esto");

        }


        //component
        body=new JTextArea();

        ImageIcon openI=new ImageIcon(Mainwin.class.getResource("Icon/open.png"));
        open=new JMenuItem("Open",openI);

        ImageIcon exitI=new ImageIcon(Mainwin.class.getResource("Icon/close.png"));
        exit=new JMenuItem("Exit",exitI);

        ImageIcon newI=new ImageIcon(Mainwin.class.getResource("Icon/new.png"));
        newFile=new JMenuItem("New File",newI);

        ImageIcon saveI=new ImageIcon(Mainwin.class.getResource("Icon/save.png"));
        save=new JMenuItem("Save",saveI);

        ImageIcon printI=new ImageIcon(Mainwin.class.getResource("Icon/print.png"));
        print=new JMenuItem("Print",printI);
         
        copy=new JMenuItem(new DefaultEditorKit.CopyAction() );
        ImageIcon copyI=new ImageIcon(Mainwin.class.getResource("Icon/copy.png"));
        copy.setIcon(copyI);
        copy.setText("Copy");

        cut=new JMenuItem(new DefaultEditorKit.CutAction() );
        ImageIcon cutI=new ImageIcon(Mainwin.class.getResource("Icon/cortar.png"));
        cut.setText("Cut");
        cut.setIcon(cutI);
        
        paste=new JMenuItem(new DefaultEditorKit.PasteAction() );
        ImageIcon pasteI=new ImageIcon(Mainwin.class.getResource("Icon/pegar.png"));
        paste.setText("Paste");
        paste.setIcon(pasteI);

        delete=new JMenuItem();
        ImageIcon deleteI=new ImageIcon(Mainwin.class.getResource("Icon/delete.png"));
        delete.setText("Delete All");
        delete.setIcon(deleteI);

        ImageIcon updateI=new ImageIcon(Mainwin.class.getResource("Icon/update.png"));
        update=new JMenuItem("About Us",updateI);

        ImageIcon helpI=new ImageIcon(Mainwin.class.getResource("Icon/help2.png"));
        helps=new JMenuItem("Help",helpI);

        ImageIcon setLookI=new ImageIcon(Mainwin.class.getResource("Icon/look.png"));
        lookAndFeel=new JMenuItem("Set Skin",setLookI);
        

        //toolbar
        openT=new JButton("Open",openI);
        openT.setToolTipText("Open");


        newFileT=new JButton("New File",newI);
        newFileT.setToolTipText("New File");


        helpsT=new JButton("Help",helpI);
        helpsT.setToolTipText("Help");


        printT=new JButton("Print",saveI);
        printT.setToolTipText("Print");

         
        saveT=new JButton("Save",printI);
        saveT.setToolTipText("Save");
        
        toolBar=new JToolBar();
        toolBar.setFloatable(false);

        

        

        //Menus
        file=new JMenu("File");
        edit=new JMenu("Edit");
        help=new JMenu("Help");

        //Menu Bar
        menuBar=new JMenuBar();


        //Add Component
        file.add(newFile);
        file.add(open);
        file.add(save);
        file.add(print);
        file.addSeparator();
        file.add(exit);
        

         
        edit.add(paste);
        edit.add(copy);
        edit.add(cut);
        edit.addSeparator();
        edit.add(delete);

        help.add(update);
        help.add(helps);
        help.add(lookAndFeel);

        //Componet Toolbar
        toolBar.add(newFileT);
        toolBar.add(openT);
        toolBar.add(saveT);
        toolBar.addSeparator();
        toolBar.add(printT);
        toolBar.add(helpsT);
        

        //add menus
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(help);

        setJMenuBar(menuBar);
        JScrollPane sc=new JScrollPane();
        sc.setViewportView(body);
        
        
        add(toolBar,BorderLayout.NORTH);
        body.setLineWrap(true);
        body.setWrapStyleWord(true);

        add(sc);

        //Action Event menu
        exit.addActionListener(event);
        open.addActionListener(event);
        save.addActionListener(event);
        newFile.addActionListener(event);
        print.addActionListener(event);
        update.addActionListener(event);
        helps.addActionListener(event);
        delete.addActionListener(event);
        lookAndFeel.addActionListener(event);

        //Event toolbar
        openT.addActionListener(event);
        newFileT.addActionListener(event);
        helpsT.addActionListener(event);
        printT.addActionListener(event);
        saveT.addActionListener(event);
        


        setVisible(true);

      
    }

    public String nameWin(String nameFrame){

        this.dir=nameFrame;
        return  nameFrame;

    }

        class Event implements ActionListener{

        String fileDir="";
        
        public void actionPerformed(ActionEvent event){
            

            //--------------------------------------------------------------Exit
            if(event.getSource()==exit){
                int exitYN=JOptionPane.showConfirmDialog(null, "Seguro De Salir","Exit",JOptionPane.YES_OPTION);
                
                if(exitYN==0){
                Toolkit.getDefaultToolkit().beep();
                System.exit(0);
                }else{}
            }

            //--------------------------------------------------------------Open

            if(event.getSource()==open ){

                JFileChooser fileC=new JFileChooser("Open");
                int fileA=fileC.showOpenDialog(null);

                if(fileA==JFileChooser.APPROVE_OPTION){

                    File dir=fileC.getSelectedFile();
                    fileDir=dir.getParent()+"\\"+dir.getName();
                    
                   }
                String bodyTxt;
                   action=new FileAction();
                   bodyTxt=action.open(fileDir);
                   body.setText(bodyTxt);


            }
            
            
            //--------------------------------------------------------------Save
            if(event.getSource()==save){
                

                JFileChooser fileS=new JFileChooser("Save");
                int file=fileS.showSaveDialog(null);

                FileWriter fileNew=null;

                if(file==fileS.APPROVE_OPTION){

                    //JOptionPane.showMessageDialog(null,fileS.getDialogTitle());
                    File fichero=fileS.getSelectedFile();
                     
                    try {
                         dir = fichero.getCanonicalPath()+".txt";//Retorna la ruta y el nombre del fichero
                         fileNew = new FileWriter(dir);
                         fileNew.write(body.getText());
                         BufferedWriter bufferFileWire=new BufferedWriter(fileNew);
                         bufferFileWire.write(body.getText());
                         bufferFileWire.close();//cierra el fichero
                         JOptionPane.showMessageDialog(null, "Save");
                         

                    } catch (IOException ex) {

                        JOptionPane.showMessageDialog(null, "Error To Save File");
                    }

                }

            }

            //----------------------------------------------------------New File
            if(event.getSource()==newFile){

                Main m=new Main();
                Toolkit.getDefaultToolkit().beep();
                Main.win=new Mainwin();
                

            }

            //-------------------------------------------------------------Print
            if(event.getSource()==print){
                try {
                    body.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Mainwin.class.getName()).log(Level.SEVERE, null, ex);
                }
                

            }
            //------------------------------------------------------------update
            if(event.getSource()==update){

                JOptionPane.showMessageDialog(null,"LPad 0.0.12.3 By Luis 2009-3285","Info",JOptionPane.INFORMATION_MESSAGE);

            }

            //------------------------------------------------------------delete
            if(event.getSource()==delete){

                int ans=JOptionPane.showConfirmDialog(null, "Seguro de Borrar Todo?");;
                if(ans==0){
                body.setText("");}
                
            }

            //--------------------------------------------------------------Help
            if(event.getSource()==helps){

                JOptionPane.showMessageDialog(null,"Para Mas Info Buscar En mi Web Personal Google.com.do","Info",JOptionPane.INFORMATION_MESSAGE);
            }

            if(event.getSource()==newFileT){

                Main m=new Main();
                Toolkit.getDefaultToolkit().beep();
                Main.win=new Mainwin();

            }
            if(event.getSource()==openT){

                JFileChooser fileC=new JFileChooser("Open");
                int fileA=fileC.showOpenDialog(null);

                if(fileA==JFileChooser.APPROVE_OPTION){

                    File dir=fileC.getSelectedFile();
                    fileDir=dir.getParent()+"\\"+dir.getName();
                   }
                   String bodyTxt;
                   action=new FileAction();
                   bodyTxt=action.open(fileDir);
                   body.setText(bodyTxt);

            }

            if(event.getSource()==saveT){

                JFileChooser fileS=new JFileChooser("Save");
                int file=fileS.showSaveDialog(null);

                FileWriter fileNew=null;

                if(file==fileS.APPROVE_OPTION){

                    //JOptionPane.showMessageDialog(null,fileS.getDialogTitle());
                    File fichero=fileS.getSelectedFile();

                    try {
                         dir = fichero.getCanonicalPath();//Retorna la ruta y el nombre del fichero
                         fileNew = new FileWriter(dir);
                         fileNew.write(body.getText());
                         BufferedWriter bufferFileWire=new BufferedWriter(fileNew);
                         bufferFileWire.write(body.getText());
                         bufferFileWire.close();//cierra el fichero
                         JOptionPane.showMessageDialog(null, "Save");


                    } catch (IOException ex) {

                        JOptionPane.showMessageDialog(null, "Error To Save File");
                    }

                }

            }

            if(event.getSource()==newFileT){

                Main m=new Main();
                Toolkit.getDefaultToolkit().beep();
                 

            }

            if(event.getSource()==helpsT){

                JOptionPane.showMessageDialog(null,"Para Mas Info Buscar En mi Web Personal Google.com.do","Info",JOptionPane.INFORMATION_MESSAGE);
           

            }

            if(event.getSource()==printT){

                try {
                    body.print();
                } catch (PrinterException ex) {
                    Logger.getLogger(Mainwin.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            //set Look and Feel
            if(event.getSource()==lookAndFeel){

                String set=new SetLookAndFeel().look();
                try {
                    UIManager.setLookAndFeel(set);
                    setSize(150,150);
                    repaint();
                    
                     
                    //repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "unknow Look and Feel");
                }


            }


       }

    }

}

    



