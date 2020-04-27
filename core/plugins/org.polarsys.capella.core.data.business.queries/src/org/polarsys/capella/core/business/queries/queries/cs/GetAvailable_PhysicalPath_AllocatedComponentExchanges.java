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
package org.polarsys.capella.core.business.queries.queries.cs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.queries.AbstractQuery;
import org.polarsys.capella.common.queries.queryContext.IQueryContext;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.cs.AbstractPathInvolvedElement;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.DeployableElement;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.pa.PhysicalComponent;
import org.polarsys.capella.core.model.helpers.ComponentExt;
import org.polarsys.capella.core.model.helpers.LogicalComponentExt;
import org.polarsys.capella.core.model.helpers.PartExt;
import org.polarsys.capella.core.model.helpers.PhysicalComponentExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;
import org.polarsys.capella.core.model.utils.ListExt;

public class GetAvailable_PhysicalPath_AllocatedComponentExchanges extends AbstractQuery {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Object> execute(Object input, IQueryContext context) {
		CapellaElement capellaElement = (CapellaElement) input;
		List<EObject> availableElements = getAvailableElements(capellaElement);
		return (List) availableElements;
	}

	/** 
	 * {@inheritDoc}
	 */
	public List<EObject> getAvailableElements(CapellaElement element) {
		List<EObject> availableElements = new ArrayList<EObject>();
		if (element instanceof PhysicalPath) {
			availableElements.addAll(getRule_MQRY_PhysicalPath_AllocatedComponentExchanges_12((PhysicalPath) element));
		}
		availableElements = ListExt.removeDuplicates(availableElements);
		return availableElements;
	}

	/** 
	 * @param element
	 * @return
	 */
	private List<CapellaElement> getRule_MQRY_PhysicalPath_AllocatedComponentExchanges_12(PhysicalPath element) {
		List<CapellaElement> availableElements = new ArrayList<CapellaElement>(1);
		List<Component> terminalPCs = new ArrayList<Component>();
		for (PhysicalPathInvolvement first : PhysicalPathExt.getFlatFirstPhysicalPathInvolvments(element)) {
			AbstractPathInvolvedElement involvedElt = first.getInvolvedElement();
			if (involvedElt instanceof Part) {
				AbstractType pc = ((Part) involvedElt).getAbstractType();
				if ((pc instanceof Component) && !terminalPCs.contains(pc)) {
					terminalPCs.add((Component) pc);
				}
			}
		}
		for (PhysicalPathInvolvement last : PhysicalPathExt.getFlatLastPhysicalPathInvolvments(element)) {
			AbstractPathInvolvedElement involvedElt = last.getInvolvedElement();
			if (involvedElt instanceof Part) {
				AbstractType pc = ((Part) involvedElt).getAbstractType();
				if ((pc instanceof Component) && !terminalPCs.contains(pc)) {
					terminalPCs.add((Component) pc);
				}
			}
		}
		for (Component firstPC : terminalPCs) {
			for (Component lastPC : terminalPCs) {
				if (!firstPC.equals(lastPC)) {
					for (CapellaElement exchange : getAvailableExchanges(firstPC, lastPC)) {
						if (!availableElements.contains(exchange)) {
							availableElements.add(exchange);
						}
					}
				}
			}
		}
		return availableElements;
	}
	
  protected List<CapellaElement> getAvailableExchanges(Component sourceComponent, Component targetComponent) {
    List<CapellaElement> availableElements = new ArrayList<CapellaElement>();

    // collect deployed component in any of the source part
    List<Component> sourceDeployedElements =  new ArrayList<Component>(1);
    // collect deployed component in any of the target part
    List<Component> targetDeployedElements =  new ArrayList<Component>(1);

    // all the descendants of the @sourceComponent
    Collection<Component> sourceComponents = ComponentExt.getAllSubUsedComponents(sourceComponent);
    sourceComponents.add(sourceComponent);

    // all the descendants of the @targetComponent
    Collection<Component> targetComponents = ComponentExt.getAllSubUsedComponents(targetComponent);
    targetComponents.add(targetComponent);

    for (Component component : sourceComponents) {
      if (component instanceof SystemComponent) {
        sourceDeployedElements.add(component);
      } else if (component instanceof LogicalComponent) {
        sourceDeployedElements.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent) {
        // get deployed Components of source
        for (AbstractTypedElement abstractTypedElement : component.getAbstractTypedElements()) {
          if (abstractTypedElement instanceof Part) {
            for (DeployableElement deployableElement : PartExt.getAllDeployableElements((Part) abstractTypedElement)) {
              if (deployableElement instanceof Part) {
                AbstractType abstractType = ((Part) deployableElement).getAbstractType();
                if (abstractType instanceof Component) {
                  sourceDeployedElements.add((Component) abstractType);
                }
              }
            }
          }
        }
      }
    }  

    for (Component component : targetComponents) {
      if (component instanceof SystemComponent) {
        targetDeployedElements.add(component);
      } else if (component instanceof LogicalComponent) {
        targetDeployedElements.addAll(LogicalComponentExt.getAllSubComponents((LogicalComponent) component));
      } else if (component instanceof PhysicalComponent) {
        // get deployed Components of target
        for (AbstractTypedElement abstractTypedElement : component.getAbstractTypedElements()) {
          if (abstractTypedElement instanceof Part) {
            for (DeployableElement deployableElement : PartExt.getAllDeployableElements((Part) abstractTypedElement)) {
              if (deployableElement instanceof Part) {
                AbstractType abstractType = ((Part) deployableElement).getAbstractType();
                if (abstractType instanceof Component) {
                  targetDeployedElements.add((Component) abstractType);
                }
              }
            }
          }
        }
      }
    }

    // find the connection between sourceList and targetList of component
    availableElements.addAll(PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(sourceDeployedElements, targetDeployedElements));
    availableElements.addAll(PhysicalComponentExt.findConnectionsBetweenPhysicalComponentes(targetDeployedElements, sourceDeployedElements ));

    return availableElements;
  }

}