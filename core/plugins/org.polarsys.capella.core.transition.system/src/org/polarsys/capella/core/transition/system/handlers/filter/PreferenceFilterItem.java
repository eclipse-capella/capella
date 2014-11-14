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
package org.polarsys.capella.core.transition.system.handlers.filter;

import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.commands.preferences.service.AbstractPreferencesInitializer;
import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPort;
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
import org.polarsys.capella.core.data.information.datavalue.DataValue;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.capellacommon.Mode;
import org.polarsys.capella.core.data.capellacommon.Region;
import org.polarsys.capella.core.data.capellacommon.State;
import org.polarsys.capella.core.data.capellacommon.StateMachine;
import org.polarsys.capella.core.data.capellacommon.StateTransition;
import org.polarsys.capella.core.data.capellacommon.TransfoLink;
import org.polarsys.capella.core.data.capellacore.AbstractPropertyValue;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyLiteral;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyType;
import org.polarsys.capella.core.data.capellacore.EnumerationPropertyValue;
import org.polarsys.capella.core.data.capellacore.PropertyValueGroup;
import org.polarsys.capella.core.data.capellacore.PropertyValuePkg;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.LogicalComponentRealization;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponentNature;
import org.polarsys.capella.core.data.pa.deployment.PartDeploymentLink;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem;
import org.polarsys.capella.core.transition.system.preferences.PreferenceConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class PreferenceFilterItem extends AbstractFilterItem {

  private boolean getDisplayPref(String prefName_p) {
    return AbstractPreferencesInitializer.getBoolean(prefName_p, false);
  }

  protected EObject getSemantic(IDifference difference_p, Role role_p) {
    EObject result = null;

    // Difference on Reference of element
    if (difference_p instanceof IReferenceValuePresence) {
      IReferenceValuePresence rvp = (IReferenceValuePresence) difference_p;
      result = rvp.getValue().get(role_p);
    }

    // Difference on Presence of new element
    if (difference_p instanceof IElementPresence) {
      IElementPresence ep = (IElementPresence) difference_p;
      result = ep.getElement();
    }

    // Difference on Attribute of an element
    if (difference_p instanceof IAttributeValuePresence) {
      IAttributeValuePresence avp = (IAttributeValuePresence) difference_p;
      result = avp.getElementMatch().get(Role.REFERENCE);
    }
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDisplayable(IDifference difference_p, Role role_p, IContext context_p) {
    EObject me = getSemantic(difference_p, role_p);
    boolean _isToDisplay = true;

    if (me instanceof AbstractFunction) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_F);
    } else if (me instanceof PhysicalComponent) {
      PhysicalComponent pc = (PhysicalComponent) me;
      if (pc.getNature() == PhysicalComponentNature.BEHAVIOR) {
        _isToDisplay = getDisplayPref(PreferenceConstants.P_BC);
      } else {
        _isToDisplay = getDisplayPref(PreferenceConstants.P_IC);
      }
    } else if (me instanceof AbstractActor) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Actor);
    } else if (me instanceof AbstractCapability) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_CAPABILITY);
    } else if (me instanceof Part) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Part);
    } else if (me instanceof PhysicalLink) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_PL);
    } else if (me instanceof FunctionalExchange) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_FE);
    } else if (me instanceof ComponentExchange) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_CE);
    } else if (me instanceof PartDeploymentLink) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_DL);
    } else if (me instanceof ComponentFunctionalAllocation) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_FA);
    } else if (me instanceof ComponentExchangeAllocation) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_CA);
    } else if (me instanceof ComponentExchangeFunctionalExchangeAllocation) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_FEA);
    } else if (me instanceof FunctionPort) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_FPort);
    } else if (me instanceof ComponentPort) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_CPort);
    } else if (me instanceof PhysicalPort) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_PPort);
    } else if (me instanceof FunctionRealization) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Other);
    } else if (me instanceof LogicalComponentRealization) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Other);
    } else if (me instanceof LogicalArchitectureRealization) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Other);
    } else if (me instanceof DataValue) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Data);
    } else if ((me instanceof ExchangeItem) || (me instanceof ExchangeItemElement)) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_EI);
    } else if (me instanceof Interface) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Iface);
    } else if ((me instanceof Region) || (me instanceof Mode) || (me instanceof StateMachine) || (me instanceof State) || (me instanceof StateTransition)) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_MS);
    } else if (me instanceof TransfoLink) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Other);
    } else if ((me instanceof PropertyValuePkg) || (me instanceof AbstractPropertyValue) || (me instanceof PropertyValueGroup)
               || (me instanceof EnumerationPropertyLiteral) || (me instanceof EnumerationPropertyType) || (me instanceof EnumerationPropertyValue)) {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_PropertyValues);
    } else {
      _isToDisplay = getDisplayPref(PreferenceConstants.P_Other);
    }

    return _isToDisplay;
  }

}
