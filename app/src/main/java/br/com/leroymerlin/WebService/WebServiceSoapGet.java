package br.com.leroymerlin.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import br.com.leroymerlin.Activity.LoginActivity;

/**
 * Created by PDA on 27/01/2017.
 */

public class WebServiceSoapGet {

    //private static String URL="http://10.56.96.86/wshomol/auditoria.asmx";
    private static String URL = "http://179.184.159.52/wshomol/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetDashboard";
    private static String METHOD_NAME2 = "EventosAbertoNivel2";

    private static String NAMESPACE = "http://tempuri.org/";


    public static SoapObject EventoAberto(int codUsuario, int codDashBoard) {
        SoapObject response = null;

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo piCodUsuario = new PropertyInfo();
        PropertyInfo piCodDashBoard = new PropertyInfo();

        piCodUsuario.setName("codigoUsuario");
        piCodUsuario.setValue(codUsuario);
        piCodUsuario.setType(PropertyInfo.INTEGER_CLASS);

        request.addProperty(piCodUsuario);

        piCodDashBoard.setName("codigoDashboad");
        piCodDashBoard.setValue(codDashBoard);
        piCodDashBoard.setType(PropertyInfo.INTEGER_CLASS);

        request.addProperty(piCodDashBoard);


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
            LoginActivity.errored = true;
            e.printStackTrace();
        }
        //Return booleam to calling object
        return response;

    }

    public static SoapObject EventoAbertoNivel2(String v) {
        SoapObject response = null;
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME2);

        PropertyInfo valor = new PropertyInfo();

        valor.setName("_codigo");
        // Set Value
        valor.setValue(v);

        valor.setType(String.class);

        request.addProperty(valor);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);

        envelope.dotNet = true;
        // Set output SOAP object
        envelope.setOutputSoapObject(request);
        // Create HTTP call object
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

        try {
            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME2, envelope);
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
