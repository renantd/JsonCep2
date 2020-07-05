package br.sofex.com.jsoncep2.CEP.EntidadeAdapter;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;

import br.sofex.com.jsoncep2.CEP.Json.JsonRequest;
import br.sofex.com.jsoncep2.CEP.Entidade.Endereco;
import br.sofex.com.jsoncep2.MainActivity;

/**
 * Created by viniciusthiengo on 03/01/17.
 */

public class EnderecoRequest extends AsyncTask<Void, Void, Endereco> {

    private WeakReference<MainActivity> activity;
    public EnderecoRequest(MainActivity activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if( activity.get() != null ){
            activity.get().lockFields( true );
        }
    }

    @Override
    protected Endereco doInBackground(Void... voids) {
        try {
            String jsonString = JsonRequest.request( activity.get().getUriZipCode() );

            Gson gson = new Gson();
            return gson.fromJson( jsonString, Endereco.class );

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Endereco endereco) {
        super.onPostExecute(endereco);

        if( activity.get() != null ){
            activity.get().lockFields( false );

            if( endereco != null ){
                activity.get().setDataViews(endereco);
            }
        }
    }
}
