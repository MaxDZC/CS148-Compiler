package ble.Network.Storage;

public class FileDirectory {
    
    protected String path = null;
    
    final protected String extension = "ble";
    
    public FileDirectory() {
        
    }
    
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public String getExtension() {
        return this.extension;
    }
    
    public String getIndexFilePath() {
        return this.path + "index." + this.extension;
    }
}