/*******************************************************************************
 * Copyright (c) 2019, 2020 THALES GLOBAL SERVICES.
 * 
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 * 
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.ui.massactions.core.edit.table;

import org.eclipse.nebula.widgets.nattable.NatTable;
import org.eclipse.nebula.widgets.nattable.config.IConfigRegistry;
import org.polarsys.kitalpha.massactions.edit.config.MEConfiguration;
import org.polarsys.kitalpha.massactions.edit.table.factory.MEFactory;

public class CapellaMEFactory extends MEFactory {
  @Override
  public MEConfiguration createConfiguration(NatTable natTable, IConfigRegistry configRegistry) {
    return new CapellaMEConfiguration(natTable, configRegistry);
  }
}
