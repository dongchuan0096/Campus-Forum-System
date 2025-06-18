package org.example.tlias.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.example.tlias.mapper.EmpMapper;
import org.example.tlias.pojo.Emp;
import org.example.tlias.pojo.Pagebean;
import org.example.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    EmpMapper empMapper;

    @Override
/*    public Pagebean pageEmp(@RequestParam(defaultValue = "0") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize)
    {
       long count= empMapper.countEmp();
       Integer start = (page-1)*pageSize;
       List<com.itheima.pojo.Emp> list=empMapper.listEmpByPage(start,pageSize);
       Pagebean pagebean = new Pagebean(count,list);
       return pagebean;

    }*/
    public Pagebean pageEmp( Integer page,
                             Integer pageSize,
                            String name,Short gender,
                            LocalDate begin,LocalDate end
    )
    {
        PageHelper.startPage(page,pageSize);
        List<Emp> list=empMapper.listEmp(name,gender,begin,end);
        PageInfo<Emp> p = new PageInfo<>(list);
        Pagebean pagebean = new Pagebean(p.getTotal(),p.getList());
        System.out.println(p.getTotal()+"  "+p.getList().size());
/*      Page<Emp> p=(Page<Emp>) list;
        Pagebean pagebean = new Pagebean(p.getTotal(),p.getResult());*/
        return pagebean;

    }

     public int deleteEmp(List<Integer> empList){
        return empMapper.deteleEmp(empList);
    }
     public int insert(Emp emp){
        if(emp!=null){
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            return empMapper.insertEmp(emp);
        }
        else
            return 0;
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);

    }

    @Override
    public int updateEmp(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        return empMapper.updateEmp(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByIdAndByPassword(emp);
    }

    @Override
    public int deteleEmpByDeptId(Integer id) {
        return empMapper.deleteEmpByDeptId(id);
    }
}
