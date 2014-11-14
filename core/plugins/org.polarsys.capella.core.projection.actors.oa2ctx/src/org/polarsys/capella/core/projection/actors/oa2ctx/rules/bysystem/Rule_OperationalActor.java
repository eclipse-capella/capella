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
package org.polarsys.capella.core.projection.actors.oa2ctx.rules.bysystem;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.oa.OperationalActor;
import org.polarsys.capella.core.tiger.ITransfo;

/**
 */
public class Rule_OperationalActor extends Rule_OperationalEntity {

  public Rule_OperationalActor() {
    super(OaPackage.Literals.OPERATIONAL_ACTOR, CsPackage.Literals.COMPONENT, CtxPackage.Literals.OPERATIONAL_ACTOR_REALIZATION);
  }

  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    return element_p instanceof OperationalActor;
  }
}
