package json;


import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
	
class MyFrame extends JFrame
{
	// 변수선언
	JTextField tfName, tfTel, tfJumin, tfGender, tfAge, tfHome;
	JButton bSave, bOpen;
	
	// 객체 생성
	public MyFrame(){		
		super("나의 첫 화면");
		tfName = new JTextField(15);
		tfTel = new JTextField(15);
		tfJumin = new JTextField(15);
		tfGender = new JTextField(15);
		tfAge = new JTextField(15);
		tfHome = new JTextField(15);
		
		bSave = new JButton("저장하기");
		bOpen = new JButton("읽어오기");
		
	}
	
	// 화면구성 및 보여주기
	public void addLayout(){
		
		JPanel p_center = new JPanel();
		p_center.setLayout( new GridLayout( 6, 2 ));
		p_center.add( new JLabel("이름"));
		p_center.add( tfName );
		p_center.add( new JLabel("전화"));
		p_center.add( tfTel );
		p_center.add( new JLabel("주민"));
		p_center.add( tfJumin );
		p_center.add( new JLabel("성별"));
		p_center.add( tfGender );
		p_center.add( new JLabel("나이"));
		p_center.add( tfAge );
		p_center.add( new JLabel("출신지"));
		p_center.add( tfHome );
		
		JPanel p_south = new JPanel();
		p_south.add( bSave );
		p_south.add( bOpen );
		
		add( p_center, BorderLayout.CENTER);
		add( p_south, BorderLayout.SOUTH);
		setSize(400, 300);
		setVisible(true);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
		
	}
	
	// 이벤트 연결
	public void eventProc()
	{
		// "저장하기" 버튼이 눌렸을 
		bSave.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				saveData();
			}
		});
		
		// "읽어오기" 버튼이 눌렸을 때
		bOpen.addActionListener(new ActionListener() { 
			public void actionPerformed( ActionEvent ev ){
				readData();
			}
		});		
	
	}
	

	/**
			http://code.google.com/p/json-simple/
			왼쪽 메뉴 > Download >  json_simple-1.1.jar를 직접 다운
			Add External Jar 추가
	 */
	void saveData() {		
	
		
		JSONObject obj = new JSONObject();
		obj.put("name", tfName.getText());
		obj.put("tel", tfTel.getText());
		obj.put("jumin", tfJumin.getText());
		obj.put("gender", tfGender.getText());
		obj.put("age", tfAge.getText());
		obj.put("home", tfHome.getText());
		
		
		//나머지도
		
		
		try {
			FileWriter out = new FileWriter("person.json");
			out.write(obj.toJSONString()); //json 문자열로변환
			
			
			
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	void readData() {		
		 
		try {
			FileReader in = new FileReader("person.json");
			JSONParser parser= new JSONParser();
			JSONObject obj =(JSONObject) parser.parse(in); 		//쪼개서 불러오는거
			tfName.setText((String) obj.get("name"));
			tfTel.setText((String) obj.get("tel"));
			tfJumin.setText((String) obj.get("jumin"));
			tfGender.setText((String) obj.get("gender"));
			tfAge.setText((String) obj.get("age"));
			tfHome.setText((String) obj.get("home"));
			
			
			//나머지도
			
			in.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
} // end of class MyFrame

public class GuiTest {
	public static void main(String[] args) {
		MyFrame my = new MyFrame();
		my.addLayout();
		my.eventProc();
	}
}
