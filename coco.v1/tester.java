abstract class expr{};
class Constant extends expr{
public String v;
Constant(String v){
  this.v=v;
  }
}
class Variable extends expr{
public String name;
Variable(String name){
  this.name=name;
  }
}
class Mult extends expr{
public expr e1;
public expr e2;
Mult(expr e1, expr e2){
  this.e1=e1;
  this.e2=e2;
  }
}
class Add extends expr{
public expr e1;
public expr e2;
Add(expr e1, expr e2){
  this.e1=e1;
  this.e2=e2;
  }
}

