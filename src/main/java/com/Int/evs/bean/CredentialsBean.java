package com.Int.evs.bean;
import javax.persistence.*;

@Entity 
@Table(name="user_credentials") 
public class CredentialsBean {
	@Id @Column(name="userId") String userId;
	@Column(name="password") String password;
	@Column(name="userType") String userType;
	@Column(name="loginStatus") int loginStatus;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public int getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(int loginStatus) {
		this.loginStatus = loginStatus;
	}
	@Override
	public String toString() {
		return "CredentialsBean [userId=" + userId + ", password=" + password + ", userType=" + userType
				+ ", loginStatus=" + loginStatus + "]";
	}
	
	
	
}
