package com.yonyou.ucf.mddtest.service;

import com.yonyou.iuap.ucf.dao.support.EntitySqlParam;
import com.yonyou.iuap.ucf.dao.support.SqlParam;
import com.yonyou.ucf.mddtest.dao.OrgDao;
import com.yonyou.ucf.mddtest.po.OrgPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/4/24
 * @des
 */
@Service
public class OrgService {

    @Autowired
    private OrgDao orgDao;

    /*public List<OrgPO> selectAll(){
        SqlParam sqlParam = EntitySqlParam.of().eq("code","001");
        List<OrgPO>  list =   orgDao.list(sqlParam);
        return list;
    }*/

    public int insert(OrgPO orgPO){
        return orgDao.insert(OrgPO.class,orgPO);
    }


}
