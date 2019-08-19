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
package org.polarsys.capella.core.transition.system.topdown.rules.oa.oe2actor;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PartRule extends org.polarsys.capella.core.transition.system.rules.cs.PartRule {

  @Override
  protected EClass getSourceType() {
    return CsPackage.Literals.PART;
  }

  @Override
  protected EObject getBestContainer(EObject element_p, EObject result_p, IContext context_p) {
    // We don't care traceability, we return default container
    return null;
  }
}
