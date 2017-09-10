/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparsermodel.process;

/**
 * progress : 
 * merubah dari vector ke arraylist (x)
 * membuat / menjadikan isi dari arraylist menjadi yang di req oleh pak daniel.
 * cara mendeteksi retur dari sebuah tipe return (func,void or string return something)
 * 
 * (1) setiap penemuan tag (pada setiap element) dimasukkan ke dalam arraylist, lalu di print sesuai dengan iterasinya.
 * (2) mengecek penemua tag return.
 * (3) menggunakan arraylist untuk bisa mengambil setiap tag lalu di keluarkan sesuai dengan iterasi.
 * (4) di iterasi , untuk tipe void / public nya , di cek setiap array 1 nya void atau bukan void
 */
import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import java.util.*;
import javaparsermodel.InisiasiCall;
import javaparsermodel.InisiatorCall;
import javaparsermodel.Method;
import javaparsermodel.MethodPublic;
import javaparsermodel.NamaClass;
import javaparsermodel.PemanggilanMethod;

public class CodeParserStack extends DefaultHandler {
    
   private Stack tagStack = new Stack();
   // Local list of Method call...
   private ArrayList<Method> methodCode= new ArrayList<Method>();
   private ArrayList<InisiasiCall> initCode = new ArrayList<InisiasiCall>();
   private ArrayList<InisiatorCall>inisiasiCode = new ArrayList<InisiatorCall>();
   private ArrayList<NamaClass> namaClassCode = new ArrayList<NamaClass>();
   private ArrayList<MethodPublic> methodPublicCode = new ArrayList<MethodPublic>();
   private ArrayList<PemanggilanMethod> panggilMethod = new ArrayList<PemanggilanMethod>();
   
   //Declare Variable untuk NamaClass Data Model
   private String isiModifierClass;
   private String isiTipeClass;
   
   //Declare Variabel untuk Method Data Model
   //untuk func yang return
   String typePublicTemp;
   String methodPublicTemp;//modifier
   String namaMethodValue; //untuk yang sifat nya function return
    //untuk yang sifatnya function void
   
   //untuk void
   String namaMethodVoid;
   String tipeVoid;
   //String methodVoidTemp; //apabila modifier nya public void
   String modifierVoid;//untuk modifier void
   
   //String untuk pemanggilan method dalam class lain 
   String namaMethodCall;
   
   //pemanggilan method nya
   String isiReturn;
   String inisiatorClass;
   private Method methodCodedata;
   private InisiasiCall initCodedata;
   private InisiatorCall inisiasiCodedata;
   private NamaClass namaClassData;
   private MethodPublic methodPublicCodeData;
   private PemanggilanMethod panggilMethodData;
   private CharArrayWriter contents = new CharArrayWriter(); //untuk mengambil char dari xml
   
   public void startElement( String namespaceURI,
               String localName,
              String qName,
              Attributes attr ) throws SAXException {
     
      namaClassData = new NamaClass();
      methodCodedata = new Method();
      methodPublicCodeData = new MethodPublic();
      initCodedata = new InisiasiCall();
      inisiasiCodedata = new InisiatorCall(); //untuk mengambil line yang pemanggilan method dalam class lain
      
       contents.reset();
      // push the tag name onto the tag stack...
      tagStack.push( localName );
      
      // display the current path that has been found...
     // System.out.println( "path found: [" + getTagPath() + "]" );
   }
   public void endElement( String namespaceURI,
               String localName,
              String qName ) throws SAXException {
       

       if ( getTagPath().equals( "/unit/CLASS/IDENT" ) ) {
         String isiNamaClass = contents.toString().trim();
         namaClassData.setNamaClass(isiNamaClass);
         namaClassData.setModifierClass(isiModifierClass);
         namaClassData.setTypeReturnClass("class");
         namaClassCode.add(namaClassData);
      }
      
       if(getTagPath().equals("/unit/CLASS/MODIFIER_LIST/PUBLIC")){
          //modifier untuk class
          isiModifierClass = contents.toString().trim();
          
      }
       
      //FUNCTION THAT HAS RETURN VALUE
      
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/MODIFIER_LIST/PUBLIC")){
          methodPublicTemp = contents.toString().trim();
      }
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/TYPE/QUALIFIED_TYPE_IDENT/IDENT")){
          //untuk tipe return value 
          typePublicTemp = contents.toString().trim();
      }
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/IDENT")){
          namaMethodValue = contents.toString().trim();
      } 
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/FORMAL_PARAM_LIST/IDENT")){
          //untuk argumen parameter nya 
         
      }
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/FUNCTION_METHOD_DECL/BLOCK_SCOPE/RETURN")){
          //untuk return value nya
          
          //Set value ke data model
          methodCodedata.setModifier(methodPublicTemp);
          methodCodedata.setTypeReturn(typePublicTemp);
          methodCodedata.setNamaMethod(namaMethodValue);
          //methodCodedata.setArgumen(qName);
//          methodPublicCodeData.setModifierReturn(methodPublicTemp);
//          methodPublicCodeData.setTypeReturn(typePublicTemp);
//          methodPublicCodeData.setNamaMethodReturn(namaMethodValue);
//          methodPublicCode.add(methodPublicCodeData);
          methodCode.add(methodCodedata);
      }
      
      //FUNGSI VOID
      
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/MODIFIER_LIST/PUBLIC")){
          modifierVoid = contents.toString().trim();
      }
        
     if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/IDENT")){
          tipeVoid ="void";
          namaMethodVoid = contents.toString().trim();
          methodCodedata.setNamaMethod(namaMethodVoid);
          if(modifierVoid!=null){
              methodCodedata.setModifier(modifierVoid);
          }
          methodCodedata.setTypeReturn(tipeVoid);
          methodCode.add(methodCodedata);
      }
     
     //VOID-PEMANGGILAN CLASS LAIN DALAM VOID
     //mengisi set ke data model . 
     //declare Variabel ke global
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/BLOCK_SCOPE/VAR_DECLARATION/TYPE/QUALIFIED_TYPE_IDENT/IDENT")){
         //Deklarasi untuk mendapatkan inisator dari code caller , contoh : Lifeline2 dua = (lifeline2 itu ini)
         //initCodedata = new InisiasiCall(); //untuk yang inisiasi class lain
         inisiatorClass = contents.toString().trim();
         //initCodedata.setNamaClassInisiator(inisiatorClass);
         //initCode.add(initCodedata);
         
         
      }
      
      if(getTagPath().equals("/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/BLOCK_SCOPE/VAR_DECLARATION/VAR_DECLARATOR_LIST/VAR_DECLARATOR/IDENT")){
        //ini isiya adalah deklarasi untuk memanggil method di dalam class lain (didalam contoh : 'dua')
        
        namaMethodCall = contents.toString().trim();
        initCodedata.setNamaMethodCaller(namaMethodCall);
        initCodedata.setNamaClassInisiator(inisiatorClass);
        initCode.add(initCodedata);
       
      }
         
      if ( getTagPath().equals( "/unit/CLASS/CLASS_TOP_LEVEL_SCOPE/VOID_METHOD_DECL/BLOCK_SCOPE/EXPR/METHOD_CALL/DOT/IDENT" ) ) {
         //memanggil call method lain
         
          isiReturn =contents.toString().trim();
         inisiasiCodedata.setNamaInisiasi(isiReturn);
         inisiasiCode.add(inisiasiCodedata);
      }
     
      // clean up the stack...
      tagStack.pop();
   }
   public void characters( char[] ch, int start, int length )
                  throws SAXException {
      // accumulate the contents into a buffer.
      contents.write( ch, start, length );
   }

   
   private String getTagPath( ){
      //  build the path string...
      String buffer = "";
      Enumeration e = tagStack.elements();
      while( e.hasMoreElements()){
               buffer  = buffer + "/" + (String) e.nextElement();        
      }
      return buffer;
   }
//   private String getTagPathClass( ){
//      //  build the path string...
//      String buffer = "";
//      Enumeration e = tagStack.elements();
//      while( e.hasMoreElements()){
//               buffer  = buffer + "/" + (String) e.nextElement();        
//               if(buffer.equalsIgnoreCase("class")){
//                   System.out.println("ada");
//                   break;
//               }
//      }
//      return buffer;
//   }
   public void getClassMethodCaller(){
       panggilMethodData = new PemanggilanMethod();
       int sizeInitCode = initCode.size();
       int sizeInisiasiCode = inisiasiCode.size();
       for(int i=0;i<sizeInitCode;i++){
           for(int y=0;y<sizeInisiasiCode;y++)
           {
               String namaMethodCallerTemp;
               String datanya = inisiasiCode.get(y).getNamaInisiasi().toString();
               String dataTemp;
               String dataMethod;
               namaMethodCallerTemp = initCode.get(i).getNamaMethodCaller().toString();
               if(datanya.equals(namaMethodCallerTemp)){
                   dataTemp = initCode.get(i).getNamaClassInisiator();
                   //System.out.println(dataTemp);
                   panggilMethodData.setVariablePemanggil(dataTemp);
                   //masukkan ke dalam arraylist yang baru untuk setting nya
               }
               if(!datanya.equals(namaMethodCallerTemp)){
                   //System.out.println(datanya); //masukkan ke dalam arraylist
                   panggilMethodData.setMethodDipanggil(datanya);
                   panggilMethod.add(panggilMethodData);
               }
           }
       }
   }
   public void readList(){
       //proses pembacaan disini
       for(int i=0;i<namaClassCode.size();i++){
           System.out.println(namaClassCode.get(i).toString());
       }
       for(int i=0;i<methodCode.size();i++){
           System.out.println(methodCode.get(i).toString());
       }
       
       
       System.out.println();
       for(int i=0;i<panggilMethod.size();i++){
           System.out.println(panggilMethod.get(i).toString());
       }
       
//       for(int i=0;i<initCode.size();i++){
//           System.out.println(initCode.get(i).toString());
//       }
//       
//       System.out.println();
//       for(int i=0;i<inisiasiCode.size();i++){
//           System.out.println(inisiasiCode.get(i).toString());
//       }
    
       
   }
   
   public static void main( String[] argv ){
      try {
          
         // Create SAX 2 parser...
         XMLReader xr = XMLReaderFactory.createXMLReader();
         // Set the ContentHandler...
         CodeParserStack ex1 = new CodeParserStack();
         xr.setContentHandler( ex1 );
         // Parse the file...
         xr.parse( new InputSource(
               new FileReader( "src/xml/menuUI.xml" )) );
        ex1.getClassMethodCaller();
        System.out.println();
        ex1.readList();
         
      }catch ( Exception e )  {
         e.printStackTrace();
      }
   }
}