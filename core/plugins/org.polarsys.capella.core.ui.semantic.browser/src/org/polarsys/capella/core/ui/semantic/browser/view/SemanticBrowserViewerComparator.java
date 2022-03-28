package org.polarsys.capella.core.ui.semantic.browser.view;

import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.polarsys.capella.common.ui.toolkit.browser.content.provider.wrapper.EObjectWrapper;

public class SemanticBrowserViewerComparator extends ViewerComparator {

    @Override
    public void sort(Viewer viewer, Object[] elements) {
        List<Object> elementsList = Arrays.asList(elements);
        if (elementsList.isEmpty() || (elementsList.get(0) instanceof EObjectWrapper)) {
            return;
        }
        super.sort(viewer, elements);
    }
    
}
