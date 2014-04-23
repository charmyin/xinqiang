package com.charmyin.xinqiang.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.charmyin.cmstudio.common.utils.JSRErrorUtil;
import com.charmyin.cmstudio.web.utils.ResponseUtil;
import com.charmyin.cmstudio.web.utils.pagination.page.Pagination;
import com.charmyin.cmstudio.web.utils.pagination.page.PaginationResultVO;
import com.charmyin.xinqiang.persistence.QuestionMapper;
import com.charmyin.xinqiang.vo.Question;

/**
 * Manage Questions
 * @author Yincm
 *
 */
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	@Resource
	private QuestionMapper questionMapper;
	
	@RequestMapping(value="/{subjectType}/manage", method=RequestMethod.GET)
	public String manage(@PathVariable String subjectType, Model model){
		model.addAttribute("subjectType", subjectType);
		return "/xinqiang/questionManage/questionManage";
	}

	@RequestMapping(value="/{subjectType}/all", method=RequestMethod.GET)
	@ResponseBody
	public PaginationResultVO findAllQuestionsByType(@PathVariable String subjectType, Pagination page){
		//model.addAttribute("subjectType", subjectType);
		Question question = new Question();
		question.setPageVO(page);
		question.setType(subjectType);
		List<Question> list = questionMapper.findAllQuestionsEqual(question);
		PaginationResultVO prv = new PaginationResultVO();
		prv.setTotal(String.valueOf(question.getPageVO().getTotalRows()));
		prv.setRows(list);
		return prv;
	}
	
	/**
	 * Insert the question committed from client
	 * @return 
	 */
	@RequestMapping(value="/save", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String saveQuestion( @Valid Question question, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			questionMapper.insertQuestion(question);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("保存过程中出错！");
		}
		return ResponseUtil.getSuccessResultString(question.getId().toString());
	 
	}
	
	
	/**
	 * Update the question committed from client
	 * @return 
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String updateQuestion( @Valid Question question, BindingResult result){
		
		if (result.hasErrors()) {
			return JSRErrorUtil.getErrorString(result);
	    }
		
		try{
			questionMapper.updateQuestion(question);
		}catch(Exception e){
			e.printStackTrace();
			return ResponseUtil.getFailResultString("更新过程中出错！");
		}
		return ResponseUtil.getSuccessResultString(question.getId().toString());
	 
	}
	
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST, produces = "text/plain;charset=UTF-8")  
	@ResponseBody
    public String fileUpload(@RequestParam MultipartFile myfile, @RequestParam String questionId, @RequestParam String subjectType, HttpServletRequest request, HttpServletResponse response) throws IOException{  
        //可以在上传文件的同时接收其它参数  
        System.out.println("收到用户[]的文件上传请求");  
        //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中  
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建  
        String realPath = request.getSession().getServletContext().getRealPath("/upload");  
        //上传文件的原名(即上传前的文件名字)  
        String originalFilename = null;  
        //如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解  
            if(myfile.isEmpty()){
            	return ResponseUtil.getFailResultString("未选择文件！");
            }else{  
                originalFilename = myfile.getOriginalFilename();  
                //如果是新建的,保存为临时文件
            	
                if(originalFilename.endsWith("jpg") || originalFilename.endsWith("jpeg") || originalFilename.endsWith("JPG") || originalFilename.endsWith("JPEG")){
                	originalFilename=subjectType+"-"+questionId;
                	originalFilename+=".jpg";
                }else if(originalFilename.endsWith("flv") || originalFilename.endsWith("FLV")){
                	originalFilename=subjectType+"-"+questionId;
                	originalFilename+=".flv";
                }else{
                	return ResponseUtil.getFailResultString("文件格式有误！");
                }
                try {  
                    //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉  
                    //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传  
                    FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));  
                } catch (IOException e) {  
                    System.out.println("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");  
                    e.printStackTrace();  
                    return ResponseUtil.getFailResultString(e.getMessage());
                }  
            }  
        //此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]  
        //System.out.println(realPath + "\\" + originalFilename);  
        //此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]  
        //System.out.println(request.getContextPath() + "/upload/" + originalFilename);  
        //不推荐返回[realPath + "\\" + originalFilename]的值  
        //因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的  

    	return ResponseUtil.getSuccessResultString(originalFilename); 
    }  

	
}
