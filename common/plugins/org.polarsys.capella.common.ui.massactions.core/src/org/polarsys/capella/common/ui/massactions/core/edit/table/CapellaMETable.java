/*******************************************************************************
 * Copyright (c) 2018, 2019 THALES GLOBAL SERVICES.
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

import org.eclipse.swt.widgets.Composite;
import org.polarsys.kitalpha.massactions.core.table.factory.IMAFactory;
import org.polarsys.kitalpha.massactions.edit.table.METable;

/**
 * An Capella Mass Edition extension of the default {@link METable}.
 * 
 * @author Sandu Postaru
 *
 */
public class CapellaMETable extends METable {

  public CapellaMETable(Composite parentView) {
    super(parentView);
  }

  @Override
  public IMAFactory createMAFactory() {
    return new CapellaMEFactory();
  }
}
