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
package org.polarsys.capella.core.command.recorder.ui.view;

import java.io.File;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import org.polarsys.capella.common.command.recorder.core.manager.AbstractRecorderManager;
import org.polarsys.capella.common.command.recorder.core.writer.TXTRecordReader;
import org.polarsys.capella.common.command.recorder.ui.view.AbstractRecorderView;
import org.polarsys.capella.core.command.recorder.ui.handler.CapellaRecorderHandler;
import org.polarsys.capella.core.command.recorder.ui.messages.CapellaRecorderUIMessages;

/**
 *
 */
public class CapellaRecorderView extends AbstractRecorderView {

  protected ITreeContentProvider _contentProvider;
  protected ITableLabelProvider _labelProvider;
  
  /**
   * 
   */
  public CapellaRecorderView() {
   
    _contentProvider = new CapellaRecorderContentProvider();
    _labelProvider = new CapellaRecorderLabelProvider();
    
  }
  
  /**
   * 
   * {@inheritDoc}
   */
  @Override
  protected TreeViewer createTreeViewer(Composite parent_p) {
  
    TreeViewer treeViewer= new TreeViewer(parent_p);
  
    Tree tree = treeViewer.getTree();
    tree.setHeaderVisible(true);
    tree.setLinesVisible(true);
    
    
    TreeColumn column1 = new TreeColumn(tree, SWT.LEFT);
    column1.setText(CapellaRecorderUIMessages.capellaRecorderView_column1);
    column1.setWidth(300);
    TreeColumn column2 = new TreeColumn(tree, SWT.CENTER);
    column2.setText(CapellaRecorderUIMessages.capellaRecorderView_column2);
    column2.setWidth(200);
  
    return treeViewer; 
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  protected ITreeContentProvider getContentProvider() {
    return _contentProvider;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ITableLabelProvider getLabelProvider() {
    return _labelProvider;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean loadRecord(File file_p) {
    
    TXTRecordReader reader = new TXTRecordReader(file_p);
    
    reader.parse();
    
    _treeViewer.setInput(reader.getData());
    
    // FIXME
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected AbstractRecorderManager getRecorderManager() {
    return (AbstractRecorderManager) CapellaRecorderHandler.INSTANCE.getRecorder();
  }
  
}
