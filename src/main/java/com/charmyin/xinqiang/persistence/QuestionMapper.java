package com.charmyin.xinqiang.persistence;

import java.util.List;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.xinqiang.vo.Question;

/**
 * Mybatis Mapper Interface used for question operation
 * @author charmyin
 *
 */
@SQLMapper
public interface QuestionMapper {
	
	/**
	 * Get all questions from question role 
	 * @return
	 */
	public List<Question> findAllQuestions();
	
	/**
	 * Get question by the conditions contained by params "question", it use "=" in where condition
	 * @param question question instance which contains the question conditions.
	 * @return
	 */
	public List<Question> findAllQuestionsEqual(Question question);
	
	/**
	 * Get question by the conditions contained by params "question", it use "like" in where condition
	 * @param question question instance which contains the conditions.
	 * @return
	 */
	public List<Question> findAllQuestionsLike(Question question);
	
	/**
	 * Insert a piece of question to table
	 * @param question
	 * @return
	 */
	public void insertQuestion(Question question);
	
	/**
	 * Update question by question object which must contain id
	 * @param question
	 */
	public void updateQuestion(Question question);
	
	/**
	 * Delete questions by id string array
	 * @param ids
	 */
	public void deleteQuestion(int id);

	/**
	 * Get Question list  by question id
	 * @return roleName list
	 */
	public Question findQuestionById(int id);
	
}
