/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.egf;

import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;

/**
 * Helper for generation of business relative interests
 */
public class BusinessGenHelper {
  /**
   * Return the naming feature if found in all genfeatures of a genClass
   * @param genModel_p
   * @param genClass_p
   * @return
   */
  public static GenFeature getNamingAttribute(GenModel genModel_p, GenClass genClass_p) {
    GenFeature result = null;
    List<GenFeature> genFeatures = genClass_p.getAllGenFeatures();
    for (GenFeature genFeature : genFeatures) {
      BusinessAnnotation businessAnnotation = BusinessAnnotationHelper.getBusinessAnnotation(genModel_p, genFeature);
      if (businessAnnotation != null && businessAnnotation.getNamingAttribute() != null) {
        result = genFeature;
      }
    }
    return result;
  }

  /**
   * Return the id feature if found in all genfeatures of a genClass
   * @param genModel_p
   * @param genClass_p
   * @return
   */
  public static GenFeature getIdAttribute(GenModel genModel_p, GenClass genClass_p) {
    GenFeature result = null;
    List<GenFeature> genFeatures = genClass_p.getAllGenFeatures();
    for (GenFeature genFeature : genFeatures) {
      BusinessAnnotation businessAnnotation = BusinessAnnotationHelper.getBusinessAnnotation(genModel_p, genFeature);
      if (businessAnnotation != null && businessAnnotation.getIdAttribute() != null) {
        result = genFeature;
      }
    }
    return result;
  }
}
