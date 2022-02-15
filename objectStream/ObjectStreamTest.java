package objectStream;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ObjectStreamTest
{
	public static void main( String [] args )
	{
		UIForm  ui = new UIForm();
		ui.addToLayout();
		ui.eventProc();
	}
}


//-----------------------------------------
// 화면을 관리하는 클래스
class UIForm extends JFrame
{
	// 데이타를 저장할 변수
	String name;
	int		age;
	double 	height;
	char	bloodType;
	
	// 화면 GUI에 관련한 변수
	JTextField tfName, tfAge, tfHeight, tfBloodType;
	JButton	   bSave, bLoad;
	
	
	UIForm()
	{
		tfName 		= new JTextField(10);
		tfAge 		= new JTextField(10);
		tfHeight 	= new JTextField(10);
		tfBloodType = new JTextField(10);
		
		bSave		= new JButton("저장하기", new ImageIcon("../img/save.gif"));
		bLoad		= new JButton("읽어오기", new ImageIcon("../img/load.gif"));
		
	}
	
	// 화면 구성하는 메소
	void addToLayout()
	{
		JPanel pCenter = new JPanel();
		pCenter.setLayout( new GridLayout(5 ,2,10,10) );
		pCenter.add( new JLabel("이름") );
		pCenter.add( tfName );
		pCenter.add( new JLabel("나이") );
		pCenter.add( tfAge );
		pCenter.add( new JLabel("신장") );
		pCenter.add( tfHeight );
		pCenter.add( new JLabel("혈액형") );
		pCenter.add( tfBloodType );
		
		pCenter.add( bSave );
		pCenter.add( bLoad );
		
		getContentPane().add("Center", pCenter);
		
		setSize( 400, 300 );
		setVisible( true );
		
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

	}
	
	
	// 이벤트 처리하는 메소드 
	void eventProc()
	{
		// "저장하기" 버튼이 눌렸을 
		bSave.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				saveData();
			}
		});
		
		// "읽어오기" 버튼이 눌렸을 때
		bLoad.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				readData();
			}
		});			
	}

	void saveData() {
		/*
		1. 텍스트필드에서 입력값 얻어와서 변수에 저장
		2. 파일출력스트림 생성 ( filter 포함 )
		3. 스트림에 출력
		4. 스트림 닫기
		5. 텍스트필드 초기화 
		*/
		name		= tfName.getText();
		age			= Integer.parseInt( 	tfAge.getText() );
		height		= Double.parseDouble(	tfHeight.getText() );
		bloodType 	= (tfBloodType.getText()).charAt(0);
		
		/** 
		 * @@@@@@@@@@@@@@@@@@@@@@@@@
		 * */
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"));   
			//객체를 통으로 주고받는 필터링
			
			//Record r = new Record(); //이건 setter로 저장
			Record r = new Record(name, age, height , bloodType); // 생성자로 직렬화
			oos.writeObject(r);
			
			
			
			oos.close();

			
			
			
		}catch( Exception ex ){ 
			System.out.println("쓰기 실패 : " +ex.getMessage());
			ex.printStackTrace();
		}
		
		tfName.setText("");
		tfAge.setText("");
		tfHeight.setText("");
		tfBloodType.setText("");
			
		
	}
	
	void readData(){
		/*
		1. 파일입력스트림 생성 (filter 포함 )
		2. 스트림에서 데이타 읽어서 변수에 저장
		3. 텍스트필드에  출력
		4. 스트림 닫기 			
		*/
			try{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"));  //객체를 넘길떄
				Record r1 = new Record(name, age, height , bloodType);
				r1 = (Record)ois.readObject();   //형변항 Object 를 Record로  역직렬화
			
				tfName.setText(name);
				tfAge.setText(Integer.toString(age));
				tfHeight.setText(Double.toString(height));
				tfBloodType.setText(Character.toString(bloodType));	//String.valueOf()
				
				
				ois.close();
				
			}catch( Exception ex ){
				System.out.println("읽기 실패");
			}

	}
	

}