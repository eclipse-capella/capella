/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import org.polarsys.capella.core.commands.preferences.model.ICategoryTreeNode;
import org.polarsys.capella.core.commands.preferences.model.IItemNode;

/**
 * 
 *
 */
public class CategoriesPreferencesFilter extends ViewerFilter {

	 private String searchValue ;
	private ICategoryTreeNode parentCategory;
	
	public void setSearchValue(String searchValue_p) {
		searchValue = ".*" + searchValue_p + ".*"; 
	}
	
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean select(Viewer viewer_p, Object parentElement,
			Object element) {
		if (searchValue==null || searchValue.length()==0 ) {
			return true ;
		}
		
		if (element!=null && element instanceof ICategoryTreeNode) {
			
			ICategoryTreeNode item = (ICategoryTreeNode) element ;
			 return item.getCategory()!=null && item.getCategory().getName().matches(searchValue) ;
			 
		}
		
		
		return false;
	}
	
	
	
	
	 /**
     * Filters the given elements for the given viewer.
     * The input array is not modified.
     * <p>
     * The default implementation of this method calls 
     * <code>select</code> on each element in the array, 
     * and returns only those elements for which <code>select</code>
     * returns <code>true</code>.
     * </p>
     * @param viewer the viewer
     * @param parent the parent element
     * @param elements the elements to filter
     * @return the filtered elements
     */
	@Override
    public Object[] filter(Viewer viewer, Object parent, Object[] elements) {
        int size = elements.length;
        List<Object> out = new ArrayList<Object>(size);
        for (int i = 0; i < size; ++i) {
            Object element = elements[i];
            if (select(viewer, parent, element)) {
				out.add(element);
				if (element instanceof IItemNode) {
					IItemNode item = (IItemNode) element ;
				}
				
			}
        }
        return out.toArray();
    }


	
}
