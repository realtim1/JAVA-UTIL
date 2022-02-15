package dataStream;


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DataStreamTest
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
		String name		= tfName.getText();
		int age			= Integer.parseInt( 	tfAge.getText() );
		double height		= Double.parseDouble(	tfHeight.getText() );
		char bloodType 	= (tfBloodType.getText()).charAt(0);
		
		/** 
		 * @@@@@@@@@@@@@@@@@@@@@@@@@
		 * */
		try{
				DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.txt"));               //필터링 (int String double 보내기 위해)
				dos.writeUTF(name);
				dos.writeInt(age);
				dos.writeDouble(height);
				dos.writeChar(bloodType);
				
			dos.close();
			
			
		}catch( Exception ex ){ 
			System.out.println("쓰기 실패");
		}
		
		tfName.setText("");
		tfAge.setText("");
		tfHeight.setText("");
		tfBloodType.setText("");
			
	}
	
	void readData() {
		/*
		1. 파일입력스트림 생성 (filter 포함 )
		2. 스트림에서 데이타 읽어서 변수에 저장
		3. 텍스트필드에  출력
		4. 스트림 닫기 			
		*/
			
			String name = null;
			int age = 0;
			double height = 0.0;
			char bloodType = '\u0000';


			try{
				DataInputStream dis = new DataInputStream(new FileInputStream("data.txt"));
				name =dis.readUTF();
				age = dis.readInt();
				height = dis.readDouble();
				bloodType = dis.readChar();
				tfName.setText(name);
				tfAge.setText(String.valueOf(age));		//or String.valueOf(age)
				 //tfName.setText(dis.readInt());
				tfHeight.setText( Double.toString(height));
				tfBloodType.setText(Character.toString(bloodType));
				
				
				
				dis.close();

				
			}catch( Exception ex ){
				System.out.println("읽기 실패");
			}
		
				
	}
	
}