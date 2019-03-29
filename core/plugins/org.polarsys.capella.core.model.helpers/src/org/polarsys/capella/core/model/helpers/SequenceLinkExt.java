/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.model.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EObjectExt;
import org.polarsys.capella.core.data.capellacore.CapellaElement;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementFunction;
import org.polarsys.capella.core.data.fa.FunctionalChainInvolvementLink;
import org.polarsys.capella.core.data.fa.SequenceLink;
import org.polarsys.capella.core.data.fa.SequenceLinkEnd;

public class SequenceLinkExt {

  private static void findClosestSemanticFCIFunctionsAsTargets(SequenceLink seqLink,
      HashSet<FunctionalChainInvolvementFunction> closestSemanticFCIFunctions) {

    SequenceLinkEnd targetEnd = seqLink.getTarget();
    if (targetEnd instanceof CapellaElement && targetEnd instanceof FunctionalChainInvolvementFunction) {
      closestSemanticFCIFunctions.add((FunctionalChainInvolvementFunction) targetEnd);
    } else {

      List<EObject> outgoingSequenceLinks = EObjectExt.getReferencers(targetEnd,
          FaPackage.Literals.SEQUENCE_LINK__SOURCE);

      outgoingSequenceLinks //
          .stream() //
          .filter(e -> e instanceof SequenceLink) //
          .forEach(s -> findClosestSemanticFCIFunctionsAsTargets((SequenceLink) s, closestSemanticFCIFunctions)); //
    }
  }

  public static HashSet<FunctionalChainInvolvementFunction> findClosestSemanticFCIFunctionsAsTargets(SequenceLink seqLink) {

    HashSet<FunctionalChainInvolvementFunction> closestSemanticFCIFunctionAsTarget = new HashSet<>();
    findClosestSemanticFCIFunctionsAsTargets(seqLink, closestSemanticFCIFunctionAsTarget);

    return closestSemanticFCIFunctionAsTarget;
  }

  private static void findClosestFCIFunctionsAsSources(SequenceLink seqLink,
      HashSet<FunctionalChainInvolvementFunction> closestSemanticFCIFunctions) {

    SequenceLinkEnd sourceEnd = seqLink.getSource();
    if (sourceEnd instanceof CapellaElement && sourceEnd instanceof FunctionalChainInvolvementFunction) {
      closestSemanticFCIFunctions.add((FunctionalChainInvolvementFunction) sourceEnd);
    } else {

      List<EObject> incomingSequenceLinks = EObjectExt.getReferencers(sourceEnd,
          FaPackage.Literals.SEQUENCE_LINK__TARGET);

      incomingSequenceLinks //
          .stream() //
          .filter(e -> e instanceof SequenceLink) //
          .forEach(s -> findClosestFCIFunctionsAsSources((SequenceLink) s, closestSemanticFCIFunctions)); //
    }
  }

  public static HashSet<FunctionalChainInvolvementFunction> findClosestSemanticFCIFunctionsAsSources(
      SequenceLink seqLink) {

    HashSet<FunctionalChainInvolvementFunction> closestFCIFunctionsAsSources = new HashSet<>();
    findClosestFCIFunctionsAsSources(seqLink, closestFCIFunctionsAsSources);

    return closestFCIFunctionsAsSources;
  }

  public static HashSet<FunctionalChainInvolvementLink> getAllFCILBetweenClosestFunctionGroups(SequenceLink seqLink) {

    HashSet<FunctionalChainInvolvementLink> involvementLinks = new HashSet<>();
    HashSet<FunctionalChainInvolvementFunction> incomingFunctions = findClosestSemanticFCIFunctionsAsSources(seqLink);
    HashSet<FunctionalChainInvolvementFunction> outgoingFunctions = findClosestSemanticFCIFunctionsAsTargets(seqLink);

    for (FunctionalChainInvolvementFunction function : incomingFunctions) {

      List<FunctionalChainInvolvementLink> allLinks = new ArrayList<>();
      allLinks.addAll(function.getIncomingInvolvementLinks());
      allLinks.addAll(function.getOutgoingInvolvementLinks());

      for (FunctionalChainInvolvementLink link : allLinks) {
        if ((outgoingFunctions.contains(link.getTarget()) && !link.getTarget().equals(function))
            || (outgoingFunctions.contains(link.getSource()) && !link.getSource().equals(function))) {
          involvementLinks.add(link);
        }
      }
    }

    return involvementLinks;
  }
}
