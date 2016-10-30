package android.projeto.com.br.appservicedesk.initializer;

import android.app.Activity;
import android.content.Context;
import android.projeto.com.br.appservicedesk.enums.StatusConsts;
import android.projeto.com.br.appservicedesk.enums.TiposDeFila;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

/**
 * Created by caiquecoelho on 10/1/16.
 */
public class InitializeSpinner {

    public static Spinner initializeAndSetAdapterSppinerForFila(Spinner spinner, Context context){
        List<TiposDeFila> spinnerArray = Arrays.asList(TiposDeFila.values());
        ArrayAdapter<TiposDeFila> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    public static Spinner initializeAndSetAdapterSppinerForStatus(Spinner spinner, Context context){
        List<StatusConsts> spinnerArray = Arrays.asList(StatusConsts.values());
        ArrayAdapter<StatusConsts> adapter = new ArrayAdapter<>(
                context, android.R.layout.simple_spinner_item, spinnerArray);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }
}
