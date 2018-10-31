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
}


class Start extends AST{
    public List<TokenDef> tokendefs;
    public List<DataTypeDef> datatypedefs;
    Start(List<TokenDef> tokendefs, List<DataTypeDef> datatypedefs){
	this.tokendefs=tokendefs;
	this.datatypedefs=datatypedefs;
    }
    @Override 
    String compile(){
        String result = "";
        for(DataTypeDef data : datatypedefs){
           result += "abstract class " + data.dataTypeName + "{};" + "\n";
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
    @Override 
    String compile(){
        String result = "Tester";
        return result;
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
    String compile(){
        String result ="";
        for(Alternative alt : alternatives){
            result = "class";
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
    String compile() {
        String result = "";
        for(Argument arg : arguments){
            result = "class " +  arg.name + " extends" + "\n"; 
         }
        return result;
    }
}

class Argument extends AST{
    public String type;
    public String name;
    Argument(String type, String name){this.type=type; this.name=name;}
    @Override 
    String compile(){
        String result = "Tester";
        return result;
    }
}

abstract class Token extends AST{}

class Nonterminal extends Token{
    public String name;
    Nonterminal(String name){this.name=name;}
}

class Terminal extends Token{
    public String token;
    Terminal(String token){this.token=token;}
}

