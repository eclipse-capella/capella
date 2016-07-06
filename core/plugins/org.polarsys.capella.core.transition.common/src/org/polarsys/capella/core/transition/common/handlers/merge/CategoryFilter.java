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

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.swt.graphics.Image;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CategoryFilter implements ICategoryItem {

  private boolean visible = true;

  private boolean modifiable = true;

  private boolean active = true;

  private boolean focus = true;

  protected IContext context;

  protected String prefix = ICommonConstants.EMPTY_STRING;

  protected String name;

  protected Image image;

  protected String id;

  public CategoryFilter(IContext context, String id, String name, Image image) {
    this.context = context;
    this.image = image;
    this.name = name;
    this.id = id;
  }

  public CategoryFilter(IContext context, String name, Image image) {
    this.context = context;
    this.image = image;
    this.name = name;
    this.id = getClass().getCanonicalName();
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getDescription() {
    return name;
  }

  @Override
  public Image getImage() {
    return image;
  }

  @Override
  public String getText() {
    return prefix + name;
  }

  @Override
  public boolean isInFocusMode() {
    return focus;
  }

  public boolean isModifiable() {
    return modifiable;
  }

  public boolean isVisible() {
    return visible;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  @Override
  public boolean isApplicable() {
    return true;
  }

  @Override
  public boolean mayCoverPendingDifferences() {
    return true;
  }

  @Override
  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public void setInFocusMode(boolean inFocusMode) {
    this.focus = inFocusMode;
  }

  @Override
  public void setModifiable(boolean modifiable) {
    this.modifiable = modifiable;
  }

  @Override
  public void setVisible(boolean visible) {
    this.visible = visible;
  }

  @Override
  public boolean covers(IDifference difference) {
    return false;
  }

  @Override
  public void setDependencies(IMergeableDifference difference) {
    // Nothing
  }

  @Override
  public boolean covers(EStructuralFeature feature) {
    return false;
  }

}
