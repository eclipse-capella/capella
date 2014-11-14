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

package org.polarsys.capella.core.platform.sirius.ui.project.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.skeleton.EngineeringDomain;
import org.polarsys.capella.core.model.skeleton.impl.SkeletonServicesImpl;

/**
 * The workspace operation allowing to create a Capella model.
 */
public class CreateCapellaModelOperation extends WorkspaceModifyOperation {
  // The engineering domain.
  private EngineeringDomain _engDomain;
  // The model name.
  private String _modelName;
  // The parent Capella project.
  private Project _parent;
  // the flag to create Operational Analysis or not.
  private boolean _opaSelected;

  /**
   * Constructs the workspace operation allowing to create a Capella model.
   * @param parent_p The parent Capella project.
   * @param modelName_p The model name.
   * @param engDomain_p the engineering domain.
   * @param opaSelected_p
   */
  public CreateCapellaModelOperation(Project parent_p, String modelName_p, EngineeringDomain engDomain_p, boolean opaSelected_p) {
    _parent = parent_p;
    _engDomain = engDomain_p;
    _modelName = modelName_p;
    _opaSelected = opaSelected_p;
  }

  /**
   * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  protected void execute(IProgressMonitor monitor_p) throws CoreException, InvocationTargetException, InterruptedException {
    SubMonitor progress = SubMonitor.convert(monitor_p, Messages.CreateCapellaModelOperation_CreateCapellaModel_Title, 1);
    SkeletonServicesImpl skeletonServicesImpl = new SkeletonServicesImpl();
    skeletonServicesImpl.doSystemEngineering(_parent, _modelName, _engDomain, _opaSelected);

    if (monitor_p.isCanceled()) {
      throw new OperationCanceledException();
    }
    progress.worked(1);
  }
}
