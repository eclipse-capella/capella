/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.detachment.version.precondition.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.capella.detachment.version.precondition.Activator;
import org.polarsys.capella.detachment.version.precondition.util.SAXModelsElementsParser;
import org.polarsys.kitalpha.model.common.precondition.interfaces.IPrecondition;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;


public class VersionChecker implements IPrecondition<IFile> {

	@Override
	public void executePrecondition(IFile param) {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		parserFactory.setNamespaceAware(false);
		InputSource is;
		try {
			SAXModelsElementsParser modelsEltParser = new SAXModelsElementsParser();
			SAXParser saxParser = parserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(modelsEltParser);
			is = new InputSource();
			is.setByteStream(param.getContents());
			xmlReader.parse(is);
			
			Collection<String> capellaModellers = modelsEltParser.getCapellaModellers();
			Collection<URI> capellaModellersURIs = new HashSet<URI>();

			String projectName = param.getProject().getName();
			//Create uris
			for (String refrencedModel : capellaModellers) {
				//workspace resources
				if (refrencedModel.startsWith("platform:/resource/")){ //$NON-NLS-1$
					URI platformResourceURI = URI.createURI(refrencedModel, true);
					capellaModellersURIs.add(platformResourceURI);
				}
				
				//the current model
				if (!refrencedModel.isEmpty() && !refrencedModel.startsWith("platform:/resource/")){ //$NON-NLS-1$
					URI plateformResourceURI = URI.createPlatformResourceURI(projectName + "/" + refrencedModel, true);
					capellaModellersURIs.add(plateformResourceURI);
				}
			}
			
			String capellaVersion = FeatureHelper.getCapellaVersion(false);
			final StringBuffer exceptionMsg = new StringBuffer();
			
			exceptionMsg.append("The model needs to be migrated: Capella version is ").append(capellaVersion).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
			exceptionMsg.append("Model version:\n"); //$NON-NLS-1$
			boolean launchException = false;
			
			for (URI uri : capellaModellersURIs) {
				String projectId = uri.segment(1);
				String modelName = uri.lastSegment();
				
				if (uri.isPlatformResource()){
					IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectId);
					IFile file = null;
					if (uri.toString().endsWith("melodyfragment")){
						IFolder folderFragments = project.getFolder("fragments");
						file = folderFragments.getFile(modelName);
					} else {
						file =	project.getFile(modelName);
					}
					
					String modelVersion = CapellaFeatureHelper.getDetectedVersion(file);
					if (capellaVersion != null && modelVersion != null && !capellaVersion.isEmpty() && !modelVersion.isEmpty()){
					  // check only major and minor version, micro (patch) is ignored
						if (!modelVersion.startsWith(capellaVersion.substring(0, 3))){
							exceptionMsg.append("\t").append(uri.toString()).append(" - version: ").append(modelVersion).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							launchException = true;
						}
					}
				}
			}
			
			//FIXME use precondition exception, and catch it in the run method
			if (launchException){
				Display.getDefault().syncExec(new Runnable() {
					@Override
					public void run() {
						RuntimeException e = new RuntimeException(exceptionMsg.toString());
						final Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
						IStatus status = new Status(IStatus.ERROR, Activator.getContext().getBundle().getSymbolicName(), exceptionMsg.toString(), null);
						ErrorDialog.openError(shell, "Detachment Error", exceptionMsg.toString(), status);
						throw e;
					}
				});
			}
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		} catch (SAXException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
