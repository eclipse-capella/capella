/*******************************************************************************
 * Copyright (c) 2020 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.tools.report.util;

import org.eclipse.core.runtime.QualifiedName;

public class IJobConstants {

  /**
   * If a job has this given property, its status will be logged even if it is not an Error or a Warning
   * @see org.eclipse.core.runtime.jobs.Job#setProperty
   */
  public static final QualifiedName ALWAYS_LOG_STATUS = new QualifiedName("org.polarsys.capella.common.tools.report", "INFO"); //$NON-NLS-1$

}
