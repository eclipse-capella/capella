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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.Role;
import org.eclipse.emf.diffmerge.api.diff.IAttributeValuePresence;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.diffmerge.api.diff.IReferenceValuePresence;
import org.polarsys.capella.core.transition.common.capellaHelpers.HashMapSet;
import org.polarsys.capella.core.transition.common.handlers.filter.AbstractFilterItem.FilterAction;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelType.DiffScope;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewer;
import org.polarsys.capella.core.transition.common.handlers.log.IDiffModelViewerFactory;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 *
 */
public class DefaultFilteringDifferencesHandler implements IFilteringDifferencesHandler, CompoundFilteringItems {

  private static final String VIEWS_TO_DISPLAY = "getViewsToDisplay"; //$NON-NLS-1$

  private static final String VIEWS_TO_MERGE = "getViewsToMerge"; //$NON-NLS-1$

  private static final String REQUIRING_DIFFERENCES = "getRequiringDifferences"; //$NON-NLS-1$

  protected Collection<IFilterItem> filterItems;

  public Collection<IDiffModelViewer> getViewsToDisplay(IContext context) {
    Collection handler = (Collection) context.get(VIEWS_TO_DISPLAY);
    if (handler == null) {
      handler = new ArrayList<IDiffModelViewer>();
      context.put(VIEWS_TO_DISPLAY, handler);
    }
    return handler;
  }

  public HashMap<IDifference, IDiffModelViewer> getViewsToMerge(IContext context) {
    HashMap<IDifference, IDiffModelViewer> handler = (HashMap) context.get(VIEWS_TO_MERGE);
    if (handler == null) {
      handler = new HashMap<IDifference, IDiffModelViewer>();
      context.put(VIEWS_TO_MERGE, handler);
    }
    return handler;
  }

  public HashMapSet<IDifference, IDifference> getRequiringDifferences(IContext context) {
    HashMapSet<IDifference, IDifference> handler = (HashMapSet) context.get(REQUIRING_DIFFERENCES);
    if (handler == null) {
      handler = new HashMapSet<IDifference, IDifference>();
      context.put(REQUIRING_DIFFERENCES, handler);
    }
    return handler;
  }

  /**
   * For each differences, initialize a DiffViewer
   * @param context_p
   * @param diffs_p
   * @param scope_p
   */
  protected void initialize(IContext context_p, Collection<IDifference> diffs_p, Role scope_p) {

    for (IDifference diff : diffs_p) {
      if (isMergeable(diff, scope_p, context_p)) {
        DiffScope scope = DiffScope.Source;
        if (scope_p == Role.TARGET) {
          scope = DiffScope.Target;
        }

        FilterAction role = getDefaultAction(diff, scope_p, context_p);

        IDiffModelViewer view = IDiffModelViewerFactory.eINSTANCE.createDiffModelViewer(diff, scope, role, context_p, isReadOnly(diff, scope_p, context_p));
        if (isDisplayable(diff, scope_p, context_p)) {
          getViewsToDisplay(context_p).add(view);
        }
        getViewsToMerge(context_p).put(diff, view);
      }
    }

  }

  /**
   * @param diff_p
   * @return
   */
  protected boolean isReadOnly(IDifference diff_p, Role role_p, IContext context_p) {

    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        if (item.isReadOnly(diff_p, role_p, context_p)) {
          return true;
        }
      }
    }
    return false;

  }

  /**
   * @param context_p
   */
  private void compute(IContext context_p) {
    HashMapSet<IDifference, IDifference> set = getRequiringDifferences(context_p);
    for (IDifference difference : getViewsToMerge(context_p).keySet()) {
      if (difference instanceof IMergeableDifference) {
        IMergeableDifference mDiff = (IMergeableDifference) difference;
        for (IMergeableDifference diff : mDiff.getDirectRequiresDependencies(Role.TARGET)) {
          set.put(diff, mDiff);
        }
      }
    }
  }

  /**
   * Proceed to uncheck all differences which requires a already uncheck difference
   * @param context_p
   * @param diffs_p
   * @param scope_p
   */
  protected void finitialize(IContext context_p, Collection<IDifference> diffs_p, Role scope_p) {

    for (IDifference diff : diffs_p) {
      IDiffModelViewer view = getViewsToMerge(context_p).get(diff);
      if (view != null) {
        if (view.getActionDiff() == FilterAction.NO_ACTION) {
          uncheck(context_p, view, true, view, true);
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  public void addFilterItem(IFilterItem filter_p, IContext context_p) {
    if (filterItems == null) {
      filterItems = new LinkedList<IFilterItem>();
    }
    filterItems.add(filter_p);
  }

  public IStatus processDifferences(IContext context_p, Collection<IDifference> diffSource_p, Collection<IDifference> diffTarget_p) {

    initialize(context_p, diffSource_p, Role.REFERENCE);
    initialize(context_p, diffTarget_p, Role.TARGET);

    compute(context_p);
    finitialize(context_p, diffSource_p, Role.REFERENCE);
    finitialize(context_p, diffTarget_p, Role.TARGET);

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context_p) {
    if (filterItems != null) {
      filterItems.clear();
      filterItems = null;
    }
    if (context_p.exists(VIEWS_TO_MERGE)) {
      ((HashMap) context_p.get(VIEWS_TO_MERGE)).clear();
    }
    if (context_p.exists(REQUIRING_DIFFERENCES)) {
      ((HashMapSet) context_p.get(REQUIRING_DIFFERENCES)).clear();
    }
    if (context_p.exists(VIEWS_TO_DISPLAY)) {
      ((Collection) context_p.get(VIEWS_TO_DISPLAY)).clear();
    }

    return Status.OK_STATUS;
  }

  public Role getMergeDestination(IContext context_p, IDifference difference_p, Role scope_p) {
    if (getViewsToMerge(context_p).containsKey(difference_p)) {
      FilterAction action = getViewsToMerge(context_p).get(difference_p).getActionDiff();

      if (action == FilterAction.NO_ACTION) {
        return null;
      }
      if (action == FilterAction.REFERENCE) {
        return Role.REFERENCE;
      }
      if (action == FilterAction.TARGET) {
        return Role.TARGET;
      }
    }

    return null;
  }

  /**
   * Returns whether the difference from the given scope should be filtered (not merged, not visible)
   * @param diff_p
   * @param scope_p
   * @return
   */
  public boolean isMergeable(IDifference diff_p, Role role_p, IContext context_p) {
    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        if (!item.isMergeable(diff_p, role_p, context_p)) {
          return false;

        } else if (diff_p instanceof IAttributeValuePresence) {
          IAttributeValuePresence aDiff = (IAttributeValuePresence) diff_p;
          if (!item.isMergeable(aDiff.getFeature(), context_p)) {
            return false;
          }

        } else if (diff_p instanceof IReferenceValuePresence) {
          IReferenceValuePresence aDiff = (IReferenceValuePresence) diff_p;
          if (!item.isMergeable(aDiff.getFeature(), context_p)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * @param diff_p
   * @param scope_p
   * @return
   */
  public boolean isDisplayable(IDifference diff_p, Role scope_p, IContext context_p) {
    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        if (!item.isDisplayable(diff_p, scope_p, context_p)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns whether the difference from the given scope should be filtered (not merged, not visible)
   * @param diff_p
   * @param scope_p
   * @return
   */
  public FilterAction getDefaultAction(IDifference diff_p, Role role_p, IContext context_p) {
    FilterAction roleResult = null;

    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        FilterAction role = item.getDestinationRole(diff_p, role_p, context_p);
        if (role != null) {
          if (role == FilterAction.NO_ACTION) {
            if ((roleResult == null) || (roleResult != FilterAction.NO_ACTION)) {
              roleResult = role;
            }
          } else {
            if (role != FilterAction.NO_ACTION) {
              if ((roleResult == null) || (roleResult != FilterAction.NO_ACTION)) {
                roleResult = role;
              }
            }
          }
        }
      }
    }

    return roleResult;
  }

  /**
   * {@inheritDoc}
   */
  public Collection<IFilterItem> getFilterItems(IContext context_p) {
    return Collections.unmodifiableCollection(filterItems);
  }

  /**
   * {@inheritDoc}
   */
  public void uncheck(IContext context_p, IDiffModelViewer diff_p) {
    uncheck(context_p, diff_p, false, diff_p, false);
  }

  public void uncheck(IContext context_p, IDiffModelViewer diff_p, boolean force_p, IDiffModelViewer source_p, boolean isInitialization_p) {
    diff_p.setRoot(source_p.getRoot());
    FilterAction diffaction = FilterAction.NO_ACTION;
    if (diff_p.getDefaultActionDiff() == null) {
      diffaction = null;
    }
    if (!force_p && (diff_p.getActionDiff() == diffaction)) {
      return;
    }

    Collection<IDiffModelViewer> map = new HashSet<IDiffModelViewer>();

    IMergeableDifference sDiff = (IMergeableDifference) diff_p.getRelatedDiff();
    diff_p.setActionDiff(diffaction);

    if (isInitialization_p) {
      diff_p.setDefaultActionDiff(diffaction);
    }
    //We disable all differences which require the given difference
    //Disable/Enable also all differences with 'explicit dependencies'

    HashMapSet<IDifference, IDifference> set = getRequiringDifferences(context_p);
    if (set != null) {
      for (IDifference difference : set.get(sDiff)) {
        IDiffModelViewer view = getViewsToMerge(context_p).get(difference);
        if (view != null) {
          map.add(view);
        }
      }
    }

    for (IDiffModelViewer diffa : map) {
      if (diffa.getActionDiff() != diffaction) {
        uncheck(context_p, diffa, force_p, source_p, isInitialization_p);
      }
    }

  }

  public void check(IContext context_p, IDiffModelViewer diff_p) {
    check(context_p, diff_p, false, diff_p, false);
  }

  /**
   * {@inheritDoc}
   */
  public void check(IContext context_p, IDiffModelViewer diff_p, boolean force_p, IDiffModelViewer source_p, boolean isInitialization_p) {
    diff_p.setRoot(source_p.getRoot());
    FilterAction diffaction = FilterAction.NO_ACTION;
    diffaction = FilterAction.TARGET;
    if (!force_p && (diff_p.getActionDiff() == diffaction)) {
      return;
    }

    Collection<IDiffModelViewer> map = new HashSet<IDiffModelViewer>();

    IMergeableDifference sDiff = (IMergeableDifference) diff_p.getRelatedDiff();
    diff_p.setActionDiff(diffaction);

    if (isInitialization_p) {
      diff_p.setDefaultActionDiff(diffaction);
    }
    //We enable all differences which require the given difference
    //and enable all differences required by the given difference

    //Disable/Enable also all differences with 'explicit dependencies'
    HashMapSet<IDifference, IDifference> set = getRequiringDifferences(context_p);
    if (set != null) {
      for (IDifference difference : set.get(sDiff)) {
        IDiffModelViewer view = getViewsToMerge(context_p).get(difference);
        if (view != null) {
          map.add(view);
        }
      }
    }
    for (IMergeableDifference difference : sDiff.getDirectRequiresDependencies(Role.TARGET)) {
      IDiffModelViewer view = getViewsToMerge(context_p).get(difference);
      if (view != null) {
        map.add(view);
      }
    }

    for (IDiffModelViewer diffa : map) {
      if (diffa.getActionDiff() != diffaction) {
        check(context_p, diffa, force_p, source_p, isInitialization_p);
      }
    }
  }

}
