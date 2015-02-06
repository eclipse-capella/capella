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
package org.polarsys.capella.test.framework.api;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

/**
 * Extends BasicTestCase as follow: - before each test case execution, unload
 * all resources in the resource set - before each test case execution, load
 * projects and libraries declared by overriding the methods
 * getLibraryNamesToLoad and getProjectNamesToLoad
 * 
 * @author Erwan Brottier
 */
public abstract class BasicTestCaseWithModelPreLoading extends BasicTestCase {

  /**
   * Must be overriden to define melody projects that must be loaded in the
   * workspace for this test. Use method getLoadedMelodyModel to get one of
   * these models in the test implementation.
   */
  protected abstract List<String> getProjectNamesToLoad();

  protected ICapellaModel getLoadedCapellaModel(String modelName) {
    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(modelName);
    if (!project.isOpen()) {
      try {
        project.open(new NullProgressMonitor());
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }
    }
    Session session = getSessionForLoadedCapellaModel(modelName);
    return (ICapellaModel) ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
  }

  protected Session getSessionForLoadedCapellaModel(String modelName) {
  	IProject project = IResourceHelpers.getEclipseProjectInWorkspace(modelName);
  	Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(project.getFile(modelName + ".aird")), new NullProgressMonitor()); //$NON-NLS-1$
    if (!session.isOpen())
      session.open(new NullProgressMonitor());
    return session;
  }

  protected String getRelativeModelsFolderName() {
  	return "model/"; //$NON-NLS-1$
  }
  
  /** Set context before the test case begins:
   * <ul><li>load all required models in the workspace as eclipse project.</li></ul>
   */
  @Override
  protected void setUp() throws Exception {
    super.setUp();
    // projects loading ...
    String relativeModelsFolder = getRelativeModelsFolderName();
    for (String modelName : getProjectNamesToLoad())
    	loadTestProjectInWorkspace(relativeModelsFolder + modelName + "/", modelName); //$NON-NLS-1$
  }
}
