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
package org.polarsys.capella.detachment.version.precondition.impl;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.capella.detachment.version.precondition.util.SAXModelsElementsParser;
import org.polarsys.kitalpha.model.common.precondition.exception.InvalidPreconditionException;
import org.polarsys.kitalpha.model.common.precondition.interfaces.IPrecondition;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class VersionChecker implements IPrecondition<IFile> {
	
	private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);

	@Override
	public void executePrecondition(IFile param, IProgressMonitor monitor) throws InvalidPreconditionException {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		parserFactory.setNamespaceAware(false);
		InputSource is;
		try {
			String projectName = param.getProject().getName();
			SAXModelsElementsParser modelsEltParser = SAXModelsElementsParser.newInstance(projectName);
			SAXParser saxParser = parserFactory.newSAXParser();
			XMLReader xmlReader = saxParser.getXMLReader();
			xmlReader.setContentHandler(modelsEltParser);
			is = new InputSource();
			is.setByteStream(param.getContents());
			xmlReader.parse(is);
			
			Collection<IFile> capellaModellers = modelsEltParser.getCapellaModellers();
			capellaModellers.add(param);
			
			String capellaVersion = FeatureHelper.getCapellaVersion(false);
			
			Collection<StringBuffer> result = new HashSet<StringBuffer>();
			for (IFile iFile : capellaModellers) {
				String modelVersion = CapellaFeatureHelper.getDetectedVersion(iFile);
				if (modelVersion != null && !modelVersion.isEmpty()){
					if (!modelVersion.startsWith(capellaVersion.substring(0, 3))){
						StringBuffer _msg = new StringBuffer();
						_msg.append(iFile.getName()).
							append(": model version is: ").
							append(modelVersion).
							append(". It needs to be migrated to ").
							append("Capella ").
							append(capellaVersion).
							append(".\n");
						result.add(_msg);
					}
				}
			}
			if (!result.isEmpty()){
				final String _msg = assembleMessage(result);
				throw new InvalidPreconditionException(_msg);
			}
			
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage());
		} catch (SAXException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		} catch (CoreException e) {
			logger.error(e.getMessage());
		}
	}

	private String assembleMessage(Collection<StringBuffer> result) {
		String _msg = "";
		for (StringBuffer m : result) {
			_msg += m.toString();
		}
		return _msg;
	}
}
