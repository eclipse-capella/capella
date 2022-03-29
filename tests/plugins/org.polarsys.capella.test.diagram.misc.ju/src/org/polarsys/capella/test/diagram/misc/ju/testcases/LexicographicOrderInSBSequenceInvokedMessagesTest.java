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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeItem;
import org.polarsys.capella.core.ui.semantic.browser.actions.LexicographicSortTreeAction;

/**
 * Test that the SB Action "Lexicographic Sort Tree" does sorts lexicographically.
 *
 */
public class LexicographicOrderInSBSequenceInvokedMessagesTest extends AbstractOrderInSemanticBrowserTest {

    public static String SCOPE_ELEMENT = "f90ae45a-782f-46da-b9a6-3390ba91cd36"; //$NON-NLS-1$

    public static String SA_EI1_CREATE_MESSAGE = "934ff0c6-e564-492f-a441-7fa581109de2"; //$NON-NLS-1$

    public static String SA_EI1_MESSAGE = "32bebe45-d3e6-49e1-bf3e-86daefe635c5"; //$NON-NLS-1$

    public static String SA_EI2_WITH_REPLY_MESSAGE = "0d92d8f5-f483-4a70-b251-20949bf7eb0b"; //$NON-NLS-1$

    public static String SA_ARM_TIMER_MESSAGE = "9713e443-17d3-4f5f-9757-61f24fcb18df"; //$NON-NLS-1$

    public static String SA_CANCEL_TIMER_MESSAGE = "abca54a6-4bf1-41dd-ae6d-cd953d6b3b68"; //$NON-NLS-1$

    public static String SA_EI2_REPLY_MESSAGE = "0e8b4830-2233-4f50-90bc-197621962258"; //$NON-NLS-1$

    public static String SA_EI1_DELETE_MESSAGE = "47d09149-b3e5-4ab6-a57f-d1a45cd1dfd0"; //$NON-NLS-1$

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

        TreeViewer viewer = semanticBrowserViewer.getReferencedViewer();

        TreeItem categoryItem = viewer.getTree().getItem(0);
        checkCategoryContentOrder(scope, categoryItem, "Invoked Messages", SA_EI1_CREATE_MESSAGE, SA_EI1_MESSAGE, SA_EI2_WITH_REPLY_MESSAGE, 
                SA_ARM_TIMER_MESSAGE, SA_CANCEL_TIMER_MESSAGE, SA_EI2_REPLY_MESSAGE, SA_EI1_DELETE_MESSAGE);

        // 2 - Check lexicographic order
        action.setChecked(true);
        action.run();

        categoryItem = viewer.getTree().getItem(0);
        checkCategoryContentOrder(scope, categoryItem, "Invoked Messages", SA_ARM_TIMER_MESSAGE, SA_CANCEL_TIMER_MESSAGE, SA_EI1_CREATE_MESSAGE, 
                SA_EI1_MESSAGE, SA_EI1_DELETE_MESSAGE, SA_EI2_WITH_REPLY_MESSAGE, SA_EI2_REPLY_MESSAGE);
        
        // Set back to initial sorting value
        action.setChecked(initialValue);
        action.run();
    }
}
