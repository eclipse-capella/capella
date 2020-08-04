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
package org.polarsys.capella.core.re.project;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.re.project.diffmerge.SkeletonUtil;
import org.polarsys.capella.core.re.project.handlers.ProjectRecHandler;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.scope.IScopeFilter;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class SkeletonScopeFilter implements IScopeFilter {

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus init(IContext context) {
    if (ProjectRecHandler.isProjectCommand(context)) {
      Collection<?> selection = (Collection<?>) context.get(ITransitionConstants.TRANSITION_SOURCES);
      if (!selection.isEmpty()) {
        SkeletonUtil util = new SkeletonUtil((EObject) selection.iterator().next());
        Collection<EObject> roots = util.getSkeletonElements();
        context.put(SkeletonScopeFilter.class.getSimpleName(), roots);
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus dispose(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValidScopeElement(EObject element, IContext context) {
    Collection<?> skeleton = (Collection<?>) context.get(SkeletonScopeFilter.class.getSimpleName());
    if (skeleton != null && skeleton.contains(element)) {
      return false;
    }
    return true;
  }

}
