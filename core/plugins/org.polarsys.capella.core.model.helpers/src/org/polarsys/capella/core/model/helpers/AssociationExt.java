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
package org.polarsys.capella.core.model.helpers;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.information.AggregationKind;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.AssociationPkg;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.Property;

public class AssociationExt {

  public static Collection<Classifier> getLinkedClassifiers(Association association_p) {
    Collection<Classifier> returnedClassifiers = new HashSet<Classifier>();
    for (Property aProperty : getProperties(association_p)) {
      if ((aProperty.getType() != null) && (aProperty.getType() instanceof Classifier)) {
        returnedClassifiers.add((Classifier) aProperty.getType());
      }
    }
    return returnedClassifiers;
  }

  public static Set<Classifier> getOwnedMembersClassifiers(Association association_p) {
    Set<Classifier> returnedClassifiers = new HashSet<Classifier>();
    for (Property aProperty : getOwnedMembersProperties(association_p)) {
      if ((aProperty.getType() != null) && (aProperty.getType() instanceof Classifier)) {
        returnedClassifiers.add((Classifier) aProperty.getType());
      }
    }
    return returnedClassifiers;
  }

  public static Set<Classifier> getNavigableMembersClassifiers(Association association_p) {
    Set<Classifier> returnedClassifiers = new HashSet<Classifier>();
    for (Property aProperty : getNavigableMembersProperties(association_p)) {
      if ((aProperty.getType() != null) && (aProperty.getType() instanceof Classifier)) {
        returnedClassifiers.add((Classifier) aProperty.getType());
      }
    }
    return returnedClassifiers;
  }

  public static Class getSourceClass(Association association_p) {
    Set<Classifier> ownedMembersClassifiers = getOwnedMembersClassifiers(association_p);
    if (!ownedMembersClassifiers.isEmpty()) {
      Classifier classifier = ownedMembersClassifiers.iterator().next();
      if (classifier instanceof Class) {
        return (Class) classifier;
      }

    }
    return null;

  }

  public static Collection<Property> getProperties(Association association_p) {
    Collection<Property> returnedProperties = new HashSet<Property>();

    returnedProperties.addAll(association_p.getNavigableMembers());
    returnedProperties.addAll(association_p.getOwnedMembers());

    return returnedProperties;
  }

  public static Collection<Property> getOwnedMembersProperties(Association association_p) {
    Collection<Property> returnedProperties = new HashSet<Property>();

    returnedProperties.addAll(association_p.getOwnedMembers());

    return returnedProperties;
  }

  public static Collection<Property> getNavigableMembersProperties(Association association_p) {
    Collection<Property> returnedProperties = new HashSet<Property>();

    returnedProperties.addAll(association_p.getNavigableMembers());

    return returnedProperties;
  }

  /**
   * Moves the association to its correct container depending on its direction:<br/>
   * <li>Unidirectional associations are moved to the source classes data package</li> <br/>
   * <li>Nondirectional & bidirectional associations are moved to source & target classes common ancestor package</li>
   * @param association_p
   */
  public static void moveToCorrectContainer(Association assoc_p) {

    if (AssociationExt.isUnidirectional(assoc_p)) {

      Set<Classifier> ownedMembers = getOwnedMembersClassifiers(assoc_p);

      // Move the association to the source classifier container
      Classifier sourceClassifier = null;
      if (ownedMembers.iterator().hasNext()) {
        sourceClassifier = ownedMembers.iterator().next();
      }
      if (null != sourceClassifier) {
        EObject eContainer = sourceClassifier.eContainer();
        if (eContainer instanceof DataPkg) {
          moveToPackage(Arrays.asList(assoc_p), (DataPkg) eContainer);
        }
      }
    }
    if (isBidirectional(assoc_p) || isNondirectional(assoc_p)) {
      // move association to source & target classes common ancestor package
      AssociationExt.moveToBestContainer(assoc_p);

    }
    return;

  }

  public static void moveToBestContainer(Association association_p) {
    EObject bestAncestor = getLinkedClassifiersCommonAncestor(association_p);
    if ((bestAncestor != null) && (bestAncestor instanceof AssociationPkg) && !association_p.eContainer().equals(bestAncestor)) {
      ((AssociationPkg) bestAncestor).getOwnedAssociations().add(association_p);
    }
  }

  /**
   * returns the common ancestor of association linked classifiers.
   * @see {@link AssociationExt#getLinkedClassifiersCommonAncestorOtherwiseSelfContainer(Association)}
   * @param association_p
   * @return
   */
  public static EObject getLinkedClassifiersCommonAncestor(Association association_p) {
    return EcoreUtil2.getCommonAncestor(getLinkedClassifiers(association_p));
  }

  /**
   * returns the common ancestor of association linked classifiers or the container of the unique linked classifier (e.g. in case of a cyclic association on the
   * same class)
   * @see {@link AssociationExt#getLinkedClassifiersCommonAncestor(Association)}
   * @param association_p
   * @return
   */
  public static EObject getLinkedClassifiersCommonAncestorOtherwiseSelfContainer(
      Association association_p) {
    Collection<Classifier> linkedClassifiers = getLinkedClassifiers(association_p);
    EObject commonAncestor = EcoreUtil2.getCommonAncestor(linkedClassifiers);
    if (linkedClassifiers.contains(commonAncestor) && (linkedClassifiers.size() == 1)) {
      return null != commonAncestor ? commonAncestor.eContainer() : null;
    } else {
      return commonAncestor;
    }
  }

  /**
   * @param asso_p
   * @param linkedClassifiers_p
   */
  public static boolean isInCommonAncestorOf(Association asso_p, Collection<Classifier> linkedClassifiers_p) {
    EObject commonAncestor = EcoreUtil2.getCommonAncestor(linkedClassifiers_p);
    if ((null == commonAncestor) || (null == asso_p.eContainer())) {
      return false;
    } else if ((null != linkedClassifiers_p) && (linkedClassifiers_p.size() == 1)) {

      Iterator<Classifier> iterator = linkedClassifiers_p.iterator();
      if (iterator.hasNext()) {
        Classifier next = iterator.next();
        if (next.eContainer() instanceof DataPkg) {
          commonAncestor = next.eContainer();
        }
      }
    }
    return asso_p.eContainer().equals(commonAncestor);

  }

  public static void moveToPackage(List<Association> assocs_p, DataPkg dataPkg_p) {
    for (Association assoc : assocs_p) {
      dataPkg_p.getOwnedAssociations().add(assoc);
    }

  }

  public static boolean isComposition(Association ass_p) {
    if (null != ass_p) {
      for (Property property : getProperties(ass_p)) {
        AggregationKind aggregationKind = property.getAggregationKind();
        if ((aggregationKind != null) && (aggregationKind == AggregationKind.COMPOSITION)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Check if given association is of kind "ASSOCIATION"
   * @param ass_p
   * @return
   */
  public static boolean isOfKindAssocation(Association ass_p) {
    if (null != ass_p) {
      for (Property property : getProperties(ass_p)) {
        AggregationKind aggregationKind = property.getAggregationKind();
        if ((aggregationKind != null) && (aggregationKind == AggregationKind.ASSOCIATION)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param association_p
   * @return
   */
  public static boolean isUnidirectional(EObject association_p) {
    if (association_p instanceof Association) {
      Association assoc = (Association) association_p;
      return ((null != assoc.getNavigableMembers()) && (null != assoc.getOwnedMembers()) && (assoc.getNavigableMembers().size() == 1) && (assoc
          .getOwnedMembers().size() == 1));
    }
    return false;
  }

  /**
   * @param association_p
   * @return
   */
  public static boolean isBidirectional(EObject association_p) {
    if (association_p instanceof Association) {
      Association assoc = (Association) association_p;
      return ((null != assoc.getNavigableMembers()) && (assoc.getNavigableMembers().size() == 2) && isNullOrEmpty(assoc.getOwnedMembers()));
    }
    return false;
  }

  /**
   * @param association_p
   * @return
   */
  public static boolean isNondirectional(EObject association_p) {
    if (association_p instanceof Association) {
      Association assoc = (Association) association_p;
      return ((null != assoc.getOwnedMembers()) && (assoc.getOwnedMembers().size() == 2) && isNullOrEmpty(assoc.getNavigableMembers()));
    }
    return false;
  }

  /**
   * @param ownedMembers_p
   * @return
   */
  private static boolean isNullOrEmpty(EList<Property> ownedMembers_p) {
    return (null == ownedMembers_p) || ownedMembers_p.isEmpty();
  }

  public static boolean isInCorrectLocation(Association ass_p) {
    if (AssociationExt.isUnidirectional(ass_p)) {
      // check that association container is the association source class container
      Class sourceClass = AssociationExt.getSourceClass(ass_p);
      if (null != sourceClass) {
        EObject sourceClassContainer = sourceClass.eContainer();
        if (sourceClassContainer.equals(ass_p.eContainer())) {
          return true;
        }
      }

    } else if (AssociationExt.isBidirectional(ass_p) || AssociationExt.isNondirectional(ass_p)) {
      // check that association container is source & target classes common ancestors
      boolean assoIsInLinkedClassifiersCommonAcestor = AssociationExt.isInCommonAncestorOf(ass_p, AssociationExt.getLinkedClassifiers(ass_p));
      if (assoIsInLinkedClassifiersCommonAcestor) {
        return true;

      }
    }
    return false;
  }

}
