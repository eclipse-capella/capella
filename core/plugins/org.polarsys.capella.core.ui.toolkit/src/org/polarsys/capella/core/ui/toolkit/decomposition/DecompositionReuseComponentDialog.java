/*******************************************************************************
 * Copyright (c) 2006, 2023 THALES GLOBAL SERVICES.
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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import org.polarsys.capella.common.ui.toolkit.widgets.MdeFilteredList;

/**
 */
public class DecompositionReuseComponentDialog extends PopupDialog {

  Text _text;
  List<Control> exclusions = new ArrayList<Control>();
  MdeFilteredList filteredList = null;
  List<DecompositionComponent> reusedComponents;

  /**
   * @param parent_p
   * @param shellStyle_p
   * @param takeFocusOnOpen_p
   * @param persistBounds_p
   * @param showDialogMenu_p
   * @param showPersistAction_p
   * @param titleText_p
   * @param infoText_p
   */
  public DecompositionReuseComponentDialog(Shell parent_p, int shellStyle_p, boolean takeFocusOnOpen_p, boolean persistBounds_p, boolean showDialogMenu_p,
      boolean showPersistAction_p, String titleText_p, String infoText_p, Text text_p, List<DecompositionComponent> reusedComponents_p) {
    super(parent_p, shellStyle_p, takeFocusOnOpen_p,persistBounds_p, persistBounds_p, showDialogMenu_p, showPersistAction_p, titleText_p, infoText_p);
    _text = text_p;
    reusedComponents = reusedComponents_p;

  }

  private List<Control> buildExclusionList(Control control) {
    List<Control> tmp = new ArrayList<Control>();
    if (!exclusions.contains(control)) {
      tmp.add(control);
      control.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
    }
    if (control instanceof Composite) {
      Control[] children = ((Composite) control).getChildren();
      for (Control cc : children) {
        tmp.addAll(buildExclusionList(cc));
      }
    }
    return tmp;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent_p) {
    filteredList = new MdeFilteredList(parent_p, SWT.CENTER, new LabelProvider(), true, false, true);
    filteredList.setFilter(_text.getText());
    filteredList.setElements(reusedComponents.toArray());
    exclusions.addAll(buildExclusionList(filteredList));

    filteredList.addSelectionListener(new SelectionListener() {

      public void widgetDefaultSelected(SelectionEvent event_p) {
        if (event_p.item != null) {
          _text.setText(filteredList.getSelection()[0].toString());
          _text.setData(filteredList.getSelection()[0]);
          _text.setFocus();
          close();
        }
      }

      @SuppressWarnings("synthetic-access")
      public void widgetSelected(SelectionEvent event_p) {
        if (event_p.item != null) {
          Object obj = filteredList.getSelection()[0];
          if (obj instanceof DecompositionComponent) {
            DecompositionComponent comp = (DecompositionComponent) obj;
            setInfoText(comp.getPath());
            filteredList.setToolTipText(comp.getPath());
          }
        }
      }

    });

    return filteredList;
  }

  @Override
  public Control createInfoTextArea(Composite parent_p) {
    Label label = (Label) super.createInfoTextArea(parent_p);
    Label lbl = new Label(parent_p, SWT.H_SCROLL | label.getStyle());
    lbl.setFont(label.getFont());
    lbl.setForeground(label.getForeground());
    lbl.setLayoutData(label.getLayoutData());
    lbl.setText(label.getText());
    return lbl;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#createTitleMenuArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createTitleMenuArea(Composite parent_p) {
    Control ctrl = super.createTitleMenuArea(parent_p);
    exclusions.addAll(buildExclusionList(ctrl));
    return ctrl;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#getBackgroundColorExclusions()
   */
  @SuppressWarnings("unchecked")
  @Override
  protected List getBackgroundColorExclusions() {
    exclusions.addAll(super.getBackgroundColorExclusions());
    return exclusions;
  }

  /**
   * @see org.eclipse.jface.dialogs.PopupDialog#getInitialLocation(org.eclipse.swt.graphics.Point)
   */
  @Override
  protected Point getInitialLocation(Point initialSize_p) {
    Rectangle rect = _text.getBounds();
    Point tmp = _text.getLocation();
    Point pt = _text.toDisplay(rect.x - tmp.x, rect.y - tmp.y);
    return pt;
  }

  /**
   * @param text_p
   */
  public void refreshList(String text_p) {
    filteredList.setFilter(text_p);
  }
}
