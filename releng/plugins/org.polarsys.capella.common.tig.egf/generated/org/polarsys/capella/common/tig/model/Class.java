//Generated with EGF 1.5.0.v20170706-0846
package org.polarsys.capella.common.tig.model;

import org.eclipse.egf.emf.pattern.base.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.*;
import org.eclipse.emf.codegen.ecore.genmodel.generator.*;
import org.eclipse.emf.codegen.util.*;
import org.eclipse.emf.ecore.util.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.egf.common.helper.*;
import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.egf.model.pattern.*;
import org.eclipse.egf.pattern.execution.*;
import org.eclipse.egf.pattern.query.*;

public class Class extends org.polarsys.kitalpha.emde.egf.model.Class {
	protected static String nl;

	public static synchronized Class create(String lineSeparator) {
		nl = lineSeparator;
		Class result = new Class();
		nl = null;
		return result;
	}

	public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
	protected final String TEXT_1 = "";
	protected final String TEXT_2 = NL + "package ";
	protected final String TEXT_3 = ";";
	protected final String TEXT_4 = NL + "package ";
	protected final String TEXT_5 = ";";
	protected final String TEXT_6 = NL;
	protected final String TEXT_7 = NL;
	protected final String TEXT_8 = NL;
	protected final String TEXT_9 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL
			+ " * An implementation of the model object '<em><b>";
	protected final String TEXT_10 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
	protected final String TEXT_11 = NL + " * <p>" + NL + " * The following features are implemented:" + NL + " * </p>"
			+ NL + " * <ul>";
	protected final String TEXT_12 = NL + " *   <li>{@link ";
	protected final String TEXT_13 = "#";
	protected final String TEXT_14 = " <em>";
	protected final String TEXT_15 = "</em>}</li>";
	protected final String TEXT_16 = NL + " * </ul>";
	protected final String TEXT_17 = NL + " *" + NL + " * @generated" + NL + " */";
	protected final String TEXT_18 = NL + "public";
	protected final String TEXT_19 = " abstract";
	protected final String TEXT_20 = " class ";
	protected final String TEXT_21 = NL + "public interface ";
	protected final String TEXT_22 = NL + "{";
	protected final String TEXT_23 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
	protected final String TEXT_24 = " copyright = ";
	protected final String TEXT_25 = ";";
	protected final String TEXT_26 = NL;
	protected final String TEXT_27 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
	protected final String TEXT_28 = " mofDriverNumber = \"";
	protected final String TEXT_29 = "\";";
	protected final String TEXT_30 = NL;
	protected final String TEXT_31 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
			+ "\tprivate static final long serialVersionUID = 1L;" + NL;
	protected final String TEXT_32 = NL + "\t/**" + NL
			+ "\t * An array of objects representing the values of non-primitive features." + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL
			+ "\t */";
	protected final String TEXT_33 = NL + "\t@";
	protected final String TEXT_34 = NL + "\tprotected Object[] ";
	protected final String TEXT_35 = ";" + NL;
	protected final String TEXT_36 = NL + "\t/**" + NL
			+ "\t * A bit field representing the indices of non-primitive feature values." + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL
			+ "\t */";
	protected final String TEXT_37 = NL + "\t@";
	protected final String TEXT_38 = NL + "\tprotected int ";
	protected final String TEXT_39 = ";" + NL;
	protected final String TEXT_40 = NL + "\t/**" + NL
			+ "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set."
			+ NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL
			+ "\t * @ordered" + NL + "\t */";
	protected final String TEXT_41 = NL + "\t@";
	protected final String TEXT_42 = NL + "\tprotected int ";
	protected final String TEXT_43 = " = 0;" + NL;
	protected final String TEXT_44 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
	protected final String TEXT_45 = "() <em>";
	protected final String TEXT_46 = "</em>}' array accessor." + NL
			+ "\t * This is specialized for the more specific element type known in this context." + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
	protected final String TEXT_47 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
	protected final String TEXT_48 = NL + "\t@SuppressWarnings(\"rawtypes\")";
	protected final String TEXT_49 = NL + "\tprotected static final ";
	protected final String TEXT_50 = "[] ";
	protected final String TEXT_51 = "_EEMPTY_ARRAY = new ";
	protected final String TEXT_52 = " [0]";
	protected final String TEXT_53 = ";" + NL;
	protected final String TEXT_54 = NL;
	protected final String TEXT_55 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
	protected final String TEXT_56 = " = ";
	protected final String TEXT_57 = ".getFeatureID(";
	protected final String TEXT_58 = ") - ";
	protected final String TEXT_59 = ";" + NL;
	protected final String TEXT_60 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
	protected final String TEXT_61 = " = ";
	protected final String TEXT_62 = ".getFeatureID(";
	protected final String TEXT_63 = ") - ";
	protected final String TEXT_64 = ";" + NL;
	protected final String TEXT_65 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL
			+ "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
	protected final String TEXT_66 = ".getOperationID(";
	protected final String TEXT_67 = ") - ";
	protected final String TEXT_68 = ";" + NL;
	protected final String TEXT_69 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
	protected final String TEXT_70 = "public";
	protected final String TEXT_71 = "protected";
	protected final String TEXT_72 = " ";
	protected final String TEXT_73 = "()" + NL + "\t{";
	protected final String TEXT_74 = NL;
	protected final String TEXT_75 = NL + "\t\t";
	protected final String TEXT_76 = " |= ";
	protected final String TEXT_77 = "_EFLAG";
	protected final String TEXT_78 = "_DEFAULT";
	protected final String TEXT_79 = ";";
	protected final String TEXT_80 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_81 = NL + "\t@Override";
	protected final String TEXT_82 = NL + "\tprotected ";
	protected final String TEXT_83 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
	protected final String TEXT_84 = ";" + NL + "\t}" + NL;
	protected final String TEXT_85 = NL;
	protected final String TEXT_86 = NL;
	protected final String TEXT_87 = NL;
	protected final String TEXT_88 = NL;
	protected final String TEXT_89 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_90 = NL + "\t@SuppressWarnings(\"unchecked\")";
	protected final String TEXT_91 = NL + "\t@Override";
	protected final String TEXT_92 = NL + "\tpublic ";
	protected final String TEXT_93 = " eInverseAdd(";
	protected final String TEXT_94 = " otherEnd, int featureID, ";
	protected final String TEXT_95 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
	protected final String TEXT_96 = ")" + NL + "\t\t{";
	protected final String TEXT_97 = NL + "\t\t\tcase ";
	protected final String TEXT_98 = ":";
	protected final String TEXT_99 = NL + "\t\t\t\treturn ((";
	protected final String TEXT_100 = "(";
	protected final String TEXT_101 = ".InternalMapView";
	protected final String TEXT_102 = ")";
	protected final String TEXT_103 = "()).eMap()).basicAdd(otherEnd, msgs);";
	protected final String TEXT_104 = NL + "\t\t\t\treturn (";
	protected final String TEXT_105 = "()).basicAdd(otherEnd, msgs);";
	protected final String TEXT_106 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL
			+ "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
	protected final String TEXT_107 = NL + "\t\t\t\treturn basicSet";
	protected final String TEXT_108 = "((";
	protected final String TEXT_109 = ")otherEnd, msgs);";
	protected final String TEXT_110 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
	protected final String TEXT_111 = ", msgs);";
	protected final String TEXT_112 = NL + "\t\t\t\t";
	protected final String TEXT_113 = " ";
	protected final String TEXT_114 = " = (";
	protected final String TEXT_115 = ")eVirtualGet(";
	protected final String TEXT_116 = ");";
	protected final String TEXT_117 = NL + "\t\t\t\t";
	protected final String TEXT_118 = " ";
	protected final String TEXT_119 = " = ";
	protected final String TEXT_120 = "basicGet";
	protected final String TEXT_121 = "();";
	protected final String TEXT_122 = NL + "\t\t\t\tif (";
	protected final String TEXT_123 = " != null)";
	protected final String TEXT_124 = NL + "\t\t\t\t\tmsgs = ((";
	protected final String TEXT_125 = ")";
	protected final String TEXT_126 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
	protected final String TEXT_127 = ", null, msgs);";
	protected final String TEXT_128 = NL + "\t\t\t\t\tmsgs = ((";
	protected final String TEXT_129 = ")";
	protected final String TEXT_130 = ").eInverseRemove(this, ";
	protected final String TEXT_131 = ", ";
	protected final String TEXT_132 = ".class, msgs);";
	protected final String TEXT_133 = NL + "\t\t\t\treturn basicSet";
	protected final String TEXT_134 = "((";
	protected final String TEXT_135 = ")otherEnd, msgs);";
	protected final String TEXT_136 = NL + "\t\t}";
	protected final String TEXT_137 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
	protected final String TEXT_138 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
	protected final String TEXT_139 = NL + "\t}" + NL;
	protected final String TEXT_140 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_141 = NL + "\t@Override";
	protected final String TEXT_142 = NL + "\tpublic ";
	protected final String TEXT_143 = " eInverseRemove(";
	protected final String TEXT_144 = " otherEnd, int featureID, ";
	protected final String TEXT_145 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
	protected final String TEXT_146 = ")" + NL + "\t\t{";
	protected final String TEXT_147 = NL + "\t\t\tcase ";
	protected final String TEXT_148 = ":";
	protected final String TEXT_149 = NL + "\t\t\t\treturn ((";
	protected final String TEXT_150 = ")((";
	protected final String TEXT_151 = ".InternalMapView";
	protected final String TEXT_152 = ")";
	protected final String TEXT_153 = "()).eMap()).basicRemove(otherEnd, msgs);";
	protected final String TEXT_154 = NL + "\t\t\t\treturn ((";
	protected final String TEXT_155 = ")((";
	protected final String TEXT_156 = ".Internal.Wrapper)";
	protected final String TEXT_157 = "()).featureMap()).basicRemove(otherEnd, msgs);";
	protected final String TEXT_158 = NL + "\t\t\t\treturn ((";
	protected final String TEXT_159 = ")";
	protected final String TEXT_160 = "()).basicRemove(otherEnd, msgs);";
	protected final String TEXT_161 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
	protected final String TEXT_162 = ", msgs);";
	protected final String TEXT_163 = NL + "\t\t\t\treturn basicUnset";
	protected final String TEXT_164 = "(msgs);";
	protected final String TEXT_165 = NL + "\t\t\t\treturn basicSet";
	protected final String TEXT_166 = "(null, msgs);";
	protected final String TEXT_167 = NL + "\t\t}";
	protected final String TEXT_168 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
	protected final String TEXT_169 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
	protected final String TEXT_170 = NL + "\t}" + NL;
	protected final String TEXT_171 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_172 = NL + "\t@Override";
	protected final String TEXT_173 = NL + "\tpublic ";
	protected final String TEXT_174 = " eBasicRemoveFromContainerFeature(";
	protected final String TEXT_175 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
	protected final String TEXT_176 = ")" + NL + "\t\t{";
	protected final String TEXT_177 = NL + "\t\t\tcase ";
	protected final String TEXT_178 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
	protected final String TEXT_179 = ", ";
	protected final String TEXT_180 = ".class, msgs);";
	protected final String TEXT_181 = NL + "\t\t}";
	protected final String TEXT_182 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
	protected final String TEXT_183 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
	protected final String TEXT_184 = NL + "\t}" + NL;
	protected final String TEXT_185 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_186 = NL + "\t@Override";
	protected final String TEXT_187 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL
			+ "\t{" + NL + "\t\tswitch (featureID";
	protected final String TEXT_188 = ")" + NL + "\t\t{";
	protected final String TEXT_189 = NL + "\t\t\tcase ";
	protected final String TEXT_190 = ":";
	protected final String TEXT_191 = NL + "\t\t\t\treturn ";
	protected final String TEXT_192 = "();";
	protected final String TEXT_193 = NL + "\t\t\t\treturn ";
	protected final String TEXT_194 = "() ? Boolean.TRUE : Boolean.FALSE;";
	protected final String TEXT_195 = NL + "\t\t\t\treturn new ";
	protected final String TEXT_196 = "(";
	protected final String TEXT_197 = "());";
	protected final String TEXT_198 = NL + "\t\t\t\tif (resolve) return ";
	protected final String TEXT_199 = "();" + NL + "\t\t\t\treturn basicGet";
	protected final String TEXT_200 = "();";
	protected final String TEXT_201 = NL + "\t\t\t\tif (coreType) return ((";
	protected final String TEXT_202 = ".InternalMapView";
	protected final String TEXT_203 = ")";
	protected final String TEXT_204 = "()).eMap();" + NL + "\t\t\t\telse return ";
	protected final String TEXT_205 = "();";
	protected final String TEXT_206 = NL + "\t\t\t\tif (coreType) return ";
	protected final String TEXT_207 = "();" + NL + "\t\t\t\telse return ";
	protected final String TEXT_208 = "().map();";
	protected final String TEXT_209 = NL + "\t\t\t\tif (coreType) return ((";
	protected final String TEXT_210 = ".Internal.Wrapper)";
	protected final String TEXT_211 = "()).featureMap();" + NL + "\t\t\t\treturn ";
	protected final String TEXT_212 = "();";
	protected final String TEXT_213 = NL + "\t\t\t\tif (coreType) return ";
	protected final String TEXT_214 = "();" + NL + "\t\t\t\treturn ((";
	protected final String TEXT_215 = ".Internal)";
	protected final String TEXT_216 = "()).getWrapper();";
	protected final String TEXT_217 = NL + "\t\t\t\treturn ";
	protected final String TEXT_218 = "();";
	protected final String TEXT_219 = NL + "\t\t}";
	protected final String TEXT_220 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
	protected final String TEXT_221 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
	protected final String TEXT_222 = NL + "\t}" + NL;
	protected final String TEXT_223 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_224 = NL + "\t@Override";
	protected final String TEXT_225 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL
			+ "\t{" + NL + "\t\tswitch (featureID";
	protected final String TEXT_226 = ")" + NL + "\t\t{";
	protected final String TEXT_227 = NL + "\t\t\tcase ";
	protected final String TEXT_228 = ":";
	protected final String TEXT_229 = NL + "\t\t\t\treturn ";
	protected final String TEXT_230 = "() ? Boolean.TRUE : Boolean.FALSE;";
	protected final String TEXT_231 = NL + "\t\t\t\treturn new ";
	protected final String TEXT_232 = "(";
	protected final String TEXT_233 = "());";
	protected final String TEXT_234 = NL + "\t\t\t\treturn ";
	protected final String TEXT_235 = "();";
	protected final String TEXT_236 = NL + "\t\t\t\tif (coreType) return ((";
	protected final String TEXT_237 = ".InternalMapView";
	protected final String TEXT_238 = ")";
	protected final String TEXT_239 = "()).eMap();" + NL + "\t\t\t\telse return ";
	protected final String TEXT_240 = "();";
	protected final String TEXT_241 = NL + "\t\t\t\tif (coreType) return ";
	protected final String TEXT_242 = "();" + NL + "\t\t\t\telse return ";
	protected final String TEXT_243 = "().map();";
	protected final String TEXT_244 = NL + "\t\t\t\tif (coreType) return ((";
	protected final String TEXT_245 = ".Internal.Wrapper)";
	protected final String TEXT_246 = "()).featureMap();" + NL + "\t\t\t\treturn ";
	protected final String TEXT_247 = "();";
	protected final String TEXT_248 = NL + "\t\t\t\tif (coreType) return ";
	protected final String TEXT_249 = "();" + NL + "\t\t\t\treturn ((";
	protected final String TEXT_250 = ".Internal)";
	protected final String TEXT_251 = "()).getWrapper();";
	protected final String TEXT_252 = NL + "\t\t\t\treturn ";
	protected final String TEXT_253 = "();";
	protected final String TEXT_254 = NL + "\t\t}";
	protected final String TEXT_255 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
	protected final String TEXT_256 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
	protected final String TEXT_257 = NL + "\t}" + NL;
	protected final String TEXT_258 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_259 = NL + "\t@SuppressWarnings(\"unchecked\")";
	protected final String TEXT_260 = NL + "\t@Override";
	protected final String TEXT_261 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL
			+ "\t\tswitch (featureID";
	protected final String TEXT_262 = ")" + NL + "\t\t{";
	protected final String TEXT_263 = NL + "\t\t\tcase ";
	protected final String TEXT_264 = ":";
	protected final String TEXT_265 = NL + "\t\t\t\t((";
	protected final String TEXT_266 = ".Internal)((";
	protected final String TEXT_267 = ".Internal.Wrapper)";
	protected final String TEXT_268 = "()).featureMap()).set(newValue);";
	protected final String TEXT_269 = NL + "\t\t\t\t((";
	protected final String TEXT_270 = ".Internal)";
	protected final String TEXT_271 = "()).set(newValue);";
	protected final String TEXT_272 = NL + "\t\t\t\t((";
	protected final String TEXT_273 = ".Setting)((";
	protected final String TEXT_274 = ".InternalMapView";
	protected final String TEXT_275 = ")";
	protected final String TEXT_276 = "()).eMap()).set(newValue);";
	protected final String TEXT_277 = NL + "\t\t\t\t((";
	protected final String TEXT_278 = ".Setting)";
	protected final String TEXT_279 = "()).set(newValue);";
	protected final String TEXT_280 = NL + "\t\t\t\t";
	protected final String TEXT_281 = "().clear();" + NL + "\t\t\t\t";
	protected final String TEXT_282 = "().addAll((";
	protected final String TEXT_283 = "<? extends ";
	protected final String TEXT_284 = ">";
	protected final String TEXT_285 = ")newValue);";
	protected final String TEXT_286 = NL + "\t\t\t\tset";
	protected final String TEXT_287 = "(((";
	protected final String TEXT_288 = ")newValue).";
	protected final String TEXT_289 = "());";
	protected final String TEXT_290 = NL + "\t\t\t\t// begin-extension-code" + NL
			+ "\t\t\t\tif (newValue == null || newValue instanceof ";
	protected final String TEXT_291 = ") {" + NL + "\t\t\t\t// end-extension-code";
	protected final String TEXT_292 = NL + "\t\t\t\t\tset";
	protected final String TEXT_293 = "((";
	protected final String TEXT_294 = ")newValue);";
	protected final String TEXT_295 = NL + "\t\t\t\t// begin-extension-code" + NL + "\t\t\t\t}" + NL
			+ "\t\t\t\t// end-extension-code";
	protected final String TEXT_296 = NL + "          set";
	protected final String TEXT_297 = "(newValue);";
	protected final String TEXT_298 = NL + "\t\t\t\treturn;";
	protected final String TEXT_299 = NL + "\t\t}";
	protected final String TEXT_300 = NL + "\t\tsuper.eSet(featureID, newValue);";
	protected final String TEXT_301 = NL + "\t\teDynamicSet(featureID, newValue);";
	protected final String TEXT_302 = NL + "\t}" + NL;
	protected final String TEXT_303 = NL;
	protected final String TEXT_304 = NL;
	protected final String TEXT_305 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_306 = NL + "\t@Override";
	protected final String TEXT_307 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
	protected final String TEXT_308 = " baseClass)" + NL + "\t{";
	protected final String TEXT_309 = NL + "\t\tif (baseClass == ";
	protected final String TEXT_310 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
	protected final String TEXT_311 = ")" + NL + "\t\t\t{";
	protected final String TEXT_312 = NL + "\t\t\t\tcase ";
	protected final String TEXT_313 = ": return ";
	protected final String TEXT_314 = ";";
	protected final String TEXT_315 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
	protected final String TEXT_316 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);"
			+ NL + "\t}";
	protected final String TEXT_317 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_318 = NL + "\t@Override";
	protected final String TEXT_319 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
	protected final String TEXT_320 = " baseClass)" + NL + "\t{";
	protected final String TEXT_321 = NL + "\t\tif (baseClass == ";
	protected final String TEXT_322 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
	protected final String TEXT_323 = NL + "\t\t\t\tcase ";
	protected final String TEXT_324 = ": return ";
	protected final String TEXT_325 = ";";
	protected final String TEXT_326 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
	protected final String TEXT_327 = NL + "\t\tif (baseClass == ";
	protected final String TEXT_328 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
	protected final String TEXT_329 = ")" + NL + "\t\t\t{";
	protected final String TEXT_330 = NL + "\t\t\t\tcase ";
	protected final String TEXT_331 = ": return ";
	protected final String TEXT_332 = ";";
	protected final String TEXT_333 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
	protected final String TEXT_334 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);"
			+ NL + "\t}" + NL;
	protected final String TEXT_335 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_336 = NL + "\t@Override";
	protected final String TEXT_337 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
	protected final String TEXT_338 = " baseClass)" + NL + "\t{";
	protected final String TEXT_339 = NL + "\t\tif (baseClass == ";
	protected final String TEXT_340 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
	protected final String TEXT_341 = NL + "\t\t\t\tcase ";
	protected final String TEXT_342 = ": return ";
	protected final String TEXT_343 = ";";
	protected final String TEXT_344 = NL
			+ "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL
			+ "\t\t}";
	protected final String TEXT_345 = NL + "\t\tif (baseClass == ";
	protected final String TEXT_346 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
	protected final String TEXT_347 = NL + "\t\t\t\tcase ";
	protected final String TEXT_348 = ": return ";
	protected final String TEXT_349 = ";";
	protected final String TEXT_350 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
	protected final String TEXT_351 = NL + "\t\tif (baseClass == ";
	protected final String TEXT_352 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
	protected final String TEXT_353 = ")" + NL + "\t\t\t{";
	protected final String TEXT_354 = NL + "\t\t\t\tcase ";
	protected final String TEXT_355 = ": return ";
	protected final String TEXT_356 = ";";
	protected final String TEXT_357 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
	protected final String TEXT_358 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL
			+ "\t}" + NL;
	protected final String TEXT_359 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_360 = NL + "\t@Override";
	protected final String TEXT_361 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
	protected final String TEXT_362 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->"
			+ NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_363 = NL + "\t@Override";
	protected final String TEXT_364 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL
			+ "\t\t";
	protected final String TEXT_365 = " = newValues;" + NL + "\t}" + NL;
	protected final String TEXT_366 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_367 = NL + "\t@Override";
	protected final String TEXT_368 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL
			+ "\t\tswitch (offset)" + NL + "\t\t{";
	protected final String TEXT_369 = NL + "\t\t\tcase ";
	protected final String TEXT_370 = " :" + NL + "\t\t\t\treturn ";
	protected final String TEXT_371 = ";";
	protected final String TEXT_372 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();"
			+ NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_373 = NL + "\t@Override";
	protected final String TEXT_374 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL
			+ "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
	protected final String TEXT_375 = NL + "\t\t\tcase ";
	protected final String TEXT_376 = " :" + NL + "\t\t\t\t";
	protected final String TEXT_377 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
	protected final String TEXT_378 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();"
			+ NL + "\t\t}" + NL + "\t}" + NL;
	protected final String TEXT_379 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_380 = NL + "\t@Override";
	protected final String TEXT_381 = NL + "\t@SuppressWarnings(";
	protected final String TEXT_382 = "\"unchecked\"";
	protected final String TEXT_383 = "{\"rawtypes\", \"unchecked\" }";
	protected final String TEXT_384 = ")";
	protected final String TEXT_385 = NL + "\tpublic Object eInvoke(int operationID, ";
	protected final String TEXT_386 = " arguments) throws ";
	protected final String TEXT_387 = NL + "\t{" + NL + "\t\tswitch (operationID";
	protected final String TEXT_388 = ")" + NL + "\t\t{";
	protected final String TEXT_389 = NL + "\t\t\tcase ";
	protected final String TEXT_390 = ":";
	protected final String TEXT_391 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
	protected final String TEXT_392 = NL + "\t\t\t\t";
	protected final String TEXT_393 = "(";
	protected final String TEXT_394 = "(";
	protected final String TEXT_395 = "(";
	protected final String TEXT_396 = ")";
	protected final String TEXT_397 = "arguments.get(";
	protected final String TEXT_398 = ")";
	protected final String TEXT_399 = ").";
	protected final String TEXT_400 = "()";
	protected final String TEXT_401 = ", ";
	protected final String TEXT_402 = ");" + NL + "\t\t\t\t";
	protected final String TEXT_403 = "return null;";
	protected final String TEXT_404 = NL + "\t\t\t\t";
	protected final String TEXT_405 = "return ";
	protected final String TEXT_406 = "new ";
	protected final String TEXT_407 = "(";
	protected final String TEXT_408 = "(";
	protected final String TEXT_409 = "(";
	protected final String TEXT_410 = "(";
	protected final String TEXT_411 = ")";
	protected final String TEXT_412 = "arguments.get(";
	protected final String TEXT_413 = ")";
	protected final String TEXT_414 = ").";
	protected final String TEXT_415 = "()";
	protected final String TEXT_416 = ", ";
	protected final String TEXT_417 = ")";
	protected final String TEXT_418 = ")";
	protected final String TEXT_419 = ";";
	protected final String TEXT_420 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
	protected final String TEXT_421 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
	protected final String TEXT_422 = "(throwable);" + NL + "\t\t\t\t}";
	protected final String TEXT_423 = NL + "\t\t}";
	protected final String TEXT_424 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
	protected final String TEXT_425 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
	protected final String TEXT_426 = NL + "\t}" + NL;
	protected final String TEXT_427 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_428 = NL + "\t@Override";
	protected final String TEXT_429 = NL + "\tpublic String toString()" + NL + "\t{" + NL
			+ "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL
			+ "\t\tStringBuffer result = new StringBuffer(super.toString());";
	protected final String TEXT_430 = NL + "\t\tresult.append(\" (";
	protected final String TEXT_431 = ": \");";
	protected final String TEXT_432 = NL + "\t\tresult.append(\", ";
	protected final String TEXT_433 = ": \");";
	protected final String TEXT_434 = NL + "\t\tif (eVirtualIsSet(";
	protected final String TEXT_435 = ")) result.append(eVirtualGet(";
	protected final String TEXT_436 = ")); else result.append(\"<unset>\");";
	protected final String TEXT_437 = NL + "\t\tif (";
	protected final String TEXT_438 = "(";
	protected final String TEXT_439 = " & ";
	protected final String TEXT_440 = "_ESETFLAG) != 0";
	protected final String TEXT_441 = "ESet";
	protected final String TEXT_442 = ") result.append((";
	protected final String TEXT_443 = " & ";
	protected final String TEXT_444 = "_EFLAG) != 0); else result.append(\"<unset>\");";
	protected final String TEXT_445 = NL + "\t\tif (";
	protected final String TEXT_446 = "(";
	protected final String TEXT_447 = " & ";
	protected final String TEXT_448 = "_ESETFLAG) != 0";
	protected final String TEXT_449 = "ESet";
	protected final String TEXT_450 = ") result.append(";
	protected final String TEXT_451 = "_EFLAG_VALUES[(";
	protected final String TEXT_452 = " & ";
	protected final String TEXT_453 = "_EFLAG) >>> ";
	protected final String TEXT_454 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
	protected final String TEXT_455 = NL + "\t\tif (";
	protected final String TEXT_456 = "(";
	protected final String TEXT_457 = " & ";
	protected final String TEXT_458 = "_ESETFLAG) != 0";
	protected final String TEXT_459 = "ESet";
	protected final String TEXT_460 = ") result.append(";
	protected final String TEXT_461 = "); else result.append(\"<unset>\");";
	protected final String TEXT_462 = NL + "\t\tresult.append(eVirtualGet(";
	protected final String TEXT_463 = ", ";
	protected final String TEXT_464 = "));";
	protected final String TEXT_465 = NL + "\t\tresult.append((";
	protected final String TEXT_466 = " & ";
	protected final String TEXT_467 = "_EFLAG) != 0);";
	protected final String TEXT_468 = NL + "\t\tresult.append(";
	protected final String TEXT_469 = "_EFLAG_VALUES[(";
	protected final String TEXT_470 = " & ";
	protected final String TEXT_471 = "_EFLAG) >>> ";
	protected final String TEXT_472 = "_EFLAG_OFFSET]);";
	protected final String TEXT_473 = NL + "\t\tresult.append(";
	protected final String TEXT_474 = ");";
	protected final String TEXT_475 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}"
			+ NL;
	protected final String TEXT_476 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
	protected final String TEXT_477 = NL + "\t@";
	protected final String TEXT_478 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */"
			+ NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
	protected final String TEXT_479 = " theKey = getKey();" + NL
			+ "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL
			+ "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->"
			+ NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL
			+ "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
	protected final String TEXT_480 = " getKey()" + NL + "\t{";
	protected final String TEXT_481 = NL + "\t\treturn new ";
	protected final String TEXT_482 = "(getTypedKey());";
	protected final String TEXT_483 = NL + "\t\treturn getTypedKey();";
	protected final String TEXT_484 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
	protected final String TEXT_485 = " key)" + NL + "\t{";
	protected final String TEXT_486 = NL + "\t\tgetTypedKey().addAll(";
	protected final String TEXT_487 = "(";
	protected final String TEXT_488 = ")";
	protected final String TEXT_489 = "key);";
	protected final String TEXT_490 = NL + "\t\tsetTypedKey(key);";
	protected final String TEXT_491 = NL + "\t\tsetTypedKey(((";
	protected final String TEXT_492 = ")key).";
	protected final String TEXT_493 = "());";
	protected final String TEXT_494 = NL + "\t\tsetTypedKey((";
	protected final String TEXT_495 = ")key);";
	protected final String TEXT_496 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
	protected final String TEXT_497 = " getValue()" + NL + "\t{";
	protected final String TEXT_498 = NL + "\t\treturn new ";
	protected final String TEXT_499 = "(getTypedValue());";
	protected final String TEXT_500 = NL + "\t\treturn getTypedValue();";
	protected final String TEXT_501 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL
			+ "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
	protected final String TEXT_502 = " setValue(";
	protected final String TEXT_503 = " value)" + NL + "\t{" + NL + "\t\t";
	protected final String TEXT_504 = " oldValue = getValue();";
	protected final String TEXT_505 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
	protected final String TEXT_506 = "(";
	protected final String TEXT_507 = ")";
	protected final String TEXT_508 = "value);";
	protected final String TEXT_509 = NL + "\t\tsetTypedValue(value);";
	protected final String TEXT_510 = NL + "\t\tsetTypedValue(((";
	protected final String TEXT_511 = ")value).";
	protected final String TEXT_512 = "());";
	protected final String TEXT_513 = NL + "\t\tsetTypedValue((";
	protected final String TEXT_514 = ")value);";
	protected final String TEXT_515 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL
			+ "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL
			+ "\t */";
	protected final String TEXT_516 = NL + "\t@SuppressWarnings(\"unchecked\")";
	protected final String TEXT_517 = NL + "\tpublic ";
	protected final String TEXT_518 = " getEMap()" + NL + "\t{" + NL + "\t\t";
	protected final String TEXT_519 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
	protected final String TEXT_520 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
	protected final String TEXT_521 = NL;
	protected final String TEXT_522 = NL + "} //";
	protected final String TEXT_523 = NL;
	protected final String TEXT_524 = NL;

	public Class() {
		//Here is the constructor
		StringBuffer stringBuffer = new StringBuffer();

		// add initialisation of the pattern variables (declaration has been already done).

	}

	public String generate(Object argument) throws Exception {
		final StringBuffer stringBuffer = new StringBuffer();

		InternalPatternContext ctx = (InternalPatternContext) argument;
		Map<String, String> queryCtx = null;
		IQuery.ParameterDescription paramDesc = null;
		Node.Container currentNode = ctx.getNode();

		List<Object> parameterList = null;
		//this pattern can only be called by another (i.e. it's not an entry point in execution)

		for (Object parameterParameter : parameterList) {

			this.parameter = (org.eclipse.emf.codegen.ecore.genmodel.GenClass) parameterParameter;

			if (preCondition(ctx)) {
				ctx.setNode(new Node.Container(currentNode, getClass()));
				orchestration(ctx);
			}

		}
		ctx.setNode(currentNode);
		if (ctx.useReporter()) {
			ctx.getReporter().executionFinished(OutputManager.computeExecutionOutput(ctx), ctx);
		}

		stringBuffer.append(TEXT_523);
		stringBuffer.append(TEXT_524);
		return stringBuffer.toString();
	}

	public String orchestration(PatternContext ctx) throws Exception {
		InternalPatternContext ictx = (InternalPatternContext) ctx;

		super.orchestration(new SuperOrchestrationContext(ictx));

		if (ictx.useReporter()) {
			Map<String, Object> parameterValues = new HashMap<String, Object>();
			parameterValues.put("parameter", this.parameter);
			String outputWithCallBack = OutputManager.computeLoopOutput(ictx);
			String loop = OutputManager.computeLoopOutputWithoutCallback(ictx);
			ictx.getReporter().loopFinished(loop, outputWithCallBack, ictx, parameterValues);
		}
		return null;
	}

	public Map<String, Object> getParameters() {
		final Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("parameter", this.parameter);
		return parameters;
	}

	protected void method_doGenerate(final StringBuffer stringBuffer, final PatternContext ctx) throws Exception {

		/**
		 * Copyright (c) 2002-2011 IBM Corporation and others.
		 * All rights reserved.   This program and the accompanying materials
		 * terms of the Eclipse Public License 2.0 which is available at
		 * http://www.eclipse.org/legal/epl-2.0
		 * 
		 * SPDX-License-Identifier: EPL-2.0
		 *
		 * Contributors:
		 *   IBM - Initial API and implementation
		 */

		final GenClass genClass = (GenClass) ((Object[]) argument)[0];
		final GenPackage genPackage = genClass.getGenPackage();
		final GenModel genModel = genPackage.getGenModel();
		final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
		final boolean isInterface = Boolean.TRUE.equals(((Object[]) argument)[1]);
		final boolean isImplementation = Boolean.TRUE.equals(((Object[]) argument)[2]);
		final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
		final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
		final String singleWildcard = isJDK50 ? "<?>" : "";
		final String negativeOffsetCorrection = genClass.hasOffsetCorrection()
				? " - " + genClass.getOffsetCorrectionField(null) : "";
		final String positiveOffsetCorrection = genClass.hasOffsetCorrection()
				? " + " + genClass.getOffsetCorrectionField(null) : "";
		final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection()
				? " - EOPERATION_OFFSET_CORRECTION" : "";
		final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection()
				? " + EOPERATION_OFFSET_CORRECTION" : "";
		stringBuffer.append(TEXT_1);
		{
			//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#LogicalName=org.eclipse.egf.emf.pattern.base.HeaderJava" args="parameter:argument"%>

			InternalPatternContext ictx = (InternalPatternContext) ctx;
			new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
			stringBuffer.setLength(0);

			final Map<String, Object> callParameters = new HashMap<String, Object>();
			callParameters.put("argument", parameter);
			CallHelper.executeWithParameterInjection(
					"platform:/plugin/org.eclipse.egf.emf.pattern.base/egf/EMF_Pattern_Base.fcore#_XHLrsCwtEd-jc5T-XaRJlg",
					new ExecutionContext((InternalPatternContext) ctx), callParameters);
			stringBuffer.setLength(0);
		}

		if (isInterface) {
			stringBuffer.append(TEXT_2);
			stringBuffer.append(genPackage.getInterfacePackageName());
			stringBuffer.append(TEXT_3);
		} else {
			stringBuffer.append(TEXT_4);
			stringBuffer.append(genPackage.getClassPackageName());
			stringBuffer.append(TEXT_5);
		}
		stringBuffer.append(TEXT_6);
		genModel.markImportLocation(stringBuffer, genPackage);
		if (isImplementation) {
			genClass.addClassPsuedoImports();
		}
		stringBuffer.append(TEXT_7);
		if (isInterface) {
			stringBuffer.append(TEXT_8);
			{
				//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.interface.javadoc.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

				InternalPatternContext ictx = (InternalPatternContext) ctx;
				new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
				stringBuffer.setLength(0);

				final Map<String, Object> callParameters = new HashMap<String, Object>();
				callParameters.put("genClass", genClass);
				callParameters.put("genPackage", genPackage);
				callParameters.put("genModel", genModel);
				callParameters.put("isJDK50", isJDK50);
				callParameters.put("isInterface", isInterface);
				callParameters.put("isImplementation", isImplementation);
				callParameters.put("isGWT", isGWT);
				callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
				callParameters.put("singleWildcard", singleWildcard);
				callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
				callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
				callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
				callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
				CallHelper.executeWithParameterInjection(
						"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_C2iO4eXDEeCxnsoQRwU99Q",
						new ExecutionContext((InternalPatternContext) ctx), callParameters);
				stringBuffer.setLength(0);
			}

			//Class/interface.javadoc.override.javajetinc
		} else {
			stringBuffer.append(TEXT_9);
			stringBuffer.append(genClass.getFormattedName());
			stringBuffer.append(TEXT_10);
			if (!genClass.getImplementedGenFeatures().isEmpty()) {
				stringBuffer.append(TEXT_11);
				for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
					stringBuffer.append(TEXT_12);
					stringBuffer.append(genClass.getQualifiedClassName());
					stringBuffer.append(TEXT_13);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_14);
					stringBuffer.append(genFeature.getFormattedName());
					stringBuffer.append(TEXT_15);
				}
				stringBuffer.append(TEXT_16);
			}
			stringBuffer.append(TEXT_17);
		}
		if (isImplementation) {
			stringBuffer.append(TEXT_18);
			if (genClass.isAbstract()) {
				stringBuffer.append(TEXT_19);
			}
			stringBuffer.append(TEXT_20);
			stringBuffer.append(genClass.getClassName());
			stringBuffer.append(genClass.getTypeParameters().trim());
			stringBuffer.append(genClass.getClassExtends());
			stringBuffer.append(genClass.getClassImplements());
		} else {
			stringBuffer.append(TEXT_21);
			stringBuffer.append(genClass.getInterfaceName());
			stringBuffer.append(genClass.getTypeParameters().trim());
			stringBuffer.append(genClass.getInterfaceExtends());
		}
		stringBuffer.append(TEXT_22);
		if (genModel.hasCopyrightField()) {
			stringBuffer.append(TEXT_23);
			stringBuffer.append(publicStaticFinalFlag);
			stringBuffer.append(genModel.getImportedName("java.lang.String"));
			stringBuffer.append(TEXT_24);
			stringBuffer.append(genModel.getCopyrightFieldLiteral());
			stringBuffer.append(TEXT_25);
			stringBuffer.append(genModel.getNonNLS());
			stringBuffer.append(TEXT_26);
		}
		if (isImplementation && genModel.getDriverNumber() != null) {
			stringBuffer.append(TEXT_27);
			stringBuffer.append(genModel.getImportedName("java.lang.String"));
			stringBuffer.append(TEXT_28);
			stringBuffer.append(genModel.getDriverNumber());
			stringBuffer.append(TEXT_29);
			stringBuffer.append(genModel.getNonNLS());
			stringBuffer.append(TEXT_30);
		}
		if (isImplementation && genClass.isJavaIOSerializable()) {
			stringBuffer.append(TEXT_31);
		}
		if (isImplementation && genModel.isVirtualDelegation()) {
			String eVirtualValuesField = genClass.getEVirtualValuesField();
			if (eVirtualValuesField != null) {
				stringBuffer.append(TEXT_32);
				if (isGWT) {
					stringBuffer.append(TEXT_33);
					stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
				}
				stringBuffer.append(TEXT_34);
				stringBuffer.append(eVirtualValuesField);
				stringBuffer.append(TEXT_35);
			}
			{
				List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
				if (!eVirtualIndexBitFields.isEmpty()) {
					for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
						stringBuffer.append(TEXT_36);
						if (isGWT) {
							stringBuffer.append(TEXT_37);
							stringBuffer
									.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
						}
						stringBuffer.append(TEXT_38);
						stringBuffer.append(eVirtualIndexBitField);
						stringBuffer.append(TEXT_39);
					}
				}
			}
		}
		if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled()
				&& genModel.getBooleanFlagsReservedBits() == -1) {
			stringBuffer.append(TEXT_40);
			if (isGWT) {
				stringBuffer.append(TEXT_41);
				stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
			}
			stringBuffer.append(TEXT_42);
			stringBuffer.append(genModel.getBooleanFlagsField());
			stringBuffer.append(TEXT_43);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()) {
			for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
				if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType()
						&& !genFeature.isMapType()) {
					String rawListItemType = genFeature.getRawListItemType(genClass);
					int index = rawListItemType.indexOf('[');
					String head = rawListItemType;
					String tail = "";
					if (index != -1) {
						head = rawListItemType.substring(0, index);
						tail = rawListItemType.substring(index);
					}
					stringBuffer.append(TEXT_44);
					stringBuffer.append(genFeature.getGetArrayAccessor());
					stringBuffer.append(TEXT_45);
					stringBuffer.append(genFeature.getFormattedName());
					stringBuffer.append(TEXT_46);
					stringBuffer.append(genFeature.getGetArrayAccessor());
					stringBuffer.append(TEXT_47);
					if (genFeature.getQualifiedListItemType(genClass).contains("<")
							|| genFeature.getArrayItemType(genClass).contains("<")) {
						stringBuffer.append(TEXT_48);
					}
					stringBuffer.append(TEXT_49);
					stringBuffer.append(rawListItemType);
					stringBuffer.append(TEXT_50);
					stringBuffer.append(genFeature.getUpperName());
					stringBuffer.append(TEXT_51);
					stringBuffer.append(head);
					stringBuffer.append(TEXT_52);
					stringBuffer.append(tail);
					stringBuffer.append(TEXT_53);
				}
			}
			for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
				stringBuffer.append(TEXT_54);
				{
					//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.declaredFieldGenFeature.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

					InternalPatternContext ictx = (InternalPatternContext) ctx;
					new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
					stringBuffer.setLength(0);

					final Map<String, Object> callParameters = new HashMap<String, Object>();
					callParameters.put("genFeature", genFeature);
					callParameters.put("genClass", genClass);
					callParameters.put("genPackage", genPackage);
					callParameters.put("genModel", genModel);
					callParameters.put("isJDK50", isJDK50);
					callParameters.put("isInterface", isInterface);
					callParameters.put("isImplementation", isImplementation);
					callParameters.put("isGWT", isGWT);
					callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
					callParameters.put("singleWildcard", singleWildcard);
					callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
					callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
					callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
					callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
					CallHelper.executeWithParameterInjection(
							"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0jpGTmJ-Ed-FqczH3ESmRw",
							new ExecutionContext((InternalPatternContext) ctx), callParameters);
					stringBuffer.setLength(0);
				}

				//Class/declaredFieldGenFeature.override.javajetinc
			}
		}
		if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
			stringBuffer.append(TEXT_55);
			stringBuffer.append(genClass.getOffsetCorrectionField(null));
			stringBuffer.append(TEXT_56);
			stringBuffer.append(genClass.getQualifiedClassifierAccessor());
			stringBuffer.append(TEXT_57);
			stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
			stringBuffer.append(TEXT_58);
			stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
			stringBuffer.append(TEXT_59);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()) {
			for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
				GenFeature reverseFeature = genFeature.getReverse();
				if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
					stringBuffer.append(TEXT_60);
					stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
					stringBuffer.append(TEXT_61);
					stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
					stringBuffer.append(TEXT_62);
					stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
					stringBuffer.append(TEXT_63);
					stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
					stringBuffer.append(TEXT_64);
				}
			}
		}
		if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection()
				&& !genClass.getImplementedGenOperations().isEmpty()) {
			stringBuffer.append(TEXT_65);
			stringBuffer.append(genClass.getQualifiedClassifierAccessor());
			stringBuffer.append(TEXT_66);
			stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
			stringBuffer.append(TEXT_67);
			stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
			stringBuffer.append(TEXT_68);
		}
		if (isImplementation) {
			stringBuffer.append(TEXT_69);
			if (genModel.isPublicConstructors()) {
				stringBuffer.append(TEXT_70);
			} else {
				stringBuffer.append(TEXT_71);
			}
			stringBuffer.append(TEXT_72);
			stringBuffer.append(genClass.getClassName());
			stringBuffer.append(TEXT_73);
			stringBuffer.append(TEXT_74);
			{
				//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.egfCustom.constructor.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

				InternalPatternContext ictx = (InternalPatternContext) ctx;
				new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
				stringBuffer.setLength(0);

				final Map<String, Object> callParameters = new HashMap<String, Object>();
				callParameters.put("genClass", genClass);
				callParameters.put("genPackage", genPackage);
				callParameters.put("genModel", genModel);
				callParameters.put("isJDK50", isJDK50);
				callParameters.put("isInterface", isInterface);
				callParameters.put("isImplementation", isImplementation);
				callParameters.put("isGWT", isGWT);
				callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
				callParameters.put("singleWildcard", singleWildcard);
				callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
				callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
				callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
				callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
				CallHelper.executeWithParameterInjection(
						"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_JLBM0HroEeC0XN9kbwkPYQ",
						new ExecutionContext((InternalPatternContext) ctx), callParameters);
				stringBuffer.setLength(0);
			}

			for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
				stringBuffer.append(TEXT_75);
				stringBuffer.append(genClass.getFlagsField(genFeature));
				stringBuffer.append(TEXT_76);
				stringBuffer.append(genFeature.getUpperName());
				stringBuffer.append(TEXT_77);
				if (!genFeature.isBooleanType()) {
					stringBuffer.append(TEXT_78);
				}
				stringBuffer.append(TEXT_79);
			}
			stringBuffer.append(TEXT_80);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_81);
			}
			stringBuffer.append(TEXT_82);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
			stringBuffer.append(TEXT_83);
			stringBuffer.append(genClass.getQualifiedClassifierAccessor());
			stringBuffer.append(TEXT_84);
		}
		stringBuffer.append(TEXT_85);
		{
			//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.reflectiveDelegation.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

			InternalPatternContext ictx = (InternalPatternContext) ctx;
			new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
			stringBuffer.setLength(0);

			final Map<String, Object> callParameters = new HashMap<String, Object>();
			callParameters.put("genClass", genClass);
			callParameters.put("genPackage", genPackage);
			callParameters.put("genModel", genModel);
			callParameters.put("isJDK50", isJDK50);
			callParameters.put("isInterface", isInterface);
			callParameters.put("isImplementation", isImplementation);
			callParameters.put("isGWT", isGWT);
			callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
			callParameters.put("singleWildcard", singleWildcard);
			callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
			callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
			callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
			callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
			CallHelper.executeWithParameterInjection(
					"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0kFyMGJ-Ed-FqczH3ESmRw",
					new ExecutionContext((InternalPatternContext) ctx), callParameters);
			stringBuffer.setLength(0);
		}

		//Class/reflectiveDelegation.override.javajetinc
		if (isImplementation) {
			new Runnable() {
				public void run() {
					GenClass classExtendsGenClass = genClass.getClassExtendsGenClass();
					List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null
							? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
					for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
						stringBuffer.append(TEXT_86);
						{
							//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.genFeatureReified.override" args="genFeature:genFeature,classExtendsGenClass:classExtendsGenClass,classExtendsAllGenFeatures:classExtendsAllGenFeatures,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

							InternalPatternContext ictx = (InternalPatternContext) ctx;
							new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
							stringBuffer.setLength(0);

							final Map<String, Object> callParameters = new HashMap<String, Object>();
							callParameters.put("genFeature", genFeature);
							callParameters.put("classExtendsGenClass", classExtendsGenClass);
							callParameters.put("classExtendsAllGenFeatures", classExtendsAllGenFeatures);
							callParameters.put("genClass", genClass);
							callParameters.put("genPackage", genPackage);
							callParameters.put("genModel", genModel);
							callParameters.put("isJDK50", isJDK50);
							callParameters.put("isInterface", isInterface);
							callParameters.put("isImplementation", isImplementation);
							callParameters.put("isGWT", isGWT);
							callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
							callParameters.put("singleWildcard", singleWildcard);
							callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
							callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
							callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
							callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
							CallHelper.executeWithParameterInjection(
									"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_odzp0LM-EeKd56X4hcZPSw",
									new ExecutionContext((InternalPatternContext) ctx), callParameters);
							stringBuffer.setLength(0);
						}

						//Class/genFeatureReified.override.javajetinc
					}
				}
			}.run();
		}
		new Runnable() {
			public void run() {
				for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures()
						: genClass.getDeclaredGenFeatures())) {
					stringBuffer.append(TEXT_87);
					{
						//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.genFeature.override" args="genFeature:genFeature,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

						InternalPatternContext ictx = (InternalPatternContext) ctx;
						new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
						stringBuffer.setLength(0);

						final Map<String, Object> callParameters = new HashMap<String, Object>();
						callParameters.put("genFeature", genFeature);
						callParameters.put("genClass", genClass);
						callParameters.put("genPackage", genPackage);
						callParameters.put("genModel", genModel);
						callParameters.put("isJDK50", isJDK50);
						callParameters.put("isInterface", isInterface);
						callParameters.put("isImplementation", isImplementation);
						callParameters.put("isGWT", isGWT);
						callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
						callParameters.put("singleWildcard", singleWildcard);
						callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
						callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
						callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
						callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
						CallHelper.executeWithParameterInjection(
								"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0kO8IGJ-Ed-FqczH3ESmRw",
								new ExecutionContext((InternalPatternContext) ctx), callParameters);
						stringBuffer.setLength(0);
					}

					//Class/genFeature.override.javajetinc
				} //for
			}
		}.run();
		for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations()
				: genClass.getDeclaredGenOperations())) {
			stringBuffer.append(TEXT_88);
			{
				//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.genOperation.override" args="genOperation:genOperation,genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

				InternalPatternContext ictx = (InternalPatternContext) ctx;
				new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
				stringBuffer.setLength(0);

				final Map<String, Object> callParameters = new HashMap<String, Object>();
				callParameters.put("genOperation", genOperation);
				callParameters.put("genClass", genClass);
				callParameters.put("genPackage", genPackage);
				callParameters.put("genModel", genModel);
				callParameters.put("isJDK50", isJDK50);
				callParameters.put("isInterface", isInterface);
				callParameters.put("isImplementation", isImplementation);
				callParameters.put("isGWT", isGWT);
				callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
				callParameters.put("singleWildcard", singleWildcard);
				callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
				callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
				callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
				callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
				CallHelper.executeWithParameterInjection(
						"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0tBrwGJ-Ed-FqczH3ESmRw",
						new ExecutionContext((InternalPatternContext) ctx), callParameters);
				stringBuffer.setLength(0);
			}

			//Class/implementedGenOperation.override.javajetinc
		} //for
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
			stringBuffer.append(TEXT_89);
			if (genModel.useGenerics()) {
				for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
					if (genFeature.isUncheckedCast(genClass)) {
						stringBuffer.append(TEXT_90);
						break;
					}
				}
			}
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_91);
			}
			stringBuffer.append(TEXT_92);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(TEXT_93);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
			stringBuffer.append(TEXT_94);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(TEXT_95);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(TEXT_96);
			for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
				stringBuffer.append(TEXT_97);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(TEXT_98);
				if (genFeature.isListType()) {
					String cast = "(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList")
							+ (!genModel.useGenerics() ? ")"
									: "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)("
											+ genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList")
											+ "<?>)");
					if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
						stringBuffer.append(TEXT_99);
						stringBuffer.append(cast);
						stringBuffer.append(TEXT_100);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
						stringBuffer.append(TEXT_101);
						stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
						stringBuffer.append(TEXT_102);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_103);
					} else {
						stringBuffer.append(TEXT_104);
						stringBuffer.append(cast);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_105);
					}
				} else if (genFeature.isContainer()) {
					stringBuffer.append(TEXT_106);
					if (genFeature.isBasicSet()) {
						stringBuffer.append(TEXT_107);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(TEXT_108);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(TEXT_109);
					} else {
						stringBuffer.append(TEXT_110);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(TEXT_111);
					}
				} else {
					if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
						stringBuffer.append(TEXT_112);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(TEXT_113);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_114);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(TEXT_115);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(positiveOffsetCorrection);
						stringBuffer.append(TEXT_116);
					} else if (genFeature.isVolatile()
							|| genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
						stringBuffer.append(TEXT_117);
						stringBuffer.append(genFeature.getImportedType(genClass));
						stringBuffer.append(TEXT_118);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_119);
						if (genFeature.isResolveProxies()) {
							stringBuffer.append(TEXT_120);
							stringBuffer.append(genFeature.getAccessorName());
						} else {
							stringBuffer.append(genFeature.getGetAccessor());
						}
						stringBuffer.append(TEXT_121);
					}
					stringBuffer.append(TEXT_122);
					stringBuffer.append(genFeature.getSafeName());
					stringBuffer.append(TEXT_123);
					if (genFeature.isEffectiveContains()) {
						stringBuffer.append(TEXT_124);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
						stringBuffer.append(TEXT_125);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_126);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(negativeOffsetCorrection);
						stringBuffer.append(TEXT_127);
					} else {
						GenFeature reverseFeature = genFeature.getReverse();
						GenClass targetClass = reverseFeature.getGenClass();
						String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
								? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
						stringBuffer.append(TEXT_128);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
						stringBuffer.append(TEXT_129);
						stringBuffer.append(genFeature.getSafeName());
						stringBuffer.append(TEXT_130);
						stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
						stringBuffer.append(reverseOffsetCorrection);
						stringBuffer.append(TEXT_131);
						stringBuffer.append(targetClass.getRawImportedInterfaceName());
						stringBuffer.append(TEXT_132);
					}
					stringBuffer.append(TEXT_133);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(TEXT_134);
					stringBuffer.append(genFeature.getImportedType(genClass));
					stringBuffer.append(TEXT_135);
				}
			}
			stringBuffer.append(TEXT_136);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_137);
			} else {
				stringBuffer.append(TEXT_138);
			}
			stringBuffer.append(TEXT_139);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
			stringBuffer.append(TEXT_140);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_141);
			}
			stringBuffer.append(TEXT_142);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(TEXT_143);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
			stringBuffer.append(TEXT_144);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(TEXT_145);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(TEXT_146);
			for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
				stringBuffer.append(TEXT_147);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(TEXT_148);
				if (genFeature.isListType()) {
					if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
						stringBuffer.append(TEXT_149);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
						stringBuffer.append(singleWildcard);
						stringBuffer.append(TEXT_150);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
						stringBuffer.append(TEXT_151);
						stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
						stringBuffer.append(TEXT_152);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_153);
					} else if (genFeature.isWrappedFeatureMapType()) {
						stringBuffer.append(TEXT_154);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
						stringBuffer.append(singleWildcard);
						stringBuffer.append(TEXT_155);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(TEXT_156);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_157);
					} else {
						stringBuffer.append(TEXT_158);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
						stringBuffer.append(singleWildcard);
						stringBuffer.append(TEXT_159);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_160);
					}
				} else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
					stringBuffer.append(TEXT_161);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(TEXT_162);
				} else if (genFeature.isUnsettable()) {
					stringBuffer.append(TEXT_163);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(TEXT_164);
				} else {
					stringBuffer.append(TEXT_165);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(TEXT_166);
				}
			}
			stringBuffer.append(TEXT_167);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_168);
			} else {
				stringBuffer.append(TEXT_169);
			}
			stringBuffer.append(TEXT_170);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
			stringBuffer.append(TEXT_171);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_172);
			}
			stringBuffer.append(TEXT_173);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(TEXT_174);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
			stringBuffer.append(TEXT_175);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(TEXT_176);
			for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
				GenFeature reverseFeature = genFeature.getReverse();
				GenClass targetClass = reverseFeature.getGenClass();
				String reverseOffsetCorrection = targetClass.hasOffsetCorrection()
						? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
				stringBuffer.append(TEXT_177);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(TEXT_178);
				stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
				stringBuffer.append(reverseOffsetCorrection);
				stringBuffer.append(TEXT_179);
				stringBuffer.append(targetClass.getRawImportedInterfaceName());
				stringBuffer.append(TEXT_180);
			}
			stringBuffer.append(TEXT_181);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_182);
			} else {
				stringBuffer.append(TEXT_183);
			}
			stringBuffer.append(TEXT_184);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEGetGenFeatures())) {
			stringBuffer.append(TEXT_185);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_186);
			}
			stringBuffer.append(TEXT_187);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(TEXT_188);
			for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
				stringBuffer.append(TEXT_189);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(TEXT_190);
				if (genFeature.isPrimitiveType()) {
					if (isJDK50) {
						stringBuffer.append(TEXT_191);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_192);
					} else if (genFeature.isBooleanType()) {
						stringBuffer.append(TEXT_193);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_194);
					} else {
						stringBuffer.append(TEXT_195);
						stringBuffer.append(genFeature.getObjectType(genClass));
						stringBuffer.append(TEXT_196);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_197);
					}
				} else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
					stringBuffer.append(TEXT_198);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_199);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(TEXT_200);
				} else if (genFeature.isMapType()) {
					if (genFeature.isEffectiveSuppressEMFTypes()) {
						stringBuffer.append(TEXT_201);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
						stringBuffer.append(TEXT_202);
						stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
						stringBuffer.append(TEXT_203);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_204);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_205);
					} else {
						stringBuffer.append(TEXT_206);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_207);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_208);
					}
				} else if (genFeature.isWrappedFeatureMapType()) {
					stringBuffer.append(TEXT_209);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
					stringBuffer.append(TEXT_210);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_211);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_212);
				} else if (genFeature.isFeatureMapType()) {
					stringBuffer.append(TEXT_213);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_214);
					stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
					stringBuffer.append(TEXT_215);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_216);
				} else {
					stringBuffer.append(TEXT_217);
					stringBuffer.append(genFeature.getGetAccessor());
					stringBuffer.append(TEXT_218);
				}
			}
			stringBuffer.append(TEXT_219);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_220);
			} else {
				stringBuffer.append(TEXT_221);
			}
			stringBuffer.append(TEXT_222);
		}
		// begin-CDO : enable derived features in reflective way when using feature delagation
		if (isImplementation && genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEGetGenFeatures())) {
			stringBuffer.append(TEXT_223);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_224);
			}
			stringBuffer.append(TEXT_225);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(TEXT_226);
			for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
				if (genFeature.isDerived()) {
					stringBuffer.append(TEXT_227);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(TEXT_228);
					if (genFeature.isPrimitiveType()) {
						if (genFeature.isBooleanType()) {
							stringBuffer.append(TEXT_229);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_230);
						} else {
							stringBuffer.append(TEXT_231);
							stringBuffer.append(genFeature.getObjectType(genClass));
							stringBuffer.append(TEXT_232);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_233);
						}
					} else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
						stringBuffer.append(TEXT_234);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_235);
					} else if (genFeature.isMapType()) {
						if (genFeature.isEffectiveSuppressEMFTypes()) {
							stringBuffer.append(TEXT_236);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
							stringBuffer.append(TEXT_237);
							stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
							stringBuffer.append(TEXT_238);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_239);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_240);
						} else {
							stringBuffer.append(TEXT_241);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_242);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_243);
						}
					} else if (genFeature.isWrappedFeatureMapType()) {
						stringBuffer.append(TEXT_244);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(TEXT_245);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_246);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_247);
					} else if (genFeature.isFeatureMapType()) {
						stringBuffer.append(TEXT_248);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_249);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(TEXT_250);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_251);
					} else {
						stringBuffer.append(TEXT_252);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_253);
					}
				} // fin isDerived
			}
			stringBuffer.append(TEXT_254);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_255);
			} else {
				stringBuffer.append(TEXT_256);
			}
			stringBuffer.append(TEXT_257);
		}
		// end-CDO 
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getESetGenFeatures())) {
			stringBuffer.append(TEXT_258);
			if (genModel.useGenerics()) {
				for (GenFeature genFeature : genClass.getESetGenFeatures()) {
					if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType()
							&& !genFeature.isMapType()) {
						stringBuffer.append(TEXT_259);
						break;
					}
				}
			}
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_260);
			}
			stringBuffer.append(TEXT_261);
			stringBuffer.append(negativeOffsetCorrection);
			stringBuffer.append(TEXT_262);
			for (GenFeature genFeature : genClass.getESetGenFeatures()) {
				stringBuffer.append(TEXT_263);
				stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
				stringBuffer.append(TEXT_264);
				if (genFeature.isListType()) {
					if (genFeature.isWrappedFeatureMapType()) {
						stringBuffer.append(TEXT_265);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(TEXT_266);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(TEXT_267);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_268);
					} else if (genFeature.isFeatureMapType()) {
						stringBuffer.append(TEXT_269);
						stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
						stringBuffer.append(TEXT_270);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_271);
					} else if (genFeature.isMapType()) {
						if (genFeature.isEffectiveSuppressEMFTypes()) {
							stringBuffer.append(TEXT_272);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
							stringBuffer.append(TEXT_273);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
							stringBuffer.append(TEXT_274);
							stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
							stringBuffer.append(TEXT_275);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_276);
						} else {
							stringBuffer.append(TEXT_277);
							stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
							stringBuffer.append(TEXT_278);
							stringBuffer.append(genFeature.getGetAccessor());
							stringBuffer.append(TEXT_279);
						}
					} else {
						stringBuffer.append(TEXT_280);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_281);
						stringBuffer.append(genFeature.getGetAccessor());
						stringBuffer.append(TEXT_282);
						stringBuffer.append(genModel.getImportedName("java.util.Collection"));
						if (isJDK50) {
							stringBuffer.append(TEXT_283);
							stringBuffer.append(genFeature.getListItemType(genClass));
							stringBuffer.append(TEXT_284);
						}
						stringBuffer.append(TEXT_285);
					}
				} else if (!isJDK50 && genFeature.isPrimitiveType()) {
					stringBuffer.append(TEXT_286);
					stringBuffer.append(genFeature.getAccessorName());
					stringBuffer.append(TEXT_287);
					stringBuffer.append(genFeature.getObjectType(genClass));
					stringBuffer.append(TEXT_288);
					stringBuffer.append(genFeature.getPrimitiveValueFunction());
					stringBuffer.append(TEXT_289);
				} else {
					if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()
							|| !genFeature.getRawType().equals(genFeature.getType(genClass))) {
						if (!genFeature.getEcoreFeature().getEGenericType().getETypeArguments().isEmpty()) {
							stringBuffer.append(TEXT_290);
							stringBuffer.append(genFeature.getRawImportedBoundType());
							stringBuffer.append(TEXT_291);
						}
						stringBuffer.append(TEXT_292);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(TEXT_293);
						stringBuffer.append(genFeature.getObjectType(genClass));
						stringBuffer.append(TEXT_294);
						if (!genFeature.getEcoreFeature().getEGenericType().getETypeArguments().isEmpty()) {
							stringBuffer.append(TEXT_295);
						}
					} else {
						stringBuffer.append(TEXT_296);
						stringBuffer.append(genFeature.getAccessorName());
						stringBuffer.append(TEXT_297);
					}
				}
				stringBuffer.append(TEXT_298);
			}
			stringBuffer.append(TEXT_299);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_300);
			} else {
				stringBuffer.append(TEXT_301);
			}
			stringBuffer.append(TEXT_302);
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
			stringBuffer.append(TEXT_303);
			{
				//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.eUnset.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

				InternalPatternContext ictx = (InternalPatternContext) ctx;
				new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
				stringBuffer.setLength(0);

				final Map<String, Object> callParameters = new HashMap<String, Object>();
				callParameters.put("genClass", genClass);
				callParameters.put("genPackage", genPackage);
				callParameters.put("genModel", genModel);
				callParameters.put("isJDK50", isJDK50);
				callParameters.put("isInterface", isInterface);
				callParameters.put("isImplementation", isImplementation);
				callParameters.put("isGWT", isGWT);
				callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
				callParameters.put("singleWildcard", singleWildcard);
				callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
				callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
				callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
				callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
				CallHelper.executeWithParameterInjection(
						"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0uN-kGJ-Ed-FqczH3ESmRw",
						new ExecutionContext((InternalPatternContext) ctx), callParameters);
				stringBuffer.setLength(0);
			}

			//Class/eUnset.override.javajetinc
		}
		if (isImplementation && !genModel.isReflectiveDelegation()
				&& genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
			stringBuffer.append(TEXT_304);
			{
				//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.eIsSet.override" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

				InternalPatternContext ictx = (InternalPatternContext) ctx;
				new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
				stringBuffer.setLength(0);

				final Map<String, Object> callParameters = new HashMap<String, Object>();
				callParameters.put("genClass", genClass);
				callParameters.put("genPackage", genPackage);
				callParameters.put("genModel", genModel);
				callParameters.put("isJDK50", isJDK50);
				callParameters.put("isInterface", isInterface);
				callParameters.put("isImplementation", isImplementation);
				callParameters.put("isGWT", isGWT);
				callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
				callParameters.put("singleWildcard", singleWildcard);
				callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
				callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
				callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
				callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
				CallHelper.executeWithParameterInjection(
						"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0uXvkGJ-Ed-FqczH3ESmRw",
						new ExecutionContext((InternalPatternContext) ctx), callParameters);
				stringBuffer.setLength(0);
			}

			//Class/eIsSet.override.javajetinc
		}
		if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty()
				|| genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
			if (!genClass.getMixinGenFeatures().isEmpty()) {
				stringBuffer.append(TEXT_305);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_306);
				}
				stringBuffer.append(TEXT_307);
				stringBuffer.append(singleWildcard);
				stringBuffer.append(TEXT_308);
				for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
					stringBuffer.append(TEXT_309);
					stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
					stringBuffer.append(TEXT_310);
					stringBuffer.append(negativeOffsetCorrection);
					stringBuffer.append(TEXT_311);
					for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
						stringBuffer.append(TEXT_312);
						stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(TEXT_313);
						stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
						stringBuffer.append(TEXT_314);
					}
					stringBuffer.append(TEXT_315);
				}
				stringBuffer.append(TEXT_316);
			}
			stringBuffer.append(TEXT_317);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_318);
			}
			stringBuffer.append(TEXT_319);
			stringBuffer.append(singleWildcard);
			stringBuffer.append(TEXT_320);
			for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
				stringBuffer.append(TEXT_321);
				stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
				stringBuffer.append(TEXT_322);
				for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
					stringBuffer.append(TEXT_323);
					stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(TEXT_324);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(TEXT_325);
				}
				stringBuffer.append(TEXT_326);
			}
			if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
				stringBuffer.append(TEXT_327);
				stringBuffer.append(genClass.getRawImportedInterfaceName());
				stringBuffer.append(TEXT_328);
				stringBuffer.append(negativeOffsetCorrection);
				stringBuffer.append(TEXT_329);
				for (GenFeature genFeature : genClass.getGenFeatures()) {
					stringBuffer.append(TEXT_330);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(TEXT_331);
					stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
					stringBuffer.append(positiveOffsetCorrection);
					stringBuffer.append(TEXT_332);
				}
				stringBuffer.append(TEXT_333);
			}
			stringBuffer.append(TEXT_334);
		}
		if (genModel.isOperationReflection() && isImplementation
				&& (!genClass.getMixinGenOperations().isEmpty()
						|| !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(),
								genClass.getImplementedGenOperations()).isEmpty()
						|| genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
			stringBuffer.append(TEXT_335);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_336);
			}
			stringBuffer.append(TEXT_337);
			stringBuffer.append(singleWildcard);
			stringBuffer.append(TEXT_338);
			for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) {
				List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations();
				List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
				if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations)
						.isEmpty()) {
					stringBuffer.append(TEXT_339);
					stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
					stringBuffer.append(TEXT_340);
					for (GenOperation genOperation : extendedImplementedGenOperations) {
						GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
						if (implementedGenOperations.contains(overrideGenOperation)) {
							stringBuffer.append(TEXT_341);
							stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
							stringBuffer.append(TEXT_342);
							stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
							stringBuffer.append(positiveOperationOffsetCorrection);
							stringBuffer.append(TEXT_343);
						}
					}
					stringBuffer.append(TEXT_344);
				}
			}
			for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
				stringBuffer.append(TEXT_345);
				stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
				stringBuffer.append(TEXT_346);
				for (GenOperation genOperation : mixinGenClass.getGenOperations()) {
					GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
					stringBuffer.append(TEXT_347);
					stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
					stringBuffer.append(TEXT_348);
					stringBuffer.append(genClass.getQualifiedOperationID(
							overrideGenOperation != null ? overrideGenOperation : genOperation));
					stringBuffer.append(positiveOperationOffsetCorrection);
					stringBuffer.append(TEXT_349);
				}
				stringBuffer.append(TEXT_350);
			}
			if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
				stringBuffer.append(TEXT_351);
				stringBuffer.append(genClass.getRawImportedInterfaceName());
				stringBuffer.append(TEXT_352);
				stringBuffer.append(negativeOperationOffsetCorrection);
				stringBuffer.append(TEXT_353);
				for (GenOperation genOperation : genClass.getGenOperations()) {
					stringBuffer.append(TEXT_354);
					stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
					stringBuffer.append(TEXT_355);
					stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
					stringBuffer.append(positiveOperationOffsetCorrection);
					stringBuffer.append(TEXT_356);
				}
				stringBuffer.append(TEXT_357);
			}
			stringBuffer.append(TEXT_358);
		}
		if (isImplementation && genModel.isVirtualDelegation()) {
			String eVirtualValuesField = genClass.getEVirtualValuesField();
			if (eVirtualValuesField != null) {
				stringBuffer.append(TEXT_359);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_360);
				}
				stringBuffer.append(TEXT_361);
				stringBuffer.append(eVirtualValuesField);
				stringBuffer.append(TEXT_362);
				if (genModel.useClassOverrideAnnotation()) {
					stringBuffer.append(TEXT_363);
				}
				stringBuffer.append(TEXT_364);
				stringBuffer.append(eVirtualValuesField);
				stringBuffer.append(TEXT_365);
			}
			{
				List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
				if (!eVirtualIndexBitFields.isEmpty()) {
					List<String> allEVirtualIndexBitFields = genClass
							.getAllEVirtualIndexBitFields(new ArrayList<String>());
					stringBuffer.append(TEXT_366);
					if (genModel.useClassOverrideAnnotation()) {
						stringBuffer.append(TEXT_367);
					}
					stringBuffer.append(TEXT_368);
					for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
						stringBuffer.append(TEXT_369);
						stringBuffer.append(i);
						stringBuffer.append(TEXT_370);
						stringBuffer.append(allEVirtualIndexBitFields.get(i));
						stringBuffer.append(TEXT_371);
					}
					stringBuffer.append(TEXT_372);
					if (genModel.useClassOverrideAnnotation()) {
						stringBuffer.append(TEXT_373);
					}
					stringBuffer.append(TEXT_374);
					for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
						stringBuffer.append(TEXT_375);
						stringBuffer.append(i);
						stringBuffer.append(TEXT_376);
						stringBuffer.append(allEVirtualIndexBitFields.get(i));
						stringBuffer.append(TEXT_377);
					}
					stringBuffer.append(TEXT_378);
				}
			}
		}
		if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
			stringBuffer.append(TEXT_379);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_380);
			}
			if (genModel.useGenerics()) {
				boolean isUnchecked = false;
				boolean isRaw = false;
				LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods()
						? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) {
					for (GenParameter genParameter : genOperation.getGenParameters()) {
						if (genParameter.isUncheckedCast()) {
							if (genParameter.getTypeGenDataType() == null
									|| !genParameter.getTypeGenDataType().isObjectType()) {
								isUnchecked = true;
							}
							if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter()
									.getEGenericType().getETypeArguments().isEmpty()) {
								isRaw = true;
								break LOOP;
							}
						}
					}
				}
				if (isUnchecked) {
					stringBuffer.append(TEXT_381);
					if (!isRaw) {
						stringBuffer.append(TEXT_382);
					} else {
						stringBuffer.append(TEXT_383);
					}
					stringBuffer.append(TEXT_384);
				}
			}
			stringBuffer.append(TEXT_385);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
			stringBuffer.append(singleWildcard);
			stringBuffer.append(TEXT_386);
			stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException"
					: "java.lang.reflect.InvocationTargetException"));
			stringBuffer.append(TEXT_387);
			stringBuffer.append(negativeOperationOffsetCorrection);
			stringBuffer.append(TEXT_388);
			for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods()
					? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) {
				List<GenParameter> genParameters = genOperation.getGenParameters();
				int size = genParameters.size();
				boolean hasCheckedException = genOperation.hasCheckedException();
				String indent = hasCheckedException ? "\t" : "";
				GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
				stringBuffer.append(TEXT_389);
				stringBuffer.append(genClass
						.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
				stringBuffer.append(TEXT_390);
				if (hasCheckedException) {
					stringBuffer.append(TEXT_391);
					/*}*/}
				if (genOperation.isVoid()) {
					stringBuffer.append(TEXT_392);
					stringBuffer.append(indent);
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(TEXT_393);
					for (int i = 0; i < size; i++) {
						GenParameter genParameter = genParameters.get(i);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(TEXT_394);
						}
						if (genParameter.getTypeGenDataType() == null
								|| !genParameter.getTypeGenDataType().isObjectType()
								|| !genParameter.usesOperationTypeParameters()
										&& !genParameter.getRawType().equals(genParameter.getType(genClass))) {
							stringBuffer.append(TEXT_395);
							stringBuffer.append(genParameter.usesOperationTypeParameters()
									? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
							stringBuffer.append(TEXT_396);
						}
						stringBuffer.append(TEXT_397);
						stringBuffer.append(i);
						stringBuffer.append(TEXT_398);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(TEXT_399);
							stringBuffer.append(genParameter.getPrimitiveValueFunction());
							stringBuffer.append(TEXT_400);
						}
						if (i < (size - 1)) {
							stringBuffer.append(TEXT_401);
						}
					}
					stringBuffer.append(TEXT_402);
					stringBuffer.append(indent);
					stringBuffer.append(TEXT_403);
				} else {
					stringBuffer.append(TEXT_404);
					stringBuffer.append(indent);
					stringBuffer.append(TEXT_405);
					if (!isJDK50 && genOperation.isPrimitiveType()) {
						stringBuffer.append(TEXT_406);
						stringBuffer.append(genOperation.getObjectType(genClass));
						stringBuffer.append(TEXT_407);
					}
					stringBuffer.append(genOperation.getName());
					stringBuffer.append(TEXT_408);
					for (int i = 0; i < size; i++) {
						GenParameter genParameter = genParameters.get(i);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(TEXT_409);
						}
						if (genParameter.getTypeGenDataType() == null
								|| !genParameter.getTypeGenDataType().isObjectType()
								|| !genParameter.usesOperationTypeParameters()
										&& !genParameter.getRawType().equals(genParameter.getType(genClass))) {
							stringBuffer.append(TEXT_410);
							stringBuffer.append(genParameter.usesOperationTypeParameters()
									? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
							stringBuffer.append(TEXT_411);
						}
						stringBuffer.append(TEXT_412);
						stringBuffer.append(i);
						stringBuffer.append(TEXT_413);
						if (!isJDK50 && genParameter.isPrimitiveType()) {
							stringBuffer.append(TEXT_414);
							stringBuffer.append(genParameter.getPrimitiveValueFunction());
							stringBuffer.append(TEXT_415);
						}
						if (i < (size - 1)) {
							stringBuffer.append(TEXT_416);
						}
					}
					stringBuffer.append(TEXT_417);
					if (!isJDK50 && genOperation.isPrimitiveType()) {
						stringBuffer.append(TEXT_418);
					}
					stringBuffer.append(TEXT_419);
				}
				if (hasCheckedException) {/*{*/
					stringBuffer.append(TEXT_420);
					stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
					stringBuffer.append(TEXT_421);
					stringBuffer.append(
							genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException"
									: "java.lang.reflect.InvocationTargetException"));
					stringBuffer.append(TEXT_422);
				}
			}
			stringBuffer.append(TEXT_423);
			if (genModel.isMinimalReflectiveMethods()) {
				stringBuffer.append(TEXT_424);
			} else {
				stringBuffer.append(TEXT_425);
			}
			stringBuffer.append(TEXT_426);
		}
		if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation()
				&& !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
			stringBuffer.append(TEXT_427);
			if (genModel.useClassOverrideAnnotation()) {
				stringBuffer.append(TEXT_428);
			}
			stringBuffer.append(TEXT_429);
			{
				boolean first = true;
				for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
					if (!genFeature.getName().equals("description")) {
						if (first) {
							first = false;
							stringBuffer.append(TEXT_430);
							stringBuffer.append(genFeature.getName());
							stringBuffer.append(TEXT_431);
							stringBuffer.append(genModel.getNonNLS());
						} else {
							stringBuffer.append(TEXT_432);
							stringBuffer.append(genFeature.getName());
							stringBuffer.append(TEXT_433);
							stringBuffer.append(genModel.getNonNLS());
						}
						if (genFeature.isUnsettable() && !genFeature.isListType()) {
							if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
								stringBuffer.append(TEXT_434);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								stringBuffer.append(TEXT_435);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								stringBuffer.append(TEXT_436);
								stringBuffer.append(genModel.getNonNLS());
							} else {
								if (genClass.isFlag(genFeature)) {
									if (genFeature.isBooleanType()) {
										stringBuffer.append(TEXT_437);
										if (genClass.isESetFlag(genFeature)) {
											stringBuffer.append(TEXT_438);
											stringBuffer.append(genClass.getESetFlagsField(genFeature));
											stringBuffer.append(TEXT_439);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(TEXT_440);
										} else {
											stringBuffer.append(genFeature.getUncapName());
											stringBuffer.append(TEXT_441);
										}
										stringBuffer.append(TEXT_442);
										stringBuffer.append(genClass.getFlagsField(genFeature));
										stringBuffer.append(TEXT_443);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_444);
										stringBuffer.append(genModel.getNonNLS());
									} else {
										stringBuffer.append(TEXT_445);
										if (genClass.isESetFlag(genFeature)) {
											stringBuffer.append(TEXT_446);
											stringBuffer.append(genClass.getESetFlagsField(genFeature));
											stringBuffer.append(TEXT_447);
											stringBuffer.append(genFeature.getUpperName());
											stringBuffer.append(TEXT_448);
										} else {
											stringBuffer.append(genFeature.getUncapName());
											stringBuffer.append(TEXT_449);
										}
										stringBuffer.append(TEXT_450);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_451);
										stringBuffer.append(genClass.getFlagsField(genFeature));
										stringBuffer.append(TEXT_452);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_453);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_454);
										stringBuffer.append(genModel.getNonNLS());
									}
								} else {
									stringBuffer.append(TEXT_455);
									if (genClass.isESetFlag(genFeature)) {
										stringBuffer.append(TEXT_456);
										stringBuffer.append(genClass.getESetFlagsField(genFeature));
										stringBuffer.append(TEXT_457);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_458);
									} else {
										stringBuffer.append(genFeature.getUncapName());
										stringBuffer.append(TEXT_459);
									}
									stringBuffer.append(TEXT_460);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(TEXT_461);
									stringBuffer.append(genModel.getNonNLS());
								}
							}
						} else {
							if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
								stringBuffer.append(TEXT_462);
								stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
								stringBuffer.append(positiveOffsetCorrection);
								if (!genFeature.isListType() && !genFeature.isReferenceType()) {
									stringBuffer.append(TEXT_463);
									stringBuffer.append(genFeature.getEDefault());
								}
								stringBuffer.append(TEXT_464);
							} else {
								if (genClass.isFlag(genFeature)) {
									if (genFeature.isBooleanType()) {
										stringBuffer.append(TEXT_465);
										stringBuffer.append(genClass.getFlagsField(genFeature));
										stringBuffer.append(TEXT_466);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_467);
									} else {
										stringBuffer.append(TEXT_468);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_469);
										stringBuffer.append(genClass.getFlagsField(genFeature));
										stringBuffer.append(TEXT_470);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_471);
										stringBuffer.append(genFeature.getUpperName());
										stringBuffer.append(TEXT_472);
									}
								} else {
									stringBuffer.append(TEXT_473);
									stringBuffer.append(genFeature.getSafeName());
									stringBuffer.append(TEXT_474);
								}
							}
						}
					}
				}
			}
			stringBuffer.append(TEXT_475);
		}
		if (isImplementation && genClass.isMapEntry()) {
			GenFeature keyFeature = genClass.getMapEntryKeyFeature();
			GenFeature valueFeature = genClass.getMapEntryValueFeature();
			String objectType = genModel.getImportedName("java.lang.Object");
			String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
			String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
			String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap")
					+ (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
			stringBuffer.append(TEXT_476);
			if (isGWT) {
				stringBuffer.append(TEXT_477);
				stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
			}
			stringBuffer.append(TEXT_478);
			stringBuffer.append(objectType);
			stringBuffer.append(TEXT_479);
			stringBuffer.append(keyType);
			stringBuffer.append(TEXT_480);
			if (!isJDK50 && keyFeature.isPrimitiveType()) {
				stringBuffer.append(TEXT_481);
				stringBuffer.append(keyFeature.getObjectType(genClass));
				stringBuffer.append(TEXT_482);
			} else {
				stringBuffer.append(TEXT_483);
			}
			stringBuffer.append(TEXT_484);
			stringBuffer.append(keyType);
			stringBuffer.append(TEXT_485);
			if (keyFeature.isListType()) {
				stringBuffer.append(TEXT_486);
				if (!genModel.useGenerics()) {
					stringBuffer.append(TEXT_487);
					stringBuffer.append(genModel.getImportedName("java.util.Collection"));
					stringBuffer.append(TEXT_488);
				}
				stringBuffer.append(TEXT_489);
			} else if (isJDK50) {
				stringBuffer.append(TEXT_490);
			} else if (keyFeature.isPrimitiveType()) {
				stringBuffer.append(TEXT_491);
				stringBuffer.append(keyFeature.getObjectType(genClass));
				stringBuffer.append(TEXT_492);
				stringBuffer.append(keyFeature.getPrimitiveValueFunction());
				stringBuffer.append(TEXT_493);
			} else {
				stringBuffer.append(TEXT_494);
				stringBuffer.append(keyFeature.getImportedType(genClass));
				stringBuffer.append(TEXT_495);
			}
			stringBuffer.append(TEXT_496);
			stringBuffer.append(valueType);
			stringBuffer.append(TEXT_497);
			if (!isJDK50 && valueFeature.isPrimitiveType()) {
				stringBuffer.append(TEXT_498);
				stringBuffer.append(valueFeature.getObjectType(genClass));
				stringBuffer.append(TEXT_499);
			} else {
				stringBuffer.append(TEXT_500);
			}
			stringBuffer.append(TEXT_501);
			stringBuffer.append(valueType);
			stringBuffer.append(TEXT_502);
			stringBuffer.append(valueType);
			stringBuffer.append(TEXT_503);
			stringBuffer.append(valueType);
			stringBuffer.append(TEXT_504);
			if (valueFeature.isListType()) {
				stringBuffer.append(TEXT_505);
				if (!genModel.useGenerics()) {
					stringBuffer.append(TEXT_506);
					stringBuffer.append(genModel.getImportedName("java.util.Collection"));
					stringBuffer.append(TEXT_507);
				}
				stringBuffer.append(TEXT_508);
			} else if (isJDK50) {
				stringBuffer.append(TEXT_509);
			} else if (valueFeature.isPrimitiveType()) {
				stringBuffer.append(TEXT_510);
				stringBuffer.append(valueFeature.getObjectType(genClass));
				stringBuffer.append(TEXT_511);
				stringBuffer.append(valueFeature.getPrimitiveValueFunction());
				stringBuffer.append(TEXT_512);
			} else {
				stringBuffer.append(TEXT_513);
				stringBuffer.append(valueFeature.getImportedType(genClass));
				stringBuffer.append(TEXT_514);
			}
			stringBuffer.append(TEXT_515);
			if (genModel.useGenerics()) {
				stringBuffer.append(TEXT_516);
			}
			stringBuffer.append(TEXT_517);
			stringBuffer.append(eMapType);
			stringBuffer.append(TEXT_518);
			stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
			stringBuffer.append(TEXT_519);
			stringBuffer.append(eMapType);
			stringBuffer.append(TEXT_520);
		}
		stringBuffer.append(TEXT_521);
		{
			//<%@ egf:patternCall patternId="platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#LogicalName=org.eclipse.egf.emf.pattern.model.call.Class.Class.insert" args="genClass:genClass,genPackage:genPackage,genModel:genModel,isJDK50:isJDK50,isInterface:isInterface,isImplementation:isImplementation,isGWT:isGWT,publicStaticFinalFlag:publicStaticFinalFlag,singleWildcard:singleWildcard,negativeOffsetCorrection:negativeOffsetCorrection,positiveOffsetCorrection:positiveOffsetCorrection,negativeOperationOffsetCorrection:negativeOperationOffsetCorrection,positiveOperationOffsetCorrection:positiveOperationOffsetCorrection"%>

			InternalPatternContext ictx = (InternalPatternContext) ctx;
			new Node.DataLeaf(ictx.getNode(), getClass(), null, stringBuffer.toString());
			stringBuffer.setLength(0);

			final Map<String, Object> callParameters = new HashMap<String, Object>();
			callParameters.put("genClass", genClass);
			callParameters.put("genPackage", genPackage);
			callParameters.put("genModel", genModel);
			callParameters.put("isJDK50", isJDK50);
			callParameters.put("isInterface", isInterface);
			callParameters.put("isImplementation", isImplementation);
			callParameters.put("isGWT", isGWT);
			callParameters.put("publicStaticFinalFlag", publicStaticFinalFlag);
			callParameters.put("singleWildcard", singleWildcard);
			callParameters.put("negativeOffsetCorrection", negativeOffsetCorrection);
			callParameters.put("positiveOffsetCorrection", positiveOffsetCorrection);
			callParameters.put("negativeOperationOffsetCorrection", negativeOperationOffsetCorrection);
			callParameters.put("positiveOperationOffsetCorrection", positiveOperationOffsetCorrection);
			CallHelper.executeWithParameterInjection(
					"platform:/plugin/org.eclipse.egf.emf.pattern/egf/EMF_Pattern.fcore#_0ug5gGJ-Ed-FqczH3ESmRw",
					new ExecutionContext((InternalPatternContext) ctx), callParameters);
			stringBuffer.setLength(0);
		}

		stringBuffer.append(TEXT_522);
		stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
		// TODO fix the space above
		genModel.emitSortedImports();
		InternalPatternContext ictx = (InternalPatternContext) ctx;
		new Node.DataLeaf(ictx.getNode(), getClass(), "doGenerate", stringBuffer.toString());
	}
}