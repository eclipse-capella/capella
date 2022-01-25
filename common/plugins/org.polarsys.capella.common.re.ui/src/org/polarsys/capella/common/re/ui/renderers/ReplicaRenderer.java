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
import org.osgi.framework.FrameworkUtil;
import org.polarsys.capella.common.flexibility.properties.PropertyChangeListener;
import org.polarsys.capella.common.flexibility.properties.PropertyChangedEvent;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.wizards.renderer.EditListRenderer;
import org.polarsys.capella.common.flexibility.wizards.schema.IRendererContext;
import org.polarsys.capella.common.flexibility.wizards.ui.DefaultLabelProvider;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.attributes.AttributesHandlerHelper;
import org.polarsys.capella.common.re.handlers.scope.DependenciesHandlerHelper;
import org.polarsys.capella.common.re.ui.Activator;
import org.polarsys.capella.common.re.ui.decorators.InstanciationLabelDecorator;
import org.polarsys.capella.common.ui.toolkit.viewers.data.AbstractData;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicaRenderer extends EditListRenderer implements PropertyChangeListener {

  ILabelProvider _defaultProvider;

  @Override
  protected int getExpandLevel() {
    return AbstractTreeViewer.ALL_LEVELS;
  }

  @Override
  public void performRender(Composite parent, IRendererContext rendererContext) {
    super.performRender(parent, rendererContext);

  }

  /**
  * {@inheritDoc}
  */
  @Override
  protected Object createInput(IProperty property, IRendererContext propertyContext) {
    return super.createInput(property, propertyContext);
  }

  @Override
  public void updatedValue(IProperty property, IRendererContext propertyContext, Object newValue) {
    AbstractData data = (AbstractData) super.createInput(property, propertyContext);
    AbstractData menu = (AbstractData) getViewer().getClientViewer().getInput();

    if (menu != null) {
      menu.removeAllElements(menu.getValidElements().toArray());
      menu.addAllElements(data.getValidElements().toArray());
      getViewer().getClientViewer().refresh();

    } else {
      reloadInput(property, propertyContext);

    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected ILabelProvider createLabelProvider(final IRendererContext rendererContext) {
    _defaultProvider = super.createLabelProvider(rendererContext);

    final InstanciationLabelDecorator decorator = new InstanciationLabelDecorator();

    return new DefaultLabelProvider(rendererContext.getLabelProvider()) {

      public Font getBold(Font font) {
        Font result = JFaceResources.getFontRegistry().getBold(JFaceResources.getFontRegistry().defaultFont().getFontData()[0].getName());
        return result;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Color getBackground(Object element) {
        return ((IColorProvider) _defaultProvider).getBackground(element);
      }

      @Override
      public Color getForeground(Object element) {

        IStatus status = isImportant(element, rendererContext);
        if (status.matches(IStatus.INFO)) {
          return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
        }

        return ((IColorProvider) _defaultProvider).getForeground(element);
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public String getText(Object object) {
        if (object instanceof EStructuralFeature) {
          return ((EStructuralFeature) object).getName();
        }

        Collection<EObject> scopeElements =
            (Collection) rendererContext.getPropertyContext().getCurrentValue(
                rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

        Collection<EObject> allScopeElements =
            (Collection) rendererContext.getPropertyContext().getCurrentValue(
                rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__ALL_SCOPE));

        String text = super.getText(object);
        if (allScopeElements.contains(object)) {
          text = decorator.decorateText(text, object);
        }

        IStatus status = isImportant(object, rendererContext);
        if (!status.isOK() && !status.getMessage().isEmpty()) {
          return NLS.bind("{0} [{1}]", text, status.getMessage());
        }

        return text;
      }

      /**
       * {@inheritDoc}
       */
      @Override
      public Font getFont(Object element) {
        Font f = super.getFont(element);
        if (f == null) {
          f = ((IFontProvider) _defaultProvider).getFont(element);
        }

        IStatus status = isImportant(element, rendererContext);

        if ((status == null) || status.isOK()) {
          return super.getFont(element);

        } else if (status.matches(IStatus.WARNING)) {
          return getBold(f);

        }
        return super.getFont(element);
      }

      protected IStatus isImportant(Object element, IRendererContext context) {
        Collection<EObject> scopeElements =
            (Collection) context.getPropertyContext().getCurrentValue(
                context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

        Collection<EObject> allScopeElements =
            (Collection) context.getPropertyContext().getCurrentValue(
                context.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__ALL_SCOPE));

        if (!allScopeElements.contains(element)) {
          return Status.OK_STATUS;
        }

        if (!scopeElements.contains(element)) {
          return new Status(IStatus.INFO, FrameworkUtil.getBundle(Activator.class).getSymbolicName(), "");
        }

        IContext ctx = (IContext) context.getPropertyContext().getSource();
        if (AttributesHandlerHelper.getInstance(ctx).isSuffixable(element, ctx)) {
          return new Status(IStatus.WARNING, FrameworkUtil.getBundle(Activator.class).getSymbolicName(), "+SUFFIX");
        }

        return Status.OK_STATUS;
      }
    };
  }

  /**
   * @param element
   * @return
   */
  @Override
  protected IStatus getStatus(Object element, IRendererContext rendererContext) {
    Collection<EObject> scopeElements =
        (Collection) rendererContext.getPropertyContext().getCurrentValue(
            rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__SCOPE));

    if (!scopeElements.contains(element)) {
      return Status.OK_STATUS;
    }

    IContext context = (IContext) rendererContext.getPropertyContext().getSource();
    IStatus status = getDependenciesStatus(Collections.singletonList((EObject) element), scopeElements, context);
    return status;
  }

  public IStatus getDependenciesStatus(Collection<EObject> elements, Collection<EObject> scopeElements, IContext context) {
    Collection<EObject> values = DependenciesHandlerHelper.getInstance(context).getDependencies(elements, scopeElements, context);
    if (values.isEmpty()) {
      return Status.OK_STATUS;
    }
    return new Status(IStatus.WARNING, FrameworkUtil.getBundle(Activator.class).getSymbolicName(), "Missing dependencies");
  }

  @Override
  public void initialize(IProperty property, IRendererContext rendererContext) {

    // fix for https://bugs.polarsys.org/show_bug.cgi?id=1845
    rendererContext.getPropertyContext().getCurrentValue(
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIXES));

    super.initialize(property, rendererContext);
    rendererContext.getPropertyContext().registerListener(this,
        rendererContext.getPropertyContext().getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIXES));
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getToolbarLocation() {
    return "toolbar:org.polarsys.capella.common.re.createRec";
  }

  @Override
  protected String getPopupLocation() {
    return "popup:org.polarsys.capella.common.re.createRec";
  }
  
  @Override
  protected void initializeControls(final Composite parent, final IRendererContext context) {
    super.initializeControls(parent, context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose(IRendererContext context) {
    super.dispose(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void update(PropertyChangedEvent event) {

    if (IReConstants.PROPERTY__REPLICABLE_ELEMENT__SUFFIXES.equals(event.getProperty().getId())) {
      getViewer().getClientViewer().refresh();

    }
  }

}
