/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.system.handlers.log;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.Structure;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentRealization;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.fa.ComponentExchange;
import org.polarsys.capella.core.data.fa.ComponentExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentExchangeFunctionalExchangeAllocation;
import org.polarsys.capella.core.data.fa.ComponentFunctionalAllocation;
import org.polarsys.capella.core.data.fa.ComponentPort;
import org.polarsys.capella.core.data.fa.FunctionPort;
import org.polarsys.capella.core.data.fa.FunctionRealization;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.information.datatype.DataType;
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.transition.common.handlers.log.DefaultLogHandler;

/**
 *
 */
public class CapellaLogHandler extends DefaultLogHandler {

  /**
   * @param reportComponent
   */
  public CapellaLogHandler(String reportComponent) {
    super(reportComponent);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getReadableText(EObject obj) {
    String eltLabel;
    String meLabel = super.getReadableText(obj);
    String text = "?"; //$NON-NLS-1$

    if (obj instanceof AbstractFunction) {
      AbstractFunction pf = (AbstractFunction) obj;
      eltLabel = (pf.getLabel() != null) ? pf.getLabel() : "?"; //$NON-NLS-1$
      text = "Function : " + eltLabel; //$NON-NLS-1$
    } else if (ComponentExt.isActor(obj)) {
      Component me = (Component) obj;
      eltLabel = (me.getLabel() != null) ? me.getLabel() : "?"; //$NON-NLS-1$
      text = "Actor : " + eltLabel; //$NON-NLS-1$
    } else if (obj instanceof SystemComponent) {
      SystemComponent me = (SystemComponent) obj;
      eltLabel = (me.getLabel() != null) ? me.getLabel() : "?"; //$NON-NLS-1$
      text = "System Component : " + eltLabel; //$NON-NLS-1$
    } else if (obj instanceof LogicalComponent) {
      LogicalComponent me = (LogicalComponent) obj;
      eltLabel = (me.getLabel() != null) ? me.getLabel() : "?"; //$NON-NLS-1$
      text = "Logical Component : " + eltLabel; //$NON-NLS-1$
    } else if (obj instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) obj;
      eltLabel = (pc.getLabel() != null) ? pc.getLabel() : "?"; //$NON-NLS-1$
      if (pc.getNature() == PhysicalComponentNature.BEHAVIOR) {
        text = "Behavioral Component : " + eltLabel; //$NON-NLS-1$
      } else {
        text = "Implementation Component : " + eltLabel; //$NON-NLS-1$
      }
    } else if (obj instanceof Part) {
      Part part = (Part) obj;
      eltLabel = (part.getLabel() != null) ? part.getLabel() : "?"; //$NON-NLS-1$
      text = "Part : " + eltLabel; //$NON-NLS-1$
    } else if (obj instanceof PhysicalLink) {
      PhysicalLink pl = (PhysicalLink) obj;
      eltLabel = (pl.getLabel() != null) ? pl.getLabel() : "?"; //$NON-NLS-1$
      text = "Physical Link : " + eltLabel; //$NON-NLS-1$
    } else if (obj instanceof FunctionalExchange) {
      FunctionalExchange fe = (FunctionalExchange) obj;
      eltLabel = (fe.getLabel() != null) ? fe.getLabel() : "?"; //$NON-NLS-1$
      String feSource = (fe.getSource() != null) ? EObjectLabelProviderHelper.getText(fe.getSource().eContainer()) : "?"; //$NON-NLS-1$
      String feTarget = (fe.getTarget() != null) ? EObjectLabelProviderHelper.getText(fe.getTarget().eContainer()) : "?"; //$NON-NLS-1$
      text = "Functional Exchange : " + eltLabel + " between " + feSource + " and " + feTarget; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } else if (obj instanceof ComponentExchange) {
      ComponentExchange c = (ComponentExchange) obj;
      eltLabel = (c.getLabel() != null) ? c.getLabel() : "?"; //$NON-NLS-1$
      String cSource = (c.getSource() != null) ? EObjectLabelProviderHelper.getText(c.getSource().eContainer()) : "?"; //$NON-NLS-1$
      String cTarget = (c.getTarget() != null) ? EObjectLabelProviderHelper.getText(c.getTarget().eContainer()) : "?"; //$NON-NLS-1$
      text = "Component Exchange : " + eltLabel + " between " + cSource + " and " + cTarget; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    } else if (obj instanceof PartDeploymentLink) {
      PartDeploymentLink pdl = (PartDeploymentLink) obj;
      String source = (pdl.getDeployedElement() != null) ? pdl.getDeployedElement().getLabel() : "?"; //$NON-NLS-1$
      String target = (pdl.getLocation() != null) ? pdl.getLocation().getLabel() : "?"; //$NON-NLS-1$
      text = "Deployment Link : " + source + " -> " + target; //$NON-NLS-1$ //$NON-NLS-2$
    } else if (obj instanceof ComponentFunctionalAllocation) {
      ComponentFunctionalAllocation cfa = (ComponentFunctionalAllocation) obj;
      String source = (cfa.getTargetElement() != null) ? cfa.getTargetElement().getLabel() : "?"; //$NON-NLS-1$
      String target = (cfa.getSourceElement() != null) ? cfa.getSourceElement().getLabel() : "?"; //$NON-NLS-1$
      text = "Functional Allocation : " + source + " -> " + target; //$NON-NLS-1$ //$NON-NLS-2$
    } else if (obj instanceof ComponentExchangeAllocation) {
      ComponentExchangeAllocation ca = (ComponentExchangeAllocation) obj;
      String source = (ca.getTargetElement() != null) ? ca.getTargetElement().getLabel() : "?"; //$NON-NLS-1$
      String target = (ca.getSourceElement() != null) ? ca.getSourceElement().getLabel() : "?"; //$NON-NLS-1$
      text = "Component Exchange Allocation : " + source + " -> " + target; //$NON-NLS-1$ //$NON-NLS-2$

    } else if (obj instanceof ComponentExchangeFunctionalExchangeAllocation) {
      ComponentExchangeFunctionalExchangeAllocation cfea = (ComponentExchangeFunctionalExchangeAllocation) obj;
      String source = (cfea.getTargetElement() != null) ? cfea.getTargetElement().getLabel() : "?"; //$NON-NLS-1$
      String target = (cfea.getSourceElement() != null) ? cfea.getSourceElement().getLabel() : "?"; //$NON-NLS-1$
      text = "Functional Exchange Allocation : " + source + " -> " + target; //$NON-NLS-1$ //$NON-NLS-2$
    } else if (obj instanceof Port) {
      Port me = (Port) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "Port : " + name; //$NON-NLS-1$
    } else if (obj instanceof DataType) {
      DataType me = (DataType) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "DataType : " + name; //$NON-NLS-1$
    } else if (obj instanceof BlockArchitecture) {
      BlockArchitecture me = (BlockArchitecture) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "Architecture : " + name; //$NON-NLS-1$
    } else if (obj instanceof AbstractCapability) {
      AbstractCapability me = (AbstractCapability) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "Capability : " + name; //$NON-NLS-1$
    } else if (obj instanceof Structure) {
      Structure me = (Structure) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "Package : " + name; //$NON-NLS-1$
    } else if (obj instanceof DataValue) {
      String name = (meLabel != null) ? meLabel : "?"; //$NON-NLS-1$
      text = "DataValue : " + name; //$NON-NLS-1$
    } else if (obj instanceof StateMachine) {
      StateMachine me = (StateMachine) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "StateMachine : " + name; //$NON-NLS-1$
    } else if (obj instanceof Region) {
      Region me = (Region) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "Region : " + name; //$NON-NLS-1$
    } else if (obj instanceof ExchangeItem) {
      ExchangeItem me = (ExchangeItem) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "ExchangeItem : " + name; //$NON-NLS-1$
    } else if (obj instanceof ExchangeItemElement) {
      ExchangeItemElement me = (ExchangeItemElement) obj;
      String name = (me.getName() != null) ? me.getName() : "?"; //$NON-NLS-1$
      text = "ExchangeItem Element : " + name; //$NON-NLS-1$
    } else {
      if (meLabel != null) {
        text = meLabel;
      }
    }

    return text;

  }

  public enum DiffElement {
    Fct, BC, IC, Actor, Part, FE, CE, PL, DL, FA, CA, FEA, FR, LCR, FPort, CPort, PPort, EI, Iface, Data, MS, Other
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getIdentifier(EObject me) {

    if (me instanceof AbstractFunction) {
      return DiffElement.Fct.toString();
    } else if (me instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) me;
      if (pc.getNature() == PhysicalComponentNature.BEHAVIOR) {
        return DiffElement.BC.toString();
      } else {
        return DiffElement.IC.toString();
      }
    } else if (me instanceof Part) {
      return DiffElement.Part.toString();
    } else if (ComponentExt.isActor(me)) {
      return DiffElement.Part.toString();
    } else if (me instanceof PhysicalLink) {
      return DiffElement.PL.toString();
    } else if (me instanceof FunctionalExchange) {
      return DiffElement.FE.toString();
    } else if (me instanceof ComponentExchange) {
      return DiffElement.CE.toString();
    } else if (me instanceof PartDeploymentLink) {
      return DiffElement.DL.toString();
    } else if (me instanceof ComponentFunctionalAllocation) {
      return DiffElement.FA.toString();
    } else if (me instanceof ComponentExchangeAllocation) {
      return DiffElement.CA.toString();

    } else if (me instanceof ComponentExchangeFunctionalExchangeAllocation) {
      return DiffElement.FEA.toString();
    } else if (me instanceof FunctionPort) {
      return DiffElement.FPort.toString();
    } else if (me instanceof ComponentPort) {
      return DiffElement.CPort.toString();
    } else if (me instanceof PhysicalPort) {
      return DiffElement.PPort.toString();
    } else if (me instanceof FunctionRealization) {
      return DiffElement.FR.toString();
    } else if (me instanceof ComponentRealization) {
      return DiffElement.LCR.toString();
    } else if (me instanceof LogicalArchitectureRealization) {
      return DiffElement.Other.toString();
    } else if (me instanceof DataValue) {
      return DiffElement.Data.toString();
    } else if ((me instanceof ExchangeItem) || (me instanceof ExchangeItemElement)) {
      return DiffElement.EI.toString();
    } else if (me instanceof Interface) {
      return DiffElement.Iface.toString();
    } else if ((me instanceof Region) || (me instanceof Mode) || (me instanceof StateMachine) || (me instanceof State) || (me instanceof StateTransition)) {
      return DiffElement.MS.toString();
    } else if (me instanceof TransfoLink) {
      return DiffElement.Other.toString();
    }
    return DiffElement.Other.toString();

  }

}
