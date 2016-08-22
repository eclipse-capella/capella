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
package org.polarsys.capella.core.data.menu.contributions.ctx;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

import org.polarsys.capella.core.data.ctx.CtxPackage;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.capellacore.CapellacorePackage;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.skeleton.helpers.SAStructureHelper;
import org.polarsys.capella.common.data.modellingcore.ModelElement;
import org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution;

/**
 */
public class SystemAnalysisItemContribution implements IMDEMenuItemContribution {

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#selectionContribution()
   */
  public boolean selectionContribution(ModelElement modelElement, EClass cls, EStructuralFeature feature) {
    if ((modelElement instanceof SystemEngineering)
      && CtxPackage.Literals.SYSTEM_ANALYSIS.equals(cls)
      && CapellacorePackage.Literals.ABSTRACT_MODELLING_STRUCTURE__OWNED_ARCHITECTURES.equals(feature))
    {
      return ((SystemEngineering) modelElement).getContainedSystemAnalysis().isEmpty();
    }
    return false;
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#executionContribution()
   */
  public Command executionContribution(EditingDomain editingDomain, ModelElement containerElement, ModelElement createdElement, EStructuralFeature feature) {
    if ((createdElement instanceof SystemAnalysis) && (containerElement instanceof SystemEngineering)) {
      return SAStructureHelper.getSystemAnalysisCreationCmd(editingDomain, (SystemEngineering) containerElement, (SystemAnalysis) createdElement);
    }
    return new IdentityCommand();
  }

  /**
   * @see org.polarsys.capella.common.menu.dynamic.contributions.IMDEMenuItemContribution#getMetaclass()
   */
  public EClass getMetaclass() {
    return CtxPackage.Literals.SYSTEM_ANALYSIS;
  }
}
