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
package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.diffmerge.structures.IEqualityTester;
import org.eclipse.emf.diffmerge.structures.common.FArrayList;
import org.eclipse.emf.diffmerge.structures.common.FHashSet;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.core.transition.common.merge.MiscUtil;

/**
 * A model scope covering subtrees of the EMF containment trees. Root addition leaves the underlying model untouched. Root removal modifies the model; use
 * removeFromScope(EObject) for side-effect-free removal.
 */
public class PartialRootedModelScope extends RootedModelScope {

  /** The elements which actually belong to this scope */
  protected final Set<EObject> _inScope;

  /**
   * Constructor
   * @param elements a non-null list of the elements initially in the scope
   */
  public PartialRootedModelScope(Collection<? extends EObject> elements) {
    super(MiscUtil.getRoots(elements));
    _inScope = new FHashSet<EObject>(elements, IEqualityTester.BY_REFERENCE);
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#add(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject element) {
    return add(element, false);
  }

  protected List<EObject> retains(List<EObject> object) {
    object.retainAll(_inScope);
    return object;
  }

  /**
   * Add the given element to the scope
   * @param element a non-null element which is out of the scope
   * @param includeChildren whether all its children must be added too
   */
  public boolean add(EObject element, boolean includeChildren) {
    boolean result = true;
    if (!EcoreUtil.isAncestor(getContents(), element)) {
      result = super.add(element);
    }
    if (result) {
      _inScope.add(element);
      if (includeChildren) {
        addAllChildrenToScope(element, null);
      }
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#add(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source, EReference reference, EObject value) {
    // Remember previously owned value if relevant
    EObject previouslyContainedValue = null;
    if (!reference.isMany() && reference.isContainment()) {
      List<EObject> values = get(source, reference);
      if (!values.isEmpty()) {
        previouslyContainedValue = values.get(0);
      }
    }
    boolean result = super.add(source, reference, value);
    if (result) {
      // Remove previously owned value from scope
      if (previouslyContainedValue != null) {
        _inScope.remove(previouslyContainedValue);
      }
      // Add new value in scope
      _inScope.add(value);
    }
    return result;
  }

  /**
   * Add all children of the current roots that are accepted by the given filter
   * @param filter an optional filter, where null stands for no filtering
   */
  public void build(IModelScopeFilter filter) {
    _inScope.clear();
    for (EObject root : super.getContents()) {
      if ((filter == null) || filter.accepts(root)) {
        _inScope.add(root);
        addAllChildrenToScope(root, filter);
      }
    }

    // Filter non relevant element => reference not present in scope
    List<EObject> scopeNotFiltered = new ArrayList<EObject>(_inScope);
    for (EObject elt : scopeNotFiltered) {
      if (!isMeaningfulWithin(elt, scopeNotFiltered)) {
        _inScope.remove(elt);
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.AbstractModelScope#get(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EReference, boolean)
   */

  @Override
  protected List<EObject> get(EObject source, EReference reference, boolean resolveProxies) {

    List<EObject> originalValues = super.get(source, reference, resolveProxies);
    List<EObject> result = new FArrayList<EObject>(originalValues, IEqualityTester.BY_REFERENCE);
    retains(result);
    return result;

  }

  /**
   * Add all children of the given in-scope element
   * @param element a non-null element which already belongs to the scope
   * @param filter an optional filter, where null stands for no filtering
   */
  protected void addAllChildrenToScope(EObject element, IModelScopeFilter filter) {
    TreeIterator<EObject> technicalIterator = element.eAllContents();
    while (technicalIterator.hasNext()) {
      EObject child = technicalIterator.next();
      if ((filter == null) || filter.accepts(child)) {
        _inScope.add(child);
      } else {
        technicalIterator.prune();
      }
    }
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#covers(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean covers(EObject element) {
    return _inScope.contains(element);
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.IReadWriteModelScope#getContents()
   */
  @Override
  public List<EObject> getContents() {
    List<EObject> result = new FArrayList<EObject>(IEqualityTester.BY_REFERENCE);
    result.addAll(super.getContents());
    retains(result);
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.AbstractModelScope#getContents(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public List<EObject> getContents(EObject element) {
    List<EObject> result = new FArrayList<EObject>(super.getContents(element), IEqualityTester.BY_REFERENCE);
    retains(result);
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#remove(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject element) {
    boolean result = super.remove(element);
    removeFromScope(element);
    return result;
  }

  /**
   * Remove the given element and its children from the scope while leaving the model untouched
   * @param element a non-null element
   */
  public void removeFromScope(EObject element) {
    _inScope.remove(element);
    TreeIterator<EObject> technicalIterator = element.eAllContents();
    while (technicalIterator.hasNext()) {
      EObject child = technicalIterator.next();
      _inScope.remove(child);
    }
  }

  /**
   * Return whether the given element is meaningful when considered within the given set of elements or their children only, i.e., when separated from anything
   * outside those elements.
   */
  protected boolean isMeaningfulWithin(EObject element, Collection<? extends EObject> contexts) {
    return true;
  }

}
