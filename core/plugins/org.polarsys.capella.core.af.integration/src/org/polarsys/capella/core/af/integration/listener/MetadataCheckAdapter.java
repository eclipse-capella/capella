/*******************************************************************************
 * Copyright (c) 2016 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 ********************************	***********************************************/
package org.polarsys.capella.core.af.integration.listener;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.polarsys.capella.core.model.handler.command.CapellaResourceHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.MetadataHelper;
import org.polarsys.kitalpha.ad.metadata.helpers.ViewpointMetadata;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

public class MetadataCheckAdapter extends AdapterImpl {

	private boolean force = false;

	public boolean isForced() {
		return force;
	}

	public MetadataCheckAdapter() {
		this(false);
	}

	public MetadataCheckAdapter(boolean force) {
		this.force = force;
	}

	@Override
	public void notifyChanged(Notification notification) {
		Object notifier = notification.getNotifier();
		if (notifier instanceof ResourceSet && notification.getEventType() == Notification.ADD
				&& notification.getNewValue() instanceof Resource) {
			ResourceSet resourceSet = (ResourceSet) notifier;
			Resource res = (Resource) notification.getNewValue();
			checkMetadata(resourceSet, res);
		}
	}

	protected void checkMetadata(ResourceSet resourceSet, Resource res) {
		URI uri = res.getURI();

		if (CapellaResourceHelper.AIRD_FILE_EXTENSION.equals(uri.fileExtension()) && uri.isPlatformResource() && CapellaResourceHelper.isCapellaProject(res)) {
		  doCheckMetadata(resourceSet, uri);
		}
	}

  protected void doCheckMetadata(ResourceSet resourceSet, URI uri) {
    loadRelativeMetadata(uri, resourceSet);
    checkNoMetadata(resourceSet);
    checkMetadataCompliancy(resourceSet);
  }

	protected void loadRelativeMetadata(URI uri, ResourceSet resourceSet) {
		// trying to load the relative metadata (afm)
		URI afmUri = uri.trimFileExtension().appendFileExtension(ViewpointMetadata.STORAGE_EXTENSION);
		try {
			resourceSet.getResource(afmUri, true);
		} catch (MetadataException e) {
			throw e;
		} catch (Exception e) {
			// resource not found then proxy resource cleanning
			Resource resource = resourceSet.getResource(afmUri, false);
			if (resource != null && resource.getContents().isEmpty()) {
				resource.unload();
				resourceSet.getResources().remove(resource);
			}
		}
	}

	protected void checkNoMetadata(ResourceSet resourceSet) {
		if (!ViewpointManager.getInstance(resourceSet).hasMetadata()) {
			throw new NoMetadataException(MetadataHelper.getViewpointMetadata(resourceSet)
					.getExpectedMetadataStorageURI().toPlatformString(true));
		}
	}

	protected void checkMetadataCompliancy(ResourceSet resourceSet) {
		IStatus result = ViewpointManager.checkViewpointsCompliancy(resourceSet);
		if (!result.isOK()) {
			IStatus capella = ViewpointManager.checkViewpointCompliancy(resourceSet,
					"org.polarsys.capella.core.viewpoint");
			if (!capella.isOK()) {
				throw new WrongCapellaVersionException(capella);
			}
			throw new MetadataException(result);
		}
	}

}
