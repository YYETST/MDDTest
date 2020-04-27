package com.yonyou.ucf.mddtest.po;

import com.yonyou.iuap.anno.Table;
import com.yonyou.iuap.ucf.dao.BasePO;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nishch
 * @version 1.0
 * @date 2020/4/23
 * @des
 */
@Setter
@Getter
@Table(name = "nsc_iuap_org")
public class OrgPO extends BasePO {
    public OrgPO() {
    }

    public OrgPO(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private String code;
    private String name;

}
