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
package org.polarsys.capella.test.model.ju.crossreferencer;

import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.polarsys.capella.common.data.modellingcore.AbstractType;
import org.polarsys.capella.common.data.modellingcore.AbstractTypedElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;

/**
 */
public class TypeDerivedFeaturesTest extends AbstractReflectiveCrossReferencerTest {
  /**
   * @see org.polarsys.capella.test.model.crossreferencer.ju.test.AbstractReflectiveCrossReferencerTest#getRootTypes()
   */
  @Override
  protected List<EClass> getRootTypes() {
    return Arrays.asList(ModellingcorePackage.Literals.ABSTRACT_TYPE,
        ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT);
  }

  /**
   * Test for all possible values for {@link AbstractType} and {@link AbstractTypedElement}.
   * 
   * @throws Exception
   */
  public void testTypeDerivedFeaturesForAll() throws Exception {
    final int abstractTypesCount = getSubTypesCount(ModellingcorePackage.Literals.ABSTRACT_TYPE);
    final int abstractTypedElementsCount = getSubTypesCount(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT);
    assertTrue("Ensure AbstractTypes were found", abstractTypesCount > 0);
    System.out.println("AbstractType Types count = " + abstractTypesCount);
    assertTrue("Ensure AbstractTypedElements were found", abstractTypedElementsCount > 0);
    System.out.println("AbstractTypedElement Types count = " + abstractTypedElementsCount);
    executeTestCommand(new AbstractReadWriteCommand() {
      /**
       * @see java.lang.Runnable#run()
       */
      @Override
      public void run() {
        // Cycle through abstract types.
        for (int abstractTypePosition = 0; abstractTypePosition < abstractTypesCount; abstractTypePosition++) {
          AbstractType type = (AbstractType) createType(
              getSubType(ModellingcorePackage.Literals.ABSTRACT_TYPE, abstractTypePosition));
          for (int abstractTypedElementPosition = 0; abstractTypedElementPosition < abstractTypedElementsCount; abstractTypedElementPosition++) {
            AbstractTypedElement typedElement = (AbstractTypedElement) createType(
                getSubType(ModellingcorePackage.Literals.ABSTRACT_TYPED_ELEMENT, abstractTypedElementPosition));
            typedElement.setAbstractType(type);
            assertEquals("Ensure typed element is valid", typedElement, type.getAbstractTypedElements().get(0));
            // Free memory.
            removeFromResource(typedElement);
          }
          // Free memory.
          removeFromResource(type);
        }
      }
    });
  }

  @Override
  public void test() throws Exception {
    testTypeDerivedFeaturesForAll();
  }
}
