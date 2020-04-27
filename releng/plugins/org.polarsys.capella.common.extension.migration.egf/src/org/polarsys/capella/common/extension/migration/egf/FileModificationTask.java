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
package org.polarsys.capella.common.extension.migration.egf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;

/**
 * 
 */
public class FileModificationTask implements ITaskProduction {

	public void doExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {

		String filePathString = productionContext.getInputValue("filePath", String.class);
		String toReplaceString = productionContext.getInputValue("toReplace", String.class);
		String replacementString = productionContext.getInputValue("replacement", String.class);

		IFile iFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(filePathString));
        if (iFile == null || !iFile.exists()) {
        	System.out.println("file " + filePathString + " doesn't exist (skipping).");
        	return;
        }

		doExecute(iFile, toReplaceString, replacementString);
	}

	public void doExecute(IFile iFile, String toReplaceString,
			String replacementString) throws InvocationException {
		File file = iFile.getRawLocation().toFile();
		
		try {
			String fileContent = readFile(file);
			String newContent = fileContent.replaceAll(toReplaceString, replacementString);
			writeFile(file, newContent);
		} catch (IOException e) {
			throw new InvocationException(e.getMessage());
		}
	}

	protected String readFile(File file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		StringBuilder stringBuilder = new StringBuilder();
		char[] buffer = new char[1024];
		int numChars;
		try {
			while ((numChars = reader.read(buffer)) != -1){
				stringBuilder.append(buffer, 0, numChars);
			}
		} finally {
			reader.close();
		}
		return stringBuilder.toString();
	}

	protected void writeFile(File file, String text) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(text);
		writer.close();		
	}

	public void postExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}

	public void preExecute(ITaskProductionContext productionContext,
			IProgressMonitor monitor) throws InvocationException {
	}
}
