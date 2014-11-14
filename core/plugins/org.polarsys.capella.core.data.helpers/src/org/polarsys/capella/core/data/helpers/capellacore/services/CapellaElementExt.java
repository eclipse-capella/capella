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
package org.polarsys.capella.core.data.helpers.capellacore.services;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.information.Partition;
import org.polarsys.capella.core.data.information.PartitionableElement;
import org.polarsys.capella.core.data.capellacore.CapellaElement;

public class CapellaElementExt {

  /**
   * Apply a subtraction
   * 
   * 
   * @param dataDefListExisting2_p
   * @param dataDefListExisting_p
   * @return
   */
  public static List<CapellaElement> setSubtraction(List<? extends CapellaElement> dataDefListExisting2_p, List<? extends CapellaElement> dataDefListExisting_p) {
    List<CapellaElement> listResult = new ArrayList<CapellaElement>();
    for (CapellaElement eltA : dataDefListExisting2_p) {
      if (!(dataDefListExisting_p.contains(eltA))) {
        listResult.add(eltA);
      }
    }
    return listResult;
  }

  /**
   * Get all recursive Partitions
   * @param current a <code>PartitionableElement<code>
   * @return list of <code>PartitionableElement<code>
   */
  public static List<PartitionableElement> getAllDescendants(PartitionableElement current) {
    List<PartitionableElement> result = new ArrayList<PartitionableElement>();
    List<Partition> ownedPartitions = current.getOwnedPartitions();
    List<PartitionableElement> children = new ArrayList<PartitionableElement>();
    for (Partition partition : ownedPartitions) {
      if (partition.getAbstractType() instanceof PartitionableElement) {
        PartitionableElement pa = (PartitionableElement) partition.getAbstractType();
        children.add(pa);
      }
    }
    result.addAll(children);

    for (PartitionableElement partitionableElement : children) {
      result.addAll(getAllDescendants(partitionableElement));
    }

    return result;
  }
}
