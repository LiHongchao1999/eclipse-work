package net.lhc.cakeshop.entitys;

public class Admin {
	private String aName;	//管理员用户名
	private String aPwd;	//管理员密码
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getaPwd() {
		return aPwd;
	}
	public void setaPwd(String aPwd) {
		this.aPwd = aPwd;
	}
	@Override
	public String toString() {
		return "Admin [aName=" + aName + ", aPwd=" + aPwd + "]";
	}
	

}
