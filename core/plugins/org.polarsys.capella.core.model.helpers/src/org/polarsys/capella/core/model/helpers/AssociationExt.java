/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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

  public static Collection<Classifier> getLinkedClassifiers(Association association) {
    Collection<Classifier> returnedClassifiers = new HashSet<>();
    for (Property aProperty : getProperties(association)) {
      if (aProperty.getType() instanceof Classifier) {
        returnedClassifiers.add((Classifier) aProperty.getType());
      }
    }
    return returnedClassifiers;
  }

  public static Set<Classifier> getOwnedMembersClassifiers(Association association) {
    Set<Classifier> returnedClassifiers = new HashSet<>();
    for (Property aProperty : getOwnedMembersProperties(association)) {
      if (aProperty.getType() instanceof Classifier) {
        returnedClassifiers.add((Classifier) aProperty.getType());
      }
    }
    return returnedClassifiers;
  }

  public static Set<Classifier> getNavigableMembersClassifiers(Association association) {
    Set<Classifier> returnedClassifiers = new HashSet<>();
    for (Property aProperty : getNavigableMembersProperties(association)) {
      if (aProperty.getType() instanceof Classifier) {
        returnedClassifiers.add((Classifier) aProperty.getType());
      }
    }
    return returnedClassifiers;
  }

  public static Class getSourceClass(Association association) {
    Set<Classifier> ownedMembersClassifiers = getOwnedMembersClassifiers(association);
    if (!ownedMembersClassifiers.isEmpty()) {
      Classifier classifier = ownedMembersClassifiers.iterator().next();
      if (classifier instanceof Class) {
        return (Class) classifier;
      }

    }
    return null;

  }

  public static Collection<Property> getProperties(Association association) {
    Collection<Property> returnedProperties = new HashSet<>();

    returnedProperties.addAll(association.getNavigableMembers());
    returnedProperties.addAll(association.getOwnedMembers());

    return returnedProperties;
  }

  public static Collection<Property> getOwnedMembersProperties(Association association) {
    Collection<Property> returnedProperties = new HashSet<>();

    returnedProperties.addAll(association.getOwnedMembers());

    return returnedProperties;
  }

  public static Collection<Property> getNavigableMembersProperties(Association association) {
    Collection<Property> returnedProperties = new HashSet<>();

    returnedProperties.addAll(association.getNavigableMembers());

    return returnedProperties;
  }

  /**
   * Moves the association to its correct container depending on its direction:<br/>
   * <li>Unidirectional associations are moved to the source classes data package</li> <br/>
   * <li>Nondirectional & bidirectional associations are moved to source & target classes common ancestor package</li>
   * @param assoc
   */
  public static void moveToCorrectContainer(Association assoc) {

    if (AssociationExt.isUnidirectional(assoc)) {

      Set<Classifier> ownedMembers = getOwnedMembersClassifiers(assoc);

      // Move the association to the source classifier container
      Classifier sourceClassifier = null;
      if (ownedMembers.iterator().hasNext()) {
        sourceClassifier = ownedMembers.iterator().next();
      }
      if (null != sourceClassifier) {
        EObject eContainer = sourceClassifier.eContainer();
        if (eContainer instanceof DataPkg) {
          moveToPackage(Arrays.asList(assoc), (DataPkg) eContainer);
        }
      }
    }
    if (isBidirectional(assoc) || isNondirectional(assoc)) {
      // move association to source & target classes common ancestor package
      AssociationExt.moveToBestContainer(assoc);

    }
  }

  public static void moveToBestContainer(Association association) {
    EObject bestAncestor = getLinkedClassifiersCommonAncestor(association);
    if (bestAncestor instanceof AssociationPkg && !association.eContainer().equals(bestAncestor)) {
      ((AssociationPkg) bestAncestor).getOwnedAssociations().add(association);
    }
  }

  /**
   * returns the common ancestor of association linked classifiers.
   * @see {@link AssociationExt#getLinkedClassifiersCommonAncestorOtherwiseSelfContainer(Association)}
   * @param association
   * @return
   */
  public static EObject getLinkedClassifiersCommonAncestor(Association association) {
    return EcoreUtil2.getCommonAncestor(getLinkedClassifiers(association));
  }

  /**
   * returns the common ancestor of association linked classifiers or the container of the unique linked classifier (e.g. in case of a cyclic association on the
   * same class)
   * @see {@link AssociationExt#getLinkedClassifiersCommonAncestor(Association)}
   * @param association
   * @return
   */
  public static EObject getLinkedClassifiersCommonAncestorOtherwiseSelfContainer(
      Association association) {
    Collection<Classifier> linkedClassifiers = getLinkedClassifiers(association);
    EObject commonAncestor = EcoreUtil2.getCommonAncestor(linkedClassifiers);
    if (linkedClassifiers.contains(commonAncestor) && (linkedClassifiers.size() == 1)) {
      return null != commonAncestor ? commonAncestor.eContainer() : null;
    }
    return commonAncestor;
  }

  /**
   * @param asso
   * @param linkedClassifiers
   */
  public static boolean isInCommonAncestorOf(Association asso, Collection<Classifier> linkedClassifiers) {
    EObject commonAncestor = EcoreUtil2.getCommonAncestor(linkedClassifiers);
    if ((null == commonAncestor) || (null == asso.eContainer())) {
      return false;
    } else if ((null != linkedClassifiers) && (linkedClassifiers.size() == 1)) {

      Iterator<Classifier> iterator = linkedClassifiers.iterator();
      if (iterator.hasNext()) {
        Classifier next = iterator.next();
        if (next.eContainer() instanceof DataPkg) {
          commonAncestor = next.eContainer();
        }
      }
    }
    return asso.eContainer().equals(commonAncestor);

  }

  public static void moveToPackage(List<Association> assocs, DataPkg dataPkg) {
    for (Association assoc : assocs) {
      dataPkg.getOwnedAssociations().add(assoc);
    }

  }

  public static boolean isComposition(Association ass) {
    if (null != ass) {
      for (Property property : getProperties(ass)) {
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
   * @param ass
   * @return
   */
  public static boolean isOfKindAssocation(Association ass) {
    if (null != ass) {
      for (Property property : getProperties(ass)) {
        AggregationKind aggregationKind = property.getAggregationKind();
        if ((aggregationKind != null) && (aggregationKind == AggregationKind.ASSOCIATION)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * @param association
   * @return
   */
  public static boolean isUnidirectional(EObject association) {
    if (association instanceof Association) {
      Association assoc = (Association) association;
      return ((null != assoc.getNavigableMembers()) && (null != assoc.getOwnedMembers()) && (assoc.getNavigableMembers().size() == 1) && (assoc
          .getOwnedMembers().size() == 1));
    }
    return false;
  }

  /**
   * @param association
   * @return
   */
  public static boolean isBidirectional(EObject association) {
    if (association instanceof Association) {
      Association assoc = (Association) association;
      return ((null != assoc.getNavigableMembers()) && (assoc.getNavigableMembers().size() == 2) && isNullOrEmpty(assoc.getOwnedMembers()));
    }
    return false;
  }

  /**
   * @param association
   * @return
   */
  public static boolean isNondirectional(EObject association) {
    if (association instanceof Association) {
      Association assoc = (Association) association;
      return ((null != assoc.getOwnedMembers()) && (assoc.getOwnedMembers().size() == 2) && isNullOrEmpty(assoc.getNavigableMembers()));
    }
    return false;
  }

  /**
   * @param ownedMembers
   * @return
   */
  private static boolean isNullOrEmpty(EList<Property> ownedMembers) {
    return (null == ownedMembers) || ownedMembers.isEmpty();
  }

  public static boolean isInCorrectLocation(Association ass) {
    if (AssociationExt.isUnidirectional(ass)) {
      // check that association container is the association source class container
      Class sourceClass = AssociationExt.getSourceClass(ass);
      if (null != sourceClass) {
        EObject sourceClassContainer = sourceClass.eContainer();
        if (sourceClassContainer.equals(ass.eContainer())) {
          return true;
        }
      }

    } else if (AssociationExt.isBidirectional(ass) || AssociationExt.isNondirectional(ass)) {
      // check that association container is source & target classes common ancestors
      boolean assoIsInLinkedClassifiersCommonAcestor = AssociationExt.isInCommonAncestorOf(ass, AssociationExt.getLinkedClassifiers(ass));
      if (assoIsInLinkedClassifiersCommonAcestor) {
        return true;

      }
    }
    return false;
  }

}
