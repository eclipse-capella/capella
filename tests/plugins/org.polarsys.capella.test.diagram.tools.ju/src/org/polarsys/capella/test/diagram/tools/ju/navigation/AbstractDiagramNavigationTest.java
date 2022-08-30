/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.tools.ju.navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.common.ui.services.action.contributionitem.ContributionItemService;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.sirius.business.api.query.DRepresentationElementQuery;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.decoration.DecorationDescriptor;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.junit.Assert;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.diagram.common.ju.step.crud.OpenDiagramStep;
import org.polarsys.capella.test.diagram.common.ju.wrapper.utils.DiagramHelper;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class AbstractDiagramNavigationTest extends AbstractDiagramTestCase {

    Session session;

    SessionContext context;

    TransactionalEditingDomain ted;

    DiagramContext diagramContext;

    DDiagram diagram;

    @Override
    protected String getRequiredTestModel() {
        return "DiagramNavigationModel";
    }
    
    protected void runTestForDiagramNavigation(String diagramName, String focusElementId, Map<String, String> mapExpectedNavigationActions) throws Exception {
        session = getSessionForTestModel(getRequiredTestModel());
        context = new SessionContext(session);
        ted = session.getTransactionalEditingDomain();
        
        diagramContext = new OpenDiagramStep(context, diagramName).run().open();
        diagram = diagramContext.getDiagram();
        emptyEventsFromUIThread();
        IWorkbenchPage page = EclipseUIUtil.getActivePage();
        IEditorPart editor = EclipseUIUtil.getActiveEditor();
        
        assertNotNull("We should have an active page", page);
        
        assertTrue("We should have a DDiagramEditor", editor instanceof DDiagramEditor);
        
        // Set the focus
        final DDiagramEditor diagramEditor = (DDiagramEditor) editor;
        DRepresentationElement representationElement = setDiagramFocusToElement(diagramEditor, focusElementId);
        
        // Populate menu
        IMenuManager popupMenu = populateOpenMenu();

        // Check the open menu
        List<IContributionItem> actionContributions = checkOpenMenuContent(popupMenu);
        
        // Check the navigation action
        final List<DDiagramEditor> diagramEditors = checkNavigationAction(actionContributions, mapExpectedNavigationActions);
        
        // Check decorator
        checkDecoratorPresence(representationElement, true);

        // Cleanup
        DialectUIManager.INSTANCE.closeEditor(diagramEditor, false);
        for (DDiagramEditor diagramEditor2: diagramEditors) {
            DialectUIManager.INSTANCE.closeEditor(diagramEditor2, false);
        }
    }


    /**
     * Wait the end of the asynchronous calls waiting in UI thread.
     */
    protected static void synchronizationWithUIThread() {
        while (PlatformUI.getWorkbench().getDisplay().readAndDispatch()) {
            // Do nothing, just wait
        }
    }
    
    /**
     * Wait the end of the asynchronous calls in UI Thread and ignore the Exception. <B>Use this exclusively</B> in the
     * setup method to ensure a clean environment.
     */
    public static void emptyEventsFromUIThread() {
        boolean shouldRetry = false;
        do {
            try {
                synchronizationWithUIThread();
                shouldRetry = false;
                // CHECKSTYLE:OFF
            } catch (final Exception e) {
                // CHECKSTYLE:ON
                shouldRetry = true;
            }
        } while (shouldRetry);
    }

    protected DRepresentationElement setDiagramFocusToElement(final DDiagramEditor diagramEditor, final String elementId) {
        EObject semanticElement = context.getSemanticElement(elementId);
        DRepresentationElement representationElement = DiagramHelper.getOnDiagramNodeElement(diagram, semanticElement);
        List<DRepresentationElement> representationElementList = List.of(representationElement);
        DialectUIManager.INSTANCE.setSelection(diagramEditor, representationElementList);
        return representationElement;
    }

    protected List<DDiagramEditor> checkNavigationAction(List<IContributionItem> actionContributions, Map<String, String> mapExpectedNavigationActions) {
        List<DDiagramEditor> lstEditors = new ArrayList<>();
        assertTrue("ActionContributions count is not as expected", actionContributions.size() == mapExpectedNavigationActions.size());
        for (IContributionItem actionContribution: actionContributions) {
            assertTrue(actionContribution instanceof ActionContributionItem);
            final IAction action = ((ActionContributionItem)actionContribution).getAction();
            
            String actionText = action.getText();
            String expectedNavigationUid = mapExpectedNavigationActions.get(actionText);
            
            assertNotNull("Action does not have the expected text", expectedNavigationUid);
            action.run();
            IEditorPart editor2 = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
    
            assertTrue("We should have a DDiagramEditor", editor2 instanceof DDiagramEditor);
    
            final DDiagramEditor diagramEditor2 = (DDiagramEditor) editor2;
            DRepresentation representation = diagramEditor2.getRepresentation();
            
            assertTrue("We should have a DDiagram", representation instanceof DDiagram);
            lstEditors.add(diagramEditor2);
            DRepresentationQuery q = new DRepresentationQuery(representation);
            DRepresentationDescriptor representationDescriptor = q.getRepresentationDescriptor();
            assertTrue("Wrong target RepresentationDescriptor", representationDescriptor.getUid().equals(expectedNavigationUid));
        }
        return lstEditors;
    }

    protected List<IContributionItem> checkOpenMenuContent(IMenuManager popupMenu) {
        IMenuManager openMenu = (IMenuManager) popupMenu.find("popup.open");
        IContributionItem[] items = openMenu.getItems();

        List<IContributionItem> lstActionContributions = Arrays.asList(items).stream().filter(ActionContributionItem.class::isInstance).collect(Collectors.toList());
        assertFalse("There should be at least one ActionContributionItem", lstActionContributions.isEmpty());
        
        return lstActionContributions;
    }

    protected IMenuManager populateOpenMenu() {
        IMenuManager popupMenu = new MenuManager();
        popupMenu.add(new MenuManager("additions", "additions") {//$NON-NLS-1$ //$NON-NLS-2$
            @Override
            public boolean isGroupMarker() {
                return true;
            }
        });
        popupMenu.add(new MenuManager("popup.open", "popup.open") {//$NON-NLS-1$ //$NON-NLS-2$
            @Override
            public boolean isGroupMarker() {
                return true;
            }
        });
        ContributionItemService.getInstance().contributeToPopupMenu(popupMenu, EclipseUIUtil.getActivePage().getActivePart());
        return popupMenu;
    }
    

    protected void checkDecoratorPresence(DRepresentationElement representationElement, boolean expectedPresence) {
        Object decorationDescriptor = getDecorationDescriptor(representationElement);
        Assert.assertEquals("There should " + (expectedPresence ? "" : "not ") + "be a DecorationDescriptor for " + ((CapellaElement)representationElement.getTarget()).getFullLabel(), expectedPresence,
                decorationDescriptor instanceof DecorationDescriptor);
    }

    private Object getDecorationDescriptor(DRepresentationElement representationElement) {
        DRepresentationElementQuery dRepresentationElementQuery = new DRepresentationElementQuery(representationElement);
        DRepresentation parentRepresentation = dRepresentationElementQuery.getParentRepresentation();
        Object decorationDescriptor = null;
        Map<Object, Object> subDiagramDecorationDescriptors = null;
        if (parentRepresentation != null) {
            subDiagramDecorationDescriptors = parentRepresentation.getUiState().getSubDiagramDecorationDescriptors();
            decorationDescriptor = subDiagramDecorationDescriptors.get(representationElement);
        }
        return decorationDescriptor;
    }

    @Override
    protected void undoAllChanges() {
        // TODO Undo all changes does not work with this test
    }
}
