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
package org.polarsys.capella.core.transition.common.transposer.current;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.polarsys.capella.core.transition.common.transposer.ExtendedMappingHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.CommonFactory;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.Mapping;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.contribution.ContributedPurpose;
import org.polarsys.kitalpha.transposer.rules.handler.rules.impl.PurposeRegistryImpl;
import org.polarsys.kitalpha.transposer.rules.handler.rules.runtime.RuntimeFactory;
import org.polarsys.kitalpha.transposer.rules.handler.rules.runtime.RuntimePurpose;

/**
 */
public class GenericPurposeRegistry extends PurposeRegistryImpl {

  protected GenericPurposeRegistry() {
    _runtimePurposes = new HashMap();
    init();
  }

  public static GenericPurposeRegistry getInstance() {
    return __instance;
  }

  @Override
  public RuntimePurpose getRegisteredPurpose(String purpose_p, String mappingId_p) {
    for (Object element : getRegisteredPurposes()) {
      RuntimePurpose purpose = (RuntimePurpose) element;
      if ((purpose_p != null) && purpose_p.equals(purpose.getName()) && (mappingId_p != null) && mappingId_p.equals(purpose.getId())) {
        return purpose;
      }
    }

    return registerPurpose(purpose_p, mappingId_p);
  }

  @Override
  public Mapping getContributedMapping(String purpose_p, String mappingId_p) {
    ContributedPurpose purpose = getContributedPurpose(purpose_p);
    Mapping result = null;
    if ((purpose != null) && (mappingId_p != null)) {
      for (Object element : purpose.getMappings()) {
        Mapping mapping = (Mapping) element;
        if (mappingId_p.equals(mapping.getId())) {
          result = mapping;
        }
      }

    }
    return result;
  }

  @Override
  public ContributedPurpose getContributedPurpose(String purpose_p) {
    for (Object element : getContributedPurposes()) {
      ContributedPurpose purpose = (ContributedPurpose) element;
      if ((purpose_p != null) && purpose_p.equals(purpose.getName())) {
        return purpose;
      }
    }

    return null;
  }

  @Override
  public IStatus validateRegisteredPurposes() {
    int severity = 1;
    String message = "Registered purpose are valid, check the error log for information.";
    for (Object element : getRegisteredPurposes()) {
      RuntimePurpose runtimePurpose = (RuntimePurpose) element;
      IStatus result = runtimePurpose.validate();
      if (result.getSeverity() != 1) {
        severity = result.getSeverity();
      }
    }

    if (severity == 2) {
      message = "WARNING ! Registered purpose validate with warnings, check the error log for information.";
    }
    if (severity == 2) {
      message = "ERROR ! Some registered purpose are invalid, check the error log for information.";
    }
    return new Status(severity, "org.polarsys.kitalpha.transposer.rules.handler", message);
  }

  @Override
  public IStatus validateContributedPurposes() {
    int severity = 1;
    String message = "Contributed purpose are valid, check the error log for information.";
    for (Object element : getContributedPurposes()) {
      ContributedPurpose contributedPurpose = (ContributedPurpose) element;
      IStatus result = contributedPurpose.validate();
      if (result.getSeverity() != 1) {
        severity = result.getSeverity();
      }
    }

    if (severity == 2) {
      message = "WARNING ! Contributed purpose validate with warnings, check the error log for information.";
    }
    if (severity == 2) {
      message = "ERROR ! Some contributed purpose are invalid, check the error log for information.";
    }
    return new Status(severity, "org.polarsys.kitalpha.transposer.rules.handler", message);
  }

  @Override
  public void reset() {
    _runtimePurposes.clear();
  }

  @Override
  public void init() {
    reset();
    getContributedPurposes().addAll(RuleMappingExtensionService.getInstance().getContributedPurposes());
  }

  @Override
  public RuntimePurpose registerPurpose(String purpose_p, String mappingId_p) {
    ContributedPurpose selectedPurpose = null;
    Mapping selectedMapping = null;
    for (Object element : getContributedPurposes()) {
      ContributedPurpose contributedPurpose = (ContributedPurpose) element;
      if (purpose_p.equals(contributedPurpose.getName())) {
        selectedPurpose = contributedPurpose;
      }
    }

    if (selectedPurpose == null) {
      throw new NullPointerException((new StringBuilder("The purpose to register is not in contributed ones : ")).append(purpose_p).toString());
    }
    for (Object element : selectedPurpose.getMappings()) {
      Mapping contributedMapping = (Mapping) element;
      if (mappingId_p.equals(contributedMapping.getId())) {
        selectedMapping = contributedMapping;
      }
    }

    if (selectedMapping == null) {
      throw new NullPointerException((new StringBuilder("The mapping to register is not in contributed ones : ")).append(purpose_p).append("::")
          .append(mappingId_p).toString());
    }
    RuntimePurpose runtimePurpose = RuntimeFactory.eINSTANCE.createRuntimePurpose();
    runtimePurpose.setName(selectedPurpose.getName());
    runtimePurpose.setDescription(getDescription(purpose_p, selectedMapping));

    if ((selectedPurpose.getMappings().size() == 1) && ((ExtendedMappingHelper.getExtendedMappings(selectedPurpose.getMappings().get(0)).isEmpty()))) {
      runtimePurpose.setMapping(EcoreUtil.copy(selectedPurpose.getMappings().get(0)));
    } else {
      runtimePurpose.setMapping(buildRuntimeMapping(selectedPurpose, selectedMapping));
    }

    runtimePurpose.setId(runtimePurpose.getMapping().getId());
    getRegisteredPurposes().add(runtimePurpose);
    return runtimePurpose;
  }

  private String getDescription(String purpose_p, Mapping mapping_p) {
    StringBuilder runtimeDescription = new StringBuilder();
    runtimeDescription.append("Purpose ").append(purpose_p).append("\n").append("\n").append(mapping_p.getCompleteDescription());
    return runtimeDescription.toString();
  }

  private Mapping buildRuntimeMapping(ContributedPurpose contributedPurpose_p, Mapping selectedMapping_p) {
    Mapping runtimeMapping = CommonFactory.eINSTANCE.createMapping();
    Map runtimeMappingElements = new HashMap();
    Map contributedMappingsElements = new HashMap();
    selectContributedMappingElementsToUse(contributedMappingsElements, selectedMapping_p);
    runtimeMapping.setId(selectedMapping_p.getId());
    runtimeMapping.setName(selectedMapping_p.getName());
    runtimeMapping.setOwnedContext(selectedMapping_p.getContext());
    runtimeMapping.setOwnedDomainHelper(selectedMapping_p.getDomainHelper());
    MappingElement runtimeElement;
    for (Iterator iterator = contributedMappingsElements.values().iterator(); iterator.hasNext(); runtimeMappingElements.put(
        runtimeElement.getDomainMetaClass(), runtimeElement)) {
      MappingElement contributedElement = (MappingElement) iterator.next();
      runtimeElement = CommonFactory.eINSTANCE.createMappingElement();
      runtimeElement.setName(contributedElement.getName());
      runtimeElement.setDomainMetaClass(contributedElement.getDomainMetaClass());
      if (!contributedElement.getOwnedPossibilities().isEmpty()) {
        runtimeElement.getOwnedPossibilities().addAll(EcoreUtil.copyAll(contributedElement.getAllPossibilities()));
      }
      if (contributedElement.getDefaultPossibility() != null) {
        runtimeElement.setOwnedDefaultPossibility(EcoreUtil.copy(contributedElement.getDefaultPossibility()));
      }
    }

    runtimeMapping.getOwnedMappingElements().addAll(runtimeMappingElements.values());
    return runtimeMapping;
  }

  protected void selectContributedMappingElementsToUse(Map contributedMappingsElements_p, Mapping mapping_p) {
    if (mapping_p.getExtendedMapping() != null) {
      selectContributedMappingElementsToUse(contributedMappingsElements_p, mapping_p.getExtendedMapping());
    }
    MappingElement currentElement;
    for (Iterator iterator = mapping_p.getAllOwnedMappingElements().iterator(); iterator.hasNext(); contributedMappingsElements_p.put(
        currentElement.getDomainMetaClass(), currentElement)) {
      currentElement = (MappingElement) iterator.next();
    }
  }

  protected static GenericPurposeRegistry __instance = new GenericPurposeRegistry();
  protected Map _runtimePurposes;

}
