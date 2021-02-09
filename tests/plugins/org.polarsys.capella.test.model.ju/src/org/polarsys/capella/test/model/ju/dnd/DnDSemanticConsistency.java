/*******************************************************************************
 * Copyright (c) 2021 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.model.ju.dnd;

import java.util.Arrays;

import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.model.ju.model.SemanticDnd;

/**
 * This test checks MoveHelper.checkSemanticRules
 */
public class DnDSemanticConsistency extends SemanticDnd {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    functions(context);
    capabilities(context);
    interfaces(context);
    datatypes(context);
    chains(context);

  }

  private void chains(SessionContext context) {

    // involvment are not moveable
    assertFalse(canDnD(context, FUNCTIONAL_CHAIN_INVOLVEMENT_LINK_TO_FUNCTIONALEXCHANGE_1, FUNCTIONALCHAIN_2));

    // chains can be moved on same architecture, not on other architecture
    assertFalse(canDnD(context, FUNCTIONALCHAIN_1, ROOT_LOGICAL_FUNCTION));
    assertTrue(canDnD(context, FUNCTIONALCHAIN_2, SYSTEMFUNCTION_1));
  }

  private void datatypes(SessionContext context) {

    assertTrue(canDnD(context, ENUMERATIONLITERAL_1, ENUM2));
    assertFalse(canDnD(context, ENUMERATIONLITERAL_1, NUMERIC3));

    assertTrue(canDnD(context, LITERALBOOLEANVALUE_1, BOOLEANTYPE_5));
    assertFalse(canDnD(context, LITERALBOOLEANVALUE_1, NUMERIC3));
  }

  private void interfaces(SessionContext context) {

    // An interface can be moved on pkg of same layers
    assertTrue(canDnD(context, SI_USED_IN_LOGICAL, SYSTEM_SUBINTERFACEPKG));

    // An interface can be moved in previous layers
    assertTrue(canDnD(context, SI_USED_IN_LOGICAL, OPERATIONAL_INTERFACES));
    assertTrue(canDnD(context, SI_FREE, OPERATIONAL_INTERFACES));

    // An interface can be moved in next layers if there is no uses in previous layers
    assertTrue(canDnD(context, SI_USED_IN_LOGICAL, LOGICAL_INTERFACES));
    assertTrue(canDnD(context, SI_FREE, LOGICAL_INTERFACES));

    // An interface can be moved in next layers if there is no uses in previous layers
    assertFalse(canDnD(context, SI_USED_IN_LOGICAL, PHYSICAL_INTERFACES));
    assertTrue(canDnD(context, SI_FREE, PHYSICAL_INTERFACES));

    // A realized Interface cannot be moved (anywhere!)
    assertFalse(canDnD(context, SI_REALIZED, SYSTEM_SUBINTERFACEPKG));
    assertFalse(canDnD(context, SI_REALIZED, OPERATIONAL_INTERFACES));
    assertFalse(canDnD(context, SI_REALIZED, LOGICAL_INTERFACES));

    // Interfaces can't be moved on EPBS
    assertFalse(canDnD(context, SYSTEM_SUBINTERFACEPKG, EPBS_ARCHITECTURE));

  }

  private void capabilities(SessionContext context) {

    // Operational capabilities

    assertTrue(canDnD(context, OC, OP));
    assertFalse(canDnD(context, OC, SP));
    assertFalse(canDnD(context, OC, LP));
    assertFalse(canDnD(context, OC, PP));
    assertFalse(canDnD(context, OC, EP));

    assertTrue(canDnD(context, OP, OP2));
    assertFalse(canDnD(context, OP, SP));
    assertFalse(canDnD(context, OP, LP));
    assertFalse(canDnD(context, OP, PP));
    assertFalse(canDnD(context, OP, EP));

    // Capabilities

    assertFalse(canDnD(context, SC, OP));
    assertTrue(canDnD(context, SC, SP));
    assertFalse(canDnD(context, SC, LP));
    assertFalse(canDnD(context, SC, PP));
    assertFalse(canDnD(context, SC, EP));

    assertFalse(canDnD(context, SP, OP));
    assertTrue(canDnD(context, SP, SP2));
    assertFalse(canDnD(context, SP, LP));
    assertFalse(canDnD(context, SP, PP));
    assertFalse(canDnD(context, SP, EP));

    // Capabilities on LA (Logical Capabilities are apparently allowed to be moved on other layers)

    assertFalse(canDnD(context, LC, OP));
    assertFalse(canDnD(context, LC, SP));
    assertTrue(canDnD(context, LC, LP));
    assertTrue(canDnD(context, LC, PP));
    assertTrue(canDnD(context, LC, EP));

    assertFalse(canDnD(context, LP, OP));
    assertFalse(canDnD(context, LP, SP));
    assertTrue(canDnD(context, LP, LP2));
    assertTrue(canDnD(context, LP, PP));
    assertTrue(canDnD(context, LC, EP));

    // Capabilities on PA (Physical Capabilities are apparently allowed to be moved on other layers)

    assertFalse(canDnD(context, PC, OP));
    assertFalse(canDnD(context, PC, SP));
    assertTrue(canDnD(context, PC, LP));
    assertTrue(canDnD(context, PC, PP));
    assertTrue(canDnD(context, PC, EP));

    assertFalse(canDnD(context, PP, OP));
    assertFalse(canDnD(context, PP, SP));
    assertTrue(canDnD(context, PP, LP));
    assertTrue(canDnD(context, PP, PP2));
    assertTrue(canDnD(context, EP, EP));

    // Capabilities on EPBS (EPBS Capabilities are apparently allowed to be moved on other layers)

    assertFalse(canDnD(context, EC, OP));
    assertFalse(canDnD(context, EC, SP));
    assertTrue(canDnD(context, EC, LP));
    assertTrue(canDnD(context, EC, PP));
    assertTrue(canDnD(context, EC, EP));

    assertFalse(canDnD(context, EP, OP));
    assertFalse(canDnD(context, EP, SP));
    assertTrue(canDnD(context, EP, LP));
    assertTrue(canDnD(context, EP, PP));
    assertTrue(canDnD(context, EP, EP2));

  }

  private void functions(SessionContext context) {

    assertFalse(canDnD(context, ROOT_OPERATIONAL_ACTIVITY, EPBS_ARCHITECTURE));

    assertTrue(canDnD(context, OA, OAP)); // allowed on OAPkg
    assertFalse(canDnD(context, OA, SYSTEM_FUNCTIONS)); // not allowed on package from other layer
    assertFalse(canDnD(context, OA, ROOT_SYSTEM_FUNCTION)); // not allowed on function from other layer

    assertTrue(canDnD(context, OAP2, OAP)); // allowed on OAPkg
    assertFalse(canDnD(context, OAP, EPBS_ARCHITECTURE)); // not allowed on EPBS
    assertFalse(canDnD(context, OAP, ROOT_SYSTEM_FUNCTION)); // not allowed on function from other layer
    assertFalse(canDnD(context, OAP, SYSTEM_FUNCTIONS));// not allowed on package from other layer

  }

  protected boolean canDnD(SessionContext context, String source, String target) {
    return GuiActions.canDnD(context.getSemanticElement(target), Arrays.asList(context.getSemanticElement(source)));
  }

}
