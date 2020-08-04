package join;

import java.sql.Date;

public class JoinVO {
	private String member_id;
	private String name;
	private String pwd;
	private Date regdate;
	
	public JoinVO() {
	}
	


	public JoinVO(String member_id, String name, String pwd) {
		super();
		this.member_id = member_id;
		this.name = name;
		this.pwd = pwd;
	}


	public JoinVO(String id, String name, String pwd, Date regdate) {
		super();
		this.member_id = id;
		this.name = name;
		this.pwd = pwd;
		this.regdate = regdate;
	}


	public String getMember_id() {
		return member_id;
	}


	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public Date getRegdate() {
		return regdate;
	}


	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}