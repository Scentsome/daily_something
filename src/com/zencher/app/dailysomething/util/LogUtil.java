package com.zencher.app.dailysomething.util;

import android.util.Log;

/**
 * Class to display the log NOTE! Caution! It can be to false always isDebug before production release
 * isDebug log display's true, toast display
 */
public class LogUtil {

	private static final String TAG = "LogUtil";
	public static boolean isDebug = false;

	public static void d() {
		if (isDebug) {
			Log.d(TAG, getMetaInfo());
		}
	}

	public static void d(String message) {
        if (isDebug) {
            Log.d(TAG, getMetaInfo() + null2str(message));
        }
    }

	public static void d(String tag, String message) {
		if (isDebug) {
			Log.d(tag + "[" + TAG + "]", getMetaInfo() + null2str(message));
		}
	}

	public static void i() {
		if (isDebug) {
			Log.i(TAG, getMetaInfo());
		}
	}

	public static void i(String message) {
		if (isDebug) {
			Log.i(TAG, getMetaInfo() + null2str(message));
		}
	}

	public static void i(String tag, String message) {
		if (isDebug) {
			Log.i(tag + "[" + TAG + "]", getMetaInfo() + null2str(message));
		}
	}

	public static void w() {
		if (isDebug) {
			Log.w(TAG, getMetaInfo());
		}
	}

	public static void w(String message) {
		if (isDebug) {
			Log.w(TAG, getMetaInfo() + null2str(message));
		}
	}

	public static void w(String tag, String message) {
		if (isDebug) {
			Log.w(tag + "[" + TAG + "]", getMetaInfo() + null2str(message));
		}
	}
	
	private static String null2str(String string) {
		if (string == null) {
			return "(null)";
		}
		return string;
	}

	/**
	 * Get the log the caller's meta-information
	 * 
	 * @return [className#methodName:line]
	 */
	private static String getMetaInfo() {
		// Get information from the stack trace // 0: VM, 1: Thread, 2: LogUtil#getMetaInfo, 3:
		// Such as LogUtil # d, 4: caller
		final StackTraceElement element = Thread.currentThread().getStackTrace()[4];
		return LogUtil.getMetaInfo(element);
	}

	/**
	 * Class name from the stack trace, the method name, get the number of rows
	 * 
	 * @return [className#methodName:line]
	 */
	public static String getMetaInfo(StackTraceElement element) {
		// Class names, method names, get the number of rows
		final String fullClassName = element.getClassName();
		final String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		final String methodName = element.getMethodName();
		final int lineNumber = element.getLineNumber();
		// Meta-information
		final String metaInfo = "[" + simpleClassName + "#" + methodName + ":" + lineNumber + "]";
		return metaInfo;
	}
}