/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparserModel;

/**
 *
 * @author USER
 */
public class Account {
     private String name;
       private String message;
       private String messageSort;
       private String type;
       private String id;

       public Account() {
       }

       public Account(String name, String message, String messageSort, String type,String id) {
              this.name = name;
              this.type = type;
              this.message = message;
              this.messageSort = messageSort;
              this.id = id;
       }
       public String getId(){
           return id;
       }
       public void setId(String id){
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
       public String getName() {
              return name;
       }

       public void setName(String name) {
              this.name = name;
       }

       public String getType() {
              return type;
       }

       public void setType(String type) {
              this.type = type;
       }
       
       public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[").append(name).append(", ");
        stringBuilder.append(type).append(", ").append(id);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
       /*public String toString() {
              StringBuffer sb = new StringBuffer();
              sb.append("Atribute element Lifeline - ");
              sb.append("Name:" + getName());
              sb.append(", ");
              sb.append("Type:" + getType());
              sb.append(", ");
              sb.append("ID:" + getId());
         

              return sb.toString();
       }*/
    
}
