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

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IProperty#getValue(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@SuppressWarnings("restriction")
	@Override
	public Object getValue(IPropertyContext context_p) {
		if (_currentName == null) {
		      IContext context = (IContext) context_p.getSource();
		      CatalogElement source =
		          (CatalogElement) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__REPLICABLE_ELEMENT__INITIAL_TARGET));
		      if (source != null) {
		        CatalogElementPkg location =
		            (CatalogElementPkg) context_p.getCurrentValue(context_p.getProperties().getProperty(IReConstants.PROPERTY__LOCATION_TARGET));
		        if ((source.getName() != null) && !(source.getName().isEmpty())) {
		          _currentName = source.getName();
		        } else {
		          _currentName = ReplicableElementHandlerHelper.getInstance(context).getInitialReplicaName(context, location);
		        }
		      }
		    }
		    return _currentName;
		
	}

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IProperty#getType()
	 */
	@Override
	public Object getType() {
		 return String.class;
	}

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IProperty#toType(java.lang.Object, org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public Object toType(Object value_p, IPropertyContext context_p) {
		if(value_p!= null){
		return value_p.toString();
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IModifiedProperty#isModified(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public boolean isModified(IPropertyContext context_p) {

		return false;
	}

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.IEditableProperty#setValue(org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public void setValue(IPropertyContext context_p) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty#getRelatedProperties()
	 */
	@Override
	public String[] getRelatedProperties() {

		return null;
	}

	/* (non-Javadoc)
	 * @see org.polarsys.capella.common.flexibility.properties.schema.ICompoundProperty#updatedValue(org.polarsys.capella.common.flexibility.properties.schema.IProperty, org.polarsys.capella.common.flexibility.properties.schema.IPropertyContext)
	 */
	@Override
	public void updatedValue(IProperty property_p, IPropertyContext context_p) {
		// TODO Auto-generated method stub
		
	}

}
