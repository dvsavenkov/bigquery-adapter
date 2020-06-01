/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.aphexteam.magazus.bigquery;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.TableId;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author dvsavenkov
 */
public interface Operation {
    
    /**
      * 
      * @param bigquery
      * @param projectId
      * @param datasetId
      * @param rowList
      * @param _tableId 
      * @throws java.security.GeneralSecurityException 
      * @throws java.io.IOException 
     */
    public void insertAll(BigQuery bigquery, String projectId, String datasetId, String _tableId, List<Map<String, Object>> rowList) throws IOException, GeneralSecurityException;
    
    public void insertAllPaged(BigQuery bigquery, String projectId, String datasetId, String _tableId, List<Map<String, Object>> rowList) throws IOException, GeneralSecurityException;

    /**
      * @param bigquery     
      * @param query
      * @return 
      * @throws java.util.concurrent.TimeoutException 
      * @throws java.lang.InterruptedException 
      * @throws java.io.IOException 
     */
    public List<Map<Integer, String>> getAll(BigQuery bigquery, String query) throws TimeoutException, InterruptedException, IOException;
    
    
    
}
