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
package org.polarsys.capella.core.transition.common.handlers.filter;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IDifference;

import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * from ITransitionDiffCompute
 */
public interface IFilteringDifferencesHandler extends IHandler {

  public IStatus processDifferences(IContext context_p, Collection<IDifference> diffSource_p, Collection<IDifference> diffTarget_p);

  public Role getMergeDestination(IContext context_p, IDifference difference_p, Role scope_p);

  public void uncheck(IContext context_p, IDiffModelViewer diff_p);

  public void check(IContext context_p, IDiffModelViewer diff_p);

}
