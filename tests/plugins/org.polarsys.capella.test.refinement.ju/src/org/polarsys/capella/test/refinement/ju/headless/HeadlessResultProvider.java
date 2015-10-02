/*******************************************************************************
 * Copyright (c) 2006, 2015 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *  
 * Contributors:
 *    Thales - initial API and implementation
 *******************************************************************************/
package org.polarsys.capella.test.refinement.ju.headless;

/**
 * Cache to store result
 */
public class HeadlessResultProvider {
	/** Singleton access*/
	public static HeadlessResultProvider INSTANCE = new HeadlessResultProvider();

	/** the current class used to short-cut external java action */
	private IHeadlessResult _result = null;

	/**
	 * Default constructor
	 */
	private HeadlessResultProvider() {
		//Do nothing
	}

	/** accessor */
	public IHeadlessResult getCurrentResult() {
		return _result;
	}

	/** write accessor */
	public void setCurrentResult(IHeadlessResult result) {
		_result = result;
	}
}
