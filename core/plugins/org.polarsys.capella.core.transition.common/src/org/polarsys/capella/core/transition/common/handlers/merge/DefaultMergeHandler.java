/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.handlers.merge;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class DefaultMergeHandler implements IMergeHandler {

  protected Collection<ICategoryItem> categories;

  public void addCategory(ICategoryItem filter, IContext context) {
    if (categories == null) {
      categories = new LinkedList<ICategoryItem>();
    }
    categories.add(filter);
  }

  public IStatus processDifferences(IContext context, Collection<IDifference> diffSource,
      Collection<IDifference> diffTarget) {
    return Status.OK_STATUS;
  }

  public IStatus init(IContext context) {
    categories = new LinkedList<ICategoryItem>();
    return Status.OK_STATUS;
  }

  public IStatus dispose(IContext context) {
    if (categories != null) {
      categories.clear();
      categories = null;
    }
    return Status.OK_STATUS;
  }

  public Collection<ICategoryItem> getCategories(IContext context) {
    return Collections.unmodifiableCollection(categories);
  }

}
