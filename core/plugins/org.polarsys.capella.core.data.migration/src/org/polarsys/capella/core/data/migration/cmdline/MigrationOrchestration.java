/*******************************************************************************
 * Copyright (c) 2026 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.migration.cmdline;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.commandline.core.CommandLineException;
import org.polarsys.capella.core.sirius.ui.helper.ResourceHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.metadata.metadata.Metadata;

/**
 * Class to sort migration order of projects.
 */
public class MigrationOrchestration {
  
  private final List<IProject> sequence = new ArrayList<>();
  private final ResourceSet resourceSet = new ResourceSetImpl();
  
  /**
   * Default Constructor.
   * 
   * @param projects to organize
   * throws CommandLineException if some project cannot be migrated.
   */
  public MigrationOrchestration(Collection<? extends IProject> projects) throws CommandLineException {
    try {
      for (IProject project : projects) {
        addToSequence(project, new ArrayDeque<>());
      }
    } catch (IOException | IllegalStateException | NoSuchElementException e) {
      throw (CommandLineException) new CommandLineException("Migration failed: " + e.getMessage()).initCause(e); //$NON-NLS-1$
    }
  }
  
  private void addToSequence(IProject project, Deque<IProject> pool) throws IOException, IllegalStateException, NoSuchElementException {
    if (sequence.contains(project)) {
      return; // already performed.
    }
    if (pool.contains(project)) {
      throw new IllegalStateException("Circular reference in Projects: " + pool); //$NON-NLS-1$
    }
    pool.push(project);
    
    for (var lib : getAdditionalMetadata(project)) {
      IProject libProject = getOwningProject(lib, project);
      if (libProject != null) {
        addToSequence(libProject, pool);
      }
    }
    
    sequence.add(project);
    pool.pop();
  }

  private List<Metadata> getAdditionalMetadata(IProject project) throws IOException, NoSuchElementException {
    IFile metadataFile = ResourceHelper.collectFiles(project).stream()
      .filter(MetadataHelper::isMetadataResource)
      .findFirst()
      .orElseThrow(() -> new NoSuchElementException("No MetadataResource found in " + project)); //$NON-NLS-1$
    
    Resource r = resourceSet.createResource(EcoreUtil2.getURI(metadataFile));
    r.load(null);
    // Reminder: loading a resource does not indicate a reference is not accessible.
    
    if (!r.getContents().isEmpty() && r.getContents().get(0) instanceof Metadata metadata) {
      return metadata.getAdditionalMetadata();
    }
    throw new NoSuchElementException("No Metadata Content found in " + metadataFile); //$NON-NLS-1$
  }
  
  private IProject getOwningProject(Metadata data, IProject caller) throws NoSuchElementException {
    checkReferenceResolution("Library", data, caller.getName()); //$NON-NLS-1$

    var projectName = getLocalProjectName(data.eResource().getURI());
    // Project has to exist otherwise it would not be loaded.
    return projectName != null  ? ResourcesPlugin.getWorkspace().getRoot().getProject(projectName) : null;
  }
  
  private void checkReferenceResolution(String type, EObject value, String caller) throws NoSuchElementException {
    if (value.eIsProxy()) {
      var referenceName = "";
      if (value instanceof InternalEObject iObject) {
        referenceName = iObject.eProxyURI().path();
        var projectName = getLocalProjectName(iObject.eProxyURI());
        if (projectName != null) {
          referenceName = projectName;
        }
      }
      throw new NoSuchElementException(MessageFormat.format("Cannot access {0} {1} referenced by {2}", //$NON-NLS-1$
          referenceName, type, caller));
    }
  }
  
  private String getLocalProjectName(URI location) {
    return location.isPlatformResource() ? location.segment(1) : null;
  }
  
  /**
   * Returns the migration sequence.
   * 
   * @return ordered list of projects.
   */
  public List<IProject> getSequence() {
    return sequence;
  }
  
}
