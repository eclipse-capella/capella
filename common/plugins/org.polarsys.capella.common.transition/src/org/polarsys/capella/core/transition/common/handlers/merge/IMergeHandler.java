/*******************************************************************************
 * Copyright (c) 2016, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.handlers.merge;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.handlers.IHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public interface IMergeHandler extends IHandler {

  /**
   * Proceed to differences
   */
  IStatus processDifferences(IContext context, Collection<IDifference<EObject>> diffSource, Collection<IDifference<EObject>> diffTarget);

  /**
   * Returns categories
   */
  Collection<ICategoryItem> getCategories(IContext context);

  /**
   * Returns category sets
   */
  Collection<ICategorySet> getCategoriesSet(IContext context);
  
  /**
   * Add a category of differences
   */
  void addCategory(ICategoryItem filter, IContext context);

  /**
   * Add a set of category
   */
  void addCategorySet(ICategorySet set, IContext context);
  
  /**
   * Retrieve the given category according to its identifier
   */
  ICategoryItem getCategory(IContext context, String id);

}
