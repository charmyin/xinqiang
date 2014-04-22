package com.charmyin.xinqiang.persistence;

import java.util.List;

import com.charmyin.cmstudio.basic.initial.SQLMapper;
import com.charmyin.xinqiang.vo.Score;

/**
 * Mybatis Mapper Interface used for score operation
 * @author charmyin
 *
 */
@SQLMapper
public interface ScoreMapper {
	
	/**
	 * Insert a piece of question to table
	 * @param question
	 * @return
	 */
	public void insertScore(Score score);
	
	/**
	 * Find score by User id and subject type
	 * @param score
	 */
	public List<Score> findScoreByUserIdAndType(Score score);
	
}
