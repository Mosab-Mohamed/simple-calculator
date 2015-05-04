package Calculator;

public class DoOperations extends Operations {

	private double num1 , num2 ;
	
	public double add(double n1,double n2){
		this.num1 = n1 ;
		this.num2 = n2 ;
		return num1 + num2 ;
	}
	
	public double subtraction(double n1,double n2){
		this.num1 = n1 ;
		this.num2 = n2 ;
		return num1 - num2 ;
	}
	
	public double multiplication(double n1,double n2){
		this.num1 = n1 ;
		this.num2 = n2 ;
		return num1 * num2 ;
	}
	
	public double division(double n1,double n2){
		this.num1 = n1 ;
		this.num2 = n2 ;
		return num1 / num2 ;
	}

}
