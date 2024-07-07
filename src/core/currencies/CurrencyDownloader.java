package core.currencies;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class CurrencyDownloader {
    public static final String apiUrl="http://api.nbp.pl/api/exchangerates/tables/A/?format=json";
    private String currencyValuesDate;
    private JSONArray rates;
    public void startDownload(){
        try{
            URL url=new URL(apiUrl);
            JSONParser parser=new JSONParser();
            JSONArray jsonArray=(JSONArray) parser.parse(new InputStreamReader(url.openStream())); //parse jsonArray
            JSONObject jsonObject= (JSONObject) jsonArray.get(0);
            currencyValuesDate=setDate(jsonObject);
            rates=setRates(jsonObject);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    private String setDate(JSONObject jsonObject){
        return (String)jsonObject.get("effectiveDate"); //cast effectiveDate as String
    }
    private JSONArray setRates(JSONObject jsonObject){
        return (JSONArray) jsonObject.get("rates"); //cast rates as JSONArray
    }
    public String getCurrencyValuesDate() {
        return currencyValuesDate;
    }
    public JSONArray getRates() {
        return rates;
    }
}
