package br.com.leroymerlin.WebService;

import android.util.Log;

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
import br.com.leroymerlin.model.DemarcaConhecida;
import br.com.leroymerlin.model.FaturamentoPendente;

/**
 * Created by PDA on 02/10/2017.
 */

public class WebServiceSoapDemarcaConhecida {

    //private static String URL = "http://179.184.159.52/wshomol/auditoria.asmx";
    private static String URL = "http://10.56.96.86/wshomol/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetListaDemarcaConhecida";
    private static String SOAP_ACTION_POST="http://tempuri.org/SetDemarcaConhecidaJustificar";

    private static String NAMESPACE = "http://tempuri.org/";


    public static List<DemarcaConhecida> GetMargemConhecida(int codFilial) {
        SoapObject response;
        List<DemarcaConhecida> lista = new ArrayList<>();

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

            androidHttpTransport.call(SOAP_ACTION + METHOD_NAME, envelope);

            response = (SoapObject) envelope.bodyIn;


            for (int i = 0; i < response.getPropertyCount(); i++) {
                SoapObject soap = (SoapObject) response.getProperty(i);
                for (int j = 0; j < soap.getPropertyCount(); j++) {
                    DemarcaConhecida d = new DemarcaConhecida();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    d.setRegional(item.getPropertyAsString("Regional"));
                    d.setCodigoFilial(Integer.parseInt(item.getPropertyAsString("CodigoFilial")));
                    d.setCodigoProduto(item.getPropertyAsString("CodigoProduto"));
                    d.setFilial(item.getPropertyAsString("Filial"));
                    d.setNumNota(item.getPropertyAsString("NumNota"));
                    d.setSerie(item.getPropertyAsString("Serie"));
                    d.setDescricao(item.getPropertyAsString("Descricao"));
                    d.setQuantidade(item.getPropertyAsString("Quantidade"));
                    d.setValorTotal(item.getPropertyAsString("ValorTotal"));
                    d.setDtAtuest(item.getPropertyAsString("DtAtuest"));
//                    d.setNomeUser(item.getPropertyAsString("NomeUser"));
                    d.setNomeUser("Não informado");
                    d.setJustificativa(item.getPropertyAsString("Justificativa"));
                    d.setDataInicio(item.getPropertyAsString("DataInicio"));
                    d.setDataFim(item.getPropertyAsString("DataFim"));
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

    public static boolean postJustificativa(DemarcaConhecida d) {

        // Create request
        String error = "";
        SoapObject request = new SoapObject(NAMESPACE, "SetCancelamentoJustificar");
        try {

            PropertyInfo propertyCod = new PropertyInfo();
            PropertyInfo propertyJus = new PropertyInfo();

            propertyCod.setName("codigo");
            propertyCod.setValue(d.getCod());
            propertyCod.setType(PropertyInfo.INTEGER_CLASS);

            request.addProperty(propertyCod);

            propertyJus.setName("justificativa");
            propertyJus.setValue(d.getJustificativa());
            propertyJus.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(propertyJus);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.implicitTypes = true;
            envelope.dotNet = true;
            envelope.setOutputSoapObject(request);

            MarshalFloat md = new MarshalFloat();
            md.register(envelope);

            MarshalDate mdDate = new MarshalDate();
            mdDate.register(envelope);

            HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

            androidHttpTransport.call(SOAP_ACTION_POST, envelope);

            error = envelope.getResponse().toString();

            Log.w("error", error.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        //Return booleam to calling object
        return true;
    }
}
