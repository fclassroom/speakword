package org.ifelse.points;

import android.content.Context;
import android.content.SharedPreferences;
import org.ifelse.vl.FlowBox;
import org.ifelse.vl.FlowPoint;


/**
 * Created by dzb on 16/9/8.
 * 根据key和value 去sharePreference中取对应的值
 *
 * <property name="key" title="key" type="0"  args="" value="" />
 <property name="value" title="存储变量" type="0"  args="" value="" />
 <property name="def" title="默认值" type="0"  args="" value="" />

 *
 */public class UserData extends FlowPoint {


    final static String key_key = "key";
    final static String key_value = "value";
    final static String key_def = "def";

    @Override
    public void run(FlowBox flowBox) throws Exception {

        String[] keys = this.params.get(key_key).split(",");
        String[] values = this.params.get(key_value).split(",");
        String[] defs = null;

        String defstr = params.get(key_def );
        if( defstr != null && defstr.length() > 0 )
        {
            defs = defstr.split(",");

        }
        else {
            defs = new String[keys.length];
            for(int i=0;i<defs.length;i++)
                defs[i] = "";
        }


        SharedPreferences sp = getPreference(flowBox.getContext());


        for(int i=0;i<keys.length;i++)
        {
            String v;
            if( defs.length > i )
                v =sp.getString(keys[i],defs[i]);
            else
                v =sp.getString(keys[i],null);


            flowBox.setValue( values[i],v);

        }

        flowBox.notifyFlowContinue();
    }

    public static SharedPreferences getPreference(Context context){

        return context.getSharedPreferences("SAFE", Context.MODE_PRIVATE);

    }

}

