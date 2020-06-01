/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.aphexteam.magazus.bigquery;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryError;
import com.google.cloud.bigquery.FieldValueList;
import com.google.cloud.bigquery.InsertAllRequest;
import com.google.cloud.bigquery.InsertAllRequest.Builder;
import com.google.cloud.bigquery.InsertAllResponse;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.TableId;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author dvsavenkov
 */
public class OperationImpl implements Operation{
   
  
  @Override
  public void insertAllPaged(BigQuery bigquery, String projectId, String datasetId, String _tableId, List<Map<String, Object>> rowList) throws IOException, GeneralSecurityException {
    int maxRows = rowList.size();
    int pageLimit = 1000;
    int pages = (int) Math.ceil((double) maxRows / (double) 1000);
    
    for(int page = 0; page < pages; page++) {
      List<Map<String, Object>> pageRows = new ArrayList<>();
      //System.out.println("page = " + page);
      //System.out.println("k = " + page * pageLimit + "; k < " + ((page * pageLimit + pageLimit) > maxRows ? maxRows : (page * pageLimit + pageLimit)));
      for(int k = page * pageLimit; k < ((page * pageLimit + pageLimit) > maxRows ? maxRows : (page * pageLimit + pageLimit)); k++) {
        pageRows.add(rowList.get(k));
      }      
      insertAll(bigquery, projectId, datasetId, _tableId, pageRows);
    }
  }
  
  /**
   *
   * @param bigquery
   * @param projectId
   * @param datasetId
   * @param _tableId
   * @param rowList
   * @throws IOException
   * @throws GeneralSecurityException
   */
  @Override
  public void insertAll(BigQuery bigquery, String projectId, String datasetId, String _tableId, List<Map<String, Object>> rowList) throws IOException, GeneralSecurityException {
    TableId tableId = TableId.of(datasetId, _tableId);
    
    // Create an insert request
    Builder insertRequestBuilder = InsertAllRequest.newBuilder(tableId);      
    rowList.forEach((row) -> {
      insertRequestBuilder.addRow(row);
    });      
    InsertAllRequest insertRequest = insertRequestBuilder.build();      
    // Insert rows
    InsertAllResponse insertResponse = bigquery.insertAll(insertRequest);
    // Check if errors occurred
    if (insertResponse.hasErrors()) {
      System.out.println("Errors occurred while inserting rows" );
      for(Map.Entry<Long, List<BigQueryError>> errors : insertResponse.getInsertErrors().entrySet()){
        for(BigQueryError error : errors.getValue()) {
          System.out.println("Message = " + error.getMessage() 
                  +", Cause = "+ error.getReason() + ", Location = " 
                  + error.getLocation());
        }
      }
    }
  }    

  @Override
  public List<Map<Integer, String>> getAll(BigQuery bigquery, String query) throws TimeoutException, InterruptedException, IOException{
    List<Map<Integer, String>> rows = new ArrayList<>();
    // Create a query request
    QueryJobConfiguration queryConfig =
        QueryJobConfiguration.newBuilder(query).build();
    // Read rows
    for (FieldValueList row : bigquery.query(queryConfig).iterateAll()) {      
      System.out.println(row.toString());
    }
    return rows;
  }
    
}
