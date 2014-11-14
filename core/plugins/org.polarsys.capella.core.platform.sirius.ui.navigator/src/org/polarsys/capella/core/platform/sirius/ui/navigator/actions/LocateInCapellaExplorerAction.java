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

package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.apache.log4j.Logger;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.ui.tools.api.views.common.item.ItemWrapper;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.tools.report.EmbeddedMessage;
import org.polarsys.capella.common.tools.report.config.registry.ReportManagerRegistry;
import org.polarsys.capella.common.tools.report.util.IReportManagerDefaultComponents;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * The action to locate a Capella model element into the Capella explorer from the diagram view.
 */
public class LocateInCapellaExplorerAction implements IObjectActionDelegate, IViewActionDelegate {
  private Logger __logger = ReportManagerRegistry.getInstance().subscribe(IReportManagerDefaultComponents.UI);
  private boolean _ignoreWorkbenchPartSite;
  private IWorkbenchPartSite _site;

  /**
   * Get the first selected element.
   * @param selection_p
   * @return <code>null</code> is returned if no selection or given selection is not {@link IStructuredSelection} instance.
   */
  protected Object getFirstSelectedElement(ISelection selection_p) {
    if (selection_p.isEmpty() || !(selection_p instanceof IStructuredSelection)) {
      return null;
    }
    IStructuredSelection structuredSelection = (IStructuredSelection) selection_p;
    return structuredSelection.getFirstElement();
  }

  /**
   * Get the selection.
   * @return <code>StructuredSelection.EMPTY</code> if no {@link IWorkbenchPart} is set to this action.
   */
  protected ISelection getSelection() {
    return (null != _site) ? _site.getSelectionProvider().getSelection() : StructuredSelection.EMPTY;
  }

  /**
   * Be careful, this methods is only called when this actions is contributed through a viewer contribution.
   * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
   */
  public void init(IViewPart view_p) {
    _site = view_p.getSite();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action_p) {
    if (_ignoreWorkbenchPartSite || (null != _site)) {
      ISelection selection = getSelection();
      if (selection instanceof IStructuredSelection) {
        Object uiSelectedElement = getFirstSelectedElement(selection);
        Object elementToSelectInCapellaExplorer = null;
        // If provided selection is a diagram or a table, let's select it in the capella explorer.
        if (uiSelectedElement instanceof ItemWrapper) {
          uiSelectedElement = ((ItemWrapper) uiSelectedElement).getWrappedObject();
        }
        if (uiSelectedElement instanceof DRepresentation) {
          elementToSelectInCapellaExplorer = uiSelectedElement;
        } else {
          // Get element from given selection.
          elementToSelectInCapellaExplorer = getElement(uiSelectedElement);
        }
        // Keep the double check here, as getSemanticElement can return error.
        if (CapellaResourceHelper.isSemanticElement(elementToSelectInCapellaExplorer) || (elementToSelectInCapellaExplorer instanceof DRepresentation)) {
          selectElementInCapellaExplorer(new StructuredSelection(elementToSelectInCapellaExplorer));
        }
      }
    }
  }

  /**
   * Set as new selection in the Capella Project Explorer given selection.
   * @param selection_p
   */
  protected void selectElementInCapellaExplorer(ISelection selection_p) {
    try {
      IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
      // Get the Capella Explorer.
      CapellaCommonNavigator explorerView = (CapellaCommonNavigator) activePage.findView(CapellaCommonNavigator.ID);
      if (null == explorerView) {
        // Show it if not found.
        explorerView = (CapellaCommonNavigator) activePage.showView(CapellaCommonNavigator.ID);
      }
      explorerView.selectReveal(selection_p);
    } catch (PartInitException exception_p) {
      __logger.warn(new EmbeddedMessage(exception_p.getMessage(), IReportManagerDefaultComponents.UI), exception_p);
    }
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action_p, ISelection selection_p) {
    // Do nothing here since we'd prefer getting the selection in a lazy way.
  }

  /**
   * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction, org.eclipse.ui.IWorkbenchPart)
   */
  public void setActivePart(IAction action_p, IWorkbenchPart targetPart_p) {
    _site = targetPart_p.getSite();
  }

  /**
   * For internal usage.
   * @param site_p
   */
  public void setSite(IWorkbenchPartSite site_p) {
    _site = site_p;
  }

  /**
   * Should ignore or not the workbench part site at runtime.
   * @param ignore_p
   */
  public void shouldIgnoreWorkbenchPartSite(boolean ignore_p) {
    _ignoreWorkbenchPartSite = ignore_p;
  }

  /**
   * Get the semantic element from given selected element.
   * @param uiSelectedElement_p
   * @return a semantic element or a {@link DRepresentation}.
   */
  public static Object getElement(Object uiSelectedElement_p) {
    Object result = null;
    // Precondition.
    if (null == uiSelectedElement_p) {
      return result;
    }
    if (CapellaResourceHelper.isSemanticElement(uiSelectedElement_p)) {
      result = uiSelectedElement_p;

    } else if (uiSelectedElement_p instanceof GraphicalEditPart) {
      GraphicalEditPart editPart = (GraphicalEditPart) uiSelectedElement_p;
      result = editPart.getModel();
      if (result instanceof View) {
        View view = (View) result;
        result = view.getElement();
      }
      if ((result instanceof DSemanticDecorator) && !(result instanceof DSemanticDiagram)) {
        DSemanticDecorator semanticDecorator = (DSemanticDecorator) result;
        result = semanticDecorator.getTarget();
      }
    } else if ((uiSelectedElement_p instanceof DSemanticDecorator) && !(uiSelectedElement_p instanceof DSemanticDiagram)) {
      DSemanticDecorator semanticDecorator = (DSemanticDecorator) uiSelectedElement_p;
      result = semanticDecorator.getTarget();
    } else if (uiSelectedElement_p instanceof EObjectWrapper) {
      result = ((EObjectWrapper) uiSelectedElement_p).getElement();
    }

    if (result instanceof Part) {
      boolean allowMultiplePart = TriStateBoolean.True.equals(CapellaProjectHelper.isReusableComponentsDriven((Part) result));
      if (!allowMultiplePart) {
        if (!(((Part) result).getAbstractType() instanceof ConfigurationItem)) {
          result = ((Part) result).getAbstractType();
        }
      }
    }

    return result;
  }
}
