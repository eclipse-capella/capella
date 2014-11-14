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
package org.polarsys.capella.core.data.fa.ui.wizards.dialogs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.service.IConstraintDescriptor;
import org.eclipse.emf.validation.service.IConstraintFilter;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import org.polarsys.capella.core.ui.toolkit.viewers.CapellaElementLabelProvider;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.core.validation.utils.ValidationHelper;

/**
 * 
 */
public class EIAllocationLabelProvider extends CapellaElementLabelProvider implements IBaseLabelProvider, IColorProvider, IFontProvider {
  /** */
  protected final boolean _isSourceViewer;
  private final EIAllocationTreeViewer _treeViewer;
  private LinkManager _linkManager;
  private Font _startedLinkElementFont;

  /** */
  private IConstraintFilter _filter = new IConstraintFilter() {
    @Override
    public boolean accept(IConstraintDescriptor constraint_p, EObject target_p) {
      return (_isSourceViewer ? _srcDesc.contains(constraint_p) : _tgtDesc.contains(constraint_p));
    }
  };

  /** */
  private static final String prefix = "org.polarsys.capella.core.data.fa.validation."; //$NON-NLS-1$
  /** */
  protected List<IConstraintDescriptor> _srcDesc = getConstraintDescriptors(Arrays.asList(
    new String[]{prefix+"TC_DF_10",prefix+"TC_DF_11",prefix+"TC_DF_12",prefix+"TC_DF_13",prefix+"TC_DF_14"})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$;
  /** */
  protected List<IConstraintDescriptor> _tgtDesc = getConstraintDescriptors(Arrays.asList(
    new String[]{prefix+"TC_DF_11",prefix+"TC_DF_12",prefix+"TC_DF_13",prefix+"TC_DF_14"})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //;

  /**
   * @param linkManager_p
   * @param treeViewer_p
   * @param isSourceViewer_p
   */
	public EIAllocationLabelProvider(LinkManager linkManager_p, EIAllocationTreeViewer treeViewer_p, boolean isSourceViewer_p) {
	  super();
	  _isSourceViewer = isSourceViewer_p;
    _treeViewer = treeViewer_p;
    _linkManager = linkManager_p;
	}

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    // Dispose created font.
    if ((null != _startedLinkElementFont) && !_startedLinkElementFont.isDisposed()) {
      _startedLinkElementFont.dispose();
      _startedLinkElementFont = null;
    }
    super.dispose();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText(Object element_p) {
    String text = super.getText(element_p);
    if (_linkManager.isStartedElement(element_p)) {
      text += " [start link]"; //$NON-NLS-1$
    }
    return text;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Font getFont(Object element_p) {
    if (_linkManager.isStartedElement(element_p)) {
      return getStartedLinkElementFont();
    }
    return super.getFont(element_p);
  }

  public final static String VALIDATION_KEY = "Validation"; //$NON-NLS-1$

  private boolean isValid(EObject element_p) {
    CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator().addConstraintFilter(_filter);
    IStatus status = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator().validate(element_p);
    CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator().removeConstraintFilter(_filter);

    Widget w = _treeViewer.findItem(element_p);
    if (w instanceof TreeItem) {
      if (!status.isOK()) {
        w.setData(VALIDATION_KEY, status);
      } else {
        w.setData(VALIDATION_KEY, null);
      }
    }
    return status.isOK();
  }

  /**
   * {@inheritDoc}
   */
  @Override
	public Color getForeground(Object element_p) {
    if (!isValid((EObject) element_p)) {
      return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
    }
	  return super.getForeground(element_p);
	}
  
  /**
   * Get all constraints contributed via the EMF Validation framework for Capella purposes
   * e.g. rules stored into the Capella constraint category
   * @return
   */
  public static List<IConstraintDescriptor> getConstraintDescriptors(List<String> ids_p) {
    List<IConstraintDescriptor> result = new ArrayList<IConstraintDescriptor>();

    ValidationHelper.ensureEMFValidationActivation();

    for (IConstraintDescriptor icd: ValidationHelper.getAllCapellaConstraintDescriptors()) {
      String id = icd.getId();
      if (ids_p.contains(id)) {
        result.add(icd);
      }
    }
    return result;
  }

  /**
   * Create started link element font.
   */
  protected Font getStartedLinkElementFont() {
    if (null == _startedLinkElementFont) {
      _startedLinkElementFont = new Font(Display.getDefault(), "Arial", 9, SWT.NORMAL | SWT.ITALIC | SWT.BOLD); //$NON-NLS-1$
    }
    return _startedLinkElementFont;
  }
}
