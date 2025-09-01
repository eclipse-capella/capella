/*******************************************************************************
 * Copyright (c) 2025 Obeo.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.sirius.elk.properties;

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IOperationHistory;
import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.commands.operations.ObjectUndoContext;
import org.eclipse.core.commands.operations.OperationHistoryFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.workspace.IWorkspaceCommandStack;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.CompositeCommand;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.common.core.util.Log;
import org.eclipse.gmf.runtime.common.core.util.Trace;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.diagram.ui.properties.filters.DiagramEditPartPropertySectionFilter;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesDebugOptions;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesPlugin;
import org.eclipse.gmf.runtime.diagram.ui.properties.internal.DiagramPropertiesStatusCodes;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractNotationPropertiesSection;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.tools.api.command.EditingDomainUndoContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polarsys.capella.core.sirius.elk.CapellaGmfLayoutProvider;
import org.polarsys.capella.core.sirius.elk.DiagramLayoutMode;
import org.polarsys.capella.core.sirius.elk.Messages;

/**
 * Section in Property View to select Layout mode in diagrams.
 * 
 * @author nperansin
 */
@SuppressWarnings("restriction") // internal access for required override
public class DiagramLayoutProperty extends AbstractNotationPropertiesSection {

    /** Choice of layout mode. */
    protected CCombo layoutModeCombo;

    private boolean bIsCommandInProgress;
    
    /**
     * Class to enable DiagramLayoutProperty display.
     * <p>
     * For a diagram with several modes.
     * </p>
     */
    public static class Filter extends DiagramEditPartPropertySectionFilter {

        @Override
        public boolean select(Object toTest) {
            return toTest instanceof DiagramEditPart ep // only part
                    && ep.getModel() instanceof Diagram gmf // targeting
                    && gmf.getElement() instanceof DDiagram diagram // diagram root
                    && !getModes(diagram).isEmpty();
        }

    }

    @Override
    protected void initializeControls(Composite parent) {
        super.initializeControls(parent);

        // Other section always have container for a line of group.
        Composite lineGroup = getWidgetFactory().createComposite(composite);
        lineGroup.setLayout(new GridLayout(1, false));

        createLayoutGroup(lineGroup);
    }

    private void createLayoutGroup(Composite parent) {
        Group mainGroup = getWidgetFactory().createGroup(parent, Messages.SECTION_GROUP_NAME);
        GridLayout mainLayout = new GridLayout(2, false);
        mainGroup.setLayout(mainLayout);

        getWidgetFactory().createCLabel(mainGroup, Messages.SECTION_MODE_NAME);

        layoutModeCombo = getWidgetFactory().createCCombo(mainGroup, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.BORDER);

        layoutModeCombo.setItems(new String[0]);
        // layerCombo.setEditable(false);
        layoutModeCombo.addSelectionListener(SelectionListener.widgetSelectedAdapter(event -> updateLayerMode()));
    }

    private void updateLayerMode() {
        if (getEObject() instanceof View view && view.getElement() instanceof DDiagram diagram) {
            String commandName = Messages.SECTION_COMMAND_NAME;
            ICommand update = createCommand(commandName, diagram.eResource(), // single item
                    () -> updateModeInModel(diagram));
            executeAsCompositeCommand(commandName, List.of(update));
        }
    }
    
    private void updateModeInModel(DDiagram diagram) {
        int index = layoutModeCombo.getSelectionIndex();
        List<DiagramLayoutMode> modes = getModes(diagram);

        if (index < 0 || modes.size() <= index) {
            // Because of filter, modes cannot be empty.
            index = 0;
        }

        DiagramLayoutMode.setMode(diagram, modes.get(index));
    }

    @Override
    public void refresh() {
        if (!isDisposed() && getEObject() instanceof View view && view.getElement() instanceof DDiagram diagram) {
            executeAsReadAction(() -> updateModeFromModel(diagram));
        }
    }

    private void updateModeFromModel(DDiagram diagram) {
        String[] names = getModes(diagram).stream().map(DiagramLayoutMode::name).toArray(String[]::new);

        if (!Arrays.equals(names, layoutModeCombo.getItems())) {
            layoutModeCombo.setItems(names);
        }
        String modeName = names[0];

        DiagramLayoutMode mode = DiagramLayoutMode.getMode(diagram);
        if (mode != null) {
            modeName = mode.name();
        }
        layoutModeCombo.setText(modeName);
        layoutModeCombo.setEnabled(!isReadOnly());
    }
    
    private static List<DiagramLayoutMode> getModes(DDiagram diagram) {
        return CapellaGmfLayoutProvider.getDiagramConfigurations(diagram.getDescription());
    }
    
    //@formatter:off
    /*
     * Inline super implementation to undo context. (No other override point)
     */
    @Override
    protected CommandResult executeAsCompositeCommand(String actionName,
            List commands) {
        
        if (true == bIsCommandInProgress)
            return null;

        bIsCommandInProgress = true;

        CompositeCommand command = new CompositeCommand(actionName, commands);
        IOperationHistory history = OperationHistoryFactory.getOperationHistory();

        command.addContext(getUndoContext()); // Specific addendum
        
        try {
            IStatus status = history.execute(command,
                new NullProgressMonitor(), null);

            if (status.getCode() == DiagramPropertiesStatusCodes.CANCELLED
                    || status.getSeverity() == IStatus.CANCEL
                    || status.getSeverity() == IStatus.ERROR) {
                refresh();
            }

        } catch (ExecutionException e) {
            Trace.catching(DiagramPropertiesPlugin.getDefault(),
                DiagramPropertiesDebugOptions.EXCEPTIONS_CATCHING, getClass(),
                "executeAsCompositeCommand", e); //$NON-NLS-1$
            Log.error(DiagramPropertiesPlugin.getDefault(),
                DiagramPropertiesStatusCodes.IGNORED_EXCEPTION_WARNING, e
                    .getLocalizedMessage(), e);
        }

        bIsCommandInProgress = false;

        return command.getCommandResult();

    }
    //@formatter:on

    private IUndoContext getUndoContext() {
        IUndoContext result;
        final TransactionalEditingDomain domain = getEditingDomain();

        if (domain != null) {
            if (domain.getCommandStack() instanceof IWorkspaceCommandStack wcStack) {
                result = wcStack.getDefaultUndoContext();
            } else {
                result = new EditingDomainUndoContext(domain);
            }
        } else {
            result = new ObjectUndoContext(this);
        }

        return result;
    }
}
