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

package org.polarsys.capella.core.transition.common.merge.scope;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * A model scope covering reference model
 */
public class ReferenceModelScope extends ContextModelScope {

  public ReferenceModelScope(Collection<? extends EObject> elements, IContext context) {
    super(elements, context);
  }

  @Override
  protected Object getDefaultOriginator() {
    return "Candidate model";
  }

}
