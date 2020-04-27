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
package org.polarsys.capella.test.framework.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.ui.internal.Workbench;
import org.junit.Assert;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.model.CapellaLibraryExt;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.test.framework.api.ModelProviderHelper;

import com.google.common.io.Files;

import junit.framework.TestCase;

/**
 * Helper for writing JUnit tests for RCP application.
 */
public class TestHelper {

  /**
   * Capella project nature. Copied from CapellaNature.ID
   */
  public static final String CAPELLA_PROJECT_NATURE = "org.polarsys.capella.project.nature"; //$NON-NLS-1$

  public static Project getProjectFromMelodyModeller(IFile melodyModellerFile) {
    URI uri = URI.createPlatformResourceURI(melodyModellerFile.getFullPath().toOSString(), true);
    ExecutionManager execManager = ExecutionManagerRegistry.getInstance().addNewManager();
    ResourceSet rsMelody = execManager.getEditingDomain().getResourceSet();
    Resource rMelody = rsMelody.getResource(uri, true);
    EcoreUtil.resolveAll(rMelody);
    return CapellaLibraryExt.getProject(rMelody);
  }

  /**
   * Create an empty capella project with given name.
   * 
   * @param name
   *          project name.
   * @return a not <code>null</code> project.
   */
  public static IProject createCapellaProject(String name) {
    // Get the workspace.
    IWorkspace workspace = ResourcesPlugin.getWorkspace();

    // Get a project for given name.
    IProject project = workspace.getRoot().getProject(name);
    // Should not exist, workspace is supposed to be cleaned before the
    // tests execution.
    if (!project.exists()) {
      try {
        IProjectDescription description = workspace.newProjectDescription(name);
        // Add the project nature.
        description.setNatureIds(new String[] { CAPELLA_PROJECT_NATURE });
        project.create(description, new NullProgressMonitor());
      } catch (CoreException exception) {
        exception.printStackTrace();
        Assert.fail(exception.getMessage());
      }
    }
    // Open the project.
    try {
      project.open(new NullProgressMonitor());
    } catch (CoreException exception) {
      exception.printStackTrace();
      Assert.fail(exception.getMessage());
    }
    return project;
  }

  /**
   * set a project as multipart or not
   * 
   * @param project
   *          the project to update
   * @param value
   *          true to set as Multipart, otherwise false
   */
  public static void setReusableComponents(final EObject anyModelElement, final boolean value) {
    ExecutionManagerRegistry.getInstance().getExecutionManager(TransactionHelper.getEditingDomain(anyModelElement))
        .execute(new AbstractReadWriteCommand() {
          @Override
          public void run() {
            Project project = CapellaProjectHelper.getProject(anyModelElement);
            if (value) {
              CapellaProjectHelper.setProjectWithApproach(project, ProjectApproach.ReusableComponents);
            } else {
              CapellaProjectHelper.setProjectWithApproach(project, ProjectApproach.SingletonComponents);
            }
          }
        });
  }

  /**
   * Get the Capella Execution manager.
   * 
   * @return a not <code>null</code> execution manager.
   */
  public static ExecutionManager getExecutionManager(EObject eObject) {
    return TransactionHelper.getExecutionManager(eObject);
  }

  /**
   * Get the Capella Execution manager.
   * 
   * @return a not <code>null</code> execution manager.
   */
  public static ExecutionManager getExecutionManager(Session session) {
    return TransactionHelper.getExecutionManager(session);
  }

  /**
   * Get the Capella Editing Domain.
   * 
   * @return a not <code>null</code> editing domain.
   * @deprecated shall not be used anymore, might not have the expected behavior
   */
  @Deprecated
  public static TransactionalEditingDomain getEditingDomain() {
    return new ArrayList<TransactionalEditingDomain>(ExecutionManagerRegistry.getInstance().getAllEditingDomains())
        .get(0);
  }

  /**
   * Get the semantic resource linked to given diagram resource (AIRD one).
   * 
   * @param diagramResource
   * @return <code>null</code> if not found.
   */
  public static Resource getSemanticResource(Session session) {
    return ModelProviderHelper.getInstance().getModelProvider().getSemanticResource(session);
  }

  public static Resource getAirdResource(Session session) {
    return ModelProviderHelper.getInstance().getModelProvider().getAirdResource(session);
  }

  public static void copy(File sourceLocation, File targetLocation) throws IOException {
    if (sourceLocation.isDirectory()) {
      copyDirectory(sourceLocation, targetLocation);
    } else if (!targetLocation.isDirectory()) {
      Files.copy(sourceLocation, targetLocation);
    } else {
      File targetFile = new File(targetLocation.toString() + "/" + sourceLocation.getName()); //$NON-NLS-1$
      Files.copy(sourceLocation, targetFile);
    }
  }

  private static void copyDirectory(File source, File target) throws IOException {
    if (!target.exists()) {
      target.mkdir();
    }

    for (String f : source.list()) {
      copy(new File(source, f), new File(target, f));
    }
  }

  public static void delete(File f) throws IOException {
    if (!f.exists()) {
      return;
    }

    if (f.isDirectory()) {
      for (File c : f.listFiles())
        delete(c);
    }
    if (!f.delete())
      throw new FileNotFoundException("Failed to delete file: " + f);
  }

  /**
   * Open given diagram resource i.e AIRD one. Note: Even if the method signature suggests that the returned session
   * will be open, it will NOT. You need to call session.open() manually.
   *
   * @param resource
   */
  public static Session openOrGetSession(IFile file) {
    Session result = null;

    try {
      if ((null == file) || !file.exists()) {
        throw new Exception("'" + file.getName() + "' doesn't exist"); //$NON-NLS-1$ //$NON-NLS-2$
      }
      String fileFullPath = file.getFullPath().toOSString();
      URI uri = URI.createPlatformResourceURI(fileFullPath, true);
      Collection<Session> allSessions = SessionManager.INSTANCE.getSessions();
      for (Session session : allSessions) {
        for (Resource res : session.getAllSessionResources()) {
          if (res.getURI().equals(uri)) {
            return session;
          }
        }
      }
      if (fileFullPath.endsWith("airfragment")) //$NON-NLS-1$
        Assert.fail("open Session on '" + fileFullPath + "' is not allowed");//$NON-NLS-1$ //$NON-NLS-2$

      // if there is no opened session, create a new one from the uri.
      result = SessionManager.INSTANCE.getSession(uri, new NullProgressMonitor());

    } catch (Exception exception) {
      exception.printStackTrace();
      Assert.fail(exception.getMessage());
    }
    return result;
  }

  /**
   * 
   * @return all derived references from Capella packages
   */
  public static Set<EReference> getAllCapellaDerivedReferences() {
    Set<EReference> allDerivedReferences = new HashSet<>();
    for (String nsURI : EPackage.Registry.INSTANCE.keySet()) {
      if (nsURI.startsWith("http://www.polarsys.org/capella")) {
        EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
        for (EClassifier eClassifier : ePackage.getEClassifiers())
          if (eClassifier instanceof EClass) {
            EClass eClass = (EClass) eClassifier;
            for (EReference eReference : eClass.getEReferences()) {
              if (eReference.isDerived()) {
                allDerivedReferences.add(eReference);
              }
            }
          }
      }
    }
    return allDerivedReferences;
  }

  public static void disposeObject(Object e) {
    Class currentClass = e.getClass();
    while (currentClass != null && TestCase.class != currentClass) {
      for (Field field : currentClass.getDeclaredFields()) {
        try {
          if (!Modifier.isFinal(field.getModifiers()) && !field.getType().isPrimitive()
              && !String.class.isAssignableFrom(field.getType())) {
            field.setAccessible(true);
            field.set(e, null);
          }
        } catch (IllegalArgumentException | IllegalAccessException e1) {
          e1.printStackTrace();
        }
      }
      currentClass = currentClass.getSuperclass();
    }
  }

  /**
   * Get the first matching element contained in given resource for given type.
   *
   * @param resource_p
   * @param elementType_p
   */
  public static EObject getFirstMatchingObject(Resource resource_p, EClass elementType_p) {
    TreeIterator<EObject> allContents = resource_p.getAllContents();
    EObject element = null;
    // Iterate over contained elements to search for an EObject that the
    // eClass matches given one.
    while (allContents.hasNext()) {
      EObject eObject = allContents.next();
      if (eObject.eClass() == elementType_p) {
        element = eObject;
        break;
      }
    }
    return element;
  }

  public static void disableAutoSaveJob() {
    // Deadlock because of Workbench Auto-Save Job, so we have to remove it 
    Job[] allJobs = Job.getJobManager().find(null);
    for (Job job : allJobs) {
      if (Workbench.WORKBENCH_AUTO_SAVE_JOB.equals(job.getName())) {
        job.cancel();
      }
    }
  }

}
