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

package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2;

/**
 * Implementation of an {@link AbstractTransferViewer} where both left and right viewers are {@link ListViewer}.<br>
 * Implementors must have to provide {@link IContentProvider} and {@link ILabelProvider} according to {@link ListViewer}.
 */
public class TransferListViewer extends AbstractTransferViewer2 {
  /**
   * Default list viewer style bits constant.
   */
  private final static int DEFAULT_LIST_VIEWER_STYLE = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;

  /**
   * Constructs the transfer list viewer. ListViewers used as left and right viewers are instantiated with default {@link ListViewer} style bits.
   * @param parent parent composite of this viewer.
   * @param style style of this viewer.
   */
  public TransferListViewer(Composite parent, int style) {
    this(parent, style, DEFAULT_LIST_VIEWER_STYLE, DEFAULT_LIST_VIEWER_STYLE);
  }

  /**
   * Constructs the transfer list viewer.
   * @param parent parent composite of this viewer.
   * @param style style of this viewer.
   * @param leftViewerStyleBits style bits used for the left list viewer.
   * @param rightViewerStyleBits style bits used for the right list viewer.
   */
  public TransferListViewer(Composite parent, int style, int leftViewerStyleBits, int rightViewerStyleBits) {
    super(parent, style, leftViewerStyleBits, rightViewerStyleBits);
    DefaultListContentProvider provider = new DefaultListContentProvider();
    setLeftContentProvider(provider);
    setRightContentProvider(provider);

    LabelProvider labelProvider = new LabelProvider();
    setLeftLabelProvider(labelProvider);
    setRightLabelProvider(labelProvider);
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractTransferViewer#doLeftViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected StructuredViewer doLeftViewer(Composite parent) {
    return new ListViewer(parent, getLeftViewerStyleBits());
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.viewers.AbstractTransferViewer#doRightViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected StructuredViewer doRightViewer(Composite parent) {
    return new ListViewer(parent, getRightViewerStyleBits());
  }
}
