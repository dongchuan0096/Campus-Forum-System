package org.example.tlias.service;

import org.example.tlias.pojo.Emp;
import org.example.tlias.pojo.Pagebean;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.LocalDate;
import java.util.List;

public interface EmpService {
/*    Pagebean pageEmp(Integer start,Integer pageSize);*/
     Pagebean pageEmp(@RequestParam(defaultValue = "0") Integer page,
                            @RequestParam(defaultValue = "10") Integer pageSize,
                            String name, Short gender,
                            LocalDate begin, LocalDate end
    );
    int deleteEmp(List<Integer> empList);
    int insert(Emp emp);

    Emp getById(Integer id);

    int updateEmp(Emp emp);

    Emp login(Emp emp);

    int deteleEmpByDeptId(Integer id);
}
