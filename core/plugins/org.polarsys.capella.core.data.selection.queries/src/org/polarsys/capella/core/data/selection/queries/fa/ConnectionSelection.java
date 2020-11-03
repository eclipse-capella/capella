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
package org.polarsys.capella.core.data.selection.queries.fa;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.selection.ILinkSelection;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeEnd;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.capellacore.Type;

/**
 * 
 */
public class ConnectionSelection implements ILinkSelection {

  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getAvailableTargetEClass()
   */
  public List<EClass> getAvailableTargetEClass() {
    List<EClass> returnedList = new ArrayList<EClass>();
    returnedList.add(CsPackage.Literals.COMPONENT);
    return null;
  }

  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getDisplayedTarget(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EObject)
   */
  public EObject getDisplayedTarget(EObject object, EObject context) {
    if (object instanceof ComponentExchange) {
      ComponentExchange currentExchange = (ComponentExchange) object;
      EObject ctx = getRelatedComponent(context);
      EObject source = getRelatedComponent(currentExchange.getSource());
      EObject target = getRelatedComponent(currentExchange.getTarget());
      
      if (source!=null && source.equals(ctx)) {
        return target;
      }else if (source!=null) {
    	  if (ctx instanceof Part && source instanceof Component) {
			Part part = (Part) ctx;
			Type type = part.getType();
			if (type != null && source.equals(type)) {
				return target;
			}
    	  } 
      }
      
      return source;
    }
    
    return null;
  }

  private EObject getRelatedComponent(EObject item) {
    EObject source = item;
    if (source instanceof Port) {
      source = source.eContainer();
    } else if (source instanceof ComponentExchangeEnd) {
      source = ((ComponentExchangeEnd)source).getPart();
    }
    return source;
  }
  /**
   * @see org.polarsys.capella.core.data.core.utils.selection.ILinkSelection#getEClass()
   */
  public EClass getEClass() {
    return FaPackage.Literals.COMPONENT_EXCHANGE;
  }

}
