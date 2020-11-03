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
package org.polarsys.capella.test.fragmentation.ju.utils.initegrity;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.business.api.session.Session;

public interface IChecker {
  public static String IntegrityChecker_Checking = "Fragmentation integrity checking..";
  public static String IntegrityChecker_Comma = ", ";
  public static String IntegrityChecker_DuplicateElement = "Some elements are defined in more than one fragment : ''{0}'':\r\n";
  public static String IntegrityChecker_DuplicateElementSolo = "Some elements are duplicated in one fragment : ''{0}'':\r\n";
  public static String IntegrityChecker_FailedDuplicateElements = "Fragmentation integrity failed: duplication of some semantic elements";
  public static String IntegrityChecker_LogDiagram = "Diagram ''{0}'' into ''{1}'' uses some duplicated elements\n";
  public static String IntegrityChecker_LogElement = "\t{0}\tid=\"{1}\r\n";
  public static String IntegrityChecker_LogResource = "from\t{0}\r\n";
  public static String IntegrityChecker_OK = "Fragmentation integrity OK: no semantic duplication";
  public static String IntegrityChecker_RetrieveDiagramDuplicatedSemantic = "Retrieve diagrams with duplicated semantic elements";
  public static String IntegrityChecker_RetriveDuplicateSemantics = "Retrieve duplicated elements between fragments";
  public static String IntegrityChecker_Tabulation = "\t\";";
  public static String ReferencedResourcesChecker_Checking = "Referenced resources checking..";
  public static String ReferencedResourcesChecker_RetrieveProjectResources = "Retrieve resources of the project";
  public static String ReferencedResourcesChecker_RetrieveSessionResources = "Retrieve resources used through the session";
  public static String ReferencedResourcesChecker_RetrieveUnusedResources = "Compute resources unused through the session";
  public static String ReferencedResourcesChecker_UnusedResource = "Unused resource ''{0}'' for session ''{1}''";
  public static String ReferencedResourcesChecker_OK = "ReferencedResources: no unused resources are located in the project";
  public static String ReferencedResourcesChecker_Error = "ReferencedResources: some resources in the project are unused through the session";
  public IStatus getStatus(Session session_p, Logger logger_p, IProgressMonitor monitor_p);
}
