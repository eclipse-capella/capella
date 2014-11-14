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

  protected void handleMappingElementHierarchy(Mapping mapping_p, HashMap hashMap_p) {
    MappingElement element;
    for (Iterator iterator = mapping_p.getAllOwnedMappingElements().iterator(); iterator.hasNext(); hashMap_p.put(element.getDomainMetaClass(), element)) {
      element = (MappingElement) iterator.next();
      if (hashMap_p.containsKey(element.getDomainMetaClass())) {
        element.setExtendedMappingElement((MappingElement) hashMap_p.get(element.getDomainMetaClass()));
      }
    }

    if ((mapping_p.getExtenders() != null) && (mapping_p.getExtenders().size() > 0)) {
      Mapping extender;
      for (Iterator iterator1 = mapping_p.getExtenders().iterator(); iterator1.hasNext(); handleMappingElementHierarchy(extender, new HashMap(hashMap_p))) {
        extender = (Mapping) iterator1.next();
      }

    }
  }

  protected ContributedPurpose getCurrentPurpose(String purpose_p) {
    ContributedPurpose currentPurpose;
    if (_contributedPurposes.containsKey(purpose_p)) {
      currentPurpose = (ContributedPurpose) _contributedPurposes.get(purpose_p);
    } else {
      currentPurpose = ContributionFactory.eINSTANCE.createContributedPurpose();
      currentPurpose.setName(purpose_p);
      _contributedPurposes.put(purpose_p, currentPurpose);
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

  protected Mapping createMapping(IConfigurationElement mappingConfigurationElement_p) {
    Mapping currentMapping = CommonFactory.eINSTANCE.createMapping();
    currentMapping.setName(mappingConfigurationElement_p.getAttribute("mappingName"));
    currentMapping.setDescription(mappingConfigurationElement_p.getAttribute("description"));
    if (mappingConfigurationElement_p.getAttribute("domainHelper") != null) {
      try {
        currentMapping.setOwnedDomainHelper((IDomainHelper) mappingConfigurationElement_p.createExecutableExtension("domainHelper"));
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }
    }
    if (mappingConfigurationElement_p.getAttribute("context") != null) {
      try {
        currentMapping.setOwnedContext((IContext) mappingConfigurationElement_p.createExecutableExtension("context"));
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
      }
    }
    return currentMapping;
  }

  protected void loadMapping(Mapping currentMapping_p, IConfigurationElement mappingConfigurationElement_p) {
    IConfigurationElement mappingChildren[] = mappingConfigurationElement_p.getChildren();
    IConfigurationElement aiconfigurationelement[];
    int j = (aiconfigurationelement = mappingChildren).length;
    for (int i = 0; i < j; i++) {
      IConfigurationElement configurationElement = aiconfigurationelement[i];
      if (configurationElement.getName().equals("mappingElement")) {
        currentMapping_p.getOwnedMappingElements().add(handleMappingElement(configurationElement, currentMapping_p));
      }
      if (configurationElement.getName().equals("mappingPackage")) {
        currentMapping_p.getOwnedPackages().add(handleMappingPackage(configurationElement, currentMapping_p));
      }
    }

  }

  protected MappingPackage handleMappingPackage(IConfigurationElement packageConfigurationElement_p, Mapping currentMapping_p_p) {
    MappingPackage currentPackage = CommonFactory.eINSTANCE.createMappingPackage();
    currentPackage.setName(packageConfigurationElement_p.getAttribute("name"));
    IConfigurationElement mappingChildren[] = packageConfigurationElement_p.getChildren();
    IConfigurationElement aiconfigurationelement[];
    int j = (aiconfigurationelement = mappingChildren).length;
    for (int i = 0; i < j; i++) {
      IConfigurationElement configurationElement = aiconfigurationelement[i];
      if (configurationElement.getName().equals("mappingElement")) {
        currentPackage.getOwnedMappingElements().add(handleMappingElement(configurationElement, currentMapping_p_p));
      }
      if (configurationElement.getName().equals("mappingPackage")) {
        currentPackage.getOwnedPackages().add(handleMappingPackage(configurationElement, currentMapping_p_p));
      }
    }

    return currentPackage;
  }

  protected MappingElement handleMappingElement(IConfigurationElement configurationElement_p, Mapping currentMapping_p) {
    MappingElement currentElement = CommonFactory.eINSTANCE.createMappingElement();
    String name = configurationElement_p.getAttribute("name");
    currentElement.setName(name);
    String domainMetaclassName = configurationElement_p.getAttribute("domainMetaClass");
    currentElement.setDomainMetaClass(currentMapping_p.getDomainHelper().getDomainMetaclass(domainMetaclassName));
    String reusedSuperPossibilities = configurationElement_p.getAttribute("reuseExtendedElementPossibilities");
    currentElement.setReuseSuperPossibilities(Boolean.parseBoolean(reusedSuperPossibilities));
    String reusedSuperDefaultPossibility = configurationElement_p.getAttribute("reuseExtendedElementDefaultPossibility");
    currentElement.setReuseSuperDefaultPossibility(Boolean.parseBoolean(reusedSuperDefaultPossibility));
    IConfigurationElement defaultPossibilities[] = configurationElement_p.getChildren("defaultMappingPossibility");
    IConfigurationElement aiconfigurationelement[];
    int j = (aiconfigurationelement = defaultPossibilities).length;
    for (int i = 0; i < j; i++) {
      IConfigurationElement defaultPossibility = aiconfigurationelement[i];
      currentElement.setOwnedDefaultPossibility(handlePossibility(defaultPossibility));
    }

    IConfigurationElement possibilities[] = configurationElement_p.getChildren("mappingPossibility");
    IConfigurationElement aiconfigurationelement1[];
    int l = (aiconfigurationelement1 = possibilities).length;
    for (int k = 0; k < l; k++) {
      IConfigurationElement possibility = aiconfigurationelement1[k];
      currentElement.getOwnedPossibilities().add(handlePossibility(possibility));
    }

    return currentElement;
  }

  protected MappingPossibility handlePossibility(IConfigurationElement possibilityConfigurationElement_p) {
    MappingPossibility currentPossibility = CommonFactory.eINSTANCE.createMappingPossibility();
    currentPossibility.setName(possibilityConfigurationElement_p.getAttribute("name"));
    String enabled = possibilityConfigurationElement_p.getAttribute("enabled");
    if (!Boolean.parseBoolean(enabled)) {
      return currentPossibility;
    }
    String completeRuleClassName = possibilityConfigurationElement_p.getAttribute("completeRule");
    if (completeRuleClassName != null) {
      if (_classNameToIRule.containsKey(completeRuleClassName)) {
        currentPossibility.setCompleteRule((IRule) _classNameToIRule.get(completeRuleClassName));
      } else {
        try {
          currentPossibility.setCompleteRule((IRule) possibilityConfigurationElement_p.createExecutableExtension("completeRule"));
        } catch (CoreException exception_p) {
          exception_p.printStackTrace();
        }
      }
    }
    String incompleteRuleClassName = possibilityConfigurationElement_p.getAttribute("incompleteRule");
    if (incompleteRuleClassName != null) {
      if (_classNameToIRule.containsKey(incompleteRuleClassName)) {
        currentPossibility.setIncompleteRule((IRule) _classNameToIRule.get(incompleteRuleClassName));
      } else {
        try {
          currentPossibility.setIncompleteRule((IRule) possibilityConfigurationElement_p.createExecutableExtension("incompleteRule"));
        } catch (CoreException exception_p) {
          exception_p.printStackTrace();
        }
      }
    }
    if (possibilityConfigurationElement_p.getAttribute("context") != null) {
      try {
        currentPossibility.setContext((IContext) possibilityConfigurationElement_p.createExecutableExtension("context"));
      } catch (CoreException exception_p) {
        exception_p.printStackTrace();
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
