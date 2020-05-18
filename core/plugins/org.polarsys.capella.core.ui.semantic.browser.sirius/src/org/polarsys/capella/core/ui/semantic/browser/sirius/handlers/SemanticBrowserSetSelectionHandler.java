/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.semantic.browser.sirius.handlers;

import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.menus.UIElement;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.helper.StringHelper;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler;
import org.polarsys.capella.core.ui.semantic.browser.sirius.helpers.SiriusSelectionHelper;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;

/**
 * Handler to set the semantic browser with a new selection.
 */
public class SemanticBrowserSetSelectionHandler extends AbstractLocateInViewPartHandler {
  private static final Logger __logger = ReportManagerRegistry.getInstance()
      .subscribe(IReportManagerDefaultComponents.UI);

  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler#getTargetedPartId()
   */
  @Override
  protected String getTargetedPartId() {
    return SemanticBrowserView.SEMANTIC_BROWSER_ID;
  }

  /**
   * @see org.polarsys.capella.common.ui.services.commands.AbstractLocateInViewPartHandler#handleSelection(org.eclipse.jface.viewers.ISelection,
   *      org.eclipse.ui.IWorkbenchPart, org.eclipse.core.commands.ExecutionEvent)
   */
  @Override
  protected IViewPart handleSelection(ISelection selection, IWorkbenchPart activePart, ExecutionEvent event) {
    IViewPart targetedPart = super.handleSelection(selection, activePart, event);
    Object objectToSelect = SiriusSelectionHelper.handleSelection(activePart, selection, true);
    if (null == objectToSelect) {
      if (__logger.isDebugEnabled()) {
        StringBuilder loggerMessage = new StringBuilder(
            "SemanticBrowserSetSelectionHandler.handleSelection(..) _ No Object to select !"); //$NON-NLS-1$
        __logger.debug(loggerMessage.toString());
        __logger.debug(new EmbeddedMessage(loggerMessage.toString(), IReportManagerDefaultComponents.UI));
      }
      return null;
    }
    if (targetedPart instanceof SiriusSemanticBrowserView) {
      SiriusSemanticBrowserView semanticBrowserView = (SiriusSemanticBrowserView) targetedPart;
      semanticBrowserView.setInput(objectToSelect);
    }
    return targetedPart;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  public void updateElement(UIElement element_p, Map parameters_p) {
    IWorkbenchWindow workbenchWindow = (IWorkbenchWindow) parameters_p.get("org.eclipse.ui.IWorkbenchWindow"); //$NON-NLS-1$
    if (null != workbenchWindow) {
      if (null == workbenchWindow.getActivePage())
        return;
      IWorkbenchPart activePart = workbenchWindow.getActivePage().getActivePart();
      if (null == activePart || null == activePart.getSite())
        return;
      IWorkbenchPartSite site = activePart.getSite();
      if (null == site.getSelectionProvider())
        return;
      Object object = SiriusSelectionHelper.handleSelection(activePart, site.getSelectionProvider().getSelection(),
          true);
      if (object instanceof EObject) {
        String title = Messages.SelectInSemanticBrowserAction_LongTitle;
        // Adapt the title when action is displayed in Semantic Browser contextual menu.
        if (activePart instanceof SemanticBrowserView) {
          title = Messages.SelectInSemanticBrowserAction_ShortTitle;
        }
        title = StringHelper.formatMessage(title,
            new String[] { EObjectLabelProviderHelper.getText((EObject) object) });
        element_p.setText(title);
      }
    }
  }
}
