/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.model.helpers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

public class SequenceLinkExt {

  private SequenceLinkExt() {
    // prevent instantiation
  }

  private static void findClosestSemanticFCIFunctionsAsTargets(SequenceLink sequenceLink,
      Set<FunctionalChainInvolvementFunction> closestSemanticFCIFunctions, Set<SequenceLinkEnd> visitedEnds) {

    SequenceLinkEnd targetEnd = sequenceLink.getTarget();

    // prevention against infinite loop
    if (!visitedEnds.contains(targetEnd)) {
      visitedEnds.add(targetEnd);

      if (targetEnd instanceof FunctionalChainInvolvementFunction) {
        closestSemanticFCIFunctions.add((FunctionalChainInvolvementFunction) targetEnd);
      } else {
        SequenceLinkEndExt.getOutgoingSequenceLinks(targetEnd) //
            .stream() //
            .forEach(link -> findClosestSemanticFCIFunctionsAsTargets(link, closestSemanticFCIFunctions, visitedEnds)); //
      }
    }
  }

  public static Set<FunctionalChainInvolvementFunction> findClosestSemanticFCIFunctionsAsTargets(SequenceLink seqLink) {

    Set<FunctionalChainInvolvementFunction> closestSemanticFCIFunctionAsTarget = new HashSet<>();
    Set<SequenceLinkEnd> visitedEnds = new HashSet<>();
    findClosestSemanticFCIFunctionsAsTargets(seqLink, closestSemanticFCIFunctionAsTarget, visitedEnds);

    return closestSemanticFCIFunctionAsTarget;
  }

  private static void findClosestFCIFunctionsAsSources(SequenceLink seqLink,
      Set<FunctionalChainInvolvementFunction> closestSemanticFCIFunctions, Set<SequenceLinkEnd> visitedEnds) {

    SequenceLinkEnd sourceEnd = seqLink.getSource();

    // prevention against infinite loop
    if (!visitedEnds.contains(sourceEnd)) {
      visitedEnds.add(sourceEnd);

      if (sourceEnd instanceof FunctionalChainInvolvementFunction) {
        closestSemanticFCIFunctions.add((FunctionalChainInvolvementFunction) sourceEnd);
      } else {
        SequenceLinkEndExt.getIncomingSequenceLinks(sourceEnd) //
            .stream() //
            .forEach(link -> findClosestFCIFunctionsAsSources(link, closestSemanticFCIFunctions, visitedEnds));
      }
    }
  }

  public static Set<FunctionalChainInvolvementFunction> findClosestSemanticFCIFunctionsAsSources(SequenceLink seqLink) {

    HashSet<FunctionalChainInvolvementFunction> closestFCIFunctionsAsSources = new HashSet<>();
    Set<SequenceLinkEnd> visitedEnds = new HashSet<>();
    findClosestFCIFunctionsAsSources(seqLink, closestFCIFunctionsAsSources, visitedEnds);

    return closestFCIFunctionsAsSources;
  }

  public static Set<FunctionalChainInvolvementLink> getAllFCILBetweenClosestFunctionGroups(SequenceLink seqLink) {

    Set<FunctionalChainInvolvementLink> involvementLinks = new HashSet<>();
    Set<FunctionalChainInvolvementFunction> incomingFunctions = findClosestSemanticFCIFunctionsAsSources(seqLink);
    Set<FunctionalChainInvolvementFunction> outgoingFunctions = findClosestSemanticFCIFunctionsAsTargets(seqLink);

    for (FunctionalChainInvolvementFunction function : incomingFunctions) {

      List<FunctionalChainInvolvementLink> outgoingInvolvementLinks = function.getOutgoingInvolvementLinks();

      for (FunctionalChainInvolvementLink link : outgoingInvolvementLinks) {
        if ((outgoingFunctions.contains(link.getTarget()) && !link.getTarget().equals(function))) {
          involvementLinks.add(link);
        }
      }
    }

    return involvementLinks;
  }
}
