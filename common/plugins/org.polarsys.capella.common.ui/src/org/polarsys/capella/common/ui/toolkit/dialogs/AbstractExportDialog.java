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

package org.polarsys.capella.common.ui.toolkit.dialogs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.polarsys.capella.common.helpers.export.DataExporter;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractContextMenuFiller;
import org.polarsys.capella.common.ui.toolkit.viewers.AbstractRegExpViewer;

/**
 * Dialog that offers an export functionality
 */
public abstract class AbstractExportDialog extends AbstractViewerDialog {

  private final static int EXPORT_ID = -100;

  private Object _data;

  private Viewer _viewer;

  private AbstractContextMenuFiller _contextMenuManagerFiller;

  private ExporterProvider _exporterProvider;

  private ExporterProvider getExporterProvider() {
    if (null == _exporterProvider) {
      _exporterProvider = new ExporterProvider();
    }
    return _exporterProvider;
  }

  /**
   * Constructor
   * @param parentShell
   * @param title
   * @param message
   * @param shellTitle
   * @see AbstractViewerDialog#AbstractViewerDialog(Shell, String, String, String)
   */
  public AbstractExportDialog(Shell parentShell, String title, String message, String shellTitle) {
    super(parentShell, title, message, shellTitle);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createButtonsForButtonBar(Composite parent) {
    // Export button
    createButton(parent, EXPORT_ID, Messages.exportButtonLabel, false);
    // create OK and Cancel buttons by default
    createButton(parent, IDialogConstants.CLOSE_ID, IDialogConstants.CLOSE_LABEL, false);
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#buttonPressed(int)
   */
  @Override
  protected void buttonPressed(int buttonId) {
    if (EXPORT_ID == buttonId) {
      exportPressed();
    } else if (IDialogConstants.CLOSE_ID == buttonId) {
      okPressed();
    } else {
      super.buttonPressed(buttonId);
    }
  }

  /**
   * The export button call-back
   */
  protected void exportPressed() {
    DataExporter dataExporter = new DataExporter(getExporterProvider());

    //
    // File Dialog setting
    //
    FileDialog fd = new FileDialog(getParentShell(), SWT.SAVE);
    fd.setText(Messages.fileBrowserDialogTitle);
    fd.setFileName(getDefaultFileName());
    fd.setFilterExtensions(dataExporter.getSupportedExtension());
    fd.setFilterNames(dataExporter.getSupportedDescription());

    //
    // File Dialog selection
    //
    String fileName = fd.open();

    
    int filterIndex = fd.getFilterIndex();
    if (filterIndex != -1 && (fileName != null)) {
      // If a filter was selected and file section was not canceled
      String selectedFilter = fd.getFilterExtensions()[filterIndex];
      String fileExtension = selectedFilter.substring(1);
      if (!fileName.endsWith(fileExtension)) {
        // If the filename doesn't already have the extension, append it
        fileName = fileName + fileExtension;
      }
    }
    
    //
    // The export operation itself
    //
    if (null != fileName) { // file selection was not canceled
      boolean result = dataExporter.exportToFile(fileName, getExportableData());
      if (result) {
        MessageDialog.openInformation(getParentShell(), Messages.exportMetrics, NLS.bind(Messages.exportOk, fileName));
      } else {
        MessageDialog.openError(getParentShell(), Messages.exportMetrics, NLS.bind(Messages.exportKo, fileName));
      }
    }
  }

  protected abstract List<String[]> getExportableData();

  protected List<String[]> getExportableData(String str) {
    Date date = new Date();
    List<String[]> result = new ArrayList<String[]>();

    result.add(new String[] { NLS.bind(Messages.exportRootFileNameLabel, str) });

    result.add(new String[] { NLS.bind(Messages.exportDateLabel, date) });

    result.add(IExportConstants.EXPORT_EMPTY_LINE);
    result.add(IExportConstants.EXPORT_EMPTY_LINE);

    return result;
  }

  /**
   * @param data the data to set
   */
  public void setData(Object data) {
    _data = data;
    if (null != _viewer) {
      _viewer.setInput(_data);
    }
  }

  public Object getData() {
    return _data;
  }

  /**
   * Set a context menu manager filler.
   * @param filler
   */
  public void setContextMenuManagerFiller(AbstractContextMenuFiller filler) {
    _contextMenuManagerFiller = filler;
  }

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#doCreateDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected void doCreateDialogArea(Composite parent) {
    GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
    gridData.heightHint = 300;
    parent.setLayoutData(gridData);
    AbstractRegExpViewer abstractViewer = createViewer(parent);

    // Install a context menu manager filler if any.
    if (null != _contextMenuManagerFiller) {
      abstractViewer.setContextMenuManagerFiller(_contextMenuManagerFiller);
    }

    _viewer = abstractViewer.getClientViewer();
    _viewer.setInput(_data);
    _viewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
  }

  /**
   * Create a 2 level tree.
   * @param parent the parent composite
   */
  protected abstract AbstractRegExpViewer createViewer(Composite parent);

  /**
   * @see org.polarsys.capella.common.ui.toolkit.dialogs.AbstractViewerDialog#getResult()
   */
  @Override
  protected Object getResult() {
    return null;
  }

  /**
   *
   */
  protected Viewer getViewer() {
    return _viewer;
  }
  
  protected String getDefaultFileName() {
    return "";//$NON-NLS-1$
  }
}
