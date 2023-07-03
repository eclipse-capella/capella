/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.sirius.ui.commands;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.polarsys.capella.common.model.copypaste.SharedCopyPasteElements;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.Component;
import org.polarsys.capella.core.data.cs.ComponentPkg;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.cs.Part;
import org.polarsys.capella.core.model.handler.helpers.CrossReferencerHelper;
import org.polarsys.capella.core.ui.toolkit.Activator;

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

  public static IStatus createPasteCommands(Collection<?> pasteElements, CompoundCommand commands, EObject owner,
      EStructuralFeature feature, EditingDomain domain, int index, boolean useIndex) {
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
        feat = getNewTargetContainingFeature((EObject) originalObject, owner, containingFeature);
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
            // Use are RecordingCommand instead of AddCommand to be sure undo is properly done(in case of cut).
            var featureCommand = feat;
            command = new RecordingCommand((TransactionalEditingDomain) domain) {
              @Override
              protected void doExecute() {
                ((EList) owner.eGet(featureCommand)).addAll(Collections.singletonList(pasteElement));
              }
            };
          } else {
            // Use are RecordingCommand instead of SetCommand to be sure undo is properly done(in case of cut).
            var featureCommand = feat;
            command = new RecordingCommand((TransactionalEditingDomain) domain) {
              @Override
              protected void doExecute() {
                owner.eSet(featureCommand, Collections.singletonList(pasteElement));
              }
            };
          }
        } else {
          // Unknown feature : EMF will computes automatically the appropriate feature; it seems to handle correctly all
          // type of feature : many or not.
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
   * @param newOwner
   * @param originalContainingFeature
   * @return
   */
  protected static EStructuralFeature getNewTargetContainingFeature(EObject originalObject, EObject newOwner,
      EStructuralFeature originalContainingFeature) {
    if (originalObject instanceof Part) {
      if (newOwner instanceof ComponentPkg
          && originalContainingFeature == CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES) {
        return CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS;
      } else if (newOwner instanceof Component
          && originalContainingFeature == CsPackage.Literals.COMPONENT_PKG__OWNED_PARTS) {
        return CapellacorePackage.Literals.CLASSIFIER__OWNED_FEATURES;
      }
    } // If I move a Component or a ComponentPkg ...
    else if (originalObject instanceof Component || originalObject instanceof ComponentPkg) {
      // ... between a Component container and a ComponentPkg container, then containing features with the same name can
      // be deduced
      if ((originalObject.eContainer() instanceof Component && newOwner instanceof ComponentPkg)
          || (originalObject.eContainer() instanceof ComponentPkg && newOwner instanceof Component)) {
        String originalFeatureName = originalContainingFeature.getName();
        Optional<EStructuralFeature> newFeatureOptional = newOwner.eClass().getEAllStructuralFeatures().stream()
            .filter(f -> f.getName().equals(originalFeatureName)).findAny();
        if (newFeatureOptional.isPresent()) {
          return newFeatureOptional.get();
        }
      }
    }
    return null;
  }

}
