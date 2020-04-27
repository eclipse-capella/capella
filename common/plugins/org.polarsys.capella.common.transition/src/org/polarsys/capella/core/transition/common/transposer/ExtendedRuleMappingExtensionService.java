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

package org.polarsys.capella.core.transition.common.transposer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;

import org.polarsys.capella.core.transition.common.transposer.current.RuleMappingExtensionService;
import org.polarsys.kitalpha.transposer.rules.handler.rules.common.Mapping;
import org.polarsys.kitalpha.transposer.rules.handler.rules.contribution.ContributedPurpose;

/**
 *
 */
public class ExtendedRuleMappingExtensionService extends RuleMappingExtensionService {

  @Override
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

          //We retrieve existing mapping if already defined.
          if (mappingConfigurationElement.getName().equals("mapping")) {
            if (!_mappingsHashMap.containsKey(currentMappingID)) {
              Mapping currentMapping = createMapping(mappingConfigurationElement);
              currentMapping.setId(currentMappingID);
              _mappingsHashMap.put(currentMappingID, currentMapping);
            }

            Mapping currentMapping = (Mapping) _mappingsHashMap.get(currentMappingID);
            String purpose = mappingConfigurationElement.getAttribute("mappingPurpose");
            ContributedPurpose currentPurpose = getCurrentPurpose(purpose);
            currentPurpose.getMappings().add(currentMapping);
          }
        }

      }

      l = (aiextension = extensions).length;
      for (int j = 0; j < l; j++) {
        IExtension extension = aiextension[j];
        String currentMappingID = extension.getUniqueIdentifier();
        Mapping currentMapping = (Mapping) _mappingsHashMap.get(currentMappingID);
        String extendedMappingIDs = getExtendedMappingID(extension);
        //We patch it to allow many extendedMappingIds !
        if (extendedMappingIDs != null) {
          for (String extendedMappingID : extendedMappingIDs.split(";")) {
            if (extendedMappingID != null) {
              Mapping extendedMapping = (Mapping) _mappingsHashMap.get(extendedMappingID);
              ExtendedMappingHelper.addExtendedMapping(currentMapping, extendedMapping);
            }
          }
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
      if (ExtendedMappingHelper.getExtendedMappings(mostGenericMappingInPurpose).isEmpty()) {
        roots.add(mostGenericMappingInPurpose);
      }
    }

    Mapping rootMapping;
    for (Iterator iterator1 = roots.iterator(); iterator1.hasNext(); handleMappingElementHierarchy(rootMapping, new HashMap())) {
      rootMapping = (Mapping) iterator1.next();
    }

    _contributedPurposesLoaded = true;
  }
}
