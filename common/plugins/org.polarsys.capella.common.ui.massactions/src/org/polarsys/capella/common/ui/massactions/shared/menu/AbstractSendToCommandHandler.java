/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.shared.menu;

import java.text.MessageFormat;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.HandlerUtil;
import org.polarsys.capella.common.ui.massactions.shared.helper.CapellaMASelectionHelper;
import org.polarsys.kitalpha.massactions.edit.MEView;
import org.polarsys.kitalpha.massactions.shared.messages.Messages;
import org.polarsys.kitalpha.massactions.shared.view.MAView;

/**
 * A abstract implementation command handler for the 'Send To' mass actions.
 * 
 * @author Ali Akar
 *
 */
public abstract class AbstractSendToCommandHandler extends AbstractHandler {

  private static final Log log = LogFactory.getLog(AbstractSendToCommandHandler.class);

  protected abstract String getCommandParameterPrimaryId();

  protected abstract String getCommandParameterSecondaryId();

  protected abstract String getCommandParameterShouldCreateViewId();

  protected CapellaMASelectionHelper selectionHelper;

  public AbstractSendToCommandHandler() {
    this.selectionHelper = new CapellaMASelectionHelper();
  }

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {

    String primaryViewId = event.getParameter(getCommandParameterPrimaryId());
    String secondaryViewId = event.getParameter(getCommandParameterSecondaryId());
    boolean shouldCreateView = Boolean.parseBoolean(event.getParameter(getCommandParameterShouldCreateViewId()));

    // generate a fresh secondary view id, if a new view should be created
    secondaryViewId = shouldCreateView ? MEView.getSecondaryViewId() : secondaryViewId;

    try {
      // The current elements selected by the user
      ISelection selection = HandlerUtil.getCurrentSelection(event);
      Collection<EObject> selectionData = selectionHelper.getElementsFromSelection(selection);

      if (selectionHelper.selectionSharesSameEditingDomain(selectionData)) {
        IViewPart viewPart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
            .showView(primaryViewId, secondaryViewId, IWorkbenchPage.VIEW_VISIBLE);

        MAView maView = (MAView) viewPart;
        if (shouldCreateView) {
          maView.setViewName(MAView.getViewName(maView.getPartName(), secondaryViewId));
        }

        maView.dataChanged(selectionData);
      } else {
        Shell activeSell = Display.getDefault().getActiveShell();
        String dialogContent = MessageFormat.format(Messages.MA_VIEW_MESSAGE_INFO_2, selectionData.size());
        String dialogTitle = Messages.MA_VIEW_NAME;

        MessageDialog.openInformation(activeSell, dialogTitle, dialogContent);
      }

    } catch (PartInitException e) {
      log.error(e.getMessage());
    }

    // The result of the execution, must be null (see method documentation)
    return null;
  }
}
