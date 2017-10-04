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
import br.com.leroymerlin.model.FaturamentoPendente;

/**
 * Created by PDA on 02/10/2017.
 */

public class WebServiceSoapFaturamentoPendente {

    private static String URL = "http://179.184.159.52/wshomol/auditoria.asmx";
    //private static String URL = "http://10.56.96.86/wshomol/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetListaFaturamentoPendente";

    private static String NAMESPACE = "http://tempuri.org/";


    public static List<FaturamentoPendente> GetListaFaturamentoPendente(int codFilial) {
        SoapObject response;
        List<FaturamentoPendente> lista = new ArrayList<>();

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
                    FaturamentoPendente f = new FaturamentoPendente();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    //f.setCod(Float.parseFloat(item.getPrimitivePropertyAsString("Codigo")));
                    f.setRegional(item.getPrimitivePropertyAsString("Regional"));
                    f.setCodigoFilial(Integer.parseInt(item.getPrimitivePropertyAsString("CodigoFilial")));
                    f.setFilial(item.getPrimitivePropertyAsString("Filial"));
                    f.setPedido(item.getPrimitivePropertyAsString("Pedido"));
                    f.setNomeCliente(item.getPrimitivePropertyAsString("NomeCliente"));
                    f.setDataPedido(item.getPrimitivePropertyAsString("DataPedido"));
                    f.setDataEntrega(item.getPrimitivePropertyAsString("DataEntrega"));
                    f.setValorTotal(item.getPrimitivePropertyAsString("ValorTotal"));
                    f.setValorParcial(item.getPrimitivePropertyAsString("ValorParcial"));
                    f.setNumCaixa(Integer.parseInt(item.getPrimitivePropertyAsString("NumCaixa")));
                    f.setDataInicio(item.getPrimitivePropertyAsString("DataInicio"));
                    f.setDataFim(item.getPrimitivePropertyAsString("DataFim"));
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

    public static boolean postJustificativa(FaturamentoPendente param) {

        return false;
    }
}
