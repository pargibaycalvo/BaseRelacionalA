/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baserelacionala;

/**
 *
 * @author oracle
 */
public class metodos {
    String CODIGO;
    String DESCRICION;
    int PREZO;

    public metodos() {
    }

    public metodos(String CODIGO, String DESCRICION, int PREZO) {
        this.CODIGO = CODIGO;
        this.DESCRICION = DESCRICION;
        this.PREZO = PREZO;
    }

    public String getCODIGO() {
        return CODIGO;
    }

    public void setCODIGO(String CODIGO) {
        this.CODIGO = CODIGO;
    }

    public String getDESCRICION() {
        return DESCRICION;
    }

    public void setDESCRICION(String DESCRICION) {
        this.DESCRICION = DESCRICION;
    }

    public int getPREZO() {
        return PREZO;
    }

    public void setPREZO(int PREZO) {
        this.PREZO = PREZO;
    }

    @Override
    public String toString() {
        return "metodos{" + "CODIGO=" + CODIGO + ", DESCRICION=" + DESCRICION + ", PREZO=" + PREZO + '}';
    }
    
    
}
