package com.yonyou.ucf.mdf.mddtest;

import com.yonyou.ucf.mddtest.po.OrgPO;
import com.yonyou.ucf.mddtest.service.OrgService;
import com.yonyou.ucf.mdf.MDFApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MDFApplication.class)
public class OrgTest {

    @Autowired
    OrgService orgService;

    /*@Test
    public void selectAll(){
        List<OrgPO>  list = orgService.selectAll();
        System.out.println(list.toString());
    }*/

    @Test
    public void insert(){
        orgService.insert(new OrgPO("001","nscertest"));
    }

}
