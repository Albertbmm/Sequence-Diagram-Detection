package javaparsermodel.process;
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.ArrayList;
import javaparsermodel.Indent;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
//nama class,nama method(yang dipanggil method callnya),
//membuat data struktur sesuai dengan permintaan pak daniel:
//---PROGRESS-- nama class (?) , list dari nama method, nama pemanggilan method/method yang memanggil method lain (V) , 
//memasukkan nama ident ke arraylist ident(pada segment class) lalu mengambil arraylist yang pertama sebagai nama class 
//cara pertama memakai throw exception (X)
//cara kedua memakai stack untuk mendeteksi posisi 
public class CodeParser extends DefaultHandler{
    private SAXParser sp;
    private String identTemp;
    private Indent idt = new Indent();  
    ArrayList<Indent> isiIndent = new ArrayList<Indent>(); 
    private CharArrayWriter contents = new CharArrayWriter();
    boolean isiMethod = false; //untuk mencaari setiap method yang terdapat di dalam sebuah class 
    boolean dalamMethod = false; //untuk sebagai penanda  ident di dalam isiMethod
    boolean firstIdent = false; //sebagai penanda untuk mengambil nama method yang ada 
    boolean bident = false;
    boolean bdot = false; //untuk mencari apakah ada pemanggilan method di dalam sebuah class.
    boolean bclass = false; //untuk mencari nama class setiap method
    boolean bidentClass = false; 
    boolean biMethod = false; //untuk mencari ident yang terdapat di dalam sebuah method didalam method.
    boolean biNamaMethod = false; //nama method terdapat di dalam void_method_Decl
    boolean panggilMethod = false;
    boolean methodCall = false;
    public static void main(String[] args) throws IOException, SAXException, ParserConfigurationException{
        CodeParser cp = new CodeParser();
        cp.parseFile("src/xml/Lifeline2.xml");
                
    }
    
    public CodeParser() throws IOException, SAXException,ParserConfigurationException, org.xml.sax.SAXException{
        SAXParserFactory spfac = SAXParserFactory.newInstance();
        sp = spfac.newSAXParser();
        
    }
    
    public void parseFile(String namefile) throws SAXException, IOException{
         sp.parse(namefile, this);
         
    }
    public class MySAXTerminatorException extends SAXException{
        
    }
    
     public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException{
         if(qName.equalsIgnoreCase("CLASS"))
        {
           
            bclass = true;
         }
         
         if(bclass){
             //untuk mengambil nama dari setiap nama class
             //untuk nama class selalu terdapat diatas class_top_level_scope , cari cara untuk bisa mengambil diatas itu.
             if(qName.equalsIgnoreCase("IDENT")){
                  
                 bidentClass = true;
             }
             //System.out.println(qName);
         }
         if(qName.equalsIgnoreCase("METHOD_CALL")){
             panggilMethod = true;
             
         }
         if(panggilMethod){
             if(qName.equalsIgnoreCase("IDENT")){
                 methodCall = true;  
             }
          
         }
         
         if(qName.equalsIgnoreCase("VOID_METHOD_DECL")){
             isiMethod = true;
         }
         if(isiMethod){
             //System.out.println(qName);
             if(qName.equalsIgnoreCase("IDENT")){
                 
                 biNamaMethod = true;
                 //System.out.println(qName);
                 //throw new MySAXTerminatorException();
             }
             
         }
         
     }
     
     public void endElement(String uri, String localName, String qName)throws SAXException {
        //System.out.println("End element :"+qName);
       
        if(qName.equalsIgnoreCase("VOID_METHOD_DECL")){
            
            
            isiMethod = false;
            
            
        }
        if(qName.equalsIgnoreCase("Dot")){
            
            bdot = false;
            biMethod = false;
        }
        
        if(qName.equalsIgnoreCase("CLASS")){
            bclass = false;
            bidentClass = false;
        }
        if(qName.equalsIgnoreCase("METHOD_CALL")){
            panggilMethod = false;
            methodCall = false;
            //System.out.println(qName);
        }
     }

    public void characters( char[] ch, int start, int length )
                  throws SAXException {
       /*menyimpan nama class*/
        if(bidentClass){
           String isiClass = null;
           isiClass = new String(ch,start,length).toString().trim();
           System.out.println("Nama clas :" + isiClass);
           bidentClass = false;
       }
       
       /*untuk memanggil method di dalam class lain*/
       if(methodCall){
           String isiReturnMethod = null;
           isiReturnMethod = new String(ch,start,length);
           System.out.println("terdapat pemanggilan method : "+isiReturnMethod );
           methodCall = false;
       }
       
       /*if(bident){
        
        //isiIndent = new ArrayList<Indent>;
        String isi =null;
        isi = new String(ch, start, length);
        System.out.println("isiIdent : " + isi +"\n");
        //simpan inden ke arraylist indent ! 
        idt.setIndent(isi);
        isiIndent.add(idt);
        //System.out.println("berhasil masuk");
        bident = false;
      }*/
       if(biNamaMethod){
           
           biNamaMethod = false;
       }
   
    }
  public void readlist(){
      
  }
}
