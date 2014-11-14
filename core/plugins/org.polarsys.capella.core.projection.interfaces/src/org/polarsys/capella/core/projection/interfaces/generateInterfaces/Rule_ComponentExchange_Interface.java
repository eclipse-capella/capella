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
package org.polarsys.capella.core.projection.interfaces.generateInterfaces;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.model.helpers.InterfaceExt;
import org.polarsys.capella.core.projection.common.CommonRule;
import org.polarsys.capella.core.projection.common.ProjectionMessages;
import org.polarsys.capella.core.tiger.ITransfo;
import org.polarsys.capella.core.tiger.helpers.Query;
import org.polarsys.capella.core.tiger.helpers.TigerRelationshipHelper;
import org.polarsys.capella.core.transfo.misc.CapellaEngine;

/**
 */
public class Rule_ComponentExchange_Interface extends CommonRule {

  /**
   * @param eclass_p
   */
  public Rule_ComponentExchange_Interface() {
    super(FaPackage.Literals.COMPONENT_EXCHANGE, CsPackage.Literals.INTERFACE);
  }

  
  /**
   * @see org.polarsys.capella.core.projection.common.CommonRule#reasonTransformFailed(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {
    return ProjectionMessages.RelatedConnectionConveyNoExchangeItem;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.CommonRule#transformIsRequired(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    ComponentExchange e = (ComponentExchange)element_p;
    return GenerationHelper.getExchangeItems(e).size()!=0;
  }

  /**
   * @see org.polarsys.capella.core.tiger.impl.TransfoRule#attach_(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  public void firstAttach(EObject element_p, ITransfo transfo_p) {
    for (EObject tgt : Query.retrieveUnattachedTransformedElements(element_p, _transfo, getTargetType())) {
      BlockArchitecture ctx = (BlockArchitecture) transfo_p.get(CapellaEngine.TRANSFO_TARGET_CONTAINER);
      InterfacePkg pkg = ctx.getOwnedInterfacePkg();
      if (pkg==null) {
        ctx.setOwnedInterfacePkg(CsFactory.eINSTANCE.createInterfacePkg());
        pkg = ctx.getOwnedInterfacePkg();
      }

      TigerRelationshipHelper.attachElementByRel(pkg, tgt, CsPackage.Literals.INTERFACE_PKG__OWNED_INTERFACES);
    }
  }

  @Override
  protected void doAddContainer(EObject element_p, List<EObject> result_p) {
    //Nothing to do
  }

  @Override
  protected void doGoDeep(EObject element_p, List<EObject> result_p) {
    //Nothing to do
  }

  @Override
  protected Object transformElement(EObject element_p, ITransfo transfo_p) {
    Interface itf = CsFactory.eINSTANCE.createInterface();
    
    //attach to container
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(element_p);
    InterfacePkg pkg = architecture.getOwnedInterfacePkg();
    if (pkg==null) {
      architecture.setOwnedInterfacePkg(CsFactory.eINSTANCE.createInterfacePkg());
      pkg = architecture.getOwnedInterfacePkg();
    }
    
    TigerRelationshipHelper.attachElementByRel(pkg, itf, CsPackage.Literals.INTERFACE_PKG__OWNED_INTERFACES);
    return itf;
  }

  @Override
  public void update_(EObject element_p, ITransfo transfo_p) {
    super.update_(element_p, transfo_p);

    ComponentExchange e = (ComponentExchange)element_p;
    for (EObject tgt : Query.retrieveTransformedElements(element_p, _transfo, getTargetType())) {
      if (tgt instanceof Interface) {
        for (ExchangeItem item : GenerationHelper.getExchangeItems(e)) {
          if (!((Interface)tgt).getExchangeItems().contains(item)) {
            ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem((Interface)tgt, item);
            allocation.setName(item.getName());
          }
        }
      }
    }
  }

  

}
