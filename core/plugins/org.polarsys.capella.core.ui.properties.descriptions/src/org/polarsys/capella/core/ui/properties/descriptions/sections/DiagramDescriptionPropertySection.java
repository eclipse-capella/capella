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
package org.polarsys.capella.core.ui.properties.descriptions.sections;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.commands.operations.OperationHistoryEvent;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.workspace.EMFCommandOperation;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDDiagramEditPart;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.descriptions.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * Section that displays a {@link DRepresentation} properties.<br>
 * This implementation overrides common implementation to adapt it to {@link DRepresentation}.
 * 
 * @author Joao Barata
 */
public class DiagramDescriptionPropertySection extends AbstractSection {
  private WeakReference<DRepresentation> _representation;
  protected CapellaElementDescriptionGroup _descriptionGroup;

  /**
   * {@inheritDoc}
   */
  @Override
  public void createControls(Composite parent_p, TabbedPropertySheetPage aTabbedPropertySheetPage_p) {
    super.createControls(parent_p, aTabbedPropertySheetPage_p);
    // This operation history listener is used to force refreshes when undo / redo operations are performed.
    OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);

    TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

    _rootParentComposite.setLayout(new GridLayout());

    // Create Description text field.
    createDescriptionWidget(widgetFactory, _rootParentComposite);
  }

  /**
   * Create description widget.
   * @param widgetFactory_p
   * @param textGroup_p
   */
  protected void createDescriptionWidget(TabbedPropertySheetWidgetFactory widgetFactory_p, Composite parent_p) {
    _descriptionGroup = new CapellaElementDescriptionGroup(parent_p, widgetFactory_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    super.dispose();

    if (null != _representation) {
      _representation.clear();
      _representation = null;
    }

    if (null != _descriptionGroup) {
      _descriptionGroup.dispose();
      _descriptionGroup = null;
    }
  }

  /**
   * @see org.eclipse.core.commands.operations.IOperationHistoryListener#historyNotification(org.eclipse.core.commands.operations.OperationHistoryEvent)
   */
  @Override
  public void historyNotification(OperationHistoryEvent event_p) {
    // We only handle undo & redo operations to force a refresh.
    int eventType = event_p.getEventType();
    if ((OperationHistoryEvent.UNDONE == eventType) || (OperationHistoryEvent.REDONE == eventType)) {
      IUndoableOperation operation = event_p.getOperation();
      // Take into account the EMF command operation.
      if (operation instanceof EMFCommandOperation) {
        // Get the command.
        Command command = ((EMFCommandOperation) operation).getCommand();
        // Is the current melody element involved in this command ?
        if (command.getAffectedObjects().contains(_representation)) {
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
    // Register as operation history listener the first time capella element is set.
    if (null == _representation.get()) {
      // This operation history listener is used to force refreshes when undo / redo operations are performed.
      OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
    }
    register(_representation.get());

    // Disable the section if the element is read only.
    IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
    if ((roHandler != null) && roHandler.isLockedByOthers(_representation.get())) {
      setEnabled(false);
    } else {
      setEnabled(true);
    }

	if (_descriptionGroup != null) {
      _descriptionGroup.loadData(_representation.get(), DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refresh() {
    loadData();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean select(Object toTest_p) {
    return (toTest_p instanceof DRepresentation) || (toTest_p instanceof IDDiagramEditPart);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setInput(IWorkbenchPart part_p, ISelection selection_p) {
    if (!selection_p.isEmpty()) {
      if (selection_p instanceof IStructuredSelection) {
        Object firstElement = ((IStructuredSelection) selection_p).getFirstElement();
        if (firstElement instanceof DRepresentation) {
          _representation = new WeakReference<DRepresentation>((DRepresentation) firstElement);
        } else if (firstElement instanceof IDDiagramEditPart) {
          IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
          _representation = new WeakReference<DRepresentation>((DRepresentation) ((Diagram) diagramEditPart.getModel()).getElement());
        } else {
          _representation = null;
        }
      }
      loadData();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled_p) {
    super.setEnabled(enabled_p);

    if (null != _descriptionGroup) {
      _descriptionGroup.setEnabled(enabled_p);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<AbstractSemanticField> getSemanticFields() {
    return Collections.emptyList();
  }
}
