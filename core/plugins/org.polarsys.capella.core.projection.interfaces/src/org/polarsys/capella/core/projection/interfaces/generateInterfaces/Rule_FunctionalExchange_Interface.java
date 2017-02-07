/*******************************************************************************
 * Copyright (c) 2006, 2017 THALES GLOBAL SERVICES.
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
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.common.tools.report.config.ReportManagerConstants;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.CsFactory;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.InterfacePkg;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
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
public class Rule_FunctionalExchange_Interface extends CommonRule {

  /**
   * @param eclass_p
   */
  public Rule_FunctionalExchange_Interface() {
    super(FaPackage.Literals.FUNCTIONAL_EXCHANGE, CsPackage.Literals.INTERFACE);
  }

  /**
   * @see org.polarsys.capella.core.projection.common.CommonRule#reasonTransformFailed(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected String reasonTransformFailed(EObject element_p, ITransfo transfo_p) {

    FunctionalExchange exchange = (FunctionalExchange)element_p;
    boolean result = GenerationHelper.getExchangeItems(exchange).size()!=0;

    if (!result) {
      return ProjectionMessages.RelatedFunctionalExchangeConveyNoExchangeItem;
    }

    for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange.getIncomingComponentExchangeFunctionalExchangeRealizations()) {
      ComponentExchange connection = allocation.getAllocatingComponentExchange();
      if (isOrWillBeTranformedTo(connection, transfo_p, CsPackage.Literals.INTERFACE)) {
        result = false;
        break;
      }
    }

    return ICommonConstants.EMPTY_STRING;
  }

  /**
   * @see org.polarsys.capella.core.projection.common.CommonRule#transformIsRequired(org.eclipse.emf.ecore.EObject, org.polarsys.capella.core.tiger.ITransfo)
   */
  @Override
  protected boolean transformIsRequired(EObject element_p, ITransfo transfo_p) {
    FunctionalExchange exchange = (FunctionalExchange)element_p;
    boolean result = GenerationHelper.getExchangeItems(exchange).size()!=0;

    if (result) {
      for (ComponentExchangeFunctionalExchangeAllocation allocation : exchange.getIncomingComponentExchangeFunctionalExchangeRealizations()) {
        ComponentExchange connection = allocation.getAllocatingComponentExchange();
        if (isOrWillBeTranformedTo(connection, transfo_p, CsPackage.Literals.INTERFACE)) {
          result = false;
          break;
        }
      }
    }
    return result;
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
    FunctionalExchange exchange = (FunctionalExchange)element_p;
    itf.setName(exchange.getName());

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

    FunctionalExchange e = (FunctionalExchange)element_p;
    for (EObject tgt : Query.retrieveTransformedElements(element_p, _transfo, getTargetType())) {
      if (tgt instanceof Interface) {
        for (ExchangeItem item : GenerationHelper.getExchangeItems(e)) {
          if (!((Interface)tgt).getExchangeItems().contains(item)) {
            ExchangeItemAllocation allocation = InterfaceExt.addExchangeItem((Interface)tgt, item);
            notifyMessage(NLS.bind("Element ''{0}'' has been allocated into ''{1}''", //$NON-NLS-1$
                new Object[] {EObjectLabelProviderHelper.getText(item), 
                              EObjectLabelProviderHelper.getText(tgt)}), new Object[] {item, tgt}, ReportManagerConstants.LOG_LEVEL_INFO, transfo_p);
            allocation.setName(item.getName());
          }
        }
      }
    }

  }

}
