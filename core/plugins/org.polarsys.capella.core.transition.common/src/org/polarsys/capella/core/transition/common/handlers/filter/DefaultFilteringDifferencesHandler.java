/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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
   * @param context
   * @param diffs
   * @param role1
   */
  protected void initialize(IContext context, Collection<IDifference> diffs, Role role1) {

    for (IDifference diff : diffs) {
      if (isMergeable(diff, role1, context)) {
        DiffScope scope = DiffScope.Source;
        if (role1 == Role.TARGET) {
          scope = DiffScope.Target;
        }

        FilterAction role = getDefaultAction(diff, role1, context);

        IDiffModelViewer view = IDiffModelViewerFactory.eINSTANCE.createDiffModelViewer(diff, scope, role, context, isReadOnly(diff, role1, context));
        if (isDisplayable(diff, role1, context)) {
          getViewsToDisplay(context).add(view);
        }
        getViewsToMerge(context).put(diff, view);
      }
    }

  }

  /**
   * @param diff
   * @return
   */
  protected boolean isReadOnly(IDifference diff, Role role, IContext context) {

    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        if (item.isReadOnly(diff, role, context)) {
          return true;
        }
      }
    }
    return false;

  }

  /**
   * @param context
   */
  private void compute(IContext context) {
    HashMapSet<IDifference, IDifference> set = getRequiringDifferences(context);
    for (IDifference difference : getViewsToMerge(context).keySet()) {
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
   * @param context
   * @param diffs
   * @param scope
   */
  protected void finitialize(IContext context, Collection<IDifference> diffs, Role scope) {

    for (IDifference diff : diffs) {
      IDiffModelViewer view = getViewsToMerge(context).get(diff);
      if (view != null) {
        if (view.getActionDiff() == FilterAction.NO_ACTION) {
          uncheck(context, view, true, view, true);
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  public void addFilterItem(IFilterItem filter, IContext context) {
    if (filterItems == null) {
      filterItems = new LinkedList<IFilterItem>();
    }
    filterItems.add(filter);
  }

  public IStatus processDifferences(IContext context, Collection<IDifference> diffSource, Collection<IDifference> diffTarget) {

    initialize(context, diffSource, Role.REFERENCE);
    initialize(context, diffTarget, Role.TARGET);

    compute(context);
    finitialize(context, diffSource, Role.REFERENCE);
    finitialize(context, diffTarget, Role.TARGET);

    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus init(IContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public IStatus dispose(IContext context) {
    if (filterItems != null) {
      filterItems.clear();
      filterItems = null;
    }
    if (context.exists(VIEWS_TO_MERGE)) {
      ((HashMap) context.get(VIEWS_TO_MERGE)).clear();
    }
    if (context.exists(REQUIRING_DIFFERENCES)) {
      ((HashMapSet) context.get(REQUIRING_DIFFERENCES)).clear();
    }
    if (context.exists(VIEWS_TO_DISPLAY)) {
      ((Collection) context.get(VIEWS_TO_DISPLAY)).clear();
    }

    return Status.OK_STATUS;
  }

  public Role getMergeDestination(IContext context, IDifference difference, Role scope) {
    if (getViewsToMerge(context).containsKey(difference)) {
      FilterAction action = getViewsToMerge(context).get(difference).getActionDiff();

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
   * @param diff
   * @param scope_p
   * @return
   */
  public boolean isMergeable(IDifference diff, Role role, IContext context) {
    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        if (!item.isMergeable(diff, role, context)) {
          return false;

        } else if (diff instanceof IAttributeValuePresence) {
          IAttributeValuePresence aDiff = (IAttributeValuePresence) diff;
          if (!item.isMergeable(aDiff.getFeature(), context)) {
            return false;
          }

        } else if (diff instanceof IReferenceValuePresence) {
          IReferenceValuePresence aDiff = (IReferenceValuePresence) diff;
          if (!item.isMergeable(aDiff.getFeature(), context)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * @param diff
   * @param scope
   * @return
   */
  public boolean isDisplayable(IDifference diff, Role scope, IContext context) {
    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        if (!item.isDisplayable(diff, scope, context)) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * Returns whether the difference from the given scope should be filtered (not merged, not visible)
   * @param diff
   * @param scope_p
   * @return
   */
  public FilterAction getDefaultAction(IDifference diff, Role role1, IContext context) {
    FilterAction roleResult = null;

    if (filterItems != null) {
      for (IFilterItem item : filterItems) {
        FilterAction role = item.getDestinationRole(diff, role1, context);
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
  public Collection<IFilterItem> getFilterItems(IContext context) {
    return Collections.unmodifiableCollection(filterItems);
  }

  /**
   * {@inheritDoc}
   */
  public void uncheck(IContext context, IDiffModelViewer diff) {
    uncheck(context, diff, false, diff, false);
  }

  public void uncheck(IContext context, IDiffModelViewer diff, boolean force, IDiffModelViewer source, boolean isInitialization) {
    diff.setRoot(source.getRoot());
    FilterAction diffaction = FilterAction.NO_ACTION;
    if (diff.getDefaultActionDiff() == null) {
      diffaction = null;
    }
    if (!force && (diff.getActionDiff() == diffaction)) {
      return;
    }

    Collection<IDiffModelViewer> map = new HashSet<IDiffModelViewer>();

    IMergeableDifference sDiff = (IMergeableDifference) diff.getRelatedDiff();
    diff.setActionDiff(diffaction);

    if (isInitialization) {
      diff.setDefaultActionDiff(diffaction);
    }
    //We disable all differences which require the given difference
    //Disable/Enable also all differences with 'explicit dependencies'

    HashMapSet<IDifference, IDifference> set = getRequiringDifferences(context);
    if (set != null) {
      for (IDifference difference : set.get(sDiff)) {
        IDiffModelViewer view = getViewsToMerge(context).get(difference);
        if (view != null) {
          map.add(view);
        }
      }
    }

    for (IDiffModelViewer diffa : map) {
      if (diffa.getActionDiff() != diffaction) {
        uncheck(context, diffa, force, source, isInitialization);
      }
    }

  }

  public void check(IContext context, IDiffModelViewer diff) {
    check(context, diff, false, diff, false);
  }

  /**
   * {@inheritDoc}
   */
  public void check(IContext context, IDiffModelViewer diff, boolean force, IDiffModelViewer source, boolean isInitialization) {
    diff.setRoot(source.getRoot());
    FilterAction diffaction = FilterAction.NO_ACTION;
    diffaction = FilterAction.TARGET;
    if (!force && (diff.getActionDiff() == diffaction)) {
      return;
    }

    Collection<IDiffModelViewer> map = new HashSet<IDiffModelViewer>();

    IMergeableDifference sDiff = (IMergeableDifference) diff.getRelatedDiff();
    diff.setActionDiff(diffaction);

    if (isInitialization) {
      diff.setDefaultActionDiff(diffaction);
    }
    //We enable all differences which require the given difference
    //and enable all differences required by the given difference

    //Disable/Enable also all differences with 'explicit dependencies'
    HashMapSet<IDifference, IDifference> set = getRequiringDifferences(context);
    if (set != null) {
      for (IDifference difference : set.get(sDiff)) {
        IDiffModelViewer view = getViewsToMerge(context).get(difference);
        if (view != null) {
          map.add(view);
        }
      }
    }
    for (IMergeableDifference difference : sDiff.getDirectRequiresDependencies(Role.TARGET)) {
      IDiffModelViewer view = getViewsToMerge(context).get(difference);
      if (view != null) {
        map.add(view);
      }
    }

    for (IDiffModelViewer diffa : map) {
      if (diffa.getActionDiff() != diffaction) {
        check(context, diffa, force, source, isInitialization);
      }
    }
  }

}
