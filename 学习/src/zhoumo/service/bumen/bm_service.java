package zhoumo.service.bumen;

import org.json.JSONObject;
import zhoumo.jdbc.bumenbiao;

public class bm_service {
    public JSONObject check(String bumenmingcheng){

        if(bumenmingcheng==null||"".equals(bumenmingcheng)){
            return  new JSONObject("{code:500,msg:'输入错误'}");
        }
        bumenbiao bumen= new bumenbiao();
        JSONObject jsonObject= bumen.zhixing(bumenmingcheng);
        return jsonObject;
    }

    }




