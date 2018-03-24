package ble.SyntaxAnalyzer;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxAnalyzer {
    /* Syntax */
    public final static String DISPLAY = "\\s*display[(]\\s*((\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*)(\\s*[+]\\s*(\".+\"|\'.+\'|\\d+(\\.\\d+)?|[a-zA-Z]\\w*))*)?\\s*[)]\\s*";
    public final static String COOKIES = "(setCookie\\(\\s*\"\\w+\"\\s*,\\s*\\d+\\)|getCookie\\(\\s*\"\\w+\"\\s*\\))";
    private final static String SESSIONS = "(setSession\\(\\s*\"\\w+\"\\s*,\\s*\\d+\\)|getSession\\(\\s*\"\\w+\"\\s*\\))";
    private final static String FETCH = "fetch\\(\\s*\"(?i)(get|post)\"\\s*,\\s*\"\\w+\"\\s*\\)";
    public final static String DATATYPES = DataTypes.getSyntax();
    public final static String VALIDATION = Validation.getValidation();
    private final static String FUNCTIONS = Functions.getFunctionRegex();
    public final static String ARRAYS = Arrays.getArrayPattern();
    public final static String CONDITIONAL = Conditions.getConditionPattern();
    public final static String QUEUE_STACK = Queue_stack.getQueue() + "|" + Queue_stack.getStack();
    private final static String SORTING = SortingAlgos.getSortAscending() + "|" + SortingAlgos.getSortDescending();
    private final static String CUSTOM_DT = CustomDatatypes.getCustomDataTypesRegex();
    private final static String NUMBERS = Numbers.getBinary() + "|" + Numbers.getDecimal() + "|" + Numbers.getHexadecimal() + "|" + Numbers.getOctal();
    private final static String TRIGO = TrigoFunctions.getTrigoFunctions();
    private final static String FILES = FilesRegex.getFilesRegex();
    public final static String TIME_LIB = TimeLibrary.getTimeSyntax();
    public final static String DB = DatabaseRegex.getDatabaseRegex();
    
    private final static String BODY = "("+COOKIES+"|"+SESSIONS+"|"+FETCH+"|"+DISPLAY+"|"+DATATYPES+"|"+VALIDATION+
            "|"+FUNCTIONS+"|"+ARRAYS+"|"+CONDITIONAL+"|"+QUEUE_STACK+"|"+SORTING+"|"+CUSTOM_DT+
            "|"+NUMBERS+"|"+TRIGO+"|"+FILES+"|"+TIME_LIB+"|"+DB+"|\\s)*";
    
    private final static String RELATIONAL = "(==|>=|<=|<|!=|>|&&|^|\\|\\|)";
    public final static String CONDITION = "(\\s*(\\w+|\\d+)\\s*"+RELATIONAL+"\\s*(\\w+||\\d+)\\s*)";
    
    public final static String REPEAT = "repeat\\(( *\\d+ *|("+CONDITION+")+)\\)";
    public final static String FORLOOP = "for\\s*\\(\\s*\\w+\\sin\\s\\w+\\s*\\)";
    private final static String FUNCTION = "("+REPEAT+"|"+FORLOOP+")";

    private final static String SCOPE = BODY+"("+FUNCTION+"(\\s*\\n\\s{4}"+BODY+"|\\s)*)*"+BODY;
    private final static String CODE = SCOPE;
    private static String bleCode;
   
    public boolean analyze(String bleCode) throws IOException {
        bleCode = Comments.comments(bleCode);
        
        Pattern pattern;
        Matcher matcher;
        
        pattern = Pattern.compile(CODE);
        matcher = pattern.matcher(bleCode);
        
        return matcher.matches();
    }
    
}