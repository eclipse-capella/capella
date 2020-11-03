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
package org.polarsys.capella.common.re.rpl2re.updatedef.properties;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;

import org.polarsys.capella.core.transition.common.constants.ITransitionConstants;
import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.flexibility.properties.schema.IRestraintProperty;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

/**
 */
public class ReplicableElementProperty extends AbstractProperty implements IEditableProperty, IRestraintProperty {

  /**
   * {@inheritDoc}
   */
  @Override
  public Object getValue(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    CatalogElement rootElement = (CatalogElement) ctx.get("RE");

    if (rootElement == null) {
      Collection<Object> selection = (Collection<Object>) ctx.get(ITransitionConstants.TRANSITION_SOURCES);
      Collection<CatalogElement> selectedElements =
          ReplicableElementHandlerHelper.getInstance(ctx).getUppestReplicableElement(ctx,
              (Collection) ReplicableElementHandlerHelper.getInstance(ctx).getIndirectlySelectedReplicableElements(ctx));
      if (selection.size() > 0) {
        for (Object item : selectedElements) {
          if (item instanceof CatalogElement) {
            rootElement = (CatalogElement) item;
            if (rootElement.getOrigin() != null) {
              rootElement = rootElement.getOrigin();
            }
            break;
          }
        }

        if (rootElement == null) {
          for (Object item : selectedElements) {
            if (item instanceof CatalogElement) {
              rootElement = (CatalogElement) item;
              if (rootElement.getOrigin() == null) {
                break;
              }
            }
          }
        }

        if (rootElement == null) {
          CatalogElementPkg pkg = ReplicableElementHandlerHelper.getInstance(ctx).getRootPackage((EObject) selection.iterator().next());
          CatalogElement element = ReplicableElementHandlerHelper.getInstance(ctx).createReplicableElement();
          element.setName(ReplicableElementHandlerHelper.getInstance(ctx).getInitialReplicableElementName(ctx, pkg));
          pkg.getOwnedElements().add(element);
          rootElement = element;
        }
        ctx.put("RE", rootElement);
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
    IContext ctx = (IContext) context.getSource();
    CatalogElement element = (CatalogElement) ctx.get("RE");
    if (value instanceof String) {
      element.setName((String) value);
      return element;
    }
    return value;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public IStatus validate(Object newValue, IPropertyContext context) {
    if (newValue instanceof String) {
      if (((String) newValue).length() == 0) {
        return new Status(IStatus.WARNING, getId(), "Should be not empty");
      }
    }
    return Status.OK_STATUS;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Collection<?> getChoiceValues(IPropertyContext context) {
    IContext ctx = (IContext) context.getSource();
    return ReplicableElementHandlerHelper.getInstance(ctx).getAllDefinedReplicableElements(ctx);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValue(IPropertyContext context) {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isMany() {
    return false;
  }

}
