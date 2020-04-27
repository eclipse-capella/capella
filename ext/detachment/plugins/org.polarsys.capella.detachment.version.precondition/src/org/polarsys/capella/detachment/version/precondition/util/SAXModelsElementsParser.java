/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.detachment.version.precondition.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;
import java.util.HashSet;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 */
public class SAXModelsElementsParser extends DefaultHandler {
	
	private static final Logger logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.MODEL);
	
	private static final String HREF_ATTR = "href"; //$NON-NLS-1
	private static final String MODELS_TAG = "models"; //$NON-NLS-1
	
	Collection<IFile> capellaModellers;
	public final IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
	
	private String projectName = null;
	
	public static SAXModelsElementsParser newInstance(String projectName){
		return new SAXModelsElementsParser(projectName);
	}
	
	private SAXModelsElementsParser(String projectName){
		this.projectName = projectName;
	}
	
	public void startDocument() throws SAXException{
		capellaModellers = new HashSet<IFile>();
	}
	
	public void startElement(String namespaceURI, String name, String qname, Attributes attr) throws SAXException {
		if (qname != null && qname.equals(MODELS_TAG)){ 
			String fileModelName = attr.getValue(HREF_ATTR);
			
			if (fileModelName != null && !fileModelName.isEmpty()){
				IResource resource = findResource(fileModelName);
				if (resource != null && resource.getType() == IResource.FILE &&
						resource.exists() && resource.isAccessible())
					capellaModellers.add((IFile)resource);
			}
		}
	}

	public void endDocument() throws SAXException {
	}

	public Collection<IFile> getCapellaModellers() {
		return capellaModellers;
	}
	
	private IResource findResource(String path){
		try {
			if (path != null){
				path = URLDecoder.decode(path, "UTF-8");
				URI uri = URI.createURI(path).trimFragment();

				if (uri.isPlatformResource()){
					path = computeResourcePath(uri);
					String projectName = uri.segment(1);
					IProject project = wsRoot.getProject(projectName);
					IResource resource = project.findMember(path);
					return resource;
				} else {
					IProject project = wsRoot.getProject(projectName);
					path = computeResourcePath(uri);
					IResource resource = project.findMember(path);
					return resource;
				}
			}
		} catch (UnsupportedEncodingException e) {
			logger.warn(e.getMessage());
		}
		return null;
	}

	private String computeResourcePath(URI uri) {
		String[] segments = uri.segments();
		String path = "";
		int i = 0;
		
		if (uri.isPlatformResource()){
			i = 2;
		} else {
			i = 0;
		}
		
		for (; i < segments.length - 1 ; i++) {
			path += segments[i] + "/";
		}
		return path + uri.lastSegment();
	}
}
