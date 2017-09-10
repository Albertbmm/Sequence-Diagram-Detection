package javaparserModel.process;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import javaparserModel.Account;
import javaparsermodel.FragmentTemp;
import javaparsermodel.Lifelinetemp;
import javaparsermodel.message;
import javaparsermodel.fragment;
import javaparsermodel.triplet;
import javaparsermodel.MessageTriplet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * BUTUH DI EDIT
 * @author USER
 */
public class FileParser extends DefaultHandler{

       private Account acct;
       private message msgg;
       private String temp;
       private fragment fgmm;
       //private Lifelinetemp tempLifeline;
       private triplet triplet;
       FragmentTemp tempFragment = new FragmentTemp();
       Lifelinetemp tempLifeline = new Lifelinetemp(); 
       private MessageTriplet msgtriplet;
       private ArrayList<FragmentTemp> listFragmentTemp = new ArrayList<FragmentTemp>();
       private ArrayList<Lifelinetemp> listLifelineTemp = new ArrayList<Lifelinetemp>();
       private ArrayList<MessageTriplet> listmsgtriplet = new ArrayList<MessageTriplet>();
       private ArrayList<Account> accList = new ArrayList<Account>();
       private ArrayList<message> msg = new ArrayList<message>();
       private ArrayList<fragment> fgm = new ArrayList<fragment>();
       private ArrayList<triplet> trp = new ArrayList<triplet>();
       ArrayList<triplet> tripletku;
       //private ArrayList<triplet> trpSC = new ArrayList<triplet>();
       private SAXParser sp;

       
       
    public static void main(String[] args) throws IOException, SAXException,ParserConfigurationException, org.xml.sax.SAXException 
    {
              //Create an instance of this class; it defines all the handler methods
              FileParser fp = new FileParser();                         
              fp.parseFile("src/xml/contoh1.xmi");
              fp.creatinTriplet();
              fp.readList();
              
    }
    
    public FileParser() throws IOException, SAXException,ParserConfigurationException, org.xml.sax.SAXException  {
                SAXParserFactory spfac = SAXParserFactory.newInstance();
                //Now use the parser factory to create a SAXParser object
             sp = spfac.newSAXParser();
             
             tripletku = new ArrayList<triplet>();
             
    }
    
    public void parseFile(String filename) throws SAXException,IOException {
                      //Finally, tell the parser to parse the input and notify the handler        
        sp.parse(filename, this);               
    }
    
    
    public void characters(char[] buffer, int start, int length) {
              temp = new String(buffer, start, length);
    }
    
    public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException {
              temp = "";
              if (qName.equalsIgnoreCase("lifeline")) {
                     acct = new Account();
                     String packageName = attributes.getValue("name");
                     String packageType = attributes.getValue("xmi:type");
                     String PackageID = attributes.getValue("xmi:id");
                     acct.setType(packageType);
                     acct.setName(packageName);
                     acct.setId(PackageID);
                    
              }
              else if (qName.equalsIgnoreCase("message")) {
                     msgtriplet = new MessageTriplet();
                     String messageName = attributes.getValue("name");
                     String receiveEvent = attributes.getValue("receiveEvent");
                     String sendEvent = attributes.getValue("sendEvent");
                     msgtriplet.setMethod(messageName);
                     msgtriplet.setIdMessage(receiveEvent);
                     msgtriplet.setCoveredId(sendEvent);
                    
              }
               else if (qName.equalsIgnoreCase("fragment")) {
                    fgmm = new fragment();
                    String messageEvent = attributes.getValue("xmi:id");
                    String lifelineCovered = attributes.getValue("covered");
                    fgmm.setId(messageEvent);
                    fgmm.setCovered(lifelineCovered);
                    
              }
              
       }
     
    public void endElement(String uri, String localName, String qName)throws SAXException {

            if (qName.equalsIgnoreCase("lifeline")) {
                     // add it to the list
                     accList.add(acct);
              }
            else if (qName.equalsIgnoreCase("message")) {
                     // add it to the list
                     listmsgtriplet.add(msgtriplet);
                     msg.add(msgg);
                     
              }
             else if (qName.equalsIgnoreCase("fragment")) {
                     // add it to the list
                     fgm.add(fgmm);
              }
      
               
       }

    public void creatinTriplet(){
       int size = listmsgtriplet.size();
       for(int i= 0;i<size;i++)
       {
            triplet tpl = new triplet();  
            //tripletku.add(tpl); 
            
            String predikat = listmsgtriplet.get(i).getMethod().toString();
            String Obyek = getObyek(listmsgtriplet.get(i).getIdMessage().toString());//DONE
            String Subyek = getSubyek(listmsgtriplet.get(i).getCoveredId().toString());//DONE
            tpl.setObyek(Obyek);
            tpl.setPredikat(predikat);
            tpl.setSubyek(Subyek);
            tripletku.add(tpl);
      }   
            
   }
    public void readList() {
        System.out.println("triplet dibentuk : " + tripletku.size());
            for (int i=0;i<tripletku.size();i++) {
                System.out.println(tripletku.get(i).toString());
            }
    
    }
     private  String getSubyek(String subyek) {
        int size = accList.size(); 
        int sizefragment = fgm.size();
        String idFragment;
        String coveredId;
        int sizeMessageTriplet = listmsgtriplet.size();
            for(int y=0;y<sizeMessageTriplet;y++){
               for(int x=0;x<sizefragment;x++){
                idFragment = fgm.get(x).getId().toString();
                coveredId = fgm.get(x).getCovered().toString();
                tempFragment.setIdFragment(idFragment);
                tempFragment.setIdCovered(coveredId);
                listFragmentTemp.add(tempFragment);
                    for(int i=0;i<size;i++){
                      String idLifeline = accList.get(i).getId().toString();
                      String namaLifeline = accList.get(i).getName().toString();
                      tempLifeline.setId(idLifeline);
                      tempLifeline.setName(namaLifeline);
                      listLifelineTemp.add(tempLifeline);
                         if(subyek.equals(listFragmentTemp.get(x).getIdFragment().toString()))
                           {
                              if(listFragmentTemp.get(x).getIdCovered().toString().equals(listLifelineTemp.get(i).getId().toString()))
                                 {
                                   subyek = listLifelineTemp.get(i).getName().toString() ; 
                                     }
                                              
                            }
                                         
                    }     
            }
        }
        return subyek;    
    }
    private String getObyek(String obyek) {
        int size = accList.size(); 
        int sizefragment = fgm.size();
        int sizeMessageTriplet = listmsgtriplet.size();
        String idFragment;
        String coveredId;
        
        for(int x=0;x<sizeMessageTriplet;x++)
        {
           for(int y=0;y<sizefragment;y++){
             idFragment = fgm.get(y).getId().toString();
             coveredId = fgm.get(y).getCovered().toString();
             tempFragment.setIdFragment(idFragment);
             tempFragment.setIdCovered(coveredId);
             listFragmentTemp.add(tempFragment);               
                for(int i=0;i<size;i++){
                    String idLifeline = accList.get(i).getId().toString();
                    String namaLifeline = accList.get(i).getName().toString();
                    tempLifeline.setId(idLifeline);
                    tempLifeline.setName(namaLifeline);
                    listLifelineTemp.add(tempLifeline);
                        if(obyek.equals(listFragmentTemp.get(y).getIdFragment().toString())){
                            if(listFragmentTemp.get(y).getIdCovered().toString().equals(listLifelineTemp.get(i).getId().toString())){
                                obyek = listLifelineTemp.get(i).getName().toString();             
                            }
                          }
                }
          }
        }
        return obyek;    
    }
       
}

