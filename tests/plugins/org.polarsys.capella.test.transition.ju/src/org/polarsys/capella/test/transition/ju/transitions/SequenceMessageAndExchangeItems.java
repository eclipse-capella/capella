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

import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.test.transition.ju.TopDownTransitionTestCase;

/**
 * Check if ExchangeItem reference on SequenceMessage is correctly propagated through all scenario transition
 */
public class SequenceMessageAndExchangeItems extends TopDownTransitionTestCase {
  public static String TRANSITIONSOFEI__LA__ROOT_LF__FE1 = "48db4e37-6479-462b-9462-c320b1a511f9";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__FS2ES = "107cb1c6-b2d9-4d67-99dd-15e4f613fdc0";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__FS2ES__FE1 = "ca86ca90-0b68-45df-9090-29e04917fa71";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESB = "c4475b68-9c65-48a1-a4d4-3ef118494c06";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESB__FE1 = "9d368668-8268-4ecb-8b51-95e3f7cdfe13";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__ESB2ESB = "71ff34a9-0449-4995-a095-eb9ec5cabf03";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__ESB2ESB__CE1 = "c0e0ecec-9ec4-452b-bde8-8ae05aab44db";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESF = "740d8135-d769-421b-a71b-98db33ecd2dd";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESF__FE1 = "ea08b51f-3706-40b6-afa0-73bf61bbf0f2";
  public static String TRANSITIONSOFEI__LA__CE1 = "d7f6d72d-59f9-4c51-b1a7-5dd99c2e62e8";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__FS2FS = "9a2e8325-e2b9-422c-85f8-f980a43f40e7";
  public static String TRANSITIONSOFEI__LA__CAP__CR1__FS2FS__FE1 = "54b030de-3e2c-4d28-8a75-00c2dbc94d41";

  public static String TRANSITIONSOFEI__SA__INTERFACES__SEI1 = "71a76ace-6de1-473c-b413-dd4437016bff";
  public static String TRANSITIONSOFEI__SA__INTERFACES__SEI2 = "89943d97-621f-4dc0-bdf8-ef4d61fab2e3";
  public static String TRANSITIONSOFEI__LA__INTERFACES__LEI1 = "0802085d-b6bc-4e39-81f0-70cd11c11d7f";
  public static String TRANSITIONSOFEI__LA__INTERFACES__LEI2 = "aeaec85b-a289-46f6-ba8c-ad5a016085c6";
  public static String TRANSITIONSOFEI__PA__INTERFACES__INTERFACES___P_LEI1 = "830f9f28-62fd-4caf-9204-458e9f4887c7";

  @Override
  public List<String> getRequiredTestModels() {
    return Arrays.asList("CreateRule_Scenario");
  }

  @Override
  public void performTest() throws Exception {
    step1();
    step2();
    step3();
    step4();
    step5();
  }

  private void step1() {
    performFStoESTransition(Arrays.asList(getObject(TRANSITIONSOFEI__LA__CAP__CR1__FS2ES)));
    EObject sm = mustBeTransitioned(TRANSITIONSOFEI__LA__CAP__CR1__FS2ES__FE1);

    EObject sei1 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI1);
    EObject sei2 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI2);
    EObject lei1 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI1);
    EObject lei2 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI2);
    shouldExist(TRANSITIONSOFEI__PA__INTERFACES__INTERFACES___P_LEI1);

    mustBeLinkedTo(sm, sei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, sei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, lei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, lei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
  }

  private void step2() {
    performESFtoESBTransition(Arrays.asList(getObject(TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESB)));
    EObject sm = mustBeTransitioned(TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESB__FE1);

    EObject sei1 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI1);
    EObject sei2 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI2);
    EObject lei1 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI1);
    EObject lei2 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI2);
    shouldExist(TRANSITIONSOFEI__PA__INTERFACES__INTERFACES___P_LEI1);

    // CE1 don't exchange sei2 and lei2
    mustBeLinkedTo(sm, sei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustNotBeLinkedTo(sm, sei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, lei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustNotBeLinkedTo(sm, lei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
  }

  private void step3() {
    performEStoESTransition(Arrays.asList(getObject(TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESF)));
    EObject sm = mustBeTransitioned(TRANSITIONSOFEI__LA__CAP__CR1__ESF2ESF__FE1);

    EObject sei1 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI1);
    EObject sei2 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI2);
    EObject lei1 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI1);
    EObject lei2 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI2);
    EObject plei1 = shouldExist(TRANSITIONSOFEI__PA__INTERFACES__INTERFACES___P_LEI1);

    // FE on PA exchange all 4, it should be updated to plei1
    mustBeLinkedTo(sm, sei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, sei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustNotBeLinkedTo(sm, lei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, lei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, plei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
  }

  private void step4() {
    performEStoESTransition(Arrays.asList(getObject(TRANSITIONSOFEI__LA__CAP__CR1__ESB2ESB)));
    EObject sm = mustBeTransitioned(TRANSITIONSOFEI__LA__CAP__CR1__ESB2ESB__CE1);

    EObject sei1 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI1);
    EObject sei2 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI2);
    EObject lei1 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI1);
    EObject lei2 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI2);
    EObject plei1 = shouldExist(TRANSITIONSOFEI__PA__INTERFACES__INTERFACES___P_LEI1);

    // CE on PA exchange only plei1
    mustBeLinkedTo(sm, sei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustNotBeLinkedTo(sm, sei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustNotBeLinkedTo(sm, lei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustNotBeLinkedTo(sm, lei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, plei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
  }

  private void step5() {
    performFStoFSTransition(Arrays.asList(getObject(TRANSITIONSOFEI__LA__CAP__CR1__FS2FS)));
    EObject sm = mustBeTransitioned(TRANSITIONSOFEI__LA__CAP__CR1__FS2FS__FE1);

    EObject sei1 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI1);
    EObject sei2 = shouldExist(TRANSITIONSOFEI__SA__INTERFACES__SEI2);
    shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI1);
    EObject lei2 = shouldExist(TRANSITIONSOFEI__LA__INTERFACES__LEI2);
    EObject plei1 = shouldExist(TRANSITIONSOFEI__PA__INTERFACES__INTERFACES___P_LEI1);

    // FE on PA dont exchange sei2 and lei2
    mustBeLinkedTo(sm, sei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, sei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, plei1, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
    mustBeLinkedTo(sm, lei2, InteractionPackage.Literals.SEQUENCE_MESSAGE__EXCHANGED_ITEMS);
  }

}
