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
package org.polarsys.capella.core.semantic.queries.basic.queries;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.common.helpers.query.IQuery;

public class ItemQuery_CapellaElement_getComponentContainer implements IQuery {

  public List<Object> compute(Object object_p) {
    List<Object> result = new ArrayList<Object>();

    if (object_p instanceof CapellaElement) {
      CapellaElement meloElt = (CapellaElement) object_p;

      if (CapellaLayerCheckingExt.isInContextLayer(meloElt)) {
        result.add(EcoreUtil2.getFirstContainer(meloElt, CtxPackage.Literals.SYSTEM_ANALYSIS));
      } else if (CapellaLayerCheckingExt.isInLogicalLayer(meloElt)) {
        LogicalComponent containerLc = (LogicalComponent) EcoreUtil2.getFirstContainer(meloElt, LaPackage.Literals.LOGICAL_COMPONENT);
        if (null == containerLc) {
          result.add(EcoreUtil2.getFirstContainer(meloElt, LaPackage.Literals.LOGICAL_ARCHITECTURE));
        } else {
          result.add(containerLc);
        }
      } else if (CapellaLayerCheckingExt.isInPhysicalLayer(meloElt)) {
        PhysicalComponent containerLc = (PhysicalComponent) EcoreUtil2.getFirstContainer(meloElt, PaPackage.Literals.PHYSICAL_COMPONENT);
        if (null == containerLc) {
          result.add(EcoreUtil2.getFirstContainer(meloElt, PaPackage.Literals.PHYSICAL_ARCHITECTURE));
        } else {
          result.add(containerLc);
        }
      } else if (CapellaLayerCheckingExt.isInEPBSLayer(meloElt)) {
        result.add(EcoreUtil2.getFirstContainer(meloElt, EpbsPackage.Literals.EPBS_ARCHITECTURE));
      }

    }
    return result;
  }

}
