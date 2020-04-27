/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.validation.rules.ju.testcases.naming;

import java.util.Arrays;
import java.util.List;

import org.polarsys.capella.core.data.capellacore.Type;
import org.polarsys.capella.core.data.information.DataPkg;
import org.polarsys.capella.core.data.information.ExchangeItem;
import org.polarsys.capella.core.data.information.ExchangeItemElement;
import org.polarsys.capella.core.data.information.InformationFactory;
import org.polarsys.capella.core.data.information.datatype.BooleanType;
import org.polarsys.capella.core.data.information.datatype.DatatypeFactory;
import org.polarsys.capella.core.data.information.datatype.NumericType;
import org.polarsys.capella.test.validation.rules.ju.testcases.AbstractSimpleValidationTest;

/**
 * Test name conflict constraints on ExchangeItems.<br>
 * In general, two ExchangeItems can have the same name if their ExchangeItemElements help to disambiguate them by:<br>
 * - having a different number of ExchangeItemElements<br>
 * - ExchangeItemElement types are different<br>
 * - different names of ExchangeItemElements do NOT disambiguate the owning ExchangeItems<br>
 */
public class ExchangeItemTests extends AbstractSimpleValidationTest {

  @Override
  protected List<String> getRuleIDs() {
    return Arrays.asList(new String[] {
        "org.polarsys.capella.core.data.core.validation.I_08",
        "org.polarsys.capella.core.data.core.validation.I_19"});
  }

  /**
   * Two ExchangeItems with identical names but different ExchangeItemElement types is allowed.
   */
  public void exchangeItem1() {

    BooleanType bool = DatatypeFactory.eINSTANCE.createBooleanType();
    NumericType number = DatatypeFactory.eINSTANCE.createNumericType();

    ExchangeItem testOk1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testOk2 = InformationFactory.eINSTANCE.createExchangeItem();

    final DataPkg pkgOk = InformationFactory.eINSTANCE.createDataPkg();

    pkgOk.getOwnedExchangeItems().add(testOk1);
    pkgOk.getOwnedExchangeItems().add(testOk2);

    testOk1.setName("wololo"); //$NON-NLS-1$
    testOk2.setName("wololo"); //$NON-NLS-1$

    createExchangeItemElement(testOk1, bool);
    createExchangeItemElement(testOk2, number);

    ok(pkgOk);
  }

  /**
   * Two ExchangeItems with identical names, but a different number of ExchangeItemElements is allowed.
   */
  public void exchangeItem2() {
    BooleanType bool = DatatypeFactory.eINSTANCE.createBooleanType();
    DataPkg pkgOk = InformationFactory.eINSTANCE.createDataPkg();
    ExchangeItem testOk1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testOk2 = InformationFactory.eINSTANCE.createExchangeItem();
    testOk1.setName("wololo"); //$NON-NLS-1$
    testOk2.setName("wololo"); //$NON-NLS-1$

    createExchangeItemElement(testOk1, bool);

    pkgOk.getOwnedExchangeItems().add(testOk1);
    pkgOk.getOwnedExchangeItems().add(testOk2);

    ok(pkgOk);

  }

  /**
   * Two ExchangeItems with the same name and identical ExchangeItemElement types are allowed, if the ExchangeItemElements are declared in a different order.
   */
  public void exchangeItem3() {

    BooleanType bool = DatatypeFactory.eINSTANCE.createBooleanType();
    NumericType number = DatatypeFactory.eINSTANCE.createNumericType();

    // same name, same elements but in different order
    DataPkg pkgOk = InformationFactory.eINSTANCE.createDataPkg();
    ExchangeItem testOk1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testOk2 = InformationFactory.eINSTANCE.createExchangeItem();
    testOk1.setName("wololo"); //$NON-NLS-1$
    testOk2.setName("wololo"); //$NON-NLS-1$

    createExchangeItemElement(testOk1, bool);
    createExchangeItemElement(testOk1, number);

    createExchangeItemElement(testOk2, number);
    createExchangeItemElement(testOk2, bool);

    pkgOk.getOwnedExchangeItems().add(testOk1);
    pkgOk.getOwnedExchangeItems().add(testOk2);
    ok(pkgOk);
  }

  /**
   * Two ExchangeItems with the same name, and the same order and type of ExchangeItemElements are not allowed. Note how this holds true, even if the
   * ExchangeItemElement names are different.
   */
  public void exchangeItem4() {

    BooleanType bool = DatatypeFactory.eINSTANCE.createBooleanType();
    NumericType number = DatatypeFactory.eINSTANCE.createNumericType();

    DataPkg pkgKo = InformationFactory.eINSTANCE.createDataPkg();
    ExchangeItem testKo1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testKo2 = InformationFactory.eINSTANCE.createExchangeItem();
    testKo1.setName("wololo"); //$NON-NLS-1$
    testKo2.setName("wololo"); //$NON-NLS-1$

    createExchangeItemElement(testKo1, bool);
    createExchangeItemElement(testKo1, number);

    createExchangeItemElement(testKo2, bool);
    createExchangeItemElement(testKo2, number);

    pkgKo.getOwnedExchangeItems().add(testKo1);
    pkgKo.getOwnedExchangeItems().add(testKo2);

    ko(pkgKo);
  }

  /**
   * Two exchange items that have different names is allowed.
   */
  public void exchangeItem5() {
    // different name, no elements
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    ExchangeItem testOk1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testOk2 = InformationFactory.eINSTANCE.createExchangeItem();
    testOk1.setName("wololo"); //$NON-NLS-1$
    testOk2.setName("wululu"); //$NON-NLS-1$
    pkg.getOwnedExchangeItems().add(testOk1);
    pkg.getOwnedExchangeItems().add(testOk2);
    ok(pkg);
  }

  /**
   * Two ExchangeItems that have the same name and no ExchangeItemElements is not allowed.
   * @return
   */
  public void exchangeItem6() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    ExchangeItem testOk1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testOk2 = InformationFactory.eINSTANCE.createExchangeItem();
    testOk1.setName("wololo"); //$NON-NLS-1$
    testOk2.setName("wololo"); //$NON-NLS-1$
    pkg.getOwnedExchangeItems().add(testOk1);
    pkg.getOwnedExchangeItems().add(testOk2);
    ko(pkg);
  }

  /**
   * Two ExchangeItems that have the same name but different capitalization and no ExchangeItemElements is not allowed.
   */
  public void exchangeItem7() {
    DataPkg pkg = InformationFactory.eINSTANCE.createDataPkg();
    ExchangeItem testKo1 = InformationFactory.eINSTANCE.createExchangeItem();
    ExchangeItem testKo2 = InformationFactory.eINSTANCE.createExchangeItem();
    testKo1.setName("wOlOlO"); //$NON-NLS-1$
    testKo2.setName("WoLoLo"); //$NON-NLS-1$
    pkg.getOwnedExchangeItems().add(testKo1);
    pkg.getOwnedExchangeItems().add(testKo2);
    ko(pkg);
  }

  // create and attaches a new ExchangeItemElement with the given type to the given ExchangeItem
  protected void createExchangeItemElement(final ExchangeItem owner, final Type t) {
    ExchangeItemElement elem = InformationFactory.eINSTANCE.createExchangeItemElement();
    elem.setName("dummy" + owner.getOwnedElements().size()); //$NON-NLS-1$
    elem.setAbstractType(t);
    owner.getOwnedElements().add(elem);
  }

  @Override
  public void test() throws Exception {
    exchangeItem1();
    exchangeItem2();
    exchangeItem3();
    exchangeItem4();
    exchangeItem5();
    exchangeItem6();
    exchangeItem7();
  }

}
