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
package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.diffmerge.impl.scopes.MultiRootTreeIterator;
import org.eclipse.emf.diffmerge.impl.scopes.RootedModelScope;
import org.eclipse.emf.diffmerge.util.structures.FArrayList;
import org.eclipse.emf.diffmerge.util.structures.FHashSet;
import org.eclipse.emf.diffmerge.util.structures.IEqualityTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.transition.common.merge.MiscUtil;

/**
 * A model scope covering subtrees of the EMF containment trees. Root addition leaves the underlying model untouched. Root removal modifies the model; use
 * removeFromScope(EObject) for side-effect-free removal.
 */
public class PartialRootedModelScope extends RootedModelScope {

  @Override
  public TreeIterator getAllContents() {
    return new MultiRootTreeIterator(PartialRootedModelScope.this, getContents().iterator()) {

      @Override
      public EObject next() {
        return super.next();
      }
    };
  }

  /** The elements which actually belong to this scope */
  protected final Set<EObject> _inScope;

  /**
   * Constructor
   * @param elements_p a non-null list of the elements initially in the scope
   */
  public PartialRootedModelScope(Collection<? extends EObject> elements_p) {
    super(MiscUtil.getRoots(elements_p));
    _inScope = new FHashSet<EObject>(elements_p, IEqualityTester.BY_REFERENCE);
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#add(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject element_p) {
    return add(element_p, false);
  }

  protected List<EObject> retains(List<EObject> object_p) {
    object_p.retainAll(_inScope);
    return object_p;
  }

  /**
   * Add the given element to the scope
   * @param element_p a non-null element which is out of the scope
   * @param includeChildren_p whether all its children must be added too
   */
  public boolean add(EObject element_p, boolean includeChildren_p) {
    boolean result = true;
    if (!EcoreUtil.isAncestor(getContents(), element_p)) {
      result = super.add(element_p);
    }
    if (result) {
      _inScope.add(element_p);
      if (includeChildren_p) {
        addAllChildrenToScope(element_p, null);
      }
    }
    return result;
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#add(org.eclipse.emf.ecore.EReference, org.eclipse.emf.ecore.EObject,
   *      org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    // Remember previously owned value if relevant
    EObject previouslyContainedValue = null;
    if (!reference_p.isMany() && reference_p.isContainment()) {
      List<EObject> values = get(source_p, reference_p);
      if (!values.isEmpty()) {
        previouslyContainedValue = values.get(0);
      }
    }
    boolean result = super.add(source_p, reference_p, value_p);
    if (result) {
      // Remove previously owned value from scope
      if (previouslyContainedValue != null) {
        _inScope.remove(previouslyContainedValue);
      }
      // Add new value in scope
      _inScope.add(value_p);
    }
    return result;
  }

  /**
   * Add all children of the current roots that are accepted by the given filter
   * @param filter_p an optional filter, where null stands for no filtering
   */
  public void build(IModelScopeFilter filter_p) {
    _inScope.clear();
    for (EObject root : super.getContents()) {
      if ((filter_p == null) || filter_p.accepts(root)) {
        _inScope.add(root);
        addAllChildrenToScope(root, filter_p);
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
  protected List<EObject> get(EObject source_p, EReference reference_p, boolean resolveProxies_p) {

    List<EObject> originalValues = super.get(source_p, reference_p, resolveProxies_p);
    List<EObject> result = new FArrayList<EObject>(originalValues, IEqualityTester.BY_REFERENCE);
    retains(result);
    return result;

  }

  /**
   * Add all children of the given in-scope element
   * @param element_p a non-null element which already belongs to the scope
   * @param filter_p an optional filter, where null stands for no filtering
   */
  protected void addAllChildrenToScope(EObject element_p, IModelScopeFilter filter_p) {
    TreeIterator<EObject> technicalIterator = element_p.eAllContents();
    while (technicalIterator.hasNext()) {
      EObject child = technicalIterator.next();
      if ((filter_p == null) || filter_p.accepts(child)) {
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
  public boolean covers(EObject element_p) {
    return _inScope.contains(element_p);
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
  public List<EObject> getContents(EObject element_p) {
    List<EObject> result = new FArrayList<EObject>(super.getContents(element_p), IEqualityTester.BY_REFERENCE);
    retains(result);
    return Collections.unmodifiableList(result);
  }

  /**
   * @see org.polarsys.capella.common.consonance.scopes.RootedModelScope#remove(org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean remove(EObject element_p) {
    boolean result = super.remove(element_p);
    removeFromScope(element_p);
    return result;
  }

  /**
   * Remove the given element and its children from the scope while leaving the model untouched
   * @param element_p a non-null element
   */
  public void removeFromScope(EObject element_p) {
    _inScope.remove(element_p);
    TreeIterator<EObject> technicalIterator = element_p.eAllContents();
    while (technicalIterator.hasNext()) {
      EObject child = technicalIterator.next();
      _inScope.remove(child);
    }
  }

  /**
   * Return whether the given element is meaningful when considered within the given set of elements or their children only, i.e., when separated from anything
   * outside those elements.
   */
  protected boolean isMeaningfulWithin(EObject element_p, Collection<? extends EObject> contexts_p) {
    return true;
  }

}
