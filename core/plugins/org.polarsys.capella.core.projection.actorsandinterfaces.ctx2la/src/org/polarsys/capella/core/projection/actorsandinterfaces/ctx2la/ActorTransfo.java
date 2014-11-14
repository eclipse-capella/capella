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
package org.polarsys.capella.core.projection.actorsandinterfaces.ctx2la;


import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;


/**
 */
public class ActorTransfo extends Transfo {

  private static final long serialVersionUID = 0L;

  protected String[] _rules = {
    "Rule_Actor", //$NON-NLS-1$
    "Rule_Generalization", //$NON-NLS-1$
    "Rule_InterfaceUse", //$NON-NLS-1$
    "Rule_InterfaceImplementation", //$NON-NLS-1$
    "Rule_Service", //$NON-NLS-1$
    "Rule_Parameter", //$NON-NLS-1$
    "Rule_ActorPkg", //$NON-NLS-1$
    "Rule_ActorPkgRoot", //$NON-NLS-1$
    "Rule_ComponentFunctionalAllocation" //$NON-NLS-1$
  }; 

  /**
   * @throws ClassNotFoundException 
   */
  public ActorTransfo(ITransfoRuleBase transfoRuleBase_p) throws ClassNotFoundException {
    super(transfoRuleBase_p,  CapellacommonPackage.Literals.TRANSFO_LINK);

    loadRules("org.polarsys.capella.core.projection.actors.ctx2la.rules", _rules); //$NON-NLS-1$
  }
}
