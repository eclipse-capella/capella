/*******************************************************************************
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.test.diagram.filters.ju.sfbd;

import org.polarsys.capella.test.diagram.filters.ju.DiagramObjectFilterTestCase;

public abstract class FiltersForSFBD extends DiagramObjectFilterTestCase {

  protected final String CONTROL_NODE_ID = "b02541f9-ccc6-45ca-bbe2-cb3323e6ad67";
  protected final String PROPERTY_VALUES_ID = "a2f462b7-2c1f-4a73-b9fb-09c52e17fcba";

  @Override
  protected String getTestProjectName() {
    return "StandardDiagramFiltersModel";
  }

  @Override
  protected String getDiagramName() {
    return "[SFBD] Root System Function";
  }

}
