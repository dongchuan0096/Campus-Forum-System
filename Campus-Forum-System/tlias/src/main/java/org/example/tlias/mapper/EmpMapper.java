package org.example.tlias.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tlias.pojo.Emp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Mapper
public interface EmpMapper {
/*    @Select("select * from emp limit #{start},#{pageSize}")
    List<com.itheima.pojo.Emp> listEmpByPage(Integer start,Integer pageSize);

    @Select("select  count(*) from emp")
    long countEmp();*/
/*    @Select("select * from emp")
    List<com.itheima.pojo.Emp> listEmp();*/
    List<Emp> listEmp(String name, Short gender, LocalDate begin, LocalDate end);
    int deteleEmp(List<Integer> empList);

    @Insert("insert into emp (username,name,gender,image,job,entrydate,dept_id,create_time,update_time) " +
            "value (#{username},#{name},#{gender},#{image},#{job},#{entrydate},#{deptId},#{createTime},#{updateTime})")
    int insertEmp(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getById(Integer id);

    int updateEmp(Emp emp);
    @Select("select * from emp where username=#{username} and password = #{password}")
    Emp getByIdAndByPassword(Emp emp);
    @Delete("delete from emp where dept_id=#{id}")
    int deleteEmpByDeptId(Integer id);

}
