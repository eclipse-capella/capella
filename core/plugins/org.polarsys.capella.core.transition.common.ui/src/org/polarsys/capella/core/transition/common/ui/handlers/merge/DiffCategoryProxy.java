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
package org.polarsys.capella.core.transition.common.ui.handlers.merge;

import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategorySet;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategoryItem;
import org.eclipse.emf.diffmerge.ui.viewers.categories.ConflictCategory;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.core.transition.common.handlers.merge.ICategoryItem;

public class DiffCategoryProxy extends AbstractDifferenceCategoryItem implements IDifferenceCategory {

  ICategoryItem item;

  public DiffCategoryProxy(ICategoryItem item) {
    this.item = item;
  }

  @Override
  public void setParent(IDifferenceCategorySet parent_p) {
    super.setParent(parent_p);
  }
  
  @Override
  public boolean covers(IDifference difference, EMFDiffNode node) {
    return item.covers(difference);
  }

  @Override
  public String getDescription(EMFDiffNode node) {
    return item.getDescription();
  }

  @Override
  public Image getImage(EMFDiffNode node) {
    Object image = item.getImage();
    if (image instanceof Image)
      return (Image) image;
    return EMFDiffMergeUIPlugin.getDefault().getImage(EMFDiffMergeUIPlugin.ImageID.FILTER);
  }

  @Override
  public String getText(EMFDiffNode node) {
    return item.getText();
  }

  @Override
  public boolean isActive() {
    return item.isActive();
  }

  @Override
  public boolean isApplicable(EMFDiffNode node) {
    return item.isApplicable();
  }

  @Override
  public boolean isInFocusMode() {
    return item.isInFocusMode();
  }

  @Override
  public boolean isModifiable() {
    return item.isModifiable();
  }

  @Override
  public boolean isVisible() {
    return item.isVisible();
  }

  @Override
  public boolean mayCoverPendingDifferences() {
    return true;
  }

  @Override
  public void setActive(boolean active) {
    item.setActive(active);
  }

  @Override
  public void setInFocusMode(boolean inFocusMode) {
    item.setInFocusMode(inFocusMode);
  }

  @Override
  public void setModifiable(boolean modifiable) {
    item.setModifiable(modifiable);
  }

  @Override
  public void setVisible(boolean visible) {
    item.setVisible(visible);
  }

  @Override
  public String getID() {
    return item.getId();
  }

  @Override
  public void copyState(IDifferenceCategory peer) {
    item.setInFocusMode(peer.isInFocusMode());
    item.setVisible(peer.isVisible());
    item.setActive(peer.isActive());
    item.setModifiable(peer.isModifiable());
  }

  private class CategoryState extends AbstractDifferenceCategory {

    CategoryState() {
      copyState(DiffCategoryProxy.this);
    }

    @Override
    public String getID() {
      return DiffCategoryProxy.this.getID();
    }

    @Override
    public String getText(EMFDiffNode node) {
      return DiffCategoryProxy.this.getText(node);
    }

    @Override
    public boolean covers(IDifference<?> difference, EMFDiffNode node) {
      return false;
    }

    /**
     * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#copy()
     */
    @Override
    public IDifferenceCategory copy() {
      CategoryState copied = new CategoryState();
      copied.copyState(this);
      return copied;
    }

  }


  /**
   * @see org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory#copy()
   */
  @Override
  public IDifferenceCategory copy() {
    DiffCategoryProxy copied = new DiffCategoryProxy(item);
    copied.copyState(this);
    return copied;
  }

}
