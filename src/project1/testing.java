package project1;

public class testing {

	public static void main(String[] args) {
		
		CountDownTimer s = new CountDownTimer("2:59:8");
		System.out.println("Time: " + s);
		
		s = new CountDownTimer("20:8");
		System.out.println("Time: " + s);
		
		s = new CountDownTimer();
		System.out.println("Time: " + s);
		
		s = new CountDownTimer(10, 10);
		System.out.println("Time: " + s);
		
		s = new CountDownTimer("8");
		System.out.println("Time: " + s);
		
		CountDownTimer s1 = new CountDownTimer(25, 2, 20);
		System.out.println("Time: " + s1);
		s1.sub(1000);
		System.out.println("Time: " + s1);
		s1.add(1000);
		System.out.println("Time: " + s1);
		
		s1 = new CountDownTimer(s);
		System.out.println("Time: " + s1);
		
		CountDownTimer s2 = new CountDownTimer(40, 10, 20);
		s2.sub(100);
		
		for (int i = 0; i < 4000; i++)
			s2.dec();
		
		for (int i = 0; i < 3999; i++)
			s2.inc();
		
		System.out.println("Time: " + s2);
		
		System.out.println(s2.compareTo(s1));
		
		System.out.println(s2.equals(s1));
		
		MyJFrame frame = new MyJFrame();
		
	} 
	
}
