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
package org.polarsys.capella.common.re.re2rpl.create.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicaProperty extends AbstractProperty implements IEditableProperty, ICompoundProperty {

  private static CatalogElement _rootElement = null;

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    CatalogElement rootElement = (CatalogElement) context.get("RPL");

    if (rootElement == null) {
      CatalogElement source =
          (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE));
      Collection<CatalogElement> selectedElements = ReplicableElementHandlerHelper.getInstance(context).getIndirectlySelectedReplicableElements(context);

      for (CatalogElement element : selectedElements) {
        if (!source.equals(element)) {
          context.put("RPL", element);
          return element;
        }
      }

      Collection<Object> selection = (Collection<Object>) context.get(ITransitionConstants.TRANSITION_SOURCES);
      CatalogElement element = ReplicableElementHandlerHelper.getInstance(context).createReplica();
      element.setOrigin(source);
      context.put("RPL", element);
      rootElement = element;
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
  public Object toType(Object value_p, IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    ReplicableElementHandlerHelper.getInstance(context).setTarget(context, (CatalogElement) value_p);
    return value_p;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context_p) {
    IContext context = (IContext) context_p.getSource();
    CatalogElement source = (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty("source"));

    CatalogElement replica = (CatalogElement) context.get("RPL");
    if (replica.getOrigin() == null) {
      replica.setOrigin(source);
    }
    if (replica.eContainer() == null) {
      CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(context).getRootPackage(source);
      pkg.getOwnedElements().add(replica);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue_p, IPropertyContext context_p) {
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String[] getRelatedProperties() {
    return new String[] { "source" };
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void updatedValue(IProperty property_p, IPropertyContext context_p) {
    //Nothing here

    if (property_p.getId().equals(IReConstants.PROPERTY__REPLICABLE_ELEMENT__SOURCE)) {
      IContext context = (IContext) context_p.getSource();

      CatalogElement element = (CatalogElement) context.get("RPL");
      if (element != null) {
        CatalogElement rpl = (CatalogElement) context_p.getCurrentValue(property_p);
        if (!element.equals(rpl)) {
          element.setOrigin(rpl);
        }
      }
    }

  }
}
