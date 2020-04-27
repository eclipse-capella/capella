/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
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

package org.polarsys.capella.core.model.helpers.query;

import org.polarsys.capella.core.data.capellamodeller.Project;
import org.polarsys.capella.common.data.modellingcore.ModelElement;

public interface IGetElementsQueries {
  public ModelElement getElementById(Project project, String id);
}
