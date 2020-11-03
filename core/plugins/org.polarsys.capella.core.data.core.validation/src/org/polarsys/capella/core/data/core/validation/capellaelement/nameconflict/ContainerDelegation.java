/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict;

import org.eclipse.emf.validation.AbstractModelConstraint;

import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.util.InteractionSwitch;
import org.polarsys.capella.core.model.utils.CapellaSwitch;
import org.polarsys.capella.core.validation.rule.TrueModelConstraint;

/**
 * Provides model constraint implementations suitable to check naming conflicts.
 * 
 */
public class ContainerDelegation extends CapellaSwitch<AbstractModelConstraint> {
  
  public ContainerDelegation(AbstractModelConstraint defaultResult) {
    super(defaultResult);
    setInteraction(new InteractionSwitch<AbstractModelConstraint>(){  
      @Override
      // For scenarios, the constraint is by definition satisfied
      public AbstractModelConstraint caseScenario(Scenario s){
        return TrueModelConstraint.INSTANCE;
      }
      
      @Override
      // For abstract capabilities, the constraint is by definition satisfied
      public AbstractModelConstraint caseAbstractCapability(AbstractCapability a){
        return TrueModelConstraint.INSTANCE;
      }
    });
  }

}
