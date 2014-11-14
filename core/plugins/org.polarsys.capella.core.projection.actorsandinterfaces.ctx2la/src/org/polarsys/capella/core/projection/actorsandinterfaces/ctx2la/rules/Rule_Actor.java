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

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfaceImplementation;
import org.polarsys.capella.core.data.cs.InterfaceUse;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.information.Service;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.projection.interfaces.ctx2la.TransformInterfacesCtx2La;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 */
public class Rule_Actor extends TransfoRule {

  /**
   * @param sourceType_p
   * @param targetType_p
   */
  public Rule_Actor() {
	  	  
    super(CtxPackage.Literals.ACTOR, LaPackage.Literals.LOGICAL_ACTOR, LaPackage.Literals.SYSTEM_ACTOR_REALIZATION);

    setNeedsContext(true);
    _updatedAttributes.add(CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__ABSTRACT.getName());    
    _updatedAttributes.remove(ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName());
  }
  
  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#getDescription()
   */
  @Override
  public String getDescription() {
    return super.getDescription() + __br
      + " - This rule manages interfaces which are not nested." + __br //$NON-NLS-1$
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
	  Actor sourceElement = (Actor) element_p;

	  List<EObject> relatedElements = new ArrayList<EObject>();
	  relatedElements.add(sourceElement.eContainer());
	  relatedElements.addAll(sourceElement.getSuperGeneralizations());

	  // Retrieve Service
	  for (Feature feature : sourceElement.getOwnedFeatures()) {
		  if (feature instanceof Service) {
			  if (!relatedElements.contains(feature))
				  relatedElements.add(feature);
		  }
	  }

	  // Retrieve ComponentFunctionalAllocation link between current Actor and LF
	  for (ComponentFunctionalAllocation compFuncAlloc : sourceElement.getFunctionalAllocations()) {
		  relatedElements.add(compFuncAlloc);
	  }
	  
	  // Perform Interface(s) projection manipulated (used/implement) by the current Actor
	  interfacesProjectionProcessing(sourceElement, transfo_p);
	  
	  // Retrieve Use/Implement link between current Actor and Interfaces
	  for (InterfaceUse use : sourceElement.getUsedInterfaceLinks()) {
		  relatedElements.add(use);
	  }
	  for (InterfaceImplementation impl : sourceElement.getImplementedInterfaceLinks()) {
		  relatedElements.add(impl);
	  }
    
    for (AbstractTypedElement typedElement : sourceElement.getAbstractTypedElements()) {
      if (typedElement instanceof Part) {
        relatedElements.add(typedElement);
      }
    }

	  return relatedElements;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#transform(java.lang.Object)
   */
  @Override
  public EObject transform_(EObject element_p, ITransfo transfo_p) {
    Actor sourceElement = (Actor) element_p;
  	LogicalActor targetElement = LaFactory.eINSTANCE.createLogicalActor(sourceElement.getName());

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
   * @throws TransfoException 
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach(java.lang.Object)
   */
  @Override
  public void attach_(EObject element_p, ITransfo transfo_p) throws TransfoException {
	  Actor sourceElement = (Actor) element_p;
	  TigerRelationshipHelper.attachTransformedContainedElementsByMapping(sourceElement, transfo_p, CtxPackage.Literals.ACTOR_PKG__OWNED_ACTORS, LaPackage.Literals.LOGICAL_ACTOR_PKG__OWNED_LOGICAL_ACTORS);
  }

  /**
   * Perform Interface(s) projection manipulated (used/implement) by the current Actor
   * @param transfo_p 
   */
  private void interfacesProjectionProcessing(Actor currentActor_p, ITransfo transfo_p) {
	  List<Interface> itfManipulated = new ArrayList<Interface>();

	  // Compute Interface(s) Used/Implemented by the current LC
	  for (Interface itf : currentActor_p.getUsedInterfaces()) {
		  if (!itfManipulated.contains(itf))
			  itfManipulated.add(itf);
	  }
	  for (Interface itf : currentActor_p.getImplementedInterfaces()) {
		  if (!itfManipulated.contains(itf))
			  itfManipulated.add(itf);
	  }

	  // Context to LA interfaces projection
	  TransformInterfacesCtx2La transfItfCtx2la = new TransformInterfacesCtx2La();
	  for (Interface itf : itfManipulated) {
		  if (EObjectExt.isContainedBy(itf, CtxPackage.Literals.SYSTEM_ANALYSIS)) {
			  transfItfCtx2la.addContext(itf);
		  }
	  }
	  transfItfCtx2la.execute();
  } 
  
}
