/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.navigator.viewer;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.viewpoint.ViewpointPackage;
import org.polarsys.capella.core.diagram.helpers.DiagramHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

/**
 * A folder-like item to categorize DRepresentationDescriptors.
 */
class RepresentationPackage {

  private final String name;
  private final EObject parent;
  
  RepresentationPackage(String name, EObject parentElement) {
    this.name = name;
    this.parent = parentElement;
  }

  String getName() {
    return name;
  }
  
  EObject getParent() {
    return parent;
  }

  Object[] getChildren() {
    Collection<EObject> descriptors = new EObjectQuery(parent).getInverseReferences(ViewpointPackage.Literals.DREPRESENTATION_DESCRIPTOR__TARGET); 
    return Collections2.filter(descriptors, new Predicate<EObject>() {
      @Override
      public boolean apply(EObject input) {
        return getName().equals(DiagramHelper.getService().getPackageName((DRepresentationDescriptor) input));
      }
    }).toArray();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + ((parent == null) ? 0 : parent.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    RepresentationPackage other = (RepresentationPackage) obj;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (parent == null) {
      if (other.parent != null)
        return false;
    } else if (!parent.equals(other.parent))
      return false;
    return true;
  }

}