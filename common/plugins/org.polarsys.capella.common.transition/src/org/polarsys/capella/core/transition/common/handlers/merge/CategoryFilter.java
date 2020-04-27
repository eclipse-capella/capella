/*******************************************************************************
 * Copyright (c) 2016, 2017 THALES GLOBAL SERVICES.
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

import org.eclipse.emf.diffmerge.api.diff.IDifference;
import org.eclipse.emf.diffmerge.api.diff.IMergeableDifference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class CategoryFilter implements ICategoryItem {

  private boolean visible = true;

  private boolean modifiable = true;

  private boolean active = true;

  private boolean focus = true;

  protected IContext context;

  protected String setId;
  
  protected String name;

  protected String description;
  
  protected Object image;

  protected String id;

  public CategoryFilter(IContext context, String id, String name, String description, Object image) {
    this.context = context;
    this.image = image;
    this.name = name;
    this.description = description;
    this.id = (id == null ? getClass().getSimpleName() : id);
  }
  
  public CategoryFilter(IContext context, String name, String description) {
    this(context, null, name, description, null);
  }

  @Override
  public String getId() {
    return id;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public Object getImage() {
    return image;
  }

  @Override
  public String getText() {
    return name;
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

  @Override
  public String getCategorySet() {
    return setId;
  }

  @Override
  public void setCategorySet(String setId) {
    this.setId=setId;
  }
  
}
