package thread;

import java.util.Iterator;

public class Ex01_ThreadTest {

	public static void main(String[] args) {
		MakeCar1 mc1 = new MakeCar1("차틀만들기");
		mc1.start();
		
		MakeCar1 mc2 = new MakeCar1("엔진부착");
		mc2.start();  // start 함수를 써야함 그래야 run을 호출
		
		System.out.println("프로그램 종료");
		
		//멀티 (작업)프로그램인것 처럼 보일려고 스레드 사용
		
	}

}
class MakeCar1	extends Thread{
	String work;
	MakeCar1(String work){
		this.work= work;
		
	}
	public void run() {			//	run 메소드를 오버라이딩
		for (int i = 0; i < 5; i++) {
			System.out.println(work + "작업중");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
