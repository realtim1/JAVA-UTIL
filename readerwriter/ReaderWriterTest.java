package readerwriter;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReaderWriterTest
{
	public static void main( String args[])
	{
		UIForm3 ui = new UIForm3();
		ui.addLayout();
		ui.eventProc();	
	}	
}

//========================================
//	화면을 관리하는 클래스 
//----------------------------------------
class UIForm3 extends JFrame
{
	JTextArea	ta;
	JButton 		bSave, bLoad, bClear;

	UIForm3()
	{
		ta		= new JTextArea();		
		bSave 	= new JButton("파일저장");
		bLoad	= new JButton("파일읽기");
		bClear	= new JButton("화면지우기");
	}

	void addLayout()
	{
		JPanel pCenter 	= new JPanel();
		pCenter.setLayout( new BorderLayout() );
		//pCenter.add("Center", ta ); //옛날 방식
		pCenter.add(new JScrollPane(ta), BorderLayout.CENTER);  //스크롤바 생성
		

		JPanel pSouth	= new JPanel();
		pSouth.add( bSave );
		pSouth.add( bLoad );
		pSouth.add( bClear );

		getContentPane().add("Center", pCenter );
		getContentPane().add("South",  pSouth );

		setSize( 400, 350 );
		setVisible( true );

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

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

	
	bClear.addActionListener(new ActionListener() { 
		public void actionPerformed( ActionEvent ev ){
			ClearData();
		}
	});	

}



	

	void saveData() {
		JFileChooser fd = new JFileChooser();  //파일 찾기
		int returnValue = fd.showSaveDialog( null );
		if( returnValue == JFileChooser.APPROVE_OPTION )   
		{
			File f = fd.getSelectedFile();		//파일을 선택하게끔
			try{
				/** 
				 * @@@@@@@@@@@@@@@@@@@@@@@@@
				 * */
				FileWriter out = new FileWriter(f);

				out.write(ta.getText());


				out.close();



			}catch(Exception ex){
				System.out.println("저장 실패");
			}	
		}
	}

	void readData() {
		JFileChooser fd = new JFileChooser();  //파일 찾기
		int returnValue = fd.showOpenDialog( null );

		if( returnValue == JFileChooser.APPROVE_OPTION)

		{
			File f = fd.getSelectedFile();
			try {
			
				FileReader in = new FileReader(f);
				
				char [] result = new char[1024];		//저장하는 곳을 만들어 놓고
				in.read(	result	);		//인자로 

				in.close();


				ta.setText( String.valueOf(	result	)	);
				
			}catch(Exception ex){
				System.out.println("저장 실패");
			}
		}
	}
	void ClearData() {
		
		ta.setText(" ");
		
	}


}