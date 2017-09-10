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
public class InisiatorCall {
    private String namaClassCaller;
    private String namaInisiasi;
    public String getNamaClassCaller(){
        return namaClassCaller;
    }
    public void setNamaClassCaller(String namaClassCaller){
        this.namaClassCaller = namaClassCaller;
    }
    public String getNamaInisiasi(){
        return namaInisiasi;
    }
    public void setNamaInisiasi(String namaInisiasi){
        this.namaInisiasi = namaInisiasi;
    }
     @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(namaInisiasi);
        return stringBuilder.toString();
    }
}
