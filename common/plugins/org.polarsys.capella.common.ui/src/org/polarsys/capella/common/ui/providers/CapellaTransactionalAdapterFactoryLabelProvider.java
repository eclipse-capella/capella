/**
 * <copyright> 
 *
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *   Thales - Modification to provide the editing domain in 'lazy' mode 
 *
 * </copyright>
 *
 * $Id: TransactionalAdapterFactoryLabelProvider.java,v 1.3 2007/11/14 18:14:06 cdamus Exp $
 */
package org.polarsys.capella.common.ui.providers;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.internal.EMFTransactionUIPlugin;
import org.eclipse.emf.transaction.ui.internal.EMFTransactionUIStatusCodes;
import org.eclipse.emf.transaction.ui.internal.Tracing;
import org.eclipse.emf.transaction.ui.internal.l10n.Messages;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.swt.graphics.Image;

/**
 * [legacy] Automatically wraps any potential access to model objects in read transactions.<p>
 * 
 * To be able to deal with several editing domains, three modes are now managed<p>
 *  1) with a provided editing domain (legacy mode)<p>
 *  *) without any provided editing domain, each call to a method 'get<...>' will try to retrieve an editing domain from the given object
 *  <blockquote>
 *    2) if this editing domain is found, then we wrap the call in an 'exclusiveRun'<p>
 *    3) if no editing domain is found, the call simply runs
 *  </blockquote>
 * 
 * @author Christian W. Damus (cdamus)
 * @contributor Thales
 */
public class CapellaTransactionalAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider {

	private final TransactionalEditingDomain domain;
	
	/**
	 * Initializes me with no editing domain.
	 * The editing domain will be retrieved later when the related object will be known.
	 * 
	 * @param adapterFactory the adapter factory
	 */
	public CapellaTransactionalAdapterFactoryLabelProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
		
		this.domain = null;
	}

	/**
	 * Initializes me with the editing domain in which I create read
	 * transactions and that adapter factory that provides content providers.
	 * 
	 * @param domain my editing domain
	 * @param adapterFactory the adapter factory
	 */
	public CapellaTransactionalAdapterFactoryLabelProvider(TransactionalEditingDomain domain, AdapterFactory adapterFactory) {
		super(adapterFactory);
		
		this.domain = domain;
	}

	/**
	 * Runs the specified runnable in the editing domain, with interrupt handling.
	 * 
	 * @param <T> the result type of the runnable
	 * @param run the runnable to run
	 * @return its result, or <code>null</code> on interrupt
	 */
	protected <T> T run(Object object, RunnableWithResult<? extends T> run) {
		try {
			if (null == domain) {
				TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(object);
				if (null == editingDomain) {
					run.run();
					return run.getResult();
				}
				return TransactionUtil.runExclusive(editingDomain, run);
			}
			return TransactionUtil.runExclusive(domain, run);
		} catch (InterruptedException e) {
			Tracing.catching(CapellaTransactionalAdapterFactoryLabelProvider.class, "run", e); //$NON-NLS-1$
			
			// propagate interrupt status because we are not throwing
			Thread.currentThread().interrupt();
			
			EMFTransactionUIPlugin.INSTANCE.log(new Status(
				IStatus.ERROR,
				EMFTransactionUIPlugin.getPluginId(),
				EMFTransactionUIStatusCodes.LABEL_PROVIDER_INTERRUPTED,
				Messages.labelInterrupt,
				e));
			
			return null;
		}
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	public Image getColumnImage(final Object object, final int columnIndex) {
		return run(object, new RunnableWithResult.Impl<Image>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.getColumnImage(object, columnIndex));
			}});
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	public String getColumnText(final Object object, final int columnIndex) {
		return run(object, new RunnableWithResult.Impl<String>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.getColumnText(object, columnIndex));
			}});
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	protected Image getDefaultImage(final Object object) {
		return run(object, new RunnableWithResult.Impl<Image>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.getDefaultImage(object));
			}});
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	public Image getImage(final Object object) {
		return run(object, new RunnableWithResult.Impl<Image>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.getImage(object));
			}});
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	protected Image getImageFromObject(final Object object) {
		return run(object, new RunnableWithResult.Impl<Image>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.getImageFromObject(object));
			}});
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	public String getText(final Object object) {
		return run(object, new RunnableWithResult.Impl<String>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.getText(object));
			}});
	}

	/**
	 * Extends the inherited implementation by running in a read-only transaction.
	 */
	@Override
	public boolean isLabelProperty(final Object object, final String id) {
		return run(object, new RunnableWithResult.Impl<Boolean>() {
			public void run() {
				setResult(CapellaTransactionalAdapterFactoryLabelProvider.super.isLabelProperty(object, id));
			}});
	}
}
