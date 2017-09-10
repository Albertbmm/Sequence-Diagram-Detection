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
public class NamaClass {
    private String namaClass;
    private String modifierClass;
    private String typeReturnClass;
    
    public void setNamaClass(String namaClass){
        this.namaClass = namaClass;
    }
    
    public String getNamaClass(){
        return namaClass;
    }
    
    public void setModifierClass(String modifierClass){
        this.modifierClass = modifierClass;
    }
    public String getModifierClass(){
        return modifierClass;
    }
    
    public void setTypeReturnClass(String typeReturnClass){
        this.typeReturnClass = typeReturnClass;
    }
    public String getTypeReturnClass(){
        return typeReturnClass;
    }
     @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(modifierClass).append(" ").append(typeReturnClass).append(" ").append(namaClass);
        return stringBuilder.toString();
    }
}
