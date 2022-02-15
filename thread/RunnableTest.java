package thread;

public class RunnableTest {

	public static void main(String[] args) {
		MakeCar2 mc1 = new MakeCar2("차틀 만들기");
		Thread t1 = new Thread(mc1);			//Thread 라는 클래스를 객체를 생성하고 인자로 mc1을 넣는다.
		
		t1.start();			// mc1 이랑 t1은 관계가 없다 .
		//------------------------------------------------
		//Thread t2 = new Thread( new MakeCar2("엔진부착"));   //한번 줄였음
		
		new Thread( new MakeCar2("엔진부착")).start();			//2번 줄였음
		//MakeCar 객체를 생성하면서 Thread 인자로 하면서 start 호출
		
	}

}
class MakeCar2	implements Runnable{				//Runnable 을 인터페이스 상속받음
	String work;
	MakeCar2(String work){
		this.work= work;
		
	}
	public void run() {			//	run 메소드를 오버라이딩
		for (int i = 0; i < 5; i++) {
			System.out.println(work + " 작업중");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

