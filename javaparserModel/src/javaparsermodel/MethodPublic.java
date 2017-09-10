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
public class MethodPublic {
    private String modifier;
    private String tipeReturn;
    private String nameMethod;
    private String Argument;
    
    public String getNamaMethodReturn(){
        return nameMethod;
    }
    public void setNamaMethodReturn(String nameMethod){
        this.nameMethod = nameMethod;
    }
    public String getModifierReturn(){
        return modifier;
    }
    public void setModifierReturn(String modifier){
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
