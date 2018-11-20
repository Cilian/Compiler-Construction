abstract class expr{};
class Constant extends expr{
  public String v;
  Constant(  String v){
    this.v=v;
  }
  public String toString(){
    return ""+v;
  }
}
class Bool extends expr{
  public String b;
  Bool(  String b){
    this.b=b;
  }
  public String toString(){
    return ""+b;
  }
}
class Variable extends expr{
  public String name;
  Variable(  String name){
    this.name=name;
  }
  public String toString(){
    return ""+name;
  }
}
class Pop extends expr{
  public expr e;
  Pop(  expr e){
    this.e=e;
  }
  public String toString(){
    return ""+e+"pop";
  }
}
class Minus extends expr{
  public expr e;
  Minus(  expr e){
    this.e=e;
  }
  public String toString(){
    return ""+e+"-";
  }
}
class Not extends expr{
  public expr e;
  Not(  expr e){
    this.e=e;
  }
  public String toString(){
    return ""+e+"!";
  }
}
class Abs extends expr{
  public expr e;
  Abs(  expr e){
    this.e=e;
  }
  public String toString(){
    return ""+e+"abs";
  }
}
class Add extends expr{
  public expr e1;
  public expr e2;
  Add(  expr e1, expr e2){
    this.e1=e1;
    this.e2=e2;
  }
  public String toString(){
    return ""+e1+e2+"+";
  }
}
class Mult extends expr{
  public expr e1;
  public expr e2;
  Mult(  expr e1, expr e2){
    this.e1=e1;
    this.e2=e2;
  }
  public String toString(){
    return ""+e1+e2+"*";
  }
}
class Div extends expr{
  public expr e1;
  public expr e2;
  Div(  expr e1, expr e2){
    this.e1=e1;
    this.e2=e2;
  }
  public String toString(){
    return ""+e1+e2+"/";
  }
}
class Pow extends expr{
  public expr e1;
  public expr e2;
  Pow(  expr e1, expr e2){
    this.e1=e1;
    this.e2=e2;
  }
  public String toString(){
    return ""+e1+e2+"^";
  }
}
class And extends expr{
  public expr e1;
  public expr e2;
  And(  expr e1, expr e2){
    this.e1=e1;
    this.e2=e2;
  }
  public String toString(){
    return ""+e1+e2+"&&";
  }
}
class Or extends expr{
  public expr e1;
  public expr e2;
  Or(  expr e1, expr e2){
    this.e1=e1;
    this.e2=e2;
  }
  public String toString(){
    return ""+e1+e2+"||";
  }
}
class Cond extends expr{
  public expr e1;
  public expr e2;
  public expr e3;
  Cond(  expr e1, expr e2, expr e3){
    this.e1=e1;
    this.e2=e2;
    this.e3=e3;
  }
  public String toString(){
    return ""+e1+e2+e3+"?";
  }
}
abstract class command{};
class Expr extends command{
  public expr e;
  Expr(  expr e){
    this.e=e;
  }
  public String toString(){
    return ""+e+"enter";
  }
}
class Store extends command{
  public expr e;
  public String name;
  Store(  expr e, String name){
    this.e=e;
    this.name=name;
  }
  public String toString(){
    return ""+e+"enter"+name+"store";
  }
}

