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
