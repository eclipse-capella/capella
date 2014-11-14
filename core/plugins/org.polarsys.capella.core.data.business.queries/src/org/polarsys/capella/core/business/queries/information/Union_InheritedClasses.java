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
package org.polarsys.capella.core.business.queries.information;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Union;

/**
 * 
 */
public class Union_InheritedClasses extends AbstractClassInheritedClasses {


  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getAvailableElements(org.polarsys.capella.core.data.capellacore.CapellaElement)
   */
  public List<CapellaElement> getAvailableElements(CapellaElement element_p) {
    List<CapellaElement>  availableElements = new ArrayList<CapellaElement>(1);
    List<CapellaElement> abstractAvailableElements = super.getAbstractAvailableElements(element_p);
    
    // add only unions
    if (!abstractAvailableElements.isEmpty()) {
      for (CapellaElement capellaElement : abstractAvailableElements) {
        if (capellaElement instanceof Union) {
          availableElements.add(capellaElement);
        }
      }
    }
   
    return availableElements;
  }

  /**
   * @see org.polarsys.capella.core.business.queries.capellacore.IBusinessQuery#getEClass()
   */
  public EClass getEClass() {
    return InformationPackage.Literals.UNION;
  }

}
