import java.util.HashMap;
import java.util.Map.Entry;
import java.util.List;
import java.util.ArrayList;

class faux{ // collection of non-OO auxiliary functions (currently just error)
    public static void error(String msg){
	System.err.println("Interpreter error: "+msg);
	System.exit(-1);
    }
}

abstract class AST{
    String compile(){
        return "";
    }
    void check(){
    }
}

class Start extends AST{
    public List<TokenDef> tokendefs;
    public List<DataTypeDef> datatypedefs;
    Start(List<TokenDef> tokendefs, List<DataTypeDef> datatypedefs){
	this.tokendefs=tokendefs;
	this.datatypedefs=datatypedefs;
    }

    @Override
    void check() {
        for(DataTypeDef data : datatypedefs){
            data.check();
          }
    }

    @Override 
    String compile(){
        String result = "";
        for(DataTypeDef data : datatypedefs){
          result +=  data.compile();
        }
    return result;
    }
}

class TokenDef extends AST{
    public String tokenname;
    public String ANTLRCODE;
    TokenDef(String tokenname, String ANTLRCODE){
	this.tokenname=tokenname;
	this.ANTLRCODE=ANTLRCODE;
    }
}

class DataTypeDef extends AST{
    public String dataTypeName;
    public List<Alternative> alternatives;
    DataTypeDef(String dataTypeName, List<Alternative> alternatives){
	this.dataTypeName=dataTypeName;
    this.alternatives=alternatives;
    }

    @Override
    void check() {      
        for(Alternative alt : alternatives){    
            alt.check();
        }
    }

    @Override 
    String compile(){
        String result = "";
        result += "abstract class " + dataTypeName + "{};\n"; 
       for(Alternative alt : alternatives){
            result += "class " + alt.constructor + " extends " + dataTypeName + "{\n";

            result +=  alt.compile();
           }
        return result;
    }
}

class Alternative extends AST{
    public String constructor;
    public List<Argument> arguments;
    public List<Token> tokens;
    Alternative(String constructor, List<Argument> arguments,  List<Token> tokens){
	this.constructor=constructor;
	this.arguments=arguments;
	this.tokens=tokens;
    }
    @Override
    void check() {

        for(int i = 0; i < tokens.size()-1; i++){
            for(int j = i+1; j < tokens.size()-1; j++){
                if(tokens.get(i).toString().equals(tokens.get(j).toString())){
                    faux.error("Duplicate token in " + constructor + ": " + tokens.toString());
                }
            }
        }
        for(Argument arg : arguments){
            if(arg.name.equals(arg.type)){
                faux.error("Can't use type as name. " + "type: " +  arg.type + " name: " +  arg.name);
            } 
            boolean checker = false;
            for(Token tok : tokens){
                if (tok.getClass().getName().equals("Nonterminal")){
                    if(tok.toString().equals(arg.name)){
                        checker = true;
                        break;
                    }
                }
            }
            if(checker == false){                
                faux.error("Token doesn't match with argument: " + arg.name + " in expression: " +  constructor);
        }

        }
        // check if the arguments are in there exactly once
        for (int i = 0; i < arguments.size()-1; i++) {
            String name = arguments.get(i).name;
            for (int j = i+1; i < arguments.size()-1; i++) {
                if (arguments.get(j).name.equals(name)){
                    faux.error("Duplicate variable name: " + name);
                }
            }
        }
    }

    @Override
    String compile() {
        String result = "";
        for(Argument arg : arguments){
            result += "    public " + arg.compile() + ";\n";
         }
         result += "    "+constructor + "(";
 
        if(arguments.size() > 1){
            for(int i = 0; i < arguments.size() - 1; i++){
                result += arguments.get(i).compile() + ", ";
                }
            } 

            result += arguments.get(arguments.size()-1).compile();
            result += "){\n";

            for(Argument arg : arguments){
                result += "        this." + arg.name + "=" + arg.name + ";\n";
             }
             
             result += "    }\n    ";
             result += "public String toString(){\n    ";
             result += "    return \"\" ";
             for(Token tok : tokens){
                result += "+";
                result += tok.toString();
            }
            result += ";";
            result += "\n    }";
            result += "\n}\n";

        return result;
    }
}

class Argument extends AST{
    public String type;
    public String name;
    Argument(String type, String name){this.type=type; this.name=name;}
    @Override 
    String compile(){
        String actType = "";
        if(!type.equals("expr")) {
            actType = "String";
        }   else actType = "expr";
        
        String result = "";
        result += actType + " " + name ; 
        return result;
    }
}

abstract class Token extends AST{
    public String toString(){
        return "";
    }
}

class Nonterminal extends Token{
    public String name;
    Nonterminal(String name){this.name=name;}
    public String toString(){
        return "" + name;
    }
}

class Terminal extends Token{
    public String token;
    Terminal(String token){this.token=token;}
    public String toString(){
        token = token.replace("\'", "\"");
        return "" + token;
    }
}