package objectStream;
// ***************** 직렬화가 필요함

import java.io.Serializable;

public class Record implements Serializable{ // implements Serializable 을통해서 직렬화 작업함
	private String name;
	private int age;
	private double height;
	private char bloodType;
	
	//(2) 생성자로 값 변경
	public Record() {}		//**************** 습관 기본생성자 만드는것
	
	
	public Record(String name, int age, double height, char bloodType) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.bloodType = bloodType;
	}
	
	//(1) setter 와 getter   private 값 변경할때  //자동으로 만들기 
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public char getBloodType() {
		return bloodType;
	}
	public void setBloodType(char bloodType) {
		this.bloodType = bloodType;
	}
	
	
	
}
