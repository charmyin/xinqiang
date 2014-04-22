package com.charmyin.xinqiang.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.timer.TimerFactoryBean;



/**
 * Score VO
 * @author YinCM
 * @since 2014-4-19 13:15:51
 */
public class Score {	
	
	private Integer id;
	//得分
	private int score;
	//1代表科目一，4代表科目四
	private  String  type;
	//用户id
	private int userId;
	//测试时间
	private Date testTime;
	//测试时间标准格式
	private String testTimeString;
	
	public String getTestTimeString() {
		SimpleDateFormat sda = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		return sda.format(testTime);
	}
	public void setTestTimeString(String testTimeString) {
		this.testTimeString = testTimeString;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Date getTestTime() {
		return testTime;
	}
	public void setTestTime(Date testTime) {
		this.testTime = testTime;
	}
	
	
}
