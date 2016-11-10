package android.projeto.com.br.appservicedesk.util;

import java.util.HashMap;

/**
 * Created by caiquecoelho on 10/23/16.
 */
public class StatusHandler {

    public static HashMap<Integer,String> status;

    static{
        status = new HashMap<>();
        status.put(1,"Aberto");
        status.put(2,"Fechado");
    }
}
