package br.com.leroymerlin.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.leroymerlin.Activity.LoginActivity;

/**
 * Created by PDA on 24/01/2017.
 */

public class WebServiceSoapLogin {

    //private static String URL="http://10.56.96.86/wspda/inventario.asmx";
    private static String URL="http://192.168.15.22/wsandroid/inventario.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/GetAutenticacao";

    private static String NAMESPACE="http://tempuri.org/";


    public static SoapObject invokeLoginWS(String userName,String passWord) {
        SoapObject response=null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, "GetAutenticacao");
        // Property which holds input parameters
        PropertyInfo unamePI = new PropertyInfo();
        PropertyInfo passPI = new PropertyInfo();
        // Set Username
        unamePI.setName("login_");
        // Set Value
        unamePI.setValue(userName);
        // Set dataType
        unamePI.setType(String.class);
        // Add the property to request object
        request.addProperty(unamePI);
        //Set Password
        passPI.setName("senha_");
        //Set dataType
        passPI.setValue(passWord);
        //Set dataType
        passPI.setType(String.class);
        //Add the property to request object
        request.addProperty(passPI);

        // Create envelope
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION, envelope);
            // Get the response
            response = (SoapObject) envelope.getResponse();

        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            LoginActivity.errored = true;
            e.printStackTrace();
        }
        //Return booleam to calling object
        return response;
    }
}
