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
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.ui.viewers.CategoryManager;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class MergeCategoryManager extends CategoryManager {

  IContext context;

  public MergeCategoryManager(EMFDiffNode node, IContext context) {
    super(node);
    this.context = context;
  }


  @Override
  public boolean isFiltered(IDifference difference) {
    boolean focused = false;
    boolean excluded = false;

    for (IDifferenceCategory category : _activeCategories) {
      if (category.isInFocusMode()) {
        focused = focused || category.covers(difference, _node);
      } else {
        excluded = excluded || category.covers(difference, _node);
      }
    }
    if (excluded)
      return true;

    if (focused)
      return false;

    return true;
  }

  @Override
  public void update() {
    super.update();
    
    Object purposeValue = this.context.get(ITransitionConstants.TRANSPOSER_PURPOSE);
    if (purposeValue instanceof String) {
      String purpose = (String) purposeValue;
      
      ScopedCapellaPreferencesStore scps = ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID);
      
      for (IDifferenceCategory category : this.getCategories()) {
        String isActiveKey = getIsActiveKey(purpose, category, _node);
        scps.setValue(isActiveKey, category.isActive());
        
        String isInFocusModeKey = getIsInFocusModeKey(purpose, category, _node);
        scps.setValue(isInFocusModeKey, category.isInFocusMode());
      }
      
      scps.save();
    }
  }
  
  public static String getIsActiveKey(String purpose, IDifferenceCategory category, EMFDiffNode _node) {
    return purpose + "_" + category.getText(_node) + ITransitionConstants.IS_ACTIVE;
  }
  
  public static String getIsInFocusModeKey(String purpose, IDifferenceCategory category, EMFDiffNode _node) {
    return purpose + "_" + category.getText(_node) + ITransitionConstants.IS_IN_FOCUS_MODE;
  }
}
