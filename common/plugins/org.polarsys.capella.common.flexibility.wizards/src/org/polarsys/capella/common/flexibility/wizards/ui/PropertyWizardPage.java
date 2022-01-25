/*******************************************************************************
 * Copyright (c) 2006, 2020 THALES GLOBAL SERVICES.
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
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.Activator;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 * Dialog which allows to modify a list of property element
 */
public class PropertyWizardPage extends WizardPage implements PropertyChangeListener {

  protected IPropertyContext context;

  protected IRendererContext renderers;
  
  protected MultiStatus status;

  @Deprecated
  protected IStatus lastStatus;

  @Deprecated
  protected IProperty lastProperty;

  /**
   * @return the context
   */
  protected IPropertyContext getContext() {
    return context;
  }

  /**
   * @return the renderers
   */
  protected IRendererContext getRendererContext() {
    return renderers;
  }

  public PropertyWizardPage(String pageName, IPropertyContext context, IRendererContext renderers) {
    super(pageName);
    this.context = context;
    this.renderers = renderers;

    IPropertyContext ctx = getContext();
    ctx.registerListener(this);
    setImageDescriptor(JFaceResources.getImageRegistry().getDescriptor(TitleAreaDialog.DLG_IMG_TITLE_BANNER));
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {
    try {

      for (IProperty property : getContext().getProperties().getAllItems()) {
        IRenderer renderer = getRendererContext().getRenderer(property);
        if (renderer != null) {
          try {
            renderer.dispose(getRendererContext());
          } catch (Exception e) {
            // Nothing here
          }
        }
      }
    } catch (Exception e) {
      // Nothing here
    }

    super.dispose();
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
  public void createControl(Composite parent) {
    ILabelProvider labelProvider = getLabelProvider();
    PropertyControl control = new PropertyControl(labelProvider, getContext(), getRendererContext());
    setControl(control.createControl(parent));

    update(null);
  }

  protected void applyToStatusLine() {
    if (status != null) {
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
  }

  @Override
  public boolean isPageComplete() {
    if (status == null) {
      return true;
    }
    boolean isError = status.matches(IStatus.ERROR);
    return !isError;
  }

  protected void findMostSevere() {
    IPropertyContext context = getContext();

    int level = IStatus.OK;
    lastProperty = null;
    lastStatus = Status.OK_STATUS;
    status = null;
    
    if (context != null) {
      MultiStatus multiStatus = new CompoundMultiStatus(FrameworkUtil.getBundle(Activator.class).getSymbolicName());
      IProperties properties = context.getProperties();
      if (properties != null) {
        for (IProperty item : properties.getAllItems()) {
          IStatus status = item.validate(context.getCurrentValue(item), context);
          multiStatus.add(status);
          if ((status != null) && !status.isOK() && (level < status.getSeverity())) {
            level = status.getSeverity();
            lastProperty = item;
            lastStatus = status;
          }
        }
      }
      status = multiStatus;
    }
  }

  /**
   * {@inheritDoc}
   */
  public void update(PropertyChangedEvent event) {
    findMostSevere();
    applyToStatusLine();
    setPageComplete(isPageComplete());
  }
}
