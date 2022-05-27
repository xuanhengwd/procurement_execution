package com.example.mapper;

import com.example.pojo.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface ContractMapper {

    /**
     * 新增合同信息
     * @param contract
     */
    void addContract(Contract contract);

    /**
     * 条件查询合同
     * @param contract
     * @return
     */
    List<Contract> selectContractByCondition(Contract contract);

    /**
     * 更新
     * @param contract
     */
    void updateById(Contract contract);

    @Select("select * from contract where id = #{id}")
    Contract selectById(int id);


    @Select("select * from contract where pro_no = #{proNo}")
    Contract selectContractByProNo(String proNo);


    @Update("update contract set contract_path = #{path} where id = #{id}")
    void addTextPath(@Param("id") int id, @Param("path") String path);

}
