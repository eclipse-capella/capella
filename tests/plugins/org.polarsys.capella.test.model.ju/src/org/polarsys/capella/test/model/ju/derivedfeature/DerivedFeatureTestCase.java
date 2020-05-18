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
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.model.helpers.HelperNotFoundException;
import org.polarsys.capella.core.data.capellacore.Trace;

/**
 * Derived Features test.
 */
public class DerivedFeatureTestCase extends AbstractReflectiveDerivedFeatureTest {
  /**
   * Test for all possible values for {@link Trace} and {@link TraceableElement}.
   */
  public void testDerivedFeaturesForAll() throws Exception {
    executeTestCommand(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        for (EClass eclass : _derivedFeatures.keySet()) {
          for (EStructuralFeature estructuralFeature : _derivedFeatures.get(eclass)) {
            EObject eobject = null;
            if (eclass.isAbstract()) {
              int subTypesCount = getSubTypesCount(eclass);
              for (int i = 0; i < subTypesCount; i++) {
                eobject = createType(getSubType(eclass, i));
                testMe(eclass, eobject, estructuralFeature);
              }
              if (subTypesCount == 0) {
                String msg = "[M2Consistency]" + " EClass: '" + eclass.getName()
                    + "' is abstract and have no concrete child";
                System.err.println(msg);
              }
            } else {
              eobject = createType(eclass);
              testMe(eclass, eobject, estructuralFeature);
            }
          }
        }
      }
    });
  }

  /**
   * @param class_p
   * @param object_p
   * @param feature_p
   */
  private void testMe(EClass class_p, EObject object_p, EStructuralFeature feature_p) {
    if (object_p != null) {
      try {
        object_p.eGet(feature_p);
      } catch (HelperNotFoundException ex) {
        String msg = "[HelperNotFoundException]" + " EClass: '" + class_p.getName() + "'" + " EStructuralFeature: '"
            + feature_p.getName() + "'" + " EObject: '" + object_p.eClass().getName() + "'";

        System.err.println(msg);
      }
    }
  }

  @Override
  protected EClass getSubType(EClass rootType_p, int index_p) {
    List<EClass> hierarchy = _hierarchies.get(rootType_p);
    return ((null != hierarchy) && (index_p < hierarchy.size())) ? hierarchy.get(index_p) : null;
  }

  /**
   * Get specified root type children types count.<br>
   * Note that this requires that this root type is specified in {@link #getRootTypes()}.
   * 
   * @param rootType_p
   * @return
   */
  @Override
  protected int getSubTypesCount(EClass rootType_p) {
    List<EClass> hierarchy = _hierarchies.get(rootType_p);
    return (null != hierarchy) ? hierarchy.size() : 0;
  }

  @Override
  public void test() throws Exception {
    testDerivedFeaturesForAll();
  }

  @Override
  protected List<EClass> getRootTypes() {
    List<EClass> result = new ArrayList<EClass>(0);
    for (EClass eclass : _derivedFeatures.keySet()) {
      result.add(eclass);
    }
    return result;
  }
}
