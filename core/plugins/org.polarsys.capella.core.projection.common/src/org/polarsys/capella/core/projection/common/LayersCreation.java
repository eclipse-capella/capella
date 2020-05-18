/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.projection.common;

import java.util.ArrayList;
import java.util.List;

import org.polarsys.capella.core.data.ctx.CtxFactory;
import org.polarsys.capella.core.data.ctx.OperationalAnalysisRealization;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.epbs.EpbsFactory;
import org.polarsys.capella.core.data.epbs.PhysicalArchitectureRealization;
import org.polarsys.capella.core.data.la.LaFactory;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.SystemAnalysisRealization;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.LogicalArchitectureRealization;
import org.polarsys.capella.core.data.pa.PaFactory;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.model.helpers.SystemEngineeringExt;
import org.polarsys.capella.common.data.modellingcore.AbstractTrace;

public class LayersCreation {

	/**
	 * Return the 'LogicalArchitecture' package Layer
	 * The 'LogicalArchitecture' layer shall be create if doesn't exist
	 */
	static public List<LogicalArchitecture> getOrCreateLaLayers(SystemAnalysis ctx) {
		List<LogicalArchitecture> lst = new ArrayList<LogicalArchitecture>();

		for (AbstractTrace trace : ctx.getIncomingTraces()) {
			if ((trace instanceof SystemAnalysisRealization) && (trace.getSourceElement() instanceof LogicalArchitecture)) {
				lst.add((LogicalArchitecture) trace.getSourceElement());
			}
		}

		if (lst.isEmpty()) {
			LogicalArchitecture la = LaFactory.eINSTANCE.createLogicalArchitecture("Logical Architecture"); //$NON-NLS-1$
			SystemEngineering se = SystemEngineeringExt.getSystemEngineering(ctx);
			se.getOwnedArchitectures().add(la);

			SystemAnalysisRealization realisationLink = LaFactory.eINSTANCE.createSystemAnalysisRealization();
			realisationLink.setTargetElement(ctx);
			realisationLink.setSourceElement(la);
			la.getOwnedSystemAnalysisRealizations().add(realisationLink);

			lst.add(la);
		}
		return lst;
	}
	
	/**
   * Return the 'SystemAnalysis' package Layer
   * The 'SystemAnalysis' layer shall be create if doesn't exist
   */
  static public List<SystemAnalysis> getOrCreateSaLayers(OperationalAnalysis oa) {
    List<SystemAnalysis> lst = new ArrayList<SystemAnalysis>();

    for (AbstractTrace trace : oa.getIncomingTraces()) {
      if ((trace instanceof OperationalAnalysisRealization) && (trace.getSourceElement() instanceof SystemAnalysis)) {
        lst.add((SystemAnalysis) trace.getSourceElement());
      }
    }

    if (lst.isEmpty()) {
      SystemAnalysis sa = CtxFactory.eINSTANCE.createSystemAnalysis("System Analysis"); //$NON-NLS-1$
      SystemEngineering se = SystemEngineeringExt.getSystemEngineering(oa);
      se.getOwnedArchitectures().add(sa);
      
      OperationalAnalysisRealization realisationLink = CtxFactory.eINSTANCE.createOperationalAnalysisRealization();
      realisationLink.setTargetElement(oa);
      realisationLink.setSourceElement(se);
      sa.getOwnedOperationalAnalysisRealizations().add(realisationLink);
      lst.add(sa);
    }
    return lst;
  }
	
	
	
	/**
	 * Return the 'PhysicalArchitecture' package Layer
	 * The 'PhysicalArchitecture' layer shall be create if doesn't exist
	 */
	static public List<PhysicalArchitecture> getOrCreatePaLayers(LogicalArchitecture la) {
		List<PhysicalArchitecture> lst = new ArrayList<PhysicalArchitecture>();

		for (AbstractTrace trace : la.getIncomingTraces()) {
			if ((trace instanceof LogicalArchitectureRealization) && (trace.getSourceElement() instanceof PhysicalArchitecture)) {
				lst.add((PhysicalArchitecture) trace.getSourceElement());
			}
		}

		if (lst.isEmpty()) {
			PhysicalArchitecture pa = PaFactory.eINSTANCE.createPhysicalArchitecture("Physical Architecture"); //$NON-NLS-1$
			SystemEngineering se = SystemEngineeringExt.getSystemEngineering(la);
			se.getOwnedArchitectures().add(pa);

			LogicalArchitectureRealization realisationLink = PaFactory.eINSTANCE.createLogicalArchitectureRealization();
			realisationLink.setTargetElement(la);
			realisationLink.setSourceElement(pa);
			pa.getOwnedLogicalArchitectureRealizations().add(realisationLink);

			lst.add(pa);
		}

		return lst;
	}
	
	/**
	 * Return the 'EPBSArchitecture' package Layer
	 * The 'EPBSArchitecture' layer shall be create if doesn't exist
	 */
	static public List<EPBSArchitecture> getOrCreateEpbsLayers(PhysicalArchitecture pa) {
		List<EPBSArchitecture> lst = new ArrayList<EPBSArchitecture>();

		for (AbstractTrace trace : pa.getIncomingTraces()) {
			if ((trace instanceof PhysicalArchitectureRealization) && (trace.getSourceElement() instanceof EPBSArchitecture)) {
				lst.add((EPBSArchitecture) trace.getSourceElement());
			}
		}

		if (lst.isEmpty()) {
			EPBSArchitecture epbs = EpbsFactory.eINSTANCE.createEPBSArchitecture("Epbs Architecture"); //$NON-NLS-1$
			SystemEngineering se = SystemEngineeringExt.getSystemEngineering(pa);
			se.getOwnedArchitectures().add(epbs);

			PhysicalArchitectureRealization realisationLink = EpbsFactory.eINSTANCE.createPhysicalArchitectureRealization();
			realisationLink.setTargetElement(pa);
			realisationLink.setSourceElement(epbs);
			epbs.getOwnedPhysicalArchitectureRealizations().add(realisationLink);

			lst.add(epbs);
		}
		return lst;
	}

	
}
