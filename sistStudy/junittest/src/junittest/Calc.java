package junittest;

public class Calc {
	public int add(int a, int b) {
		try {
			Thread.sleep(1500);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return a+b;
	}
}
