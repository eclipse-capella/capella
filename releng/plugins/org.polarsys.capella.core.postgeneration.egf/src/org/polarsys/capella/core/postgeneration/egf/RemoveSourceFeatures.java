/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.postgeneration.egf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.egf.core.producer.InvocationException;
import org.eclipse.egf.ftask.producer.context.ITaskProductionContext;
import org.eclipse.egf.ftask.producer.invocation.ITaskProduction;
import org.eclipse.pde.internal.core.feature.ExternalFeatureModel;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureChild;

/**
 * 
 */
public class RemoveSourceFeatures implements ITaskProduction {
	/**
	 * Source feature suffix.
	 */
	private static final String SOURCE_FEATURE_SUFFIX = ".source"; //$NON-NLS-1$

	/**
	 * 
	 */
	private static final String FEATURE_FILE = "feature.xml"; //$NON-NLS-1$

	/**
	 * Feature ids.
	 */
//	private String[] _featureIds;
	/**
	 * Absolute path to features folder.
	 */
//	private String _featuresFolder;

	/**
	 * Throws a <tt>BuildException</tt> if <tt>expression_p</tt> is false.
	 * 
	 * @param message_p
	 * @param expression_p
	 * @throws BuildException
	 */
//	public void assertTrue(String message_p, boolean expression_p)
//			throws InvocationException {
//		if (!expression_p) {
//			throw new InvocationException(message_p);
//		}
//	}

	/**
	 * Do execute the task.
	 * 
	 * @param feature_p
	 *            a file that points to a feature.xml file.
	 */
	@SuppressWarnings("restriction")
	protected void execute(File feature_p) throws InvocationException {
		// Create a PDE Feature model object to manage the given feature.
		ExternalFeatureModel featureModel = new ExternalFeatureModel() {
			private static final long serialVersionUID = 1L;

			/**
			 * @see org.eclipse.pde.internal.core.feature.ExternalFeatureModel#isEditable()
			 */
			@Override
			public boolean isEditable() {
				// ExternalFeature model was not supposed to be modified.
				return true;
			}
		};
		try {
			// Input stream on given file.
			InputStream inputStream = new FileInputStream(feature_p);
			featureModel.load(inputStream, false);
			// Close the stream
			inputStream.close();
			// Get the 'real' feature.
			IFeature feature = featureModel.getFeature();
			String featureId = feature.getId();
			// Get included features.
			IFeatureChild[] includedFeatures = feature.getIncludedFeatures();
			ArrayList<IFeatureChild> featuresToRemove = new ArrayList<IFeatureChild>(
					0);
			// Loop over these ones to find the 'source' ones (a source feature
			// ends by '.source'.
			for (IFeatureChild currentIncludedFeature : includedFeatures) {
				String currentFeatureId = currentIncludedFeature.getId();
				// Check if current feature is a source one.
				if (currentFeatureId.endsWith(SOURCE_FEATURE_SUFFIX)) {
					featuresToRemove.add(currentIncludedFeature);
					System.out.println("Source feature:" + currentFeatureId + " will be removed from " + featureId); //$NON-NLS-1$ //$NON-NLS-2$
				}
			}
			// Do some features need to be removed ?
			if (!featuresToRemove.isEmpty()) {
				feature.removeIncludedFeatures(featuresToRemove.toArray(new IFeatureChild[featuresToRemove.size()]));
				// Save the new feature contents.
				String contents = getContents(feature);
				FileOutputStream outputStream = new FileOutputStream(feature_p);
				outputStream.write(contents.getBytes("UTF8")); //$NON-NLS-1$
				outputStream.close();
			}
		} catch (Exception exception_p) {
			throw new InvocationException(exception_p);
		}
	}

	/**
	 * @see org.eclipse.egf.ftask.producer.invocation.ITaskProduction#doExecute(org.eclipse.egf.ftask.producer.context.ITaskProductionContext,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void doExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	    IWorkspace workspace = ResourcesPlugin.getWorkspace();
	    for (IProject project : workspace.getRoot().getProjects()) {
			IResource xml = project.findMember(FEATURE_FILE);
			if (xml instanceof IFile) {
				execute(((IFile) xml).getRawLocation().toFile());
			}
		}
	}

	/**
	 * Get content as an XML string content.
	 * 
	 * @param feature_p
	 * @return a string that contains the XML representation of given feature.
	 */
	protected String getContents(IFeature feature_p) throws IOException {
		StringWriter swriter = new StringWriter();
		PrintWriter writer = new PrintWriter(swriter);
		writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"); //$NON-NLS-1$
		feature_p.write("", writer); //$NON-NLS-1$
		writer.flush();
		swriter.close();
		return swriter.toString();
	}

	/**
	 * @see org.eclipse.egf.ftask.producer.invocation.ITaskProduction#preExecute(org.eclipse.egf.ftask.producer.context.ITaskProductionContext,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void preExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}

	/**
	 * @see org.eclipse.egf.ftask.producer.invocation.ITaskProduction#postExecute(org.eclipse.egf.ftask.producer.context.ITaskProductionContext,
	 *      org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void postExecute(ITaskProductionContext productionContext, IProgressMonitor monitor) throws InvocationException {
	}
}
