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

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;

/**
 * The workspace operation to create aCapella project root.
 */
public class CreateCapellaProjectOperation extends WorkspaceModifyOperation {
  // Log4j reference logger.
  private static final Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);

  // The MDE project.
  private Project _capellaProject;
  // The project name.
  private String _projectName;
  // The parent Eclipse project.
  private IProject _parent;
  /**
   * Project approach.
   */
  private ProjectApproach _projectApproach;

  /**
   * Constructs the workspace operation to create the Capella project.
   * @param parent_p The parent Eclipse project.
   * @param projectName_p The Capella project name.
   */
  public CreateCapellaProjectOperation(IProject parent_p, String projectName_p, ProjectApproach projectApproach_p) {
    _projectName = projectName_p;
    _parent = parent_p;
    _projectApproach = projectApproach_p;
  }

  /**
   * @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
   */
  @Override
  protected void execute(IProgressMonitor monitor_p) throws CoreException, InvocationTargetException, InterruptedException {
    SubMonitor progress = SubMonitor.convert(monitor_p, 1);
    try {
      TransactionalEditingDomain domain = MDEAdapterFactory.getEditingDomain();
      CreateCapellaProjectCmd command = createCreationCommand(monitor_p, domain);
      MDEAdapterFactory.getExecutionManager().execute(command);

      if (monitor_p.isCanceled()) {
        throw new OperationCanceledException();
      }
      _capellaProject = command.getProject();
    } catch (Exception ex) {
      __logger.debug(new EmbeddedMessage(ex.getMessage(), IReportManagerDefaultComponents.MODEL));
    }
    progress.worked(1);
  }

  /**
   * Gets the new Capella project.
   * @return The Capella project.
   */
  public Project getProject() {
    return _capellaProject;
  }

  /**
   * @param monitor_p
   * @param domain_p
   * @return
   */
  protected CreateCapellaProjectCmd createCreationCommand(IProgressMonitor monitor_p, TransactionalEditingDomain domain_p) {
    return new CreateCapellaProjectCmd(getParent(), getProjectName(), domain_p, getProjectApproach());
  }

  /**
   * @return the projectName
   */
  protected String getProjectName() {
    return _projectName;
  }

  /**
   * @return the parent
   */
  protected IProject getParent() {
    return _parent;
  }

  /**
   * @return the projectApproach
   */
  protected ProjectApproach getProjectApproach() {
    return _projectApproach;
  }

}
