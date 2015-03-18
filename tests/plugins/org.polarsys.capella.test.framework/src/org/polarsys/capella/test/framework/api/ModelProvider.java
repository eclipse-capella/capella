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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.libraries.ILibraryManager;
import org.polarsys.capella.common.mdsofa.common.helper.ProjectHelper;
import org.polarsys.capella.core.libraries.model.CapellaModel;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;
import org.polarsys.capella.test.framework.helpers.TestHelper;

/**
 * Manages input models for test. Called by @see BasicTestCase and @see BasicTestSuite
 * to load and unload models used in test cases.<br>
 * This class handles read-only check for models that are used by several test cases without being unloaded.
 * 
 * @author Erwan Brottier
 */
public class ModelProvider {

	protected static HashMap<String, BasicTestArtefact> modelIdentifier2Owner = new HashMap<String, BasicTestArtefact>();
	protected static HashMap<String, String> modelIdentifier2ProjectNameInWorkspace = new HashMap<String, String>();
	
  /**
   * Create an eclipse project in the workspace and feed it with data stored in the test case model repository. Open the project and refresh the workspace.<br>
   * @return the new eclipse project in the workspace.
   * @param relativeModelPath the project identifier in the context of the given test case
   * @param artefact the context where data to add in the new project are stored
   * @throws IOException 
   */
	public static void requireTestModel(String relativeModelPath, BasicTestArtefact artefact) throws IOException {
		File sourceFolder = artefact.getFileOrFolderInTestModelRepository(relativeModelPath);  	
  	String modelIdentifier = sourceFolder.toString();
  	System.out.println(">> require "+modelIdentifier);
  	if (!modelIdentifier2Owner.containsKey(modelIdentifier)) {
  		if (!sourceFolder.exists() || !sourceFolder.isDirectory())
  			throw new IllegalArgumentException("test model '"+relativeModelPath+"' does not exist");
  		System.out.println(">> load "+modelIdentifier);
  		String projectName = sourceFolder.getName();
  		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();    
  		File targetFolder = new File(root.getRawLocation().toString()+"/"+projectName+"/"); //$NON-NLS-1$ //$NON-NLS-2$
  		TestHelper.copy(sourceFolder, targetFolder);
  		IProject project = TestHelper.createCapellaProject(projectName);
  		normalizeEclipseProjectForTest(project);   
  		ProjectHelper.refreshProject(project, new NullProgressMonitor());
  		modelIdentifier2Owner.put(modelIdentifier, artefact);
  		modelIdentifier2ProjectNameInWorkspace.put(modelIdentifier, projectName);
  	}
	}
	
	public static void releaseTestModel(String relativeModelPath, BasicTestArtefact artefact) {
  	File sourceFolder = artefact.getFileOrFolderInTestModelRepository(relativeModelPath);  	
  	String modelIdentifier = sourceFolder.toString();
  	System.out.println(">> release "+modelIdentifier);
  	if (!modelIdentifier2Owner.containsKey(modelIdentifier))
			throw new IllegalArgumentException("test model '"+relativeModelPath+"' has not been loaded");
		if (modelIdentifier2Owner.get(modelIdentifier) == artefact) {
			System.out.println(">> unload "+modelIdentifier);
			Session session = getSessionForTestModel(relativeModelPath, artefact);
			if (session.isOpen()) {
				session.save(new NullProgressMonitor());
				session.close(new NullProgressMonitor());
			}
			IProject eclipseProject = getEclipseProjectForTestModel(relativeModelPath, artefact);
			try {
				eclipseProject.delete(true, new NullProgressMonitor());    			
			} catch (Exception e) {
				e.printStackTrace();
			}
			modelIdentifier2Owner.remove(modelIdentifier);
			modelIdentifier2ProjectNameInWorkspace.remove(modelIdentifier);
		}
	}
	
	public static IProject getEclipseProjectForTestModel(String relativeModelPath, BasicTestArtefact artefact) {
  	File sourceFolder = artefact.getFileOrFolderInTestModelRepository(relativeModelPath);
  	String modelIdentifier = sourceFolder.toString();
  	if (!modelIdentifier2Owner.keySet().contains(modelIdentifier))
  		throw new IllegalArgumentException("No model has been loaded for identifier '"+relativeModelPath+"'");
  	String projectName = modelIdentifier2ProjectNameInWorkspace.get(modelIdentifier);
  	IProject project = IResourceHelpers.getEclipseProjectInWorkspace(projectName);
  	normalizeEclipseProjectForTest(project);
  	return project;
	}
	
  public static CapellaModel getTestModel(String relativeModelPath, BasicTestCase artefact) {
    Session session = getSessionForTestModel(relativeModelPath, artefact);
    CapellaModel model = (CapellaModel) ILibraryManager.INSTANCE.getModel(session.getTransactionalEditingDomain());
    return model;
  }
  
  public static Session getSessionForTestModel(String relativeModelPath, BasicTestArtefact artefact) {  	
  	IProject project = getEclipseProjectForTestModel(relativeModelPath, artefact);
  	IFile airdFile = new IFileRequestor().search(project, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);
  	Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFile), new NullProgressMonitor()); //$NON-NLS-1$
    if (!session.isOpen())
      session.open(new NullProgressMonitor());
    return session;
  }
  
  private static void normalizeEclipseProjectForTest(IProject project) {
    if (!project.isOpen()) {
      try {
        project.open(new NullProgressMonitor());
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }
    }  	
  }

}
