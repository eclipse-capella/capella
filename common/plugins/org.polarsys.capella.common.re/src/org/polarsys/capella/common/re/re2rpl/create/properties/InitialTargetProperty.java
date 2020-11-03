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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.capella.common.re.properties.AbstractReProperty;
import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class InitialTargetProperty extends AbstractReProperty implements IEditableProperty, ICompoundProperty, IModifiedProperty {

  protected static String TARGET = "RPL";

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();

    CatalogElement rootElement = (CatalogElement) ctx.get(TARGET);

    if (rootElement == null) {
      CatalogElement source =
          (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));

      if (source != null) {

        Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx);

        for (CatalogElement element : selectedElements) {
          if (!source.equals(element)) {
            ctx.put(TARGET, element);
            return element;
          }
        }

        CatalogElement element = ReplicableElementHandlerHelper.getInstance(ctx).createReplica();

        if (element.eContainer() == null) {
          EObject tsource = source;
          Collection selection = (Collection) ctx.get(ITransitionConstants.TRANSITION_SOURCES);
          if (!selection.isEmpty()) {
            tsource = (EObject) selection.iterator().next();
          }
          CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(ctx).getRootPackage(tsource);
          pkg.getOwnedElements().add(element);
        }

        ctx.put(TARGET, element);
        rootElement = element;
      }
    }

    return rootElement;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getType() {
    return CatalogElement.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object toType(Object value, IPropertyContext context) {
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
    CatalogElement source =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE));

    CatalogElement replica =
        (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));

    if ((replica != null) && (replica.getOrigin() == null)) {
      replica.setOrigin(source);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property, IPropertyContext context) {
    // Nothing here

    if (property.getId().equals(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_SOURCE)) {
      IContext ctx = (IContext) context.getSource();

      CatalogElement element = (CatalogElement) ctx.get(TARGET);
      if (element != null) {
        CatalogElement source = (CatalogElement) context.getCurrentValue(property);
        if (!element.equals(source) && (element.getOrigin() == null)) {
          element.setOrigin(source);
        }
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isModified(IPropertyContext context) {
    return true;
  }
}
