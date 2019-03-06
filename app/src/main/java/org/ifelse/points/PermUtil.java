package org.ifelse.points;

import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import org.ifelse.vl.FlowBox;
import org.ifelse.vl.FlowPoint;


/**
 <property name="permissioin" title="权限" type="0" args="" value=""/>
 <property name="result" title="结果(0:1)" type="0" args="" value=""/>
 */

public class PermUtil extends FlowPoint {

    final String key_permission = "permissioin";
    final String key_result = "result";



    //Manifest.permission.READ_EXTERNAL_STORAGE)
    @Override
    public void run(FlowBox flowBox) throws Exception {


        String permission = flowBox.getVarString(params.get(key_permission));

        int result = ContextCompat.checkSelfPermission(flowBox.getContext(),permission)
                == PackageManager.PERMISSION_GRANTED ? 1 : 0;



        flowBox.setValue(params.get(key_result),result);

        flowBox.notifyFlowContinue();
    }
}
