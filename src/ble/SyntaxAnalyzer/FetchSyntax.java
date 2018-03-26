/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ble.SyntaxAnalyzer;

/**
 *
 * @author Sean Cadigal
 */
public class FetchSyntax {
    public static String getFetchPattern(){
        return "fetch\\(\\s*([\"'])(get|post)\\1\\s*,\\s*\\1.*\\1\\s*\\)";
    }
}
