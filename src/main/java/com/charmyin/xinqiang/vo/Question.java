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
	@Size(max=50, message="登录名长度应小于50")
	private String type;
	
	@NotNull(message = "题干不能为空")
	@Size(max=50, message="登录名长度应小于300")
	private String content;
	
	@Size(max=50, message="图片地址长度应小于300")
	private String imageVideoPath;
	
	@Size(max=50, message="答案选项A应小于300")
	private String choosea;
	
	@Size(max=50, message="答案选项B应小于300")
	private String chooseb;
	
	@Size(max=50, message="答案选项C应小于300")
	private String choosec;
	
	@Size(max=50, message="答案选项D应小于300")
	private String choosed;
	
	@NotNull(message = "答案名不允许为空")
	@Size(max=50, message="答案长度应小于300")
	private String answer;
	
	@Size(max=50, message="答案注释长度应小于300")
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
