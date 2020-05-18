/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.common.flexibility.wizards.renderer;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.constants.ICommonConstants;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

public class ComboRenderer extends AbstractRenderer {

  private ComboViewer viewer;

  Label label;

  public Label createPartLabel(Composite parent, String text) {
    label = new Label(parent, 0);
    label.setText(text);
    return label;
  }
  
  @Override
  public void initialize(final IProperty property, final IRendererContext rendererContext) {
    viewer.setInput(property);
    viewer.setSelection(new StructuredSelection(rendererContext.getPropertyContext().getCurrentValue(property)));

    viewer.addSelectionChangedListener(new ISelectionChangedListener() {

      @Override
      public void selectionChanged(SelectionChangedEvent event) {
        Object value = ((IStructuredSelection) event.getSelection()).getFirstElement();
        updatedValue(property, rendererContext, value);
        changeValue(property, rendererContext, value);
      }
    });
  }
  
  @Override
  public void performRender(Composite parent, IRendererContext context) {
    
    if (isDescription() && !Boolean.FALSE.equals(context.getParameter(ICommonConstants.PARAMETER_RENDER_LABEL))) {
      createPartLabel(parent, context.getProperty(this).getName() + ICommonConstants.COLON_CHARACTER);
    }
    viewer = new ComboViewer(parent);

    GridData data = new GridData(SWT.FILL, SWT.FILL, true, false);
    viewer.getControl().setLayoutData(data);

    viewer.setContentProvider(new RestraintPropertyContentProvider(context.getPropertyContext()));
  }

  protected boolean isDescription() {
    return true;
  }

  @Override
  public void dispose(IRendererContext context) {
    viewer.getCombo().dispose();
    label.dispose();
    super.dispose(context);
  }

}
