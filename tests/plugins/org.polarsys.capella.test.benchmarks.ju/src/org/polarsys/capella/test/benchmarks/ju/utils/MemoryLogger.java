/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.benchmarks.ju.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * A utility class that allows to log the used memory for benchmark tests and write the cumulated results to a file.
 *
 */
public class MemoryLogger {

  private static MemoryLogger singleton;

  protected Map<String, Long> usedMemoryInfo;

  public static MemoryLogger getInstance() {
    if (singleton == null) {
      singleton = new MemoryLogger();
    }
    return singleton;
  }

  private MemoryLogger() {
    usedMemoryInfo = new HashMap<>();
  }

  public void log(String testCaseName, Long usedMemory) {
    usedMemoryInfo.put(testCaseName, usedMemory);
  }

  public void clear() {
    usedMemoryInfo.clear();
  }

  public void saveToFile(String fileNamePath) {

    try (Writer writer = new BufferedWriter(
        new OutputStreamWriter(new FileOutputStream(getMemoryFile(fileNamePath), true), "utf-8"))) {

      for (Map.Entry<String, Long> entry : usedMemoryInfo.entrySet()) {
        String testName = entry.getKey();
        Long memoryValue = entry.getValue();

        writer.write(testName + "," + memoryValue + " MB \n");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  protected static File getMemoryFile(String fileNamePath) {
    File memoryFile = new File(fileNamePath);

    if (!memoryFile.getParentFile().exists()) {
      memoryFile.getParentFile().mkdirs();
    }

    if (!memoryFile.exists()) {
      try {
        memoryFile.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return memoryFile;
  }


}
