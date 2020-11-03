/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.service;

import java.util.Properties;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;

/**
 */
public class EPFConstraintFilter implements IConstraintFilter {
  /**
   * Category path for Common constraints.
   */
  public final static String KEY_PREFIX = "/instance/org.eclipse.emf.validation//con.disabled/"; //$NON-NLS-1$

  /** */
  protected Properties properties;

  /**
   * 
   */
  public EPFConstraintFilter(Properties roperties) {
    this.properties = roperties;
  }

  /**
   * Accept all constraints owned by the Common constraint category and the one defined by {@link #getFilterCategoryPath()}.
   * @see org.eclipse.emf.validation.service.IConstraintFilter#accept(org.eclipse.emf.validation.service.IConstraintDescriptor, org.eclipse.emf.ecore.EObject)
   */
  @Override
  public boolean accept(IConstraintDescriptor constraint, EObject target) {
    if (null != properties) {
      String key = KEY_PREFIX + constraint.getId();
      String value = properties.getProperty(key);
      if (null != value) {
        boolean booleanValue = Boolean.parseBoolean(value);
        if (booleanValue) {
          return false;
        }
        return true;
      }
      return true;
    }
    return true;
  }

}
