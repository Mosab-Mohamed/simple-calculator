package Calculator;

import java.util.*;
import java.io.*;

public class Calc implements Calculator {

	private String currentPostfix ;
	private Stack<String> formulas ;
	private int current ;

	public Calc(){
		formulas = new Stack<String>();
		current = -1 ;
	}
	
	
	public void Input(String s){
		formulas.addElement( s ) ;
		current = stackLength(formulas);
	}
	
	private boolean isValid(String s){
		if(s.length()==0)
			return false ;
		
		char[] exp = s.toCharArray();
		if(exp[0]=='*' || exp[0]=='/' || exp[0]=='.')
			return false;
		
		if(exp[s.length()-1]=='+' || exp[s.length()-1]=='-' || exp[s.length()-1]=='*' || exp[s.length()-1]=='/' || exp[s.length()-1]=='.')
			return false ;
		
		if( exp[0]== '+' || exp[0]=='-' )
			if(exp[1]=='+' || exp[1]=='-' || exp[1]=='*' || exp[1]=='/' || exp[1]=='.')
				return false ;
		
		for(int i=0 ; i<s.length() ; i++){
			if(! ( (exp[i]<=57 && exp[i]>=48) || exp[i]=='-' || exp[i]=='+' || exp[i]=='/' || exp[i]=='*' || exp[i]=='.' ) )
				return false ;
		}
		
		for(int i=1 ; i<s.length()-1 ; i++){
			
			if(exp[i]=='-' || exp[i]=='+'){
				if( (exp[i-1]=='-' || exp[i-1]=='*' || exp[i-1]=='/' || exp[i-1]=='+') && (exp[i+1]=='-' || exp[i+1]=='*' || exp[i+1]=='/' || exp[i+1]=='+') )
					return false ;
				
				else if( exp[i+1]=='/' || exp[i+1]=='*' )
					return false ;
			}
			
			else if( exp[i]=='*' || exp[i]=='/' ){
				if( exp[i+1]=='*' || exp[i+1]=='/' )
					return false ;
			}
			
			else if(exp[i]=='.'){
				if( !(exp[i+1]>='0' && exp[i+1]<='9') || !(exp[i-1]>='0' && exp[i-1]<='9') )
					return false ;
			}
		}
		return true ;
	}
	
	public String current_formula(){
		if( formulas.isEmpty() )
			return "there is no expresions are saved";
		if(current==-1 && formulas.isEmpty())
			return "no saved data in the history";
		
		if(current==-1)
			current = stackLength(formulas);
		return formulas.get(current);
	}
	
	public String getResults(){
//		if(formulas.isEmpty())
//			return "no saved data in the history";
//		
//		if(current==-1)
//			current = stackLength(formulas);
		
		if(! isValid( formulas.get(current) ) ){
			return this.BAD_FORMAT;
		}
		Stack<Double> container = new Stack<Double>();
		currentPostfix = getPostfix( formulas.get(current) ) ;
		StringTokenizer cut = new StringTokenizer(currentPostfix);
		String token ;
		Operations solve = new DoOperations(); 			// using polymorphism
		double operator1 , operator2 ;
		while(cut.hasMoreElements()){
			token = cut.nextToken();
			if( token.equals("+") ){
				operator1 = container.pop();
				operator2 = container.pop();
				container.push(solve.add(operator2, operator1) );
			}
			
			else if( token.equals("-") ){
				operator1 = container.pop();
				operator2 = container.pop();
				container.push(solve.subtraction(operator2, operator1) );
			}
			
			else if( token.equals("*") ){
				operator1 = container.pop();
				operator2 = container.pop();
				container.push(solve.multiplication(operator2, operator1) );
			}
			
			else if( token.equals("/") ){
				operator1 = container.pop();
				operator2 = container.pop();
				
//				if(operator1 < 0.000000000000001)        //less than 10^-15
//					return DIVISION_BY_ZERO;
				
				if(operator1 == 0.0)			//true
					return DIVISION_BY_ZERO;
				
				container.push(solve.division(operator2, operator1) );
			}
			
			else
				container.push( Double.parseDouble(token) );
		}
		String value = Double.toString( container.pop() ) ;
		if(value.contains("."))
			return value.substring(0, value.indexOf(46)+2);
		else
			return value ;
	}
	
	public void save(){
		try {
			FileWriter write = new FileWriter("data.txt");
			int counter = 5 ;
			if(! formulas.isEmpty() ){
				for(int i=stackLength(formulas) ; i>=0&&counter!=0 ; i--){
					write.write( formulas.get(i)+"\n" );
					counter--;
				}
			}
			else
				System.out.println("there is no thing to save");
			write.close();
			
		} catch (IOException e1) {
			System.out.println("FAILED TO SAVE");
		}
	}
	
	public void load(){
		Scanner read ;
		try{
			read = new Scanner(new File("data.txt"));
			formulas.clear();
			while(read.hasNext()){
				formulas.push( read.nextLine() );
			}
			if(formulas.isEmpty())
				current = -1;
			else
				current = stackLength(formulas);
		}
		catch(Exception e){
			System.out.println("FAILED TO LOAD");
		}
		
	}
	
	public String prev(){
		if(formulas.isEmpty())
			return END_LIST_UP;
		
		if(current==0)
			return END_LIST_UP ;
		
		return formulas.get(--current);
	}
	
	public String next(){
		if(formulas.isEmpty())
			return END_LIST_DOWN;
		
		if(current==stackLength(formulas))
			return END_LIST_DOWN ;
		
		return formulas.get(++current);
	}

	private String getPostfix(String s){
		Stack<Character> k = new Stack<Character>();
		String returnedValue = "" ;
		char[] exp = s.toCharArray();
		for(int i=0 ; i<s.length() ; i++){
	
			if( i<s.length() && ( (exp[i]>='0' && exp[i]<='9') || exp[i]=='.' ) ){
				while( i<s.length() && ( (exp[i]>='0' && exp[i]<='9') || exp[i]=='.' ) ){
					returnedValue += exp[i] ;
					i++;
				}
				returnedValue += " ";
			}
				
				if(i>=s.length())
					break ;
				
				if(k.isEmpty() && i!=0)
					k.push(exp[i]);
				
				
				else if(exp[i]=='+'){
					
					if(i==0)
						returnedValue += "+" ;
					
					else if(exp[i-1]=='+' || exp[i-1]=='/' || exp[i-1]=='*' || exp[i-1]=='-'){
						returnedValue += "+" ;
					}
					
					else{
						while(! k.isEmpty() ){
							returnedValue += k.pop()+" " ;	
						}
						k.push(exp[i]);
					}
				}
				
				else if(exp[i]=='-'){
					if(i==0)
						returnedValue += "-" ;
					
					else if(exp[i-1]=='+' || exp[i-1]=='/' || exp[i-1]=='*' || exp[i-1]=='-'){
						returnedValue += "-" ;
					}
					
					else{
						while(! k.isEmpty() ){
							returnedValue += k.pop()+" " ;	
						}
						k.push(exp[i]);
					}
				}
				
				else if(exp[i]=='*' || exp[i]=='/'){
					while(!k.empty() && (k.peek()=='/' || k.peek()=='*') )
						returnedValue += k.pop()+" " ;	
					k.push(exp[i]);
				}
				
		}
		
		while(!k.isEmpty())
			returnedValue += k.pop()+" ";
		return returnedValue ;
	}
	
	private int stackLength(Stack<String> s){
		String string = s.lastElement();
		return s.lastIndexOf(string);
	}
	
}
