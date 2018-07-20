/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.edit.commands;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.massactions.activator.MACapellaActivator;
import org.polarsys.capella.common.ui.massactions.shared.helper.CapellaMASelectionHelper;
import org.polarsys.kitalpha.massactions.edit.MEView;

/**
 * A command handler for the 'Send To Mass Edition' action.
 * 
 * @author Sandu Postaru
 *
 */
public class SentToMECommandHandler extends AbstractHandler {

  private static final Log log = LogFactory.getLog(SentToMECommandHandler.class);

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    String primaryViewId = event.getParameter(MACapellaActivator.SEND_TO_ME_VIEW_COMMAND_PARAMETER_PRIMARY_ID);
    String secondaryViewId = event.getParameter(MACapellaActivator.SEND_TO_ME_VIEW_COMMAND_PARAMETER_SECONDARY_ID);

    try {
      IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(primaryViewId,
          secondaryViewId, IWorkbenchPage.VIEW_VISIBLE);

      MEView meView = (MEView) viewPart;
      ISelection selection = HandlerUtil.getCurrentSelection(event);
      Collection<EObject> selectionData = CapellaMASelectionHelper.getElementsFromSelection(selection);

      meView.dataChanged(selectionData);

    } catch (PartInitException e) {
      log.error(e.getMessage());
    }

    // the result of the execution, must be null (see method documentation)
    return null;
  }

}
