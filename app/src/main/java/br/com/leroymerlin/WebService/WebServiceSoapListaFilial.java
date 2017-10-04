package br.com.leroymerlin.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.Activity.LoginActivity;
import br.com.leroymerlin.model.Filial;

/**
 * Created by PDA on 29/09/2017.
 */

public class WebServiceSoapListaFilial {

    private static String URL = "http://179.184.159.52/wshomol/inventario.asmx";
    //private static String URL = "http://10.56.96.86/wshomol/inventario.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetFilial";

    private static String NAMESPACE = "http://tempuri.org/";

    public static List<Filial> getFilial() {
        SoapObject response;
        List<Filial> lista = new ArrayList<>();

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo piCodFilial = new PropertyInfo();

        piCodFilial.setName("codigoFilial");
        piCodFilial.setValue(-1);
        piCodFilial.setType(PropertyInfo.INTEGER_CLASS);

        request.addProperty(piCodFilial);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.implicitTypes = true;
        envelope.setOutputSoapObject(request);


        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {

            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            response = (SoapObject) envelope.bodyIn;

//fazer depois
            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) response.getProperty(i);
                for (int j = 0; j < soap.getPropertyCount(); j++) {
                    Filial f = new Filial();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    f.setCodigo(Integer.parseInt(item.getPropertyAsString("Codigo")));
                    f.setNome(item.getPropertyAsString("Nome"));

                    lista.add(f);
                }
            }


        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            LoginActivity.errored = true;
            e.printStackTrace();
        }
        //Return booleam to calling object
        return lista;

    }
}
