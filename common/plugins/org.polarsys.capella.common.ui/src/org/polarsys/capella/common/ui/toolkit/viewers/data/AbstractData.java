/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param displayedElements
   * @param context optional parameter.
   */
  protected AbstractData(Collection<? extends Object> displayedElements, Object context) {
    _context = context;
    _rootElements = initializeRootElementCollection();
    _validElements = initializeValidElementCollection(displayedElements);
    for (Object modelElement : displayedElements) {
      addElement(modelElement);
    }
  }

  /**
   * Add all given elements.
   * @param elements must be not <code>null</code>.
   */
  public void addAllElements(Object[] elements) {
    for (Object element : elements) {
      addElement(element);
      addValidElement(element);
    }
  }

  /**
   * Add given element in root ones.
   * @param element
   */
  public void addElement(Object element) {
    _rootElements.add(element);
  }

  /**
   * Add given element in valid ones.
   * @param element
   */
  protected void addValidElement(Object element) {
    _validElements.add(element);
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
   * @param element
   * @return a not <code>null</code> array.
   */
  public abstract Object[] getChildren(Object element);

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
   * @param element
   * @return <code>null</code> if no parent.
   */
  public abstract Object getParent(Object element);

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
   */
  protected Collection<Object> initializeRootElementCollection() {
    return new HashSet<Object>(0);
  }

  /**
   * Initialize internal data structure for valid elements.<br>
   * Default implementation uses {@link HashSet} to avoid duplicated elements.
   * @param displayedElements
   */
  protected Collection<Object> initializeValidElementCollection(Collection<? extends Object> displayedElements) {
    return new HashSet<Object>(displayedElements);
  }

  /**
   * Is given element valid ?
   * @param elment
   * @return <code>true</code> means valid.
   */
  public boolean isValid(Object elment) {
    return _validElements.contains(elment);
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
   * @param elements must be not <code>null</code>.
   */
  public void removeAllElements(Object[] elements) {
    for (Object element : elements) {
      removeElement(element);
      removeValidElement(element);
    }
  }

  /**
   * Remove given element from root ones.
   * @param element
   */
  public void removeElement(Object element) {
    _rootElements.remove(element);
  }

  /**
   * Remove given element from root ones.
   * @param element
   */
  public void removeValidElement(Object element) {
    _validElements.remove(element);
  }

  /**
   * Set given object as the one notified (i.e call {@link Runnable#run()} on it) when valid elements change.
   * @param notifiedForValidElementsChanges the notifiedForValidElementsChanges to set
   */
  public void setNotifiedForValidElementsChanges(Runnable notifiedForValidElementsChanges) {
    _notifiedForValidElementsChanges = notifiedForValidElementsChanges;
  }
}
