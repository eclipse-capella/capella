/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionalExt;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;

/**
 * The block extension.
 */
public class BlockExt {

  /**
   * Gets all the ports from the specified block model element.
   * @param element The block where to get ports from.
   * @return The ports list.
   */
  public static List<CapellaElement> getAllPorts(CapellaElement element) {
    SystemEngineering sysEng = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);
 // FIXME : update to SystemAnalysis
    Set<EObject> portsSet = EObjectExt.getAll(SystemEngineeringExt.getSystem(sysEng), InformationPackage.Literals.PORT);

    List<CapellaElement> portsList = new ArrayList<CapellaElement>();
    for (EObject obj : portsSet) {
      portsList.add((Port) obj);
    }
    return portsList;
  }
  

  /**
   * Gets all the ports from the specified block model element.
   * @param element The block where to get ports from.
   * @return The ports list.
   */
  public static Collection<CapellaElement> getAllOwnedPorts(CapellaElement element) {
    Collection<CapellaElement> portsList = new HashSet<CapellaElement>();
    if (!(element instanceof ComponentPort)) {
      return portsList;
    }
    ComponentPort port = (ComponentPort)element;
    for (ComponentExchange connection : port.getComponentExchanges()) {
      portsList.addAll(FunctionalExt.getRelatedPorts(connection)); 
    }
    
    portsList.remove(element);
    return portsList;
  }

}
