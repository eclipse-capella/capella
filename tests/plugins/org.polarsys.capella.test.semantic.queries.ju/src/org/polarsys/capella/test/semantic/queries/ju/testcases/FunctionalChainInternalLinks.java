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
package org.polarsys.capella.test.semantic.queries.ju.testcases;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.libraries.IModel;
import org.polarsys.capella.common.ui.toolkit.browser.category.ICategory;
import org.polarsys.capella.core.data.fa.FunctionInputPort;
import org.polarsys.capella.core.data.fa.FunctionOutputPort;
import org.polarsys.capella.core.model.helpers.graph.InternalLinksGraph.InternalLinkEdge;
import org.polarsys.capella.test.framework.helpers.EObjectHelper;
import org.polarsys.capella.test.semantic.queries.ju.model.SemanticQueries;

public class FunctionalChainInternalLinks extends SemanticQueries {
    String QUERY = "org.polarsys.capella.core.semantic.queries.technical.queries.internalLinks";

    @Override
    protected String getQueryCategoryIdentifier() {
        return QUERY;
    }

    @Override
    public void test() throws Exception {
        /**
         * Code taken from
         * {@code org.polarsys.capella.test.semantic.queries.ju.AbstractSemanticQueryTestCase.testQuery(String, Collection<String>)}
         * as the query results provides technical elements which does not compare as CapellaElements.
         */

        // First we get the eobject on which to test the query
        IModel model = getTestModel(getRequiredTestModels().iterator().next());
        EObject testObject = EObjectHelper.getObject(model, PA__FUNCTIONAL_CHAIN);

        // Find the category related to the query
        ICategory category = getCategory(getQueryCategoryIdentifier());

        // Category cannot be found in the registry
        if (category == null) {
            assertTrue(NLS.bind("Query {0} doesn't exist", getQueryCategoryIdentifier()), false);
        }

        // Query is incompatible with the type of the object
        if (!category.isAvailableForType(testObject)) {
            assertTrue(NLS.bind("Query {0} is not applicable for {1}", getQueryCategoryIdentifier(), EObjectLabelProviderHelper.getText(testObject)), false);
        }

        // Query has been found! We execute it.
        List<Object> objResult = category.compute(testObject);

        // Ensure we have the correct number of elements
        assertEquals(3, objResult.size());

        /**
         * Assert those are indeed internal: Input Port to Output Port Edge and Input/Output Ports are from the same
         * Function.
         */
        for (int i = 0; i < objResult.size(); i++) {
            InternalLinkEdge edge = (InternalLinkEdge) objResult.get(i);
            assertTrue(edge.getSource().getSemantic() instanceof FunctionInputPort);
            assertTrue(edge.getTarget().getSemantic() instanceof FunctionOutputPort);
            assertEquals(edge.getSource().getSemantic().eContainer(), edge.getTarget().getSemantic().eContainer());
        }

    }
}
