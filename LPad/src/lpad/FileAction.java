package lpad;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


public class FileAction {

    Mainwin event;
     
    public String open(String dirFile){

        
        String ln="";
        String bodyText="";
        

        File fileDir=new File(dirFile);

        FileInputStream textAreaBody = null;

        try{

            textAreaBody=new FileInputStream(fileDir);

        }catch(Exception e){
        }

        DataInputStream str=new DataInputStream(textAreaBody);
        BufferedReader readFile=new BufferedReader(new InputStreamReader(str));

        try{

           while((ln=readFile.readLine())!=null){

               bodyText+=ln;

           } 

        }catch(Exception ex){}

        try{

            readFile.close();

        }catch(Exception e){}

        

        return bodyText;

    }

     
    

}
