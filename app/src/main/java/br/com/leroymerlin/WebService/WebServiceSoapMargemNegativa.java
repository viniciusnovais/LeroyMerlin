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
import br.com.leroymerlin.model.DemarcaConhecida;
import br.com.leroymerlin.model.FaturamentoPendente;
import br.com.leroymerlin.model.MargemNegativa;

/**
 * Created by PDA on 02/10/2017.
 */

public class WebServiceSoapMargemNegativa {

    private static String URL = "http://179.184.159.52/wshomol/auditoria.asmx";
    //private static String URL = "http://10.56.96.86/wshomol/auditoria.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/";

    private static String METHOD_NAME = "GetListaMargemNegativa";

    private static String NAMESPACE = "http://tempuri.org/";


    public static List<MargemNegativa> GetMargemNegativa(int codFilial) {
        SoapObject response;
        List<MargemNegativa> lista = new ArrayList<>();

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
                    MargemNegativa m = new MargemNegativa();
                    SoapObject item = (SoapObject) soap.getProperty(j);
                    m.setRegional(item.getPrimitivePropertyAsString("Regional"));
                    m.setCodigoFilial(Integer.parseInt(item.getPrimitivePropertyAsString("CodigoFilial")));
                    m.setFilial(item.getPrimitivePropertyAsString("Filial"));
                    m.setCodigoProduto(item.getPrimitivePropertyAsString("CodigoProduto"));
                    m.setPrecoVenda(item.getPrimitivePropertyAsString("PrecoVenda"));
                    m.setDescricao(item.getPrimitivePropertyAsString("Descricao"));
                    m.setQuantidade(item.getPrimitivePropertyAsString("Quantidade"));
                    m.setCusto(item.getPrimitivePropertyAsString("Custo"));
                    m.setDtAtuest(item.getPropertyAsString("DtAtuest"));
                    m.setEncargos(item.getPrimitivePropertyAsString("Encargos"));
                    m.setDataInicio(item.getPrimitivePropertyAsString("DataInicio"));
                    m.setDataFim(item.getPrimitivePropertyAsString("DataFim"));
                    m.setMargem(item.getPrimitivePropertyAsString("Margem"));
                    lista.add(m);
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
