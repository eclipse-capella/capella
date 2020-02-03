/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.migration.contribution;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.migration.context.MigrationContext;
import org.polarsys.kitalpha.emde.model.ElementExtension;
import org.polarsys.kitalpha.emde.model.EmdePackage;

/**
 * This class removes pattern instances
 */
public class PatternInstanceDeletionContribution extends AbstractMigrationContribution {

  @Override
  public void updateElement(EObject peekObject, String typeName, EObject result, EStructuralFeature feature,
      Resource resource, MigrationContext context) {
    
    super.updateElement(peekObject, typeName, result, feature, resource, context);

    if (peekObject instanceof Project) {
      AnyType ee = ((XMLResource) resource).getEObjectToExtensionMap().get(peekObject);
      if (ee != null && ee.getMixed() != null) {
        Iterator<FeatureMap.Entry> entries = ee.getMixed().iterator();
        while (entries.hasNext()) {
          FeatureMap.Entry entry = entries.next();
          if (entry.getEStructuralFeature().getName()
              .equals(EmdePackage.Literals.EXTENSIBLE_ELEMENT__OWNED_EXTENSIONS.getName())) {
            if (entry.getValue() instanceof AnyType) {
              AnyType type = (AnyType)entry.getValue();
              if ("EmdePatternInstanceSet".equals(type.eClass().getName())) {
                entries.remove();
              }
            }
          }
        }
      }
      System.out.println();
    }
  }

}
