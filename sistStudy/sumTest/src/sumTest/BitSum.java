package sumTest;

public class BitSum {
	
	
	public static int getSum(int n) {
		int tot = 0;
		int i;
		for(i = 1; i <= n; i++) {
			tot += i;
		}
		return tot;
	}
}
