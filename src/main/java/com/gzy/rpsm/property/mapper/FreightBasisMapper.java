package com.gzy.rpsm.property.mapper;

import com.gzy.rpsm.property.pojo.FreightBasis;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreightBasisMapper {
    @Select("select * from freight_basis")
    List<FreightBasis> findAll();
    @Update("<script>update freight_basis" +
            "        <set>" +
            "            <if test='price != null'>" +
            "                price = #{price}," +
            "            </if>" +
            "            <if test=\"intro != null and intro != ''\">" +
            "                intro = #{intro}," +
            "            </if>" +
            "            <if test=\"basis_name != null and basis_name != ''\">" +
            "                basis_name = #{basis_name}," +
            "            </if>" +
            "        </set>" +
            "        where freight_basis_id = #{freight_basis_id}</script>")
    int updateById(FreightBasis fh);
}
