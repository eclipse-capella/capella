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
package org.polarsys.capella.core.data.cs.validation.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.utils.NamingHelper;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This validation rule ensures that a partition of an element cannot have a partition typed by this element. Thus it avoids "partitions cycles".
 */
public class MDCHK_ComponentCycle extends AbstractValidationRule {
  
  /**
   * This list allows to store all the partitionable elements already seen in order to detect cycle involving one of them
   */
  private List<Component> processedElements;
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    processedElements = new ArrayList<Component>();
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // Handles <code>PartitionableElement</code> instances
      if (eObj instanceof Component) {
        // This is the code>PartitionableElement</code> that will be tested to be sure it is not a part of one of its children
        Component partitionableElement = (Component) eObj;
        // And then process recursively the partitions
        IStatus status = processPartitions(ctx, partitionableElement, partitionableElement.getContainedParts());
        processedElements = null;
        return status;
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }


  /**
   * Process the given <code>Partition</code>'s instances to verify that the given <code>PartitionableElement</code> () is not referenced as a one of their
   * partitions.
   * @param ctx the <code>IValidationContext</code>
   * @param component the "parent" <code>PartitionableElement</code>
   * @param ownedParts the <code>Partition</code>'s instances to test
   * @return a <code>IStatus</code> instance
   */
  private IStatus processPartitions(IValidationContext ctx, Component component, EList<Part> ownedParts) {
    // Gets the name of the "parent" partitionable element
    processedElements.add(component);
    for (Part part : ownedParts) {
      // Iterates other the owned partitions
      // Gets the type of the part
      Type type = part.getType();
      if (type instanceof Component) {
        // If it is also a <code>PartitionableElement</code>, checks its partitions to be sure no one references the "parent" partition"
        Component inner = (Component) type;
        // Also test this one

        // Gets the partition name
        String partitionName = inner.getName();
        EList<Part> innerParts = inner.getContainedParts();
        // Test each one of the inner partitions
        for (Part innerPart : innerParts) {
          Type innerType = innerPart.getType();
          String innerPartitionInnerPartitionName = innerPart.getName();
          for (Component alreadySawElem : processedElements) {
            if (innerType == alreadySawElem) {
              String alreadySawElemName = alreadySawElem.getName();
              // There is a cycle!!!
              return ctx.createFailureStatus(new Object[] { alreadySawElemName, partitionName,
                  innerPartitionInnerPartitionName, NamingHelper.getTitleLabel(alreadySawElem),
                  NamingHelper.getTitleLabel(inner), NamingHelper.getTitleLabel(innerPart) });
            }
          }
        }
        // No cycle has been found yet, so process the inner partitions recursively
        // processes the partitions recursively:
        return processPartitions(ctx, inner, innerParts);
      }
    }
    return ctx.createSuccessStatus();
  }
}
