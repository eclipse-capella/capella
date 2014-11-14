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
package org.polarsys.capella.common.re.ui.renderers;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.capella.common.ui.toolkit.viewers.data.DataLabelProvider;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.EditListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;
import org.polarsys.capella.common.helpers.adapters.MDEAdapterFactory;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.capella.common.re.ui.decorators.InstanciationLabelDecorator;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicaRenderer extends EditListRenderer implements PropertyChangeListener {

  DataLabelProvider _parentProvider;
  ILabelProvider _defaultProvider;

  @Override
  protected int getExpandLevel() {
    return AbstractTreeViewer.ALL_LEVELS;
  }

  @Override
  public void performRender(Composite parent_p, IRendererContext rendererContext_p) {
    super.performRender(parent_p, rendererContext_p);
    _parentProvider.setViewer(getViewer().getClientViewer());
  }

  /**
  * {@inheritDoc}
  */
  @Override
  protected Object createInput(IProperty property_p, IRendererContext propertyContext_p) {
    return super.createInput(property_p, propertyContext_p);
  }

  @Override
  public void updatedValue(IProperty property_p, IRendererContext propertyContext_p, Object newValue_p) {
    AbstractData data = (AbstractData) super.createInput(property_p, propertyContext_p);
    AbstractData menu = (AbstractData) getViewer().getClientViewer().getInput();

    if (menu != null) {
      menu.removeAllElements(menu.getValidElements().toArray());
      menu.addAllElements(data.getValidElements().toArray());
      getViewer().getClientViewer().refresh();

    } else {
      reloadInput(property_p, propertyContext_p);

    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext_p) {
    _parentProvider = new DataLabelProvider(MDEAdapterFactory.getEditingDomain(), MDEAdapterFactory.getAdapterFactory());
    _defaultProvider = super.createLabelProvider(rendererContext_p);

    final InstanciationLabelDecorator decorator = new InstanciationLabelDecorator();

    return new DefaultLabelProvider(_parentProvider) {

      public Font getBold(Font font_p) {
        Font result = JFaceResources.getFontRegistry().getBold(JFaceResources.getFontRegistry().defaultFont().getFontData()[0].getName());
        return result;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Color getBackground(Object element_p) {
        return ((IColorProvider) _defaultProvider).getBackground(element_p);
      }

      @Override
      public Color getForeground(Object element_p) {

        IStatus status = isImportant(element_p, rendererContext_p);
        if (status.matches(IStatus.INFO)) {
          return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
        }

        return ((IColorProvider) _defaultProvider).getForeground(element_p);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public String getText(Object object_p) {
        if (object_p instanceof EStructuralFeature) {
          return ((EStructuralFeature) object_p).getName();
        }

        Collection<EObject> scopeElements =
            (Collection) rendererContext_p.getPropertyContext().getCurrentValue(
                rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

        Collection<EObject> allScopeElements =
            (Collection) rendererContext_p.getPropertyContext().getCurrentValue(
                rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__ALL_SCOPE));

        String text = super.getText(object_p);
        if (allScopeElements.contains(object_p)) {
          text = decorator.decorateText(text, object_p);
        }

        IStatus status = isImportant(object_p, rendererContext_p);
        if (!status.isOK() && !status.getMessage().isEmpty()) {
          return NLS.bind("{0} [{1}]", text, status.getMessage());
        }

        return text;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Font getFont(Object element_p) {
        Font f = super.getFont(element_p);
        if (f == null) {
          f = ((IFontProvider) _defaultProvider).getFont(element_p);
        }

        IStatus status = isImportant(element_p, rendererContext_p);

        if ((status == null) || status.isOK()) {
          return super.getFont(element_p);

        } else if (status.matches(IStatus.WARNING)) {
          return getBold(f);

        }
        return super.getFont(element_p);
      }

      protected IStatus isImportant(Object element_p, IRendererContext rendererContext_p) {
        Collection<EObject> scopeElements =
            (Collection) rendererContext_p.getPropertyContext().getCurrentValue(
                rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

        Collection<EObject> allScopeElements =
            (Collection) rendererContext_p.getPropertyContext().getCurrentValue(
                rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__ALL_SCOPE));

        if (!allScopeElements.contains(element_p)) {
          return Status.OK_STATUS;
        }

        if (!scopeElements.contains(element_p)) {
          return new Status(IStatus.INFO, "dd", "");
        }

        IContext context = (IContext) rendererContext_p.getPropertyContext().getSource();
        if (AttributesHandlerHelper.getInstance(context).isSuffixable(element_p, context)) {
          return new Status(IStatus.WARNING, "dd", "+SUFFIX");
        }

        return Status.OK_STATUS;
      }
    };
  }

  /**
   * @param element_p
   * @return
   */
  @Override
  protected IStatus getStatus(Object element_p, IRendererContext rendererContext_p) {
    Collection<EObject> scopeElements =
        (Collection) rendererContext_p.getPropertyContext().getCurrentValue(
            rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

    if (!scopeElements.contains(element_p)) {
      return Status.OK_STATUS;
    }

    IContext context = (IContext) rendererContext_p.getPropertyContext().getSource();
    IStatus status =
        DependenciesHandlerHelper.getInstance(context).getDependenciesStatus(Collections.singletonList((EObject) element_p), scopeElements, context);
    return status;
  }

  @Override
  public void initialize(IProperty property_p, IRendererContext rendererContext_p) {
    super.initialize(property_p, rendererContext_p);
    rendererContext_p.getPropertyContext().registerListener(this,
        rendererContext_p.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIXES));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getToolbarLocation() {
    return "toolbar:org.polarsys.capella.common.re.createRec";
  }

  @Override
  protected void initializeControls(final Composite parent_p, final IRendererContext context_p) {
    super.initializeControls(parent_p, context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context_p) {
    super.dispose(context_p);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(PropertyChangedEvent event_p) {

    if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIXES.equals(event_p.getProperty().getId())) {
      getViewer().getClientViewer().refresh();

    }
  }

}
