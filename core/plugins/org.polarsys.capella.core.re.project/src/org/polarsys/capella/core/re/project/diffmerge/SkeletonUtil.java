/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.re.project.diffmerge;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.diffmerge.api.IComparison;
import org.eclipse.emf.diffmerge.api.IMatch;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IElementPresence;
import org.eclipse.emf.diffmerge.api.scopes.IEditableModelScope;
import org.eclipse.emf.diffmerge.diffdata.impl.EComparisonImpl;
import org.eclipse.emf.diffmerge.impl.scopes.FragmentedModelScope;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentsEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.libraries.ui.wizard.newLibrary.CreateCapellaLibraryCommand;
import org.polarsys.capella.core.model.handler.helpers.CapellaProjectHelper.ProjectApproach;
import org.polarsys.capella.core.model.skeleton.EngineeringDomain;
import org.polarsys.capella.core.model.skeleton.impl.SkeletonServicesImpl;
import org.polarsys.capella.core.model.skeleton.impl.cmd.CreateCapellaProjectCmd;

/**
 * Several skeleton related helper methods, such as: Is a given element part of the skeleton,
 * find all skeleton elements, does an element reference a skeleton element. Each instance of
 * this class is bound to a specific skeleton which is calculated from the context argument
 * passed in the constructors.
 */
public class SkeletonUtil {

  private final EObject context;
  private IComparison comparison;

  public SkeletonUtil(EObject context) {
    this.context = context;
  }

  private static Project createSkeleton(EClass root, ExecutionManager manager) {

    Project result = null;
    ResourceSet set = manager.getEditingDomain().getResourceSet();
    Resource skeletonResource = set.createResource(URI.createURI("skeleton.melodymodeller")); //$NON-NLS-1$

    CreateCapellaProjectCmd cmd = null;
    if (root == CapellamodellerPackage.Literals.PROJECT) {
      cmd = new CreateCapellaProjectCmd(skeletonResource, "skeleton", ProjectApproach.SingletonComponents); //$NON-NLS-1$
    } else if (root == CapellamodellerPackage.Literals.LIBRARY) {
      cmd = new CreateCapellaLibraryCommand(skeletonResource, "skeleton", ProjectApproach.SingletonComponents); //$NON-NLS-1$
    }

    if (cmd != null) {
      manager.execute(cmd);
      result = cmd.getProject();
      new SkeletonServicesImpl().doSystemEngineering(result, result.getName(), EngineeringDomain.System, true);
    }

    return result;
  }

  private IComparison getComparison() {

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
    IComparison c = getComparison();
    for (Iterator<IMatch> matchIterator = c.getAllContents(Role.REFERENCE); matchIterator.hasNext();) {
      IMatch next = matchIterator.next();
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
   * Find all user root elements. A user root element is a child of a template
   * element which is not itself a template element.
   */
  public Collection<EObject> getUserRoots() {

    Collection<EObject> elementsForRec = new ArrayList<EObject>();
    IComparison comparison = getComparison();

    for (IDifference diff : comparison.getRemainingDifferences()) {

      if (diff instanceof IElementPresence && ((IElementPresence) diff).getPresenceRole() == Role.TARGET) {

        IElementPresence presence = (IElementPresence) diff;
        EObject element = presence.getElement();
        if (presence.getOwnerMatch() != null && presence.getOwnerMatch().get(Role.REFERENCE) != null) {
          elementsForRec.add(element);
        }
      }
    }

    return elementsForRec;
  }

}
