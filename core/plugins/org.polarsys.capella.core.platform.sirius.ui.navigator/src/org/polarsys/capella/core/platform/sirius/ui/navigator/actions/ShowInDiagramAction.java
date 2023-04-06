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
package org.polarsys.capella.core.platform.sirius.ui.navigator.actions;

import static org.polarsys.capella.common.helpers.cache.ModelCache.getCache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.business.api.query.DDiagramElementQuery;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.CapellaAdapterHelper;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.platform.sirius.ui.navigator.CapellaNavigatorPlugin;
import org.polarsys.capella.core.sirius.analysis.DiagramServices;

/**
 * Show selected {@link ModelElement} in the active diagram editor.
 */
public class ShowInDiagramAction extends BaseSelectionListenerAction implements IViewActionDelegate {
  /**
   * Constructor.
   */
  public ShowInDiagramAction() {
    super(Messages.ShowInDiagramAction_Title);
    setActionDefinitionId("org.polarsys.capella.core.platform.sirius.ui.navigator.showInDiagramCommand"); //$NON-NLS-1$
  }

  /**
   * @see org.eclipse.jface.action.Action#run()
   */

  @Override
  public void run() {
    IStatus status = runWithStatus();

    if (!status.isOK()) {
      MessageDialog.openInformation(Display.getCurrent().getActiveShell(),
          Messages.ShowInDiagramAction_UnknownElement_Title, status.getMessage());
    }
  }

  @SuppressWarnings("unchecked")
  public IStatus runWithStatus() {

    List<Object> selectedElements = getStructuredSelection().toList();
    Collection<EObject> semanticElements = CapellaAdapterHelper.resolveBusinessObjects(selectedElements);
    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();

    Set<DDiagramElement> viewsFromEditor = getViewsFromEditor(activeEditor, semanticElements);

    if (viewsFromEditor.isEmpty()) {
      return new Status(IStatus.INFO, CapellaNavigatorPlugin.PLUGIN_ID,
          Messages.ShowInDiagramAction_UnknownElement_Message);
    }

    List<DDiagramElement> availableElements = viewsFromEditor.stream().filter(isElementAvailable())
        .collect(Collectors.toList());

    if (availableElements.isEmpty()) {
      // there are no available elements to display
      // return a status containing the reason
      DDiagramElement diagramElement = viewsFromEditor.iterator().next();
      String message = getUnavailableElementMessage(diagramElement);
      return new Status(IStatus.INFO, CapellaNavigatorPlugin.PLUGIN_ID, message);
    }

    if (activeEditor instanceof DialectEditor) {
      DialectUIManager.INSTANCE.selectAndReveal((DialectEditor) activeEditor, new ArrayList<>(availableElements));
    }

    return Status.OK_STATUS;
  }

  protected Set<DDiagramElement> getViewsFromEditor(IEditorPart activeEditor, Collection<EObject> semanticElements) {

    Set<DDiagramElement> allViewElements = new HashSet<>();

    if (activeEditor instanceof SiriusDiagramEditor) {
      SiriusDiagramEditor diagramEditor = (SiriusDiagramEditor) activeEditor;
      DRepresentation diagram = (DRepresentation) diagramEditor.getDiagram().getElement();

      for (EObject semanticElement : semanticElements) {
        Collection<DDiagramElement> currentViewElements = Collections.emptyList();

        if (semanticElement instanceof Component) {
          currentViewElements = getCache(ComponentExt::getRepresentingParts, (Component) semanticElement) //
              .stream() //
              .flatMap(part -> getDDiagramElements(diagram, part))//
              .collect(Collectors.toList());
        }

        if (currentViewElements.isEmpty()) {
          currentViewElements = getDDiagramElements(diagram, semanticElement).collect(Collectors.toList());
        }

        allViewElements.addAll(currentViewElements);
      }
    }

    return allViewElements;
  }

  private Stream<DDiagramElement> getDDiagramElements(DRepresentation diagram, EObject semantic) {
    return DiagramServices.getDiagramServices() //
        .getDiagramElements(diagram, semantic) //
        .stream() //
        .filter(DDiagramElement.class::isInstance) //
        .map(DDiagramElement.class::cast);
  }

  protected Predicate<DDiagramElement> isElementAvailable() {
    return element -> {
      DDiagramElementQuery query = new DDiagramElementQuery(element);
      return !(query.isFolded() || query.isHidden() || query.isCollapsed() || query.isFiltered());
    };
  }

  protected String getUnavailableElementMessage(DDiagramElement element) {

    String message = Messages.ShowInDiagramAction_UnknownElement_Message;
    DDiagramElementQuery query = new DDiagramElementQuery(element);

    if (query.isFolded()) {
      message = Messages.ShowInDiagramAction_FoldedElement_Message;

    } else if (query.isHidden()) {
      message = Messages.ShowInDiagramAction_HiddenElement_Message;

    } else if (query.isCollapsed()) {
      message = Messages.ShowInDiagramAction_CollapseElement_Message;

    } else if (query.isFiltered()) {
      message = Messages.ShowInDiagramAction_FilteredElement_Message;

    }

    return message;
  }

  /**
   * @see org.eclipse.ui.actions.BaseSelectionListenerAction#updateSelection(org.eclipse.jface.viewers.IStructuredSelection)
   */
  @Override
  protected boolean updateSelection(IStructuredSelection selection) {
    boolean result = false;

    if (!selection.isEmpty()) {
      result = CapellaResourceHelper.isSemanticElements(selection.toList());
    }

    IEditorPart activeEditor = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    result &= (activeEditor instanceof SiriusDiagramEditor);

    return result;
  }

  /**
   * @see org.eclipse.ui.IViewActionDelegate#init(org.eclipse.ui.IViewPart)
   */
  @Override
  public void init(IViewPart view) {
    // Do nothing.
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#run(org.eclipse.jface.action.IAction)
   */
  @Override
  public void run(IAction action) {
    run();
  }

  /**
   * @see org.eclipse.ui.IActionDelegate#selectionChanged(org.eclipse.jface.action.IAction,
   *      org.eclipse.jface.viewers.ISelection)
   */
  @Override
  public void selectionChanged(IAction action, ISelection selection) {
    selectionChanged((IStructuredSelection) selection);
  }
}
