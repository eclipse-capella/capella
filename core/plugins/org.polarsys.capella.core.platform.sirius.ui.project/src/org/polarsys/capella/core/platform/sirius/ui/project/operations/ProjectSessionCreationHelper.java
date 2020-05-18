/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.project.operations;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.EngineeringDomain;
import org.polarsys.capella.core.model.skeleton.impl.SkeletonServicesImpl;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;

public class ProjectSessionCreationHelper extends SessionCreationHelper {

  ProjectApproach projectApproach;
  boolean epbsSelected;
  boolean opaSelected;

  public ProjectSessionCreationHelper(boolean epbsSelected_p, boolean opaSelected_p) {
    this(epbsSelected_p, opaSelected_p, ProjectApproach.SingletonComponents);
  }

  public ProjectSessionCreationHelper(boolean epbsSelected_p, boolean opaSelected_p, ProjectApproach projectApproach_p) {
    projectApproach = projectApproach_p;
    epbsSelected = epbsSelected_p;
    opaSelected = opaSelected_p;
  }

  protected ProjectApproach getProjectApproach() {
    return projectApproach;
  }

  protected boolean isEpbsSelected() {
    return epbsSelected;
  }

  protected boolean isOpaSelected() {
    return opaSelected;
  }

  @Override
  protected ICommand createInitialElementsCommand(Resource resource_p, String projectName_p, IProgressMonitor monitor_p) {
    return new CreateCapellaProjectCmd(resource_p, projectName_p, projectApproach);
  }

  @Override
  protected ICommand updateInitialElementsCommand(final Resource semanticResource_p, final String name_p, final IProgressMonitor monitor_p) {
    return new AbstractReadWriteCommand() {
      public void run() {
        EList<EObject> content = semanticResource_p.getContents();
        if (!content.isEmpty()) {
          EObject root = content.get(0);
          if (root instanceof Project) {
            fillNewModel((Project) root, name_p, monitor_p);
          }
        }
      }
    };
  }

  public void fillNewModel(Project project, String name_p, IProgressMonitor monitor_p) {
    EngineeringDomain engDomain = EngineeringDomain.Software;
    if (epbsSelected) {
      engDomain = EngineeringDomain.System;
    }
    SkeletonServicesImpl skeletonServicesImpl = new SkeletonServicesImpl();
    skeletonServicesImpl.doSystemEngineering(project, name_p, engDomain, opaSelected);
  }

  /**
   * This method allows to create a new System Engineering in the semantic resource
   */
  public void createNewSystemEngineering(Resource semanticResource, String name_p, IProgressMonitor monitor_p) {

    monitor_p.beginTask("Create initial skeletton", 1);
    ICommand command = updateInitialElementsCommand(semanticResource, name_p, monitor_p);
    if (command != null) {
      TransactionHelper.getExecutionManager(semanticResource).execute(command);
    }
    monitor_p.worked(1);
  }

}
