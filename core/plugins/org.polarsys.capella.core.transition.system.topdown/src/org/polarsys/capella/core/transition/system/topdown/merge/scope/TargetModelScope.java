/*******************************************************************************
 * Copyright (c) 2006, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.topdown.merge.scope;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.polarsys.capella.core.transition.common.merge.scope.IModelScopeFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class TargetModelScope extends org.polarsys.capella.core.transition.common.merge.scope.TargetModelScope {

  /**
   * @param elements_p
   * @param context_p
   */
  public TargetModelScope(List<? extends EObject> elements_p, IContext context_p) {
    super(elements_p, context_p);
  }
  
  @Override
  protected void initializeSiriusImageHelper(IContext context, boolean active) {
      // Force to deactivate the SiriusImageHelper as in TopDown we do not want to update images paths 
      super.initializeSiriusImageHelper(context, false);
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject element_p) {
    return super.add(element_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject element_p, boolean includeChildren_p) {
    return super.add(element_p, includeChildren_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    return super.add(source_p, reference_p, value_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addAllChildrenToScope(EObject element_p, IModelScopeFilter filter_p) {
    super.addAllChildrenToScope(element_p, filter_p);
  }

  @Override
  protected List<EObject> get(EObject source_p, EReference reference_p, boolean resolveProxies_p) {
    List<EObject> originalValues = super.get(source_p, reference_p, resolveProxies_p);
    return originalValues;

  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<EObject> retains(List<EObject> object_p) {

    Iterator<EObject> it = object_p.iterator();
    while (it.hasNext()) {
      EObject next = it.next();
      if (!_inScope.contains(next)) {
        it.remove();
      }
    }
    return object_p;
  }

}
