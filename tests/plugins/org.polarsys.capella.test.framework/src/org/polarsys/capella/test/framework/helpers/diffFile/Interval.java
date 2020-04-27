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
package org.polarsys.capella.test.framework.helpers.diffFile;

/**
 * This class implements a data structure to handle file fragment location. 
 * 
 * @author Erwan Brottier
 */
public class Interval {

	public long bornInf;
	public long bornSup;
	
	public Interval(long bornInf, long bornSup) {
		this.bornInf = bornInf;
		this.bornSup = bornSup;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Interval) {
			Interval interval = (Interval) o;
			return interval.bornInf == bornInf && interval.bornSup == bornSup;
		}
		return false;
	}
	
	public String toString() {
		return "["+bornInf+", "+bornSup+"]";   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
	}
}
