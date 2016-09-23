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
package org.polarsys.capella.core.refinement.resolver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Interface;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.MessageEndExt.COMPONENT_TYPE;
import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.AbstractEventOperation;
import org.polarsys.capella.core.data.information.AbstractInstance;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.Port;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.MessageEnd;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.CapellaElementExt;
import org.polarsys.capella.core.model.helpers.RefinementLinkExt;
import org.polarsys.capella.core.refinement.scenarios.core.RefinementServices;
import org.polarsys.capella.core.refinement.scenarios.core.datastructures.ScenarioRepresentation;
import org.polarsys.capella.core.refinement.scenarios.core.exceptions.ResolverException;
import org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver;
import org.polarsys.capella.common.data.modellingcore.AbstractExchangeItem;

/**
 *
 */
public class StandardResolving implements IResolver {

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IRefinementPlug#getName()
   */
  public Object getName() {
    return "Standard Resolver"; //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.core.refinement.scenarios.core.plugs.IResolver#resolving(java.util.List, org.polarsys.capella.core.common.model.MessageEnd, org.polarsys.capella.core.data.helpers.interaction.services.model.helpers.MessageEndExt.COMPONENT_TYPE)
   *
   * This 'Resolver' verifies if it already exists a 'MessageEnd' mapped with the current 'MessageEnd'
   * If this is true, it verifies if the 'Operation' carried by this mapped 'MessageEnd' is owned by
   * the 'Interface' implemented or used by one of the candidates 'Component'.
   */
  public List<AbstractInstance> resolving(List<AbstractInstance> candidateAbstractInstances, ScenarioRepresentation srcTree, ScenarioRepresentation tgtTree, AbstractEnd srcMsg, COMPONENT_TYPE type) throws ResolverException {
	  List<AbstractInstance> selectedAbstractInstances = new ArrayList<AbstractInstance>();

	  if ((candidateAbstractInstances != null) && (srcMsg != null)) {
		  MessageEnd tgtMsg = null;
		  for (CapellaElement msg : RefinementLinkExt.getRefinementRelatedSourceElements(srcMsg, InteractionPackage.Literals.MESSAGE_END)) {
			  if (CapellaElementExt.areInSameDecompositionAlternative(msg, tgtTree.getScenario())) {
				  tgtMsg = (MessageEnd) msg;
			  }
		  }

		  if (tgtMsg != null) {
			  AbstractInstance inst = tgtMsg.getCovered().getRepresentedInstance();
			  if (inst instanceof Part) {
          Part currentTargetPart = (Part) inst;
				  Component currentTargetComponent = (Component) currentTargetPart.getAbstractType();
				  AbstractEventOperation op = MessageEndExt.getOperation(tgtMsg);
				  List<Part> validParts = new ArrayList<Part>();

				  if (type == COMPONENT_TYPE.SENDER) {
				    if (op instanceof AbstractExchangeItem) {
              for (Interface itf : ComponentExt.getAllUsedAndRequiredInterfaces(currentTargetComponent)) {
				        if (itf.getExchangeItems().contains(op)) { 
		              for (Component cpnt : getAllUsers(itf)) {
	                  for (Part part : RefinementServices.getReferencerParts(cpnt)) {
  		                if (candidateAbstractInstances.contains(part)) {
  		                  validParts.add(part);
  		                }
	                  }
		              }
				        }
				      }
	          }
				  }
				  else if (type == COMPONENT_TYPE.RECEIVER) {
            for (Interface itf : ComponentExt.getAllImplementedAndProvidedInterfaces(currentTargetComponent)) {
              if (itf.getExchangeItems().contains(op)) { 
                for (Component cpnt : getAllImplementors(itf)) {
                  for (Part part : RefinementServices.getReferencerParts(cpnt)) {
                    if (candidateAbstractInstances.contains(part)) {
                      validParts.add(part);
                    }
                  }
                }
              }
				    }
				  }

				  if (validParts.contains(currentTargetPart)) {
				    selectedAbstractInstances.add(currentTargetPart);
				  }
				  else {
				    selectedAbstractInstances.addAll(validParts);
				  }
			  }
			  else {
				  /** the instance is not related to a component */
			    selectedAbstractInstances.addAll(candidateAbstractInstances);
			  }
		  }
		  else {
			  /** there is no existing refined message */
		    selectedAbstractInstances.addAll(candidateAbstractInstances);
		  }
	  }
	  else {
		  /** the given parameters are invalid, no choice is made */
	    selectedAbstractInstances.addAll(candidateAbstractInstances);
	  }

	  return selectedAbstractInstances;
  }

  /**
   * This method returns all the components using the given interface:<br>
   *  - through a 'use' link<br>
   *  - through a required port
   */
  private Collection<Component> getAllUsers(Interface itf) {
    Collection<Component> users = new HashSet<Component>();
    for (Component cpnt : itf.getUserComponents()) {
      users.add(cpnt);
      for (GeneralizableElement elt : GeneralizableElementExt.getAllSubGeneralizableElements(cpnt)) {
        if ((elt instanceof Component) && !users.contains(elt)) {
          users.add(cpnt);
        }
      }
    }
    for (EObject obj : EObjectExt.getReferencers(itf, InformationPackage.Literals.PORT__REQUIRED_INTERFACES)) {
      if (obj instanceof Port) {
        EObject owner = ((Port) obj).eContainer();
        if ((owner instanceof Component) && !users.contains(owner)) {
          users.add((Component) owner);
        }
      }
    }
    return users;
  }

  /**
   * This method returns all the components implementing the given interface:<br>
   *  - through an 'implementation' link<br>
   *  - through a provided port
   */
  private Collection<Component> getAllImplementors(Interface itf) {
    Collection<Component> implementors = new HashSet<Component>();
    for (Component cpnt : itf.getImplementorComponents()) {
      implementors.add(cpnt);
      for (GeneralizableElement elt : GeneralizableElementExt.getAllSubGeneralizableElements(cpnt)) {
        if ((elt instanceof Component) && !implementors.contains(elt)) {
          implementors.add(cpnt);
        }
      }
    }
    for (EObject obj : EObjectExt.getReferencers(itf, InformationPackage.Literals.PORT__PROVIDED_INTERFACES)) {
      if (obj instanceof Port) {
        EObject owner = ((Port) obj).eContainer();
        if ((owner instanceof Component) && !implementors.contains(owner)) {
          implementors.add((Component) owner);
        }
      }
    }
    return implementors;
  }
}
