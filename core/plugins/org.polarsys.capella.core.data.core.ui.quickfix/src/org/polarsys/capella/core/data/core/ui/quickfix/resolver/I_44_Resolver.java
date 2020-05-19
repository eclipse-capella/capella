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
package org.polarsys.capella.core.data.core.ui.quickfix.resolver;

import java.util.Map;
import java.util.Optional;

import org.eclipse.core.resources.IMarker;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.tools.report.appenders.reportlogview.MarkerViewHelper;
import org.polarsys.capella.core.data.common.validation.project.UnkownFeaturesRule.UnknownFeatureData;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.Unit;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;

public class I_44_Resolver extends AbstractCapellaMarkerResolution {

  @Override
  public void run(IMarker marker) {

    Optional<UnknownFeatureData> unknownFeatureData = extractUnknownFeatureData(marker);

    if (unknownFeatureData.isPresent()) {
      UnknownFeatureData data = unknownFeatureData.get();

      Map<EObject, AnyType> objectToExtensionMap = data.resource.getEObjectToExtensionMap();
      AnyType anyType = objectToExtensionMap.get(data.modelElement);

      if (anyType != null) {
        FeatureMap attributeMap = anyType.getAnyAttribute();

        if (attributeMap != null) {
          boolean featureRemoved = attributeMap
              .removeIf(entry -> data.unknownFeature.equals(entry.getEStructuralFeature()));

          if (featureRemoved) {
            setResourceDirty(data.resource);            
            deleteMarker(marker);
          }
        }
      }
    }
  }

  private Optional<UnknownFeatureData> extractUnknownFeatureData(IMarker marker) {
    return MarkerViewHelper.getModelElementsFromMarker(marker) //
        .stream() //
        .filter(UnknownFeatureData.class::isInstance) //
        .map(UnknownFeatureData.class::cast) //
        .findAny();
  }

  private void setResourceDirty(Resource resource) {
    TransactionHelper.getExecutionManager(resource).execute(new AbstractReadWriteCommand() {

      @Override
      public void run() {
        EList<EObject> contents = resource.getContents();
        int lastIndex = contents.size();

        Unit lightDummy = InformationFactory.eINSTANCE.createUnit();
        contents.add(lightDummy);

        // O(1) removal
        contents.remove(lastIndex);
      }
    });
  }

}
