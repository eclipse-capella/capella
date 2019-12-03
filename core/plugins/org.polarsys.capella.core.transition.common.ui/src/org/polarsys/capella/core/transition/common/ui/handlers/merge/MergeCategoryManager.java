/*******************************************************************************
 * Copyright (c) 2016, 2019 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import java.util.HashMap;

import org.eclipse.emf.diffmerge.ui.viewers.CategoryManager;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;
import org.eclipse.emf.diffmerge.ui.viewers.categories.DifferenceCategorySet;
import org.polarsys.capella.core.commands.preferences.service.ScopedCapellaPreferencesStore;
import org.polarsys.capella.core.preferences.Activator;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategorySet;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 * <pre>
 * A category manager with:
 * - A map allowing to retrieve CategorySets
 * - A save/load of checked categories into preferences
 * </pre>
 */
public class MergeCategoryManager extends CategoryManager {

  IContext context;

  HashMap<String, IDifferenceCategorySet> sets = new HashMap<String, IDifferenceCategorySet>();

  public MergeCategoryManager(EMFDiffNode node, IContext context) {
    super(node);
    this.context = context;
  }

  @Override
  public boolean addCategory(IDifferenceCategory category) {
    IDifferenceCategory squatter = getCategory(category.getID());
    if (squatter != null) {
      if (squatter.getParent() != null) {
        squatter.getParent().getChildren().remove(squatter);
      }
      removeCategory(category.getID());
    }
    IDifferenceCategorySet set = category.getParent();
    if (set != null && !(set.getChildren().contains(category))) {
      set.getChildren().add(category);
    }
    return super.addCategory(category);
  }
  
  public boolean addCategories(IDifferenceCategorySet categorySet_p) {
    if (!sets.containsKey(categorySet_p.getText(_node))) {
      sets.put(categorySet_p.getText(_node), categorySet_p);
    }
    return super.addCategories(categorySet_p);
  }
  
  public void addCategorySet(ICategorySet categorySet) {
    DifferenceCategorySet category = new DifferenceCategorySet(categorySet.getId(), categorySet.getDescription());
    addCategories(category);
    category.setText(categorySet.getText());
  }

  public boolean addCategory(ICategoryItem category) {
    String parent = category.getCategorySet();
    DiffCategoryProxy cat = new DiffCategoryProxy(category);
    if (parent != null) {
      cat.setParent(getOrCreateCategorySet(parent));
    }
    return addCategory(cat);
  }

  public IDifferenceCategorySet getCategorySet(String id) {
    return sets.get(id);
  }
  
  public IDifferenceCategorySet getOrCreateCategorySet(String id) {
    if (!sets.containsKey(id)) {
      sets.put(id, new DifferenceCategorySet(id));
    }
    return sets.get(id);
  }
  
  public void setDefaultConfiguration() {
    //We are called here two times.
    //- One time before loading the states stored in the preferences, 
    //- One time after opening the window, which is not what we want to store in the defaultConfiguration
    if (!hasDefaultConfiguration()) {
      super.setDefaultConfiguration();
    }
  }
  
  protected boolean hasDefaultConfiguration() {
    return !_defaultConfiguration.isEmpty();
  }
  
  public void initializeFromPreferences() {

    Object purposeValue = context.get(ITransitionConstants.TRANSPOSER_PURPOSE);
    if (purposeValue instanceof String) {
      String purpose = (String) purposeValue;
      ScopedCapellaPreferencesStore scps = ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID);

      for (IDifferenceCategory category : getCategories()) {
        String isActiveKey = getIsActiveKey(purpose, category);
        scps.setDefault(isActiveKey, category.isActive());
        if (scps.containsKey(isActiveKey)) {
          boolean active = scps.getBoolean(isActiveKey);
          category.setActive(active);
        }
        
        String inFocusModeKey = getIsInFocusModeKey(purpose, category);
        scps.setDefault(inFocusModeKey, category.isInFocusMode());
        if (scps.containsKey(inFocusModeKey)) {
          boolean inFocusMode = scps.getBoolean(inFocusModeKey);
          category.setInFocusMode(inFocusMode);
        }
        
      }
    }
  }
  
  public void saveToPreferences() {
    Object purposeValue = context.get(ITransitionConstants.TRANSPOSER_PURPOSE);
    if (purposeValue instanceof String) {
      String purpose = (String) purposeValue;
      
      ScopedCapellaPreferencesStore scps = ScopedCapellaPreferencesStore.getInstance(Activator.PLUGIN_ID);
      
      for (IDifferenceCategory category : this.getCategories()) {
        String isActiveKey = getIsActiveKey(purpose, category);
        scps.setValue(isActiveKey, category.isActive());
        
        String isInFocusModeKey = getIsInFocusModeKey(purpose, category);
        scps.setValue(isInFocusModeKey, category.isInFocusMode());
      }
      
      scps.save();
    }
  }
  
  @Override
  public void update() {
    super.update();
    saveToPreferences();
  }
  
  public String getIsActiveKey(String purpose, IDifferenceCategory category) {
    return purpose + "_" + category.getID() + ITransitionConstants.IS_ACTIVE;
  }
  
  public String getIsInFocusModeKey(String purpose, IDifferenceCategory category) {
    return purpose + "_" + category.getID() + ITransitionConstants.IS_IN_FOCUS_MODE;
  }

}
