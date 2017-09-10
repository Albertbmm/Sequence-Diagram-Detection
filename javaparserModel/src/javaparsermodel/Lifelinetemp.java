/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparsermodel;

/**
 *
 * @author User
 */
public class Lifelinetemp {
       private String idLifeline;
       private String nameLifeline;
       
       public Lifelinetemp() {
       }

       public Lifelinetemp(String idLifeline, String namaLifeline) {
              this.idLifeline = idLifeline;
              this.nameLifeline = nameLifeline;
       }
       public String getId(){
           return idLifeline;
       }
       public void setId(String idLifeline){
           this.idLifeline = idLifeline;
       }
       public String getName(){
           return nameLifeline;
       }
       public void setName(String nameLifeline){
           this.nameLifeline = nameLifeline;
       }
       
       
       public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[").append(nameLifeline).append(", ");
        stringBuilder.append(", ").append(idLifeline);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
      
}
