package br.sofex.com.jsoncep2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import br.sofex.com.jsoncep2.Biblioteca.Lib;
import br.sofex.com.jsoncep2.CEP.Entidade.Endereco;
import br.sofex.com.jsoncep2.CEP.CepFIndIDs.AutoFindID;
import br.sofex.com.jsoncep2.CEP.CEPListener.ZipCodeListener;

public class MainActivity extends AppCompatActivity {

    private EditText CEP;
    Spinner sp_state;
    private AutoFindID autoFindID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Lib lib = new Lib(MainActivity.this);
        sp_state = findViewById(R.id.sp_state);
        lib.ComboBox(ListEstados(),sp_state,0);

        CEP =  findViewById(R.id.et_zip_code);
        CEP.addTextChangedListener( new ZipCodeListener( this ) );

        autoFindID = new AutoFindID(this,
                R.id.et_zip_code,
                R.id.et_street,
                R.id.et_complement,
                R.id.et_neighbor,
                R.id.et_city,
                R.id.sp_state,
                R.id.et_number);

    }

    public List<String> ListEstados(){
        List<String> list = new ArrayList<>();
        list.add("*Estado");
        list.add("Acre (AC)");
        list.add("Alagoas (AL)");
        list.add("Amapá (AP)");
        list.add("Amazonas (AM)");
        list.add("Bahia (BA)");
        list.add("Ceará (CE)");
        list.add("Distrito Federal (DF)");
        list.add("Espírito Santo (ES)");
        list.add("Goiás (GO)");
        list.add("Maranhão (MA)");
        list.add("Mato Grosso (MT)");
        list.add("Mato Grosso do Sul (MS)");
        list.add("Minas Gerais (MG)");
        list.add("Pará (PA)");
        list.add("Paraíba (PB)");
        list.add("Paraná (PR)");
        list.add("Pernambuco (PE)");
        list.add("Piauí (PI)");
        list.add("Rio de Janeiro (RJ)");
        list.add("Rio Grande do Norte (RN)");
        list.add("Rio Grande do Sul (RS)");
        list.add("Rondônia (RO)");
        list.add("Roraima (RR)");
        list.add("Santa Catarina (SC)");
        list.add("São Paulo (SP)");
        list.add("Sergipe (SE)");
        list.add("Tocantins (TO)");
        
        return list;
    }

    public String getUriZipCode(){
        return "https://viacep.com.br/ws/"+CEP.getText()+"/json/";
    }

    public void lockFields( boolean isToLock ){
        autoFindID.lockFields( isToLock );
    }

    public void setDataViews(Endereco endereco){
        setField( R.id.et_street, endereco.getLogradouro() );
        setField( R.id.et_complement, endereco.getComplemento() );
        setField( R.id.et_neighbor, endereco.getBairro() );
        setField( R.id.et_city, endereco.getLocalidade() );
        setSpinner( R.id.sp_state, R.array.states, endereco.getUf() );
    }

    private void setField( int id, String data ){
        ((EditText) findViewById(id)).setText( data );
    }

    private void setSpinner( int id, int arrayId, String data ){
        String[] itens = getResources().getStringArray(arrayId);

        for( int i = 0; i < itens.length; i++ ){

            if( itens[i].endsWith( "("+data+")" ) ){
                ((Spinner) findViewById(id)).setSelection( i );
                return;
            }
        }
        ((Spinner) findViewById(id)).setSelection( 0 );
    }

}