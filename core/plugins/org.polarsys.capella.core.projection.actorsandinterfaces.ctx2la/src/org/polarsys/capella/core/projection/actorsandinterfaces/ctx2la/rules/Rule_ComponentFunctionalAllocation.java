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

import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.FaFactory;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.TransfoException;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.TransfoHelper;
import org.polarsys.capella.core.transfo.systemfunction.TransformSystemFunction;

public class Rule_ComponentFunctionalAllocation extends TransfoRule {

	public Rule_ComponentFunctionalAllocation() {
		super(FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION, FaPackage.Literals.COMPONENT_FUNCTIONAL_ALLOCATION);
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
		ComponentFunctionalAllocation componentFuncAlloc = (ComponentFunctionalAllocation) element_p;

		// LogicalFunction to PhysicalFunction transformation
		SystemFunction lf = (SystemFunction) componentFuncAlloc.getTargetElement();
		TransformSystemFunction transformSystemFunction = new TransformSystemFunction();
		transformSystemFunction.setContext(lf);
		transformSystemFunction.execute();
		
		// ComponentFunctionalAllocation link creation
		ComponentFunctionalAllocation componentFuncAllocTgt = FaFactory.eINSTANCE.createComponentFunctionalAllocation();
		
		return componentFuncAllocTgt;
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
		ComponentFunctionalAllocation componentFuncAlloc = (ComponentFunctionalAllocation) element_p;
		ComponentFunctionalAllocation componentFuncAllocTgt = (ComponentFunctionalAllocation) Query.retrieveTransformedElement(componentFuncAlloc, transfo_p);
		
		// Attach ComponentFunctionalAllocation link between PC to PF
		Actor actor = (Actor) componentFuncAlloc.getSourceElement();
		LogicalActor logicalActor = (LogicalActor) Query.retrieveTransformedElement(actor, transfo_p);
		SystemFunction sf = (SystemFunction) componentFuncAlloc.getTargetElement();
		
		LogicalFunction lf =  (LogicalFunction) TransfoHelper.getReconciliation(sf, LaPackage.Literals.LOGICAL_FUNCTION, FaPackage.Literals.FUNCTION_REALIZATION);
		if (actor != null && lf != null) {
			componentFuncAllocTgt.setSourceElement(logicalActor);
			componentFuncAllocTgt.setTargetElement(lf);
			logicalActor.getOwnedFunctionalAllocation().add(componentFuncAllocTgt);
		}    
	}
}
