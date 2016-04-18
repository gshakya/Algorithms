package lab1;

public class GCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(99,27));
	}

	public static int  gcd (int a , int b){
		int min = a>b?b:a;	
		while ( min > 1){
			if (a%min==0 && b%min==0){
				return min;
			}
			min --;
		}
		return 1;
	}
}
