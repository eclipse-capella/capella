/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.recrpl.ju.testcases;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.CreationHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.ActionContributionProvider;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.core.data.capellacommon.ChangeEvent;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;

/**
 * A helper to create all children offered by the "Add capella element..." context menu.
 */
public final class ChildCreationHelper {

  private ChildCreationHelper() {  }


/**
 * Creates all possible children for the given EObject. This simulates the behavior of the "Add Capella Element..."
 * context menu, which is different from what the item providers new child descriptors would tell you.
 *
 * @param parent the parent element
 * @return the newly created children of the parent
 */
  public static Collection<EObject> createAllChildren(EObject parent, Filter filter){

    AdapterFactoryEditingDomain domain = (AdapterFactoryEditingDomain) TransactionUtil.getEditingDomain(parent);
    Collection<?> childDescriptors = domain.getNewChildDescriptors(parent, null);

    Collection<EObject> createdChildren = new ArrayList<EObject>();

    for (Object o : childDescriptors) {

      EClass objectClass = ((EObject) ((CommandParameter) o).getValue()).eClass();
      EReference feature = ((CommandParameter) o).getEReference();

      if (!internalFilter(parent, objectClass, feature) && !filter.filter(parent, objectClass, feature)) {

        Command c = CreateChildCommand.create(domain, parent, o, Collections.emptyList());
        domain.getCommandStack().execute(c);

        EObject created = (EObject) c.getResult().iterator().next();
        createdChildren.add(created);

        if (!(created instanceof ChangeEvent)) { // https://bugs.polarsys.org/show_bug.cgi?id=1826
          CreationHelper.performContributionCommands(created);
        }
      }
    }

    return createdChildren;
  }


  @FunctionalInterface
  /**
   * Allows to skip over certain possible child elements.
   */
  interface Filter {
    /**
     * Should createChildren skip this clazz/feature/parent combination? Returns false by default,
     * so that all possible children are created.
     * @param parent
     * @param childClazz
     * @param reference
     * @return
     */
    boolean filter(EObject parent, EClass childClazz, EReference reference);
  }


  // this tries to mimic the filtering behavior of which elements can actually be created
  // under the given element from DynamicCreationAction and its friends.. which is.. a mess.
  private static boolean internalFilter(EObject container, EClass clazz, EStructuralFeature feature) {
    if (CapellacorePackage.Literals.RELATIONSHIP.isSuperTypeOf(clazz)
        || OaPackage.Literals.COMMUNICATION_MEAN.isSuperTypeOf(clazz)
        || FaPackage.Literals.FUNCTIONAL_CHAIN_INVOLVEMENT.isSuperTypeOf(clazz)
    ) {
      return true;
    }

    for (IMDEMenuItemContribution contribution : ActionContributionProvider.getInstance().getAllActionContributions(clazz)) {
      if (!contribution.selectionContribution((ModelElement)container, clazz, feature)) {
        return true;
      }
    }
    return false;
  }


}
