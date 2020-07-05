package br.sofex.com.jsoncep2.CEP.CEPListener;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import br.sofex.com.jsoncep2.CEP.EntidadeAdapter.EnderecoRequest;
import br.sofex.com.jsoncep2.MainActivity;

/**
 * Created by viniciusthiengo on 03/01/17.
 */

public class ZipCodeListener implements TextWatcher {
    private Context context;
    Activity activity;

    public ZipCodeListener(Context context ){
        this.context = context;
        this.activity = (Activity) context;
    }


    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

    @Override
    public void afterTextChanged(Editable editable) {
        String zipCode = editable.toString();

        if( editable.length() == 8 ){
            new EnderecoRequest( (MainActivity) context ).execute();
        }
    }
}
