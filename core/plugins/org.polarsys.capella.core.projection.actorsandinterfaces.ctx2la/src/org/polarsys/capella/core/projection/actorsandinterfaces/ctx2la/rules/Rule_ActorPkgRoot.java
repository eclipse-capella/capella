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
package org.polarsys.capella.core.projection.actorsandinterfaces.ctx2la.rules;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_ActorPkgRoot extends TransfoRule {

	public Rule_ActorPkgRoot() {
		super(CtxPackage.Literals.ACTOR_PKG, LaPackage.Literals.LOGICAL_ACTOR_PKG);
		setNeedsContext(true);    
	}
  
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#update(java.lang.Object)
   */
  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
	  // do nothing
  }
  
  
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br
      + " - This rule manages the root interfacePkgs." + __br       //$NON-NLS-1$
      + " - Optional parameter: " + TransfoEngine.TRANSFO_TARGET + __br //$NON-NLS-1$
      + " - The container must NOT be a " + CsPackage.Literals.INTERFACE_PKG.getName() //$NON-NLS-1$
        + " or a " + SharedmodelPackage.Literals.GENERIC_PKG.getName() + __br //$NON-NLS-1$
      + " - The target system engineering interfacePkg is set directly." + __br //$NON-NLS-1$
      + " - There is a potential collision with the current rule, a merge is required." + __br         //$NON-NLS-1$
      + " - Note: GenericPkgs like SharedPkgs are not processed by transformation. They are cloned."; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    if (!super.when(element_p, transfo_p))
      return false;

    return element_p.eContainer().eClass() == CtxPackage.Literals.SYSTEM_ANALYSIS;
      
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#retrieveRelatedElements(java.lang.Object)
   */
  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
    List<EObject> relatedElements = new ArrayList<EObject>();

    return relatedElements;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  @Override
  public EObject transform_(EObject element_p, ITransfo transfo_p) {
	  LogicalActorPkg logActorPkg = null;

	  LogicalArchitecture la = (LogicalArchitecture) transfo_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
	  if (la != null) {
		  logActorPkg = la.getOwnedLogicalActorPkg();
		  if (logActorPkg == null) {
			  logActorPkg = LaFactory.eINSTANCE.createLogicalActorPkg();
			  logActorPkg.setName("Logical Actor Pkg"); //$NON-NLS-1$
			  la.setOwnedLogicalActorPkg(logActorPkg);
		  }
	  }
	  return logActorPkg;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    // do nothing
  }  
  
  
}
