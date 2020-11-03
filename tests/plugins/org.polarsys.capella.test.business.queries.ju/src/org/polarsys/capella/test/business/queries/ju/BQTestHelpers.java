/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.business.queries.ju;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.internal.registry.ExtensionHandle;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.business.api.session.Session;
import org.osgi.framework.Bundle;
import org.polarsys.capella.core.business.queries.IBusinessQuery;

/**
 * @author Erwan Brottier
 */
public class BQTestHelpers {

  public static String getQueryClassification(String fqn, char separator) {
    List<String> segments = Arrays.asList(fqn.split("\\.")); //$NON-NLS-1$
    String folder = segments.get(segments.size() - 2);
    return folder;
  }

  public static Class<?> loadClass(Bundle bundle, String queryIdentifier) {
    try {
      Class<?> bqClass = null;
      if (bundle != null) {
        bqClass = bundle.loadClass(queryIdentifier);
      }
      if (bqClass == null) {
        bqClass = Class.forName(queryIdentifier);
      }
      return bqClass;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public static <T> T newObject(Bundle bundle, String queryIdentifier) {
    try {
      return (T) loadClass(bundle, queryIdentifier).getConstructors()[0].newInstance();
    } catch (Exception e) {
      System.err.println(e.getMessage());
      return null;
    }
  }

  public static IBusinessQuery instanciateBQ(Bundle bundle, String queryIdentifier) {
    return (IBusinessQuery) newObject(bundle, queryIdentifier);
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
      String last = segs.get(segs.size() - 1);
      if (last.endsWith(".java")) { //$NON-NLS-1$
        last = last.substring(0, last.length() - 5);
        segs.remove(segs.size() - 1);
        segs.add(last);
      }
    }
    return StringUtils.join(segs, '.');
  }

  public static Hashtable<String, EObject> getId2ObjectTableInSessionForTest(Session session, EClass clazz) {
    Hashtable<String, EObject> id2ObjectTable = new Hashtable<String, EObject>();
    for (EObject capellaElement : getAllObjectsInSession(session, clazz)) {
      id2ObjectTable.put(QueryResult.getObjectId(capellaElement), capellaElement);
    }
    return id2ObjectTable;
  }

  public static List<EObject> getAllObjectsInSession(Session session, EClass clazz) {
    List<EObject> objects = new ArrayList<EObject>();
    for (Resource semanticResource : session.getSemanticResources()) {
      TreeIterator<EObject> treeIterator = semanticResource.getAllContents();
      while (treeIterator.hasNext()) {
        EObject obj = treeIterator.next();
        if (clazz.isInstance(obj)) {
          objects.add(obj);
        }
      }
    }
    return objects;
  }

  /**
   * Return the File location of this plugin. On a debug session, Generation will generate directly on it.
   */
  public static File getBqTestProject() {
    Bundle bundle = TestBusinessQueriesPlugin.getDefault().getBundle();
    File file = new File(bundle.getLocation().substring("reference:file:/".length()));
    return file;
  }

  public static File getTestSuiteFile(File bqTestProjectFolder, String queryIdentifier, String testProjectName) {
    String pck = getQueryClassification(queryIdentifier, '/');
    String relativePath = BQTestConstants.TEST_SUITES_RELATIVE_FOLDER + "/" + testProjectName + "/" + pck + "/" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        + queryIdentifier + "." + BQTestConstants.TEST_SUITE_FILE_EXTENSION; //$NON-NLS-1$
    return new File(bqTestProjectFolder.toString() + "/" + relativePath); //$NON-NLS-1$
  }

  public static String getPackagePath(String bqTestProjectName, String queryIdentifier, String testProjectName) {
    String pck = getQueryClassification(queryIdentifier, '/');
    String path = bqTestProjectName.replaceAll("\\.", "/");
    return NLS.bind(BQTestConstants.TEST_CASES_PACKAGE, new String[] { path, testProjectName, pck });
  }

  public static String getPackageName(String bqTestProjectName, String queryIdentifier, String testProjectName) {
    return getPackagePath(bqTestProjectName, queryIdentifier, testProjectName).replaceAll("/", ".");
  }

  public static File getJUnitFile(File bqTestProject, String queryIdentifier, String testProjectName, String fileName) {
    String packageName = getPackageName(bqTestProject.getName(), queryIdentifier, testProjectName).replaceAll("\\.",
        "/");
    String junitFileName = bqTestProject.toString() + "/" + BQTestConstants.TEST_CASES_RELATIVE_FOLDER + "/"
        + packageName + "/" + fileName;
    File junitFile = new File(junitFileName);
    return junitFile;
  }

  /**
   * Returns a map of <EPackageName, List<IBusinessQuery>> of all registered queries
   */
  public static HashMap<String, ArrayList<IBusinessQuery>> getQueryPerPackages() {

    HashMap<String, ArrayList<IBusinessQuery>> pkgs = new HashMap<String, ArrayList<IBusinessQuery>>();
    for (IConfigurationElement element : Platform.getExtensionRegistry()
        .getConfigurationElementsFor("org.polarsys.capella.core.data.business.queries.MDEBusinessQueries")) {
      String pkg = ((ExtensionHandle) element.getParent()).getLabel();
      if (!pkgs.containsKey(pkg)) {
        pkgs.put(pkg, new ArrayList<IBusinessQuery>());
      }
      try {
        pkgs.get(pkg).add((IBusinessQuery) element.createExecutableExtension("class"));
      } catch (CoreException e) {
        e.printStackTrace();
      }
    }

    return pkgs;
  }

}
