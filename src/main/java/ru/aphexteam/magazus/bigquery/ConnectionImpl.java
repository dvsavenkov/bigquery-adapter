/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.aphexteam.magazus.bigquery;

import com.google.auth.oauth2.ServiceAccountCredentials;
import com.google.cloud.Service;
import com.google.cloud.bigquery.BigQueryOptions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author dvsavenkov
 */
public final class ConnectionImpl implements Connection{
       
    public Service getService(String type, String key, String projectId) throws FileNotFoundException, IOException {
        if (type.equals("BigQuery")) {
            return BigQueryOptions.newBuilder().setProjectId(projectId)
                .setCredentials(ServiceAccountCredentials
                .fromStream(new FileInputStream(key)))
              .build()
              .getService();
        }
    return (Service) new Object();        
    }
    
}
