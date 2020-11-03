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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.business.api.session.IEditingSession;
import org.eclipse.sirius.ui.business.api.session.SessionUIManager;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.core.model.handler.command.BasicRepresentationDeleteCommand;

/**
 *
 */
public class DeleteRepresentationCommand extends BasicRepresentationDeleteCommand {

  /**
   * Constructs the command allowing to delete representations.
   * @param domain
   * @param representations
   */
  public DeleteRepresentationCommand(TransactionalEditingDomain domain, Collection<DRepresentationDescriptor> representations) {
    this(domain, representations, new NullProgressMonitor());
  }

  public DeleteRepresentationCommand(TransactionalEditingDomain domain, Collection<DRepresentationDescriptor> representations, IProgressMonitor monitor) {
    super(domain, representations, monitor);
  }

  /**
   * Closes the opened editor about the current representation.
   */
  protected void closeActiveRepresentationEditor(DRepresentationDescriptor representation, Session session) {
    IEditingSession editingSession = SessionUIManager.INSTANCE.getUISession(session);
    if (null == editingSession) {
      return;
    }
    if (representation.isLoadedRepresentation()) {
      DialectEditor editor = editingSession.getEditor(representation.getRepresentation());
      if (editor != null) {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        // We detach the editor here because sometimes cause of the asyncExec of the closing, the detach could not be done during the dispose of the editor
        editingSession.detachEditor(editor);
      }
    }
  }
}
