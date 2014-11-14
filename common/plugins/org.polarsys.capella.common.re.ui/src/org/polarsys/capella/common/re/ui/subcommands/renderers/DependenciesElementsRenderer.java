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
  protected ISelection getInitialSelection(IRendererContext context_p) {
    Object value = context_p.getPropertyContext().getCurrentValue(context_p.getProperty(this));
    return new StructuredSelection(new ArrayList((Collection) value));
  }

}
