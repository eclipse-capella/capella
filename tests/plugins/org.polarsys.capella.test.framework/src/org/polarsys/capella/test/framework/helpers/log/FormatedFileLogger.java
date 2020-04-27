/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.helpers.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Erwan Brottier
 */
public class FormatedFileLogger extends FormatedBufferLogger {

  public FormatedFileLogger() {
  	super();
	}

	
  public FormatedFileLogger(String indentString) {
		super(indentString);
	}

	public void saveInFile(File file, boolean concatenateData) {
    String data = buffer.toString();
  	if (concatenateData && file.exists()) {
  		String oldData = readFileAsString(file);
  		data = oldData + '\n' + data;
  	}
  	writeStringInFile(file, data);
  }

  private String readFileAsString(File file) {
    StringBuffer fileData = new StringBuffer();
    try {
      BufferedReader reader = new BufferedReader(new FileReader(file));
      char[] buf = new char[1024];
      int numRead = 0;
      while ((numRead = reader.read(buf)) != -1) {
        String readData = String.valueOf(buf, 0, numRead);
        fileData.append(readData);
      }
      reader.close();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return fileData.toString();
  }
  
  private void writeStringInFile(File file, String data) {
    try {
      FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
      fileWriter.write(data);
      fileWriter.close();
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }
}
