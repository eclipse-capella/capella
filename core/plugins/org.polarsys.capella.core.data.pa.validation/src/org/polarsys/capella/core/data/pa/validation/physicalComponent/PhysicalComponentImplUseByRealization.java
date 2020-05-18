/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.pa.validation.physicalComponent;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.transition.system.topdown.preferences.PreferenceHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This rule ensures that if a physical component does not realize any logical component. 
 * It should only use/impl the interfaces of physical level
 */
public class PhysicalComponentImplUseByRealization extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx_p) {
    EObject eObj = ctx_p.getTarget();
    EMFEventType eType = ctx_p.getEventType();

    if (eType == EMFEventType.NULL) {
      if (eObj instanceof PhysicalComponent) {
        PhysicalComponent physicalComponent = (PhysicalComponent) eObj;
        // continue if the preference transition of interface is active
        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        if (!preferenceHelper.transitionInterfaceWhileComponentTransition()) {
	        List<Component> realizedComponents = physicalComponent.getRealizedComponents();
	        if (realizedComponents.isEmpty()) {
	            // collect all the used, provided, required and implemented interfaces
	            List<Interface> allInterface = new ArrayList<Interface>(1);
	            allInterface.addAll(physicalComponent.getUsedInterfaces());
	            allInterface.addAll(physicalComponent.getImplementedInterfaces());
	            
	            // check weather interface used/implemented/provided/required is not of the level other then physical
	            // collect interface from other levels
	            List<Interface> otherLevelInterfaces = new ArrayList<Interface>(1);
	            for (Interface myInterface : allInterface) {
	              // filter physicalArchitecture 
	              if (! EcoreUtil2.isContainedBy(myInterface, PaPackage.Literals.PHYSICAL_ARCHITECTURE)) {
	            	  otherLevelInterfaces.add(myInterface);
	            	  //return createFailureStatus(ctx_p, new Object[] { physicalComponent.getName(),myInterface.getName() });
	              }
	            }
	            StringBuffer otherInterfaceNames = new StringBuffer();
	            boolean first = true;
	            for (Interface itf : otherLevelInterfaces) {
      					if (first) {
      						otherInterfaceNames.append(itf.getName());
      						first = false;
      					}else{
      						otherInterfaceNames.append(ICommonConstants.COMMA_CHARACTER);
      						otherInterfaceNames.append(ICommonConstants.WHITE_SPACE_CHARACTER);
      						otherInterfaceNames.append(itf.getName());
      					}
      				}
	            if (!otherLevelInterfaces.isEmpty()) {
	            	return ctx_p.createFailureStatus(ctx_p, new Object[] { physicalComponent.getName(),otherInterfaceNames.toString() });	
				}
	      	    
			}
        }
      }
    }
    return ctx_p.createSuccessStatus();
  }

}
