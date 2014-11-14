/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.menu.dynamic;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.menu.dynamic.contributions.ActionContributionProvider;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

/**
 */
public class CreationHelper {

  /**
   * Call the creation helper
   * @param createdElement_p
   * @return
   */
  public static EObject performContributionCommands(EObject createdElement_p) {
    EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    editingDomain.getCommandStack().execute(CreationHelper.getContributorsCommand(editingDomain, createdElement_p, createdElement_p.eContainer(), createdElement_p.eClass(), createdElement_p.eContainmentFeature()));
    return createdElement_p;
  }

  /**
   * Call the creation helper
   * @param createdElement_p
   * @param container_p
   * @return
   */
  public static EObject performContributionCommands(EObject createdElement_p, EObject container_p) {
    EditingDomain editingDomain = MDEAdapterFactory.getEditingDomain();
    editingDomain.getCommandStack().execute(CreationHelper.getContributorsCommand(editingDomain, createdElement_p, container_p, createdElement_p.eClass(), createdElement_p.eContainmentFeature()));
    return createdElement_p;
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * @param editingDomain_p
   * @param createdElement_p
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(EditingDomain editingDomain_p, ModelElement createdElement_p) {
    return getAdditionnalCommand(editingDomain_p, createdElement_p, createdElement_p.eContainer(), createdElement_p.eClass(),
        createdElement_p.eContainmentFeature(), createdElement_p.eClass().getName());
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * @param editingDomain_p
   * @param namedElement_p
   * @param owner_p
   * @param objectClass_p
   * @param feature_p
   * @param namingPrefix_p
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(final EditingDomain editingDomain_p, ModelElement namedElement_p, final EObject owner_p,
      final EClass objectClass_p, final EStructuralFeature feature_p) {
    return getAdditionnalCommand(editingDomain_p, namedElement_p, owner_p, objectClass_p, feature_p, namedElement_p.eClass().getName());
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * @param editingDomain_p
   * @param namedElement_p
   * @param owner_p
   * @param objectClass_p
   * @param feature_p
   * @param namingPrefix_p
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(final EditingDomain editingDomain_p, ModelElement namedElement_p, final EObject owner_p,
      final EClass objectClass_p, final EStructuralFeature feature_p, String namingPrefix_p) {
    StrictCompoundCommand cc = new StrictCompoundCommand();

    if (namedElement_p instanceof AbstractNamedElement) {
      // do the naming
      final Command namingCmd = getNamingCommand(editingDomain_p, (AbstractNamedElement) namedElement_p, (ModelElement) owner_p, feature_p, namingPrefix_p);
      cc.append(namingCmd);

      Command contributionCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = namingCmd.getResult();
          if (res.size() == 1) {
            Object createdElt = res.iterator().next();
            if (createdElt instanceof EObject) {
              // call all the execution contributors
              return getContributorsCommand(editingDomain_p, (EObject) createdElt, owner_p, objectClass_p, feature_p);
            }
          }
          return new IdentityCommand();
        }
      };
      cc.append(contributionCmd);
    } else {
      cc.append(new IdentityCommand());
    }

    return cc;
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * @param editingDomain_p
   * @param createdElement_p
   * @param namingPrefix_p
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(EditingDomain editingDomain_p, ModelElement createdElement_p, String namingPrefix_p) {
    return getAdditionnalCommand(editingDomain_p, createdElement_p, createdElement_p.eContainer(), createdElement_p.eClass(), createdElement_p
        .eContainmentFeature(), namingPrefix_p);
  }

  /**
   * This returns a composite command containing all the contributions. If no contributors were declared, returns an IdentityCommand.
   * @param editingDomain_p
   * @param createdElement_p
   * @param owner_p
   * @param objectClass_p
   * @param feature_p
   */
  public static CompoundCommand getContributorsCommand(EditingDomain editingDomain_p, EObject createdElement_p, EObject owner_p, EClass objectClass_p,
      EStructuralFeature feature_p) {
    CompoundCommand cmd = new CompoundCommand();

    for (IMDEMenuItemContribution contribution : ActionContributionProvider.getInstance().getAllActionContributions(objectClass_p)) {
      Command contributionCommand = contribution.executionContribution(editingDomain_p, (ModelElement) owner_p, (ModelElement) createdElement_p, feature_p);
      if (contributionCommand != null) {
        cmd.append(contributionCommand);
      }
    }

    if (cmd.isEmpty()) {
      cmd.append(new IdentityCommand());
    }

    return cmd;
  }

  /**
   * This returns a simple default naming command.
   * @param editingDomain_p
   * @param namedElement_p
   * @param owner_p
   * @param feature_p
   */
  public static Command getNamingCommand(EditingDomain editingDomain_p, AbstractNamedElement namedElement_p, ModelElement owner_p, EStructuralFeature feature_p) {
    return new SetCommand(editingDomain_p, namedElement_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, EcoreUtil2.getUniqueName(namedElement_p,
        owner_p, feature_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, namedElement_p.eClass().getName()));
  }

  /**
   * This returns a simple default naming command.
   * @param editingDomain_p
   * @param namedElement_p
   * @param owner_p
   * @param feature_p
   * @param prefix_p
   */
  public static Command getNamingCommand(EditingDomain editingDomain_p, AbstractNamedElement namedElement_p, ModelElement owner_p,
      EStructuralFeature feature_p, String prefix_p) {
    return new SetCommand(editingDomain_p, namedElement_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, EcoreUtil2.getUniqueName(namedElement_p,
        owner_p, feature_p, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, prefix_p));
  }
}
