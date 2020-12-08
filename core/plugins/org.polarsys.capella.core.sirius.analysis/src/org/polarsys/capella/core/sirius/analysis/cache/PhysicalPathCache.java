/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.sirius.analysis.cache;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.data.cs.PhysicalLink;
import org.polarsys.capella.core.data.cs.PhysicalPath;
import org.polarsys.capella.core.data.cs.PhysicalPathInvolvement;
import org.polarsys.capella.core.data.cs.PhysicalPathReference;
import org.polarsys.capella.core.model.helpers.PhysicalLinkExt;
import org.polarsys.capella.core.model.helpers.PhysicalPathExt;

public class PhysicalPathCache {

  private static PhysicalPathCache instance;

  public static PhysicalPathCache getInstance() {
    if (instance == null) {
      instance = new PhysicalPathCache();
    }
    return instance;
  }

  private Map<PhysicalPath, Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>>> previousInvolvements = new HashMap<>();
  private Map<PhysicalPath, Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>>> nextInvolvements = new HashMap<>();

  private void calculatePreviousNextInvolvements(PhysicalPath path) {
    if (!previousInvolvements.containsKey(path) && !nextInvolvements.containsKey(path)) {
      previousInvolvements.put(path, new HashMap<>());
      nextInvolvements.put(path, new HashMap<>());
      Collection<PhysicalPathInvolvement> allInvolvements = PhysicalPathExt.getFlatInvolvements(path);
      List<PhysicalPathInvolvement> allLinkInvolvements = allInvolvements.stream()
          .filter(i -> i.getInvolved() instanceof PhysicalLink).collect(Collectors.toList());
      allLinkInvolvements.stream().forEach(l -> {
        if (l.getPreviousInvolvements().get(0) instanceof PhysicalPathReference) {
          updatePreviousInvolvement(l,
              ((PhysicalPathReference) l.getPreviousInvolvements().get(0)).getReferencedPhysicalPath(),
              previousInvolvements.get(path), nextInvolvements.get(path));
        }
        if (l.getNextInvolvements().get(0) instanceof PhysicalPathReference) {
          updateNextInvolvement(l, ((PhysicalPathReference) l.getNextInvolvements().get(0)).getReferencedPhysicalPath(),
              previousInvolvements.get(path), nextInvolvements.get(path));
        }
      });
    }
  }

  public Collection<PhysicalPathInvolvement> getPreviousInvolvements(PhysicalPathInvolvement involvement,
      PhysicalPath path) {
    calculatePreviousNextInvolvements(path);
    Set<PhysicalPathInvolvement> allPreviousInvolvements = new HashSet<>();
    allPreviousInvolvements.addAll(involvement.getPreviousInvolvements());
    if (previousInvolvements.get(path).get(involvement) != null) {
      allPreviousInvolvements.addAll(previousInvolvements.get(path).get(involvement));
    }
    return allPreviousInvolvements;
  }

  public Collection<PhysicalPathInvolvement> getNextInvolvements(PhysicalPathInvolvement involvement,
      PhysicalPath path) {
    calculatePreviousNextInvolvements(path);
    Set<PhysicalPathInvolvement> allNextInvolvements = new HashSet<>();
    allNextInvolvements.addAll(involvement.getNextInvolvements());
    if (nextInvolvements.get(path).get(involvement) != null) {
      allNextInvolvements.addAll(nextInvolvements.get(path).get(involvement));
    }
    return allNextInvolvements;
  }

  private void updatePreviousInvolvement(PhysicalPathInvolvement linkInv, PhysicalPath referencedPhysicalPath,
      Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>> previousInvolvements,
      Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>> nextInvolvements) {
    Collection<PhysicalPathInvolvement> allInvolvements = PhysicalPathExt.getFlatInvolvements(referencedPhysicalPath);
    PhysicalLink link = (PhysicalLink) linkInv.getInvolvedElement();
    allInvolvements.stream()
        .filter(inv -> inv.getInvolved() instanceof Part && (PhysicalLinkExt.getSourcePart(link) == inv.getInvolved()
            || PhysicalLinkExt.getTargetPart(link) == inv.getInvolved()))
        .forEach(partInv -> {
          updateMap(previousInvolvements, linkInv, partInv);
          updateMap(nextInvolvements, partInv, linkInv);
        });
  }

  private void updateNextInvolvement(PhysicalPathInvolvement linkInv, PhysicalPath referencedPhysicalPath,
      Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>> previousInvolvements,
      Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>> nextInvolvements) {
    Collection<PhysicalPathInvolvement> allInvolvements = PhysicalPathExt.getFlatInvolvements(referencedPhysicalPath);
    PhysicalLink link = (PhysicalLink) linkInv.getInvolvedElement();
    allInvolvements.stream()
        .filter(inv -> inv.getInvolved() instanceof Part && (PhysicalLinkExt.getSourcePart(link) == inv.getInvolved()
            || PhysicalLinkExt.getTargetPart(link) == inv.getInvolved()))
        .forEach(partInv -> {
          updateMap(nextInvolvements, linkInv, partInv);
          updateMap(previousInvolvements, partInv, linkInv);
        });
  }

  private void updateMap(Map<PhysicalPathInvolvement, Set<PhysicalPathInvolvement>> invMap, PhysicalPathInvolvement inv,
      PhysicalPathInvolvement previousOrNextInv) {
    Set<PhysicalPathInvolvement> set = invMap.computeIfAbsent(inv, k -> new HashSet<>());
    set.add(previousOrNextInv);
  }

  public void reset() {
    previousInvolvements.clear();
    nextInvolvements.clear();
  }
}
