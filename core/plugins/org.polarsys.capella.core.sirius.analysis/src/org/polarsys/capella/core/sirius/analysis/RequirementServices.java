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
package org.polarsys.capella.core.sirius.analysis;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;
import org.polarsys.capella.common.mdsofa.common.constant.ICommonConstants;
import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.cs.BlockArchitecture;
import org.polarsys.capella.core.data.fa.AbstractFunction;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionExt;
import org.polarsys.capella.core.data.helpers.fa.services.FunctionPkgExt;
import org.polarsys.capella.core.data.requirement.Requirement;
import org.polarsys.capella.core.model.helpers.BlockArchitectureExt;
import org.polarsys.capella.core.sirius.analysis.tool.StringUtil;

/**
 */
public class RequirementServices {
  
  private static RequirementServices service = null;

  /**
   * Returns the instance
   */
  public static RequirementServices getService() {
    if (service == null) {
      service = new RequirementServices();
    }
    return service;
  }
  

  /**
   * Retrieve label for a cell in the RF table
   */
  public String getRFLabel(EObject context_p, EObject  line, EObject column) {
    if (column != null && column instanceof AbstractFunction) {
      if (line instanceof Requirement) {
        for (AbstractTrace trace : ((Requirement)line).getIncomingTraces()) {
          if (column.equals(trace.getSourceElement())) {
            return "X"; //$NON-NLS-1$
          }
        }
      }
    }
    return ICommonConstants.EMPTY_STRING;
  }

  
  /**
   * Retrieve the name of the requirement
   */
  public String getRequirementElementLabel(NamedElement requirment_p) {
    if (requirment_p instanceof Requirement) {
      Requirement requirment = (Requirement)requirment_p;
      if (!StringUtil.isNullOrEmpty(requirment.getRequirementId())) {
        return NLS.bind(Messages.Requirement_Label, requirment.getRequirementId(), requirment.getName());
      }
    }
    return requirment_p.getName();
  }
  
  /** 
   * Retrieve column elements for the RF table
   */
  @SuppressWarnings("unchecked")
  public Collection<EObject> getRFColumnCandidates(EObject source_p) {
    BlockArchitecture architecture = BlockArchitectureExt.getRootBlockArchitecture(source_p);
    List<AbstractFunction> functions = FunctionExt.getAllAbstractFunctions(architecture);
    
    //Remove root functions
    if (architecture.getOwnedFunctionPkg() != null) {
      functions.removeAll(FunctionPkgExt.getOwnedFunctions(architecture.getOwnedFunctionPkg()));
    }
    return (Collection)functions;
  }
  
  
}
