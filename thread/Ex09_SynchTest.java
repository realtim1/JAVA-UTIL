package thread;

class Count {
	int i;
	 void increment() {		//lock을 검 메소드를 걸면 밑에 것들이 다 락이 걸리니 lock 걸 곳에 써준다
		synchronized(this) {i++;}
		// 	(1) read i 				값이 2억이 안되는 이유 tc1이 10 되고 +1 되고 그때 tc가 10읽고 11저장하고 그다음 tc1이 11저장 그리고 tc2가 저장 해서  
		//	(2) i + 1				그걸 방지하기 위해 synchronized 을 사용 객체를 넣어줘야한다 인자로 (없으면 자기자신으로)
		//	(3) write i
	} // method 함수
	Count () {
		
	}
}

class ThreadCount extends Thread {
	
	Count cnt;
	
	ThreadCount(Count cnt) {
		this.cnt = cnt;
	}
	
	public void run() {
		for(int i=0; i<100000000; i++) {
			cnt.increment();
		}
	}
	
}

public class Ex09_SynchTest {
	public static void main(String[] args) {
		
		Count count = new Count(); // Count 변수 선언
		
		ThreadCount tc1 = new ThreadCount(count); 
		tc1.start();
		
		ThreadCount tc2 = new ThreadCount(count);
		tc2.start();
		
		
		try {
			tc1.join();
			tc2.join();
			
		} catch (Exception ex) {
		}
		
	
		
		
		System.out.println("최종 i의 결과 : " + count.i);
		
	}
}
