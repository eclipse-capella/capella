/*******************************************************************************
 * Copyright (c) 2019, 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import org.eclipse.emf.diffmerge.ui.viewers.DefaultDifferenceCategoryProvider;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.polarsys.capella.core.compare.CapellaDifferenceCategoryUtil;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategorySet;
import org.polarsys.capella.core.transition.common.handlers.merge.IMergeHandler;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class MergeCategoryProvider extends DefaultDifferenceCategoryProvider {

  IMergeHandler mergeHandler;

  IContext context;

  public MergeCategoryProvider(IContext context, IMergeHandler mergeHandler) {
    this.mergeHandler = mergeHandler;
    this.context = context;
  }

  @Override
  public void provideCategories(EMFDiffNode node_p) {

    super.provideCategories(node_p);

    if (node_p instanceof MergeEMFDiffNode) {

      MergeEMFDiffNode node = (MergeEMFDiffNode) node_p;

      provideMergeHandlerCategories(node);

      // Store the (real) default configuration
      node.getCategoryManager().setDefaultConfiguration();

      // Load states from preferences
      node.getCategoryManager().initializeFromPreferences();
    }
    CapellaDifferenceCategoryUtil.eINSTANCE.provideCapellaCategories(node_p);
  }

  protected void provideMergeHandlerCategories(MergeEMFDiffNode node) {

    if (mergeHandler != null) {
      for (ICategorySet item : mergeHandler.getCategoriesSet(context)) {
        node.getCategoryManager().addCategorySet(item);
      }

      for (ICategoryItem item : mergeHandler.getCategories(context)) {
        node.getCategoryManager().addCategory(item);
      }
    }

  }

}
