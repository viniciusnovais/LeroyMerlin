package br.com.leroymerlin.WebService;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalBase64;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.MarshalHashtable;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import br.com.leroymerlin.Activity.LoginActivity;
import br.com.leroymerlin.Activity.LogisticaActivity;
import br.com.leroymerlin.model.Desconto;

/**
 * Created by PDA on 18/09/2017.
 */

public class WebServiceSoapDesconto {

    private static String URL = "http://192.168.15.22/wsandroid/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetListaDesconto";

    private static String NAMESPACE = "http://tempuri.org/";

    private static String SOAP_ACTION_POST = "http://tempuri.org/SetDescontoJustificar ";


    public static List<Desconto> GetListaDesconto(int codFilial) {
        SoapObject response;
        List<Desconto> lista = new ArrayList<>();

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
                    Desconto d = new Desconto();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    d.setCodigoUsuario(Integer.parseInt(item.getProperty("CodigoUsuario").toString()));
                    d.setCodigoFilial(Integer.parseInt(item.getProperty("CodigoFilial").toString()));
                    d.setFilial(item.getProperty("Filial").toString());
                    d.setRegional(item.getProperty("Regional").toString());
                    d.setNumNota(Float.parseFloat(item.getProperty("NumNota").toString()));
                    d.setSerie(item.getProperty("Serie").toString());
                    d.setSecao(item.getProperty("Secao").toString());
                    d.setCodLm(Float.parseFloat(item.getProperty("CodigoLm").toString()));
                    d.setDescricao(item.getProperty("Descricao").toString());
                    d.setValorNF(Float.parseFloat(item.getProperty("ValorNF").toString()));
                    d.setValorDesconto(Float.parseFloat(item.getProperty("ValorDesconto").toString()));
                    d.setPercentual(Float.parseFloat(item.getProperty("Percentual").toString()));
                    d.setOcorrencia(item.getProperty("Ocorrencia").toString());
                    d.setData(item.getProperty("DtAtuest").toString());
                    d.setJustificativa(item.getProperty("Justificativa").toString());
                    d.setDataInicio(item.getProperty("DataInicio").toString());
                    d.setDataFim(item.getProperty("DataFim").toString());
                    d.setNomeCliente(item.getProperty("NomeCliente").toString());
                    if (item.getProperty("CPFCliente").toString().equals("anyType{}")) {
                        d.setCpfCliente("Não Informado");
                    } else {
                        d.setCpfCliente(item.getProperty("CPFCliente").toString());
                    }
                    lista.add(d);
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
