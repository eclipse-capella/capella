/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.re.project.diffmerge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.diffdata.EElementPresence;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.generic.api.IMatch;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.model.skeleton.CapellaModelSkeleton;

/**
 * Several skeleton related helper methods, such as: Is a given element part of the skeleton,
 * find all skeleton elements, does an element reference a skeleton element. Each instance of
 * this class is bound to a specific skeleton which is calculated from the context argument
 * passed in the constructors.
 */
public class SkeletonUtil {

  private final EObject context;
  private EComparison comparison;

  public SkeletonUtil(EObject context) {
    this.context = context;
  }

  private static Project createSkeleton(EClass root, ExecutionManager manager) {
    return new CapellaModelSkeleton.Builder(manager).setRootType(root).build().getProject();
  }

  private EComparison getComparison() {

    if (comparison == null) {

      ExecutionManager skeletonManager = ExecutionManagerRegistry.getInstance().addNewManager();

      try {

        EObject first = context;
        Project project = createSkeleton(EcoreUtil.getRootContainer(first).eClass(), skeletonManager);

        if (project != null) {
          IEditableModelScope targetScope = new FragmentedModelScope(first.eResource(), true);
          IEditableModelScope referenceScope = new FragmentedModelScope(project.eResource(), true);

          comparison = new EComparisonImpl(targetScope, referenceScope);
          comparison.compute(new SkeletonMatchPolicy(), null, null, new NullProgressMonitor());

        }

      } finally {
        ExecutionManagerRegistry.getInstance().removeManager(skeletonManager);
      }

    }

    return comparison;

  }


  /**
   * Is the given element part of the project skeleton of the context.
   * @param element the element to test
   */
  public boolean isSkeletonElement(EObject element) {
    EComparison c = getComparison();
    for (Iterator<IMatch<EObject>> matchIterator = c.getAllContents(Role.REFERENCE); matchIterator.hasNext();) {
      IMatch<EObject> next = matchIterator.next();
      if (next.get(Role.TARGET) == element) {
        return true;
      }
    }
    return false;
  }

  /**
   * Is the given element referencing a skeleton element of the context.
   */
  public boolean hasReferenceToSkeleton(EObject element) {
    for (EContentsEList.FeatureIterator<?> featureIterator = (EContentsEList.FeatureIterator<?>)element.eCrossReferences().iterator(); featureIterator.hasNext(); ) {

      EObject referencedEObject = (EObject)featureIterator.next();
      EReference reference = (EReference)featureIterator.feature();

      if (!reference.isDerived() && isSkeletonElement(referencedEObject)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Find all template elements. 
   */
  public Collection<EObject> getSkeletonElements() {
    Collection<EObject> skeleton = new ArrayList<EObject>();
    EComparison c = getComparison();
    for (Iterator<IMatch<EObject>> matchIterator = c.getAllContents(Role.REFERENCE); matchIterator.hasNext();) {
      IMatch<EObject> next = matchIterator.next();
      if (next.get(Role.TARGET) != null) {
        skeleton.add(next.get(Role.TARGET));
      }
    }
    return skeleton;
  }

  /**
   * Find all user root elements. A user root element is a child of a template
   * element which is not itself a template element.
   */
  public Collection<EObject> getUserRoots() {

    Collection<EObject> elementsForRec = new ArrayList<EObject>();
    EComparison comparison = getComparison();

    for (IDifference<EObject> diff : comparison.getRemainingDifferences()) {

      if (diff instanceof EElementPresence && ((EElementPresence) diff).getPresenceRole() == Role.TARGET) {
        EElementPresence presence = (EElementPresence) diff;
        EObject element = presence.getElement();
        if (presence.getOwnerMatch() != null && presence.getOwnerMatch().get(Role.REFERENCE) != null) {
          elementsForRec.add(element);
        }
      }
    }

    return elementsForRec;
  }

}
