/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.data.core.validation.capellaelement.nameconflict;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.oa.CommunicationMean;
import org.polarsys.capella.core.model.utils.CapellaSwitch;
import org.polarsys.capella.core.model.utils.NamingHelper;

/**
 * A name conflict constraint that works as follows:
 * 
 * For each (direct) child element of the current target, 
 * a pair (Slot, Signature) is computed. 
 * - Slots and Signatures are Objects and can be anything. 
 * - Typically, signatures will be Strings though.
 * 
 * A name conflict then exists between two elements, if we computed
 * the same slot and the same signature for both elements.
 * 
 * Slot and signature computation are delegated to EMF switches.
 * 
 * The validation will skip any objects that return a signature
 * which is null, or the empty string or case insensitively
 * equals "null".
 * 
 */
public class DefaultNameConflictConstraint extends AbstractModelConstraint {

  private CapellaSwitch<?> signatureSwitch;
  private CapellaSwitch<?> slotSwitch;
  
  public DefaultNameConflictConstraint(CapellaSwitch<?> signatureSwitch, CapellaSwitch<?> slotSwitch) {
    this.slotSwitch = slotSwitch;
    this.signatureSwitch = signatureSwitch; 
  }
  
  @Override
  public IStatus validate(IValidationContext ctx) {
    EMFEventType eType = ctx.getEventType();
    if (eType != EMFEventType.NULL) {
      return Status.OK_STATUS;
    }
    IStatus result = Status.OK_STATUS;
    EObject container = ctx.getTarget();
    // slot => ( signature => [ children ])
    Map<Object, Map<Object, List<EObject>>> symbols = new HashMap<Object, Map<Object, List<EObject>>>();

    for (EObject child : container.eContents()) {
      if (child instanceof CommunicationMean) {
        continue;
      }
      Object slot = slotSwitch.doSwitch(child);
      Map<Object, List<EObject>> slotEntries = symbols.get(slot);
      if (slotEntries == null) {
        slotEntries = new HashMap<Object, List<EObject>>();
        symbols.put(slot, slotEntries);
      }
      Object signature = signatureSwitch.doSwitch(child);

      if (signature != null && !signature.equals(ICommonConstants.EMPTY_STRING)) {

        if (signature instanceof String && (((String) signature).equalsIgnoreCase("null"))) { //$NON-NLS-1$
          continue;
        }

        List<EObject> objectsForSignature = slotEntries.get(signature);
        if (objectsForSignature == null) {
          objectsForSignature = new ArrayList<EObject>();
          slotEntries.put(signature, objectsForSignature);
        }
        objectsForSignature.add(child);
      }
    }

    List<IStatus> problems = new ArrayList<IStatus>();

    for (Map.Entry<Object, Map<Object, List<EObject>>> symbol : symbols.entrySet()) {
      Map<Object, List<EObject>> slotEntries = symbol.getValue();
      for (Map.Entry<Object, List<EObject>> slotEntry : slotEntries.entrySet()) {
        List<EObject> objectsForSignature = slotEntry.getValue();
        if (objectsForSignature.size() > 1) {
          ctx.addResults(objectsForSignature);
          IStatus status = doCreateFailureStatus(ctx, container, objectsForSignature);
          problems.add(status);
        }
      }
    }

    if (problems.size() > 0) {
      result = ConstraintStatus.createMultiStatus(ctx, problems);
    }

    return result;
  }

  protected IStatus doCreateFailureStatus(IValidationContext ctx, EObject container, List<EObject> conflictingChildren){
    
    StringBuilder builder = new StringBuilder();
    for (EObject child : conflictingChildren){
      builder.append(", "); //$NON-NLS-1$
      builder.append(NamingHelper.getValue(child, null));
    }
    String result = builder.substring(2);
    
    return ctx.createFailureStatus(NamingHelper.getValue(container, null), conflictingChildren.size(), result);
  }

    
}
