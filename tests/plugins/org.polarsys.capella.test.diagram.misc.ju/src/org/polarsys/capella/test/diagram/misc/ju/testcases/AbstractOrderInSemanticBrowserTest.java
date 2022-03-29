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
package org.polarsys.capella.test.diagram.misc.ju.testcases;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.CategoryWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.core.libraries.model.ICapellaModel;
import org.polarsys.capella.core.libraries.utils.ScopeModelWrapper;
import org.polarsys.capella.core.ui.semantic.browser.sirius.view.SiriusSemanticBrowserView;
import org.polarsys.capella.core.ui.semantic.browser.view.SemanticBrowserView;
import org.polarsys.capella.shared.id.handler.IScope;
import org.polarsys.capella.shared.id.handler.IdManager;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * Test that the SB does not update its input when the synchronization is deactivated
 *
 */
public abstract class AbstractOrderInSemanticBrowserTest extends BasicTestCase {

    protected String projectTestName = "SBOrdering"; //$NON-NLS-1$
    
    protected SiriusSemanticBrowserView semanticBrowserViewer;
    
    protected IScope scope;

    @Override
    public List<String> getRequiredTestModels() {
        return Arrays.asList(projectTestName);
    }
    
    protected abstract String getScopeElement();
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        ICapellaModel model = getTestModel(projectTestName);
        scope = new ScopeModelWrapper(model);

        // Open SB
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
        semanticBrowserViewer = getSemanticBrowserViewer();

        // Reset the default listening state to avoid a potential corruption of following tests in the suite
        semanticBrowserViewer.activateListeningToPageSelectionEvents();
        
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().activate(semanticBrowserViewer);

        // Set input to model element
        EObject scenario = IdManager.getInstance().getEObject(getScopeElement(), scope);
        semanticBrowserViewer.saveInput(scenario, null);
    }
    
    @Override
    protected void tearDown() throws Exception {
        // Reset the default listening state to avoid a potential corruption of following tests in the suite
        semanticBrowserViewer.activateListeningToPageSelectionEvents();
        
        super.tearDown();
    }

    protected SiriusSemanticBrowserView getSemanticBrowserViewer() {
        final SiriusSemanticBrowserView[] viewer = new SiriusSemanticBrowserView[1];
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                viewer[0] = (SiriusSemanticBrowserView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(SemanticBrowserView.SEMANTIC_BROWSER_ID);
            }
        });
        return viewer[0];
    }

    protected void checkCategoryContentOrder(IScope scope, TreeItem categoryItem, String categoryName, String... expectedObject) {
        // Check CategoryWrapper
        assertTrue(categoryItem.getData() instanceof CategoryWrapper);
        assertTrue(((CategoryWrapper) categoryItem.getData()).getElement().getName().equals(categoryName));

        // Check EObjectWrappers
        TreeItem[] eObjectItems = categoryItem.getItems();
        for (int i = 0; i < eObjectItems.length; i++) {
            EObject message = IdManager.getInstance().getEObject(expectedObject[i], scope);
            EObjectWrapper wrappedMessage = new EObjectWrapper(message);
            assertEquals(wrappedMessage, eObjectItems[i].getData());
        }
    }

}
