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
package org.polarsys.capella.core.projection.common.rules.cs;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.projection.common.context.IContext;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;

/**
 */
public class Rule_Actor extends Rule_Component {

  public Rule_Actor(EClass source, EClass target, EClass realization) {
    super(source, target, realization);
  }

  public Rule_Actor() {
    super(CsPackage.Literals.ABSTRACT_ACTOR, CsPackage.Literals.ABSTRACT_ACTOR, CsPackage.Literals.COMPONENT_ALLOCATION);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    if (!super.when(element_p, transfo_p))
      return false;

    return element_p.eContainer() instanceof Structure;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus transformRequired(EObject element_p, IContext context_p) {
    EObject transfoSource = (EObject) context_p.get(TransfoEngine.TRANSFO_SOURCE);
    if (transfoSource instanceof LogicalActorPkg) {
      return Status.OK_STATUS;
    }
    return super.transformRequired(element_p, context_p);
  }

}
