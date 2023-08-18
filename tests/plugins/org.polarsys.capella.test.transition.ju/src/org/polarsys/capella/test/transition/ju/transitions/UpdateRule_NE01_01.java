/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.transition.ju.transitions;

import java.util.Arrays;
import java.util.List;

import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.test.transition.ju.Messages;
import org.polarsys.capella.test.transition.ju.ProjectionTestUtils;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Test the contextual transition - Context00 - Context01
 * 
 * <pre>
 * Model is created with the following elements�
 * - Create SF1 with name, sum, desc
 * - Create SF2 with name, sum, desc
 * - Create SF3 with name, sum
 * - Perform functional transition on Root
 * - Delete SF1t
 * - Change SF2t name, sum, desc
 * - Remove SF3t sum, set desc
 * - Rename SF3t
 * - Delete SF2t name
 * 
 * 
 * Expected Result:\
 * - SF1t should have same attributes than SF1
 * - SF2t should have != attributes than SF2, but SF2.name
 * - SF3t should have sum but != desc,name
 * 
 * </pre>
 * 
 */
public class UpdateRule_NE01_01 extends TopDownTransitionTestCase {
  private String id_rootsf = "7340bc44-2706-4405-ab42-8a09577bdc7e";
  private String id_sf1 = "a96e33f5-fed6-47be-859c-1678e1c0b030";
  private String id_sf2 = "776a1291-37a1-4ecd-9fb4-bde40399c9ae";
  private String id_sf3 = "22231562-5e7a-4899-9ec7-693b2937a679";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList(getClass().getSimpleName());
  }

  @Override
  public void performTest() throws Exception {
    step1();
  }

  private void step1() {
    performFunctionalTransition(Arrays.asList(getObject(id_rootsf)));
    // ROOT SYSTEM FUNCTION must be transitioned
    AbstractFunction rootsf = getObject(id_rootsf);
    assertNotNull(NLS.bind(Messages.NullElement, "ROOT SYSTEM FUNCTION"), rootsf);
    NamedElement rootsft = ProjectionTestUtils.getAllocatingFunction(rootsf);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, rootsf.getName()), rootsft);

    // SF1 must be transitioned
    AbstractFunction sf1 = getObject(id_sf1);
    assertNotNull(NLS.bind(Messages.NullElement, "SF1"), sf1);
    NamedElement sf1t = ProjectionTestUtils.getAllocatingFunction(sf1);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf1.getName()), sf1t);

    assertTrue(
        NLS.bind(Messages.ShouldHaveSameAttribute,
            new Object[] { sf1.getName(), sf1t.getName(),
                ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() }),
        sf1.getName().equals(sf1t.getName()));
    assertTrue(
        NLS.bind(Messages.ShouldHaveSameAttribute,
            new Object[] { sf1.getName(), sf1t.getName(),
                CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName() }),
        sf1.getDescription().equals(sf1t.getDescription()));
    assertTrue(
        NLS.bind(Messages.ShouldHaveSameAttribute,
            new Object[] { sf1.getName(), sf1t.getName(),
                CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName() }),
        sf1.getSummary().equals(sf1t.getSummary()));

    // SF2 must be transitioned
    AbstractFunction sf2 = getObject(id_sf2);
    assertNotNull(NLS.bind(Messages.NullElement, "SF2"), sf2);
    NamedElement sf2t = ProjectionTestUtils.getAllocatingFunction(sf2);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf2.getName()), sf2t);

    assertTrue(
        NLS.bind(Messages.ShouldHaveSameAttribute,
            new Object[] { sf2.getName(), sf2t.getName(),
                ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() }),
        sf2.getName().equals(sf2t.getName()));
    assertFalse(
        NLS.bind(Messages.ShouldHaveDifferentAttribute,
            new Object[] { sf2.getName(), sf2t.getName(),
                CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName() }),
        sf2.getDescription().equals(sf2t.getDescription()));
    assertFalse(
        NLS.bind(Messages.ShouldHaveDifferentAttribute,
            new Object[] { sf2.getName(), sf2t.getName(),
                CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName() }),
        sf2.getSummary().equals(sf2t.getSummary()));

    // SF3 must be transitioned
    AbstractFunction sf3 = getObject(id_sf3);
    assertNotNull(NLS.bind(Messages.NullElement, "SF3"), sf3);
    NamedElement sf3t = ProjectionTestUtils.getAllocatingFunction(sf3);
    assertNotNull(NLS.bind(Messages.ShouldBeTransitioned, sf3.getName()), sf3t);

    assertFalse(
        NLS.bind(Messages.ShouldHaveDifferentAttribute,
            new Object[] { sf3.getName(), sf3t.getName(),
                ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME.getName() }),
        sf3.getName().equals(sf3t.getName()));
    assertFalse(
        NLS.bind(Messages.ShouldHaveDifferentAttribute,
            new Object[] { sf3.getName(), sf3t.getName(),
                CapellacorePackage.Literals.CAPELLA_ELEMENT__DESCRIPTION.getName() }),
        sf3t.getDescription().equals(sf3.getDescription()));
    assertTrue(
        NLS.bind(Messages.ShouldHaveSameAttribute,
            new Object[] { sf3.getName(), sf3t.getName(),
                CapellacorePackage.Literals.CAPELLA_ELEMENT__SUMMARY.getName() }),
        sf3.getSummary().equals(sf3t.getSummary()));
  }

}
