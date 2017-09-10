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
public class FragmentTemp {
       private String idFragment;
       private String idCovered;
       
       public FragmentTemp() {
       }

       public FragmentTemp(String idFragment, String idCovered) {
              this.idFragment = idFragment;
              this.idCovered = idCovered;
       }
       public String getIdFragment(){
           return idFragment;
       }
       public void setIdFragment(String idFragment){
           this.idFragment = idFragment;
       }
       public String getIdCovered(){
           return idCovered;
       }
       public void setIdCovered(String idCovered){
           this.idCovered = idCovered;
       }
       
       
       public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[").append(idFragment).append(", ");
        stringBuilder.append(", ").append(idCovered);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
      
}
