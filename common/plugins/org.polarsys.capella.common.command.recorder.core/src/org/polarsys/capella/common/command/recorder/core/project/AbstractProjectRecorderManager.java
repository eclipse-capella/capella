/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.command.recorder.core.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;

import org.polarsys.capella.common.command.recorder.core.exception.RecorderException;
import org.polarsys.capella.common.command.recorder.core.manager.AbstractRecorderManager;
import org.polarsys.capella.common.command.recorder.core.output.OutputHelper;
import org.polarsys.capella.common.command.recorder.core.output.OutputRecorder;
import org.polarsys.capella.common.command.recorder.core.preferences.RecorderCorePreferenceServices;
import org.polarsys.capella.common.command.recorder.core.project.utils.ProjectUtils;
import org.polarsys.capella.common.command.recorder.core.recorder.AbstractRecorder;

/**
 * Manager for recording the user actions with a granularity on Project.
 *
 */
abstract public class AbstractProjectRecorderManager extends AbstractRecorderManager implements IResourceChangeListener {
  
  /** workspace */
  private IWorkspace _wksp;
  
  /** natures of interest storage */
  final private Collection<String> _natureIds;
  
  /** Accessor on matching project nature ids. */ 
  final public Collection<String> getMatchingNatures() {
    return _natureIds;
  }
  
  /** Constructor */
  @SuppressWarnings("unchecked")
  public AbstractProjectRecorderManager(Collection<String> natureIdsToMatch) {
    
    super();

    _wksp = ResourcesPlugin.getWorkspace();
    _natureIds = ( null == natureIdsToMatch ? (Collection<String>) Collections.EMPTY_SET : natureIdsToMatch);
    
    setOutPutManager(new OutputRecorder());

  }  
  
  /**
   * @see org.polarsys.capella.common.command.recorder.core.manager.IRecorderManager#startup()
   * @throws RecorderException 
   */
  @Override
  public void startup() throws RecorderException {
    
    super.startup();
    
    //Resource changes
    _wksp.addResourceChangeListener(this);
    
    return;
  }
  
  /** 
   * @see org.polarsys.capella.common.command.recorder.core.manager.IRecorderManager#shutDown()
   * @throws RecorderException
   */
  @Override
  public void shutDown() throws RecorderException {
   
    super.shutDown();
    
    // Remove listener(s)
    _wksp.removeResourceChangeListener(this);
    
    return;
  }
  
  /**
   * 
   * {@inheritDoc}
   */
  @Override
  protected void initRecorders() {

    super.initRecorders();
        
    boolean createNewLog = RecorderCorePreferenceServices.isNewLogFileShouldBeCreatedOnStartUp();
    
    
    // Create a recorder per project
    for (IProject project:  ProjectUtils.getAllProjectsOfType(getMatchingNatures()) ) {
      addProjectRecorder(project, createNewLog);
    }
    
    return;
  }
 
  /**
   * Add a single recorder to a given project if no recorder
   * have been already set for this project.  
   * @param project the target project
   * @param createNewRecordFile Should we force the creation of a new Record file
   */
  protected void addProjectRecorder(IProject project, boolean createNewRecordFile) {
    
    if (
        null != project &&
        !isProjectHasBeenAlreadyAdded(project) &&
        ProjectUtils.isProjectOfType(project, getMatchingNatures())
    ) {
      addRecorder(createProjectRecorder(project), createNewRecordFile);
    }
    
    return;
  }
  
  /** default implementation */
  protected abstract AbstractProjectRecorder createProjectRecorder(IProject project);
  
  /**
   * Returns the recorder affected to a given project.
   * @param project the target {@link IProject}
   * @return <code>null</code> whether no recorders has been already affected to this project
   */
  protected AbstractProjectRecorder getRecorder(IProject project) {
    for (AbstractRecorder recorder: _recorders) {
      if (
          ((AbstractProjectRecorder) recorder).getProject().equals(project)
      ) {
        return (AbstractProjectRecorder) recorder;
      }
    }

    return null;
  }

  /**
   * Check whether a recorders is set for a given project.
   * @param project the target {@link IProject}
   * @return
   */
  protected boolean isProjectHasBeenAlreadyAdded(IProject project) {
    return (null != getRecorder(project));    
  }
  
  /**
   * {@inheritDoc}
   */
  public void resourceChanged(IResourceChangeEvent event) {

  //we are only interested in POST_CHANGE events
    if (event.getType() != IResourceChangeEvent.POST_CHANGE) {
       return;
    }
    
    IResourceDelta rootDelta = event.getDelta();
    
    //get the delta, if any
    IResourceDelta docDelta = rootDelta.findMember(new Path("/")); //$NON-NLS-1$
    if (docDelta == null) {
       return;
    }
    
    
    final ArrayList<IProject> added = new ArrayList<IProject>();
    final ArrayList<IProject> deleted = new ArrayList<IProject>();
    final ArrayList<IProject> closed =  new ArrayList<IProject>();

    IResourceDeltaVisitor visitor = new IResourceDeltaVisitor() {
      public boolean visit(IResourceDelta delta) {
          //We are only interested about add/remove or opening of project
          if (
              ! (
                  (
                      delta.getResource().getType() == IResource.PROJECT
                  ) &&
                  (
                      delta.getKind() == IResourceDelta.ADDED ||
                      delta.getKind() == IResourceDelta.REMOVED ||
                      ( delta.getKind() == IResourceDelta.CHANGED && (delta.getFlags()& IResourceDelta.OPEN) != 0)
                  )
              )
          ) {
             return true;
          }
          
          IProject project = (IProject) delta.getResource();
          
          switch (delta.getKind()) {
            case IResourceDelta.ADDED:
              added.add(project);
              break;
            case IResourceDelta.REMOVED:
              deleted.add(project);
              break;
            case IResourceDelta.CHANGED:
              if ( (delta.getFlags()& IResourceDelta.OPEN) != 0 ) {
                if (project.isOpen()) {
                  added.add(project);
                } else {
                  closed.add(project);
                }
              }
              break;
              default:
                // Do nothing (can not occur)
          }
          
          return true;
       }
    };
    
    try {
       docDelta.accept(visitor);
    } catch (CoreException e) {
       // Do nothing
    }
    
    // Add recorder for new projects
    for (IProject current: added) {
      addProjectRecorder(current, false);
    }
    
    // Remove recorders for deleted projects
    AbstractRecorder recorder = null;
    for (IProject current: deleted) {
      recorder = getRecorder(current);
      removeRecorder(recorder);
      // Check whether the records should be deleted too
      if (RecorderCorePreferenceServices.isRecordsShouldBeDeletedWithProject()) {
        OutputHelper.deleteFile(OutputHelper.getDir(recorder));
      }
    }
    
    // Remove recorders for closed projects
    for (IProject current: closed) {
      removeRecorder(getRecorder(current));
    }
    
    return;
  }
  
}
