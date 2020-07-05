package br.sofex.com.jsoncep2.CEP.CepFIndIDs;

import android.app.Activity;

/**
 * Created by viniciusthiengo on 02/01/17.
 */

public class AutoFindID {
    private Activity activity;
    private int[] ids;

    public AutoFindID(Activity activity, int... ids ){
        this.activity = activity;
        this.ids = ids;
    }

    public void lockFields( boolean isToLock ){
        for( int id : ids ){
            setLockField( id, isToLock );
        }
    }

    private void setLockField( int fieldId, boolean isToLock ){
        activity.findViewById( fieldId ).setEnabled( !isToLock );
    }
}
