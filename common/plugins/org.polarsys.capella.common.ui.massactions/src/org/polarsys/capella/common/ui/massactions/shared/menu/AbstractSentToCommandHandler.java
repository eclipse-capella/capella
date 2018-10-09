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
package org.polarsys.capella.common.ui.massactions.shared.menu;

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
import org.polarsys.kitalpha.massactions.edit.MEView;
import org.polarsys.kitalpha.massactions.shared.view.MAView;

/**
 * A abstract implementation command handler for the 'Send To' mass actions.
 * 
 * @author Ali Akar
 *
 */
public abstract class AbstractSentToCommandHandler extends AbstractHandler {

  private static final Log log = LogFactory.getLog(AbstractSentToCommandHandler.class);

  protected abstract String getCommandParameterPrimaryId();

  protected abstract String getCommandParameterSecondaryId();

  protected abstract String getCommandParameterShouldCreateViewId();

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    String primaryViewId = event.getParameter(getCommandParameterPrimaryId());
    String secondaryViewId = event.getParameter(getCommandParameterSecondaryId());
    boolean shouldCreateView = Boolean.parseBoolean(event.getParameter(getCommandParameterShouldCreateViewId()));

    // generate a fresh secondary view id, if a new view should be created
    secondaryViewId = shouldCreateView ? MEView.getSecondaryViewId() : secondaryViewId;

    try {
      IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(primaryViewId,
          secondaryViewId, IWorkbenchPage.VIEW_VISIBLE);

      MAView maView = (MAView) viewPart;
      if (shouldCreateView) {
        maView.setViewName(MAView.getViewName(maView.getPartName(), secondaryViewId));
      }

      ISelection selection = HandlerUtil.getCurrentSelection(event);
      Collection<EObject> selectionData = maView.getSelectionHelper().getElementsFromSelection(selection);

      maView.dataChanged(selectionData);

    } catch (PartInitException e) {
      log.error(e.getMessage());
    }

    // The result of the execution, must be null (see method documentation)
    return null;
  }
}
