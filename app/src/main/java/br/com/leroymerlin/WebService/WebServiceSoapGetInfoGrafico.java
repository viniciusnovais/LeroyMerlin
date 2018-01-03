package br.com.leroymerlin.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.model.Grafico;

/**
 * Created by PDA on 01/09/2017.
 */

public class WebServiceSoapGetInfoGrafico {

    //private static String URL = "http://179.184.159.52/wshomol/auditoria.asmx";
    private static String URL = "http://10.56.96.86/wshomol/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetGrafico";

    private static String NAMESPACE = "http://tempuri.org/";

    public static List<Grafico> infoGrafico(int tipo, int codFilial) {
        SoapObject response;
        List<Grafico> lista = new ArrayList<>();
        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo piCod = new PropertyInfo();
        PropertyInfo piTipo = new PropertyInfo();

        piCod.setName("codFil");
        piCod.setValue(codFilial);
        piCod.setType(PropertyInfo.INTEGER_CLASS);
        request.addProperty(piCod);

        piTipo.setName("Tipo");
        piTipo.setValue(tipo);
        piTipo.setType(PropertyInfo.INTEGER_CLASS);
        request.addProperty(piTipo);

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
            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) response.getProperty(i);
                for (int j = 0; j < soap.getPropertyCount(); j++) {
                    Grafico g = new Grafico();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    g.setDescricao(item.getProperty("Descricao").toString());
                    g.setValor(Integer.parseInt(item.getProperty("Valor").toString()));
                    g.setAno(Integer.parseInt(item.getProperty("Ano").toString()));
                    g.setDia(Integer.parseInt(item.getProperty("Dia").toString()));
                    g.setMes(Integer.parseInt(item.getProperty("Mes").toString()));
                    g.setSemana(Integer.parseInt(item.getProperty("Semana").toString()));
                    g.setTipoGrafico(i);
                    lista.add(g);
                }
            }

        } catch (Exception e) {
            //Assign Error Status true in static variable 'errored'
            //LoginActivity.errored = true;
            e.printStackTrace();
        }
        //Return booleam to calling object
        return lista;

    }
}
