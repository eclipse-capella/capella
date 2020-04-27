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

package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 */
public abstract class PropertyPreferencePage extends PreferencePage implements PropertyChangeListener, IWorkbenchPreferencePage {

  /**
   * @return the context
   */
  public abstract IPropertyContext getContext();

  /**
   * @return the renderers
   */
  public abstract IRendererContext getRendererContext();

  public PropertyPreferencePage(String pageName) {
    super(pageName);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void init(IWorkbench workbench) {
    IPropertyContext context = getContext();
    if (context != null) {
      context.registerListener(this);
    }
  }

  /**
   * @return
   */
  protected ILabelProvider getLabelProvider() {
    return getRendererContext().getLabelProvider();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performApply() {
    super.performApply();
    IPropertyContext context = getContext();
    if (context != null) {
      context.writeAll();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean performOk() {
    IPropertyContext context = getContext();
    if (context != null) {
      context.writeAll();
    }
    return super.performOk();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performDefaults() {
    super.performDefaults();
    IPropertyContext context = getContext();
    if (context != null) {
      context.setDefaults();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Control createContents(Composite parent) {
    ILabelProvider labelProvider = getLabelProvider();
    PropertyControl control = new PropertyControl(labelProvider, getContext(), getRendererContext()) {

      @Override
      protected boolean isFlat() {
        return true;
      }
    };
    return control.createControl(parent);
  }

  protected void applyToStatusLine(IStatus status) {
    String message = status.getMessage();
    if ((message == null) || (message.length() == 0)) {
      setErrorMessage(null);
      setMessage(getDescription());

    } else {
      switch (status.getSeverity()) {
        case IStatus.OK:
          setErrorMessage(null);
          setMessage(getDescription());
        break;
        case IStatus.WARNING:
          setErrorMessage(null);
          setMessage(message, IMessageProvider.WARNING);
        break;
        case IStatus.INFO:
          setErrorMessage(null);
          setMessage(message, IMessageProvider.INFORMATION);
        break;
        default:
          setErrorMessage(null);
          setMessage(message, IMessageProvider.ERROR);
        break;
      }
    }
  }

  protected IStatus findMostSevere() {
    IPropertyContext context = getContext();
    if (context != null) {
      IProperties properties = context.getProperties();
      if (properties != null) {
        for (IProperty item : properties.getAllItems()) {
          IStatus status = item.validate(context.getCurrentValue(item), context);
          if (!status.isOK()) {
            return status;
          }
        }
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  public void update(PropertyChangedEvent event) {
    applyToStatusLine(findMostSevere());
  }

}
