/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.business.queries.ju;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.core.business.queries.IBusinessQuery;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

/**
 * @author Erwan Brottier
 */
public class BQTestHelpers {

	public static String getQueryClassification(String fqn, char separator) {
		List<String> segments = Arrays.asList(fqn.substring(BQTestConstants.BQ_PLUGIN_NAME.length()+1, fqn.length()).split("\\.")); //$NON-NLS-1$
		StringBuilder pck = new StringBuilder();
		for (int i = 0; i < segments.size()-1; i++) {
			pck.append(segments.get(i));
			if (i < segments.size()-2)
				pck.append(separator);
		}
		return pck.toString();
	}
	
	public static IBusinessQuery instanciateBQ(String queryIdentifier) {
		try {
			Class<?> bqClass = Class.forName(queryIdentifier);
			return (IBusinessQuery) bqClass.getConstructors()[0].newInstance();
		} catch (Exception e) {
			return null;
		}
	}
	
	public static Hashtable<String, EObject> createId2ObjectTable(List<EObject> elements) {
		Hashtable<String, EObject> table = new Hashtable<String, EObject>();
		for (EObject capellaElement : elements) {
			table.put(QueryResult.getObjectId(capellaElement), capellaElement);
		}
		return table;
	}

	public static String getFullQualifiedName(IFile javaFile) {
		List<String> segs = new ArrayList<String>();
		for (String string : javaFile.getFullPath().segments()) {
			segs.add(string);
		}
		while (segs.size() > 0 && !segs.get(0).equals("src")) { //$NON-NLS-1$
			segs.remove(0);
		}
		if (segs.size() > 0) {
			segs.remove(0);
		}
		if (segs.size() > 0) {
			String last = segs.get(segs.size()-1);
			if (last.endsWith(".java")) { //$NON-NLS-1$
				last = last.substring(0, last.length()-5);
				segs.remove(segs.size()-1);
				segs.add(last);
			}
		}
		return StringUtils.join(segs, '.');
	}
	
	public static Hashtable<String, CapellaElement> getId2ObjectTableInSessionForTest(Session session, EClass clazz) {
		Hashtable<String, CapellaElement> id2ObjectTable = new Hashtable<String, CapellaElement>();
		for (CapellaElement capellaElement : getAllObjectsInSession(session, clazz)) {
			id2ObjectTable.put(QueryResult.getObjectId(capellaElement), capellaElement);
		}			
		return id2ObjectTable;
	}

	public static List<CapellaElement> getAllObjectsInSession(Session session, EClass clazz) {
		List<CapellaElement> objects = new ArrayList<CapellaElement>();
		for (Resource semanticResource : session.getSemanticResources()) {
			TreeIterator<EObject> treeIterator = semanticResource.getAllContents();
			while (treeIterator.hasNext()) {
				EObject obj = treeIterator.next();				
				if (obj instanceof CapellaElement && clazz.isInstance(obj)) {
					objects.add((CapellaElement) obj);					
				}
			}
		}
		return objects;
	}
	
	public static File getTestSuiteFile(File bqTestProjectFolder, String queryIdentifier, String testProjectName) {
		String pck = getQueryClassification(queryIdentifier, '/');
		String relativePath = BQTestConstants.TEST_SUITES_RELATIVE_FOLDER + "/" + testProjectName + "/" + pck + "/" + queryIdentifier + "." +BQTestConstants.TEST_SUITE_FILE_EXTENSION; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		return new File(bqTestProjectFolder.toString()+"/"+relativePath); //$NON-NLS-1$
	}
	
	public static File getJUnitFile(File bqTestProjectFolder, String queryIdentifier, String testProjectName, String fileName) {
		String pck = getQueryClassification(queryIdentifier, '/');
		File junitFile = new File(bqTestProjectFolder.toString()+"/"+BQTestConstants.TEST_CASES_RELATIVE_FOLDER+testProjectName + "/" + pck + "/" + fileName); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return junitFile;
	}
	
}
