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

package org.polarsys.capella.core.data.helpers.interaction;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityExtendHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityExtensionPointHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityGeneralizationHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityIncludeHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityRealizationHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractFunctionAbstractCapabilityInvolvementHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.CombinedFragmentHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.EventHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.ExecutionEndHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.ExecutionHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.FragmentEndHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.FunctionalChainAbstractCapabilityInvolvementHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.InstanceRoleHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.InteractionStateHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.InteractionUseHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.MessageEndHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.ScenarioHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.ScenarioRealizationHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.SequenceMessageHelper;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtend;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityExtensionPoint;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityGeneralization;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityInclude;
import org.polarsys.capella.core.data.interaction.AbstractCapabilityRealization;
import org.polarsys.capella.core.data.interaction.AbstractFunctionAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.CombinedFragment;
import org.polarsys.capella.core.data.interaction.ConstraintDuration;
import org.polarsys.capella.core.data.interaction.Event;
import org.polarsys.capella.core.data.interaction.Execution;
import org.polarsys.capella.core.data.interaction.ExecutionEnd;
import org.polarsys.capella.core.data.interaction.FragmentEnd;
import org.polarsys.capella.core.data.interaction.FunctionalChainAbstractCapabilityInvolvement;
import org.polarsys.capella.core.data.interaction.Gate;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionOperand;
import org.polarsys.capella.core.data.interaction.InteractionState;
import org.polarsys.capella.core.data.interaction.InteractionUse;
import org.polarsys.capella.core.data.interaction.MergeLink;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.interaction.RefinementLink;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.interaction.ScenarioRealization;
import org.polarsys.capella.core.data.interaction.SequenceMessage;
import org.polarsys.capella.core.data.interaction.SequenceMessageValuation;
import org.polarsys.capella.core.data.interaction.StateFragment;
import org.polarsys.capella.core.data.interaction.TimeLapse;

public class CapellaInteractionHelper implements IHelper {

  /**
   * Default constructor
   */
  public CapellaInteractionHelper() {
    // Preferences are now loaded automatically when the appropriate plug-in starts due to plug-in dependencies.
    // Hence, it is not required to call checkPreferences to get DataListeners loaded.
  }

  /**
   * @see org.polarsys.capella.common.model.helpers.IHelper#getValue(org.eclipse.emf.ecore.EObject, org.eclipse.emf.ecore.EStructuralFeature,
   *      org.eclipse.emf.ecore.EAnnotation)
   */
  @Override
  public Object getValue(EObject object, EStructuralFeature feature, EAnnotation annotation) {
    Object ret = null;

    if (object instanceof AbstractCapabilityRealization) {
      ret = AbstractCapabilityRealizationHelper.getInstance().doSwitch((AbstractCapabilityRealization) object, feature);
    } else if (object instanceof AbstractCapability) {
      ret = AbstractCapabilityHelper.getInstance().doSwitch((AbstractCapability) object, feature);
    } else if (object instanceof Scenario) {
      ret = ScenarioHelper.getInstance().doSwitch((Scenario) object, feature);
    } else if (object instanceof InteractionOperand) {
      ret = NamedElementHelper.getInstance().doSwitch((InteractionOperand) object, feature);
    } else if (object instanceof InteractionState) {
      ret = InteractionStateHelper.getInstance().doSwitch((InteractionState) object, feature);
    } else if (object instanceof FragmentEnd) {
      ret = FragmentEndHelper.getInstance().doSwitch((FragmentEnd) object, feature);
    } else if (object instanceof Gate) {
      ret = MessageEndHelper.getInstance().doSwitch((Gate) object, feature);
    } else if (object instanceof MessageEnd) {
      ret = MessageEndHelper.getInstance().doSwitch((MessageEnd) object, feature);
    } else if (object instanceof ExecutionEnd) {
      ret = ExecutionEndHelper.getInstance().doSwitch((ExecutionEnd) object, feature);
    } else if (object instanceof InteractionUse) {
      ret = InteractionUseHelper.getInstance().doSwitch((InteractionUse) object, feature);
    } else if (object instanceof CombinedFragment) {
      ret = CombinedFragmentHelper.getInstance().doSwitch((CombinedFragment) object, feature);
    } else if (object instanceof StateFragment) {
      ret = NamedElementHelper.getInstance().doSwitch((StateFragment) object, feature);
    } else if (object instanceof SequenceMessage) {
      ret = SequenceMessageHelper.getInstance().doSwitch((SequenceMessage) object, feature);
    } else if (object instanceof InstanceRole) {
      ret = InstanceRoleHelper.getInstance().doSwitch((InstanceRole) object, feature);
    } else if (object instanceof Event) {
      ret = EventHelper.getInstance().doSwitch((Event) object, feature);
    } else if (object instanceof Execution) {
      ret = ExecutionHelper.getInstance().doSwitch((Execution) object, feature);
    } else if (object instanceof RefinementLink) {
      ret = TraceHelper.getInstance().doSwitch((RefinementLink) object, feature);
    } else if (object instanceof MergeLink) {
      ret = TraceHelper.getInstance().doSwitch((MergeLink) object, feature);
    } else if (object instanceof AbstractCapabilityExtensionPoint) {
      ret = AbstractCapabilityExtensionPointHelper.getInstance().doSwitch((AbstractCapabilityExtensionPoint) object, feature);
    } else if (object instanceof AbstractCapabilityExtend) {
      ret = AbstractCapabilityExtendHelper.getInstance().doSwitch((AbstractCapabilityExtend) object, feature);
    } else if (object instanceof AbstractCapabilityInclude) {
      ret = AbstractCapabilityIncludeHelper.getInstance().doSwitch((AbstractCapabilityInclude) object, feature);
    } else if (object instanceof AbstractCapabilityGeneralization) {
      ret = AbstractCapabilityGeneralizationHelper.getInstance().doSwitch((AbstractCapabilityGeneralization) object, feature);
    } else if (object instanceof AbstractFunctionAbstractCapabilityInvolvement) {
      ret = AbstractFunctionAbstractCapabilityInvolvementHelper.getInstance().doSwitch((AbstractFunctionAbstractCapabilityInvolvement) object, feature);
    } else if (object instanceof FunctionalChainAbstractCapabilityInvolvement) {
      ret = FunctionalChainAbstractCapabilityInvolvementHelper.getInstance().doSwitch((FunctionalChainAbstractCapabilityInvolvement) object, feature);
    } else if (object instanceof ScenarioRealization) {
      ret = ScenarioRealizationHelper.getInstance().doSwitch((ScenarioRealization) object, feature);
    } else if (object instanceof ConstraintDuration) {
      ret = NamedElementHelper.getInstance().doSwitch((ConstraintDuration) object, feature);
    } else if (object instanceof TimeLapse) {
      ret = NamedElementHelper.getInstance().doSwitch((TimeLapse) object, feature);
    } else if (object instanceof SequenceMessageValuation){
      ret = CapellaElementHelper.getInstance().doSwitch((CapellaElement) object, feature);
    }
    
    

    if (null != ret || feature.getUpperBound() == 1)
      return ret;

    throw new HelperNotFoundException();
  }
}
