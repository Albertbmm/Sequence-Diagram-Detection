/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparsermodel;

      
 
public class fragment {
        private String id;
       private String covered;
       
    public fragment() {
        
    }
    public fragment(String id, String covered){
            this.id = id;
            this.covered = covered;
        }
       
       public String getId(){
           return id;
       }
       public void setId(String id){
           this.id = id;
       }
       public String getCovered(){
           return covered;
       }
       public void setCovered(String covered){
           this.covered = covered;
       }
      public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[");
        stringBuilder.append(id).append(", ").append(covered);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
       /* public String toString() {
              StringBuffer sb = new StringBuffer();
              sb.append("Atribute element Fragment ");
              sb.append("ID dari event:" + getId());
              sb.append(", ");
              sb.append("Lifeline:" + getCovered());
              return sb.toString();
       }*/
}
