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

package org.polarsys.capella.core.transition.common.launcher;

/**
 *
 */
public class IDefaultWorkflow {
  public static final String WORKFLOW__DEFAULT_ID = "transition.workflow";

  public static final String WORKFLOW_STEP__INITIALIZATION = "transition.workflow.init";

  public static final String WORKFLOW_STEP__TRANSPOSITION = "transition.workflow.transposer";

  public static final String WORKFLOW_STEP__DIFF_MERGE = "transition.workflow.diffmerge";

  public static final String WORKFLOW_STEP__FINALIZATION = "transition.workflow.finalization";

}
