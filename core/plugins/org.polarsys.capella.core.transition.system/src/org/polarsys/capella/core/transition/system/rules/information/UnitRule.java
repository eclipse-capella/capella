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

package org.polarsys.capella.core.transition.system.rules.information;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.transition.system.rules.AbstractCapellaElementRule;

/**
 *
 */
public class UnitRule extends AbstractCapellaElementRule {

  @Override
  protected EClass getSourceType() {
    return InformationPackage.Literals.UNIT;
  }

}
