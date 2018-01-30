package ble.SyntaxAnalyzer;

/**
 *
 * @author Max
 */
public class FilesRegex {
    public static String getFilesRegex(){
        String checkeropen = "(Formatter\\s)?\\w+\\s*=\\s*new\\sFormatter\\s*\\(\\s*\"\\w:(\\\\\\w+)+.\\w+\\\"\\s*\\)";
        String checkerread = "(Scanner\\s)?\\w+\\s*=\\s*new\\sScanner\\s*\\(new\\sFile\\s*\\(\\s*\"\\w:(\\\\\\w+)+.\\w+\\\"\\s*\\)\\)";
        String checkerwrite = "\\w+\\.format\\(\\s*\".+\"\\s*(,\\s*\\w+(.\\w+(\\((\\w+)?\\))?)?)+\\s*\\)";
        String checkerclose = "\\w+\\.close\\(\\)";
           
        return "("+checkeropen+"|"+checkerread+"|"+checkerwrite+"|"+checkerclose+")";
    }
}
