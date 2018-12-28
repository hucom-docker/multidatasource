package com.example.multidatasource.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.multidatasource.mapper.CompanyMapper;
import com.example.multidatasource.mapper.EmpInfoMapper;
import com.example.multidatasource.model.Company;
import com.example.multidatasource.model.EmpInfo;
import com.example.multidatasource.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import static com.example.multidatasource.util.JsonResult.*;

@Service
public class TestServiceImpl implements TestService {

    /**
     * 数据源1
     */
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 数据源2
     */
    @Autowired
    private EmpInfoMapper empInfoMapper;

    @Override
    public JSONObject test(HttpServletRequest request) {
        try {
            String coId =  request.getParameter("coId");
            String empId =  request.getParameter("empId");
            Company company = companyMapper.findCompanyById(coId);
            EmpInfo empInfo =  empInfoMapper.findEmpById(empId);
            String sex = empInfo.getSex().equals("1") ? "男" : empInfo.getSex().equals("0") ? "女" : "未知";
            String result = empInfo.getName() + ",性别，"+ sex + "，今年" + empInfo.getAge() + "岁，在" + company.getCoAddress() + "的" + company.getCoName()+"上班，"+"从事"+empInfo.getRole()+",他们" +
                    "公司成立" + company.getCoAGE() + "年，目前有" + company.getCoNum() + "个人。";
            return build(CODE_SUCCESS,MSG_SUCCESS,result);
        }catch (Exception e){
            return build(CODE_FAIL,MSG_FAIL,e.toString());
        }
    }
}
