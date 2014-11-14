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
import org.polarsys.capella.core.data.ctx.ActorPkg;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActorPkg;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoRule;

/**
 */
public class Rule_ActorPkg extends TransfoRule {

  public Rule_ActorPkg() {
    super(CtxPackage.Literals.ACTOR_PKG, LaPackage.Literals.LOGICAL_ACTOR_PKG);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br
      + " - This rule manages the intermediate interfacePkgs." + __br         //$NON-NLS-1$
      + " - The container must be an " + CsPackage.Literals.INTERFACE_PKG.getName() + __br; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#when(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.impl.Transfo)
   */
  @Override
  public boolean when(EObject element_p, ITransfo transfo_p) {
    if (!super.when(element_p, transfo_p))
      return false;

    return element_p.eContainer().eClass().equals(CtxPackage.Literals.ACTOR_PKG);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#retrieveRelatedElements(java.lang.Object)
   */
  @Override
  public List<EObject> retrieveRelatedElements_(EObject element_p, ITransfo transfo_p) {
	  ActorPkg sourceElement = (ActorPkg) element_p;

    List<EObject> relatedElements = new ArrayList<EObject>();
    relatedElements.add(sourceElement.eContainer());

    return relatedElements;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  @Override
  public EObject transform_(EObject element_p, ITransfo transfo_p) {
    LogicalActorPkg targetElement = LaFactory.eINSTANCE.createLogicalActorPkg();

    return targetElement;    
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#update(java.lang.Object)
   */
  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
    ActorPkg sourceElement = (ActorPkg) element_p;
    
    TigerRelationshipHelper.attachTransformedContainedElementsByMapping(sourceElement, transfo_p, CtxPackage.Literals.ACTOR_PKG__OWNED_ACTOR_PKGS, LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTOR_PKGS);
  }
}
