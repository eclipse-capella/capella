/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.refactoring;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;
import org.polarsys.capella.core.sirius.ui.helper.ResourceHelper;

public class CapellaRenameParticipant extends RenameParticipant {

  private IContainer container;

  public CapellaRenameParticipant() {
    // Do nothing
  }

  @Override
  protected boolean initialize(Object element) {
    if (element instanceof IContainer) {
      container = (IContainer) element;
      return true;
    }
    return false;
  }

  @Override
  public String getName() {
    return "Capella Rename Participant";

  }

  @Override
  public RefactoringStatus checkConditions(IProgressMonitor pm, CheckConditionsContext context) {
    if (container.isAccessible()) {
      return new RefactoringStatus();
    }
    return RefactoringStatus
        .createErrorStatus("Unable to update Workspace Image paths because container is not accessible !");
  }

  // Do it in the createPreChange instead of the createChange so that Sirius session is still open and the container is
  // still accessible
  @Override
  public Change createPreChange(IProgressMonitor pm) throws CoreException {
    // Always create a change when it's a project because we need to rename some model elements.
    // When we rename a folder, create a change only if this folder contains images
    if (container instanceof IProject
        || (getArguments().getUpdateReferences() && !ResourceHelper.collectImageFiles(container).isEmpty())) {
      return doCreateChange(pm);
    }
    return null;
  }

  @Override
  public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
    return null;
  }

  private Change doCreateChange(IProgressMonitor pm) {
    return new WorkspaceImagePathChange(container, getArguments());
  }
}
