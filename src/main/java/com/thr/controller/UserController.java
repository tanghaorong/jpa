package com.thr.controller;

import com.thr.entity.PageEntity;
import com.thr.entity.User;
import com.thr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/list")
    public User list(){
        User user= userRepository.findByUserName("admin");
        return user;
    }

    /**
     * 根据ID删除用户信息
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public String delete(){
        userRepository.deleteById(1);
        return "用户信息删除成功！";
    }

    /**
     * 分页查询测试
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/userPage")
    public List<User> userPage(int page){
        PageEntity pageEntity=new PageEntity();
        pageEntity.setSize(2);
        pageEntity.setPage(page);
        //创建分页对象
        Pageable pageable= PageRequest.of(pageEntity.getPage()-1,pageEntity.getSize());
        //执行分页查询
        return userRepository.findAll(pageable).getContent();
    }

    /**
     * 分页查询排序测试
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping("/userPageSort")
    public List<User> userPageSort(int page){
        PageEntity pageEntity=new PageEntity();
        pageEntity.setSize(2);
        pageEntity.setSort("desc");
        pageEntity.setPage(page);

        //获取排序对象
        Sort.Direction direction=Sort.Direction.ASC.toString().equalsIgnoreCase(pageEntity.getSort())? Sort.Direction.ASC:Sort.Direction.DESC;
        //设置排序对象参数
        Sort sort=new Sort(direction,pageEntity.getSidx());
        //创建分页对象
        Pageable pageable= PageRequest.of(pageEntity.getPage()-1,pageEntity.getSize(),sort);
        //执行分页查询
        return userRepository.findAll(pageable).getContent();
    }


    /**
     * 根据动态条件查询
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/lists")
    public List<User> lists(User user){
        user.setUserName("admin");
        //进行拼接
        List<User> userlist= userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate=criteriaBuilder.conjunction();
                if (user!=null){
                    //SELECT * FROM t_user where user_name LIKE '%admin%'
                    predicate.getExpressions().add(criteriaBuilder.like(root.get("userName"),"%"+user.getUserName()+"%"));
                }
                return predicate;
            }
        });
        return userlist;
    }
}
