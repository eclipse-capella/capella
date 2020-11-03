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
package org.polarsys.capella.common.re.re2rpl.detach.properties;

import org.polarsys.capella.common.flexibility.properties.property.AbstractProperty;
import org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IProperty;
import org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext;
import org.polarsys.capella.common.re.CatalogElement;
import org.polarsys.capella.common.re.CatalogElementPkg;
import org.polarsys.capella.common.re.constants.IReConstants;
import org.polarsys.capella.common.re.handlers.replicable.ReplicableElementHandlerHelper;
import org.polarsys.kitalpha.transposer.rules.handler.rules.api.IContext;

public class ElementNameProperty extends AbstractProperty implements ICompoundProperty, IEditableProperty, IModifiedProperty {

	
	public String _currentName;

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IProperty#getValue(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public Object getValue(IPropertyContext context) {
		if (_currentName == null) {
		      IContext ctx = (IContext) context.getSource();
		      CatalogElement source =
		          (CatalogElement) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
		      if (source != null) {
		        CatalogElementPkg location =
		            (CatalogElementPkg) context.getCurrentValue(context.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET));
		        if ((source.getName() != null) && !(source.getName().isEmpty())) {
		          _currentName = source.getName();
		        } else {
		          _currentName = ReplicableElementHandlerHelper.getInstance(ctx).getInitialReplicaName(ctx, location);
		        }
		      }
		    }
		    return _currentName;
		
	}

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IProperty#getType()
	 */
	@Override
	public Object getType() {
		 return String.class;
	}

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IProperty#toType(java.lang.Object, org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public Object toType(Object value, IPropertyContext context) {
		if(value!= null){
		return value.toString();
		}
		return null;
	}

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty#isModified(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public boolean isModified(IPropertyContext context) {

		return false;
	}

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public void setValue(IPropertyContext context) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty#getRelatedProperties()
	 */
	@Override
	public String[] getRelatedProperties() {

		return null;
	}

	/**
	 * @see org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty#updatedValue(org.polarsys.capella.common.flexibility.properties.schema.IProperty, org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public void updatedValue(IProperty property, IPropertyContext context) {
		// TODO Auto-generated method stub
		
	}

}
