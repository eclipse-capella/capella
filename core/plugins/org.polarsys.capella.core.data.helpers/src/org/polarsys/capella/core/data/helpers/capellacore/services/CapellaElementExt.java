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
   * @param dataDefListExisting2
   * @param dataDefListExisting
   * @return
   */
  public static List<CapellaElement> setSubtraction(List<? extends CapellaElement> dataDefListExisting2, List<? extends CapellaElement> dataDefListExisting) {
    List<CapellaElement> listResult = new ArrayList<>();
    for (CapellaElement eltA : dataDefListExisting2) {
      if (!(dataDefListExisting.contains(eltA))) {
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
    List<PartitionableElement> result = new ArrayList<>();
    List<Partition> ownedPartitions = current.getOwnedPartitions();
    List<PartitionableElement> children = new ArrayList<>();
    for (Partition partition : ownedPartitions) {
      if (partition.getAbstractType() instanceof PartitionableElement) {
        children.add((PartitionableElement) partition.getAbstractType());
      }
    }
    result.addAll(children);

    for (PartitionableElement partitionableElement : children) {
      result.addAll(getAllDescendants(partitionableElement));
    }

    return result;
  }
}
