/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.properties.richtext.sections;

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
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.polarsys.capella.core.model.handler.provider.CapellaReadOnlyHelper;
import org.polarsys.capella.core.model.handler.provider.IReadOnlySectionHandler;
import org.polarsys.capella.core.ui.properties.fields.AbstractSemanticField;
import org.polarsys.capella.core.ui.properties.richtext.fields.CapellaElementDescriptionGroup;
import org.polarsys.capella.core.ui.properties.sections.AbstractSection;

/**
 * Section that displays a {@link DRepresentation} properties.<br>
 * This implementation overrides common implementation to adapt it to {@link DRepresentation}.
 * 
 * @author Joao Barata
 */
public class DiagramDescriptionPropertySection extends AbstractSection {
    private WeakReference<DRepresentation> representation;

    protected CapellaElementDescriptionGroup descriptionGroup;

    /**
     * {@inheritDoc}
     */
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        // This operation history listener is used to force refreshes when undo / redo operations are performed.
        OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);

        TabbedPropertySheetWidgetFactory widgetFactory = getWidgetFactory();

        rootParentComposite.setLayout(new GridLayout());

        // Create Description text field.
        createDescriptionWidget(widgetFactory, rootParentComposite);
    }

    /**
     * Create description widget.
     * 
     * @param widgetFactory
     * @param textGroup
     */
    protected void createDescriptionWidget(TabbedPropertySheetWidgetFactory widgetFactory, Composite parent) {
        descriptionGroup = new CapellaElementDescriptionGroup(parent, widgetFactory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();

        if (null != representation) {
            representation.clear();
            representation = null;
        }

        if (null != descriptionGroup) {
            descriptionGroup.dispose();
            descriptionGroup = null;
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
                if (command.getAffectedObjects().contains(representation)) {
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
        if (null == representation.get()) {
            // This operation history listener is used to force refreshes when undo / redo operations are performed.
            OperationHistoryFactory.getOperationHistory().addOperationHistoryListener(this);
        }
        register(representation.get());

        // Disable the section if the element is read only.
        IReadOnlySectionHandler roHandler = CapellaReadOnlyHelper.getReadOnlySectionHandler();
        if ((roHandler != null) && roHandler.isLockedByOthers(representation.get())) {
            setEnabled(false);
        } else {
            setEnabled(true);
        }

        if (descriptionGroup != null) {
            descriptionGroup.loadData(representation.get(), DescriptionPackage.Literals.DOCUMENTED_ELEMENT__DOCUMENTATION);
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
    public boolean select(Object toTest) {
        return (toTest instanceof DRepresentationDescriptor) || (toTest instanceof DRepresentation) || (toTest instanceof IDDiagramEditPart);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        if (!selection.isEmpty()) {
            if (selection instanceof IStructuredSelection) {
                Object firstElement = ((IStructuredSelection) selection).getFirstElement();

                if (firstElement instanceof DRepresentationDescriptor) {
                    firstElement = ((DRepresentationDescriptor) firstElement).getRepresentation();
                }

                if (firstElement instanceof DRepresentation) {
                    representation = new WeakReference<DRepresentation>((DRepresentation) firstElement);
                } else if (firstElement instanceof IDDiagramEditPart) {
                    IDDiagramEditPart diagramEditPart = (IDDiagramEditPart) firstElement;
                    representation = new WeakReference<DRepresentation>((DRepresentation) ((Diagram) diagramEditPart.getModel()).getElement());
                } else {
                    representation = null;
                }
            }
            loadData();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        if (null != descriptionGroup) {
            descriptionGroup.setEnabled(enabled);
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
