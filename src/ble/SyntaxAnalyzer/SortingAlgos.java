package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class SortingAlgos {
    public static String getSortAscending(){
        return "sortAsc\\((\\w[\\w\\d\\_]*)?\\)|sortAsc\\(\\w[\\w\\d\\_]*\\)";
    }          
    public static String getSortDescending(){
        return "sortDesc\\((\\w[\\w\\d\\_]*)?\\)|sortDesc\\(\\w[\\w\\d\\_]*\\)";
    }
}
