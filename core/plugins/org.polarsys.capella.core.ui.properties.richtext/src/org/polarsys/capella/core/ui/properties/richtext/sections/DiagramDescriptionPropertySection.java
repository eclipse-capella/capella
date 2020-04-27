/*******************************************************************************
 * Copyright (c) 2017, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.properties.richtext.sections;

import java.lang.ref.WeakReference;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.polarsys.capella.core.model.handler.helpers.RepresentationHelper;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;

/**
 * Section that displays a {@link DRepresentation} properties.<br>
 * This implementation overrides common implementation to adapt it to {@link DRepresentation}.
 * 
 * @author Joao Barata
 * 
 * @deprecated This class has been replaced by {@link CapellaDescriptionPropertySection}
 */
@Deprecated
public class DiagramDescriptionPropertySection extends DescriptionPropertySection {
  private WeakReference<DRepresentationDescriptor> representationDescriptor;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createContents(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
    super.createContents(parent, aTabbedPropertySheetPage);
    // This operation history listener is used to force refreshes when undo / redo operations are performed.
    OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();
    if (null != representationDescriptor) {
      representationDescriptor.clear();
      representationDescriptor = null;
    }
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  @Override
  public void historyNotification(OperationHistoryEvent event) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current melody element involved in this command ?
        if (command.getAffectedObjects().contains(representationDescriptor)) {
          // If so, let's refresh the content.
          refresh();
        }
      }
    }
  }

  /**
   * Reload widgets according to data model.
   */
  public void loadData() {
    if (null == representationDescriptor) {
      return;
    }
    // Register as operation history listener the first time capella element is set.
    if (null == representationDescriptor.get()) {
      // This operation history listener is used to force refreshes when undo / redo operations are performed.
      OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
    }
    register(representationDescriptor.get());

    // Disable the section if the element is read only.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(representationDescriptor.get())) {
      setEnabled(false);
    } else {
      setEnabled(true);
    }

    EAttribute descriptionAttribute = DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION;

    if (descriptionGroup != null) {
      descriptionGroup.loadData(representationDescriptor.get(), descriptionAttribute);
    } else if (descriptionFallbackGroup != null) {
      descriptionFallbackGroup.loadData(representationDescriptor.get(), descriptionAttribute);
    }
  }

  @Override
  public void refresh() {
    if (shouldRefresh()) {
      loadData();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest) {
    return (toTest instanceof DRepresentationDescriptor) || (toTest instanceof DRepresentation)
        || (toTest instanceof IDDiagramEditPart);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part, ISelection selection) {
    if (!selection.isEmpty()) {
      if (selection instanceof IStructuredSelection) {
        Object firstElement = ((IStructuredSelection) selection).getFirstElement();
        representationDescriptor = getRepresentationDescriptor(firstElement);
      }
      loadData();
    }
  }

  protected WeakReference<DRepresentationDescriptor> getRepresentationDescriptor(Object firstElement) {
    if (firstElement instanceof DRepresentationDescriptor) {
      return new WeakReference<>((DRepresentationDescriptor) firstElement);

    } else if (firstElement instanceof DRepresentation) {
      DRepresentationDescriptor descriptor = RepresentationHelper
          .getRepresentationDescriptor((DRepresentation) firstElement);
      return new WeakReference<>(descriptor);

    } else if (firstElement instanceof IDDiagramEditPart) {
      IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
      Diagram diagram = (Diagram) diagramEditPart.getModel();
      DRepresentation representation = (DRepresentation) diagram.getElement();
      DRepresentationDescriptor descriptor = RepresentationHelper.getRepresentationDescriptor(representation);

      return new WeakReference<>(descriptor);
    }
    return null;
  }
}
