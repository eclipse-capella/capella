/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.ui.PlatformUI;
import org.polarsys.capella.common.data.modellingcore.AbstractNamedElement;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.EcoreUtil2;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.common.menu.dynamic.contributions.ActionContributionProvider;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;
import org.polarsys.capella.common.menu.dynamic.util.INamePrefixService;

/**
 */
public class CreationHelper {

  private CreationHelper() {
    // avoid instantiation
  }

  /**
   * Call the creation helper
   * 
   * @param createdElement
   * @return
   */
  public static EObject performContributionCommands(EObject createdElement) {
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(createdElement);
    if (null != editingDomain) {
      editingDomain.getCommandStack().execute(CreationHelper.getContributorsCommand(editingDomain, createdElement,
          createdElement.eContainer(), createdElement.eClass(), createdElement.eContainmentFeature()));
    }
    return createdElement;
  }

  /**
   * Call the creation helper
   * 
   * @param createdElement
   * @param container
   * @return
   */
  public static EObject performContributionCommands(EObject createdElement, EObject container) {
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(createdElement);
    if (null != editingDomain) {
      editingDomain.getCommandStack().execute(CreationHelper.getContributorsCommand(editingDomain, createdElement,
          container, createdElement.eClass(), createdElement.eContainmentFeature()));
    }
    return createdElement;
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * 
   * @param editingDomain
   * @param createdElement
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(EditingDomain editingDomain, ModelElement createdElement) {
    INamePrefixService prefixService = PlatformUI.getWorkbench().getService(INamePrefixService.class);
    String createdElementPrefix = prefixService.getPrefix(createdElement);

    return getAdditionnalCommand(editingDomain, createdElement, createdElement.eContainer(), createdElement.eClass(),
        createdElement.eContainmentFeature(), createdElementPrefix);
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * 
   * @param editingDomain
   * @param namedElement
   * @param owner
   * @param objectClass
   * @param feature
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(final EditingDomain editingDomain,
      ModelElement namedElement, final EObject owner, final CommandParameter descriptor,
      final EStructuralFeature feature) {

    EObject element = (EObject) descriptor.getValue();
    EClass eClass = element.eClass();
    INamePrefixService prefixService = PlatformUI.getWorkbench().getService(INamePrefixService.class);

    String elementPrefix = prefixService.getPrefix(element);

    return getAdditionnalCommand(editingDomain, namedElement, owner, eClass, feature, elementPrefix);
  }

  /**
   * This returns a composite command containing the naming command, followed by all the contribution commands.
   * 
   * @param editingDomain
   * @param namedElement
   * @param owner
   * @param objectClass
   * @param feature
   * @param namingPrefix
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(final EditingDomain editingDomain,
      ModelElement namedElement, final EObject owner, final EClass objectClass, final EStructuralFeature feature,
      String namingPrefix) {
    StrictCompoundCommand cc = new StrictCompoundCommand();

    if (namedElement instanceof AbstractNamedElement) {
      // do the naming
      final Command namingCmd = getNamingCommand(editingDomain, (AbstractNamedElement) namedElement,
          (ModelElement) owner, feature, namingPrefix);
      cc.append(namingCmd);

      Command contributionCmd = new CommandWrapper() {
        @Override
        public Command createCommand() {
          Collection<?> res = namingCmd.getResult();
          if (res.size() == 1) {
            Object createdElt = res.iterator().next();
            if (createdElt instanceof EObject) {
              // call all the execution contributors
              return getContributorsCommand((EObject) createdElt, owner, objectClass, feature);
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
   * 
   * @param editingDomain
   * @param createdElement
   * @param namingPrefix
   * @return
   */
  public static StrictCompoundCommand getAdditionnalCommand(EditingDomain editingDomain, ModelElement createdElement,
      String namingPrefix) {
    return getAdditionnalCommand(editingDomain, createdElement, createdElement.eContainer(), createdElement.eClass(),
        createdElement.eContainmentFeature(), namingPrefix);
  }

  /**
   * This returns a composite command containing all the contributions.<br/>
   * If no contributors were declared, returns an IdentityCommand.
   * 
   * @param createdElement
   * @param owner
   * @param objectClass
   * @param feature
   */
  public static CompoundCommand getContributorsCommand(EObject createdElement, EObject owner, EClass objectClass,
      EStructuralFeature feature) {
    EditingDomain editingDomain = TransactionHelper.getEditingDomain(owner != null ? owner : createdElement);
    return getContributorsCommand(editingDomain, createdElement, owner, objectClass, feature);
  }

  /**
   * This returns a composite command containing all the contributions.<br/>
   * If no contributors were declared, returns an IdentityCommand.
   * 
   * @param editingDomain
   * @param createdElement
   * @param owner
   * @param objectClass
   * @param feature
   */
  private static CompoundCommand getContributorsCommand(EditingDomain editingDomain, EObject createdElement,
      EObject owner, EClass objectClass, EStructuralFeature feature) {
    CompoundCommand cmd = new CompoundCommand();

    for (IMDEMenuItemContribution contribution : ActionContributionProvider.getInstance()
        .getAllActionContributions(objectClass)) {
      Command contributionCommand = contribution.executionContribution(editingDomain, (ModelElement) owner,
          (ModelElement) createdElement, feature);
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
   * 
   * @param editingDomain
   * @param namedElement
   * @param owner
   * @param feature
   */
  public static Command getNamingCommand(EditingDomain editingDomain, AbstractNamedElement namedElement,
      ModelElement owner, EStructuralFeature feature) {
    return new SetCommand(editingDomain, namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        EcoreUtil2.getUniqueName(namedElement, owner, feature,
            ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, namedElement.eClass().getName()));
  }

  /**
   * This returns a simple default naming command.
   * 
   * @param editingDomain
   * @param namedElement
   * @param owner
   * @param feature
   * @param prefix
   */
  public static Command getNamingCommand(EditingDomain editingDomain, AbstractNamedElement namedElement,
      ModelElement owner, EStructuralFeature feature, String prefix) {
    return new SetCommand(editingDomain, namedElement, ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME,
        EcoreUtil2.getUniqueName(namedElement, owner, feature,
            ModellingcorePackage.Literals.ABSTRACT_NAMED_ELEMENT__NAME, prefix));
  }
}
