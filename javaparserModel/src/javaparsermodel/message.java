/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparsermodel;


/**
 *
 * @author USER
 */
public class message  {

   
       private String message;
       private String messageSort;
       private String id;
 

       

    public message() {
        
    }
    
       public message(String message, String messageSort,String id) {
              
              this.message = message;
              this.messageSort = messageSort;
              this.id = id;
       }
       public String getID(){
           return id;
       }
       public void setID(String id){
           this.id = id;
       }
       public String getMessage(){
           return message;
       }
       public void setMessage(String message){
           this.message = message;
       }
       public String getMessageSort(){
           return messageSort;
       }
       public void setMessageSort(String messageSort){
           this.messageSort = messageSort;
       }
       
       public String toString() {
              StringBuffer sb = new StringBuffer();
              sb.append("Atribute element Message - ");
              sb.append("pesan:" + getMessage());
              sb.append(", ");
              sb.append("Tipe pesan:" + getMessageSort());
              sb.append(". ");
              sb.append("Id dari message: " + getID());
              return sb.toString();
       }
    
}
