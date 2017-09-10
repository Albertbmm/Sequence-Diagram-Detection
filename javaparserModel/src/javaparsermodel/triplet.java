/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaparsermodel;

/**
 *
 * @author meisyal
 */
public class triplet {
    private String subyek;
    private String predikat;
    private String obyek;

    public String getSubyek() {
        return subyek;
    }

    public void setSubyek(String value) {
        this.subyek = value;
    }

    public String getObyek() {
        return obyek;
    }

    public void setObyek(String value) {
        this.obyek = value;
    }

    public String getPredikat() {
        return predikat;
    }

    public void setPredikat(String value) {
        this.predikat = value;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("[").append(subyek).append(", ");
        stringBuilder.append(predikat).append(", ").append(obyek);
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}
