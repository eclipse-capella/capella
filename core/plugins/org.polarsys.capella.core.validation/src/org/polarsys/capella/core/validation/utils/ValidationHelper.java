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
package org.polarsys.capella.core.validation.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.ModelValidationService;

import org.polarsys.capella.core.validation.filter.CapellaConstraintFilter;


/**
 * Utility class for EMF validation use.
 *
 */
public class ValidationHelper {

  /**
   * Get all constraints contributed via the EMF Validation framework 
   * @return
   */
  public static List<IConstraintDescriptor> getAllConstraintDescriptors() {
    
    List<IConstraintDescriptor> result = new ArrayList<IConstraintDescriptor>();
    
    ensureEMFValidationActivation();
    
    ConstraintRegistry registry = ConstraintRegistry.getInstance();
    
    result.addAll(registry.getAllDescriptors());
    
    return result;
  }
  
  /**
   * Get all constraints contributed via the EMF Validation framework for Capella purposes
   * e.g. rules stored into the Capella constraint category 
   * @return
   */
  public static List<IConstraintDescriptor> getAllCapellaConstraintDescriptors() {
    
    List<IConstraintDescriptor> result = new ArrayList<IConstraintDescriptor>();
    
    ensureEMFValidationActivation();
    
    for (IConstraintDescriptor icd: getAllConstraintDescriptors()) {
      Set<Category> categories = icd.getCategories();
      for (Category category : categories) {
        String categoryPath = category.getPath();
        if (categoryPath.startsWith(CapellaConstraintFilter.CAPELLA_CONSTRAINT_CATEGORY_PATH)) {
          result.add(icd);
          break;
        }
      }
    }
    
    return result;
  }
  
  /**
   * Ensure that all constraints have been loaded.
   */
  public static void ensureEMFValidationActivation() {
    
    ModelValidationService.getInstance().loadXmlConstraintDeclarations();
    
    return;
  }
  
}
