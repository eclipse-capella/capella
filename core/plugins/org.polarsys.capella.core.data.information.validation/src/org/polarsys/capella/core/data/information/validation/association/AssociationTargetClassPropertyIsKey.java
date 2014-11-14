/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.data.information.validation.association;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;

import org.polarsys.capella.core.data.helpers.capellacore.services.GeneralizableElementExt;
import org.polarsys.capella.core.data.information.Association;
import org.polarsys.capella.core.data.information.Class;
import org.polarsys.capella.core.data.information.Property;
import org.polarsys.capella.core.data.capellacore.Classifier;
import org.polarsys.capella.core.data.capellacore.Feature;
import org.polarsys.capella.core.data.capellacore.GeneralizableElement;
import org.polarsys.capella.core.model.helpers.AssociationExt;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.common.data.modellingcore.AbstractType;

/**
 * A reference relationship association of kind ASSOCIATION can only exist, if and only if the target class has or inherits at least one property being a key.
 */
public class AssociationTargetClassPropertyIsKey extends AbstractValidationRule {
  /**
   * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
   */
  @Override
  public IStatus validate(IValidationContext ctx) {
    EObject eObj = ctx.getTarget();
    EMFEventType eType = ctx.getEventType();
    if (eType == EMFEventType.NULL) {
      if (eObj instanceof Association) {
        // current Association
        Association association = (Association) eObj;
        // Association most have at least one Role with AggregationKind=Association
        if (!AssociationExt.isOfKindAssocation(association)) {
          return ctx.createSuccessStatus();
        }

        EList<Property> navigableMembers = association.getNavigableMembers();

        // consider only if one of the role is navigable
        if (null != navigableMembers) {
          for (Property property : navigableMembers) {

            if (null != property) {
              // if navigable member is part of key return success status
              // if (property.isIsPartOfKey()) {
              // return ctx.createSuccessStatus();
              // }
              // get the target Class
              AbstractType abstractType = property.getAbstractType();
              if (abstractType instanceof Class) {
                // list to collect all super Classes
                List<GeneralizableElement> allSuperGenEles = new ArrayList<GeneralizableElement>(0);
                Class cls = (Class) abstractType;
                // add current
                allSuperGenEles.add(cls);
                // add all supper
                allSuperGenEles.addAll(GeneralizableElementExt
                    .getAllSuperGeneralizableElements(cls));
                for (GeneralizableElement genEle : allSuperGenEles) {
                  // filter Classifier
                  if (genEle instanceof Classifier) {
                    Classifier classifier = (Classifier) genEle;
                    // get owned properties
                    EList<Feature> ownedFeatures = classifier.getOwnedFeatures();
                    for (Feature feature : ownedFeatures) {
                      // filter properties
                      if (feature instanceof Property) {
                        Property pro = (Property) feature;
                        // if at least one property is part of key return success status
                        if (pro.isIsPartOfKey()) {
                          return ctx.createSuccessStatus();
                        }
                      }
                    }
                  }
                }
                return ctx.createFailureStatus(association.getName());

              }
            }
          }
        }
      }
    }
    return ctx.createSuccessStatus();
  }
}
