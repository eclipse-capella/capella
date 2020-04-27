/*******************************************************************************
 * Copyright (c) 2006, 2019 THALES GLOBAL SERVICES.
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
import org.polarsys.capella.common.ui.providers.MDEAdapterFactoryLabelProvider;
import org.polarsys.capella.core.validation.CapellaValidationActivator;
import org.polarsys.capella.core.validation.utils.ValidationHelper;

public class EIAllocationLabelProvider extends MDEAdapterFactoryLabelProvider implements IBaseLabelProvider, IColorProvider, IFontProvider {
  protected final boolean isSourceViewer;
  private final EIAllocationTreeViewer treeViewer;
  private LinkManager linkManager;
  private Font startedLinkElementFont;

  private IConstraintFilter _filter = new IConstraintFilter() {
    @Override
    public boolean accept(IConstraintDescriptor constraint, EObject target) {
      return (isSourceViewer ? _srcDesc.contains(constraint) : _tgtDesc.contains(constraint));
    }
  };

  private static final String prefix = "org.polarsys.capella.core.data.fa.validation."; //$NON-NLS-1$

  protected List<IConstraintDescriptor> _srcDesc = getConstraintDescriptors(Arrays.asList(
    new String[]{prefix+"TC_DF_10",prefix+"TC_DF_11",prefix+"TC_DF_12",prefix+"TC_DF_13",prefix+"TC_DF_14"})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$;

  protected List<IConstraintDescriptor> _tgtDesc = getConstraintDescriptors(Arrays.asList(
    new String[]{prefix+"TC_DF_11",prefix+"TC_DF_12",prefix+"TC_DF_13",prefix+"TC_DF_14"})); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //;

  /**
   * @param linkManager
   * @param treeViewer
   * @param isSourceViewer
   */
	public EIAllocationLabelProvider(LinkManager linkManager, EIAllocationTreeViewer treeViewer, boolean isSourceViewer) {
	  super();
	  this.isSourceViewer = isSourceViewer;
    this.treeViewer = treeViewer;
    this.linkManager = linkManager;
	}

  /**
   * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider#dispose()
   */
  @Override
  public void dispose() {
    // Dispose created font.
    if ((null != startedLinkElementFont) && !startedLinkElementFont.isDisposed()) {
      startedLinkElementFont.dispose();
      startedLinkElementFont = null;
    }
    super.dispose();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getText(Object element) {
    String text = super.getText(element);
    if (linkManager.isStartedElement(element)) {
      text += " [start link]"; //$NON-NLS-1$
    }
    return text;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Font getFont(Object element) {
    if (linkManager.isStartedElement(element)) {
      return getStartedLinkElementFont();
    }
    return super.getFont(element);
  }

  public final static String VALIDATION_KEY = "Validation"; //$NON-NLS-1$

  private boolean isValid(EObject element) {
    CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator().addConstraintFilter(_filter);
    IStatus status = CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator().validate(element);
    CapellaValidationActivator.getDefault().getCapellaValidatorAdapter().getValidator().removeConstraintFilter(_filter);

    Widget w = treeViewer.findItem(element);
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
	public Color getForeground(Object element) {
    if (!isValid((EObject) element)) {
      return Display.getCurrent().getSystemColor(SWT.COLOR_RED);
    }
	  return super.getForeground(element);
	}
  
  /**
   * Get all constraints contributed via the EMF Validation framework for Capella purposes
   * e.g. rules stored into the Capella constraint category
   * @return
   */
  public static List<IConstraintDescriptor> getConstraintDescriptors(List<String> ids) {
    List<IConstraintDescriptor> result = new ArrayList<IConstraintDescriptor>();

    ValidationHelper.ensureEMFValidationActivation();

    for (IConstraintDescriptor icd: ValidationHelper.getAllCapellaConstraintDescriptors()) {
      String id = icd.getId();
      if (ids.contains(id)) {
        result.add(icd);
      }
    }
    return result;
  }

  /**
   * Create started link element font.
   */
  protected Font getStartedLinkElementFont() {
    if (null == startedLinkElementFont) {
      startedLinkElementFont = new Font(Display.getDefault(), "Arial", 9, SWT.NORMAL | SWT.ITALIC | SWT.BOLD); //$NON-NLS-1$
    }
    return startedLinkElementFont;
  }
}
