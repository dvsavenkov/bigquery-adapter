/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.aphexteam.magazus.bigquery;

import com.google.cloud.bigquery.BigQuery;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author dvsavenkov
 */
public class Test {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.util.concurrent.TimeoutException
     * @throws java.lang.InterruptedException
     * @throws java.security.GeneralSecurityException
     */
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException, GeneralSecurityException {
        Operation q = new OperationImpl();        
        List<Map<String, Object>> rows1 = new ArrayList<>();
        
        for(int i = 0; i < 980999; i++) {
          Map<String, Object> row1 = new HashMap<>();        
          row1.put("Model_name", "Model_name" + i);
          row1.put("model_code", "model_code" + i);
          row1.put("Region", "Region" + i);
          row1.put("Shop_name", "Shop_name" + i);
          row1.put("Shop_price", 1000F);
          row1.put("rrc", 1000F);
          row1.put("Date", null);
          rows1.add(row1);
        }
        
        BigQuery bq = (BigQuery) (new ConnectionImpl()).getService("BigQuery", 
                "fim-team-204614-451ce8dcc256.json",
                "fim-team-204614");     

        q.insertAllPaged(bq, "fim-team-204614", 
                "price_monitoring", 
                "yandex_copy", 
                rows1);
        
        /*List<Map<Integer, String>> rows = q.getAll(bq,
               "SELECT * FROM price_monitoring.yandex_copy");*/
       
        /*for(Map<Integer, String> row : rows) {
          for(Integer index : row.keySet()){
           System.out.println(row.get(index));
          }
        }*/
        
    }
    
}
