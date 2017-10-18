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
package org.polarsys.capella.core.data.information.validation.partionableelement;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;

/**
 * This validation rule ensures that a partition of an element cannot have a partition typed by this element. Thus it avoids "partitions cycles".
 */
public class MDCHK_PartionableElementCyle extends AbstractValidationRule {
  
  /**
   * This list allows to store all the partitionable elements already seen in order to detect cycle involving one of them
   */
  private List<PartitionableElement> processedPartitionableElements;
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    processedPartitionableElements = new ArrayList<PartitionableElement>();
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      // Handles <code>PartitionableElement</code> instances
      if (eObj instanceof PartitionableElement) {
        // This is the code>PartitionableElement</code> that will be tested to be sure it is not a part of one of its children
        PartitionableElement partitionableElement = (PartitionableElement) eObj;
        // Gets its owned partitions
        EList<Partition> ownedPartitions = partitionableElement.getOwnedPartitions();
        // And then process recursively the partitions
        return processPartitions(ctx, partitionableElement, ownedPartitions);
      }
    }
    // No problem encountered
    return ctx.createSuccessStatus();
  }


  /**
   * Process the given <code>Partition</code>'s instances to verify that the given <code>PartitionableElement</code> () is not referenced as a one of their
   * partitions.
   * @param ctx the <code>IValidationContext</code>
   * @param partitionableElement the "parent" <code>PartitionableElement</code>
   * @param ownedPartitions the <code>Partition</code>'s instances to test
   * @return a <code>IStatus</code> instance
   */
  private IStatus processPartitions(IValidationContext ctx, PartitionableElement partitionableElement, EList<Partition> ownedPartitions) {
    // Gets the name of the "parent" partitionable element
    processedPartitionableElements.add(partitionableElement);
    for (Partition partition : ownedPartitions) {
      // Iterates other the owned partitions
      // Gets the type of the part
      Type partitionType = partition.getType();
      if (partitionType instanceof PartitionableElement) {
        // If it is also a <code>PartitionableElement</code>, checks its partitions to be sure no one references the "parent" partition"
        PartitionableElement innerPartitionableElement = (PartitionableElement) partitionType;
        // Also test this one

        // Gets the partition name
        String partitionName = innerPartitionableElement.getName();
        EList<Partition> innerOwnedPartitions = innerPartitionableElement.getOwnedPartitions();
        // Test each one of the inner partitions
        for (Partition innerPartition : innerOwnedPartitions) {
          Type innerType = innerPartition.getType();
          String innerPartitionInnerPartitionName = innerPartition.getName();
          for (PartitionableElement alreadySawElem : processedPartitionableElements) {
            if (innerType == alreadySawElem) {
              String alreadySawElemName = alreadySawElem.getName();
              // There is a cycle!!!
              return ctx.createFailureStatus(new Object[] { alreadySawElemName, partitionName, innerPartitionInnerPartitionName });
            }
          }
        }
        // No cycle has been found yet, so process the inner partitions recursively
        // processes the partitions recursively:
        return processPartitions(ctx, innerPartitionableElement, innerOwnedPartitions);
      }
    }
    return ctx.createSuccessStatus();
  }
}
