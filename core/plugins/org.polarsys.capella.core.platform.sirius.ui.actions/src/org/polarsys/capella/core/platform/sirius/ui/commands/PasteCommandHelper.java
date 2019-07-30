/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;

import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.core.ui.toolkit.Activator;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;

/**
 * Helper to ease paste commands to create when cut/copy/drag paste elements.
 */
public class PasteCommandHelper {
  /**
   * 
   */
  private PasteCommandHelper() {
    // Avoid to instantiate.
  }

  public static IStatus createPasteCommands(Collection<?> pasteElements, CompoundCommand commands, EObject owner, EStructuralFeature feature,
      EditingDomain domain, int index, boolean useIndex) {
    IStatus status = Status.OK_STATUS;
    EStructuralFeature feat = feature;
    SharedCopyPasteElements instance = SharedCopyPasteElements.getInstance();

    for (Object pasteElement : pasteElements) {
      Object originalObject = instance.getOriginalObject(instance.getPasteCopyOfCopiedObject(pasteElement));
      if (null == originalObject) {
        originalObject = instance.getOriginalObject(pasteElement);
        if (null == originalObject) {
          // Cut case only.
          originalObject = pasteElement;
        }
      }

      EStructuralFeature containingFeature = ((EObject) originalObject).eContainingFeature();
      List<EReference> ownerContainments = owner.eClass().getEAllContainments();
      if (ownerContainments.contains(containingFeature)) {
        feat = containingFeature;
      } else {
        feat = getNewTargetContainingFeature(originalObject, owner, containingFeature);
      }
      
      boolean append = true;
      // Check original object and the new owner are in the same session.
      Session session = SessionManager.INSTANCE.getSession(owner);
      if ((null != session) && !session.equals(SessionManager.INSTANCE.getSession((EObject) originalObject))) {
        // The paste is in other session so
        // check if the original object is self contained
        if (!CrossReferencerHelper.isSelfContained((EObject) originalObject, false)) {
          append = false;
        }
      }
      if (append) {
        Command command = null;
        // Is the feature identified ?
        if (null != feat) {
          // Is a many feature ?
          if (feat.isMany()) {
            // Use AddCommand.
            command =
                (useIndex) ? AddCommand.create(domain, owner, feat, Collections.singletonList(pasteElement), index) : AddCommand.create(domain,
                    owner, feat, Collections.singletonList(pasteElement));
          } else {
            // Not many : use a SetCommand.
            command =
                (useIndex) ? SetCommand.create(domain, owner, feat, pasteElement, index) : SetCommand.create(domain, owner, feat,
                    pasteElement);
          }
        } else {
          // Unknown feature : EMF will computes automatically the appropriate feature; it seems to handle correctly all type of feature : many or not.
          command = AddCommand.create(domain, owner, null, pasteElement);
        }
        if (null != command) {
          commands.append(command);
        } else {
          // It was not possible to create the command
          status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.CapellaPasteCommand_error_command, null);
          commands.append(UnexecutableCommand.INSTANCE);
        }
      } else {
        // Different session and no self contained element
        status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.CapellaPasteCommand_error_session, null);
        commands.append(UnexecutableCommand.INSTANCE);
      }
    }
    return status;
  }

  /**
   * In case the original containing feature is not the same as the target containing feature
   * 
   * @param originalObject
   * @param owner
   * @param containingFeature
   * @return
   */
  protected static EStructuralFeature getNewTargetContainingFeature(Object originalObject, EObject owner,
      EStructuralFeature containingFeature) {
    if (originalObject instanceof Part) {
      if (owner instanceof ComponentPkg
          && containingFeature == CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES) {
        return CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS;
      } else if (owner instanceof Component && containingFeature == CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS) {
        return CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
      }
    }
    return null;
  }

}
