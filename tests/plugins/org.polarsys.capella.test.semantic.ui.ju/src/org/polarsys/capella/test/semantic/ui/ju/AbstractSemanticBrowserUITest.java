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
package org.polarsys.capella.test.semantic.ui.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.ui.tools.api.editor.DDiagramEditor;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.WorkbenchException;
import org.eclipse.ui.intro.IIntroPart;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.test.diagram.common.ju.api.AbstractDiagramTestCase;
import org.polarsys.capella.test.diagram.common.ju.context.DiagramContext;
import org.polarsys.capella.test.framework.context.SessionContext;

public abstract class AbstractSemanticBrowserUITest extends AbstractDiagramTestCase {

    public static final String SB_VIEW_ID = SemanticBrowserView.SEMANTIC_BROWSER_ID;
    
    public static final String SB_REFERENCED_VIEWER_NAME = "referenced";
    public static final String SB_REFERENCING_VIEWER_NAME = "referencing";
    public static final String SB_CURRENT_VIEWER_NAME = "current";
    
    public static final String OPEN_REPRESENTATION_ACTION_TITLE = "Open Diagram / Table...";
    
    Session session;

    SessionContext context;

    TransactionalEditingDomain ted;

    DiagramContext diagramContext;

    DDiagram diagram;

    @Override
    protected String getRequiredTestModel() {
        return "DiagramNavigationModel";
    }
    
    protected void init() {
        session = getSessionForTestModel(getRequiredTestModel());
        context = new SessionContext(session);
        ted = session.getTransactionalEditingDomain();
    }
    
    protected List<IContributionItem> getSBViewerOpenMenuContributions(String inputElement, String elementToSelectInSBView, String viewer) {
        // Cleanup workbench
        IIntroPart introPart = PlatformUI.getWorkbench().getIntroManager().getIntro();
        PlatformUI.getWorkbench().getIntroManager().closeIntro(introPart);
        try {
            PlatformUI.getWorkbench().showPerspective("capella.sirius.perspective", PlatformUI.getWorkbench().getActiveWorkbenchWindow());
        } catch (WorkbenchException e) {
            e.printStackTrace();
        }
        emptyEventsFromUIThread();
        
        IViewPart view = EclipseUIUtil.showView(SB_VIEW_ID);
        emptyEventsFromUIThread();
        assertTrue(view instanceof SemanticBrowserView);
        SemanticBrowserView sbView = (SemanticBrowserView) view;
        
        EObject semanticInputElement = context.getSemanticElement(inputElement);
        sbView.setInput(semanticInputElement);
                
        EObject semanticElement = context.getSemanticElement(elementToSelectInSBView);
        
        TreeViewer treeViewer = null;
        if (viewer.equals(SB_REFERENCED_VIEWER_NAME)) {
            treeViewer = sbView.getReferencedViewer();
        } else if (viewer.equals(SB_REFERENCING_VIEWER_NAME)) {
            treeViewer = sbView.getReferencingViewer();
        } else {
            treeViewer = sbView.getCurrentViewer();
        }
        StructuredSelection structuredSelection = new StructuredSelection(new EObjectWrapper(semanticElement));
                
        treeViewer.setSelection(structuredSelection, true);
        emptyEventsFromUIThread();
        
        MenuManager menuMgr = new MenuManager();
        menuMgr.setRemoveAllWhenShown(true);
        Menu menu = menuMgr.createContextMenu(treeViewer.getTree());
        treeViewer.getControl().setMenu(menu);
        sbView.getSite().registerContextMenu(menuMgr, treeViewer);
        menu.notifyListeners(SWT.Show, null);
        MenuItem[] items = menu.getItems();
        List<MenuItem> openReItem = Arrays.asList(items).stream().filter(it -> it.getText().equals(OPEN_REPRESENTATION_ACTION_TITLE)).collect(Collectors.toList());
        assertTrue("There should be a menu Open", openReItem.size() == 1);
        Menu subMenu = openReItem.get(0).getMenu();
        subMenu.notifyListeners(SWT.Show, null);
        MenuItem[] subItems = subMenu.getItems();
        List<IContributionItem> result = new ArrayList<>();
        Arrays.asList(subItems).stream().filter(it -> it.getData() instanceof ActionContributionItem).forEach(it -> result.add((ActionContributionItem)it.getData()));
        
        subMenu.dispose();
        menu.dispose();
        
        return result;
    }
    
    protected void runTestSBViewerOpenMenu(String inputElement, String elementToSelectInSBView, String viewer, Map<String, String> mapExpectedNavigationActions) throws Exception {
        session = getSessionForTestModel(getRequiredTestModel());
        context = new SessionContext(session);
        ted = session.getTransactionalEditingDomain();
     
        // Check the open menu
        List<IContributionItem> actionContributions = getSBViewerOpenMenuContributions(inputElement, elementToSelectInSBView, viewer);

        // Check the navigation action
        final List<DDiagramEditor> diagramEditors = checkNavigationAction(actionContributions, mapExpectedNavigationActions);
        
        // Cleanup
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

    @Override
    protected void undoAllChanges() {
        // TODO Undo all changes does not work with this test
    }
}
