package net.lhc.cakeshop.entitys;

public class User {
	
	private int id;	//普通用户id
	private String uName;	//普通用户用户名
	private String uPwd;	//普通用户密码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPwd() {
		return uPwd;
	}
	public void setuPwd(String uPwd) {
		this.uPwd = uPwd;
	}
	
	
	@Override
	public String toString() {
		return "User [id=" + id + ", uName=" + uName + ", uPwd=" + uPwd + ", getId()=" + getId() + ", getuName()="
				+ getuName() + ", getuPwd()=" + getuPwd() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	
	
	

}
