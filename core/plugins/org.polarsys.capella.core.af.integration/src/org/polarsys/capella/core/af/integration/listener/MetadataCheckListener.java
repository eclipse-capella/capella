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

import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.polarsys.capella.common.ef.domain.IEditingDomainListener;

/**
 * @author Thomas Guiu
 *
 */
public class MetadataCheckListener implements IEditingDomainListener {

	public static boolean enabled = true;

	public static void enable() {
		MetadataCheckListener.enabled = true;
	}

	public static boolean isEnabled() {
		return MetadataCheckListener.enabled;
	}

	public static void disable() {
		MetadataCheckListener.enabled = false;
	}

	public void register(EditingDomain editingDomain) {
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Iterator<Adapter> it = resourceSet.eAdapters().iterator();
		while (it.hasNext()) {
			Adapter adapter = it.next();
			if (adapter instanceof MetadataCheckAdapter) {
				if (((MetadataCheckAdapter) adapter).isForced()) {
					return;
				}
				it.remove();
			}
		}
		resourceSet.eAdapters().add(createAdapter(editingDomain));
		
	}

	protected Adapter createAdapter(EditingDomain editingDomain) {
		return new MetadataCheckAdapter();
	}

	public static void unregister(EditingDomain editingDomain) {
		ResourceSet resourceSet = editingDomain.getResourceSet();
		Iterator<Adapter> it = resourceSet.eAdapters().iterator();
		while (it.hasNext()) {
			Adapter adapter = it.next();
			if (adapter instanceof MetadataCheckAdapter) {
				it.remove();
			}
		}
	}

	@Override
	public void createdEditingDomain(EditingDomain editingDomain) {
		if (enabled) {
			register(editingDomain);
		}
	}

	@Override
	public void disposedEditingDomain(EditingDomain editingDomain) {
		unregister(editingDomain);
	}

}
