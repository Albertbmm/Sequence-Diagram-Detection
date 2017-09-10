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
public class PemanggilanMethod {
    private String variablePemanggil;
    private String methodDipanggil;
    
    public String getVariablePemanggil(){
        return variablePemanggil;
    }
    public void setVariablePemanggil(String variablePemanggil){
        this.variablePemanggil = variablePemanggil;
    }
    public String getMethodDipanggil(){
        return methodDipanggil;
    }
    public void setMethodDipanggil(String methodDipanggil){
        this.methodDipanggil = methodDipanggil;
    }
     @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(variablePemanggil).append(".").append(methodDipanggil);
        return stringBuilder.toString();
    }
    
}
