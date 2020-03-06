/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.ui.search.searchfor;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public abstract class AbstractMetaModelParticipantsItemProvider implements ITreeContentProvider {
	/**
	 * {@inheritDoc}
	 */
    public Object[] getChildren(Object parentElement) {
    	return new Object[0];
    }
	/**
	 * {@inheritDoc}
	 */
    public Object getParent(Object element) {
    	return new Object[0];
    }
	/**
	 * {@inheritDoc}
	 */
    public boolean hasChildren(Object element) {
    	return false;
    }
	/**
	 * {@inheritDoc}
	 */
    public Object[] getElements(Object inputElement) {
    	return new Object[0];
    }
    
    public Object[] getElements() {
      return new Object[0];
    }
    
	/**
	 * {@inheritDoc}
	 */
    public void dispose() {}
	/**
	 * {@inheritDoc}
	 */
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {}
}
