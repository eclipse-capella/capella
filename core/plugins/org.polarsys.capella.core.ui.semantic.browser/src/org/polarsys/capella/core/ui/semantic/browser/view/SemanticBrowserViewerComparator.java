package org.polarsys.capella.core.ui.semantic.browser.view;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;
import org.polarsys.capella.common.ui.toolkit.browser.model.ISemanticBrowserModel;

public class SemanticBrowserViewerComparator extends ViewerComparator {

    ISemanticBrowserModel model;
    
    public SemanticBrowserViewerComparator(ISemanticBrowserModel model) {
        this.model = model;
    }

    @Override
    public void sort(Viewer viewer, Object[] elements) {
        if (!model.doesLexicographicSortTree()) {
            List<Object> elementsList = Arrays.asList(elements);
            if (elementsList.isEmpty() || (elementsList.get(0) instanceof EObjectWrapper)) {
                return;
            }
        }
        super.sort(viewer, elements);
    }
    
}
