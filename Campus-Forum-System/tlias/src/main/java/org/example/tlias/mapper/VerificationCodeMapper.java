package org.example.tlias.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.tlias.pojo.VerificationCode;
@Mapper
public interface VerificationCodeMapper {
    @Select("select * from verification_codes where email =#{email} ")
    VerificationCode getVercodeByEmail(String email);
    @Delete("delete from verification_codes where email=#{email}")
    Integer deleteVercodeByEmail(String email);

    @Insert("insert into verification_codes (code,email,expires_at) values(#{code},#{email},#{expiresAt})")
    Integer insert(VerificationCode verificationCode);
}
