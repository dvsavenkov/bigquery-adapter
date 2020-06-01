/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.aphexteam.magazus.bigquery;

import com.google.cloud.Service;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author dvsavenkov
 */
public interface Connection{
    
   /**
   * @param type
   * @param key
   * @param projectId
   * @return
   * @throws FileNotFoundException
   * @throws IOException
   */
  public Service getService(String type, String key, String projectId) throws FileNotFoundException, IOException;
    
    
}
