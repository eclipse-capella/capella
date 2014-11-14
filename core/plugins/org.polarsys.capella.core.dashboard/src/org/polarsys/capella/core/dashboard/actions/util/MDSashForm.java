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
package org.polarsys.capella.core.dashboard.actions.util;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Sash;
import org.eclipse.ui.forms.FormColors;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.IManagedForm;

/**
 * Nice Sash Form.<br>
 * Copied from org.eclipse.ui.forms.MasterDetailsBlock.MDSashForm.
 */
public class MDSashForm extends SashForm {
  /**
   * Sashes created.
   */
  private ArrayList<Object> _sashes = new ArrayList<Object>(0);
  /**
   * Listener.
   */
  private Listener listener = new Listener() {
    @SuppressWarnings("synthetic-access")
    public void handleEvent(Event e) {
      switch (e.type) {
        case SWT.MouseEnter:
          e.widget.setData("hover", Boolean.TRUE); //$NON-NLS-1$
          ((Control) e.widget).redraw();
        break;
        case SWT.MouseExit:
          e.widget.setData("hover", null); //$NON-NLS-1$
          ((Control) e.widget).redraw();
        break;
        case SWT.Paint:
          onSashPaint(e);
        break;
        case SWT.Resize:
          hookSashListeners();
        break;
      }
    }
  };

  /**
   * Constructor.
   * @param parent
   * @param style
   */
  public MDSashForm(Composite parent, int style) {
    super(parent, style);
  }

  /**
   * @return the listener
   */
  public Listener getListener() {
    return listener;
  }

  private void hookSashListeners() {
    purgeSashes();
    Control[] children = getChildren();
    for (int i = 0; i < children.length; i++) {
      if (children[i] instanceof Sash) {
        Sash sash = (Sash) children[i];
        if (_sashes.contains(sash))
          continue;
        sash.addListener(SWT.Paint, listener);
        sash.addListener(SWT.MouseEnter, listener);
        sash.addListener(SWT.MouseExit, listener);
        _sashes.add(sash);
      }
    }
  }

  @Override
  public void layout(boolean changed) {
    super.layout(changed);
    hookSashListeners();
  }

  @Override
  public void layout(Control[] children) {
    super.layout(children);
    hookSashListeners();
  }

  private void onSashPaint(Event e) {
    Sash sash = (Sash) e.widget;
    IManagedForm form = (IManagedForm) sash.getParent().getData("form"); //$NON-NLS-1$
    FormColors colors = form.getToolkit().getColors();
    boolean vertical = (sash.getStyle() & SWT.VERTICAL) != 0;
    GC gc = e.gc;
    Boolean hover = (Boolean) sash.getData("hover"); //$NON-NLS-1$
    gc.setBackground(colors.getColor(IFormColors.TB_BG));
    gc.setForeground(colors.getColor(IFormColors.TB_BORDER));
    Point size = sash.getSize();
    if (vertical) {
      if (hover != null)
        gc.fillRectangle(0, 0, size.x, size.y);
    } else {
      if (hover != null)
        gc.fillRectangle(0, 0, size.x, size.y);
    }
  }

  private void purgeSashes() {
    for (Iterator<Object> iter = _sashes.iterator(); iter.hasNext();) {
      Sash sash = (Sash) iter.next();
      if (sash.isDisposed())
        iter.remove();
    }
  }
}
