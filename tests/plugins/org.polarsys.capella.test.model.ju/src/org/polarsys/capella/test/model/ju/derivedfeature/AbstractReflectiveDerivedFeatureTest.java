/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.derivedfeature;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.capella.common.mdsofa.common.helper.EcoreHelper;
import org.polarsys.capella.test.model.ju.crossreferencer.AbstractReflectiveCrossReferencerTest;

/**
 * Abstract reflective derived feature test.<br>
 * Loads the capella metamodel.
 *
 */
public abstract class AbstractReflectiveDerivedFeatureTest extends AbstractReflectiveCrossReferencerTest {
  /**
   * Map of (EClass, list of derived EStructuralFeature).
   */
  protected HashMap<EClass, List<EStructuralFeature>> _derivedFeatures;

  @Override
  protected void postRunTest() {
    super.postRunTest();
    if (null != _derivedFeatures) {
      // Empty contained hierarchies.
      for (List<EStructuralFeature> hierarchy : _derivedFeatures.values()) {
        hierarchy.clear();
      }
      // Empty and nullify map.
      _derivedFeatures.clear();
      _derivedFeatures = null;
    }
  }

  /**
   * @see org.polarsys.capella.test.common.ju.AbstractCapellaTest#preTestRun()
   */
  @Override
  protected void preRunTest() {
    try {
      loadM2();

      _derivedFeatures = new HashMap<EClass, List<EStructuralFeature>>(0);
      // Get all contents.
      ResourceSet resourceSet = getExecutionManager().getEditingDomain().getResourceSet();
      TreeIterator<Notifier> contents = resourceSet.getAllContents();
      while (contents.hasNext()) {
        Notifier type = contents.next();
        // Retain class only.
        if (type instanceof EClass) {
          EClass classType = EcoreHelper.getStaticClass((EClass) type);
          for (EStructuralFeature structuralFeature : classType.getEStructuralFeatures()) {
            if (structuralFeature.isDerived()) {
              List<EStructuralFeature> lst = _derivedFeatures.get(classType);
              if (lst == null) {
                lst = new ArrayList<EStructuralFeature>(0);
                _derivedFeatures.put(classType, lst);
              }
              lst.add(structuralFeature);
            }
          }
        }
      }
    } finally {
      super.preRunTest();
    }
  }
}
