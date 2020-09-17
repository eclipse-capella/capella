/*******************************************************************************
 * Copyright (c) 2018, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.validation.ui.ide.quickfix;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.ef.ExecutionManager;
import org.polarsys.capella.common.ef.command.AbstractReadWriteCommand;
import org.polarsys.capella.common.helpers.TransactionHelper;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.platform.sirius.ui.commands.CapellaDeleteCommand;

/**
 * A resolver for org.eclipse.emf.ecore.4 style markers (Unresolved proxy (reference) to an element).
 * 
 * It also deletes the referencing element if it is a known technical one
 * 
 * Works for EMF.4 (invalid proxy reference) or proxy references on EMF.16 (invalid values on opposite features)
 */
public class DanglingReferenceResolver extends AbstractCapellaMarkerResolution {

  private static List<EStructuralFeature> FEATURES = Arrays.asList(
      InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENDED,
      InteractionPackage.Literals.ABSTRACT_CAPABILITY_EXTEND__EXTENSION,
      InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUB,
      InteractionPackage.Literals.ABSTRACT_CAPABILITY_GENERALIZATION__SUPER,
      InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUDED,
      InteractionPackage.Literals.ABSTRACT_CAPABILITY_INCLUDE__INCLUSION,
      CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__DEPLOYED_ELEMENT,
      CsPackage.Literals.ABSTRACT_DEPLOYMENT_LINK__LOCATION,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__SOURCE_ELEMENT,
      ModellingcorePackage.Literals.ABSTRACT_TRACE__TARGET_ELEMENT,
      CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATED_ITEM,
      CsPackage.Literals.EXCHANGE_ITEM_ALLOCATION__ALLOCATING_INTERFACE,
      CsPackage.Literals.INTERFACE_IMPLEMENTATION__IMPLEMENTED_INTERFACE,
      CsPackage.Literals.INTERFACE_IMPLEMENTATION__INTERFACE_IMPLEMENTOR,
      CapellacorePackage.Literals.INVOLVEMENT__INVOLVED, CapellacorePackage.Literals.INVOLVEMENT__INVOLVER,
      CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUPER, CapellacorePackage.Literals.GENERALIZABLE_ELEMENT__SUB,
      CsPackage.Literals.INTERFACE_USE__INTERFACE_USER, CsPackage.Literals.INTERFACE_USE__USED_INTERFACE);

  @Override
  public void run(IMarker[] markers, IProgressMonitor monitor) {
    final Set<EObject> toDelete = new HashSet<EObject>();
    final Set<IMarker> toDeleteMarker = new HashSet<IMarker>();

    ExecutionManager em = getManager(markers);
    AbstractReadWriteCommand abstrctCommand = new AbstractReadWriteCommand() {
      @Override
      public void run() {
        for (IMarker marker : markers) {
          List<?> data = getValues(marker);
          if (data != null) {
            EObject object = (EObject) data.get(0);
            EStructuralFeature ref = (EStructuralFeature) data.get(1);
            EObject proxy = (EObject) data.get(2);

            if (!ref.isDerived() && ref.isChangeable()) {
              if (cleanupReference(object, ref, proxy)) {
                if (implyDeletionOfElement(object, ref, proxy)) {
                  toDelete.add(object);
                }
                toDeleteMarker.add(marker);
              }
            } else {
              // If derived, this marker is not relevant (but raised by EMF anyhow)
              // it's highly probable marker to be fixed by another marker.
              toDeleteMarker.add(marker);
            }
          }
        }

        if (!toDelete.isEmpty()) {
          CapellaDeleteCommand command = new CapellaDeleteCommand(em, toDelete, false, false, true);
          if (command.canExecute()) {
            command.execute();
          }
        }
      }
    };

    if (em != null) {
      em.execute(abstrctCommand);
    }

    for (IMarker marker : toDeleteMarker) {
      deleteMarker(marker);
    }
  }

  @Override
  public void run(IMarker marker) {
    run(new IMarker[] { marker }, new NullProgressMonitor());
  }

  protected ExecutionManager getManager(IMarker[] markers) {
    EObject source = getModelElements(markers[0]).get(0);
    return TransactionHelper.getExecutionManager(source);
  }

  /**
   * Returns a list of values <EObject source, EStructuralFeature, EObject proxy> if the marker is a correct one.
   */
  protected List<?> getValues(IMarker marker) {
    Diagnostic diagnostic = marker.getAdapter(Diagnostic.class);
    List<?> data = null;
    if (diagnostic != null) {
      List<?> ddata = diagnostic.getData();
      if (ddata != null) {
        // EMF.4 returns <EObject source, EStructuralFeature, EObject proxy> whereas EMF.16 returns <EObject source,
        // EStructuralFeature, EObject referencedObject, EStructuralFeature opposite>
        if (ddata.size() == 3 || ddata.size() == 4) {
          if (ddata.get(0) instanceof EObject && ddata.get(1) instanceof EStructuralFeature
              && ddata.get(2) instanceof EObject && ((EObject) ddata.get(2)).eIsProxy()) {
            data = ddata;
          }
        }
      }
    }
    return data;
  }

  protected boolean implyDeletionOfElement(EObject object, EStructuralFeature ref, EObject proxy) {
    return FEATURES.contains(ref);
  }

  /**
   * Remove the given proxy on the object.eGet(feature)
   */
  protected boolean cleanupReference(EObject object, EStructuralFeature feature, EObject proxy) {
    if (feature.isMany()) {
      EList<?> values = (EList<?>) object.eGet(feature);
      values.remove(proxy);
      return true;
    }
    Object value = object.eGet(feature);
    if (proxy.equals(value)) {
      object.eSet(feature, null);
      return true;
    }
    return false;
  }
}
