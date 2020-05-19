/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.common.validation.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.service.ConstraintRegistry;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.polarsys.capella.common.helpers.EObjectLabelProviderHelper;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.capella.core.model.handler.helpers.SemanticResourcesScope;
import org.polarsys.capella.core.validation.rule.AbstractValidationRule;
import org.polarsys.capella.shared.id.handler.IdManager;

public class UnkownFeaturesRule extends AbstractValidationRule {

  public static final String ID = "org.polarsys.capella.core.data.common.validation.I_44";

  /**
   * A container class that encloses all the elements required to efficiently perform the quickfix for the unknown
   * feature. This container will be added to the validation context and used in the quickfix. We are required to
   * inherit from EObject since the validation context can contain only EObjects.
   */
  public static class UnknownFeatureData extends BasicEObjectImpl {
    public final EObject modelElement;
    public final EStructuralFeature unknownFeature;
    public final XMLResource resource;

    public UnknownFeatureData(EObject modelElement, EStructuralFeature unknownFeature, XMLResource resource) {
      this.modelElement = modelElement;
      this.unknownFeature = unknownFeature;
      this.resource = resource;
    }

    /**
     * We are obligated to return a valid resource here. The reason is that after a quick fix is performed, all of the
     * invalid markers are removed from the view. Invalid markers are markers that have elements in their validation
     * context that have no resource.
     */
    @Override
    public Resource eResource() {
      return this.resource;
    }

  }

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
              URI xmlResourceURI = xmlResource.getURI();
              String resourceName = URI.decode(xmlResourceURI.lastSegment());

              Map<EObject, AnyType> objectToExtensionMap = xmlResource.getEObjectToExtensionMap();

              for (Map.Entry<EObject, AnyType> entry : objectToExtensionMap.entrySet()) {
                EObject modelElement = entry.getKey();
                String modelElementName = EObjectLabelProviderHelper.getText(modelElement);

                AnyType anyType = entry.getValue();

                if (anyType != null) {
                  FeatureMap attributeMap = anyType.getAnyAttribute();

                  for (Entry attributeEntry : attributeMap) {
                    EStructuralFeature unknownFeature = attributeEntry.getEStructuralFeature();
                    Object unknownFeatureValue = formatFeatureValue(attributeEntry.getValue(), resourceSetScope);

                    IStatus status = createFailureStatus(validationContext, modelElement, unknownFeature, xmlResource,
                        modelElementName, resourceName, unknownFeature.getName(), unknownFeatureValue);

                    errorStatuses.add(status);
                  }
                }
              }
            }
          }
        }
      }
    }

    return !errorStatuses.isEmpty() ? ConstraintStatus.createMultiStatus(validationContext, errorStatuses)
        : Status.OK_STATUS;
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
  private Object formatFeatureValue(Object featureValue, SemanticResourcesScope resourceSetScope) {
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

  private IStatus createFailureStatus(IValidationContext validationContext, EObject target,
      EStructuralFeature unknownFeature, XMLResource resource, Object... messageArguments) {

    IConstraintDescriptor constraintDescriptor = ConstraintRegistry.getInstance()
        .getDescriptor(validationContext.getCurrentConstraintId());

    Set<EObject> localResultLocus = new HashSet<>();
    localResultLocus.add(target);

    UnknownFeatureData unknownFeatureData = new UnknownFeatureData(target, unknownFeature, resource);
    localResultLocus.add(unknownFeatureData);

    return ConstraintStatus.createStatus(validationContext, target, localResultLocus,
        constraintDescriptor.getMessagePattern(), messageArguments);
  }
}
