/*******************************************************************************
 * Copyright (c) 2006, 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/

package org.polarsys.capella.common.flexibility.wizards.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperties;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.wizards.schema.IRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;

/**
 * Dialog which allows to modify a list of property element
 */
public class PropertyWizardPage extends WizardPage implements PropertyChangeListener {

  protected IPropertyContext context;

  protected IRendererContext renderers;

  protected IStatus lastStatus;

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
    if (lastStatus != null) {
      String message = lastStatus.getMessage();
      if ((message == null) || (message.length() == 0)) {
        setErrorMessage(null);
        setMessage(getDescription());

      } else {
        switch (lastStatus.getSeverity()) {
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
    if (lastStatus == null) {
      return true;
    }
    boolean isError = lastStatus.matches(IStatus.ERROR);
    return !isError;
  }

  protected void findMostSevere() {
    IPropertyContext context = getContext();

    int level = IStatus.OK;
    lastStatus = Status.OK_STATUS;
    lastProperty = null;

    if (context != null) {
      IProperties properties = context.getProperties();
      if (properties != null) {
        for (IProperty item : properties.getAllItems()) {
          IStatus status = item.validate(context.getCurrentValue(item), context);
          if ((status != null) && !status.isOK() && (level < status.getSeverity())) {
            level = status.getSeverity();
            lastStatus = status;
            lastProperty = item;
          }
        }
      }
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
