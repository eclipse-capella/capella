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
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.polarsys.kitalpha.ad.services.manager.ViewpointManager;

/**
 * @author Thomas Guiu
 *
 */
public class MetadataCheckAdapter extends EContentAdapter{
   
	private ResourceSet resourceSet;

	public MetadataCheckAdapter(ResourceSet resourceSet) {
		this.resourceSet = resourceSet;
	}

	@Override
    public void notifyChanged(Notification notification)
    {
      Object notifier = notification.getNotifier();
      if (notifier == resourceSet && notification.getEventType() == Notification.ADD && notification.getNewValue() instanceof Resource)
      {
      		Resource res = (Resource)notification.getNewValue();
      		URI uri = res.getURI();
      		if ("aird".equals(uri.fileExtension()) && "platform".equals(uri.scheme()))
      		{
      			//  we try to load its afm companion
      			URI afmUri = uri.trimFileExtension().appendFileExtension("afm");
  				try {
  					resourceSet.getResource(afmUri, true);
  				} catch (MetadataException e) {
  					throw e;
  				} catch (Exception e)
  				{
  					// cannot find the resource
  					// clean proxy resource
  					Resource resource = resourceSet.getResource(afmUri, false);
  					if (resource != null && resource.getContents().isEmpty()) {
  						resource.unload();
  						resourceSet.getResources().remove(resource);
  					}
  					return ;
  				}
      			IStatus result = ViewpointManager.checkViewpointsCompliancy(resourceSet);
        	      if (!result.isOK())
        	      {
        	    	  throw new MetadataException(toString(result));
        	      }
     		}
      		if ("afm".equals(uri.fileExtension()) && "cdo".equals(uri.scheme()))
      		{
      			IStatus result = ViewpointManager.checkViewpointsCompliancy(resourceSet);
				if (!result.isOK()) {
					throw new MetadataException(toString(result));
				}
      		}
      }
    }
	public void addElement(StringBuilder sb, String indent, IStatus status) {
		sb.append(indent).append(status.getMessage());
		if (status.getChildren().length != 0)
			sb.append("\n");
		for (IStatus child : status.getChildren())
			addElement(sb, indent+'\t', child);
	}
	public String toString(IStatus status) {
		StringBuilder sb = new StringBuilder();
		addElement(sb, "", status);
		return sb.toString();
	}
	public static final class MetadataException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1545083406195117594L;

		public MetadataException(String message) {
			super(message);
		}

	}
	

}
