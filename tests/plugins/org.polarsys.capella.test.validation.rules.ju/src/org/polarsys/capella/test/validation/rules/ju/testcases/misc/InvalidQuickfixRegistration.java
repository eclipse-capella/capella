/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.misc;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IMarkerResolutionGenerator;
import org.polarsys.capella.common.mdsofa.common.helper.ExtensionPointHelper;
import org.polarsys.capella.common.utils.ReflectUtil;
import org.polarsys.capella.core.validation.ui.ide.internal.quickfix.CapellaQuickFixExtPointUtil;
import org.polarsys.capella.core.validation.ui.ide.internal.quickfix.ICapellaQuickFixExtPointConstants;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractCapellaMarkerResolution;
import org.polarsys.capella.core.validation.ui.ide.quickfix.AbstractMarkerResolutionGenerator;
import org.polarsys.capella.core.validation.utils.ValidationHelper;
import org.polarsys.capella.test.framework.api.BasicTestCase;

/**
 * A test that checks that there is no quickfix that fix an unexisting validation rule
 */
public class InvalidQuickfixRegistration extends BasicTestCase {

  private String toRuleId(String qualifiedRuleId) {
    return qualifiedRuleId.substring(qualifiedRuleId.lastIndexOf(".") + 1);
  }
  /** 
   */
  public void test() throws Exception {
    final List<IStatus> logged = new ArrayList<IStatus>();

    Set<String> existingRules = ValidationHelper.getAllConstraintDescriptors().stream()
        .map(v -> toRuleId(v.getId())).collect(Collectors.toSet());

    Map<AbstractCapellaMarkerResolution, Set<String>> resolutions = CapellaQuickFixExtPointUtil
        .gettAllAvailableMarkerResolution();
    for (Entry<AbstractCapellaMarkerResolution, Set<String>> values : resolutions.entrySet()) {
      Collection<String> invalidIds = values.getValue().stream().filter(id -> !id.contains("ecore") && !existingRules.contains(id))
          .collect(Collectors.toList());
      if (!invalidIds.isEmpty()) {
        logged
            .add(Status.error(values.getKey().getClass() + "=" + invalidIds.stream().collect(Collectors.joining(","))));
      }
    }
    
    Map<AbstractMarkerResolutionGenerator, Set<String>> resolutions2 = getOthers();
    for (Entry<AbstractMarkerResolutionGenerator, Set<String>> values : resolutions2.entrySet()) {
      Collection<String> invalidIds = values.getValue().stream().filter(id -> !id.contains("ecore") && !existingRules.contains(id))
          .collect(Collectors.toList());
      if (!invalidIds.isEmpty()) {
        logged
            .add(Status.error(values.getKey().getClass() + "=" + invalidIds.stream().collect(Collectors.joining(","))));
      }
    }
    
    assertTrue(logged.stream().map(s -> s.getMessage()).collect(Collectors.joining("\n")), logged.isEmpty());
  }

  public void testNoDuplicateRuleIdsTest() throws Exception {
    test();
  }

  private Map<AbstractMarkerResolutionGenerator, Set<String>> getOthers() {
    Map<AbstractMarkerResolutionGenerator, Set<String>> result = new HashMap<AbstractMarkerResolutionGenerator, Set<String>>(); 
    IConfigurationElement[] configurationElements =
        ExtensionPointHelper.getConfigurationElements(
            "org.eclipse.ui.ide",
        "markerResolution"
        )
    ;
    for (IConfigurationElement e : configurationElements) {
      if ("markerResolutionGenerator".equals(e.getName()) && "org.polarsys.capella.core.validation.markers".equals(e.getAttribute("markerType"))) {

        IMarkerResolutionGenerator resolverClass = (IMarkerResolutionGenerator) 
            ExtensionPointHelper.createInstance(
              e,
              ICapellaQuickFixExtPointConstants.CLASS_ATT
            );
          
            if (resolverClass instanceof AbstractMarkerResolutionGenerator) {
              try {
                String id = toRuleId((String) ReflectUtil.invokeInvisibleMethod(resolverClass, "getRuleId"));
                result.put((AbstractMarkerResolutionGenerator) resolverClass, Arrays.asList(id).stream().collect(Collectors.toSet()));
              } catch (SecurityException | NoSuchMethodException | IllegalArgumentException | IllegalAccessException
                  | InvocationTargetException e1) {
                e1.printStackTrace();
              }
            }
      }
     
    }
    return result;
   
  }
}
