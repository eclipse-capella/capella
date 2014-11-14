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
package org.polarsys.capella.core.projection.interfaces.la2pa;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.Namespace;
import org.polarsys.capella.core.model.utils.CapellaLayerCheckingExt;
import org.polarsys.capella.core.projection.common.AbstractTransform;
import org.polarsys.capella.core.projection.common.LayersCreation;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.ITransfoRuleBase;
import org.polarsys.capella.core.tiger.impl.Transfo;
import org.polarsys.capella.core.tiger.impl.TransfoEngine;
import org.polarsys.capella.core.tiger.impl.TransfoRule;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class TransformInterfacesLa2Pa extends AbstractTransform {

  /**
   * Tiger context used. This context is declared implementing an extension point.
   */
  private static final String CAPELLA_INTERFACES_LA2PA_RULES = "org.polarsys.capella.core.projection.interfaces.la2pa.rules"; //$NON-NLS-1$

  TransfoLink temporyLink = null;

  public List<EObject> getContext() {
    return _context;
  }

  @Override
  protected ITransfo createTransfo(ITransfoRuleBase ruleBase_p) throws ClassNotFoundException {
    // Gets the base rules
    Iterator<TransfoRule> iterator = ruleBase_p.iterator();
    Transfo transfo = new Transfo(CapellacommonPackage.Literals.TRANSFO_LINK, CAPELLA_INTERFACES_LA2PA_RULES);
    // Adds the base rules:
    while (iterator.hasNext()) {
      transfo.addRule(iterator.next());
    }
    transfo.put(TransfoEngine.TRANSFO_SOURCE, _context);

    return transfo;
  }

  /**
   * Override this parent method for delete the temporary link after transformation between the LogicalInterfacePkg (in PA layer) and InterfacePkg (in LA layer)
   */
  @Override
  public void execute() {
    super.execute();
    if (temporyLink != null) {
      Namespace container = (Namespace) temporyLink.eContainer();
      container.getOwnedTraces().remove(temporyLink);
    }
  }

  public void setContext(EObject context_p) {
    if (context_p instanceof LogicalArchitecture) {
      InterfacePkg itfPkg = ((LogicalArchitecture) context_p).getOwnedInterfacePkg();
      if (itfPkg != null) {
        _context.add(itfPkg);
      }
    } else if (context_p instanceof Component) {
      InterfacePkg itfPkg = ((Component) context_p).getOwnedInterfacePkg();
      if (itfPkg != null) {
        _context.add(itfPkg);
      }
    } else if (CapellaLayerCheckingExt.isInLogicalLayer((CapellaElement) context_p)
               && ((context_p instanceof Interface) || (context_p instanceof InterfacePkg) || (context_p instanceof ExchangeItem))) {
      _context.add(context_p);
    }
  }

  @Override
  protected boolean retainContextElement(EObject contextElement, ITransfo transfo) {
    LogicalArchitecture la = (LogicalArchitecture) EcoreUtil2.getFirstContainer(contextElement, LaPackage.Literals.LOGICAL_ARCHITECTURE);
    if (la != null) {
      for (BlockArchitecture physicalArchitecture : LayersCreation.getOrCreatePaLayers(la)) {
        transfo.put(TransfoEngine.TRANSFO_SOURCE, contextElement);
        transfo.put(CapellaEngine.TRANSFO_TARGET_CONTAINER, physicalArchitecture);
      }
      return true;
    }
    return false;
  }

}
