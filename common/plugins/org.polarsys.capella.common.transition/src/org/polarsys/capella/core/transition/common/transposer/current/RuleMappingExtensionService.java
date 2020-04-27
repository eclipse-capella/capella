/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.transition.common.transposer.current;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;

import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IDomainHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IRule;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.CommonFactory;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.Mapping;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingElement;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPackage;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.MappingPossibility;
import org.polarsys.kitalpha.transposer.rules.handler.rules.contribution.ContributedPurpose;
import org.polarsys.kitalpha.transposer.rules.handler.rules.contribution.ContributionFactory;

/**
 *
 */
public class RuleMappingExtensionService implements IRuleMappingExtensionConstants {

  protected RuleMappingExtensionService() {
    _contributedPurposesLoaded = false;
    _contributedPurposes = new HashMap();
    _classNameToIRule = new HashMap();
    _mappingsHashMap = new HashMap();
  }

  public static RuleMappingExtensionService getInstance() {
    return __instance;
  }

  public Collection getContributedPurposes() {
    if (!_contributedPurposesLoaded) {
      loadContributedPurposes();
    }
    return _contributedPurposes.values();
  }

  public void loadContributedPurposes() {
    _contributedPurposes.clear();
    _mappingsHashMap.clear();
    if (EMFPlugin.IS_ECLIPSE_RUNNING) {
      IExtension extensions[] = Platform.getExtensionRegistry().getExtensionPoint("org.polarsys.kitalpha.transposer.rules.handler.mapping").getExtensions();
      IExtension aiextension[];
      int l = (aiextension = extensions).length;
      for (int i = 0; i < l; i++) {
        IExtension extension = aiextension[i];
        String currentMappingID = extension.getUniqueIdentifier();
        IConfigurationElement aiconfigurationelement[];
        int k1 = (aiconfigurationelement = extension.getConfigurationElements()).length;
        for (int i1 = 0; i1 < k1; i1++) {
          IConfigurationElement mappingConfigurationElement = aiconfigurationelement[i1];
          if (mappingConfigurationElement.getName().equals("mapping")) {
            Mapping currentMapping = createMapping(mappingConfigurationElement);
            String purpose = mappingConfigurationElement.getAttribute("mappingPurpose");
            ContributedPurpose currentPurpose = getCurrentPurpose(purpose);
            currentPurpose.getMappings().add(currentMapping);
            currentMapping.setId(currentMappingID);
            _mappingsHashMap.put(currentMappingID, currentMapping);
          }
        }

      }

      l = (aiextension = extensions).length;
      for (int j = 0; j < l; j++) {
        IExtension extension = aiextension[j];
        String currentMappingID = extension.getUniqueIdentifier();
        Mapping currentMapping = (Mapping) _mappingsHashMap.get(currentMappingID);
        String extendedMappingID = getExtendedMappingID(extension);
        if (extendedMappingID != null) {
          Mapping extendedMapping = (Mapping) _mappingsHashMap.get(extendedMappingID);
          currentMapping.setExtendedMapping(extendedMapping);
        }
      }

      l = (aiextension = extensions).length;
      for (int k = 0; k < l; k++) {
        IExtension extension = aiextension[k];
        String currentMappingID = extension.getUniqueIdentifier();
        IConfigurationElement aiconfigurationelement1[];
        int l1 = (aiconfigurationelement1 = extension.getConfigurationElements()).length;
        for (int j1 = 0; j1 < l1; j1++) {
          IConfigurationElement mappingConfigurationElement = aiconfigurationelement1[j1];
          if (mappingConfigurationElement.getName().equals("mapping") && _mappingsHashMap.containsKey(currentMappingID)) {
            Mapping currentMapping = (Mapping) _mappingsHashMap.get(currentMappingID);
            try {
              loadMapping(currentMapping, mappingConfigurationElement);
            } catch (Exception e) {
              e.printStackTrace();
            }
          }
        }

      }

    }
    List roots = new ArrayList();
    for (Iterator iterator = _contributedPurposes.values().iterator(); iterator.hasNext();) {
      ContributedPurpose purpose = (ContributedPurpose) iterator.next();
      Mapping mostGenericMappingInPurpose = purpose.getMostGenericMapping();
      if (mostGenericMappingInPurpose.getExtendedMapping() == null) {
        roots.add(mostGenericMappingInPurpose);
      }
    }

    Mapping rootMapping;
    for (Iterator iterator1 = roots.iterator(); iterator1.hasNext(); handleMappingElementHierarchy(rootMapping, new HashMap())) {
      rootMapping = (Mapping) iterator1.next();
    }

    _contributedPurposesLoaded = true;
  }

  protected void handleMappingElementHierarchy(Mapping mapping, HashMap hashMap) {
    MappingElement element;
    for (Iterator iterator = mapping.getAllOwnedMappingElements().iterator(); iterator.hasNext(); hashMap.put(element.getDomainMetaClass(), element)) {
      element = (MappingElement) iterator.next();
      if (hashMap.containsKey(element.getDomainMetaClass())) {
        element.setExtendedMappingElement((MappingElement) hashMap.get(element.getDomainMetaClass()));
      }
    }

    if ((mapping.getExtenders() != null) && (mapping.getExtenders().size() > 0)) {
      Mapping extender;
      for (Iterator iterator1 = mapping.getExtenders().iterator(); iterator1.hasNext(); handleMappingElementHierarchy(extender, new HashMap(hashMap))) {
        extender = (Mapping) iterator1.next();
      }

    }
  }

  protected ContributedPurpose getCurrentPurpose(String purpose) {
    ContributedPurpose currentPurpose;
    if (_contributedPurposes.containsKey(purpose)) {
      currentPurpose = (ContributedPurpose) _contributedPurposes.get(purpose);
    } else {
      currentPurpose = ContributionFactory.eINSTANCE.createContributedPurpose();
      currentPurpose.setName(purpose);
      _contributedPurposes.put(purpose, currentPurpose);
    }
    return currentPurpose;
  }

  protected String getExtendedMappingID(IExtension extension) {
    IConfigurationElement elements[] = extension.getConfigurationElements();
    IConfigurationElement mappingConfigurationElement = elements[0];
    if (!mappingConfigurationElement.getName().equals("mapping")) {
      return null;
    } else {
      return mappingConfigurationElement.getAttribute("extendedMappingExtensionID");
    }
  }

  protected Mapping createMapping(IConfigurationElement mappingConfigurationElement) {
    Mapping currentMapping = CommonFactory.eINSTANCE.createMapping();
    currentMapping.setName(mappingConfigurationElement.getAttribute("mappingName"));
    currentMapping.setDescription(mappingConfigurationElement.getAttribute("description"));
    if (mappingConfigurationElement.getAttribute("domainHelper") != null) {
      try {
        currentMapping.setOwnedDomainHelper((IDomainHelper) mappingConfigurationElement.createExecutableExtension("domainHelper"));
      } catch (CoreException exception) {
        exception.printStackTrace();
      }
    }
    if (mappingConfigurationElement.getAttribute("context") != null) {
      try {
        currentMapping.setOwnedContext((IContext) mappingConfigurationElement.createExecutableExtension("context"));
      } catch (CoreException exception) {
        exception.printStackTrace();
      }
    }
    return currentMapping;
  }

  protected void loadMapping(Mapping currentMapping, IConfigurationElement mappingConfigurationElement) {
    IConfigurationElement mappingChildren[] = mappingConfigurationElement.getChildren();
    IConfigurationElement aiconfigurationelement[];
    int j = (aiconfigurationelement = mappingChildren).length;
    for (int i = 0; i < j; i++) {
      IConfigurationElement configurationElement = aiconfigurationelement[i];
      if (configurationElement.getName().equals("mappingElement")) {
        currentMapping.getOwnedMappingElements().add(handleMappingElement(configurationElement, currentMapping));
      }
      if (configurationElement.getName().equals("mappingPackage")) {
        currentMapping.getOwnedPackages().add(handleMappingPackage(configurationElement, currentMapping));
      }
    }

  }

  protected MappingPackage handleMappingPackage(IConfigurationElement packageConfigurationElement, Mapping currentMapping) {
    MappingPackage currentPackage = CommonFactory.eINSTANCE.createMappingPackage();
    currentPackage.setName(packageConfigurationElement.getAttribute("name"));
    IConfigurationElement mappingChildren[] = packageConfigurationElement.getChildren();
    IConfigurationElement aiconfigurationelement[];
    int j = (aiconfigurationelement = mappingChildren).length;
    for (int i = 0; i < j; i++) {
      IConfigurationElement configurationElement = aiconfigurationelement[i];
      if (configurationElement.getName().equals("mappingElement")) {
        currentPackage.getOwnedMappingElements().add(handleMappingElement(configurationElement, currentMapping));
      }
      if (configurationElement.getName().equals("mappingPackage")) {
        currentPackage.getOwnedPackages().add(handleMappingPackage(configurationElement, currentMapping));
      }
    }

    return currentPackage;
  }

  protected MappingElement handleMappingElement(IConfigurationElement configurationElement, Mapping currentMapping) {
    MappingElement currentElement = CommonFactory.eINSTANCE.createMappingElement();
    String name = configurationElement.getAttribute("name");
    currentElement.setName(name);
    String domainMetaclassName = configurationElement.getAttribute("domainMetaClass");
    currentElement.setDomainMetaClass(currentMapping.getDomainHelper().getDomainMetaclass(domainMetaclassName));
    String reusedSuperPossibilities = configurationElement.getAttribute("reuseExtendedElementPossibilities");
    currentElement.setReuseSuperPossibilities(Boolean.parseBoolean(reusedSuperPossibilities));
    String reusedSuperDefaultPossibility = configurationElement.getAttribute("reuseExtendedElementDefaultPossibility");
    currentElement.setReuseSuperDefaultPossibility(Boolean.parseBoolean(reusedSuperDefaultPossibility));
    IConfigurationElement defaultPossibilities[] = configurationElement.getChildren("defaultMappingPossibility");
    IConfigurationElement aiconfigurationelement[];
    int j = (aiconfigurationelement = defaultPossibilities).length;
    for (int i = 0; i < j; i++) {
      IConfigurationElement defaultPossibility = aiconfigurationelement[i];
      currentElement.setOwnedDefaultPossibility(handlePossibility(defaultPossibility));
    }

    IConfigurationElement possibilities[] = configurationElement.getChildren("mappingPossibility");
    IConfigurationElement aiconfigurationelement1[];
    int l = (aiconfigurationelement1 = possibilities).length;
    for (int k = 0; k < l; k++) {
      IConfigurationElement possibility = aiconfigurationelement1[k];
      currentElement.getOwnedPossibilities().add(handlePossibility(possibility));
    }

    return currentElement;
  }

  protected MappingPossibility handlePossibility(IConfigurationElement possibilityConfigurationElement) {
    MappingPossibility currentPossibility = CommonFactory.eINSTANCE.createMappingPossibility();
    currentPossibility.setName(possibilityConfigurationElement.getAttribute("name"));
    String enabled = possibilityConfigurationElement.getAttribute("enabled");
    if (!Boolean.parseBoolean(enabled)) {
      return currentPossibility;
    }
    String completeRuleClassName = possibilityConfigurationElement.getAttribute("completeRule");
    if (completeRuleClassName != null) {
      if (_classNameToIRule.containsKey(completeRuleClassName)) {
        currentPossibility.setCompleteRule((IRule) _classNameToIRule.get(completeRuleClassName));
      } else {
        try {
          currentPossibility.setCompleteRule((IRule) possibilityConfigurationElement.createExecutableExtension("completeRule"));
        } catch (CoreException exception) {
          exception.printStackTrace();
        }
      }
    }
    String incompleteRuleClassName = possibilityConfigurationElement.getAttribute("incompleteRule");
    if (incompleteRuleClassName != null) {
      if (_classNameToIRule.containsKey(incompleteRuleClassName)) {
        currentPossibility.setIncompleteRule((IRule) _classNameToIRule.get(incompleteRuleClassName));
      } else {
        try {
          currentPossibility.setIncompleteRule((IRule) possibilityConfigurationElement.createExecutableExtension("incompleteRule"));
        } catch (CoreException exception) {
          exception.printStackTrace();
        }
      }
    }
    if (possibilityConfigurationElement.getAttribute("context") != null) {
      try {
        currentPossibility.setContext((IContext) possibilityConfigurationElement.createExecutableExtension("context"));
      } catch (CoreException exception) {
        exception.printStackTrace();
      }
    }
    return currentPossibility;
  }

  public void reset() {
    _contributedPurposesLoaded = false;
    _classNameToIRule.clear();
    _contributedPurposes.clear();
    _mappingsHashMap.clear();
  }

  public void dispose() {
    _contributedPurposesLoaded = false;
    _contributedPurposes.clear();
  }

  protected static RuleMappingExtensionService __instance = new RuleMappingExtensionService();
  protected Map _contributedPurposes;
  protected Map _mappingsHashMap;
  protected Map _classNameToIRule;
  protected boolean _contributedPurposesLoaded;

}
