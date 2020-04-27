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
package org.polarsys.capella.core.data.core.validation.capellaelement;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.ContainerDelegation;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.DefaultNameConflictConstraint;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.CsSlots;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.DatavalueSlots;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.FaSlots;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.InformationSignatures;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.InformationSlots;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.CapellaSlots;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.CapellacoreSlots;
import org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict.signatures.ModellingcoreSignatures;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.model.utils.CapellaSwitch;
import org.polarsys.capella.core.validation.rule.AbstractDelegatedModelConstraint;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;


/**
 * A model constraint for Capella name conflicts.
 * 
 * A name conflicts exist between two or more children of a target 
 * container if some conditions are satisfied. These conditions 
 * depend on the target container and its children (we assume
 * that elements share the same parent), e.g.:
 * 
 * - Two or more 'Namespace' elements have the same name
 * - Two or more elements of the same type have the same name
 * 
 * For more concrete examples, check the NameConflict* Test cases under 
 * org.polarsys.capella.test.rules.ju.cases
 * 
 */
public class NameConflict extends AbstractDelegatedModelConstraint {

  /** 
   * Dynamically provide an AbstractModelConstraint based on the target type.
   */
  private CapellaSwitch<AbstractModelConstraint> delegate;

  
  public NameConflict(){
    
    // add new package switches if necessary
    CapellaSwitch<String> signatures = new CapellaSwitch<String>(){
      
      /**
       * The default signature for any element is its name, 
       * if the element is an AbstractNamedElement, otherwise
       * null. It is not sufficient to override 
       * 
       * {@inheritDoc}
       */
      @Override
      public String defaultCase(EObject e){
        String result = null;
        if (e instanceof AbstractNamedElement){
          result = ((AbstractNamedElement) e).getName();
        }
        return result;
      }
    };
    signatures.setInformation(new InformationSignatures());
    signatures.setModellingcore(new ModellingcoreSignatures(true));
    
    // add new package switches if necessary
    CapellaSwitch<Object> slots = new CapellaSlots();
    slots.setCapellacore(new CapellacoreSlots());
    slots.setFa(new FaSlots());
    slots.setCs(new CsSlots());
    slots.setDatavalue(new DatavalueSlots());
    slots.setInformation(new InformationSlots());
    
    // if container based switch doesn't say otherwise, use the slot/signature method
    delegate = new ContainerDelegation(new DefaultNameConflictConstraint(signatures, slots));   
  }
  
    
  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractModelConstraint getDelegateFor(IValidationContext ctx) {
    return delegate.doSwitch(ctx.getTarget());
  }
  
  @Override
  public IStatus validate(IValidationContext ctx) {
 	if (isNotImpactedRule(ctx)) {
	   return ctx.createSuccessStatus();
	}
    return super.validate(ctx);
  }


/**
 * @param ctx
 * @return
 */
	private boolean isNotImpactedRule(IValidationContext ctx) {
		return (ctx.getTarget() instanceof CommunicationMean);
	}

}
