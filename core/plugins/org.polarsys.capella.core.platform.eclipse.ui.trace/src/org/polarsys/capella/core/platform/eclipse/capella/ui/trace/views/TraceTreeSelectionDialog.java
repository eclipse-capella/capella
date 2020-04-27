/*******************************************************************************
 * Copyright (c) 2006, 2014 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

import org.polarsys.capella.core.data.capellacore.NamedElement;
import org.polarsys.capella.core.data.capellamodeller.SystemEngineering;
import org.polarsys.capella.core.model.helpers.query.CapellaQueries;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.MDTrace;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.Messages;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.messages.TraceUtil;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.CapellaModelFilter;
import org.polarsys.capella.core.platform.eclipse.capella.ui.trace.views.providers.IImageKeys;
import org.polarsys.capella.common.data.modellingcore.TraceableElement;

/**
 */
public class TraceTreeSelectionDialog extends ElementTreeSelectionDialog {

  private Text _statusBarText;
  private TraceableElement _currentElement;

  private CapellaModelFilter filter = new CapellaModelFilter();
  private boolean _isNewTrace = false;

  /**
   * @param parent_p
   * @param labelProvider_p
   * @param contentProvider_p
   */
  public TraceTreeSelectionDialog(Shell parent_p, ILabelProvider labelProvider_p, ITreeContentProvider contentProvider_p, TraceableElement currentElement_p,
      boolean isNewTrace_p) {
    super(parent_p, labelProvider_p, contentProvider_p);
    this._currentElement = currentElement_p;
    this._isNewTrace = isNewTrace_p;
  }

  /**
   * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createDialogArea(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected Control createDialogArea(Composite parent_p) {

    Composite composite = (Composite) super.createDialogArea(parent_p);
    _statusBarText = new Text(composite, SWT.BORDER | SWT.READ_ONLY);
    _statusBarText.setEditable(false);
    _statusBarText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    setValidator(_validator);
    return composite;
  }

  /**
   * @see org.eclipse.ui.dialogs.ElementTreeSelectionDialog#createTreeViewer(org.eclipse.swt.widgets.Composite)
   */
  @Override
  protected TreeViewer createTreeViewer(Composite parent_p) {
    Group group = new Group(parent_p, SWT.CENTER);
    group.setText(Messages.getString("MdeElementTreeSelectionDialog.group_text")); //$NON-NLS-1$
    group.setLayout(new GridLayout());
    group.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

    Label label = new Label(group, SWT.LEFT);
    label.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    label.setText(Messages.getString("MdeElementTreeSelectionDialog.filter_char")); //$NON-NLS-1$

    Text text = new Text(group, SWT.BORDER);
    text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
    text.addModifyListener(filter);

    TreeViewer treeViewer = super.createTreeViewer(parent_p);
    treeViewer.addFilter(filter);

    treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

      @SuppressWarnings("synthetic-access")
      public void selectionChanged(SelectionChangedEvent event_p) {
        ISelection selection = event_p.getSelection();
        if (selection instanceof IStructuredSelection && ((IStructuredSelection) selection).size() == 1) {
          Object elem = ((IStructuredSelection) selection).getFirstElement();
          updateStatusBar(elem);
          if (elem instanceof TraceableElement) {
            if (_currentElement.equals(elem)) {
              updateStatus(new Status(IStatus.WARNING, MDTrace.PLUGIN_ID, IStatus.WARNING, Messages.getString("AddTraceWizard.warning_element_already_exists"), //$NON-NLS-1$
                                      null));
            } else if (!_isNewTrace && TraceUtil.containsTraceElement(_currentElement, (TraceableElement) elem)) {
              updateStatus(new Status(IStatus.WARNING, MDTrace.PLUGIN_ID, IStatus.WARNING, Messages.getString("AddTraceWizard.warning_element_already_exists"), //$NON-NLS-1$
                                      null));
            } else {
              updateStatus(new Status(IStatus.OK, MDTrace.PLUGIN_ID, IStatus.OK, "", null)); //$NON-NLS-1$
            }
          }
        }

      }

    });
    return treeViewer;
  }

  ISelectionStatusValidator _validator = new ISelectionStatusValidator() {

    @SuppressWarnings("synthetic-access")
    public IStatus validate(Object[] selection_p) {
      if (selection_p.length == 1) {
        if (selection_p[0] instanceof NamedElement) {
          if (_currentElement.equals(selection_p[0])) {
            updateStatus(new Status(IStatus.WARNING, MDTrace.PLUGIN_ID, IStatus.WARNING, Messages.getString("AddTraceWizard.warning_element_already_exists"), //$NON-NLS-1$
                                    null));
          } else if (!_isNewTrace && TraceUtil.containsTraceElement(_currentElement, (NamedElement) selection_p[0])) {
            return new Status(IStatus.WARNING, MDTrace.PLUGIN_ID, IStatus.WARNING, Messages.getString("AddTraceWizard.warning_element_already_exists"), null); //$NON-NLS-1$
          }
        }
      }
      return new Status(IStatus.OK, MDTrace.PLUGIN_ID, IStatus.OK, "", null); //$NON-NLS-1$
    }

  };

  @Override
  protected void updateButtonsEnableState(IStatus status) {
    Button okButton = getOkButton();
    if (okButton != null && !okButton.isDisposed()) {
      okButton.setEnabled(!(status.matches(IStatus.ERROR) || (status.matches(IStatus.WARNING))));
    }
  }

  public String getElementPath(Object object_p) {
    if (object_p instanceof NamedElement) {
      NamedElement element = (NamedElement) object_p;

      SystemEngineering systemEngineering = CapellaQueries.getInstance().getRootQueries().getSystemEngineering(element);

      return getPath(systemEngineering, element);
    }
    return Util.ZERO_LENGTH_STRING;
  }

  /**
   * Gets the path.
   * @param sysEng_p
   *          tghe system engineering.
   * @param target_p
   *          The target element.
   * @return The path.
   */
  public String getPath(SystemEngineering sysEng_p, NamedElement target_p) {
    StringBuffer path = new StringBuffer(target_p.getName());

    EObject container = target_p.eContainer();
    if (container instanceof NamedElement) {
      NamedElement parent = (NamedElement) target_p.eContainer();
      if (parent != sysEng_p) {
        path.insert(path.indexOf(path.toString()), getPath(sysEng_p, parent) + IImageKeys.PATH_SEPARATOR);
      } else {
        path.insert(path.indexOf(path.toString()), parent.getName() + IImageKeys.PATH_SEPARATOR);
      }
    }
    return path.toString();
  }

  private void updateStatusBar(Object element_p) {
    if (null == element_p) {
      _statusBarText.setText(Util.ZERO_LENGTH_STRING);
      return;
    }
    _statusBarText.setText(getElementPath(element_p));
  }
}
