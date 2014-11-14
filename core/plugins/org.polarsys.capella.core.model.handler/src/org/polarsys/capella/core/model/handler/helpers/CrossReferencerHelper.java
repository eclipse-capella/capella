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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentsEList.FeatureIterator;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * Cross referencer helper.
 */
public class CrossReferencerHelper {

  private static boolean enabled = true;

  /**
   * Collect all elements that reference (containment relationships are not taken into account) given one.<br>
   * In fact that collects Non Navigable Inverse References for given element.<br>
   * Derived features are ignored too.
   * @param referencedElement_p
   * @return a not <code>null</code> collection.
   */
  public static List<EObject> getReferencingElements(final EObject referencedElement_p) {
    // Get the execution manager.
    final List<EObject> referencingElements = new ArrayList<EObject>(0);
    // Create a read only command to make sure computation is performed in a transactional way.
    ICommand command = new AbstractReadOnlyCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      public void run() {
        // Get the cross referencer.
        ECrossReferenceAdapter crossReferencer = ((SemanticEditingDomain) TransactionHelper.getEditingDomain(referencedElement_p)).getCrossReferencer();
        referencingElements.addAll(EcoreUtil2.getReferencingElements(referencedElement_p, crossReferencer));
      }
    };
    // Run the command.
    TransactionHelper.getExecutionManager(referencedElement_p).execute(command);
    return referencingElements;
  }

  /**
   * Allows to enable or disable crossReferencer resolution
   * @param enabled_p
   */
  public static void enableResolution(boolean enabled_p) {
    enabled = enabled_p;
  }

  /**
   * Returns whether crossReferencer is allowed to perform resolution
   * @return
   */
  public static boolean resolutionEnabled() {
    return enabled;
  }

  /**
   * Self containment is the property of an EObject which hierarchy does not contain any reference outside its hierarchy. allowsReferencingElements parameter
   * establish if we allow to receive references from external EObjects.
   * @param eObject
   * @param allowsReferencingElements
   * @return true if the eObject is self contained
   */
  public static boolean isSelfContained(EObject eObject, boolean allowsReferencingElements) {
    // Get all children and add itself
    HashSet<EObject> hierarchyMembers = new HashSet<EObject>();
    hierarchyMembers.add(eObject);
    Iterator<EObject> allContents = eObject.eAllContents();
    while (allContents.hasNext()) {
      hierarchyMembers.add(allContents.next());
    }
    // Return false if there are references or referencing elements outside the hierarchy
    for (EObject member : hierarchyMembers) {
      // References [_]-->
      for (FeatureIterator<EObject> featureIterator = (FeatureIterator<EObject>) member.eCrossReferences().iterator(); featureIterator.hasNext();) {
        EObject referencedElement = featureIterator.next();
        EReference eReference = (EReference) featureIterator.feature();
        // Check valid reference if it is not derived
        if (!eReference.isDerived()) {
          if (!hierarchyMembers.contains(referencedElement)) {
            return false;
          }
        }
      }

      // The parameter for allowing referencing elements or not
      if (allowsReferencingElements) {
        // Referencing -->[_]
        List<EObject> referencingElements = getReferencingElements(member);
        if (!hierarchyMembers.containsAll(referencingElements)) {
          return false;
        }
      }
    }
    return true;
  }
}
