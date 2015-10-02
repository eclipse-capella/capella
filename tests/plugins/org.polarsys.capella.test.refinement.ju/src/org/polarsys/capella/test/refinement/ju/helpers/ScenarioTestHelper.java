/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.helpers;

import java.util.Comparator;
import java.util.List;

import junit.framework.Assert;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.osgi.util.NLS;

import org.polarsys.capella.core.data.helpers.interaction.services.AbstractEndExt;
import org.polarsys.capella.core.data.helpers.interaction.services.ScenarioExt;
import org.polarsys.capella.core.data.interaction.AbstractEnd;
import org.polarsys.capella.core.data.interaction.InstanceRole;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.interaction.Scenario;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 * Utility tools for scenario
 */
public class ScenarioTestHelper {

  public static void compareStructure(Scenario scenario1, Scenario scenario2) {
    for (EStructuralFeature feature : ScenarioExt.getElementOfInterestOnScenario()) {
      List<?> elts1 = (List<?>) scenario1.eGet(feature);
      List<?> elts2 = (List<?>) scenario2.eGet(feature);

      Assert.assertTrue(NLS.bind(Messages.differentNumberOfElement, new Object[] { String.valueOf(elts1.size()), feature.getName() }),
          elts1.size() == elts2.size());
    }
  }

  public static void compareOrdering(Scenario scenario1, Scenario scenario2) {
    compareOrder(scenario1, scenario2, InteractionPackage.Literals.SCENARIO__OWNED_INTERACTION_FRAGMENTS, new Comparator<EObject>() {
      public int compare(EObject arg0, EObject arg1) {
        if ((arg0 instanceof AbstractEnd) && (arg1 instanceof AbstractEnd)) {
          return AbstractEndExt.compareAbstractEnd((AbstractEnd) arg0, (AbstractEnd) arg1) ? 0 : -1;
        }
        return 0;
      }
    }, NLS.bind(Messages.wrongOrderingOfAbstractEnds, null));
    compareOrder(scenario1, scenario2, InteractionPackage.Literals.SCENARIO__OWNED_INSTANCE_ROLES, new Comparator<EObject>() {
      public int compare(EObject arg0, EObject arg1) {
        return AbstractEndExt.compareInstanceRole((InstanceRole) arg0, (InstanceRole) arg1) ? 0 : -1;
      }
    }, NLS.bind(Messages.wrongOrderingOfInstanceRoles, null));
  }

  protected static void compareOrder(Scenario scenario1, Scenario scenario2, EReference feature, Comparator<EObject> comparator, String message) {
    List<?> elts1 = (List<?>) scenario1.eGet(feature);
    List<?> elts2 = (List<?>) scenario2.eGet(feature);
    if (elts1.size() == elts2.size()) {
      for (int i = 0; i < elts1.size(); i++) {
        EObject ae1 = (EObject) elts1.get(i);
        EObject ae2 = (EObject) elts2.get(i);

        Assert.assertTrue(message, comparator.compare(ae1, ae2) == 0);
      }
    }
  }

  public static void checkMergeLink(Scenario sc) {
    checkLink(sc, InteractionPackage.Literals.MERGE_LINK, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, Messages.noMergeLink);
  }

  public static void checkRefinementLink(Scenario sc) {
    checkLink(sc, InteractionPackage.Literals.REFINEMENT_LINK, ModellingcorePackage.Literals.TRACEABLE_ELEMENT__OUTGOING_TRACES,
        ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT, Messages.noRefinementLink);
  }

  public static void checkLink(Scenario sc, EClass linkType, EStructuralFeature feature1, EStructuralFeature feature2, String errorMessagePattern) {

    // Link on scenario
    List<TraceableElement> list = ScenarioExt.hasLinkOftype(sc, linkType, feature1, feature2);
    TraceableElement result = list.isEmpty() ? null : list.get(0);

    Assert.assertNotNull(NLS.bind(errorMessagePattern, new Object[] { sc.eClass().getName(), sc.getName() }), result);

    List<EStructuralFeature> features = ScenarioExt.getElementOfInterestOnScenario();

    for (EStructuralFeature feature : features) {
      for (Object elt : (List<?>) sc.eGet(feature)) {
        list = ScenarioExt.hasLinkOftype((TraceableElement) elt, linkType, feature1, feature2);
        result = list.isEmpty() ? null : list.get(0);
        Assert.assertNotNull(
            NLS.bind(errorMessagePattern, new Object[] { ((EObject) elt).eClass().getName(),
                                                          (elt instanceof NamedElement ? ((NamedElement) elt).getName() : Messages.na) }), result);
      }
    }
  }
}
