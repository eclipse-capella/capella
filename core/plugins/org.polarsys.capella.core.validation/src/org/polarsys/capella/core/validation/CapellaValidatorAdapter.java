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
package org.polarsys.capella.core.validation;

import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.validation.model.IConstraintStatus;
import org.eclipse.emf.validation.service.IBatchValidator;
import org.polarsys.capella.common.data.activity.ActivityPackage;
import org.polarsys.capella.common.data.behavior.BehaviorPackage;
import org.polarsys.capella.common.data.modellingcore.ModellingcorePackage;
import org.polarsys.capella.common.helpers.validation.ConstraintStatusDiagnostic;
import org.polarsys.capella.common.re.RePackage;
import org.polarsys.capella.core.data.capellacommon.CapellacommonPackage;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.CapellamodellerPackage;
import org.polarsys.capella.core.data.cs.CsPackage;
import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.epbs.EpbsPackage;
import org.polarsys.capella.core.data.fa.FaPackage;
import org.polarsys.capella.core.data.information.InformationPackage;
import org.polarsys.capella.core.data.information.communication.CommunicationPackage;
import org.polarsys.capella.core.data.information.datatype.DatatypePackage;
import org.polarsys.capella.core.data.information.datavalue.DatavaluePackage;
import org.polarsys.capella.core.data.interaction.InteractionPackage;
import org.polarsys.capella.core.data.la.LaPackage;
import org.polarsys.capella.core.data.oa.OaPackage;
import org.polarsys.capella.core.data.pa.PaPackage;
import org.polarsys.capella.core.data.sharedmodel.SharedmodelPackage;

/**
 * A specialized validator adapter that handles rule-aware constraints.
 */
public class CapellaValidatorAdapter extends EValidatorAdapter {

  /**
     * 
     */
  public CapellaValidatorAdapter() {
  }

  /**
   * @see org.polarsys.capella.core.validation.EValidatorAdapter#appendDiagnostics(org.eclipse.core.runtime.IStatus,
   *      org.eclipse.emf.common.util.DiagnosticChain)
   */
  @Override
  protected void appendDiagnostics(IStatus status_p, DiagnosticChain diagnostics_p) {
    // Deal recursively with multi status.
    if (status_p.isMultiStatus()) {
      IStatus[] children = status_p.getChildren();
      for (IStatus element : children) {
        appendDiagnostics(element, diagnostics_p);
      }
    } else if (status_p instanceof IConstraintStatus) {
      diagnostics_p.add(new ConstraintStatusDiagnostic((IConstraintStatus) status_p));
    }

  }

  /**
   * @see org.polarsys.capella.core.validation.EValidatorAdapter#getValidator()
   */
  @Override
  public IBatchValidator getValidator() {
    return super.getValidator();
  }

  /**
   * 
   */
  public void initializeValidatorRegistry() {

    // capella meta-models
    EValidator.Registry.INSTANCE.put(CapellacommonPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(CapellacorePackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(CsPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(CtxPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(EpbsPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(FaPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(InformationPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(CommunicationPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(DatatypePackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(DatavaluePackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(InteractionPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(LaPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(CapellamodellerPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(OaPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(PaPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(SharedmodelPackage.eINSTANCE, this);
    // re meta-model
    EValidator.Registry.INSTANCE.put(RePackage.eINSTANCE, this);
    // shared meta-models
    EValidator.Registry.INSTANCE.put(ActivityPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(BehaviorPackage.eINSTANCE, this);
    EValidator.Registry.INSTANCE.put(ModellingcorePackage.eINSTANCE, this);

  }

  public void registerAdditionalPackages(List<? extends EPackage> packages) {
    for (EPackage ePkg : packages)
      EValidator.Registry.INSTANCE.put(ePkg, this);
  }
}
