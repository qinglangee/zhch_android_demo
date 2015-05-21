package com.zhch.andex.runtimestate;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.zhch.andex.R;
import com.zhch.andex.common.activity.BaseAct;
import com.zhch.andex.runtimestate.StateFrag.MyDataObject;

public class StateAct extends BaseAct{

    private StateFrag dataFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.runtimestate_state_act);

        // find the retained fragment on activity restarts
        FragmentManager fm = getFragmentManager();
        dataFragment = (StateFrag) fm.findFragmentByTag("data");

        // create the fragment and data the first time
        if (dataFragment == null) {
            // add the fragment
            dataFragment = new StateFrag();
            fm.beginTransaction().add(dataFragment, "data").commit();
            // load the data from the web
            dataFragment.setData(loadMyData());
        }

        // the data is available in dataFragment.getData()
        // ...
    }

    private MyDataObject loadMyData() {
    	MyDataObject data = dataFragment.new MyDataObject();
		return data;
	}

	@Override
    public void onDestroy() {
        super.onDestroy();
        // store the data in the fragment
        dataFragment.setData(collectMyLoadedData());
    }

	private MyDataObject collectMyLoadedData() {
		MyDataObject data = dataFragment.new MyDataObject();
		// do some thing
		return data;
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	        Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	        Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
	    }
	}

}
