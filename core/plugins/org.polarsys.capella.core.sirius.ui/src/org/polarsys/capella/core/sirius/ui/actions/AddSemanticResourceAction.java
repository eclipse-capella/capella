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

package org.polarsys.capella.core.sirius.ui.actions;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.window.Window;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.helpers.TransactionHelper;

/**
 * The action allowing to add a semantic resource in session(s).
 * @deprecated
 */
public class AddSemanticResourceAction extends BaseSelectionListenerAction {
  /**
   * Creates a new {@link AddSemanticResourceAction}.
   */
  public AddSemanticResourceAction() {
    super("Add Semantic Resource"); //$NON-NLS-1$
  }

  /**
   * {@inheritDoc}
   * @see org.eclipse.jface.action.Action#run()
   */
  @Override
  public void run() {
    // Lets the user selecting semantics resources.
    Collection<URI> selectedResourcesURIs = selectResources();
    if ((null != selectedResourcesURIs) && !selectedResourcesURIs.isEmpty()) {
      // Gets all selected sessions.
      Iterator<?> iterator = getStructuredSelection().iterator();
      while (iterator.hasNext()) {
        Object object = iterator.next();
        if (object instanceof Session) {
          // Calls the command to add semantic resources.
          TransactionalEditingDomain domain = TransactionHelper.getEditingDomain((Session) object);
          RecordingCommand command = new AddSemanticResourceCommand(domain, "Add Semantic Resource", Collections.singletonList((Session) object), selectedResourcesURIs); //$NON-NLS-1$
          domain.getCommandStack().execute(command);
        }
      }
    }
  }

  // Opens a dialog to select the semantics resources.
  private Collection<URI> selectResources() {
    Shell activeShell = Display.getCurrent().getActiveShell();
    ResourceDialog resourceDialog = new ResourceDialog(activeShell, "Select resources to add", SWT.OPEN | SWT.MULTI); //$NON-NLS-1$
    if (Window.OK == resourceDialog.open()) {
      return resourceDialog.getURIs();
    }
    return Collections.<URI> emptyList();
  }

  // The command to add semantics resources.
  private class AddSemanticResourceCommand extends RecordingCommand {

    private List<Session> _selectedSessions;
    private TransactionalEditingDomain _domain;
    private Collection<URI> _uris;

    public AddSemanticResourceCommand(TransactionalEditingDomain domain_p, String label_p, List<Session> sessions_p, Collection<URI> uris_p) {
      super(domain_p, label_p);
      _selectedSessions = sessions_p;
      _domain = domain_p;
      _uris = uris_p;
    }

    @Override
    protected void doExecute() {
      for (URI uri : _uris) {
//        Resource resource = _domain.getResourceSet().getResource(uri, true);
        for (Session session : _selectedSessions) {
          session.addSemanticResource(uri, new NullProgressMonitor());
        }
      }
    }
  }
}
