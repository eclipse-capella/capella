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

package org.polarsys.capella.core.model.skeleton;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.data.ctx.SystemAnalysis;
import org.polarsys.capella.core.data.ctx.SystemComponent;
import org.polarsys.capella.core.data.ctx.SystemFunction;
import org.polarsys.capella.core.data.epbs.EPBSArchitecture;
import org.polarsys.capella.core.data.la.LogicalArchitecture;
import org.polarsys.capella.core.data.la.LogicalComponent;
import org.polarsys.capella.core.data.la.LogicalFunction;
import org.polarsys.capella.core.data.oa.OperationalActivity;
import org.polarsys.capella.core.data.oa.OperationalAnalysis;
import org.polarsys.capella.core.data.pa.PhysicalArchitecture;
import org.polarsys.capella.core.data.pa.PhysicalComponent;

/**
 * The skeleton services.
 */
public interface ISkeletonServices {

  /**
   * Does the entire system engineering skeleton.
   * @param project_p The parent Capella project.
   * @param systemName_p The system engineering name.
   * @param engDomain_p The engineering domain.
   * @param isOpAnalysisRequired_p <code>True</code> if the operational analysis is required else <code>false</code>.
   * @return The system engineering skeleton.
   */
  public SystemEngineering doSystemEngineering(Project project_p, String systemName_p, EngineeringDomain engDomain_p, boolean isOpAnalysisRequired_p);

  /**
   * Does the operational analysis skeleton.
   * @param systemEng_p The system engineering.
   * @return The operational analysis skeleton.
   */
  public OperationalAnalysis doOperationalAnalysis(SystemEngineering systemEng_p);

  /**
   * Does the System Analysis skeleton.
   * @param systemEng_p The parent system engineering.
   * @param opAnalysis_p The operational analysis.
   * @param opActivity_p The operational activity.
   * @return The System Analysis skeleton.
   */
  public SystemAnalysis doSystemAnalysis(SystemEngineering systemEng_p, OperationalAnalysis opAnalysis_p, OperationalActivity opActivity_p);

  /**
   * Does the logical architecture skeleton.
   * @param systemEng_p The parent system engineering.
   * @param ctxArchitecture_p The System Analysis.
   * @param systemFunction_p The system function.
   * @param system_p The system.
   * @return The logical architecture skeleton.
   */
  public LogicalArchitecture doLogicalArchitecture(SystemEngineering systemEng_p, SystemAnalysis ctxArchitecture_p, SystemFunction systemFunction_p,
      SystemComponent system_p);

  /**
   * Does the physical architecture skeleton.
   * @param systemEng_p The parent system engineering.
   * @param logicalArchitecture_p The logical architecture.
   * @param logicalComponent_p The logical component.
   * @param logicalFunction_p The logical function.
   * @return The physical architecture skeleton.
   */
  public PhysicalArchitecture doPhysicalArchitecture(SystemEngineering systemEng_p, LogicalArchitecture logicalArchitecture_p,
      LogicalComponent logicalComponent_p, LogicalFunction logicalFunction_p);

  /**
   * Does the EPBS architecture skeleton.
   * @param systemEng_p The parent system engineering.
   * @param physicalArchitecture_p The physical architecture.
   * @return The EPBS architecture skeleton.
   */
  public EPBSArchitecture doEPBSArchitecture(SystemEngineering systemEng_p, PhysicalArchitecture physicalArchitecture_p, PhysicalComponent pc_p);
}
