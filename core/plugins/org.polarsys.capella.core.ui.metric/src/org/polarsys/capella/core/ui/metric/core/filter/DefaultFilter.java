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
package org.polarsys.capella.core.ui.metric.core.filter;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.pa.deployment.DeploymentPackage;
import org.polarsys.capella.core.ui.metric.core.IMetricFilter;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;

/**
 * Default Filter,
 *
 */
public class DefaultFilter implements IMetricFilter {

  /**
   * @see org.polarsys.capella.core.ui.metric.core.IMetricFilter#accept(org.eclipse.emf.ecore.EObject)
   */
  public boolean accept(EObject eobject_p) {

    EClass ec = eobject_p.eClass();
    
    boolean result = 
      !ModellingcorePackage.Literals.ABSTRACT_TRACE.isSuperTypeOf(ec) && // No Traces
      !InteractionPackage.Literals.EVENT.isSuperTypeOf(ec) && // No Event
      !InteractionPackage.Literals.ABSTRACT_END.isSuperTypeOf(ec) && // No AE
      !DeploymentPackage.Literals.PART_DEPLOYMENT_LINK.isSuperTypeOf(ec) && //...
      !CapellacorePackage.Literals.INVOLVEMENT.isSuperTypeOf(ec)
    ;
      
    return result;
  }

}
