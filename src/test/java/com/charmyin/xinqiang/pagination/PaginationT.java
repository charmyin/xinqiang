package com.charmyin.xinqiang.pagination;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.charmyin.cmstudio.basic.authorize.persistence.UserMapper;
import com.charmyin.cmstudio.basic.authorize.service.UserService;
import com.charmyin.cmstudio.basic.authorize.vo.User;
import com.charmyin.cmstudio.web.utils.pagination.page.Page;
import com.charmyin.cmstudio.web.utils.pagination.page.Pagination;
import com.charmyin.xinqiang.vo.Question;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-context.xml"})
@TransactionConfiguration(defaultRollback=false)
public class PaginationT{
	
	@Resource
	private UserMapper userMapper;
	
	@Resource
	private UserService userService;
	
    @Test
    public void testName() throws Exception {
        
    	User find = new User();
    	/*find.setName("admi");
        Page paginationSupport = new Pagination();
        paginationSupport.setCurrentPage(1);
        paginationSupport.setPageSize(2);
        find.setPageVO(paginationSupport);
        List<User> list = userService.findAllUser(paginationSupport);
        System.out.println(list.size());
        System.out.println(paginationSupport.getTotalPages());
        System.out.println(paginationSupport.getTotalRows());*/
    }

	
}
