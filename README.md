# bigquery-adapter

typical calls:

<code>
    Operation q = new OperationImpl();        
    List<Map<String, Object>> rows = new ArrayList<>();
    
    for(int i = 0; i < revisionList.size(); i++) {
      Map<String, Object> row = new HashMap<>();        
      row.put("key", "value");
      ....
      rows.add(row);
    }
    
    BigQuery bq = (BigQuery) (new ConnectionImpl()).getService("BigQuery", 
            "fim-team-204614-xxxxxxxxxxxx.json",
            "fim-team-204614");     

    q.insertAllPaged(bq, "fim-team-204614", 
            "price_monitoring", 
            "yandex", 
            rows);
 </code>
