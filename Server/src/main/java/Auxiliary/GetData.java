package Auxiliary;

import org.postgresql.jdbc2.ArrayAssistant;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetData {
    public static Map<String, ArrayList<String>> getData(ResultSet table){
        Map<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>();
        try{
            while (table.next()) {
                for (int i = 1; i <= table.getMetaData().getColumnCount(); i++) {
                    String key = table.getMetaData().getColumnName(i);
                    String value = table.getString(i);
                    if (data.get(key) == null){
                        ArrayList<String> t = new ArrayList<String>();
                        t.add(value);
                        data.put(key, t);
                        continue;
                    }
                    data.get(key).add(value);
                }
            }
        } catch (Exception e){

        }
        return data;
    }
}
