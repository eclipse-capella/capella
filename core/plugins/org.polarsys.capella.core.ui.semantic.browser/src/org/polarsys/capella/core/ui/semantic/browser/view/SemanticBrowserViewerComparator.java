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
package org.polarsys.capella.core.ui.semantic.browser.view;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

/**
 * When SemanticBrowser LexicographicSortTree option is active and the elements to sort are {@code EObjectWrapper}s
 * containing {@code DRepresentationDescriptor}s then do not sort the content as the expected order is already provided
 * by the query.
 * 
 * When SemanticBrowser LexicographicSortTree option is not active and the elements to sort are {@code EObjectWrapper}s
 * then this comparator does nothing to prevent the default {@code ViewerComparator} comparison (lexicographic) and keep
 * the order of elements provided by queries.
 */
public class SemanticBrowserViewerComparator extends ViewerComparator {

    ISemanticBrowserModel model;

    public SemanticBrowserViewerComparator(ISemanticBrowserModel model) {
        this.model = model;
    }

    @Override
    public void sort(Viewer viewer, Object[] elements) {
        List<Object> elementsList = Arrays.asList(elements);
        if (!model.doesLexicographicSortTree()) {
            if (elementsList.isEmpty() || (elementsList.get(0) instanceof EObjectWrapper)) {
                return;
            }
        } else if (elementsList.isEmpty() || ((elementsList.get(0) instanceof EObjectWrapper) && (((EObjectWrapper) elementsList.get(0)).getElement() instanceof DRepresentationDescriptor))) {
            return;
        }
        super.sort(viewer, elements);
    }

}
