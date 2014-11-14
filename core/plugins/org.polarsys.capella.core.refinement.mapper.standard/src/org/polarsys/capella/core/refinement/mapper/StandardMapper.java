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
package org.polarsys.capella.core.refinement.mapper;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EClass;

import org.polarsys.capella.core.data.cs.AbstractActor;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.ExchangeItemAllocation;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.ctx.Actor;
import org.polarsys.capella.core.data.ctx.System;
import org.polarsys.capella.core.data.epbs.ConfigurationItem;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.la.LogicalActor;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.pa.PhysicalActor;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.CapabilityRealizationExt;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.ScenarioExt;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.core.refinement.scenarios.core.RefinementServices;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.MapperException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IMapper;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * 
 */
public class StandardMapper implements IMapper {
  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Standard Mapper"; //$NON-NLS-1$
  }

  /**
   * Depending on current object's type, this method returns candidate components:<br>
   * Actor -> Actor System -> AbstractLogicalComponent<br>
   * AbstractLogicalComponent -> AbstractLogicalComponent (if isIntraLayer == True)<br>
   * AbstractLogicalComponent -> PhysicalComponent (if isIntraLayer == False)<br>
   * PhysicalComponent -> EPBSElement<br>
   * ... else -> null
   */
  public List<AbstractInstance> candidateComponents(AbstractInstance abstractInstance_p, boolean isIntraLayer_p, Component decomposedComponent_p, NamedElement target_p, Scenario srcScenario_p, AbstractEnd srcAbstractEnd_p) throws MapperException {
	  List<AbstractInstance> partSet = new ArrayList<AbstractInstance>();

	  AbstractType partType = abstractInstance_p.getAbstractType();

	  if (partType instanceof AbstractActor) {
	    if (isIntraLayer_p) {
  	    partSet.add(abstractInstance_p);
	    } else {
	      AbstractActor abstractActor = getActorProjection((AbstractActor) partType, isIntraLayer_p);
	      if (abstractActor != null) {
	        Part projectedPart = getPartProjection((Part) abstractInstance_p);
	        if ((projectedPart != null) && abstractActor.equals(projectedPart.getAbstractType())) {
	          partSet.add(projectedPart);
	        } else {
	          partSet.addAll(RefinementServices.getReferencerParts(abstractActor));
	        }
	      }
	    }
	  }
	  else if (partType instanceof System) {
		  // Retrieves the logical components involved in Root LC decomposition
		  LogicalComponent rootLc = SystemEngineeringExt.getRootLogicalComponent((LogicalArchitecture) target_p);
		  if (rootLc != null) {
			  for (Part part : ComponentExt.getDecompositionPartInvolved(rootLc)) {
				  partSet.add(part);
			  }
		  }
	  }
	  else if (partType instanceof LogicalComponent) {
		  if (isIntraLayer_p) {
			  // Retrieve candidate LogicalComponent by other way : Search in source scenario
			  partSet = getCandidateComponentFromSourceScenario((Part) abstractInstance_p, decomposedComponent_p);
			  if (partSet.size() == 0) {
				  // Retrieves brother LCs for the target logical component (from the source Scenario)
				  LogicalComponent containerLC = LogicalComponentExt.getLogicalComponentContainerFromScenario(srcScenario_p);
				  for (Part currentBrother : ComponentExt.getDecompositionPartInvolved(containerLC)) {
					  if (!currentBrother.getAbstractType().equals(decomposedComponent_p))
						  partSet.add(currentBrother);
				  }
				  // Retrieves children LCs for target logical component
				  partSet.addAll(ComponentExt.getDecompositionPartInvolved((LogicalComponent) target_p));

				  // Retrieves LCs brothers for all parent LCs in hierarchy involved in the hierarchy CapabilityRealzation
				  // from the source logical component of the refinement
				  partSet.addAll(getParentBrothersInvolved(containerLC, srcScenario_p));
			  }
		  }
		  else {
			  // Return physical components that implement current logical component
        for (PhysicalComponent physicalComponent : LogicalComponentExt.getImplementors((LogicalComponent) partType)) {
          partSet.addAll(RefinementServices.getReferencerParts(physicalComponent));
        }
		  }
	  }
	  else if (partType instanceof PhysicalComponent) {
      if (isIntraLayer_p) {
        // Retrieve candidate PhysicalComponent by other way : Search in source scenario
        partSet = getCandidateComponentFromSourceScenario((Part) abstractInstance_p, decomposedComponent_p);
        if (partSet.size() == 0) {
          // Retrieves brother PCs for the target physical component (from the source Scenario)
          PhysicalComponent containerPC = PhysicalComponentExt.getPhysicalComponentContainerFromScenario(srcScenario_p);
          for (Part currentBrother : ComponentExt.getDecompositionPartInvolved(containerPC)) {
            if (!currentBrother.getAbstractType().equals(decomposedComponent_p))
              partSet.add(currentBrother);
          }
          // Retrieves children PCs for target physical component
          partSet.addAll(ComponentExt.getDecompositionPartInvolved((PhysicalComponent) target_p));

          // Retrieves PCs brothers for all parent PCs in hierarchy involved in the hierarchy CapabilityRealzation
          // from the source physical component of the refinement
          partSet.addAll(getParentBrothersInvolved(containerPC, srcScenario_p));
        }
      }
      else {
  		  // Return EPBS components that implement current physical  component
  		  for (ConfigurationItem ci : PhysicalComponentExt.getImplementors((PhysicalComponent) partType)) {
  		    partSet.addAll(RefinementServices.getReferencerParts(ci));      
  		  }
      }
	  }
	  return partSet;
  }

  /**
   * Retrieve candidate LogicalComponent in source scenario or among children of LogicalComponent decomposed
   * when message start/end on it
   */
  private List<AbstractInstance> getCandidateComponentFromSourceScenario(Part part_p, Component decomposedComponent_p) {
	  List<AbstractInstance> componentSet = new ArrayList<AbstractInstance>();

    Component component_p = (Component) part_p.getAbstractType();

	  if (component_p == decomposedComponent_p) {
		  // Case message start/end on target decomposition component : Only component children of component decomposed are retrieved.
		  componentSet.addAll(ComponentExt.getDecompositionPartInvolved(component_p));
	  }
	  else {
	    componentSet.add(part_p);
	  }
	  return componentSet;
  }  

  /**
   * Retrieve the AbstractActor (LogicalActor or PhysicalActor) projected from the current AbstractActor (Actor or LogicalActor)
   */
  private AbstractActor getActorProjection(AbstractActor abstractActor, boolean isIntraLayer_p) {
	  AbstractActor actorProjected = null;

	  if (isIntraLayer_p && abstractActor instanceof LogicalActor)
		  actorProjected = abstractActor;
	  else if (!isIntraLayer_p && abstractActor instanceof PhysicalActor) //Because, not specific Actor in EPBS
	    actorProjected = abstractActor;
	  else {
		  if (abstractActor instanceof Actor) {
			  actorProjected =  (LogicalActor) getReconciliation(abstractActor, LaPackage.Literals.LOGICAL_ACTOR, LaPackage.Literals.SYSTEM_ACTOR_REALIZATION);
		  }
		  else if (abstractActor instanceof LogicalActor) {
			  actorProjected =  (PhysicalActor) getReconciliation(abstractActor, PaPackage.Literals.PHYSICAL_ACTOR, PaPackage.Literals.LOGICAL_ACTOR_REALIZATION);
		  }
	  }
	  return actorProjected;
  }

  /**
   * Retrieve the Part projected from the current Part
   */
  private Part getPartProjection(Part part) {
    return (Part) getReconciliation(part, CsPackage.Literals.PART, CapellacommonPackage.Literals.TRANSFO_LINK);
  }

  /**
   * 
   */
  public List<AbstractInstance> componentMapping(COMPONENT_TYPE componentType, AbstractEventOperation invokedOperation, List<AbstractInstance> candidateAbstractInstances, AbstractEnd abstractEnd_p)
      throws MapperException {
    List<AbstractInstance> componentSet = new ArrayList<AbstractInstance>();

    for (AbstractInstance abstractInstance : candidateAbstractInstances) {
      AbstractType type = abstractInstance.getAbstractType();
      if (type instanceof Component) {
        Component cpnt = (Component) type;
        if (componentType == COMPONENT_TYPE.SENDER) {
          if (isUsingOperation(cpnt, invokedOperation)) {
            componentSet.add(abstractInstance);
          }
        } else if (componentType == COMPONENT_TYPE.RECEIVER) {
          if (isImplementingOperation(cpnt, invokedOperation)) {
            componentSet.add(abstractInstance);
          }
        }
      }
    }

    return componentSet;
  }

  /**
   * @return TRUE, if this operation is defined in one of the interfaces implemented by this 'SubSystem'.
   */
  private boolean isImplementingOperation(NamedElement implementorComponent, AbstractEventOperation implementedOperation) {
    List<Interface> itfLst = null;

    //Enlarged search to StandardPorts
    if (implementorComponent instanceof Component) {
      itfLst = ComponentExt.getAllImplementedAndProvidedInterfaces((Component) implementorComponent);
    }

    if (itfLst != null) {
      for (Interface cls : itfLst) {
        List<ExchangeItemAllocation> lstOp = cls.getOwnedExchangeItemAllocations();
        if (lstOp.contains(implementedOperation))
          return true;
        // Try to match Operation by delegation
        for (ExchangeItemAllocation operation : lstOp) {
          List<AbstractEventOperation> lstOpDelegated = RefinementServices.getDelegatedAndRefinedOperations(operation, true);
          if (lstOpDelegated.contains(implementedOperation))
            return true;
        }
      }
    }

    return false;
  }

  /**
   * @return TRUE, if the operation is defined in one of the interfaces used by this 'SubSystem'.
   */
  private boolean isUsingOperation(NamedElement userComponent, AbstractEventOperation usedOperation) {
    List<Interface> itfLst = null;

    // Enlarged search to StandardPorts
    if (userComponent instanceof AbstractActor) {
      itfLst = ComponentExt.getAllUsedAndRequiredInterfaces((AbstractActor) userComponent);
    } else if (userComponent instanceof Component) {
      itfLst = ComponentExt.getAllUsedAndRequiredInterfaces((Component) userComponent);
    }

    if (itfLst != null) {
      for (Interface itf : itfLst) {
        List<ExchangeItemAllocation> lstOp = itf.getOwnedExchangeItemAllocations();
        if (lstOp.contains(usedOperation))
          return true;
        // Try to match Operation by delegation
        for (ExchangeItemAllocation operation : lstOp) {
          List<AbstractEventOperation> lstOpDelegated = RefinementServices.getDelegatedAndRefinedOperations(operation, true);
          if (lstOpDelegated.contains(usedOperation))
            return true;
        }
      }
    }
    return false;
  }

  /**
   * 
   */
  public TraceableElement getReconciliation(TraceableElement src_p, EClass tgtMC_p, EClass linkMC_p) {
	  for (AbstractTrace abstractTrace : src_p.getIncomingTraces()) {
		  if (abstractTrace.eClass() == linkMC_p) {
			  TraceableElement traceableElt = abstractTrace.getSourceElement();
			  if (traceableElt != null && traceableElt.eClass() == tgtMC_p)
				  return traceableElt;
		  }
	  }
	  return null;
  }

  /**
   * 
   */
  private static List<Part> getParentBrothersInvolved(LogicalComponent component_p, Scenario srcScenario_p) {
	  List<LogicalComponent> involvedLcByUpperCapa = CapabilityRealizationExt.retrieveLcInvolvedByUpperCapabilityRealization(ScenarioExt.getRelatedCapability(srcScenario_p));
	  List<Part> listParentBrother = getBrothersRecursively(component_p, involvedLcByUpperCapa);
	  return listParentBrother;
  }

  /**
   * 
   */
  private static List<Part> getParentBrothersInvolved(PhysicalComponent component_p, Scenario srcScenario_p) {
    List<PhysicalComponent> involvedPcByUpperCapa = CapabilityRealizationExt.retrievePcInvolvedByUpperCapabilityRealization(ScenarioExt.getRelatedCapability(srcScenario_p));
    List<Part> listParentBrother = getBrothersRecursively(component_p, involvedPcByUpperCapa);
    return listParentBrother;
  }

  /**
   * Retrieve recursively all brothers by 'part relation' hierarchy from the current LC.
   * Only parent LC and brother LC are belong to the LogicalComponent list given in parameter is take into account.  
   */
  private static List<Part> getBrothersRecursively(Component component_p, List<? extends Component> involvedCpntByUpperCapa) {
	  List<Part> listParentBrothers = new ArrayList<Part>();

	  for (Component currentFatherCpnt : ComponentExt.getDirectParents(component_p)) {
		  if (involvedCpntByUpperCapa.contains(currentFatherCpnt) || ComponentExt.isComponentRoot(currentFatherCpnt)) {
			  for (Part currentBrotherCpnt : ComponentExt.getDecompositionPartInvolved(currentFatherCpnt)) {
				  if (currentBrotherCpnt.getAbstractType() != component_p && involvedCpntByUpperCapa.contains(currentBrotherCpnt.getAbstractType())) {
					  if (!listParentBrothers.contains(currentBrotherCpnt))
						  listParentBrothers.add(currentBrotherCpnt);
				  }
			  }
			  for (Part upperParentBrother : getBrothersRecursively(currentFatherCpnt, involvedCpntByUpperCapa)) {
				  if (!listParentBrothers.contains(upperParentBrother))
					  listParentBrothers.add(upperParentBrother);
			  }
		  }
	  }
	  return listParentBrothers;
  }
}
