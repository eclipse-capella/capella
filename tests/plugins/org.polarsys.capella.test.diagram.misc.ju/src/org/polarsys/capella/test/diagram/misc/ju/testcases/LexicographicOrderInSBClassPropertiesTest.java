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

import org.eclipse.swt.widgets.TreeItem;
import org.polarsys.capella.core.ui.semantic.browser.actions.LexicographicSortTreeAction;

/**
 * Test that the SB Action "Lexicographic Sort Tree" does sorts lexicographically.
 *
 */
public class LexicographicOrderInSBClassPropertiesTest extends AbstractOrderInSemanticBrowserTest {

    private static String SCOPE_ELEMENT = "3344baa0-d2c3-49a4-81b2-0a84ed281a05"; //$NON-NLS-1$

    public static String SA_PROPERTY_1 = "4d1c2f9b-6a44-4a53-9a82-ef987d0e2bde"; //$NON-NLS-1$

    public static String SA_PROPERTY_2 = "82436295-c65d-4826-be58-b594d02af7ac"; //$NON-NLS-1$

    public static String SA_PROPERTY_3 = "e4cd582c-e72d-4b24-b67b-865c886b2a20"; //$NON-NLS-1$

    @Override
    protected String getScopeElement() {
        return SCOPE_ELEMENT;
    }
    
    @Override
    public void test() throws Exception {
        // Backup initial sorting value
        boolean initialValue = semanticBrowserViewer.getModel().doesLexicographicSortTree();

        // 1 - Check default order
        LexicographicSortTreeAction action = new LexicographicSortTreeAction(semanticBrowserViewer);
        action.setChecked(false);
        action.run();
        
        TreeItem rootItem = semanticBrowserViewer.getCurrentViewer().getTree().getItem(0);
        TreeItem categoryItem = rootItem.getItems()[1];
        checkCategoryContentOrder(scope, categoryItem, "Properties", SA_PROPERTY_1, SA_PROPERTY_3, SA_PROPERTY_2);

        // 2 - Check lexicographic order
        action.setChecked(true);
        action.run();
        
        rootItem = semanticBrowserViewer.getCurrentViewer().getTree().getItem(0);
        categoryItem = rootItem.getItems()[1];
        checkCategoryContentOrder(scope, categoryItem, "Properties", SA_PROPERTY_1, SA_PROPERTY_2, SA_PROPERTY_3);
        
        // Set back to initial sorting value
        action.setChecked(initialValue);
        action.run();
    }
}
