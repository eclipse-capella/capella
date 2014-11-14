/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.toolkit.viewers.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * Base class data.
 */
public abstract class AbstractData {
  /**
   * No child constant.
   */
  public static final Object[] NO_CHILD = new Object[] {};
  /**
   * Undefined context constant.<br>
   * Use this context to enable computation on {@link ILinkSelection} contribution mechanism without an explicit context object.
   */
  public static final Object UNDEFINED_CONTEXT = new Object();
  /**
   * Context element used for usage based on {@link ILinkSelection}.
   */
  private Object _context;
  /**
   * Object notified when valid elements change.
   */
  private Runnable _notifiedForValidElementsChanges;
  /**
   * Set of root elements.
   */
  Collection<Object> _rootElements;
  /**
   * Valid elements are elements.
   */
  Collection<Object> _validElements;

  /**
   * Constructor.
   * @param displayedElements_p
   * @param context_p optional parameter.
   */
  protected AbstractData(Collection<? extends Object> displayedElements_p, Object context_p) {
    _context = context_p;
    _rootElements = initializeRootElementCollection();
    _validElements = initializeValidElementCollection(displayedElements_p);
    for (Object modelElement : displayedElements_p) {
      addElement(modelElement);
    }
  }

  /**
   * Add all given elements.
   * @param elements_p must be not <code>null</code>.
   */
  public void addAllElements(Object[] elements_p) {
    for (Object element : elements_p) {
      addElement(element);
      addValidElement(element);
    }
  }

  /**
   * Add given element in root ones.
   * @param element_p
   */
  public void addElement(Object element_p) {
    _rootElements.add(element_p);
  }

  /**
   * Add given element in valid ones.
   * @param element_p
   */
  protected void addValidElement(Object element_p) {
    _validElements.add(element_p);
  }

  /**
   * Clear data.<br>
   * Default implementation clears root elements.
   */
  protected void clearData() {
    _rootElements.clear();
  }

  /**
   * Get children for given element.
   * @param element_p
   * @return a not <code>null</code> array.
   */
  public abstract Object[] getChildren(Object element_p);

  /**
   * Get the context given at construction time.
   * @return <code>null</code> if no context provided.
   */
  public Object getContext() {
    return _context;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getElements()
   */
  public Object[] getElements() {
    return _rootElements.toArray(new Object[_rootElements.size()]);
  }

  /**
   * object as the one notified (i.e call {@link Runnable#run()} on it) when valid elements change.
   * @return <code>null</code> if not set.
   */
  public Runnable getNotifiedForValidElementsChanges() {
    return _notifiedForValidElementsChanges;
  }

  /**
   * Get parent of given object.
   * @param element_p
   * @return <code>null</code> if no parent.
   */
  public abstract Object getParent(Object element_p);

  /**
   * Get valid elements.
   * @return a not <code>null</code> list (a clone).
   */
  public List<Object> getValidElements() {
    return new ArrayList<Object>(_validElements);
  }

  /**
   * Initialize internal data structure for root elements.<br>
   * Default implementation uses {@link HashSet} to avoid duplicated elements.
   * @param displayedElements_p
   */
  protected Collection<Object> initializeRootElementCollection() {
    return new HashSet<Object>(0);
  }

  /**
   * Initialize internal data structure for valid elements.<br>
   * Default implementation uses {@link HashSet} to avoid duplicated elements.
   * @param displayedElements_p
   */
  protected Collection<Object> initializeValidElementCollection(Collection<? extends Object> displayedElements_p) {
    return new HashSet<Object>(displayedElements_p);
  }

  /**
   * Is given element valid ?
   * @param elment_p
   * @return <code>true</code> means valid.
   */
  public boolean isValid(Object elment_p) {
    return _validElements.contains(elment_p);
  }

  /**
   * Run registered runnable when valid elements change.
   */
  protected void notifyValidElementsChanges() {
    if (null != _notifiedForValidElementsChanges) {
      _notifiedForValidElementsChanges.run();
    }
  }

  /**
   * Remove all given elements.
   * @param elements_p must be not <code>null</code>.
   */
  public void removeAllElements(Object[] elements_p) {
    for (Object element : elements_p) {
      removeElement(element);
      removeValidElement(element);
    }
  }

  /**
   * Remove given element from root ones.
   * @param element_p
   */
  public void removeElement(Object element_p) {
    _rootElements.remove(element_p);
  }

  /**
   * Remove given element from root ones.
   * @param element_p
   */
  public void removeValidElement(Object element_p) {
    _validElements.remove(element_p);
  }

  /**
   * Set given object as the one notified (i.e call {@link Runnable#run()} on it) when valid elements change.
   * @param notifiedForValidElementsChanges_p the notifiedForValidElementsChanges to set
   */
  public void setNotifiedForValidElementsChanges(Runnable notifiedForValidElementsChanges_p) {
    _notifiedForValidElementsChanges = notifiedForValidElementsChanges_p;
  }
}
