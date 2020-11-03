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
package org.polarsys.capella.core.transition.system.topdown.merge.scope;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A model scope covering reference model
 */
public class ReferenceModelScope extends org.polarsys.capella.core.transition.common.merge.scope.ReferenceModelScope {

  public ReferenceModelScope(List<? extends EObject> elements_p, IContext context_p) {
    super(elements_p, context_p);
  }

  @Override
  protected List<EObject> get(EObject source_p, EReference reference_p, boolean resolveProxies_p) {
    List<EObject> originalValues = super.get(source_p, reference_p, resolveProxies_p);
    return retains(originalValues);
  }

  @Override
  public boolean add(EObject source_p, EReference reference_p, EObject value_p) {
    return super.add(source_p, reference_p, value_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected List<EObject> retains(List<EObject> object_p) {
    return object_p;
  }

}
