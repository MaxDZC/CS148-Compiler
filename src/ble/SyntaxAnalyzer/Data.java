package ble.SyntaxAnalyzer;

public class Data<T> {

    private T value; 
    public static int currScope = 0;
    private int varScope; 
    
    
    public int getScope() 
    {
        return varScope;
    }

    public T getValue() {
	return value;
    }

    public void setValue(T value) 
    {
        // value is "" if let va
	this.value = value; 
        this.varScope = this.currScope; 
    }
        
    // ??? 
    public static void outVarUndeclared(String varName){
        System.out.println("Varible " + varName + " does not exist.");
    }
    

}  
