/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.model;

import java.util.Collection;

import org.eclipse.jface.viewers.CheckStateChangedEvent;

import org.polarsys.capella.core.commands.preferences.preferences.ConfigurabilityPreferences;
import org.polarsys.capella.core.commands.preferences.service.IItemDescriptor;

/**
 * Concrete implementation of the {@link IItemNode} interface.
 */
public class ItemNode implements IItemNode {

  private static final java.util.Map<String, IItemNode> instanceMap = new java.util.HashMap<String, IItemNode>();

  private final IItemDescriptor itemDescriptor;
  private boolean checked;

  private final java.util.Set<ICategoryTreeNode> categories = new java.util.HashSet<ICategoryTreeNode>();

  /**
   * Initializes me with the constraint that I represent.
   * @param constraint my constraint
   */
  private ItemNode(IItemDescriptor constraint) {
    this.itemDescriptor = constraint;
    checked = constraint.isEnabled();
  }

  /**
   * Obtains the cached node instance corresponding to the specified <code>constraint</code>. Items are mapped one-to-one to nodes.
   * @param constraint a validation constraint descriptor
   * @return the corresponding node
   */
  static IItemNode getInstance(IItemDescriptor constraint) {
    String id = constraint.getId();
    IItemNode result = null;

    if (id != null) {
      result = instanceMap.get(id);

      if (result == null) {
        result = new ItemNode(constraint);
        instanceMap.put(id, result);
      }
    }

    return result;
  }

  /**
   * Flushes the current cache of constraint nodes. This should only be called when the nodes are no longer in use.
   */
  public static void flushCache() {
    instanceMap.clear();
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public String getId() {
    return itemDescriptor.getId();
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public String getName() {
    return itemDescriptor.getName();
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public String getDescription() {
    return itemDescriptor.getDescription() != null ? itemDescriptor.getDescription() : "";
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public Collection<CategoryPreferences> getCategories() {
    return itemDescriptor.getCategories();
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public boolean isChecked() {
    return checked;
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void setChecked(boolean checked) {

    if (checked != isChecked()) {
      if (isMandatory()) {
        // reject the attempt to uncheck me
        this.checked = true;
      } else {
        this.checked = checked;
      }

      updateCategories();
    }
    this.checked = checked;
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public boolean isMandatory() {

    return false;
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void addCategory(ICategoryTreeNode category) {
    categories.add(category);
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void checkStateChanged(CheckStateChangedEvent event) {
    if (event.getChecked() != isChecked()) {
      if (isMandatory() && !event.getChecked()) {
        // reject the attempt to uncheck me
        event.getCheckable().setChecked(this, true);
      } else {
        checked = event.getChecked();
      }

      updateCategories();
    }
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void applyToPreferences() {
    // set the preference
    ConfigurabilityPreferences.setItemEnabled(itemDescriptor.getId(), isChecked());
    // tell the constraint, too
    itemDescriptor.setEnabled(!isChecked());
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void revertFromPreferences() {
    setChecked(ConfigurabilityPreferences.isInstanceScopePreferenceItemEnabled(itemDescriptor.getId()));
  }

  /*
   * (non-Javadoc) Implements the inherited method.
   */
  public void restoreDefaults() {
    setChecked(ConfigurabilityPreferences.isItemDisabledByDefault(itemDescriptor.getId()));
  }

  /**
   * Informs the categories that include me that my checked state has changed. This allows them to update theirs, to match.
   */
  private void updateCategories() {
    for (ICategoryTreeNode next : categories) {
      next.updateCheckState(this);
    }
  }

}
