package com.gzy.rpsm.property.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gzy.rpsm.property.mapper.BuildingMapper;
import com.gzy.rpsm.property.pojo.Building;
import com.gzy.rpsm.property.pojo.PageResult;
import com.gzy.rpsm.property.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService{

    @Autowired
    BuildingMapper buildingMapper;

    @Override
    public List<Building> buildingsOfUsed() {
        return buildingMapper.buildingsOfStatus(1);
    }
    @Override
    public int insertBuilding(Building building) throws DuplicateKeyException {
        return buildingMapper.insertBuilding(building);
    }
    @Override
    public PageResult<Building> PageOfBuilding(int current, int size) {
        PageHelper.startPage(current,size);
        List<Building> page = buildingMapper.buildingsOfStatus(0);
        return PageResult.restPage((Page<Building>)page);
    }
    @Override
    public int insertBuildings(Building building,List<Building> buildings, int size) {
        buildings.add(building);
        int result = -1;
        try{
            //将建筑名和建筑编码中的数字序号+1，并生成列表
            for(int i = 0;i < size;i++){
                Building tempBuilding = building.clone();
                String code = building.getBuilding_code();
                code = code.replaceAll("\\d+",
                        StringUtil.numberToLen2String(StringUtil.getNumberFromString(code) + i + 1)
                );
                tempBuilding.setBuilding_code(code);
                String name = building.getBuilding_name();
                name = name.replaceAll("\\d+",
                        StringUtil.numberToLen2String(StringUtil.getNumberFromString(name) + i + 1)
                );
                tempBuilding.setBuilding_name(name);
                buildings.add(tempBuilding);
            }
            result = buildingMapper.insertBuildings(buildings);
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }catch (DuplicateKeyException e){
            e.printStackTrace();
            throw new DuplicateKeyException("建筑编号重复。"+e.getMessage());
        }
        return result;
    }
    @Override
    public int changeBuildingStatus(List<Integer> idList, Integer status) {
        return buildingMapper.changeBuildingStatus(idList,status);
    }
}
