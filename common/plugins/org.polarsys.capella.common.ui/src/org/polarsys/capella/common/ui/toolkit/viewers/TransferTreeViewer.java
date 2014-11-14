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
package org.polarsys.capella.common.ui.toolkit.viewers;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import org.polarsys.capella.common.ui.toolkit.viewers.transfer.AbstractTransferViewer2;

/**
 * Implementation of an {@link AbstractTransferViewer} where both left and right viewers are {@link TreeViewer}.<br>
 * Implementors must have to provide {@link ITreeContentProvider} and {@link ILabelProvider} according to {@link TreeViewer}.
 */
public class TransferTreeViewer extends AbstractTransferViewer2 {
  /**
   * Default TreeViewer style bits constant.
   */
  private final static int DEFAULT_TREE_VIEWER_STYLE = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER;

  /**
   * Constructs the transfer tree viewer. TreeViewers used as left and right viewers are instantiated with default {@link TreeViewer} style bits.
   * @param parent_p parent composite of this viewer.
   * @param style_p style of this viewer.
   */
  public TransferTreeViewer(Composite parent_p, int style_p) {
    this(parent_p, style_p, DEFAULT_TREE_VIEWER_STYLE, DEFAULT_TREE_VIEWER_STYLE);
  }

  /**
   * Constructs the transfer tree viewer.
   * @param parent_p parent composite of this viewer.
   * @param style_p style of this viewer.
   * @param leftViewerStyleBits_p style bits used for the left tree viewer.
   * @param rightViewerStyleBits_p style bits used for the right tree viewer.
   */
  public TransferTreeViewer(Composite parent_p, int style_p, int leftViewerStyleBits_p, int rightViewerStyleBits_p) {
    super(parent_p, style_p, leftViewerStyleBits_p, rightViewerStyleBits_p);
  }

  /**
   * @see AbstractTransferViewer#doLeftViewer(AbstractTransferViewer)
   */
  @Override
  protected StructuredViewer doLeftViewer(Composite parent_p) {
    return new TreeViewer(parent_p, getLeftViewerStyleBits());
  }

  /**
   * @see AbstractTransferViewer#doRightViewer(AbstractTransferViewer)
   */
  @Override
  protected StructuredViewer doRightViewer(Composite parent_p) {
    return new TreeViewer(parent_p, getRightViewerStyleBits());
  }
}
