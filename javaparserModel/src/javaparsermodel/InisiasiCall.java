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
public class InisiasiCall {
    private String namaInisiatorClass;
    private String namaMethodCaller;
    
    public String getNamaClassInisiator(){
        return namaInisiatorClass;
    }
    public void setNamaClassInisiator(String namaInisiatorClass){
        this.namaInisiatorClass = namaInisiatorClass;
    }
    public String getNamaMethodCaller(){
        return namaMethodCaller;
    }
    public void setNamaMethodCaller(String namaMethodCaller){
        this.namaMethodCaller = namaMethodCaller;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(namaInisiatorClass).append(" ").append(namaMethodCaller);
        return stringBuilder.toString();
    }
}
