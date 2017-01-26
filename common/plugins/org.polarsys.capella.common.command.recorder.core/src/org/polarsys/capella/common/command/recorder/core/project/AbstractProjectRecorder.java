/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.command.recorder.core.project;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.transaction.NotificationFilter;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

import org.polarsys.capella.common.command.recorder.core.manager.AbstractRecorderManager;
import org.polarsys.capella.common.command.recorder.core.recorder.AbstractRecorder;

/**
 * Recorder with granularity on Project
 *
 */
abstract public class AbstractProjectRecorder extends AbstractRecorder {

	/** the target project */ 
	protected IProject project;
	
	/** Accessor on target project */
	public IProject getProject() {
	  return project;
	}
	
  /**
   * @see org.eclipse.emf.transaction.ResourceSetListenerImpl#getFilter()
   */
  @Override
  public NotificationFilter getFilter() {        
    return new NotificationFilter.Custom() {
      @Override
      public boolean matches(Notification notification) {
        boolean b = false;
        
        // ENotification only
        if ( notification  instanceof ENotificationImpl ) {
          b = true;
        }
        
        if (b) { // From this project
          EObject eObj = ( (EObject)
              ((ENotificationImpl) notification).getNotifier()
          );
          if ( null != eObj && null != eObj.eResource() ) {
            IFile file = WorkspaceSynchronizer.getFile(eObj.eResource());
            if (null != file) {
              b = file.getProject().equals(project);
            } else {
              b = false;
            }
          }
        } else {
          b = false;
        }
        
        return b;
      }
    };
  }
	
	/**
	 * Constructor
	 * @param manager
	 * @param project
	 */
	public AbstractProjectRecorder(AbstractRecorderManager manager, IProject project) {
		super(manager);
		
		this.project = project;
		
	}

  /**
   * {@inheritDoc}
   */
  public String getRelativePath() {
    return project.getName();
  }

}
