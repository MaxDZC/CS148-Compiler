package ble.SyntaxAnalyzer;

public class Data<T> {
    private T value; 
    private String scope;

    public T getValue() {
            return value;
    }
    public void setValue(T value) {
            this.value = value;
    }
	
	public String getScope() {
            return scope;
    }
    public  void setScope(String scope) {
            this.scope = scope;
    }
	
}    