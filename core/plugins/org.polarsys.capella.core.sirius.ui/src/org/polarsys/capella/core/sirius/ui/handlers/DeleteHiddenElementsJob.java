/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.ui.handlers;

import java.util.Collection;

import org.eclipse.core.resources.WorkspaceJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.util.IJobConstants;

/**
 * Eclipse's job that will call the remove hidden diagrams command.
 */
public class DeleteHiddenElementsJob extends WorkspaceJob {
  private Session _session;
  private Collection<DRepresentationDescriptor> _representationsToRefresh;
  private boolean _unsynchDiags;

  public DeleteHiddenElementsJob(Collection<DRepresentationDescriptor> representationsToRefresh_p, Session session_p, boolean unsyncDiags_p) {
    super(Messages.RemoveHiddenElementsHandler_JobName);
    setProperty(IJobConstants.ALWAYS_LOG_STATUS, true);
    _session = session_p;
    _representationsToRefresh = representationsToRefresh_p;
    _unsynchDiags = unsyncDiags_p;
  }

  @Override
  public IStatus runInWorkspace(IProgressMonitor monitor_p) throws CoreException {
    RemoveHiddenElementsCommand deleteHiddenEltCmd =
        new RemoveHiddenElementsCommand(_representationsToRefresh, TransactionHelper.getExecutionManager(_session), _unsynchDiags);
    TransactionHelper.getExecutionManager(_session).execute(deleteHiddenEltCmd);
    return deleteHiddenEltCmd.getStatus();
  }
}