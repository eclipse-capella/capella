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

package org.polarsys.capella.common.re.ui.subcommands.renderers;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 *
 */
public class DependenciesElementsRenderer extends SelectionElementsRenderer {

  @Override
  protected int getExpandLevel() {
    return AbstractTreeViewer.ALL_LEVELS;
  }

  @Override
  protected ISelection getInitialSelection(IRendererContext context) {
    Object value = context.getPropertyContext().getCurrentValue(context.getProperty(this));
    return new StructuredSelection(new ArrayList((Collection) value));
  }

}
