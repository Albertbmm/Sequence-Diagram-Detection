/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javaparsermodel;

/**
 *
 * @author User
 * Untuk setiap pemanggilan method dan fungsi 
 * 
 */
public class Method {
    private String modifier;
    private String tipeReturn;
    private String nameMethod;
    private String Argument;
    
    public String getNamaMethod(){
        return nameMethod;
    }
    public void setNamaMethod(String nameMethod){
        this.nameMethod = nameMethod;
    }
    public String getModifier(){
        return modifier;
    }
    public void setModifier(String modifier){
        this.modifier = modifier;
    }
    public String getTypeReturn(){
        return tipeReturn;
    }
    public void setTypeReturn(String tipeReturn){
        this.tipeReturn = tipeReturn;
    }
    public String getArgumen(){
        return Argument;
    }
    public void setArgumen(String Argument){
        this.Argument = Argument;
    }
     @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(modifier).append(" ").append(tipeReturn).append(" ").append(nameMethod).append(" ").append(Argument);
        return stringBuilder.toString();
    }
}
