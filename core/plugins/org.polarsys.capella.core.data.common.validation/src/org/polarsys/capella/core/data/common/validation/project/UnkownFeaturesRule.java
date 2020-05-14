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

package org.polarsys.capella.core.data.common.validation.project;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.SemanticResourcesScope;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.shared.id.handler.IdManager;

public class UnkownFeaturesRule extends AbstractValidationRule {

  public static final String ID = "org.polarsys.capella.core.data.common.validation.I_44";

  @Override
  public IStatus validate(IValidationContext validationContext) {

    EObject target = validationContext.getTarget();
    List<IStatus> errorStatuses = new ArrayList<>();

    if (target instanceof SystemEngineering) {
      Resource targetResource = target.eResource();
      
      if (targetResource != null) {
        ResourceSet resourceSet = targetResource.getResourceSet();
        SemanticResourcesScope resourceSetScope = new SemanticResourcesScope(resourceSet);

        if (resourceSet != null) {
          for (Resource resource : resourceSet.getResources()) {

            if (requiresAnalysis(resource) && resource instanceof XMLResource) {
              XMLResource xmlResource = (XMLResource) resource;
              String resourceName = URI.decode(xmlResource.getURI().lastSegment());
              
              Map<EObject, AnyType> objectToExtensionMap = xmlResource.getEObjectToExtensionMap();

              for (Map.Entry<EObject, AnyType> entry : objectToExtensionMap.entrySet()) {
                EObject modelElement = entry.getKey();
                String modelElementName = EObjectLabelProviderHelper.getText(modelElement);

                AnyType anyType = entry.getValue();

                if (anyType != null) {
                  FeatureMap attributeMap = anyType.getAnyAttribute();

                  for (Entry attributeEntry : attributeMap) {
                    EStructuralFeature feature = attributeEntry.getEStructuralFeature();
                    Object featureValue = extractFeatureValue(attributeEntry.getValue(), resourceSetScope);

                    IStatus status = validationContext.createFailureStatus(modelElementName, resourceName,
                        feature.getName(), featureValue);
                    errorStatuses.add(status);
                  }
                }
              }
            }
          }
        }
      }
    }

    return !errorStatuses.isEmpty() ? ConstraintStatus.createMultiStatus(validationContext, errorStatuses) : Status.OK_STATUS;
  }

  /**
   * Tries to identify the model element behind the featureValue.
   * 
   * @param featureValue
   *          the feature value.
   * @param resourceSetScope
   *          the resource scope
   * @return a textual representation based on the model element behind the feature value.
   */
  private Object extractFeatureValue(Object featureValue, SemanticResourcesScope resourceSetScope) {
    if (featureValue instanceof String) {
      String value = ((String) featureValue).replaceAll("#", "");
      EObject object = IdManager.getInstance().getEObject(value, resourceSetScope);

      if (object != null) {
        return EObjectLabelProviderHelper.getText(object);
      }

    }

    return featureValue;
  }

  private boolean requiresAnalysis(Resource resource) {
    return CapellaResourceHelper.isCapellaResource(resource) || CapellaResourceHelper.isAirdResource(resource.getURI());
  }
}
