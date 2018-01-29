/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import Arrays.ArrayLexicon;
/**
 *
 * @author Cadigal, Dimpas, Gelbolingo, Gimenez, Po
 */
public class compiler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayLexicon lx = new ArrayLexicon();
        
        lx.readSource("source.ble");
    }
}
