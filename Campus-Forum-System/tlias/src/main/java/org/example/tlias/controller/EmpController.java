package org.example.tlias.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tlias.ano.Log;
import org.example.tlias.pojo.Emp;
import org.example.tlias.utils.Result;
import org.example.tlias.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController

public class EmpController {
    @Autowired
    EmpService empService;
    @GetMapping
    Result pageEmp(@RequestParam(defaultValue = "0")  Integer page,
                   @RequestParam(defaultValue = "10") Integer pageSize,
                   String name, Short gender,
                   @DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate begin,@DateTimeFormat(pattern = "yyyy-mm-dd") LocalDate end){
        log.info("分页查询emp page:"+page+" pagesize"+pageSize);
        return Result.success(empService.pageEmp(page,pageSize,name,gender,begin,end));
    }
    @Log
    @DeleteMapping("/{ids}")
    Result deleteEmp(@PathVariable List<Integer> ids){
        Integer result= empService.deleteEmp(ids);
        return Result.success(result);
    }
    @Log
    @PostMapping
    Result insertEmp(@RequestBody Emp emp){
        Integer result=empService.insert(emp);
        return Result.success(result);
    }
    @GetMapping("/{id}")
    Result getById(@PathVariable Integer id){
       Emp emp= empService.getById(id);
       emp.setPassword(null);
       return Result.success(emp);
    }
    @Log
    @PutMapping
    Result updateEmp(@RequestBody Emp emp){
        return Result.success(empService.updateEmp(emp));
    }

}
