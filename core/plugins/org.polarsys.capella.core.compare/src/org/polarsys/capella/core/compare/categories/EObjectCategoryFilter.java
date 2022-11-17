/*******************************************************************************
 * Copyright (c) 2022 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.compare.categories;

import org.eclipse.emf.diffmerge.diffdata.EElementRelativePresence;
import org.eclipse.emf.diffmerge.generic.api.Role;
import org.eclipse.emf.diffmerge.generic.api.diff.IDifference;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin;
import org.eclipse.emf.diffmerge.ui.EMFDiffMergeUIPlugin.ImageID;
import org.eclipse.emf.diffmerge.ui.viewers.EMFDiffNode;
import org.eclipse.emf.diffmerge.ui.viewers.IDifferenceCategory;
import org.eclipse.emf.diffmerge.ui.viewers.categories.AbstractDifferenceCategory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.graphics.Image;

/**
 * Difference Category based on the type of an Eobject
 * 
 * @author Erwann Traisnel
 *
 */
public class EObjectCategoryFilter extends AbstractDifferenceCategory {

  /**
   * Filter name
   */
  private String name;
  /**
   * Filter description
   */
  private String description;
  /**
   * Filter id
   */
  private String id;

  /**
   * 
   * @param id_p
   *          : Filter id
   * @param name_p
   *          : Filter name
   * @param active
   *          : default active, true activates the filter
   */
  public EObjectCategoryFilter(String id_p, String name_p, boolean active) {
    this(active);
    this.id = id_p;
    this.name = NLS.bind(Messages.EObjectCategoryFilter, name_p);
    this.description = NLS.bind(Messages.EObjectCategoryFilter_Description, name_p);
  }

  /**
   * 
   * @param active
   *          : default active, true activates the filter
   */
  public EObjectCategoryFilter(boolean active) {
    super();
    setActive(active);
    setInFocusMode(false);
    setVisible(true);
  }

  /**
   * 
   * @param element
   *          : element to be checked
   * @return whether the element should be filtered or not
   * 
   *         This method shall be overriden
   */
  public boolean keepElement(Object element) {
    return false;
  }

  /**
   * copy the difference category
   */
  public IDifferenceCategory copy() {
    EObjectCategoryFilter copy = new EObjectCategoryFilter(isActive());
    copy.name = name;
    copy.description = description;
    copy.id = id;
    copy.setActive(isActive());
    copy.setInFocusMode(isInFocusMode());
    copy.setModifiable(isModifiable());
    copy.setParent(getParent());
    copy.setVisible(isVisible());
    return copy;
  }

  /**
   * Shouldn't be overriden
   */
  public boolean covers(IDifference<?> difference_p, EMFDiffNode node_p) {
    if (difference_p instanceof EElementRelativePresence) {
      EObject o = ((EElementRelativePresence) difference_p).getElementMatch().get(Role.REFERENCE);
      if (o != null && keepElement(o)) {
        return true;
      }
      o = ((EElementRelativePresence) difference_p).getElementMatch().get(Role.TARGET);
      if (o != null && keepElement(o)) {
        return true;
      }
    }
    return false;
  }

  public String getID() {
    return id;
  }

  /**
   * returns filter text to be displayed
   */
  public String getText(EMFDiffNode node_p) {
    return name;
  }

  /**
   * returns the image to be used
   */
  @Override
  public Image getImage(EMFDiffNode node_p) {
    return EMFDiffMergeUIPlugin.getDefault().getImage(ImageID.FILTER);
  }

  /**
   * returns the description
   */
  @Override
  public String getDescription(EMFDiffNode node_p) {
    return description;
  }

}
