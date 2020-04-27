/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.ui.toolkit.viewers.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;

import org.polarsys.capella.common.ui.services.helper.AdapterManagerHelper;
import org.polarsys.capella.common.ui.toolkit.viewers.ITreeContentAdapter;

/**
 * Tree data structure.<br>
 * Elements specified at construction time are considered as children. Their parents are computed to structure data as a tree.
 */
public class TreeData extends AbstractData {
  /**
   * Children for a root element.
   */
  Map<Object, Collection<Object>> _childrenForRootElements;

  /**
   * Constructor.
   * @param displayedElements
   * @param context optional parameter.
   */
  public TreeData(Collection<? extends Object> displayedElements, Object context) {
    super(displayedElements, context);
    // Lazy creation pattern, needed if displayedElements is empty to avoid _childrenForRootElements to be null.
    if (null == _childrenForRootElements) {
      _childrenForRootElements = new HashMap<Object, Collection<Object>>(0);
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#addElement(java.lang.Object)
   */
  @Override
  public void addElement(Object element) {
    // Lazy creation pattern.
    if (null == _childrenForRootElements) {
      _childrenForRootElements = new HashMap<Object, Collection<Object>>(0);
    }
    // Get the parent of given element.
    Object currentElement = element;
    Object parent = doGetParent(currentElement);
    if (null == parent) {
      // if null, given element is a root one.
      super.addElement(currentElement);
      return; // Stop recursing.
    }
    // Handle a child...
    do {
      // Get the children collection for retrieved parent.
      Collection<Object> children = _childrenForRootElements.get(parent);
      boolean shouldRecurseToBuildParentHierarchy = false;
      // Lazy creation pattern.
      if (null == children) {
        children = createChildrenCollection();
        _childrenForRootElements.put(parent, children);
        shouldRecurseToBuildParentHierarchy = true;
      }
      // Add element as child of retrieved parent.
      children.add(currentElement);
      if (shouldRecurseToBuildParentHierarchy) {
        // Current element is now the parent one.
        currentElement = parent;
        // Recurse...
        parent = doGetParent(currentElement);
        // When null is reached, add given element as a root one.
        if (null == parent) {
          super.addElement(currentElement);
        }
      } else {
        // No need to recurse since parent hierarchy is already known.
        parent = null;
      }
    } while (null != parent);
  }

  /**
   * In addition, clears children data structure.
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#clearData()
   */
  @Override
  protected void clearData() {
    super.clearData();
    _childrenForRootElements.clear();
  }

  /**
   * Create the collection used to store children for a root element.<br>
   * Default implementation uses a {@link HashSet}.
   * @return
   */
  protected Collection<Object> createChildrenCollection() {
    return new HashSet<Object>(1);
  }

  /**
   * Get a parent for given element.<br>
   * Default implementation is based on meta-model structure.
   * @param element
   * @return <code>null</code> if not found or filter out by {@link #filterComputedParent(Object)}.
   */
  protected Object doGetParent(Object element) {
    Object parent = null;
    if (!(element instanceof EObject)) {
      return parent;
    }
    EObject elt = (EObject) element;
    // Search for a "contributed" way to get a parent (ILinkSelection for instance).
    ITreeContentAdapter linkSelectionAdapter = null;
    Object context = getContext();
    if (null != context) {
      linkSelectionAdapter = (ITreeContentAdapter) AdapterManagerHelper.getAdapter(elt.eClass(), ITreeContentAdapter.class);// LinkSelectionProvider.getInstance().getContribution(element.eClass());
    }
    if (null != linkSelectionAdapter) {
      // Use it to get the parent.
      parent = linkSelectionAdapter.getParent(elt, (context == UNDEFINED_CONTEXT) ? null : context);
    } else {
      // Parent is computed from meta-model structure.
      IEditingDomainItemProvider provider = getItemProvider(elt);
      if (null != provider) {
        parent = provider.getParent(elt);
      } else {
        // Last chance based on containment.
        parent = elt.eContainer();
      }
    }
    return (null != parent) ? filterComputedParent(parent, element) /* Last chance to filter the computed parent */: null;
  }

  /**
   * Filter computed parent for specified element.<br>
   * Default behavior filters out EMF {@link Resource}.
   * @param parent computed parent for <code>element</code>
   * @param element
   * @return
   */
  protected Object filterComputedParent(Object parent, Object element) {
    if (parent instanceof Resource) {
      return null;
    }
    return parent;
  }

  /**
   * Children are computed from internal data structure not from meta-model.
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getChildren(java.lang.Object)
   */
  @Override
  public Object[] getChildren(Object element) {
    Object[] result = NO_CHILD;
    Collection<Object> children = _childrenForRootElements.get(element);
    if ((null != children) && !children.isEmpty()) {
      result = children.toArray();
    }
    return result;
  }

  /**
   * Get {@link IEditingDomainItemProvider} for given element.
   * @param element
   * @param result
   * @return <code>null</code> if not found.
   */
  private IEditingDomainItemProvider getItemProvider(EObject element) {
    IEditingDomainItemProvider result = null;
    AdapterFactoryEditingDomain editingDomain = (AdapterFactoryEditingDomain) AdapterFactoryEditingDomain.getEditingDomainFor(element);
    if (null != editingDomain) {
      result = (IEditingDomainItemProvider) editingDomain.getAdapterFactory().adapt(element, IEditingDomainItemProvider.class);
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getParent(java.lang.Object)
   */
  @Override
  public Object getParent(Object element) {
    Object result = null;
    Iterator<Entry<Object, Collection<Object>>> iterator = _childrenForRootElements.entrySet().iterator();
    // Iterate over children map to search for a children collection that contains given element.
    while (iterator.hasNext()) {
      Map.Entry<Object, Collection<Object>> entry = iterator.next();
      if (entry.getValue().contains(element)) {
        result = entry.getKey();
        break;
      }
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#removeAllElements(java.lang.Object[])
   */
  @Override
  public void removeAllElements(Object[] elements) {
    super.removeAllElements(elements);
    if (getValidElements().isEmpty()) {
      clearData();
    }
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#removeElement(java.lang.Object)
   */
  @Override
  public void removeElement(Object element) {
    super.removeElement(element);

    // Check if given element to remove has children ?
    Collection<Object> childrenForElement = _childrenForRootElements.get(element);
    if (null != childrenForElement) {
      if (childrenForElement.isEmpty()) {
        _childrenForRootElements.remove(element);
      } else {
        // Not empty node, don't remove it.
        return;
      }
    }
    // Remove given element as a leaf.
    // Clone entry set to avoid concurrent modification exceptions.
    Set<Entry<Object, Collection<Object>>> entrySet = new HashSet<Entry<Object, Collection<Object>>>(_childrenForRootElements.entrySet());
    Iterator<Entry<Object, Collection<Object>>> iterator = entrySet.iterator();
    // Iterate over children map to search for a children collection that contains given element.
    HashSet<Object> removedNodes = new HashSet<Object>(0);
    while (iterator.hasNext()) {
      Map.Entry<Object, Collection<Object>> entry = iterator.next();
      // Remove it from the children set.
      Collection<Object> values = entry.getValue();
      if (values.remove(element)) {
        Object parent = entry.getKey();
        if (values.isEmpty() && !isValid(parent)) {
          removedNodes.add(parent);
          iterator.remove();
        }
      }
    }
    // Handle removed nodes due to a leaf removal.
    Iterator<Object> removedNodesIterator = removedNodes.iterator();
    while (removedNodesIterator.hasNext()) {
      Object removedNode = removedNodesIterator.next();
      removeElement(removedNode);
    }
  }
}
