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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * TreeNodeContentProvider for Decomposition.
 */
public class DecompositionTreeNodeContentProvider extends TreeNodeContentProvider {
  
	private boolean synthesisCheckPage;
  protected DecompositionModel model;
  
  public DecompositionTreeNodeContentProvider(DecompositionModel model_p, boolean synthesisCheckPage_p) {
    setSynthesisCheckPage(synthesisCheckPage_p);
    model = model_p;
  }
  
  public DecompositionTreeNodeContentProvider(DecompositionModel model_p) {
    this(model_p, false);
  }

  /**
   * @see org.eclipse.jface.viewers.TreeNodeContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
   */
  @Override
  public void inputChanged(Viewer viewer_p, Object oldInput_p, Object newInput_p) {
    super.inputChanged(viewer_p, oldInput_p, newInput_p);
  }

  private List<DecompositionItem> getFilteredItems(DecompositionComponent component) {
  	List<DecompositionItem> res = new ArrayList<DecompositionItem>();
  	for (DecompositionItem item : component.getItems()) {
			if (!model.isTechnicalInterface(item) || !model.doesHideTechnicalInterfaces()) {
				res.add(item);
			}
		}
  	return res;
  }

  
  /**
   * @see org.eclipse.jface.viewers.TreeNodeContentProvider#getElements(java.lang.Object)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public Object[] getElements(Object inputElement_p) {
  	if (inputElement_p instanceof DecompositionModel) {
      return new DecompositionComponent[] { ((DecompositionModel) inputElement_p).getSourceComponent() };
    }
    if (inputElement_p instanceof DecompositionComponent) {
    	return getFilteredItems((DecompositionComponent) inputElement_p).toArray();
//    	return ((DecompositionComponent) inputElement_p).getItems().toArray();
    }
    if (inputElement_p instanceof Decomposition) {
      return ((Decomposition) inputElement_p).getTargetComponents().toArray();
    }
    if ((inputElement_p instanceof DecompositionItem) && isSynthesisCheckPage()) {
      DecompositionItem item = (DecompositionItem) inputElement_p;
      if (item.getStatus() != DecompositionItem.ASSIGNED)
        return item.getMessages().toArray();
    }
    if (inputElement_p instanceof List) {
      for (Object obj : (List) inputElement_p)
        return getElements(obj);
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.TreeNodeContentProvider#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object parentElement_p) {
  	if (parentElement_p instanceof DecompositionComponent) {
  		return getFilteredItems((DecompositionComponent) parentElement_p).toArray();
//      return ((DecompositionComponent) parentElement_p).getItems().toArray();
    }
    if (parentElement_p instanceof Decomposition) {
      return ((Decomposition) parentElement_p).getTargetComponents().toArray();
    }
    if ((parentElement_p instanceof DecompositionItem)) {
      DecompositionItem item = (DecompositionItem) parentElement_p;
      return item.getServiceItems().toArray();
    }
    return null;
  }

  /**
   * @see org.eclipse.jface.viewers.TreeNodeContentProvider#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element_p) {
  	if (element_p instanceof DecompositionComponent) {
      if (((DecompositionComponent) element_p).isSourceComponent()) {
        return element_p;
      }
      return ((DecompositionComponent) element_p).getParentDecomposition();
    }
    if (element_p instanceof Decomposition) {
      return ((Decomposition) element_p).getDecompositionModel();
    }
    if (element_p instanceof DecompositionItem) {
      return ((DecompositionItem) element_p).getParentComponent();
    }
    if (element_p instanceof DecompositionItemService) {
        return ((DecompositionItemService) element_p).getParentDecompositionItem();
      }
    return null;
  }
  
  /**
   * @see org.eclipse.jface.viewers.TreeNodeContentProvider#hasChildren(java.lang.Object)
   */
  @Override
  public boolean hasChildren(Object element_p) {
	  if (element_p instanceof DecompositionComponent) {
	  	return !getFilteredItems((DecompositionComponent) element_p).isEmpty();
	  }

	  if (element_p instanceof Decomposition) {
		  return !((Decomposition) element_p).getTargetComponents().isEmpty();
	  }

	  if ((element_p instanceof DecompositionItem)) {
		  DecompositionItem item = (DecompositionItem) element_p;
		  return !(item.getServiceItems().isEmpty());
	  }
	  

	  return false;
  }

  /**
   * @return the synthesisCheckPage
   */
  public boolean isSynthesisCheckPage() {
    return synthesisCheckPage;
  }

  /**
   * @param synthesisCheckPage_p the synthesisCheckPage to set
   */
  public void setSynthesisCheckPage(boolean synthesisCheckPage_p) {
    synthesisCheckPage = synthesisCheckPage_p;
  }

}
