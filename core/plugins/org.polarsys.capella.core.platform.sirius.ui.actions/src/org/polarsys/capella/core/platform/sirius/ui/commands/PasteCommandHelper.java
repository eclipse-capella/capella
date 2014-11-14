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

  public static IStatus createPasteCommands(Collection<?> pasteElements_p, CompoundCommand commands_p, EObject owner_p, EStructuralFeature feature_p,
      EditingDomain domain_p, int index_p, boolean useIndex_p) {
    IStatus status = Status.OK_STATUS;
    EStructuralFeature feature = feature_p;
    SharedCopyPasteElements instance = SharedCopyPasteElements.getInstance();

    for (Object pasteElement : pasteElements_p) {
      Object originalObject = instance.getOriginalObject(instance.getPasteCopyOfCopiedObject(pasteElement));
      if (null == originalObject) {
        originalObject = instance.getOriginalObject(pasteElement);
        if (null == originalObject) {
          // Cut case only.
          originalObject = pasteElement;
        }
      }

      EStructuralFeature containingFeature = ((EObject) originalObject).eContainingFeature();
      List<EReference> ownerContainments = owner_p.eClass().getEAllContainments();
      if (ownerContainments.contains(containingFeature)) {
        feature = containingFeature;
      }
      boolean append = true;
      // Check original object and the new owner are in the same session.
      Session session = SessionManager.INSTANCE.getSession(owner_p);
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
        if (null != feature) {
          // Is a many feature ?
          if (feature.isMany()) {
            // Use AddCommand.
            command =
                (useIndex_p) ? AddCommand.create(domain_p, owner_p, feature, Collections.singletonList(pasteElement), index_p) : AddCommand.create(domain_p,
                    owner_p, feature, Collections.singletonList(pasteElement));
          } else {
            // Not many : use a SetCommand.
            command =
                (useIndex_p) ? SetCommand.create(domain_p, owner_p, feature, pasteElement, index_p) : SetCommand.create(domain_p, owner_p, feature,
                    pasteElement);
          }
        } else {
          // Unknown feature : EMF will computes automatically the appropriate feature; it seems to handle correctly all type of feature : many or not.
          command = AddCommand.create(domain_p, owner_p, null, pasteElement);
        }
        if (null != command) {
          commands_p.append(command);
        } else {
          // It was not possible to create the command
          status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.CapellaPasteCommand_error_command, null);
          commands_p.append(UnexecutableCommand.INSTANCE);
        }
      } else {
        // Different session and no self contained element
        status = new Status(IStatus.ERROR, Activator.PLUGIN_ID, Messages.CapellaPasteCommand_error_session, null);
        commands_p.append(UnexecutableCommand.INSTANCE);
      }
    }
    return status;
  }

}
