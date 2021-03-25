/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;

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
   * Use this context to enable computation on {@link ILinkSelection} contribution mechanism without an explicit context
   * object.
   */
  public static final Object UNDEFINED_CONTEXT = new Object();
  /**
   * Context element used for usage based on {@link ILinkSelection}.
   */
  private Object context;
  /**
   * Object notified when valid elements change.
   */
  private Runnable notifiedForValidElementsChanges;
  /**
   * Set of root elements.
   */
  Collection<Object> rootElements;
  /**
   * Valid elements are elements.
   */
  Collection<Object> validElements;

  /**
   * Constructor.
   * 
   * @param displayedElements
   * @param context
   *          optional parameter.
   */
  protected AbstractData(Collection<? extends Object> displayedElements, Object context) {
    this.context = context;
    rootElements = initializeRootElementCollection();
    validElements = initializeValidElementCollection(displayedElements);
    for (Object modelElement : displayedElements) {
      addElement(modelElement);
    }
  }

  /**
   * Add all given elements.
   * 
   * @param elements
   *          must be not <code>null</code>.
   */
  public void addAllElements(Object[] elements) {
    for (Object element : elements) {
      addElement(element);
      addValidElement(element);
    }
  }

  /**
   * Add given element in root ones.
   * 
   * @param element
   */
  public void addElement(Object element) {
    rootElements.add(element);
  }

  /**
   * Add given element in valid ones.
   * 
   * @param element
   */
  protected void addValidElement(Object element) {
    validElements.add(element);
  }

  /**
   * Clear data.<br>
   * Default implementation clears root elements.
   */
  public void clearData() {
    rootElements.clear();
  }

  /**
   * Get children for given element.
   * 
   * @param element
   * @return a not <code>null</code> array.
   */
  public abstract Object[] getChildren(Object element);

  /**
   * Get the context given at construction time.
   * 
   * @return <code>null</code> if no context provided.
   */
  public Object getContext() {
    return context;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData#getElements()
   */
  public Object[] getElements() {
    return rootElements.toArray(new Object[rootElements.size()]);
  }

  /**
   * object as the one notified (i.e call {@link Runnable#run()} on it) when valid elements change.
   * 
   * @return <code>null</code> if not set.
   */
  public Runnable getNotifiedForValidElementsChanges() {
    return notifiedForValidElementsChanges;
  }

  /**
   * Get parent of given object.
   * 
   * @param element
   * @return <code>null</code> if no parent.
   */
  public abstract Object getParent(Object element);

  /**
   * Get valid elements.
   * 
   * @return a not <code>null</code> list (a clone).
   */
  public List<Object> getValidElements() {
    return new ArrayList<>(validElements);
  }

  /**
   * Initialize internal data structure for root elements.<br>
   * Default implementation uses {@link HashSet} to avoid duplicated elements.
   */
  protected Collection<Object> initializeRootElementCollection() {
    return new LinkedHashSet<>(0);
  }

  /**
   * Initialize internal data structure for valid elements.<br>
   * Default implementation uses {@link LinkedHashSet} to avoid duplicated elements.
   * 
   * @param displayedElements
   */
  protected Collection<Object> initializeValidElementCollection(Collection<? extends Object> displayedElements) {
    return new LinkedHashSet<>(displayedElements);
  }

  /**
   * Is given element valid ?
   * 
   * @param elment
   * @return <code>true</code> means valid.
   */
  public boolean isValid(Object elment) {
    return validElements.contains(elment);
  }

  /**
   * Run registered runnable when valid elements change.
   */
  protected void notifyValidElementsChanges() {
    if (null != notifiedForValidElementsChanges) {
      notifiedForValidElementsChanges.run();
    }
  }

  /**
   * Remove all given elements.
   * 
   * @param elements
   *          must be not <code>null</code>.
   */
  public void removeAllElements(Object[] elements) {
    for (Object element : elements) {
      removeElement(element);
      removeValidElement(element);
    }
  }

  /**
   * Remove given element from root ones.
   * 
   * @param element
   */
  public void removeElement(Object element) {
    rootElements.remove(element);
  }

  /**
   * Remove given element from root ones.
   * 
   * @param element
   */
  public void removeValidElement(Object element) {
    validElements.remove(element);
  }

  /**
   * Set given object as the one notified (i.e call {@link Runnable#run()} on it) when valid elements change.
   * 
   * @param notifiedForValidElementsChanges
   *          the notifiedForValidElementsChanges to set
   */
  public void setNotifiedForValidElementsChanges(Runnable notifiedForValidElementsChanges) {
    this.notifiedForValidElementsChanges = notifiedForValidElementsChanges;
  }
}
