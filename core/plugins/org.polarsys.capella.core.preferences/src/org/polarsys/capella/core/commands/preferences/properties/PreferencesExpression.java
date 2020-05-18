/*******************************************************************************
 * Copyright (c) 2006, 2018 THALES GLOBAL SERVICES.
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
package org.polarsys.capella.core.commands.preferences.properties;

import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.Expression;
import org.eclipse.core.expressions.ExpressionInfo;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.internal.expressions.ExpressionMessages;
import org.eclipse.core.internal.expressions.ExpressionStatus;
import org.eclipse.core.internal.expressions.Expressions;
import org.eclipse.core.internal.expressions.Property;
import org.eclipse.core.internal.expressions.TypeExtensionManager;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.activities.IActivity;
import org.eclipse.ui.activities.IActivityManager;
import org.eclipse.ui.activities.IWorkbenchActivitySupport;
import org.eclipse.ui.menus.IMenuService;
import org.w3c.dom.Element;

public class PreferencesExpression extends Expression {

	private String fNamespace;
	private String fProperty;
	private Object[] fArgs;
	private Object fExpectedValue;
	private boolean fForcePluginActivation;

	private static final char PROP_SEP = '.';
	private static final String ATT_PROPERTY= "property"; //$NON-NLS-1$
	private static final String ATT_ARGS= "args"; //$NON-NLS-1$
	private static final String ATT_FORCE_PLUGIN_ACTIVATION= "forcePluginActivation"; //$NON-NLS-1$
	/**
	 * The seed for the hash code for all test expressions.
	 */
	private static final int HASH_INITIAL= PreferencesExpression.class.getName().hashCode();

	private static final TypeExtensionManager fgTypeExtensionManager= new TypeExtensionManager("preferenceTesters"); //$NON-NLS-1$

	public PreferencesExpression(IConfigurationElement element) throws CoreException {
		String property= element.getAttribute(ATT_PROPERTY);
		int pos= property.lastIndexOf(PROP_SEP);
		if (pos == -1) {
			throw new CoreException(new ExpressionStatus(
				ExpressionStatus.NO_NAMESPACE_PROVIDED,
				ExpressionMessages.TestExpression_no_name_space));
		}
		fNamespace= property.substring(0, pos);
		fProperty= property.substring(pos + 1);
		fArgs= Expressions.getArguments(element, ATT_ARGS);
		fExpectedValue= Expressions.convertArgument(element.getAttribute(ATT_VALUE));
		fForcePluginActivation= Expressions.getOptionalBooleanAttribute(element, ATT_FORCE_PLUGIN_ACTIVATION);
	}

	public PreferencesExpression(Element element) throws CoreException {
		String property= element.getAttribute(ATT_PROPERTY);
		int pos= property.lastIndexOf(PROP_SEP);
		if (pos == -1) {
			throw new CoreException(new ExpressionStatus(
				ExpressionStatus.NO_NAMESPACE_PROVIDED,
				ExpressionMessages.TestExpression_no_name_space));
		}
		fNamespace= property.substring(0, pos);
		fProperty= property.substring(pos + 1);
		fArgs= Expressions.getArguments(element, ATT_ARGS);
		String value = element.getAttribute(ATT_VALUE);
		fExpectedValue= Expressions.convertArgument(value.length() > 0 ? value : null);
		fForcePluginActivation= Expressions.getOptionalBooleanAttribute(element, ATT_FORCE_PLUGIN_ACTIVATION);
	}

	public PreferencesExpression(String namespace, String property, Object[] args, Object expectedValue) {
		this(namespace, property, args, expectedValue, false);
	}

	public PreferencesExpression(String namespace, String property, Object[] args, Object expectedValue, boolean forcePluginActivation) {
		Assert.isNotNull(namespace);
		Assert.isNotNull(property);
		fNamespace= namespace;
		fProperty= property;
		fArgs= args != null ? args : Expressions.EMPTY_ARGS;
		fExpectedValue= expectedValue;
		fForcePluginActivation= forcePluginActivation;
	}

	public EvaluationResult evaluate(IEvaluationContext context) throws CoreException {
		Object element= context.getDefaultVariable();
		if (System.class.equals(element)) {
			String str= System.getProperty(fProperty);
			if (str == null)
				return EvaluationResult.FALSE;
			return EvaluationResult.valueOf(str.equals(fArgs[0]));
		}
		Property property= fgTypeExtensionManager.getProperty(element, fNamespace, fProperty, context.getAllowPluginActivation() && fForcePluginActivation);
		if (!property.isInstantiated())
			return EvaluationResult.NOT_LOADED;
		return EvaluationResult.valueOf(property.test(element, fArgs, fExpectedValue));
	}

	@Override
	public void collectExpressionInfo(ExpressionInfo info) {
		info.markDefaultVariableAccessed();
		info.addAccessedPropertyName(fNamespace + PROP_SEP + fProperty);
	}

	@Override
	public boolean equals(final Object object) {
		if (!(object instanceof PreferencesExpression))
			return false;

		final PreferencesExpression that= (PreferencesExpression)object;
		return this.fNamespace.equals(that.fNamespace) && this.fProperty.equals(that.fProperty)
			&& this.fForcePluginActivation == that.fForcePluginActivation
			&& equals(this.fArgs, that.fArgs) && equals(this.fExpectedValue, that.fExpectedValue);
	}
	
	@Override
	public int hashCode() {
		// To satisfy Sonar
		return super.hashCode();
	}

	@Override
	protected int computeHashCode() {
		return HASH_INITIAL * HASH_FACTOR + hashCode(fArgs)
			* HASH_FACTOR + hashCode(fExpectedValue)
			* HASH_FACTOR + fNamespace.hashCode()
			* HASH_FACTOR + fProperty.hashCode()
			* HASH_FACTOR + (fForcePluginActivation ? 1 : 0);
	}

	//---- Debugging ---------------------------------------------------

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder args= new StringBuilder();
		for (int i= 0; i < fArgs.length; i++) {
			Object arg= fArgs[i];
			if (arg instanceof String) {
				args.append('\'');
				args.append(arg);
				args.append('\'');
			} else {
				args.append(arg.toString());
			}
			if (i < fArgs.length - 1)
				args.append(", "); //$NON-NLS-1$
		}
		return "<test property=\"" + fProperty +  //$NON-NLS-1$
		  (fArgs.length != 0 ? "\" args=\"" + args + "\"" : "\"") + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		  (fExpectedValue != null ? "\" value=\"" + fExpectedValue + "\"" : "\"") + //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		  " plug-in activation: " + (fForcePluginActivation ? "eager" : "lazy") +   //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
		  "/>"; //$NON-NLS-1$
	}

	//---- testing ---------------------------------------------------

	public boolean testGetForcePluginActivation() {
		return fForcePluginActivation;
	}

	public static TypeExtensionManager testGetTypeExtensionManager() {
		return fgTypeExtensionManager;
	}
	
	
	/**
	 * 
	 */
	public void showhideMenuContribution() {
		
		IWorkbenchActivitySupport activitySupport = PlatformUI.getWorkbench().getActivitySupport();
		IActivityManager activityManager = activitySupport.getActivityManager();
		IActivity a = activityManager.getActivity("org.eclipse.ui.actions");
		IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow() ;
		
		IMenuService mSvc = window.getService(IMenuService.class);
		
		
		
		ISelection selection = window.getSelectionService().getSelection("capella.project.explorer");

		((TreeSelection) selection).getFirstElement() ;

	}
}
