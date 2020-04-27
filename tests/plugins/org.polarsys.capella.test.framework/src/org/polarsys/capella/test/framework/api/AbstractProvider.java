/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.framework.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.IFileRequestor;
import org.polarsys.capella.test.framework.helpers.IResourceHelpers;

public abstract class AbstractProvider implements IModelProvider {

  protected static HashMap<String, BasicTestArtefact> modelIdentifier2Owner = new HashMap<String, BasicTestArtefact>();
  protected static HashMap<String, ChangeLocker> modelIdentifier2ChangeLocker = new HashMap<String, ChangeLocker>();
  protected static HashMap<String, String> modelIdentifier2ProjectNameInWorkspace = new HashMap<String, String>();
  protected static HashMap<BasicTestArtefact, List<String>> lstSetUpArtefacts = new HashMap<BasicTestArtefact, List<String>>();

  /**
   * Create an eclipse project in the workspace and feed it with data stored in the test case model repository. Open the
   * project and refresh the workspace.<br>
   * 
   * @return the new eclipse project in the workspace.
   * @param relativeModelPath
   *          the project identifier in the context of the given test case
   * @param artefact
   *          the context where data to add in the new project are stored
   * @throws IOException
   */
  @Override
  public void requireTestModel(List<String> relativeModelsPath, BasicTestArtefact artefact) throws IOException {
    Set<String> toImport = new HashSet<String>();
    for (String relativeModelPath : relativeModelsPath) {
      String modelIdentifier = getModelIdentifier(artefact, relativeModelPath);
      System.out.print(getTestName(artefact) + ">> require ");
      // Load the model if it is not already the case
      if (!modelIdentifier2Owner.containsKey(modelIdentifier)) {
        File sourceFolder = artefact.getFolderInTestModelRepository(relativeModelPath);
        if (!sourceFolder.exists() || !sourceFolder.isDirectory()) {
          System.out.println(getShortIdentifier(modelIdentifier));
          throw new IllegalArgumentException("test model '" + relativeModelPath + "' does not exist");
        }
        System.out.println(">> load " + getShortIdentifier(modelIdentifier));

        BasicTestArtefact startArtefact = getSetupArtefact(artefact);
        if (startArtefact == null) {
          try {
            setUp();
            List<String> loadedModels = new ArrayList<String>();
            loadedModels.add(modelIdentifier);
            lstSetUpArtefacts.put(artefact, loadedModels);
          } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedException(e);
          }
        } else {
          List<String> loadedModels = lstSetUpArtefacts.get(startArtefact);
          if (!loadedModels.contains(modelIdentifier)) {
            loadedModels.add(modelIdentifier);
          }
        }

        toImport.add(relativeModelPath);

        modelIdentifier2Owner.put(modelIdentifier, artefact);
        modelIdentifier2ProjectNameInWorkspace.put(modelIdentifier, sourceFolder.getName());
      }else{
        System.out.println(getShortIdentifier(modelIdentifier));
      }
    }

    importCapellaProject(toImport, artefact);

    for (String relativeModelPath : relativeModelsPath) {
      String modelIdentifier = getModelIdentifier(artefact, relativeModelPath);
      // Add a change lock on the test model if the artefact is not the owner
      // of the test model
      if (modelIdentifier2Owner.get(modelIdentifier) != artefact
          && !artefact.getAllParentTestSuites().contains(modelIdentifier2Owner.get(modelIdentifier))) {
        Session session = getSessionForTestModel(relativeModelPath, artefact);
        TransactionalEditingDomain ted = TransactionHelper.getEditingDomain(session);
        ChangeLocker changeLocker = new ChangeLocker();
        modelIdentifier2ChangeLocker.put(modelIdentifier, changeLocker);
        ted.addResourceSetListener(changeLocker);
      }
    }
  }

  protected static String getModelIdentifier(BasicTestArtefact artefact, String relativeModelPath) {
    File sourceFolder = artefact.getFolderInTestModelRepository(relativeModelPath);
    String modelIdentifier = sourceFolder.toString();
    return modelIdentifier;
  }

  BasicTestArtefact getSetupArtefact(BasicTestArtefact artefact) {
    BasicTestArtefact result = lstSetUpArtefacts.containsKey(artefact) ? artefact : null;
    if (result == null) {
      BasicTestArtefact parent = artefact.getParentTestSuite();
      if (parent != null) {
        result = getSetupArtefact(parent);
      }
    }
    return result;
  }

  protected abstract void importCapellaProject(Set<String> toImport, BasicTestArtefact artefact);

  protected abstract void removeCapellaProject(String relativeModelPath, BasicTestArtefact artefact,
      boolean eraseProject);

  @Override
  public void releaseTestModel(String relativeModelPath, BasicTestArtefact artefact) {
    releaseTestModel(relativeModelPath, artefact, false);
  }

  protected void releaseTestModel(String relativeModelPath, BasicTestArtefact artefact, boolean eraseProject) {
    File sourceFolder = artefact.getFolderInTestModelRepository(relativeModelPath);
    String modelIdentifier = sourceFolder.toString();
    System.out.print(getTestName(artefact) + ">> release ");
    if (!modelIdentifier2Owner.containsKey(modelIdentifier)) {
      System.out.println(getShortIdentifier(modelIdentifier));
      throw new IllegalArgumentException("test model '" + relativeModelPath + "' has not been loaded");
    }
    // If the test artefact is the owner of the test model, do actually the
    // unload
    if (modelIdentifier2Owner.get(modelIdentifier) == artefact) {
      System.out.println(">> unload " + getShortIdentifier(modelIdentifier));
      removeCapellaProject(relativeModelPath, artefact, eraseProject);

      modelIdentifier2Owner.remove(modelIdentifier);
      modelIdentifier2ProjectNameInWorkspace.remove(modelIdentifier);

      BasicTestArtefact startArtefact = getSetupArtefact(artefact);
      if (startArtefact != null) {
        lstSetUpArtefacts.get(startArtefact).remove(modelIdentifier);
        if (lstSetUpArtefacts.get(startArtefact).isEmpty()) {
          try {
            tearDown();
            lstSetUpArtefacts.remove(startArtefact);
          } catch (Exception e) {
            e.printStackTrace();
            throw new WrappedException(e);
          }
        }
      }
    }
    // Remove the change locker if any
    else if (modelIdentifier2ChangeLocker.containsKey(modelIdentifier)) {
      System.out.println(getShortIdentifier(modelIdentifier));
      ChangeLocker changeLocker = modelIdentifier2ChangeLocker.get(modelIdentifier);
      modelIdentifier2ChangeLocker.remove(modelIdentifier);
      Session session = getSessionForTestModel(relativeModelPath, artefact);
      TransactionalEditingDomain ted = TransactionHelper.getEditingDomain(session);
      ted.removeResourceSetListener(changeLocker);
    }else{
      System.out.println(getShortIdentifier(modelIdentifier));
    }
    GuiActions.closeAllOpenedEditors();
    // BEGIN To be delete when the following bug will be solved :
    // Bug 261 - Testability issue due to creation of async GUI jobs during
    // session state changes
    GuiActions.flushASyncGuiThread();
    // END
  }

  /**
   * @return the session of the test model that corresponds to the given identifiers but don't try to open it.
   */
  public static Session getExistingSessionForTestModel(String relativeModelPath, BasicTestArtefact artefact) {
    IProject project = getEclipseProjectForTestModel(relativeModelPath, artefact);
    IFile airdFile = new IFileRequestor().search(project, CapellaResourceHelper.AIRD_FILE_EXTENSION).get(0);
    Session session = SessionManager.INSTANCE.getSession(EcoreUtil2.getURI(airdFile), new NullProgressMonitor());
    return session;
  }

  protected static void normalizeEclipseProjectForTest(IProject project) {
    if (!project.isOpen()) {
      try {
        project.open(new NullProgressMonitor());
      } catch (CoreException exception) {
        exception.printStackTrace();
        throw new WrappedException(exception);
      }
    }
  }

  /**
   * @return the eclipse project in the workspace that corresponds to the given identifiers.<br>
   *         Notice that the test model should have been required previously (@see method requireTestModel)
   */
  public static IProject getEclipseProjectForTestModel(String relativeModelPath, BasicTestArtefact artefact) {
    File sourceFolder = artefact.getFolderInTestModelRepository(relativeModelPath);
    String modelIdentifier = sourceFolder.toString();
    if (!modelIdentifier2Owner.keySet().contains(modelIdentifier)/*hasModelIdentifier2Owner(modelIdentifier)*/) {
      throw new IllegalArgumentException("No model has been loaded for identifier '" + relativeModelPath + "'");
    }
    String projectName = getModelIdentifier2ProjectNameInWorkspace(modelIdentifier);
    IProject project = IResourceHelpers.getEclipseProjectInWorkspace(projectName);
    normalizeEclipseProjectForTest(project);
    return project;
  }

  public static String getModelIdentifier2ProjectNameInWorkspace(String modelIdentifier) {
    return modelIdentifier2ProjectNameInWorkspace.get(modelIdentifier);
  }
  
  private String getTestName(BasicTestArtefact artefact) {
    return "[" + artefact.getClass().getSimpleName() + "] ";
  }
  
  private String getShortIdentifier(String modelIdentifier) {
    URI createURI = URI.createFileURI(modelIdentifier);
    return createURI.lastSegment();
  }
}
