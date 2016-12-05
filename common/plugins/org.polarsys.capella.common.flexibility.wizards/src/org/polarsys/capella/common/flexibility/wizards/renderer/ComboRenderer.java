/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

public class ComboRenderer extends AbstractRenderer {

  private ComboViewer viewer;

  @Override
  public void initialize(final IProperty property, final IRendererContext rendererContext) {
    viewer.setInput(property);
    viewer.setSelection(new StructuredSelection(rendererContext.getPropertyContext().getCurrentValue(property)));

    viewer.addSelectionChangedListener(new ISelectionChangedListener() {

      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        changeValue(property, rendererContext, ((IStructuredSelection) event.getSelection()).getFirstElement());
      }
    });
  }

  @Override
  public void performRender(Composite parent, IRendererContext context) {
    viewer = new ComboViewer(parent);

    GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
    viewer.getControl().setLayoutData(data);

    viewer.setContentProvider(new RestraintPropertyContentProvider(context.getPropertyContext()));
  }

  @Override
  public void dispose(IRendererContext context) {
    viewer.getCombo().dispose();
    super.dispose(context);
  }

}
