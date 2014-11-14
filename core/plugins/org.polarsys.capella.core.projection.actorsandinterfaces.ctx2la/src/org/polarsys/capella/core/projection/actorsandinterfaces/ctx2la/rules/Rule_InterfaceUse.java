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

import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.TransfoHelper;

/**
 */
public class Rule_InterfaceUse extends TransfoRule {

  public Rule_InterfaceUse() {
    super(CsPackage.Literals.INTERFACE_USE, CsPackage.Literals.INTERFACE_USE);

    setNeedsContext(true);
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br
    		 + " - Required parameter: " + TransfoEngine.TRANSFO_SOURCE; //$NON-NLS-1$
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
    return CsFactory.eINSTANCE.createInterfaceUse();    
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
    InterfaceUse sourceElement = (InterfaceUse) element_p;
    InterfaceUse targetElement = (InterfaceUse) Query.retrieveTransformedElement(sourceElement, transfo_p);
    
    Actor sourceActor = (Actor) sourceElement.getInterfaceUser();
    Interface sourceInterface = sourceElement.getUsedInterface();
    
    LogicalActor targetActor  =  (LogicalActor) TransfoHelper.getReconciliation(sourceActor, LaPackage.Literals.LOGICAL_ACTOR, LaPackage.Literals.SYSTEM_ACTOR_REALIZATION);
    Interface targetInterface =  (Interface) TransfoHelper.getReconciliation(sourceInterface, CsPackage.Literals.INTERFACE, LaPackage.Literals.CONTEXT_INTERFACE_REALIZATION);
    
    if (targetActor != null && targetInterface != null) {
    	targetActor.getOwnedInterfaceUses().add(targetElement);
    	targetElement.setUsedInterface(targetInterface);
    }
  }
  
  
}
