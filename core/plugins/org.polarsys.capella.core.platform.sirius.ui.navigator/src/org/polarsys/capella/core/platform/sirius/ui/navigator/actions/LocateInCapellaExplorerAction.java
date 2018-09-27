/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.osgi.util.NLS;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IShowInTarget;
import org.eclipse.ui.part.ShowInContext;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.ui.services.helper.EObjectImageProviderHelper;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.TriStateBoolean;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.platform.sirius.ui.navigator.view.CapellaCommonNavigator;

/**
 * The action to locate a Capella model element into the Capella explorer from the diagram view.
 */
public class LocateInCapellaExplorerAction implements IObjectActionDelegate, IViewActionDelegate {
  private boolean ignoreWorkbenchPartSite;
  private IWorkbenchPartSite site;
  ISelection selection = null;

  public static IAction createLocateTowards(EObject referenced, String message, boolean useElementIcon) {
    LocateInCapellaExplorerAction goToAction = new LocateInCapellaExplorerAction() {

      @Override
      protected ISelection getSelection() {
        return new StructuredSelection(referenced);
      }

      @Override
      public void run(IAction action) {
        Object elementToSelectInCapellaExplorer = referenced;
        // Keep the double check here, as getSemanticElement can return an element not from the model.
        selectElementInCapellaExplorer(new StructuredSelection(elementToSelectInCapellaExplorer));
      }
    };

    IAction action = new Action() {
      @Override
      public void run() {
        goToAction.run(this);
      }
    };

    // Ignore workbench part site, since in a dialog, site has no meaning.
    goToAction.shouldIgnoreWorkbenchPartSite(true);

    if (useElementIcon) {
      action.setImageDescriptor(ImageDescriptor.createFromImage(EObjectImageProviderHelper.getImage(referenced)));
    } else {
      action.setImageDescriptor(CapellaNavigatorPlugin.getDefault().getImageDescriptor("capella_16.png"));
    }
    action.setText(NLS.bind(message, EObjectLabelProviderHelper.getText(referenced)));
    return action;
  }

  /**
   * Get the first selected element.
   * 
   * @param selection
   * @return <code>null</code> is returned if no selection or given selection is not {@link IStructuredSelection}
   *         instance.
   */
  protected Object getFirstSelectedElement(ISelection selection) {
    if (selection.isEmpty() || !(selection instanceof IStructuredSelection)) {
      return null;
    }
    IStructuredSelection structuredSelection = (IStructuredSelection) selection;
    return structuredSelection.getFirstElement();
  }

  /**
   * Get the selection.
   * 
   * @return <code>StructuredSelection.EMPTY</code> if no {@link IWorkbenchPart} is set to this action.
   */
  protected ISelection getSelection() {
    return selection;
  }

  /**
   * Be careful, this methods is only called when this actions is contributed through a viewer contribution.
   * 
   * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
   */
  public void init(IViewPart view) {
    site = view.getSite();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  public void run(IAction action) {
    if (ignoreWorkbenchPartSite || (null != site)) {
      if (selection instanceof IStructuredSelection) {
          selectElementInCapellaExplorer(selection);
      }
    }
  }

  /**
   * Set as new selection in the Capella Project Explorer given selection.
   * 
   * @param selection
   */
  protected void selectElementInCapellaExplorer(ISelection selection) {
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      IViewPart part = window.getActivePage().findView(CapellaCommonNavigator.ID); //$NON-NLS-1$
      if (part != null) {
        IShowInTarget showInTarget = part.getAdapter(IShowInTarget.class);
        showInTarget.show(new ShowInContext(null, selection));
      }
    }
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  public void selectionChanged(IAction action, ISelection selection) {
    // Do nothing here since we'd prefer getting the selection in a lazy way.
    this.selection = selection;
  }

  /**
   * @see org.eclipse.ui.IObjectActionDelegate#setActivePart(org.eclipse.jface.action.IAction,
   *      org.eclipse.ui.IWorkbenchPart)
   */
  public void setActivePart(IAction action, IWorkbenchPart targetPart) {
    this.site = targetPart.getSite();
  }

  /**
   * For internal usage.
   * 
   * @param site
   */
  public void setSite(IWorkbenchPartSite site) {
    this.site = site;
  }

  /**
   * Should ignore or not the workbench part site at runtime.
   * 
   * @param ignore
   */
  public void shouldIgnoreWorkbenchPartSite(boolean ignore) {
    ignoreWorkbenchPartSite = ignore;
  }

  /**
   * Get the semantic element from given selected element.
   * 
   * @param uiSelectedElement
   * @return a semantic element or a {@link DRepresentation}.
   */
  public static Object getElement(Object uiSelectedElement) {
    Object semanticElement = CapellaAdapterHelper.resolveSemanticObject(uiSelectedElement, true);
    
    if (semanticElement instanceof Part) {
      boolean allowMultiplePart = TriStateBoolean.True
          .equals(CapellaProjectHelper.isReusableComponentsDriven((Part) semanticElement));
      if (!allowMultiplePart && !(((Part) semanticElement).getAbstractType() instanceof ConfigurationItem)) {
        semanticElement = ((Part) semanticElement).getAbstractType();
      }
    }

    return semanticElement;
  }
}
