/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.core.af.integration.listener;

import java.util.ArrayList;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;

/**
 * @author Thomas Guiu
 *
 */
public class MetadataCheckListener implements IEditingDomainListener {

	/**
	 * 
	 */
	public MetadataCheckListener() {
	}

	@Override
	public void createdEditingDomain(EditingDomain editingDomain) {
		ResourceSet resourceSet = editingDomain.getResourceSet();
		resourceSet.eAdapters().add(new MetadataCheckAdapter(resourceSet));
	}

	@Override
	public void disposedEditingDomain(EditingDomain editingDomain) {
		ResourceSet resourceSet = editingDomain.getResourceSet();
		for (Adapter adapter : new ArrayList<Adapter>(resourceSet.eAdapters())) {
			if (adapter instanceof MetadataCheckAdapter)
				resourceSet.eAdapters().remove(adapter);
		}
	}

}
