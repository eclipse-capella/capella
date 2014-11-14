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
package org.polarsys.capella.core.data.helpers.interaction;

import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.common.model.helpers.IHelper;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.CapellaElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedElementHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.NamedRelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.RelationshipHelper;
import org.polarsys.capella.core.data.helpers.capellacore.delegates.TraceHelper;
import org.polarsys.capella.core.data.helpers.interaction.delegates.AbstractCapabilityHelper;
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
  public Object getValue(EObject object_p, EStructuralFeature feature_p, EAnnotation annotation_p) {
    Object ret = null;

    if (object_p instanceof AbstractCapabilityRealization) {
      ret = AbstractCapabilityRealizationHelper.getInstance().doSwitch((AbstractCapabilityRealization) object_p, feature_p);
    } else if (object_p instanceof AbstractCapability) {
      ret = AbstractCapabilityHelper.getInstance().doSwitch((AbstractCapability) object_p, feature_p);
    } else if (object_p instanceof Scenario) {
      ret = ScenarioHelper.getInstance().doSwitch((Scenario) object_p, feature_p);
    } else if (object_p instanceof InteractionOperand) {
      ret = NamedElementHelper.getInstance().doSwitch((InteractionOperand) object_p, feature_p);
    } else if (object_p instanceof InteractionState) {
      ret = InteractionStateHelper.getInstance().doSwitch((InteractionState) object_p, feature_p);
    } else if (object_p instanceof FragmentEnd) {
      ret = FragmentEndHelper.getInstance().doSwitch((FragmentEnd) object_p, feature_p);
    } else if (object_p instanceof Gate) {
      ret = MessageEndHelper.getInstance().doSwitch((Gate) object_p, feature_p);
    } else if (object_p instanceof MessageEnd) {
      ret = MessageEndHelper.getInstance().doSwitch((MessageEnd) object_p, feature_p);
    } else if (object_p instanceof ExecutionEnd) {
      ret = ExecutionEndHelper.getInstance().doSwitch((ExecutionEnd) object_p, feature_p);
    } else if (object_p instanceof InteractionUse) {
      ret = InteractionUseHelper.getInstance().doSwitch((InteractionUse) object_p, feature_p);
    } else if (object_p instanceof CombinedFragment) {
      ret = CombinedFragmentHelper.getInstance().doSwitch((CombinedFragment) object_p, feature_p);
    } else if (object_p instanceof StateFragment) {
      ret = NamedElementHelper.getInstance().doSwitch((StateFragment) object_p, feature_p);
    } else if (object_p instanceof SequenceMessage) {
      ret = SequenceMessageHelper.getInstance().doSwitch((SequenceMessage) object_p, feature_p);
    } else if (object_p instanceof InstanceRole) {
      ret = InstanceRoleHelper.getInstance().doSwitch((InstanceRole) object_p, feature_p);
    } else if (object_p instanceof Event) {
      ret = EventHelper.getInstance().doSwitch((Event) object_p, feature_p);
    } else if (object_p instanceof Execution) {
      ret = ExecutionHelper.getInstance().doSwitch((Execution) object_p, feature_p);
    } else if (object_p instanceof RefinementLink) {
      ret = TraceHelper.getInstance().doSwitch((RefinementLink) object_p, feature_p);
    } else if (object_p instanceof MergeLink) {
      ret = TraceHelper.getInstance().doSwitch((MergeLink) object_p, feature_p);
    } else if (object_p instanceof AbstractCapabilityExtensionPoint) {
      ret = NamedRelationshipHelper.getInstance().doSwitch((AbstractCapabilityExtensionPoint) object_p, feature_p);
    } else if (object_p instanceof AbstractCapabilityExtend) {
      ret = RelationshipHelper.getInstance().doSwitch((AbstractCapabilityExtend) object_p, feature_p);
    } else if (object_p instanceof AbstractCapabilityInclude) {
      ret = RelationshipHelper.getInstance().doSwitch((AbstractCapabilityInclude) object_p, feature_p);
    } else if (object_p instanceof AbstractCapabilityGeneralization) {
      ret = RelationshipHelper.getInstance().doSwitch((AbstractCapabilityGeneralization) object_p, feature_p);
    } else if (object_p instanceof AbstractFunctionAbstractCapabilityInvolvement) {
      ret = AbstractFunctionAbstractCapabilityInvolvementHelper.getInstance().doSwitch((AbstractFunctionAbstractCapabilityInvolvement) object_p, feature_p);
    } else if (object_p instanceof FunctionalChainAbstractCapabilityInvolvement) {
      ret = FunctionalChainAbstractCapabilityInvolvementHelper.getInstance().doSwitch((FunctionalChainAbstractCapabilityInvolvement) object_p, feature_p);
    } else if (object_p instanceof ScenarioRealization) {
      ret = ScenarioRealizationHelper.getInstance().doSwitch((ScenarioRealization) object_p, feature_p);
    } else if (object_p instanceof ConstraintDuration) {
      ret = NamedElementHelper.getInstance().doSwitch((ConstraintDuration) object_p, feature_p);
    } else if (object_p instanceof TimeLapse) {
      ret = NamedElementHelper.getInstance().doSwitch((TimeLapse) object_p, feature_p);
    } else if (object_p instanceof SequenceMessageValuation){
      ret = CapellaElementHelper.getInstance().doSwitch((CapellaElement) object_p, feature_p);
    }

    if (null != ret || feature_p.getUpperBound() == 1)
      return ret;

    throw new HelperNotFoundException();
  }
}
