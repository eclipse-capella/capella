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
package org.polarsys.capella.core.flexibility.commands.handlers;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.commands.AbstractUiHandler;
import org.polarsys.capella.common.mdsofa.common.misc.Couple;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.obfuscator.actions.ObfuscateSessionAction;
import org.polarsys.capella.core.sirius.ui.helper.SessionHelper;

/**
 */
public class ObfuscateModelHandler extends AbstractUiHandler {

  /**
   * {@inheritDoc}
   */
  public Object execute(final ExecutionEvent event_p) throws ExecutionException {
    final Logger logger = Logger.getLogger(IReportManagerDefaultComponents.MODEL);

    List<IFile> selection = getSelection(event_p, IFile.class);

    if ((selection != null) && (selection.size() > 0)) {

      for (IFile file : selection) {
        if (CapellaResourceHelper.isAirdResource(file, true)) {
          obfuscate(file, event_p);
        }
      }
    }
    logger.info("Obfuscation is made on a AIRD file with opened session");

    return null;
  }

  /**
   * @param file_p
   */
  protected void obfuscate(IFile file_p, final ExecutionEvent event_p) {
    final Logger logger = Logger.getLogger(IReportManagerDefaultComponents.MODEL);

    Session session = SessionHelper.getSession(file_p);
    if (session != null) {
      ObfuscateSessionAction action = new ObfuscateSessionAction() {
        @Override
        protected void obfuscateFile(IFile value_p, Session session_p) {

        }

        @Override
        protected void obfuscateFile(IFile value_p) {
          //Disable Repair/Migrate
        }
      };

      action.selectionChanged(new StructuredSelection(file_p));
      action.run();
      logger.info("Obfuscation is done (refresh is required!");
    }
  }

  protected IStructuredSelection getSelection() {
    IWorkbenchPart activePart = null;
    IWorkbench workbench = PlatformUI.getWorkbench();
    if (workbench != null) {
      IWorkbenchWindow windows = workbench.getActiveWorkbenchWindow();
      if (windows != null) {
        IWorkbenchPage page = windows.getActivePage();
        if (page != null) {
          activePart = page.getActivePart();
        }
      }
    }

    if (activePart != null) {
      ISelection selection = activePart.getSite().getSelectionProvider().getSelection();
      if (selection instanceof IStructuredSelection) {
        return (IStructuredSelection) selection;
      }
    }
    return new StructuredSelection();
  }

  @Override
  public boolean isEnabled() {
    IStructuredSelection selection = getSelection();
    if (selection.size() == 0) {
      return false;
    }
    List<Couple<Session, IFile>> sessions = org.polarsys.capella.core.sirius.ui.helper.SessionHelper.getSessionsFromSelection(selection);
    return (sessions.size() == selection.size());

  }

}
