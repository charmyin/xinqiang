package com.charmyin.xinqiang.vo;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.charmyin.cmstudio.web.utils.pagination.annotation.Paging;
import com.charmyin.cmstudio.web.utils.pagination.page.Page;



/**
 * Question VO
 * @author YinCM
 * @since 2014-4-19 13:15:51
 */
@Paging(field = "pageVO")
public class Question {	
	
	private Page pageVO;

    public Page getPageVO() {
        return pageVO;
    }

    public void setPageVO(Page pageVO) {
        this.pageVO = pageVO;
    }
	
	private Integer id;
	
	@NotNull(message = "科目类型不能为空")
	private String type;
	
	@NotNull(message = "题干不能为空")
	private String content;
	
	private String imageVideoPath;
	
	private String choosea;
	
	private String chooseb;
	
	private String choosec;
	
	private String choosed;
	
	private String answer;
	
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageVideoPath() {
		return imageVideoPath;
	}

	public void setImageVideoPath(String imageVideoPath) {
		this.imageVideoPath = imageVideoPath;
	}

	public String getChoosea() {
		return choosea;
	}

	public void setChoosea(String choosea) {
		this.choosea = choosea;
	}

	public String getChooseb() {
		return chooseb;
	}

	public void setChooseb(String chooseb) {
		this.chooseb = chooseb;
	}

	public String getChoosec() {
		return choosec;
	}

	public void setChoosec(String choosec) {
		this.choosec = choosec;
	}

	public String getChoosed() {
		return choosed;
	}

	public void setChoosed(String choosed) {
		this.choosed = choosed;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
