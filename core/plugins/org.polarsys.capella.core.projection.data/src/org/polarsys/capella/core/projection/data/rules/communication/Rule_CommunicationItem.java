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
package org.polarsys.capella.core.projection.data.rules.communication;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.information.communication.CommunicationItem;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.projection.common.rules.core.Rule_CapellaElement;

public abstract class Rule_CommunicationItem extends Rule_CapellaElement {

  public Rule_CommunicationItem(EClass source, EClass target) {
    super(source, target);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void retrieveGoDeep(EObject source_p, List<EObject> result_p, IContext context_p) {
    super.retrieveGoDeep(source_p, result_p, context_p);

    CommunicationItem sourceElement = (CommunicationItem) source_p;
    result_p.addAll(sourceElement.getOwnedFeatures());
    result_p.addAll(sourceElement.getOwnedPropertyValueGroups());
    result_p.addAll(sourceElement.getOwnedConstraints());
    result_p.addAll(sourceElement.getOwnedPropertyValues());
  }

}
