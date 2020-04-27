/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.semantic.queries.basic.queries.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.data.fa.AbstractFunctionalBlock;
import org.polarsys.capella.core.data.fa.FunctionalExchange;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.data.oa.Entity;
import org.polarsys.capella.core.data.oa.EntityPkg;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.common.data.activity.ActivityNode;

/**
 * This class is a temporary helper to provide some utility methods which will be given later by helpers on the related meta-classes.<br>
 */
public class ConnectionsUtils {

  /**
   * Gets the communication means on which the given functional exchange have been allocated to
   * @param functionalExchange the functional exchange
   * @return a list of <code>CommunicationMean</code> instances
   */
  public static List<CommunicationMean> getRelatedCommunicationMeans(FunctionalExchange functionalExchange) {
    List<CommunicationMean> returnValue = new ArrayList<CommunicationMean>();
    ActivityNode source = functionalExchange.getSource();
    if (source instanceof OperationalActivity) {
      OperationalActivity oa = (OperationalActivity) source;
      EList<AbstractFunctionalBlock> allocationBlocks = oa.getAllocationBlocks();
      Iterator<AbstractFunctionalBlock> iterator = allocationBlocks.iterator();
      while (iterator.hasNext()) {
        AbstractFunctionalBlock next = iterator.next();
        if (next instanceof Entity) {
          Entity entity = (Entity) next;
          List<CommunicationMean> outgoingCommunicationMeans = getOutgoingCommunicationMeans(entity);
          for (CommunicationMean commMean : outgoingCommunicationMeans) {
            if (commMean.getAllocatedFunctionalExchanges().contains(functionalExchange)) {
              returnValue.add(commMean);
            }
          }
        }
      }
    }
    return returnValue;
  }

  /**
   * Gets the outgoing communication means of an Operational Entity
   * @param entity the operational entity
   * @return a list of <code>CommunicationMean</code> instances
   */
  private static List<CommunicationMean> getOutgoingCommunicationMeans(Entity entity) {
    List<CommunicationMean> result = new ArrayList<CommunicationMean>();
    // FIXME There is no helper to get the communication mean from an entity
    // so, for now, does a little recursion over the whole package
    EObject container = entity.eContainer();
    while ((null != container) && !(container instanceof EntityPkg)) {
      container = entity.eContainer();
    }
    if (null != container) {
      EntityPkg entityPackage = (EntityPkg) container;
      TreeIterator<EObject> allContents = entityPackage.eAllContents();
      while (allContents.hasNext()) {
        EObject next = allContents.next();
        if (next instanceof CommunicationMean) {
          CommunicationMean comMean = (CommunicationMean) next;
          if (comMean.getSource() == entity) {
            result.add(comMean);
          }
        }
      }
    }
    return result;
  }
}
