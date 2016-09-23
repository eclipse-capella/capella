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
package org.polarsys.capella.core.refinement.processor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.interaction.AbstractCapability;
import org.polarsys.capella.core.data.la.CapabilityRealization;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.AbstractCapabilityExt;
import org.polarsys.capella.core.model.helpers.CapabilityRealizationExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.model.utils.ListExt;
import org.polarsys.capella.core.transfo.misc.TransfoHelper;

/**
 */
public class StaticRealizationContributionProcessor extends UpdateRealizationContributionProcessor {

  /**
   * Default constructor
   */
  public StaticRealizationContributionProcessor() {
    super(Kind.STATIC);
  }

  /**
   * Constructor
   *
   * @param context the {@link NamedElement} on which the processing will be applied
   */
  public StaticRealizationContributionProcessor(NamedElement context) {
    super(Kind.STATIC, context);
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "realization contribution update based on static informations"; //$NON-NLS-1$
  }

  /**
   * 
   * @param currentCapabilityUseCase
   * @param target
   */
  @Override
  protected void updateInvolvements(AbstractCapability currentCapability, EClass target) {
	  /**
	   * collects the 'Components' involved in the current realization
	   */
	  List<Component> involvedCpnts = retrieveInvolvedComponents(currentCapability);

	  /**
	   * collects the 'Component' implementing the 'Component' previously collected
	   */
	  List<CapellaElement> tgtInvolvedCpnts = new ArrayList<CapellaElement>();
	  for (Component cpnt : involvedCpnts) { 
		  if (cpnt instanceof Actor) {
			  LogicalActor logicalActor  =  (LogicalActor) TransfoHelper.getReconciliation(cpnt, LaPackage.Literals.LOGICAL_ACTOR, LaPackage.Literals.SYSTEM_ACTOR_REALIZATION);
			  if (!tgtInvolvedCpnts.contains(logicalActor)) {
				  tgtInvolvedCpnts.add(logicalActor);
			  }
		  }
		  else if (cpnt instanceof LogicalActor) {
			  PhysicalActor physicalActor  =  (PhysicalActor) TransfoHelper.getReconciliation(cpnt, PaPackage.Literals.PHYSICAL_ACTOR, PaPackage.Literals.LOGICAL_ACTOR_REALIZATION);
			  if (!tgtInvolvedCpnts.contains(physicalActor)) {
				  tgtInvolvedCpnts.add(physicalActor);
			  }
		  }
		  // Specific case for PhysicalActor because not Actor in EPBS layer
		  else if (cpnt instanceof PhysicalActor) {
		    if (!tgtInvolvedCpnts.contains(cpnt)) {
          tgtInvolvedCpnts.add(cpnt);
        }
		  }
		  else if (cpnt instanceof LogicalComponent) {
			  List<PhysicalComponent> pc = LogicalComponentExt.getImplementors((LogicalComponent) cpnt);
			  for (PhysicalComponent physicalComponent : pc) {
				  if (!tgtInvolvedCpnts.contains(physicalComponent)) {
					  tgtInvolvedCpnts.add(physicalComponent);
				  }          
			  }
		  }
		  else if (cpnt instanceof PhysicalComponent) {
			  List<ConfigurationItem> epbsList = PhysicalComponentExt.getImplementors((PhysicalComponent) cpnt);
			  for (ConfigurationItem configurationItem : epbsList) {
				  if (!tgtInvolvedCpnts.contains(configurationItem)) {
					  tgtInvolvedCpnts.add(configurationItem);          
				  }
			  }
		  }
	  }

	  /**
	   * retrieve the realization use case linked to the current realization use case
	   */
	  CapabilityRealization updatedUseCase = null;
	  for (CapellaElement elt : RefinementLinkExt.getRefinementRelatedSourceElements(currentCapability, LaPackage.Literals.CAPABILITY_REALIZATION)) {
		  if (EcoreUtil2.isContainedBy(elt, target)) {
			  updatedUseCase = (CapabilityRealization) elt;
		  }
	  }

	  if (updatedUseCase != null) {
		  /**
		   * collects the 'Component' involved with the linked realization use case
		   */
		  List<CapellaElement> tgtAlreadyInvoldedCpnts = new ArrayList<CapellaElement>();
		  for (Component cpnt : CapabilityRealizationExt.getInvolvedComponents(updatedUseCase)) {
			  tgtAlreadyInvoldedCpnts.add(cpnt);
		  }

		  /**
		   * Evaluation of the 'Component' to be added (involvement) to the linked realization use case (not yet involved)
		   */
		  List<CapellaElement> setCpntToAdd = ListExt.substract(tgtInvolvedCpnts, tgtAlreadyInvoldedCpnts);

		  /**
		   * Evaluation of the 'Component' to be removed (involvement) from the linked realization use case (currently involved)
		   */
		  List<CapellaElement> setCpntToRemove = ListExt.substract(tgtAlreadyInvoldedCpnts, tgtInvolvedCpnts);

		  /**
		   * Commit adds
		   */
		  for (CapellaElement cpnt : setCpntToAdd) {
			  if (!CapabilityRealizationExt.getInvolvedComponents(updatedUseCase).contains(cpnt)) {
				  CapabilityRealizationExt.addInvolvedComponent(updatedUseCase, (Component) cpnt);
			  }
		  }

		  /**
		   * Commit removes
		   */
		  for (CapellaElement cpnt : setCpntToRemove) {
			  if (CapabilityRealizationExt.getInvolvedComponents(updatedUseCase).contains(cpnt)) {
				  CapabilityRealizationExt.removeInvolvedComponent(updatedUseCase, (Component) cpnt);
			  }
		  }
	  }
  }

  /**
   * Retrieves the components involved in the capability 'currentUseCase'.
   * 
   * @param currentUseCase
   * @return the involved components
   */
  private List<Component> retrieveInvolvedComponents(AbstractCapability currentUseCase) {
    List<Component> interactingComponents = new ArrayList<Component>();

    for (Component cpnt : AbstractCapabilityExt.getInvolvedComponents(currentUseCase)) {
      if (!interactingComponents.contains(cpnt)) interactingComponents.add(cpnt);
    }

    return interactingComponents;
  }
}
