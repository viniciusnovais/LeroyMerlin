package br.com.leroymerlin.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.leroymerlin.Activity.LoginActivity;

/**
 * Created by PDA on 01/09/2017.
 */

public class WebServiceSoapGetInfoGrafico {

    private static String URL = "http://10.56.96.86/wsauditoria/Auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetInfoGrafico";

    private static String NAMESPACE = "http://tempuri.org/";

    public static SoapObject infoGrafico(int cod) {
        SoapObject response = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo piCod = new PropertyInfo();

        piCod.setName("codigo");

        piCod.setValue(cod);

        piCod.setType(PropertyInfo.INTEGER_CLASS);

        request.addProperty(piCod);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            // Get the response
            response = (SoapObject) envelope.getResponse();
        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            //LoginActivity.errored = true;
            e.printStackTrace();
        }
        //Return booleam to calling object
        return response;

    }
}
