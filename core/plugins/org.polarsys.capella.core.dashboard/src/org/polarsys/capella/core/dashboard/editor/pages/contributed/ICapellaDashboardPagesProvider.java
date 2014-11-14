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
package org.polarsys.capella.core.dashboard.editor.pages.contributed;

import org.polarsys.capella.core.dashboard.editor.pages.EpbsArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.LogicalArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.OperationalAnalysisDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.PhysicalArchitectureDashboardPage;
import org.polarsys.capella.core.dashboard.editor.pages.SystemAnalysisDashboardPage;

/**
 * Allow implementors to provide Capella Dashboard pages with specific contributions.
 */
public interface ICapellaDashboardPagesProvider {
  /**
   * Get contribution (e.g additional sections) to the Operational Analysis page.
   * {@link OperationalAnalysisDashboardPage}
   * @return <code>null</code> means no contribution for Operational Analysis page.
   */
  public ICapellaDashboardPageContribution getOperationalAnalysisContribution();

  /**
   * Get contribution (e.g additional sections) to the System Analysis page.
   * {@link SystemAnalysisDashboardPage}
   * @return <code>null</code> means no contribution for System Analysis page.
   */
  public ICapellaDashboardPageContribution getSystemAnalysisContribution();

  /**
   * Get contribution (e.g additional sections) to the Logical Architecture page.
   * {@link LogicalArchitectureDashboardPage}
   * @return <code>null</code> means no contribution for Logical Architecture page.
   */
  public ICapellaDashboardPageContribution getLogicalArchitectureContribution();

  /**
   * Get contribution (e.g additional sections) to the Physical Architecture page.
   * {@link PhysicalArchitectureDashboardPage}
   * @return <code>null</code> means no contribution for Physical Architecture page.
   */
  public ICapellaDashboardPageContribution getPhysicalArchitectureContribution();

  /**
   * Get contribution (e.g additional sections) to the EPBS Architecture page.
   * {@link EpbsArchitectureDashboardPage}
   * @return <code>null</code> means no contribution for EPBS Architecture page.
   */
  public ICapellaDashboardPageContribution getEPBSArchitectureContribution();
}
