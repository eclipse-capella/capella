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
package org.polarsys.capella.core.model.handler.helpers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentsEList.FeatureIterator;
import org.eclipse.emf.ecore.util.ECrossReferenceAdapter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadOnlyCommand;
import org.polarsys.capella.common.ef.command.ICommand;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.platform.sirius.ted.SemanticEditingDomainFactory.SemanticEditingDomain;

/**
 * Cross referencer helper.
 */
public class CrossReferencerHelper {

  private static boolean enabledResolution = true;

  /**
   * Collect all elements that reference (containment relationships are not taken into account) given one.<br>
   * In fact that collects Non Navigable Inverse References for given element.<br>
   * Derived features are ignored too.
   * @param referencedElement
   * @return a not <code>null</code> collection.
   */
  public static List<EObject> getReferencingElements(final EObject referencedElement) {
    // Get the execution manager.
    final List<EObject> referencingElements = new ArrayList<EObject>(0);
    // Create a read only command to make sure computation is performed in a transactional way.
    ICommand command = new AbstractReadOnlyCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
    public void run() {
        // Get the cross referencer.
        ECrossReferenceAdapter crossReferencer = ((SemanticEditingDomain) TransactionHelper.getEditingDomain(referencedElement)).getCrossReferencer();
        referencingElements.addAll(EcoreUtil2.getReferencingElements(referencedElement, crossReferencer));
      }
    };
    // Run the command.
    ExecutionManager mgr = TransactionHelper.getExecutionManager(referencedElement);
    if (mgr != null) {
      mgr.execute(command);
    }
    return referencingElements;
  }

  /**
   * Allows to enable or disable crossReferencer resolution
   * @param editingDomain
   * @param enablement
   * 
   */
  public static void enableResolveProxy(TransactionalEditingDomain editingDomain) {
    if (editingDomain instanceof SemanticEditingDomain) {
      SemanticEditingDomain domain = (SemanticEditingDomain)editingDomain;
      domain.getCrossReferencer().enableResolveProxy();
    }
  }
  
  public static void disableResolveProxy(TransactionalEditingDomain editingDomain) {
    if (editingDomain instanceof SemanticEditingDomain) {
      SemanticEditingDomain domain = (SemanticEditingDomain)editingDomain;
      domain.getCrossReferencer().disableResolveProxy();
    }
  }
  
  /**
   * Returns whether crossReferencer is allowed to perform resolution
   *
   */
  public static boolean isResolveProxyEnabled(EditingDomain editingDomain) {
    if (editingDomain instanceof SemanticEditingDomain) {
      return ((SemanticEditingDomain)editingDomain).getCrossReferencer().isResolveProxyEnabled();
    }
    return true;
  }
  
  /**
   * <p>
   * Use {@link#enableResolveProxy(TransactionalEditingDomain)} or {@link SemanticCrossReferencer#enableResolveProxy()}
   * </p>
   * Allows to enable or disable crossReferencer resolution
   * @param enabled
   * 
   */
  @Deprecated
  public static void enableResolution(boolean enabled) {
    enabledResolution = enabled;
  }

  /**
   * <p>
   * Use {@link #isResolveProxyEnabled(TransactionalEditingDomain)} or {@link SemanticCrossReferencer#isResolveProxyEnabled()}
   * </p>
   * Returns whether crossReferencer is allowed to perform resolution
   * @return
   */
  @Deprecated
  public static boolean resolutionEnabled() {
    return enabledResolution;
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
