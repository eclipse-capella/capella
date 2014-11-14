/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.compare;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.util.MiscUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.bundle.FeatureHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaFeatureHelper;
import org.polarsys.capella.core.model.handler.pre.commit.listener.FileModificationPreCommitListener;
import org.polarsys.capella.common.consonance.ui.sirius.SiriusScope;


/**
 * A scope for fragmented Capella files including diagrams.
 */
public class CapellaScope extends SiriusScope {
  
  /** Whether Capella version compatibility must be checked or ignored */
  protected boolean _ignoreCapellaVersions;
  
  
  /**
   * Constructor
   * @param resource_p a non-null resource
   */
  public CapellaScope(Resource resource_p) {
    super(resource_p);
    // Do not explicitly assign _ignoreCapellaVersions: it may be assigned via super constructor
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#addNewResource(org.eclipse.emf.ecore.resource.Resource)
   */
  @Override
  protected void addNewResource(Resource resource_p) {
    if (!_ignoreCapellaVersions) // Automatically initialized to false
      checkCapellaVersion(resource_p);
    super.addNewResource(resource_p);
    if (!resource_p.isTrackingModification())
      resource_p.setTrackingModification(true);
  }
  
  /**
   * Return whether models made with either version of the tool can be loaded
   * in the tool of the other given version
   * @param toolVersion1_p a potentially null short version (in format "X.Y.Z")
   * @param toolVersion2_p a potentially null short version (in format "X.Y.Z")
   */
  protected boolean areCompatible(String toolVersion1_p, String toolVersion2_p) {
    boolean result = false;
    String toolVersion1 = toCanonicalVersion(toolVersion1_p);
    String toolVersion2 = toCanonicalVersion(toolVersion2_p);
    String pattern = Pattern.quote(
        ICommonConstants.EMPTY_STRING + ICommonConstants.POINT_CHARACTER);
    String[] segments1 = toolVersion1.split(pattern);
    String[] segments2 = toolVersion2.split(pattern);
    if (segments1.length >= 2 && segments2.length >=2) {
      if (segments1[0].equals(segments2[0]))
        result = segments1[1].equals(segments2[1]);
    }
    return result;
  }
  
  /**
   * Throw an exception if the given resource is a Capella resource with an
   * incorrect version and the user does not confirm that comparison must be carried out
   * @param resource_p a potentially null resource
   */
  protected void checkCapellaVersion(final Resource resource_p) {
    if (resource_p != null && CapellaResourceHelper.isCapellaResource(resource_p)) {
      // Checking Capella version of Capella resource
      final IFile capellaFile = getFileFor(resource_p);
      if (capellaFile != null) {
        String capellaVersion =
          FeatureHelper.getCapellaVersion(false);
        String rawVersion = CapellaFeatureHelper.getDetectedVersion(capellaFile);
        final String fileVersion = (rawVersion == null || rawVersion.length()<5)? null:
          rawVersion.substring(0, 5);
        if (!areCompatible(fileVersion, capellaVersion)) {
          final Collection<Object> emptyIfProceed = new ArrayList<Object>(1);
          PlatformUI.getWorkbench().getDisplay().syncExec(new Runnable() {
            /**
             * @see java.lang.Runnable#run()
             */
            public void run() {
              String msg = String.format(Messages.CapellaScope_DifferentVersion,
                  fileVersion, capellaFile.getFullPath().toString());
              boolean proceed = MessageDialog.openQuestion(
                  PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
                  EMFDiffMergeUIPlugin.LABEL,
                  msg);
              if (!proceed)
                emptyIfProceed.add(new Object());
            }
          });
          if (!emptyIfProceed.isEmpty())
            throw new RuntimeException(Messages.CapellaScope_DifferentVersionInterruption);
          _ignoreCapellaVersions = true;
        }
      }
    }
  }
  
  /**
   * Return whether write permission is acquired for the modified files
   */
  protected boolean checkWritePermission() {
    final TransactionalEditingDomain domain = CapellaComparePlugin.getDefault().getEditingDomain();
    boolean result = domain == null; // If no editing domain, consider permission is ok
    if (domain != null) {
      // Building the list of modified resources
      int nbResources = getResources().size();
      List<Resource> modifiedResources = new ArrayList<Resource>(nbResources);
      for (Resource currentRes : getResources()) {
        if (currentRes.isModified())
          modifiedResources.add(currentRes);
      }
      // Building the list of modified resource files
      final List<IFile> modifiedFiles = new ArrayList<IFile>(modifiedResources.size());
      for (Resource currentRes : modifiedResources) {
        IFile file = getFileFor(currentRes);
        if (file != null) {
          modifiedFiles.add(file);
          try {
            file.touch(null); // Trigger file modification validators if any
          } catch (CoreException e) {
            // Proceed
          }
        }
      }
      // Checking ability to write in resource files
      final List<Exception> emptyIfOk = new ArrayList<Exception>(1);
      MiscUtil.executeOnDomain(
          domain, Messages.CapellaScope_PermissionCommandName, new Runnable() {
        /**
         * @see java.lang.Runnable#run()
         */
        public void run() {
          try {
            FileModificationPreCommitListener.makeFilesWritable(domain, modifiedFiles);
          } catch (Exception e) {
            emptyIfOk.add(e);
          }
        }
      });
      result = emptyIfOk.isEmpty();
    }
    return result;
  }
  
  /**
   * Return the Eclipse file that holds the given resource, if any.
   * If the file is outside the workspace, create a link to it in a dedicated Eclipse project.
   * @param resource_p a non-null resource
   * @return a potentially null Eclipse file
   */
  protected IFile getFileFor(Resource resource_p) {
    IFile result = null;
    URI uri = resource_p.getURI();
    if (uri != null && uri.isPlatformResource()) {
      // Resource in workspace
      String platformResourcePath = uri.toPlatformString(true);
      result = ResourcesPlugin.getWorkspace().getRoot().getFile(
          new Path(platformResourcePath));
    } else {
      // Resource from external file
      IPath path = new Path(uri.toFileString());
      IProject proxyProject = CapellaComparePlugin.getDefault().getProxyProject(this, path);
      if (proxyProject != null) {
        try {
          String linkName = makeLinkName(uri);
          result = proxyProject.getFile(linkName);
          result.createLink(path, IResource.REPLACE, null);
        } catch (CoreException e) {
          // Just proceed
        }
      }
    }
    return result;
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#getRelevantReferencedResources(org.eclipse.emf.ecore.EObject)
   */
  @Override
  protected final List<Resource> getRelevantReferencedResources(EObject element_p) {
    // Filter out metamodels because of Sirius bug that adds eMDE.ecore to the models
    // referenced by DAnalysis (DAnalysis_Models)
    List<Resource> result = super.getRelevantReferencedResources(element_p);
    for (Resource resource : new ArrayList<Resource>(result)) {
      if ("ecore".equals(resource.getURI().fileExtension())) //$NON-NLS-1$
        result.remove(resource);
    }
    return result;
  }
  
  /**
   * Return a file link name for this scope
   * @param uri_p a non-null URI
   * @return a non-null string
   */
  protected String makeLinkName(URI uri_p) {
    URI truncated = uri_p.trimFileExtension();
    String extension = uri_p.fileExtension();
    String segment = truncated.lastSegment();
    // A suffix could be added to segment, which is why we decompose so much
    URI result = truncated.trimSegments(1).appendSegment(segment);
    result = result.appendFileExtension(extension);
    return result.lastSegment();
  }
  
  /**
   * @see org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope#save()
   */
  @Override
  public boolean save() throws Exception {
    boolean result = checkWritePermission();
    if (result)
      result = super.save();
    return result;
  }
  
  /**
   * Return a canonical variant of the given tool version
   * @param toolVersion_p a potentially null string (in format "X.Y.Z")
   * @return a non-null string
   */
  protected String toCanonicalVersion(String toolVersion_p) {
    String maVersion = FeatureHelper.getCapellaVersion(false);
    final String DEFAULT_VERSION = maVersion != null? maVersion: "2.3.0"; //$NON-NLS-1$
    String result = toolVersion_p != null? toolVersion_p: DEFAULT_VERSION;
    result.replaceFirst(Pattern.quote("1.6."), "2.1.");  //$NON-NLS-1$//$NON-NLS-2$
    result.replaceFirst(Pattern.quote("1.7."), "2.2."); //$NON-NLS-1$ //$NON-NLS-2$
    result.replaceFirst(Pattern.quote("1.8."), "2.3."); //$NON-NLS-1$ //$NON-NLS-2$
    return result;
  }
  
}
