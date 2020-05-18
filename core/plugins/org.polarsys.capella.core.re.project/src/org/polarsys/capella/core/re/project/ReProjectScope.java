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
package org.polarsys.capella.core.re.project;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.polarsys.capella.common.re.RecCatalog;
import org.polarsys.capella.core.re.project.diffmerge.SkeletonUtil;

/**
 * This helper calculates the scope for REC that encompass entire libraries.
 *
 * The scope is calculated from any element of the library that the REC should target:
 * <ul>
 *  <li>Initially all {@link SkeletonUtil#getUserRoots() user root objects} of the library are calculated.</li>
 *  <li>RecCatalogs are then removed from these objects</li>
 *  <li>Then calculates the combined content tree of these elements and purges
 *   all subtrees with a root that has an external cross reference. There are three possible cases where
 *   such external crossreferences may exist</li>
 *   <ol>
 *     <li> A reference to an element in a REC Catalog in the library, since REC catalogs are not considered</li>
 *     <li>A reference to a template element of the library. E.g. a CapabilitySystemInvolvment</li>
 *     <li>A reference to an element in another library.</li>
 *     </ol>
 *   </ul>
 */
public class ReProjectScope {

  public static Collection<EObject> getScope(EObject context, IProgressMonitor progressMonitor) throws InterruptedException {

    SubMonitor monitor = SubMonitor.convert(progressMonitor, Messages.ReProjectScope_SearchLibraryElementsTask, 30);
    SkeletonUtil util = new SkeletonUtil(context);

    Collection<EObject> roots = util.getUserRoots();
    roots = roots.stream()
          .filter(e -> !(e instanceof RecCatalog))
          .collect(Collectors.toList());

    monitor.worked(10);
    if (monitor.isCanceled()) {
      throw new InterruptedException();
    }

    Map<EObject, Collection<EStructuralFeature.Setting>> externalRefs = EcoreUtil.ExternalCrossReferencer.find(roots);
    monitor.worked(10);
    if (monitor.isCanceled()) {
      throw new InterruptedException();
    }

    Collection<EObject> scope = new ArrayList<EObject>();

    tree:
    for (TreeIterator<EObject> it = EcoreUtil.getAllContents(roots); it.hasNext();) {

      EObject next = it.next();

      for (Collection<EStructuralFeature.Setting> settings : externalRefs.values()) {

        for (EStructuralFeature.Setting s : settings) {

          if (!s.getEStructuralFeature().isDerived() && s.getEObject() == next) {

            it.prune();
            continue tree;
          }

        }

      }

      scope.add(next);

    }
    monitor.worked(10);
    if (monitor.isCanceled()) {
      throw new InterruptedException();
    }

    return scope;

  }

}
