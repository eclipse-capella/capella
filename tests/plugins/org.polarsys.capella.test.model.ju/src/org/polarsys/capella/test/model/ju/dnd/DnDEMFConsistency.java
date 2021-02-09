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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.polarsys.capella.common.ef.ExecutionManagerRegistry;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.core.data.oa.OaFactory;
import org.polarsys.capella.core.model.handler.helpers.HoldingResourceHelper;
import org.polarsys.capella.test.framework.context.SessionContext;
import org.polarsys.capella.test.framework.helpers.GuiActions;
import org.polarsys.capella.test.framework.helpers.SkeletonHelper;
import org.polarsys.capella.test.framework.model.GenericModel;
import org.polarsys.capella.test.model.ju.model.MiscModel;

/**
 * This test checks MoveHelper.checkEMFConsistency
 */
public class DnDEMFConsistency extends MiscModel {

  @Override
  public void test() throws Exception {
    Session session = getSessionForTestModel(getRequiredTestModels().get(0));
    SessionContext context = new SessionContext(session);

    // Ensure that MoveHelper doesn't allow drop of an architecture on an architecture
    // (even if obj.getOwnedMigratedElements might add it) (customisation on ItemProviderAdapter)
    assertFalse(canDnD(context, OA, SA));

    // Ensure that MoveHelper still allow some drop allowed by metamodel
    assertTrue(canDnD(context, LA__CAPABILITIES, PA__CAPABILITIES));

    // Ensure that MoveHelper doesn't allow drop of an object not related to any session
    assertFalse(canDnD(context.getSemanticElement(OA__OPERATIONAL_ACTIVITIES__ROOT_OA),
        OaFactory.eINSTANCE.createOperationalActivity()));

    // Ensure that MoveHelper doesn't allow drop of an object related to another editing domain
    assertFalse(canDnD(context.getSemanticElement(OA__OPERATIONAL_ACTIVITIES__ROOT_OA), createObjectAttached()));

    // Ensure that MoveHelper on unary reference. (If empty, allowed, if set, unallowed)
    assertTrue(canDnD(context, SA__INTERFACES, SA__SYSTEM));
    SkeletonHelper.createInterfacePkg(SA__SYSTEM, GenericModel.INTERFACE_PKG, context);
    assertFalse(canDnD(context, SA__INTERFACES, SA__SYSTEM));

    // Ensure that MoveHelper on reference with a fixed max cardinality (Association allows only 2 sub properties)
    SkeletonHelper.createAssociation(PA__DATA, GenericModel.ASSOCIATION_1, context);
    SkeletonHelper.createAssociation(PA__DATA, GenericModel.ASSOCIATION_2, context);
    SkeletonHelper.createAssociation(PA__DATA, GenericModel.ASSOCIATION_3, context);
    SkeletonHelper.createAssociationProperty(GenericModel.ASSOCIATION_1, "p11", context);
    SkeletonHelper.createAssociationProperty(GenericModel.ASSOCIATION_1, "p12", context);
    SkeletonHelper.createAssociationProperty(GenericModel.ASSOCIATION_2, "p2", context);

    assertFalse(canDnD(context, "p2", GenericModel.ASSOCIATION_1)); // on 2
    assertTrue(canDnD(context, "p11", GenericModel.ASSOCIATION_2)); // on 1
    assertTrue(canDnD(context, "p11", GenericModel.ASSOCIATION_3)); // on 0
  }

  /**
   * Create an Operational Activity attached to a domain
   */
  protected EObject createObjectAttached() {
    EObject[] result = new EObject[1];
    TransactionalEditingDomain domain = ExecutionManagerRegistry.getInstance().addNewManager().getEditingDomain();
    ExecutionManagerRegistry.getInstance().getExecutionManager(domain).execute(new AbstractReadWriteCommand() {
      @Override
      public void run() {
        result[0] = OaFactory.eINSTANCE.createOperationalActivity();
        HoldingResourceHelper.attachToHoldingResource(result[0], HoldingResourceHelper.getHoldingResource(domain));
      }
    });
    return result[0];
  }

  protected boolean canDnD(SessionContext context, String source, String target) {
    return canDnD(context.getSemanticElement(source), context.getSemanticElement(target));
  }
  
  protected boolean canDnD(EObject source, EObject target) {
    return GuiActions.canDnD(target, Arrays.asList(source));
  }

}
