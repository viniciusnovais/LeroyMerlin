package br.com.leroymerlin.WebService;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.Activity.LoginActivity;
import br.com.leroymerlin.model.Cancelamento;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 18/09/2017.
 */

public class WebServiceSoapCancelamento {

    private static String URL = "http://192.168.15.22/wsandroid/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetListaCancelamento";

    private static String NAMESPACE = "http://tempuri.org/";

    private static String SOAP_ACTION_POST = "http://tempuri.org/SetCancelamentoJustificar ";


    public static List<Cancelamento> GetListaCancelamento(int codFilial) {
        SoapObject response;
        List<Cancelamento> lista = new ArrayList<>();

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        PropertyInfo piCodFilial = new PropertyInfo();

        piCodFilial.setName("codfil");
        piCodFilial.setValue(codFilial);
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

            MarshalDate date = new MarshalDate();
            date.register(envelope);

            MarshalFloat marFloat = new MarshalFloat();
            marFloat.register(envelope);

            // Invoke web service
            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);
            // Get the response
            //res = envelope.getResponse();
            response = (SoapObject) envelope.bodyIn;


            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) response.getProperty(i);
                for (int j = 0; j < soap.getPropertyCount(); j++) {
                    Cancelamento c = new Cancelamento();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    c.setCodigoFilial(Integer.parseInt(item.getProperty("CodigoFilial").toString()));
                    c.setFilial(item.getProperty("Filial").toString());
                    c.setRegional(item.getProperty("Regional").toString());
                    c.setNumNota(Float.parseFloat(item.getProperty("NumNota").toString()));
                    c.setSerie(item.getProperty("Serie").toString());
                    c.setSecao(item.getProperty("Secao").toString());
                    c.setJustificativa(item.getProperty("Justificativa").toString());
                    c.setDatNota(item.getProperty("DataNota").toString());
                    c.setDataCancelamento(item.getProperty("DataCancelamento").toString());
                    c.setUsuarioCancel(item.getProperty("UsuarioCancel").toString());
                    c.setCodigoCondicao(Integer.parseInt(item.getProperty("CodigoCondicao").toString()));
                    c.setCodigoPagto(item.getProperty("CondicaoPagto").toString());
                    c.setDataDe(item.getProperty("DataDe").toString());
                    c.setDataAte(item.getProperty("DataAte").toString());
                    c.setNumCaixa(Float.parseFloat(item.getProperty("NumNota").toString()));
                    c.setValorTotal(Float.parseFloat(item.getProperty("ValorTotal").toString()));
                    lista.add(c);
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

    public static boolean postJustificativa(Desconto d) {

        // Create request
        SoapObject request = new SoapObject(NAMESPACE, "SetDescontoJustificar");
        try {

            request.addProperty("justificativa", d);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            MarshalFloat md = new MarshalFloat();
            md.register(envelope);

            MarshalDate mdDate = new MarshalDate();
            mdDate.register(envelope);

            envelope.addMapping(NAMESPACE, "justificativa", new Desconto().getClass());
            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION_POST, envelope);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //Return booleam to calling object
        return true;
    }
}
