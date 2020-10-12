/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.diffdata.EComparison;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DefaultMergeHandler implements IMergeHandler {

  protected Collection<ICategorySet> categorySets = new LinkedList<ICategorySet>();

  protected Collection<ICategoryItem> categories = new LinkedList<ICategoryItem>();

  protected final boolean processTargetDifferences;

  public DefaultMergeHandler() {
    this(false);
  }

  public DefaultMergeHandler(boolean processTargetDifferences) {
    this.processTargetDifferences = processTargetDifferences;
  }

  public void addCategorySet(ICategorySet set, IContext context) {
    categorySets.add(set);
  }

  public void addCategory(ICategoryItem filter, IContext context) {
    categories.add(filter);
  }

  public IStatus processDifferences(IContext context, Collection<IDifference<EObject>> diffSource,
      Collection<IDifference<EObject>> diffTarget) {

    Collection<IDifference<EObject>> differences = diffSource;

    if (processTargetDifferences) {
      differences = new ArrayList<IDifference<EObject>>(differences);
      differences.addAll(diffTarget);
    }

    EComparison comparison = (EComparison) context.get(ITransitionConstants.MERGE_COMPARISON);
    mergeDifferences(comparison, filterDifferences(context, differences));

    return Status.OK_STATUS;
  }

  /**
   * Filters the list of detected differences according to the merge handlers category items.
   *
   * @param context
   *          the transposer context
   * @param differences
   *          an unfiltered collection of differences
   * @return the filtered collection of differences
   */
  protected Collection<IDifference<EObject>> filterDifferences(IContext context, Collection<IDifference<EObject>> differences) {
    Collection<IDifference<EObject>> result = new ArrayList<IDifference<EObject>>();
    for (IDifference<EObject> difference : differences) {
      if (!isFiltered(difference)) {
        result.add(difference);
      }
    }
    return result;
  }

  /**
   * Merge the given collection of differences into the target model
   *
   * @param comparison
   *          the compariton
   * @param differences
   *          a possibly empty collection of differences to merge
   */
  protected void mergeDifferences(EComparison comparison, Collection<IDifference<EObject>> differences) {
    comparison.merge(differences, Role.TARGET, true, new NullProgressMonitor());
  }

  public boolean isFiltered(IDifference<EObject> difference) {
    boolean globalFocus = false; // At least one category is in focus mode
    boolean diffFocus = false; // At least one covering category is in focus mode
    for (ICategoryItem category : categories) {
      
      if (category.isActive()) {
        boolean catFocus = category.isInFocusMode();
        globalFocus = globalFocus || catFocus;
        boolean covered = category.covers(difference);
        if (covered) {
          // Covered by active category
          if (!catFocus) {
            // Covered by category in filtering mode
            return true;
          }
          // Else covered by category in focus mode: proceed
          diffFocus = true;
        }
      }
    }
    // Not filtered out by any category
    return globalFocus && !diffFocus; // All categories in focus mode are non-covering

  }

  public IStatus init(IContext context) {
    categories = new LinkedList<ICategoryItem>();

    addCategorySet(new CategorySet(ITransitionConstants.CATEGORY_BUSINESS,
        Messages.DefaultMergeHandler_CategoryBusiness_Name, Messages.DefaultMergeHandler_CategoryBusiness_Description),
        context);
    addCategorySet(new CategorySet(ITransitionConstants.CATEGORY_SEMANTIC,
        Messages.DefaultMergeHandler_CategorySemantic_Name, Messages.DefaultMergeHandler_CategorySemantic_Description),
        context);

    return Status.OK_STATUS;
  }

  public IStatus dispose(IContext context) {
    if (categories != null) {
      categories.clear();
      categories = null;
    }
    return Status.OK_STATUS;
  }

  public Collection<ICategorySet> getCategoriesSet(IContext context) {
    return Collections.unmodifiableCollection(categorySets);
  }

  public Collection<ICategoryItem> getCategories(IContext context) {
    return Collections.unmodifiableCollection(categories);
  }

  @Override
  public ICategoryItem getCategory(IContext context, String id) {
    for (ICategoryItem category : getCategories(context)) {
      String currentId = category.getId();
      if (currentId != null && currentId.equals(id)) {
        return category;
      }
    }
    return null;
  }

}
