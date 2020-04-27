/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.ui.toolkit.decomposition;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;

import org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer;

/**
 */
public class DecompositionSourceViewer extends FieldsViewer {

  private TreeViewer _sourceTreeViewer;

  /**
   * @param parent_p
   */
  public DecompositionSourceViewer(Composite parent_p) {
    super(parent_p);
  }

  /**
   * Adds listeners to the tree and its shell to dispose the light bulb during synthesis check
   */
  public void addSourceTreeLightbulbListener() {
    _sourceTreeViewer.getControl().getShell().addControlListener(new ControlListener() {

      public void controlMoved(ControlEvent event_p) {
        refreshItems();
      }

      public void controlResized(ControlEvent event_p) {
        // do nothing
      }
    });
    _sourceTreeViewer.getTree().addFocusListener(new FocusListener() {

      public void focusGained(FocusEvent arg0_p) {
        // do nothing
      }

      public void focusLost(FocusEvent arg0_p) {
        // do nothing
      }
    });
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#createControl(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void createControl(Composite parent_p) {
    super.createControl(parent_p);
    parent_p.setLayout(new FillLayout());
    Group group = new Group(parent_p, SWT.CENTER | SWT.SHADOW_NONE);
    group.setText(Messages.getString("LCDecompGeneralViewer.currentlc")); //$NON-NLS-1$

    GridLayout layout = new GridLayout();
    layout.marginHeight = 5;
    layout.marginWidth = 5;
    _sourceTreeViewer = new TreeViewer(group, SWT.SINGLE | SWT.VIRTUAL | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);

    group.setLayout(layout);

    _sourceTreeViewer.setAutoExpandLevel(2);
    _sourceTreeViewer.getControl().setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, true));
    _sourceTreeViewer.setColumnProperties(new String[] { "Component" }); //$NON-NLS-1$
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#getInput()
   */
  @Override
  public Object getInput() {
    return null;
  }

  /**
   * @return the sourceTreeViewer
   */
  public TreeViewer getSourceTreeViewer() {
    return _sourceTreeViewer;
  }

  public void refreshItems() {
    _sourceTreeViewer.refresh(true);
    _sourceTreeViewer.setSelection(null, true);
    _sourceTreeViewer.getTree().notifyListeners(SWT.Selection, new Event());
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.FieldsViewer#setInput(java.lang.Object)
   */
  @Override
  public void setInput(Object input_p) {
    // do nothing
  }

  /**
   * @param sourceTreeViewer_p the sourceTreeViewer to set
   */
  public void setSourceTreeViewer(TreeViewer sourceTreeViewer_p) {
    _sourceTreeViewer = sourceTreeViewer_p;
  }

}
